package org.project.services;

import org.project.entities.Company;
import org.project.entities.Expense;
import org.project.exceptions.DomainException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static org.project.entities.Expense.formatDoubleExpense;

public class ReadingExpenseCsvService {

    public static void readingExpenseFile(String path, Map<String, Company> companies) {

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String[] space;
            br.readLine();
            String line;

            while ((line = br.readLine()) != null) {
                space = line.split(";");

                String contaContabil = Expense.formatString(space[2]).replaceAll("\"", "");
                if (!contaContabil.equals("46411")) {
                    continue;
                }

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                Integer quarter = LocalDate.parse(Expense.formatString(space[0]), dtf).getMonthValue();
                Integer year = LocalDate.parse(Expense.formatString(space[0]), dtf).getYear();

                String ansExpense =  Expense.formatString(space[1]);
                String description = Expense.formatString(space[3]);
                Double expenseValue = formatDoubleExpense(space[5]);

                if (!companies.containsKey(ansExpense)) {
                    LogService log = new LogService(ansExpense, "Despesa sem empresa na base de dados",
                            "Valor: " + expenseValue, path);
                    LogService.logWriter(log);
                    continue;
                }

                Expense expense = new Expense(description, quarter, expenseValue, year);
                Company company = companies.get(ansExpense);
                company.addDespesa(expense);
            }
        }
        catch (Exception e) {
            throw new DomainException("Não foi possível ler o arquivo: " + path);
        }
    }
}
