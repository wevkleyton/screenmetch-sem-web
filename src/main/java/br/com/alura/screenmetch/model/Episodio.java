package br.com.alura.screenmetch.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episodio {
    private Integer temporada;
    private Integer numero;
    private String titulo;
    private Integer numeroEpsodio;
    private Double avaliacao;
    private LocalDate dataLancamento;

    public Episodio(Integer numeroTermporada, DadosEpsodio dadosEpsodio) {
            this.temporada = numeroTermporada;
            this.titulo = dadosEpsodio.titulo();
            this.numeroEpsodio = dadosEpsodio.numero();
            try{
                this.avaliacao = Double.valueOf(dadosEpsodio.avaliacao());
            } catch (NumberFormatException ex) {
                this.avaliacao = 0.0;

            }
            try{
                this.dataLancamento = LocalDate.parse(dadosEpsodio.dataLancamento());

            } catch (DateTimeParseException ex) {
                this.dataLancamento = null;
            }

    }

    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumeroEpsodio() {
        return numeroEpsodio;
    }

    public void setNumeroEpsodio(Integer numeroEpsodio) {
        this.numeroEpsodio = numeroEpsodio;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    @Override
    public String toString() {
        return  "temporada=" + temporada +
                ", numero=" + numero +
                ", titulo='" + titulo + '\'' +
                ", numeroEpsodio=" + numeroEpsodio +
                ", avaliacao=" + avaliacao +
                ", dataLancamento=" + dataLancamento;
    }
}
