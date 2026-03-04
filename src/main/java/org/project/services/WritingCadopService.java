package org.project.services;

import org.project.entities.Company;
import org.project.exceptions.DomainException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class WritingCadopService {

    public static void writeCadop(Map<String, Company> companies) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\projetos java\\consumir_api\\dados_processados\\operadoras.csv"))) {
            bw.write("CNPJ, ANS, UF, RAZÃO SOCIAL, MODALIDADE");
            bw.newLine();

            for (Company company : companies.values()) {
                String line = company.toString();
                bw.write(line);
                bw.newLine();
            }

        } catch (DomainException | IOException e) {
            throw new DomainException("Não foi possível gravar as operadoras!");
        }
    }
}
