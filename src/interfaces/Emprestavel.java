package interfaces;

/**
 * Contrato para qualquer item que possa ser emprestado e devolvido.
 */
public interface Emprestavel {

    boolean isDisponivel();

    void emprestar();

    void devolver();
}
