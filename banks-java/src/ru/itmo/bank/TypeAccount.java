package ru.itmo.bank;

import java.util.HashMap;
import java.util.Map;

public enum TypeAccount {
    CREDIT("credit"),
    DEBIT("debit"),
    DEPOSIT("deposit");

    private final String name;

    TypeAccount(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private static final Map<String, TypeAccount> LOOKUP_MAP = new HashMap<>();

    static {
        for (TypeAccount env : values()) {
            LOOKUP_MAP.put(env.getName(), env);
        }
    }

    public static TypeAccount getTypeByName(String url) {
        return LOOKUP_MAP.get(url);
    }
}
