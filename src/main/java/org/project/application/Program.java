package org.project.application;

import org.project.entities.Company;
import org.project.services.DataExtractionService;
import org.project.services.ReadingCadopCsv;
import org.project.services.WritingCadopService;
import org.project.utils.CreateDirectoriesUtil;

import java.util.HashMap;
import java.util.Map;

public class Program {
    public static void main(String[] args) {

        Map<String, Company> companies = new HashMap<>();

        CreateDirectoriesUtil.creatingDirectories("dados_brutos");
        CreateDirectoriesUtil.creatingDirectories("dados_processados");
        DataExtractionService.executeExtraction();
        ReadingCadopCsv.readingCompanyFile("dados_brutos/Relatorio_cadop.csv", companies);
        WritingCadopService.writeCadop(companies);
    }
}
