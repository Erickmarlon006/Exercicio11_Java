import repositories.EmprestimoRepository;
import repositories.MaterialRepository;
import repositories.UsuarioRepository;
import services.EmprestimoService;
import services.MaterialService;
import services.UsuarioService;
import views.Menu;

/** Ponto de entrada: monta as camadas e inicia o menu. */
public class Main {

    public static void main(String[] args) {
        // Persistência
        MaterialRepository materialRepo = new MaterialRepository();
        UsuarioRepository usuarioRepo = new UsuarioRepository();
        EmprestimoRepository emprestimoRepo = new EmprestimoRepository();

        // Serviços
        MaterialService materialService = new MaterialService(materialRepo);
        UsuarioService usuarioService = new UsuarioService(usuarioRepo);
        EmprestimoService emprestimoService =
                new EmprestimoService(materialRepo, usuarioRepo, emprestimoRepo);

        // Apresentação
        new Menu(materialService, usuarioService, emprestimoService).iniciar();
    }
}
