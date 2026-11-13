package models;

import exceptions.BibliotecaException;

public class Livro extends Material {

    private String autor;
    private int numeroPaginas;

    public Livro(int codigo, String titulo, int anoPublicacao, String autor, int numeroPaginas) {
        super(codigo, titulo, anoPublicacao);
        setAutor(autor);
        setNumeroPaginas(numeroPaginas);
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

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        if (numeroPaginas <= 0) {
            throw new BibliotecaException("O número de páginas deve ser positivo.");
        }
        this.numeroPaginas = numeroPaginas;
    }

    @Override
    public String getTipo() {
        return "Livro";
    }

    @Override
    public String getDetalhes() {
        return "Autor: " + autor + ", " + numeroPaginas + " páginas";
    }
}
