package br.com.campusbase.exception;

public class EntidadeInexistenteException extends Exception {

    /**
     * Creates a new instance of <code>EntidadeInexistenteException</code>
     * without detail message.
     */
    public EntidadeInexistenteException() {
    }

    /**
     * Constructs an instance of <code>EntidadeInexistenteException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public EntidadeInexistenteException(String msg) {
        super(msg);
    }
}
