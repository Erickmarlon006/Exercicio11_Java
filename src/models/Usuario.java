package models;

import exceptions.BibliotecaException;

/**
 * Classe base abstrata dos usuários da biblioteca.
 * O prazo de empréstimo é definido pelas subclasses (polimorfismo).
 */
public abstract class Usuario {

    private final int identificador;
    private String nome;

    protected Usuario(int identificador, String nome) {
        if (identificador <= 0) {
            throw new BibliotecaException("O identificador do usuário deve ser um número positivo.");
        }
        this.identificador = identificador;
        setNome(nome);
    }

    public int getIdentificador() {
        return identificador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new BibliotecaException("O nome não pode ser vazio.");
        }
        this.nome = nome.trim();
    }

    /** Prazo de empréstimo, em dias, conforme a categoria do usuário. */
    public abstract int calcularPrazoEmprestimo();

    public abstract String getCategoria();

    public abstract String getDetalhes();

    @Override
    public String toString() {
        return String.format("[%d] %s | %s | %s | Prazo: %d dias",
                identificador, nome, getCategoria(), getDetalhes(), calcularPrazoEmprestimo());
    }
}
