package repositories;

import exceptions.BibliotecaException;
import models.Usuario;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/** Armazena os usuários em memória, garantindo identificador único. */
public class UsuarioRepository {

    private final Map<Integer, Usuario> usuarios = new LinkedHashMap<>();

    public void salvar(Usuario usuario) {
        if (usuarios.containsKey(usuario.getIdentificador())) {
            throw new BibliotecaException("Já existe um usuário com o identificador "
                    + usuario.getIdentificador() + ".");
        }
        usuarios.put(usuario.getIdentificador(), usuario);
    }

    public Optional<Usuario> buscarPorId(int identificador) {
        return Optional.ofNullable(usuarios.get(identificador));
    }

    public List<Usuario> listarTodos() {
        return new ArrayList<>(usuarios.values());
    }
}
