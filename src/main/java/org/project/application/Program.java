package org.project.application;

import org.project.entities.Company;
import org.project.services.*;
import org.project.utils.CreateDirectoriesUtil;
import java.util.HashMap;
import java.util.Map;

public class Program {
    public static void main(String[] args) {

        Map<String, Company> companies = new HashMap<>();

        CreateDirectoriesUtil.creatingDirectories("dados_brutos");
        CreateDirectoriesUtil.creatingDirectories("dados_processados");
        DataExtractionService.executeExtraction();
        ReadingCadopCsvService.readingCompanyFile("dados_brutos/Relatorio_cadop.csv", companies);
        ReadingExpenseCsvService.readingExpenseFile("dados_brutos/", companies);
        WritingCadopService.writeCadop(companies, "dados_processados//consolidados.csv");
        WritingExpenseService.writeExpense(companies, "dados_processados/despesas_agregadas.csv");
    }
}
