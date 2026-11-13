package models;

import exceptions.BibliotecaException;

public class Revista extends Material {

    private int numeroEdicao;
    private String editora;

    public Revista(int codigo, String titulo, int anoPublicacao, int numeroEdicao, String editora) {
        super(codigo, titulo, anoPublicacao);
        setNumeroEdicao(numeroEdicao);
        setEditora(editora);
    }

    public int getNumeroEdicao() {
        return numeroEdicao;
    }

    public void setNumeroEdicao(int numeroEdicao) {
        if (numeroEdicao <= 0) {
            throw new BibliotecaException("O número da edição deve ser positivo.");
        }
        this.numeroEdicao = numeroEdicao;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        if (editora == null || editora.isBlank()) {
            throw new BibliotecaException("A editora não pode ser vazia.");
        }
        this.editora = editora.trim();
    }

    @Override
    public String getTipo() {
        return "Revista";
    }

    @Override
    public String getDetalhes() {
        return "Edição " + numeroEdicao + ", Editora: " + editora;
    }
}
