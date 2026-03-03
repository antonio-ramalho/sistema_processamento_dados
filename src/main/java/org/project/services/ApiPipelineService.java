package org.project.services;

import org.project.infrastructure.WebClient;
import org.project.utils.ExtractUrl;
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
