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

    public void addDespesa(Expense despesa) {
        this.despesas.add(despesa);
    }

    public List<Expense> getDespesas() {
        return despesas;
    }

    public static String formatString(String fdf) {
        String companyString = fdf;
        if (fdf.endsWith(".") || fdf.endsWith(" ")) {
            companyString = fdf.substring(0, fdf.length() - 1);
        }
        return companyString.toUpperCase().replaceAll("\"", "");
    }

    @Override
    public String toString() {
        return String.format("%s - %s - %s - %s - %s", cnpjRegister, ansRegister, uf, companyName, modality);
    }
}
