package org.project.services;

import org.project.entities.Company;
import org.project.exceptions.DomainException;
import org.project.utils.DeleteFileUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;

public class ReadingCadopCsvService {

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

                if (!ValidateService.validateCNPJ(companyCNPJ, companyAns, path)
                || !ValidateService.validadeRazaoSocial(companyName, companyAns, companyCNPJ, path)
                || !ValidateService.validadeAnsDuplicity(companies, companyAns, companyCNPJ, path))  {
                    continue;
                }

                Company com = new  Company(companyAns, companyCNPJ, companyName, companyModality, companyUf);
                companies.put(companyAns, com);
            }
        }
        catch (Exception e) {
            throw new DomainException("Não foi possível ler o arquivo: " + path);
        }
        finally {
            DeleteFileUtil.delete(path);
        }
    }
}
