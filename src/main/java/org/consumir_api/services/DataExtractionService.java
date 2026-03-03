package org.consumir_api.services;

import org.consumir_api.infrastructure.WebClient;
import org.consumir_api.utils.CreateDirectoriesUtil;
import org.consumir_api.utils.DownloadZipUtil;

import java.io.BufferedInputStream;

public class DataExtractionService {

    public static void executeExtraction(String apiConcat, String file) {
        CreateDirectoriesUtil.creatingDirectories("dados_brutos");
        BufferedInputStream in = WebClient.downloadWebAgent(apiConcat).bodyStream();
        DownloadZipUtil.download(in, "dados_brutos/", file);
    }
}
