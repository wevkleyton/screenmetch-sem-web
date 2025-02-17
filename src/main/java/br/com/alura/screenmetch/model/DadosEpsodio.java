package br.com.alura.screenmetch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpsodio(@JsonAlias("Title") String titulo,
                           @JsonAlias("Epsode") Integer numero,
                           @JsonAlias("imdbRating") String avaliacao,
                           @JsonAlias("Released") String dataLancamento) {
}
