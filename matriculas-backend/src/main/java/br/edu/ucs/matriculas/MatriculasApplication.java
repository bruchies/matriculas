package br.edu.ucs.matriculas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.ucs.matriculas.service.CSVImportService;


@SpringBootApplication
public class MatriculasApplication implements CommandLineRunner {

    @Autowired
	private CSVImportService csvImportService;

    @Value("${spring.profiles.active:default}")
    private String activeProfile;

    public static void main(String[] args) {
        SpringApplication.run(MatriculasApplication.class, args);
    }

    @Override
	public void run(String... args) {
		System.out.println("🔍 Profile ativo: " + activeProfile);
		if ("dev".equalsIgnoreCase(activeProfile.trim())) {
			System.out.println("🔁 Ambiente DEV detectado — iniciando importação do CSV...");
			csvImportService.importarCSV();
		} else {
			System.out.println("✅ Ambiente não-DEV — importação de CSV ignorada.");
		}
	}
}