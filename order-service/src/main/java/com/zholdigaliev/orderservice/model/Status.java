package com.zholdigaliev.orderservice.model;

import java.util.Map;
import java.util.Set;

public enum Status {
    NEW,
    PENDING,
    RESERVED,
    PAID,
    DELIVERED,
    CANCELLED;

    private static final Map<Status, Set<Status>> TRANSITIONS = Map.of(
            NEW, Set.of(PENDING, CANCELLED),
            PENDING, Set.of(RESERVED, CANCELLED),
            RESERVED, Set.of(PAID, CANCELLED),
            PAID, Set.of(DELIVERED),
            DELIVERED, Set.of(),
            CANCELLED, Set.of()
    );

    public boolean canTransitionTo(Status target) {
        return TRANSITIONS.getOrDefault(this, Set.of()).contains(target);
    }

    public boolean isTerminal(Status target) {
        return TRANSITIONS.getOrDefault(this, Set.of()).isEmpty();
    }

    public Set<Status> getAllowedTransitions() {
        return TRANSITIONS.getOrDefault(this, Set.of());
    }
}
