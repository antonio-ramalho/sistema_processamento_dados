package org.project.utils;

import java.io.File;

public class DeleteFileUtil {

    public static void delete(String path) {
        File file = new File(path);
        file.delete();
    }
}
