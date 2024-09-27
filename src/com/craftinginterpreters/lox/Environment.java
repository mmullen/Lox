package com.craftinginterpreters.lox;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Environment {
    final Environment enclosing;
    private final Map<String, Object> values = new HashMap<>();
    private final Set<String> names = new HashSet<>();

    Environment() {
        enclosing = null;
    }

    Environment(Environment enclosing) {
        this.enclosing = enclosing;
    }

    Object get(Token name) {
        if (values.containsKey(name.lexeme)) {
            return values.get(name.lexeme);
        }

        if (enclosing != null) return enclosing.get(name);

        throw new RuntimeError(name,
                "Undefined variable '" + name.lexeme + "'.");
    }

    void assign(Token name, Object value) {
        if (values.containsKey(name.lexeme)) {
            values.put(name.lexeme, value);
            return;
        }

        if (enclosing != null) {
            enclosing.assign(name, value);
            return;
        }

        throw new RuntimeError(name,
                "Undefined variable '" + name.lexeme + "'.");
    }

    void define(String name, Object value) {
        values.put(name, value);
    }

    void declare(String name) {
        names.add(name);
    }

    void set(String name, Object value) {
        if (names.contains(name)) {
            //bad
        }

        values.put(name, value);
    }
}