package edu.unisabana.tyvs.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Pruebas del modelo puro Person.
 *
 * Registry no usa getName() ni getGender() en ninguna regla de negocio
 * (solo consulta getId(), getAge() e isAlive()), por eso esos dos getters
 * quedaban sin cobertura y con mutantes sin matar (NO_COVERAGE) en el reporte
 * de PIT: se ejecutan al construir la persona, pero ningún assert depende de
 * su valor. Esta clase los verifica directamente.
 */
class PersonTest {

    @Test
    @DisplayName("Person expone exactamente los datos con los que fue construida")
    void shouldExposeConstructorData() {
        // Arrange
        Person person = new Person("Ana", 1, 30, Gender.FEMALE, true);

        // Act & Assert (getters puros: no hay una "accion" separada de la verificacion)
        assertEquals("Ana", person.getName());
        assertEquals(1, person.getId());
        assertEquals(30, person.getAge());
        assertEquals(Gender.FEMALE, person.getGender());
        assertEquals(true, person.isAlive());
    }
}
