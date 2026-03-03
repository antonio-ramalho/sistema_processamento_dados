package org.project.infrastructure;

import org.project.exceptions.DomainException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnZipFile {

    public static void unzip(File pathName, String zipFileName) {

        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFileName))) {
            ZipEntry zipEntry = zis.getNextEntry();

            while (zipEntry != null) {
                File newFile = new File(pathName, zipEntry.getName());

                try (FileOutputStream fos = new FileOutputStream(newFile)) {
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                }
                zipEntry = zis.getNextEntry();
            }
            zis.closeEntry();
        } catch (IOException e) {
            throw new DomainException("Não foi possível descompactar o arquivo!");
        }
    }
}
