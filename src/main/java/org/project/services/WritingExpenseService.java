package org.project.services;

import org.project.entities.Company;
import org.project.entities.Expense;
import org.project.exceptions.DomainException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class WritingExpenseService {

    public static void writeExpense(Map<String, Company> companies) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\projetos java\\consumir_api\\dados_processados\\expenses.csv"))) {
            bw.write("ANS, ANS, DESCRIÇÃO, ANO, TRIMESTRE, VALOR");
            bw.newLine();

            for (Company company : companies.values()) {
                for (Expense expense : company.getDespesas()) {
                    String line = company + expense.toString();
                    bw.write(line);
                    bw.newLine();
                }
            }

        } catch (DomainException | IOException e) {
            throw new DomainException("Não foi possível gravar as operadoras!");
        }
    }
}
