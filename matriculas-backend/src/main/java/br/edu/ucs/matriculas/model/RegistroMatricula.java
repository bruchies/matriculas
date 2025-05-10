package br.edu.ucs.matriculas.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

/**
 * Essa classe representa cada linha do CSV como uma entidade de domínio. 
 * Utilizamos @Data do Lombok para gerar automaticamente getters, setters, equals(), hashCode() e toString().
 */
@Data
public class RegistroMatricula {

    @CsvBindByName(column = "Estado")
    private String estado;

    @CsvBindByName(column = "Cidade")
    private String cidade;

    @CsvBindByName(column = "IES")
    private String ies;

    @CsvBindByName(column = "Sigla")
    private String sigla;

    @CsvBindByName(column = "Organização")
    private String organizacao;

    @CsvBindByName(column = "Categoria Administrativa")
    private String categoriaAdministrativa;

    @CsvBindByName(column = "Nome do Curso")
    private String nomeCurso;

    @CsvBindByName(column = "Nome Detalhado do Curso")
    private String nomeDetalhadoCurso;

    @CsvBindByName(column = "Modalidade")
    private String modalidade;

    @CsvBindByName(column = "Grau")
    private String grau;

    @CsvBindByName(column = "2014")
    private int matriculas2014;

    @CsvBindByName(column = "2015")
    private int matriculas2015;

    @CsvBindByName(column = "2016")
    private int matriculas2016;

    @CsvBindByName(column = "2017")
    private int matriculas2017;

    @CsvBindByName(column = "2018")
    private int matriculas2018;

    @CsvBindByName(column = "2019")
    private int matriculas2019;

    @CsvBindByName(column = "2020")
    private int matriculas2020;

    @CsvBindByName(column = "2021")
    private int matriculas2021;

    @CsvBindByName(column = "2022")
    private int matriculas2022;
}