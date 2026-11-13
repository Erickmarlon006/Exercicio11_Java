package repositories;

import models.Emprestimo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** Armazena o histórico de empréstimos em memória. */
public class EmprestimoRepository {

    private final List<Emprestimo> emprestimos = new ArrayList<>();

    public void salvar(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
    }

    public Optional<Emprestimo> buscarAtivoPorMaterial(int codigoMaterial) {
        return emprestimos.stream()
                .filter(e -> e.isAtivo() && e.getMaterial().getCodigo() == codigoMaterial)
                .findFirst();
    }

    public List<Emprestimo> listarAtivos() {
        return emprestimos.stream().filter(Emprestimo::isAtivo).toList();
    }
}
