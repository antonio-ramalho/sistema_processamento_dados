package org.consumir_api.services;

import org.consumir_api.infrastructure.WebClient;
import org.consumir_api.utils.ExtractUrl;
import org.jsoup.nodes.Element;

public class ApiPipelineService {
    public static String executePipeline (String api, String searchedUrl) {
        Element linkUrl = WebClient.returnDocumentPage(api).select("a[href*=" + searchedUrl + "]").first();

        if (linkUrl == null) {
            return null;
        }

        return ExtractUrl.returnUrl(linkUrl);
    }
}
