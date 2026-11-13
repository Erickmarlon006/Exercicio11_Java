package services;

import exceptions.BibliotecaException;
import models.Emprestimo;
import models.Material;
import models.Usuario;
import repositories.EmprestimoRepository;
import repositories.MaterialRepository;
import repositories.UsuarioRepository;

import java.util.List;

/** Regras de negócio de empréstimos e devoluções. */
public class EmprestimoService {

    private final MaterialRepository materialRepositorio;
    private final UsuarioRepository usuarioRepositorio;
    private final EmprestimoRepository emprestimoRepositorio;

    public EmprestimoService(MaterialRepository materialRepositorio,
                             UsuarioRepository usuarioRepositorio,
                             EmprestimoRepository emprestimoRepositorio) {
        this.materialRepositorio = materialRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.emprestimoRepositorio = emprestimoRepositorio;
    }

    public Emprestimo realizarEmprestimo(int codigoMaterial, int idUsuario) {
        Usuario usuario = usuarioRepositorio.buscarPorId(idUsuario)
                .orElseThrow(() -> new BibliotecaException(
                        "Usuário não cadastrado: " + idUsuario + "."));
        Material material = materialRepositorio.buscarPorCodigo(codigoMaterial)
                .orElseThrow(() -> new BibliotecaException(
                        "Material não encontrado: " + codigoMaterial + "."));

        // Lança exceção se o material já estiver emprestado
        material.emprestar();

        Emprestimo emprestimo = new Emprestimo(usuario, material);
        emprestimoRepositorio.salvar(emprestimo);
        return emprestimo;
    }

    public Emprestimo realizarDevolucao(int codigoMaterial) {
        Material material = materialRepositorio.buscarPorCodigo(codigoMaterial)
                .orElseThrow(() -> new BibliotecaException(
                        "Material não encontrado: " + codigoMaterial + "."));
        Emprestimo emprestimo = emprestimoRepositorio.buscarAtivoPorMaterial(codigoMaterial)
                .orElseThrow(() -> new BibliotecaException(
                        "O material \"" + material.getTitulo() + "\" não está emprestado."));

        material.devolver();
        emprestimo.registrarDevolucao();
        return emprestimo;
    }

    public List<Emprestimo> consultarEmprestimosAtivos() {
        return emprestimoRepositorio.listarAtivos();
    }
}
