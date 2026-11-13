package models;

import exceptions.BibliotecaException;

public class Professor extends Usuario {

    private static final int PRAZO_DIAS = 14;

    private String departamento;

    public Professor(int identificador, String nome, String departamento) {
        super(identificador, nome);
        setDepartamento(departamento);
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        if (departamento == null || departamento.isBlank()) {
            throw new BibliotecaException("O departamento não pode ser vazio.");
        }
        this.departamento = departamento.trim();
    }

    @Override
    public int calcularPrazoEmprestimo() {
        return PRAZO_DIAS;
    }

    @Override
    public String getCategoria() {
        return "Professor";
    }

    @Override
    public String getDetalhes() {
        return "Departamento: " + departamento;
    }
}
