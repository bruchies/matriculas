package br.edu.ucs.matriculas.repository;

import br.edu.ucs.matriculas.model.RegistroMatricula;

import java.util.List;

/**
 * Interface para o repositório de matrículas.
 Utilizaria apenas se futuramente migrar para Spring Data JPA
 */
public interface MatriculaRepository {
    void salvarTodos(List<RegistroMatricula> registros);
}