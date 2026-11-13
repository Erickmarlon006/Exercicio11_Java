package services;

import models.Estudante;
import models.Pesquisador;
import models.Professor;
import models.Usuario;
import repositories.UsuarioRepository;

import java.util.List;

/** Regras de negócio relacionadas aos usuários. */
public class UsuarioService {

    private final UsuarioRepository repositorio;

    public UsuarioService(UsuarioRepository repositorio) {
        this.repositorio = repositorio;
    }

    public Usuario cadastrarEstudante(int id, String nome, String curso) {
        return salvar(new Estudante(id, nome, curso));
    }

    public Usuario cadastrarProfessor(int id, String nome, String departamento) {
        return salvar(new Professor(id, nome, departamento));
    }

    public Usuario cadastrarPesquisador(int id, String nome, String areaPesquisa) {
        return salvar(new Pesquisador(id, nome, areaPesquisa));
    }

    public List<Usuario> listarUsuarios() {
        return repositorio.listarTodos();
    }

    private Usuario salvar(Usuario usuario) {
        repositorio.salvar(usuario);
        return usuario;
    }
}
