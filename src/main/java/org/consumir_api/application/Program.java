package org.consumir_api.application;

import org.consumir_api.infrastructure.WebClient;
import org.consumir_api.services.DataExtractionService;
import org.consumir_api.utils.CreateDirectoriesUtil;
import org.consumir_api.utils.ExtractUrl;
import org.jsoup.nodes.Element;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o link do servidor(https://dadosabertos.ans.gov.br/FTP/PDA/): ");
        String api =  sc.nextLine();
        System.out.print("Digite a pasta a ser baixada (demonstracoes_contabeis):");
        String dem =  sc.nextLine();

        Element linkElement = WebClient.returnDocumentPage(api).select("a[href*=" + dem + "]").first();

        System.out.print("Digite o ano desejado (2025/): ");
        String year = sc.nextLine();

        if (linkElement != null) {
            String newUrl = ExtractUrl.returnUrl(linkElement);
            linkElement = WebClient.returnDocumentPage(newUrl).select("a[href*=" + year + "]").first();
            newUrl = ExtractUrl.returnUrl(linkElement);
            System.out.println("Navegando para: " + newUrl);
            String filesName = WebClient.returnDocumentPage(newUrl).select("a[href$=.zip]").text();
            System.out.println("Conteúdo capturado: " + filesName);
            CreateDirectoriesUtil.creatingDirectories("dados_brutos");

            String[] files = filesName.split(" ");
            for (int i = 0; i < files.length; i++) {
                String file = files[i].replace("\"", "");
                String apiConcat = newUrl + file;

                DataExtractionService.executeExtraction(apiConcat, file);
            }
            sc.close();
        }
    }
}
