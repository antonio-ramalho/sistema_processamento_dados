package org.project.utils;

import org.project.exceptions.DomainException;
import java.io.FileOutputStream;
import java.io.InputStream;

public class FileDownloadUtil {

    public static void download(InputStream in, String pathName, String file) {
        try (FileOutputStream out = new FileOutputStream(pathName + file)) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            System.out.println("Download completado com sucesso!");
        }
        catch (Exception e) {
            throw new DomainException("Não foi possível fazer o download do arquivo: " + file);
        }
    }
}
