package org.project.services;

import org.project.infrastructure.WebClient;
import org.project.utils.CreateDirectoriesUtil;
import org.project.utils.DownloadZipUtil;

import java.io.BufferedInputStream;

public class DataExtractionService {

    public static void executeExtraction(String apiConcat, String file) {
        CreateDirectoriesUtil.creatingDirectories("dados_brutos");
        BufferedInputStream in = WebClient.downloadWebAgent(apiConcat).bodyStream();
        DownloadZipUtil.download(in, "dados_brutos/", file);
    }
}
