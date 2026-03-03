package org.project.infrastructure;

import java.io.File;

public class DeleteZipFile {

    public static void delete(String path) {
        File file = new File(path);

        if (file.delete()) {
            System.out.println("Arquivo ZIP excluído: " + file.getName());
        } else {
            System.out.println("Falha ao excluir o arquivo ZIP.");
        }
    }
}
