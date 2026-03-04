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

    public String getQuarter() {
        if (quarter == 1) {
            return "1º trimestre";
        }
        if (quarter == 4) {
            return "2º trimestre";
        }
        if (quarter == 7) {
            return "3º trimestre";
        }
        return null;
    }

    public String getDescription() {
        return description;
    }

    public Integer getYear() {
        return year;
    }

    public Double getExpenseValue() {
        return expenseValue;
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
}
