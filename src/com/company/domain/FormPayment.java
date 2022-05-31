package com.company.domain;

public class FormPayment {
    private String description;

    public FormPayment(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "FormPayment{" + "description=" + description + '}';
    }
}