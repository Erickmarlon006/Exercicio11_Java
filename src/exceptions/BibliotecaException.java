package exceptions;

/**
 * Exceção de negócio da biblioteca.
 * Usada para sinalizar operações inválidas com mensagens claras ao usuário.
 */
public class BibliotecaException extends RuntimeException {

    public BibliotecaException(String mensagem) {
        super(mensagem);
    }
}
