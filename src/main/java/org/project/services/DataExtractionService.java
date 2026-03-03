package org.project.services;

import org.jsoup.nodes.Element;
import org.project.infrastructure.UnZipFile;
import org.project.infrastructure.WebClient;
import org.project.utils.CreateDirectoriesUtil;
import org.project.utils.DeleteFileUtil;
import org.project.utils.ExtractUrl;
import org.project.utils.FileDownloadUtil;

import java.io.BufferedInputStream;
import java.io.File;

public class DataExtractionService {

    public static void executeExtraction() {

        String api =  "https://dadosabertos.ans.gov.br/FTP/PDA/";
        String[] dem = new String[3];
        dem[0] = "operadoras_de_plano_de_saude_ativas";
        dem[1] = "demonstracoes_contabeis ";
        dem[2] = "2025/";

        Element apiOperadora = WebClient.returnDocumentPage(api).select("a[href*=" + dem[0] + "]").first();
        String extractedApi = ExtractUrl.returnUrl(apiOperadora);
        System.out.println("Navegando para: " + extractedApi);

        String filesName = WebClient.returnDocumentPage(extractedApi).select("a[href$=.csv]").text();
        System.out.println("Conteúdo Encontrado: " + filesName);
        String apiConcat = extractedApi + filesName;
        BufferedInputStream in = WebClient.downloadWebAgent(apiConcat).bodyStream();
        FileDownloadUtil.download(in, "dados_brutos/", filesName);
        System.out.println("----------------------------------------");

        Element apiDespesa = WebClient.returnDocumentPage(api).select("a[href*=" + dem[1] + "]").first();
        extractedApi = ExtractUrl.returnUrl(apiDespesa);

        apiDespesa = WebClient.returnDocumentPage(extractedApi).select("a[href*=" + dem[2] + "]").first();
        extractedApi = ExtractUrl.returnUrl(apiDespesa);

        System.out.println("Navegando para: " + extractedApi);
        filesName = WebClient.returnDocumentPage(extractedApi).select("a[href$=.zip]").text();
        System.out.println("Conteúdo Encontrado: " + filesName);
        System.out.println("Baixando e descompactando arquivos!");
        System.out.println("----------------------------------------");

        String[] files = filesName.split(" ");
        for (String s : files) {
            String file = s.replace("\"", "");
            apiConcat = extractedApi + file;
            in = WebClient.downloadWebAgent(apiConcat).bodyStream();
            FileDownloadUtil.download(in, "dados_brutos/", file);
            UnZipFile.unzip(new File("dados_brutos"), "dados_brutos/" + file);
            DeleteFileUtil.delete("dados_brutos/" + file);
            System.out.println("Arquivo baixado e extraído com sucesso: " + file);
        }
        System.out.println("----------------------------------------");
    }
}
