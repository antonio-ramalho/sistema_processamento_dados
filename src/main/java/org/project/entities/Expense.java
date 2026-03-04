package org.project.entities;

import jdk.jfr.Description;

import java.time.LocalDate;
import java.util.Date;

public class Expense {
    private String description;
    private Integer year;
    private Integer quarter;
    private Double expenseValue;

    public Expense() {}

    public Expense(String description, Integer quarter, Double expenseValue, Integer year) {
        this.description = description;
        this.quarter = quarter;
        this.expenseValue = expenseValue;
        this.year = year;
    }

    public static String formatString(String description) {
        String companyString = description;
        if (description.endsWith(".") || description.endsWith(" ")) {
            companyString = description.substring(0, description.length() - 1);
        }
        return companyString.toUpperCase().replaceAll("\"", "");
    }

    public static Double formatDoubleExpense(String expense) {
        String f1 = expense.replace(",", ".");
        return Double.parseDouble(f1);
    }

    @Override
    public String toString() {
        return String.format("%s - %s - %s - %s", description, year, quarter + "º Trimestre", expenseValue);
    }
}
