package org.project.services;

import org.project.entities.Company;
import org.project.entities.Expense;
import org.project.exceptions.DomainException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class WritingCadopService {

    public static void writeCadop(Map<String, Company> companies, String path) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write("CNPJ, ANS, UF, RAZÃO SOCIAL, DESCRIÇÃO, ANO, TRIMESTRE, VALOR/DESPESA");
            bw.newLine();

            List<Company> companyExpense = companies.values().stream()
                    .filter(x -> x.sumTotal() > 0)
                    .sorted(Comparator.comparingDouble(Company::sumTotal).reversed())
                    .toList();

            for (Company c : companyExpense) {
                for (Expense ex : c.getExpenses()) {
                    String line =
                            c.getCnpjRegister() + ","
                            + c.getAnsRegister() + ","
                            + c.getUf() + ","
                            + c.getCompanyName() + ",";
                    line +=
                            ex.getDescription() + ","
                            + ex.getYear() + ","
                            + ex.getQuarter() + ","
                            + ex.getExpenseValue();
                    bw.write(line);
                    bw.newLine();
                }
            }

        } catch (DomainException | IOException e) {
            throw new DomainException("Não foi possível gravar as operadoras!");
        }
        finally {
            System.out.println("Relatório criado: " + path);
        }
    }
}
