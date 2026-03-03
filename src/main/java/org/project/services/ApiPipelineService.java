package org.project.services;

import org.project.infrastructure.WebClient;
import org.project.utils.CreateDirectoriesUtil;
import org.project.utils.ExtractUrl;
import org.jsoup.nodes.Element;

public class ApiPipelineService {

    public static void executePipeline() {

        String api =  "https://dadosabertos.ans.gov.br/FTP/PDA/";
        String[] dem = new String[3];
        dem[0] = "operadoras_de_plano_de_saude_ativas";
        dem[1] = "demonstracoes_contabeis ";
        dem[2] = "2025/";

        CreateDirectoriesUtil.creatingDirectories("dados_brutos");
        CreateDirectoriesUtil.creatingDirectories("dados_processados");

        for (int i=0; i< 2; i++) {

            if (i == 0) {
                Element apiOperadora = WebClient.returnDocumentPage(api).select("a[href*=" + dem[0] + "]").first();
                String extractedApi = ExtractUrl.returnUrl(apiOperadora);
                System.out.println("Navegando para: " + extractedApi);
                String filesName = WebClient.returnDocumentPage(extractedApi).select("a[href$=.csv]").text();
                System.out.println("Conteúdo capturado: " + filesName);
                String apiConcat = extractedApi + filesName;
                DataExtractionService.executeExtraction(apiConcat, filesName);
                System.out.println("----------------------------------------");
            } else {
                Element apiDespesa = WebClient.returnDocumentPage(api).select("a[href*=" + dem[1] + "]").first();
                String extractedApi = ExtractUrl.returnUrl(apiDespesa);

                apiDespesa = WebClient.returnDocumentPage(extractedApi).select("a[href*=" + dem[2] + "]").first();
                extractedApi = ExtractUrl.returnUrl(apiDespesa);

                System.out.println("Navegando para: " + extractedApi);
                String filesName = WebClient.returnDocumentPage(extractedApi).select("a[href$=.zip]").text();
                System.out.println("Conteúdo capturado: " + filesName);

                String[] files = filesName.split(" ");
                for (String s : files) {
                    String file = s.replace("\"", "");
                    String apiConcat = extractedApi + file;
                    DataExtractionService.executeExtraction(apiConcat, file);
                }
                System.out.println("----------------------------------------");
            }
        }
    }
}
