package views;

import exceptions.BibliotecaException;
import models.Emprestimo;
import models.Material;
import models.Usuario;
import services.EmprestimoService;
import services.MaterialService;
import services.UsuarioService;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/** Camada de apresentação: menu, entrada e saída de dados. */
public class Menu {

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final Scanner scanner = new Scanner(System.in);
    private final MaterialService materialService;
    private final UsuarioService usuarioService;
    private final EmprestimoService emprestimoService;

    public Menu(MaterialService materialService,
                UsuarioService usuarioService,
                EmprestimoService emprestimoService) {
        this.materialService = materialService;
        this.usuarioService = usuarioService;
        this.emprestimoService = emprestimoService;
    }

    public void iniciar() {
        System.out.println("Sistema de Biblioteca");
        int opcao = -1;
        while (opcao != 0) {
            exibirMenu();
            try {
                opcao = lerInt("Escolha uma opção: ");
                executar(opcao);
            } catch (BibliotecaException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        System.out.println("Encerrando o sistema. Até logo!");
    }

    private void exibirMenu() {
        System.out.println();
        System.out.println("========== MENU ==========");
        System.out.println("1 - Cadastrar material");
        System.out.println("2 - Cadastrar usuário");
        System.out.println("3 - Listar materiais");
        System.out.println("4 - Realizar empréstimo");
        System.out.println("5 - Realizar devolução");
        System.out.println("6 - Consultar empréstimos");
        System.out.println("7 - Listar usuários");
        System.out.println("0 - Sair");
    }

    private void executar(int opcao) {
        switch (opcao) {
            case 1 -> cadastrarMaterial();
            case 2 -> cadastrarUsuario();
            case 3 -> listarMateriais();
            case 4 -> realizarEmprestimo();
            case 5 -> realizarDevolucao();
            case 6 -> consultarEmprestimos();
            case 7 -> listarUsuarios();
            case 0 -> { }
            default -> System.out.println("Opção inválida. Escolha um número do menu.");
        }
    }

    private void cadastrarMaterial() {
        System.out.println("Tipo de material: 1 - Livro | 2 - Revista | 3 - Artigo Científico");
        int tipo = lerInt("Tipo: ");
        if (tipo < 1 || tipo > 3) {
            throw new BibliotecaException("Tipo de material inválido.");
        }
        int codigo = lerInt("Código: ");
        String titulo = lerTexto("Título: ");
        int ano = lerInt("Ano de publicação: ");

        Material material;
        switch (tipo) {
            case 1 -> {
                String autor = lerTexto("Autor: ");
                int paginas = lerInt("Número de páginas: ");
                material = materialService.cadastrarLivro(codigo, titulo, ano, autor, paginas);
            }
            case 2 -> {
                int edicao = lerInt("Número da edição: ");
                String editora = lerTexto("Editora: ");
                material = materialService.cadastrarRevista(codigo, titulo, ano, edicao, editora);
            }
            default -> {
                String autor = lerTexto("Autor: ");
                String periodico = lerTexto("Periódico: ");
                material = materialService.cadastrarArtigo(codigo, titulo, ano, autor, periodico);
            }
        }
        System.out.println("Material cadastrado com sucesso!");
        System.out.println(material);
    }

    private void cadastrarUsuario() {
        System.out.println("Categoria: 1 - Estudante | 2 - Professor | 3 - Pesquisador");
        int categoria = lerInt("Categoria: ");
        if (categoria < 1 || categoria > 3) {
            throw new BibliotecaException("Categoria de usuário inválida.");
        }
        int id = lerInt("Identificador: ");
        String nome = lerTexto("Nome: ");

        Usuario usuario;
        switch (categoria) {
            case 1 -> {
                String curso = lerTexto("Curso: ");
                usuario = usuarioService.cadastrarEstudante(id, nome, curso);
            }
            case 2 -> {
                String departamento = lerTexto("Departamento: ");
                usuario = usuarioService.cadastrarProfessor(id, nome, departamento);
            }
            default -> {
                String area = lerTexto("Área de pesquisa: ");
                usuario = usuarioService.cadastrarPesquisador(id, nome, area);
            }
        }
        System.out.println("Usuário cadastrado com sucesso!");
        System.out.println(usuario);
    }

    private void listarMateriais() {
        List<Material> materiais = materialService.listarMateriais();
        if (materiais.isEmpty()) {
            System.out.println("Nenhum material cadastrado.");
            return;
        }
        System.out.println("--- Acervo ---");
        materiais.forEach(System.out::println);
    }

    private void listarUsuarios() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }
        System.out.println("--- Usuários ---");
        usuarios.forEach(System.out::println);
    }

    private void realizarEmprestimo() {
        int codigo = lerInt("Código do material: ");
        int idUsuario = lerInt("Identificador do usuário: ");
        Emprestimo emprestimo = emprestimoService.realizarEmprestimo(codigo, idUsuario);

        System.out.println("Empréstimo realizado com sucesso!");
        System.out.println("Material: " + emprestimo.getMaterial().getTitulo());
        System.out.println("Usuário: " + emprestimo.getUsuario().getNome());
        System.out.println("Categoria: " + emprestimo.getUsuario().getCategoria());
        System.out.println("Prazo de empréstimo: "
                + emprestimo.getUsuario().calcularPrazoEmprestimo() + " dias");
        System.out.println("Devolver até: " + emprestimo.getDataPrevistaDevolucao().format(FORMATO));
    }

    private void realizarDevolucao() {
        int codigo = lerInt("Código do material: ");
        Emprestimo emprestimo = emprestimoService.realizarDevolucao(codigo);

        System.out.println("Devolução registrada com sucesso!");
        System.out.println("Material: " + emprestimo.getMaterial().getTitulo());
        System.out.println("Usuário: " + emprestimo.getUsuario().getNome());
        if (emprestimo.getDiasAtraso() > 0) {
            System.out.println("Atenção: devolução com " + emprestimo.getDiasAtraso() + " dia(s) de atraso.");
        }
    }

    private void consultarEmprestimos() {
        List<Emprestimo> ativos = emprestimoService.consultarEmprestimosAtivos();
        if (ativos.isEmpty()) {
            System.out.println("Nenhum empréstimo ativo.");
            return;
        }
        System.out.println("--- Empréstimos ativos ---");
        ativos.forEach(System.out::println);
    }

    // ---------- Leitura de dados com validação ----------

    private int lerInt(String mensagem) {
        System.out.print(mensagem);
        String linha = scanner.nextLine().trim();
        try {
            return Integer.parseInt(linha);
        } catch (NumberFormatException e) {
            throw new BibliotecaException("Digite um número inteiro válido.");
        }
    }

    private String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }
}
