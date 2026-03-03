package org.consumir_api.utils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class ConnectWebUtil {

    public static Connection createBaseConnection(String api) {
        return Jsoup.connect(api)
                .userAgent("Mozilla/5.0")
                .timeout(2000);
    }
}
