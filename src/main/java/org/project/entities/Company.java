package org.project.entities;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private String cnpjRegister;
    private String companyName;
    private String ansRegister;
    private String uf;
    private String modality;

    private List <Expense> despesas = new ArrayList();

    public Company() {}

    public Company(String ansRegister, String cnpjRegister, String companyName, String modality, String uf) {
        this.ansRegister = ansRegister;
        this.cnpjRegister = cnpjRegister;
        this.companyName = companyName;
        this.modality = modality;
        this.uf = uf;
    }

    public String getCnpjRegister() {
        return cnpjRegister;
    }

    public String getAnsRegister() {
        return ansRegister;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getUf() {
        return uf;
    }

    public String getModality() {
        return modality;
    }

    public List<Expense> getExpenses() {
        return despesas;
    }

    public void addDespesa(Expense despesa) {
        this.despesas.add(despesa);
    }

    public static String formatString(String fdf) {
        String companyString = fdf;
        if (fdf.endsWith(".") || fdf.endsWith(" ")) {
            companyString = fdf.substring(0, fdf.length() - 1);
        }
        return companyString.toUpperCase().replaceAll("\"", "");
    }

    public Double sumTotal() {
        Double sum = 0.0;
        for (Expense despesa : despesas) {
            sum += despesa.getExpenseValue();
        }
        return sum;
    }

    public Double averageExpense() {
        return sumTotal() / despesas.size();
    }

    public double calcularDesvioPadrao() {
        if (despesas.isEmpty()) return 0.0;
        double sumSquares = 0.0;

        for (Expense d : despesas) {
            double diferenca = d.getExpenseValue() - averageExpense();
            sumSquares += Math.pow(diferenca, 2);
        }

        double variance = sumSquares / despesas.size();
        return Math.sqrt(variance);
    }
}
