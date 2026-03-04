package org.project.entities;

import java.util.Date;

public class Expense {
    private String ansRegister;
    private String description;
    private Date year;
    private String quarter;
    private Double expenseValue;

    public Expense() {}

    public Expense(String description, String ansRegister, String quarter, Double expenseValue, Date year) {
        this.description = description;
        this.ansRegister = ansRegister;
        this.quarter = quarter;
        this.expenseValue = expenseValue;
        this.year = year;
    }
}
