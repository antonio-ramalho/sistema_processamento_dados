package org.consumir_api.infrastructure;

import org.consumir_api.exceptions.DomainException;
import org.consumir_api.utils.ConnectWebUtil;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;

public class WebClient {

    public static Document returnDocumentPage(String api) {
        try {
            return ConnectWebUtil.createBaseConnection(api).get();
        } catch (Exception e) {
            throw new DomainException("Erro em se conectar com a web!");
        }
    }

    public static Connection.Response downloadWebAgent(String api) {
        try {
            return ConnectWebUtil.createBaseConnection(api)
                    .ignoreContentType(true)
                    .maxBodySize(0)
                    .execute();
        } catch (Exception e) {
            throw new DomainException("Não foi possível se conectar com a rede");
        }
    }
}


