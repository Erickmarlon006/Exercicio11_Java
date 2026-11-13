package models;

import exceptions.BibliotecaException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Composição: um empréstimo é formado por um usuário e um material.
 * O prazo é obtido do próprio usuário, sem verificar a categoria (polimorfismo).
 */
public class Emprestimo {

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final Usuario usuario;
    private final Material material;
    private final LocalDate dataEmprestimo;
    private final LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;

    public Emprestimo(Usuario usuario, Material material) {
        if (usuario == null || material == null) {
            throw new BibliotecaException("Um empréstimo precisa de um usuário e de um material.");
        }
        this.usuario = usuario;
        this.material = material;
        this.dataEmprestimo = LocalDate.now();
        this.dataPrevistaDevolucao = dataEmprestimo.plusDays(usuario.calcularPrazoEmprestimo());
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Material getMaterial() {
        return material;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public boolean isAtivo() {
        return dataDevolucao == null;
    }

    public void registrarDevolucao() {
        if (!isAtivo()) {
            throw new BibliotecaException("Este empréstimo já foi devolvido.");
        }
        this.dataDevolucao = LocalDate.now();
    }

    /** Dias de atraso na devolução (0 se estiver no prazo). */
    public long getDiasAtraso() {
        LocalDate referencia = isAtivo() ? LocalDate.now() : dataDevolucao;
        return Math.max(0, ChronoUnit.DAYS.between(dataPrevistaDevolucao, referencia));
    }

    @Override
    public String toString() {
        return String.format("Material: %s | Usuário: %s (%s) | Emprestado em: %s | Devolver até: %s",
                material.getTitulo(), usuario.getNome(), usuario.getCategoria(),
                dataEmprestimo.format(FORMATO), dataPrevistaDevolucao.format(FORMATO));
    }
}
