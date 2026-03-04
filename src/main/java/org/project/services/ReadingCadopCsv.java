package org.project.services;

import org.project.entities.Company;
import org.project.exceptions.DomainException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;

public class ReadingCadopCsv {

    public static void readingCompanyFile(String path, Map<String, Company> companies) {

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String[] space;
            br.readLine();
            String line;

            while ((line = br.readLine()) != null) {
                space = line.split(";");

                String companyName = Company.formatString(space[2]);
                String companyCNPJ = Company.formatString(space[1]);
                String companyAns = Company.formatString(space[0]);
                String companyUf = Company.formatString(space[10]);
                String companyModality = Company.formatString(space[4]);

                if (!Company.validateCnpj(companyCNPJ)) {
                    LogService log = new LogService(companyAns, companyCNPJ, "CNPJ inválido", path);
                    LogService.logWriter(log);
                    continue;
                }

                if (companyName.isEmpty()) {
                    LogService log = new LogService(companyAns, companyCNPJ, "Razão social vazia", path);
                    LogService.logWriter(log);
                    continue;
                }

                Company checkCompany = companies.get(companyAns);
                if (checkCompany != null) {
                    String razaoAntiga = checkCompany.getCompanyName();

                    if (!razaoAntiga.equalsIgnoreCase(companyName)) {
                        LogService log = new LogService(companyAns,
                                companyCNPJ, "Razão social duplicada: "
                                + razaoAntiga + ":" + companyName, path);
                        LogService.logWriter(log);
                    }
                }

                Company com = new  Company(companyAns, companyCNPJ, companyName, companyModality, companyUf);
                companies.put(companyAns, com);
            }
        }
        catch (Exception e) {
            throw new DomainException("Não foi possível ler o arquivo: " + path);
        }
    }
}
