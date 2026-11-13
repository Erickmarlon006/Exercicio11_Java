package models;

import exceptions.BibliotecaException;

/**
 * Desafio de extensibilidade: novo tipo de material que reaproveita Material.
 */
public class ArtigoCientifico extends Material {

    private String autor;
    private String periodico;

    public ArtigoCientifico(int codigo, String titulo, int anoPublicacao, String autor, String periodico) {
        super(codigo, titulo, anoPublicacao);
        setAutor(autor);
        setPeriodico(periodico);
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        if (autor == null || autor.isBlank()) {
            throw new BibliotecaException("O autor não pode ser vazio.");
        }
        this.autor = autor.trim();
    }

    public String getPeriodico() {
        return periodico;
    }

    public void setPeriodico(String periodico) {
        if (periodico == null || periodico.isBlank()) {
            throw new BibliotecaException("O periódico não pode ser vazio.");
        }
        this.periodico = periodico.trim();
    }

    @Override
    public String getTipo() {
        return "Artigo Científico";
    }

    @Override
    public String getDetalhes() {
        return "Autor: " + autor + ", Periódico: " + periodico;
    }
}
