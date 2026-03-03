package org.consumir_api.utils;

import org.jsoup.nodes.Element;

public class ExtractUrl {
    public static String returnUrl(Element link) {
        if (link == null) {
            return null;
        }
        return link.attr("abs:href");
    }
}
