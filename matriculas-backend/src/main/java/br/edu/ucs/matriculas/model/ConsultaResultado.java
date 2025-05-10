package br.edu.ucs.matriculas.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Funciona como um DTO (Data Transfer Object) para encapsular o resultado de uma consulta, como total de alunos por curso ou estado.
 */
@Data
@AllArgsConstructor
public class ConsultaResultado {
    private String chave;   // Pode ser o nome do curso, estado, etc.
    private int total;      // Quantidade de alunos
}