package org.project.services;

import org.project.entities.Company;
import java.util.Map;

public class ValidateService {

    public static boolean calcCNPJ(String cnpj) {
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

    public static boolean validateCNPJ(String companyCNPJ, String companyAns, String path) {

        if (!calcCNPJ(companyCNPJ)) {
            LogService log = new LogService(companyAns, companyCNPJ, "CNPJ inválido", path);
            LogService.logWriter(log);
            return false;
        }
        return true;
    }

    public static boolean validadeRazaoSocial(String companyName, String companyAns,String companyCNPJ, String path) {
        if (companyName.isEmpty()) {
            LogService log = new LogService(companyAns, companyCNPJ, "Razão social vazia", path);
            LogService.logWriter(log);
            return false;
        }
        return true;
    }

    public static boolean validadeAnsDuplicity(Map<String, Company> companies, String companyAns, String companyCNPJ, String path) {
        if (companies.containsKey(companyAns)) {
            LogService log = new LogService(companyAns,
                    companyCNPJ, "ANS duplicada: " + companyAns, path);
            LogService.logWriter(log);
            return false;
        }
        return true;
    }
}
