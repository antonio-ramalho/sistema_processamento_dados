package org.project.entities;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private String cnpjRegister;
    private String companyName;
    private String ansRegister;
    private String uf;
    private String modality;

    List <String> despesas = new ArrayList();

    public Company() {}

    public Company(String ansRegister, String cnpjRegister, String companyName, String modality, String uf) {
        this.ansRegister = ansRegister;
        this.cnpjRegister = cnpjRegister;
        this.companyName = companyName;
        this.modality = modality;
        this.uf = uf;
    }

    public String getCompanyName() {
        return companyName;
    }

    public static String formatString(String fdf) {
        String companyString = fdf;
        if (fdf.endsWith(".") || fdf.endsWith(" ")) {
            companyString = fdf.substring(0, fdf.length() - 1);
        }
        return companyString.toUpperCase().replaceAll("\"", "");
    }

    public static boolean validateCnpj(String cnpj) {
        if (cnpj == null) return false;

        cnpj = cnpj.replaceAll("\\D", "");

        if (cnpj.length() != 14 || cnpj.matches("(\\d)\\1{13}")) {
            return false;
        }

        try {
            int[] pesos1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            int[] pesos2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

            int soma = 0;
            for (int i = 0; i < 12; i++) {
                soma += Character.getNumericValue(cnpj.charAt(i)) * pesos1[i];
            }
            int resto = soma % 11;
            int digito1 = (resto < 2) ? 0 : 11 - resto;

            soma = 0;
            for (int i = 0; i < 13; i++) {
                int num = (i < 12) ? Character.getNumericValue(cnpj.charAt(i)) : digito1;
                soma += num * pesos2[i];
            }
            resto = soma % 11;
            int digito2 = (resto < 2) ? 0 : 11 - resto;

            return Character.getNumericValue(cnpj.charAt(12)) == digito1 &&
                    Character.getNumericValue(cnpj.charAt(13)) == digito2;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("%s - %s - %s - %s - %s", cnpjRegister, ansRegister, uf, companyName, modality);
    }
}
