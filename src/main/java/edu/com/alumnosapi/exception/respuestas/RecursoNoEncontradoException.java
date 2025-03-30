package edu.com.alumnosapi.exception.respuestas;

public class RecursoNoEncontradoException extends RuntimeException {
    //constructor 404
    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
