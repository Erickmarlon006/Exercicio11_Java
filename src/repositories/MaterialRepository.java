package repositories;

import exceptions.BibliotecaException;
import models.Material;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/** Armazena os materiais em memória, garantindo código único. */
public class MaterialRepository {

    private final Map<Integer, Material> materiais = new LinkedHashMap<>();

    public void salvar(Material material) {
        if (materiais.containsKey(material.getCodigo())) {
            throw new BibliotecaException("Já existe um material com o código " + material.getCodigo() + ".");
        }
        materiais.put(material.getCodigo(), material);
    }

    public Optional<Material> buscarPorCodigo(int codigo) {
        return Optional.ofNullable(materiais.get(codigo));
    }

    public List<Material> listarTodos() {
        return new ArrayList<>(materiais.values());
    }
}
