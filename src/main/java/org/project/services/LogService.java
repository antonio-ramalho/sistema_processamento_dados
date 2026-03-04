package org.project.services;

import org.project.exceptions.DomainException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LogService {
    private String fileName;
    private String ansRegister;
    private String description;
    private String CNPJRegister;

    public LogService() {
    }

    public LogService(String ansRegister, String cnpjRegister, String description, String fileName) {
        this.ansRegister = ansRegister;
        this.CNPJRegister = cnpjRegister;
        this.description = description;
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s", CNPJRegister, ansRegister, fileName, description);
    }

    public static void logWriter(LogService log) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("dados_processados/registroLog.csv", true))) {
            bw.write(log.toString());
            bw.newLine();
        } catch (DomainException | IOException e) {
            throw new DomainException("Não foi possível gravar o arquivo de log!");
        }
    }
}
