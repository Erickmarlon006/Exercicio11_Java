package models;

import exceptions.BibliotecaException;

/**
 * Desafio de novo comportamento: nova categoria com prazo de 21 dias,
 * sem alterar a lógica de empréstimo existente.
 */
public class Pesquisador extends Usuario {

    private static final int PRAZO_DIAS = 21;

    private String areaPesquisa;

    public Pesquisador(int identificador, String nome, String areaPesquisa) {
        super(identificador, nome);
        setAreaPesquisa(areaPesquisa);
    }

    public String getAreaPesquisa() {
        return areaPesquisa;
    }

    public void setAreaPesquisa(String areaPesquisa) {
        if (areaPesquisa == null || areaPesquisa.isBlank()) {
            throw new BibliotecaException("A área de pesquisa não pode ser vazia.");
        }
        this.areaPesquisa = areaPesquisa.trim();
    }

    @Override
    public int calcularPrazoEmprestimo() {
        return PRAZO_DIAS;
    }

    @Override
    public String getCategoria() {
        return "Pesquisador";
    }

    @Override
    public String getDetalhes() {
        return "Área: " + areaPesquisa;
    }
}
