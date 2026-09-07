package edu.unisabana.tyvs.domain.service;

import java.util.Set;
import java.util.HashSet;

import edu.unisabana.tyvs.domain.model.Person;
import edu.unisabana.tyvs.domain.model.RegisterResult;

public class Registry {

    private static final int MIN_AGE = 0;
    private static final int MAX_AGE = 120;
    private static final int UNDERAGE_LIMIT = 18;

    private final Set<Integer> usedIds = new HashSet<>();

    public RegisterResult registerVoter(Person p) {
        if (p == null || p.getId() <= 0) {
            return RegisterResult.INVALID; // regla defensiva
        }
        if (!p.isAlive()) {
            return RegisterResult.DEAD;
        }
        if (p.getAge() < MIN_AGE || p.getAge() > MAX_AGE) {
            return RegisterResult.INVALID_AGE;
        }
        if (p.getAge() < UNDERAGE_LIMIT) {
            return RegisterResult.UNDERAGE;
        }
        if (usedIds.contains(p.getId())) {
            return RegisterResult.DUPLICATED;
        } else {
            usedIds.add(p.getId());
        }

        return RegisterResult.VALID;
    }
}
