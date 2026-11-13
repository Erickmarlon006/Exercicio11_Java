package models;

import exceptions.BibliotecaException;
import interfaces.Emprestavel;

/**
 * Classe base abstrata dos materiais do acervo.
 * Implementa o contrato Emprestavel e protege o estado de disponibilidade.
 */
public abstract class Material implements Emprestavel {

    private final int codigo;
    private String titulo;
    private int anoPublicacao;
    private boolean disponivel;

    protected Material(int codigo, String titulo, int anoPublicacao) {
        if (codigo <= 0) {
            throw new BibliotecaException("O código do material deve ser um número positivo.");
        }
        this.codigo = codigo;
        setTitulo(titulo);
        setAnoPublicacao(anoPublicacao);
        this.disponivel = true;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new BibliotecaException("O título não pode ser vazio.");
        }
        this.titulo = titulo.trim();
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        if (anoPublicacao <= 0) {
            throw new BibliotecaException("O ano de publicação deve ser um número positivo.");
        }
        this.anoPublicacao = anoPublicacao;
    }

    @Override
    public boolean isDisponivel() {
        return disponivel;
    }

    @Override
    public void emprestar() {
        if (!disponivel) {
            throw new BibliotecaException("O material \"" + titulo + "\" já está emprestado.");
        }
        disponivel = false;
    }

    @Override
    public void devolver() {
        if (disponivel) {
            throw new BibliotecaException("O material \"" + titulo + "\" não está emprestado.");
        }
        disponivel = true;
    }

    /** Polimorfismo: cada tipo informa o seu nome. */
    public abstract String getTipo();

    /** Polimorfismo: cada tipo apresenta suas informações específicas. */
    public abstract String getDetalhes();

    @Override
    public String toString() {
        return String.format("[%d] %-17s %s (%d) | %s | %s",
                codigo, getTipo(), titulo, anoPublicacao, getDetalhes(),
                disponivel ? "Disponível" : "Emprestado");
    }
}
