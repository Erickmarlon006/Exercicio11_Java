package models;

import exceptions.BibliotecaException;

public class Estudante extends Usuario {

    private static final int PRAZO_DIAS = 7;

    private String curso;

    public Estudante(int identificador, String nome, String curso) {
        super(identificador, nome);
        setCurso(curso);
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        if (curso == null || curso.isBlank()) {
            throw new BibliotecaException("O curso não pode ser vazio.");
        }
        this.curso = curso.trim();
    }

    @Override
    public int calcularPrazoEmprestimo() {
        return PRAZO_DIAS;
    }

    @Override
    public String getCategoria() {
        return "Estudante";
    }

    @Override
    public String getDetalhes() {
        return "Curso: " + curso;
    }
}
