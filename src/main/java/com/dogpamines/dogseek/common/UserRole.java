package com.dogpamines.dogseek.common;

public enum UserRole {

    SLEEP("SLEEP"), USER("USER"), ADMIN("ADMIN"), ALL("SLEEP, USER, ADMIN");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
