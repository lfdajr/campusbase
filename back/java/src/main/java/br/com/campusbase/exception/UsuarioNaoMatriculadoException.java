package br.com.campusbase.exception;

public class UsuarioNaoMatriculadoException extends Exception {

    /**
     * Creates a new instance of <code>UsuarioNaoMatriculadoException</code>
     * without detail message.
     */
    public UsuarioNaoMatriculadoException() {
    }

    /**
     * Constructs an instance of <code>UsuarioNaoMatriculadoException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public UsuarioNaoMatriculadoException(String msg) {
        super(msg);
    }
}
