package org.project.application;

import org.project.services.DataExtractionService;
import org.project.utils.CreateDirectoriesUtil;

public class Program {
    public static void main(String[] args) {

        CreateDirectoriesUtil.creatingDirectories("dados_brutos");
        CreateDirectoriesUtil.creatingDirectories("dados_processados");
        DataExtractionService.executeExtraction();

    }
}
