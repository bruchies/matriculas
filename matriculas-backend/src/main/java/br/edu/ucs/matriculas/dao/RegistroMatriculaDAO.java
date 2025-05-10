package br.edu.ucs.matriculas.dao;

import br.edu.ucs.matriculas.model.ConsultaResultado;
import br.edu.ucs.matriculas.model.RegistroMatricula;
import br.edu.ucs.matriculas.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RegistroMatriculaDAO implements MatriculaRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void salvarTodos(List<RegistroMatricula> registros) {
        String sql = "INSERT INTO matriculas (" +
                "estado, cidade, ies, sigla, organizacao, categoria, nomeCurso, nomeDetalhadoCurso, " +
                "modalidade, grau, " +
                "ano2014, ano2015, ano2016, ano2017, ano2018, ano2019, ano2020, ano2021, ano2022" +
                ") VALUES (" +
                ":estado, :cidade, :ies, :sigla, :organizacao, :categoria, :nomeCurso, :nomeDetalhadoCurso, " +
                ":modalidade, :grau, " +
                ":ano2014, :ano2015, :ano2016, :ano2017, :ano2018, :ano2019, :ano2020, :ano2021, :ano2022" +
                ")";

        for (RegistroMatricula m : registros) {
            Map<String, Object> params = new HashMap<>();
            params.put("estado", m.getEstado());
            params.put("cidade", m.getCidade());
            params.put("ies", m.getIes());
            params.put("sigla", m.getSigla());
            params.put("organizacao", m.getOrganizacao());
            params.put("categoria", m.getCategoriaAdministrativa());
            params.put("nomeCurso", m.getNomeCurso());
            params.put("nomeDetalhadoCurso", m.getNomeDetalhadoCurso());
            params.put("modalidade", m.getModalidade());
            params.put("grau", m.getGrau());
            params.put("ano2014", m.getMatriculas2014());
            params.put("ano2015", m.getMatriculas2015());
            params.put("ano2016", m.getMatriculas2016());
            params.put("ano2017", m.getMatriculas2017());
            params.put("ano2018", m.getMatriculas2018());
            params.put("ano2019", m.getMatriculas2019());
            params.put("ano2020", m.getMatriculas2020());
            params.put("ano2021", m.getMatriculas2021());
            params.put("ano2022", m.getMatriculas2022());

            jdbcTemplate.update(sql, params);
        }
    }

    /**
     * Método para somar o total de matrículas por ano.
     * 
     * @return Lista de resultados com o total de matrículas por ano.
     */
    public List<ConsultaResultado> somarTotalPorAno() {
        String sql = """
            SELECT '2014' AS ano, SUM(ano2014) AS total FROM matriculas
            UNION
            SELECT '2015', SUM(ano2015) FROM matriculas
            UNION
            SELECT '2016', SUM(ano2016) FROM matriculas
            UNION
            SELECT '2017', SUM(ano2017) FROM matriculas
            UNION
            SELECT '2018', SUM(ano2018) FROM matriculas
            UNION
            SELECT '2019', SUM(ano2019) FROM matriculas
            UNION
            SELECT '2020', SUM(ano2020) FROM matriculas
            UNION
            SELECT '2021', SUM(ano2021) FROM matriculas
            UNION
            SELECT '2022', SUM(ano2022) FROM matriculas
        """;
    
        return jdbcTemplate.query(sql, (rs, rowNum) ->
            new ConsultaResultado(rs.getString("ano"), rs.getInt("total"))
        );
    }

    /**
     * Método para somar o total de matrículas por ano e modalidade.
     * 
     * @param modalidade A modalidade a ser filtrada (ex: "EaD" ou "Presencial").
     * @return Lista de resultados com o total de matrículas por ano e modalidade.
     */
    public List<ConsultaResultado> somarTotalPorAnoPorModalidade(String modalidade) {
        String sql = """
            SELECT '2014' AS ano, SUM(ano2014) AS total FROM matriculas WHERE modalidade = :modalidade
            UNION
            SELECT '2015', SUM(ano2015) FROM matriculas WHERE modalidade = :modalidade
            UNION
            SELECT '2016', SUM(ano2016) FROM matriculas WHERE modalidade = :modalidade
            UNION
            SELECT '2017', SUM(ano2017) FROM matriculas WHERE modalidade = :modalidade
            UNION
            SELECT '2018', SUM(ano2018) FROM matriculas WHERE modalidade = :modalidade
            UNION
            SELECT '2019', SUM(ano2019) FROM matriculas WHERE modalidade = :modalidade
            UNION
            SELECT '2020', SUM(ano2020) FROM matriculas WHERE modalidade = :modalidade
            UNION
            SELECT '2021', SUM(ano2021) FROM matriculas WHERE modalidade = :modalidade
            UNION
            SELECT '2022', SUM(ano2022) FROM matriculas WHERE modalidade = :modalidade
        """;
    
        Map<String, Object> params = Map.of("modalidade", modalidade);
    
        return jdbcTemplate.query(sql, params, (rs, rowNum) ->
            new ConsultaResultado(rs.getString("ano"), rs.getInt("total"))
        );
    }

    /**
     * Método para buscar os 10 cursos com mais matrículas em 2022.
     * 
     * @return Lista de resultados com os 10 cursos mais matriculados em 2022.
     */
    public List<ConsultaResultado> buscarTop10CursosPorMatricula2022() {
        String sql = """
            SELECT nomeCurso AS chave, SUM(ano2022) AS total
            FROM matriculas
            GROUP BY nomeCurso
            ORDER BY total DESC
            LIMIT 10
        """;
    
        return jdbcTemplate.query(sql, (rs, rowNum) ->
            new ConsultaResultado(rs.getString("chave"), rs.getInt("total"))
        );
    }

    /**
     * Método para buscar os 10 cursos com mais matrículas em 2022 filtrados por modalidade.
     * 
     * @param modalidade A modalidade a ser filtrada (ex: "EaD" ou "Presencial").
     * @return Lista de resultados com os 10 cursos mais matriculados em 2022 e a modalidade especificada.
     */
    public List<ConsultaResultado> buscarTop10CursosPor2022ComModalidade(String modalidade) {
        String sql = """
            SELECT nomeCurso AS chave, SUM(ano2022) AS total
            FROM matriculas
            WHERE modalidade = :modalidade
            GROUP BY nomeCurso
            ORDER BY total DESC
            LIMIT 10
        """;
    
        Map<String, Object> params = new HashMap<>();
        params.put("modalidade", modalidade);
    
        return jdbcTemplate.query(sql, params, (rs, rowNum) ->
            new ConsultaResultado(rs.getString("chave"), rs.getInt("total"))
        );
    }

    // COM FILTRO DE ESTADO
    // 1e.1 — Total de alunos por ano com filtro por estado
    /**
     * Método para buscar o total de matrículas por ano filtrado por estado.
     * 
     * @param estado O estado a ser filtrado (ex: "RS").
     * @return Lista de resultados com o total de matrículas por ano e estado.
     */
    public List<ConsultaResultado> buscarTotaisPorAnoEstado(String estado) {
        String sql = """
            SELECT '2014' AS ano, SUM(ano2014) AS total FROM matriculas WHERE estado = :estado
            UNION
            SELECT '2015', SUM(ano2015) FROM matriculas WHERE estado = :estado
            UNION
            SELECT '2016', SUM(ano2016) FROM matriculas WHERE estado = :estado
            UNION
            SELECT '2017', SUM(ano2017) FROM matriculas WHERE estado = :estado
            UNION
            SELECT '2018', SUM(ano2018) FROM matriculas WHERE estado = :estado
            UNION
            SELECT '2019', SUM(ano2019) FROM matriculas WHERE estado = :estado
            UNION
            SELECT '2020', SUM(ano2020) FROM matriculas WHERE estado = :estado
            UNION
            SELECT '2021', SUM(ano2021) FROM matriculas WHERE estado = :estado
            UNION
            SELECT '2022', SUM(ano2022) FROM matriculas WHERE estado = :estado
        """;
    
        Map<String, Object> params = new HashMap<>();
        params.put("estado", estado);
    
        return jdbcTemplate.query(sql, params, (rs, rowNum) ->
            new ConsultaResultado(rs.getString("ano"), rs.getInt("total"))
        );
    }

    // 1e.2 — Total por ano + modalidade + estado
    /**
     * Método para buscar o total de matrículas por ano e modalidade filtrado por estado.
     * 
     * @param estado O estado a ser filtrado (ex: "RS").
     * @param modalidade A modalidade a ser filtrada (ex: "EaD" ou "Presencial").
     * @return Lista de resultados com o total de matrículas por ano, estado e modalidade.
     */
    public List<ConsultaResultado> buscarTotaisPorAnoEstadoEModalidade(String estado, String modalidade) {
        String sql = """
            SELECT '2014' AS ano, SUM(ano2014) AS total FROM matriculas WHERE estado = :estado AND modalidade = :modalidade
            UNION
            SELECT '2015', SUM(ano2015) FROM matriculas WHERE estado = :estado AND modalidade = :modalidade
            UNION
            SELECT '2016', SUM(ano2016) FROM matriculas WHERE estado = :estado AND modalidade = :modalidade
            UNION
            SELECT '2017', SUM(ano2017) FROM matriculas WHERE estado = :estado AND modalidade = :modalidade
            UNION
            SELECT '2018', SUM(ano2018) FROM matriculas WHERE estado = :estado AND modalidade = :modalidade
            UNION
            SELECT '2019', SUM(ano2019) FROM matriculas WHERE estado = :estado AND modalidade = :modalidade
            UNION
            SELECT '2020', SUM(ano2020) FROM matriculas WHERE estado = :estado AND modalidade = :modalidade
            UNION
            SELECT '2021', SUM(ano2021) FROM matriculas WHERE estado = :estado AND modalidade = :modalidade
            UNION
            SELECT '2022', SUM(ano2022) FROM matriculas WHERE estado = :estado AND modalidade = :modalidade
        """;
    
        Map<String, Object> params = new HashMap<>();
        params.put("estado", estado);
        params.put("modalidade", modalidade);
    
        return jdbcTemplate.query(sql, params, (rs, rowNum) ->
            new ConsultaResultado(rs.getString("ano"), rs.getInt("total"))
        );
    }

    // 1e.3 — Top 10 cursos com mais alunos matriculados em 2022 filtrados por estado
    /**
     * Método para buscar os 10 cursos com mais matrículas em 2022 filtrados por estado.
     * @param estado
     * @return Lista de resultados com os 10 cursos mais matriculados em 2022 e o estado especificado.
     */
    public List<ConsultaResultado> buscarTop10Cursos2022PorEstado(String estado) {
        String sql = """
            SELECT nomeCurso AS chave, SUM(ano2022) AS total
            FROM matriculas
            WHERE estado = :estado
            GROUP BY nomeCurso
            ORDER BY total DESC
            LIMIT 10
        """;
    
        Map<String, Object> params = new HashMap<>();
        params.put("estado", estado);
    
        return jdbcTemplate.query(sql, params, (rs, rowNum) ->
            new ConsultaResultado(rs.getString("chave"), rs.getInt("total"))
        );
    }

    // 1e.4 — Top 10 cursos com mais alunos matriculados em 2022 filtrados por estado e modalidade
    /**
     * Método para buscar os 10 cursos com mais matrículas em 2022 filtrados por estado e modalidade.
     * @param estado
     * @param modalidade
     * @return Lista de resultados com os 10 cursos mais matriculados em 2022, estado e modalidade especificados.
     */
    public List<ConsultaResultado> buscarTop10Cursos2022EstadoEModalidade(String estado, String modalidade) {
        String sql = """
            SELECT nomeCurso AS chave, SUM(ano2022) AS total
            FROM matriculas
            WHERE estado = :estado AND modalidade = :modalidade
            GROUP BY nomeCurso
            ORDER BY total DESC
            LIMIT 10
        """;
    
        Map<String, Object> params = new HashMap<>();
        params.put("estado", estado);
        params.put("modalidade", modalidade);
    
        return jdbcTemplate.query(sql, params, (rs, rowNum) ->
            new ConsultaResultado(rs.getString("chave"), rs.getInt("total"))
        );
    }
}