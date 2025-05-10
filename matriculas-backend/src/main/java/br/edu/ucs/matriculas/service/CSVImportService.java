package br.edu.ucs.matriculas.service;

import br.edu.ucs.matriculas.dao.RegistroMatriculaDAO;
import br.edu.ucs.matriculas.model.RegistroMatricula;
import com.opencsv.CSVReader;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CSVImportService {

    private final RegistroMatriculaDAO matriculaDAO;

    public void importarCSV() {
        List<RegistroMatricula> registrosValidos = new ArrayList<>();
        int totalLidas = 0;
        int totalInvalidas = 0;

        try (
            Reader reader = new InputStreamReader(
                    getClass().getResourceAsStream("/seed/matriculas.csv"), StandardCharsets.UTF_8);
            CSVReader csvReader = new CSVReader(reader);
            PrintWriter logWriter = new PrintWriter(
                    new FileWriter("src/main/resources/logs/log-importacao.csv", false)) // sobrescreve
        ) {
            String[] header = csvReader.readNext();
            int expectedColumns = header.length;

            logWriter.println("Linha;Erro;Conteúdo");

            String[] linha;
            int linhaAtual = 2;

            while ((linha = csvReader.readNext()) != null) {
                totalLidas++;
                if (linha.length != expectedColumns) {
                    logWriter.println(linhaAtual + ";Colunas incorretas (" + linha.length + " de " + expectedColumns + ");" + String.join(";", linha));
                    totalInvalidas++;
                } else {
                    try {
                        RegistroMatricula registro = parseLinha(header, linha);
                        registrosValidos.add(registro);
                    } catch (Exception e) {
                        logWriter.println(linhaAtual + ";Erro de mapeamento;" + String.join(";", linha));
                        totalInvalidas++;
                    }
                }
                linhaAtual++;
            }

            matriculaDAO.salvarTodos(registrosValidos);

            System.out.println("✅ Total de registros lidos: " + totalLidas);
            System.out.println("✅ Registros válidos: " + registrosValidos.size());
            System.out.println("⚠️ Linhas inválidas: " + totalInvalidas);
            System.out.println("📄 Log de erros: src/main/resources/logs/log-importacao.csv");

        } catch (Exception e) {
            System.err.println("❌ Erro ao importar CSV: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private RegistroMatricula parseLinha(String[] header, String[] linha) throws IOException {
        HeaderColumnNameMappingStrategy<RegistroMatricula> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(RegistroMatricula.class);

        StringBuilder csvLinha = new StringBuilder();
        csvLinha.append(String.join(";", header)).append("\n");
        csvLinha.append(String.join(";", linha)).append("\n");

        try (
            Reader reader = new StringReader(csvLinha.toString())
        ) {
            CsvToBean<RegistroMatricula> csvToBean = new CsvToBeanBuilder<RegistroMatricula>(reader)
                    .withMappingStrategy(strategy)
                    .withSeparator(';')
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<RegistroMatricula> list = csvToBean.parse();
            if (list.isEmpty()) throw new IOException("Linha não pôde ser convertida");
            return list.get(0);
        }
    }
}