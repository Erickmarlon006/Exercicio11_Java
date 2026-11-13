package services;

import models.ArtigoCientifico;
import models.Livro;
import models.Material;
import models.Revista;
import repositories.MaterialRepository;

import java.util.List;

/** Regras de negócio relacionadas ao acervo. */
public class MaterialService {

    private final MaterialRepository repositorio;

    public MaterialService(MaterialRepository repositorio) {
        this.repositorio = repositorio;
    }

    public Material cadastrarLivro(int codigo, String titulo, int ano, String autor, int paginas) {
        return salvar(new Livro(codigo, titulo, ano, autor, paginas));
    }

    public Material cadastrarRevista(int codigo, String titulo, int ano, int edicao, String editora) {
        return salvar(new Revista(codigo, titulo, ano, edicao, editora));
    }

    public Material cadastrarArtigo(int codigo, String titulo, int ano, String autor, String periodico) {
        return salvar(new ArtigoCientifico(codigo, titulo, ano, autor, periodico));
    }

    public List<Material> listarMateriais() {
        return repositorio.listarTodos();
    }

    private Material salvar(Material material) {
        repositorio.salvar(material);
        return material;
    }
}
