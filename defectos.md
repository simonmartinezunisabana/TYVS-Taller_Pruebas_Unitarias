# Registro de Defectos — Taller de Pruebas Unitarias (Registraduría)

Defectos encontrados durante el desarrollo TDD de `Registry.registerVoter`, en el orden en que aparecieron.

---

### Defecto 01
- **Caso de prueba**: Documento inválido (`id <= 0`).
- **Entrada**: `Person(name="Ana", id=0/-1/-100, age=30, gender=FEMALE, alive=true)`
- **Resultado esperado**: `INVALID`
- **Resultado obtenido**: `VALID`
- **Causa probable**: `Registry` no validaba el número de documento antes de aceptar el registro.
- **Estado**: Resuelto — corregido agregando `p.getId() <= 0` a la guarda de `INVALID`, verificado con `shouldRejectWhenDocumentIsZeroOrNegative`.

---

### Defecto 02
- **Caso de prueba**: Edad fuera del rango biológico posible.
- **Entrada**: `Person(name="Ana", id=1, age=-1/121/-100/200, gender=FEMALE, alive=true)`
- **Resultado esperado**: `INVALID_AGE`
- **Resultado obtenido**: `VALID`
- **Causa probable**: No existía validación del rango `0 <= edad <= 120`.
- **Estado**: Resuelto — corregido con la guarda de `INVALID_AGE` usando las constantes `MIN_AGE`/`MAX_AGE`, verificado con `shouldRejectWhenAgeIsOutOfBiologicalRange`.

---

### Defecto 03
- **Caso de prueba**: Persona menor de edad.
- **Entrada**: `Person(name="Ana", id=1, age=0/17, gender=FEMALE, alive=true)`
- **Resultado esperado**: `UNDERAGE`
- **Resultado obtenido**: `VALID`
- **Causa probable**: No se evaluaba la mayoría de edad (`edad < 18`).
- **Estado**: Resuelto — corregido con la guarda de `UNDERAGE`, verificado con `shouldRejectWhenPersonIsUnderage`.

---

### Defecto 04
- **Caso de prueba**: Registro duplicado con el mismo `id`.
- **Entradas**: `Person(name="Carlos", id=1, age=40, alive=true)` seguido de `Person(name="Ana", id=1, age=30, alive=true)`.
- **Resultado esperado**: 1ª → `VALID`, 2ª → `DUPLICATED`.
- **Resultado obtenido**: ambas → `VALID`.
- **Causa probable**: `Registry` no tenía memoria de los `id` ya registrados (no había estado de instancia).
- **Estado**: Resuelto — corregido agregando `Set<Integer> usedIds`, verificado con `shouldRejectDuplicatedId`.

---

## Convenciones de Estado
| Estado | Significado |
|---------|-------------|
| **Abierto** | El defecto fue detectado pero no corregido. |
| **En progreso** | El defecto se encuentra en análisis o corrección. |
| **Resuelto** | El defecto fue corregido y validado mediante pruebas. |