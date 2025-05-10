package br.edu.ucs.matriculas.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

/**
 * Armazena informações sobre uma consulta realizada e seu momento de execução.
 * Registro das últimas consultas feitas: pode ser usado para armazenar as últimas 2 consultas e fornecer acesso rápido a elas, conforme a funcionalidade 2 do projeto.
 */
@Data
public class HistoricoConsulta {
    private ConsultaFiltro filtro;
    private LocalDateTime timestamp;
    private List<ConsultaResultado> resultado;

    public HistoricoConsulta(ConsultaFiltro filtro, LocalDateTime timestamp, List<ConsultaResultado> resultado) {
        this.filtro = filtro;
        this.timestamp = timestamp;
        this.resultado = resultado;
    }
}