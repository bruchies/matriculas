package br.edu.ucs.matriculas.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ucs.matriculas.model.ConsultaResultado;
import br.edu.ucs.matriculas.model.HistoricoConsulta;
import br.edu.ucs.matriculas.service.MatriculaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/consultas")
@RequiredArgsConstructor
public class MatriculaController {

    private final MatriculaService consultaService;

    // 1a. Total de alunos matriculados por ano (Brasil)
    // GET /api/matriculas/total-por-ano
    @GetMapping("/total-por-ano")
    public List<ConsultaResultado> getTotalPorAno() {
        return consultaService.buscarTotalPorAno();
    }

    // 1b. Total por ano filtrado por modalidade (EaD ou Presencial)
    // GET /api/matriculas/total-por-ano/modalidade?modalidade=EaD
    @GetMapping("/total-por-ano/modalidade")
    public List<ConsultaResultado> totalPorAnoPorModalidade(@RequestParam String modalidade) {
        return consultaService.consultarTotalPorAnoPorModalidade(modalidade);
    }

    //1c. Top 10 cursos com mais alunos matriculados em 2022
    // GET /api/matriculas/ranking/cursos-2022
    @GetMapping("/ranking/cursos-2022")
    public List<ConsultaResultado> top10Cursos2022() {
        return consultaService.consultarTop10CursosPor2022();
    }

    // 1d. Top 10 cursos com mais alunos matriculados em 2022 filtrado por modalidade (EaD ou Presencial)
    // GET /api/matriculas/ranking/cursos-2022/modalidade?modalidade=EaD
    @GetMapping("/ranking/cursos-2022/modalidade")
    public List<ConsultaResultado> top10Cursos2022PorModalidade(@RequestParam String modalidade) {
        return consultaService.consultarTop10CursosPor2022Modalidade(modalidade);
    }

    @GetMapping("/total-por-ano/estado")
    public List<ConsultaResultado> totalPorAnoEstado(@RequestParam String estado) {
        return consultaService.getTotaisPorAnoEstado(estado);
    }

    @GetMapping("/total-por-ano/estado-modalidade")
    public List<ConsultaResultado> totalPorAnoEstadoEModalidade(
            @RequestParam String estado,
            @RequestParam String modalidade) {
        return consultaService.getTotaisPorAnoEstadoEModalidade(estado, modalidade);
    }

    @GetMapping("/top-cursos-2022/estado")
    public List<ConsultaResultado> topCursos2022Estado(@RequestParam String estado) {
        return consultaService.getTop10Cursos2022PorEstado(estado);
    }

    @GetMapping("/top-cursos-2022/estado-modalidade")
    public List<ConsultaResultado> topCursos2022EstadoEModalidade(
            @RequestParam String estado,
            @RequestParam String modalidade) {
        return consultaService.getTop10Cursos2022EstadoEModalidade(estado, modalidade);
    }

    @GetMapping("/historico")
    public List<HistoricoConsulta> getHistorico() {
        return consultaService.getHistoricoConsultas();
    }

}