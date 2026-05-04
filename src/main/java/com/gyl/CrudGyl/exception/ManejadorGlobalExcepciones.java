package com.gyl.CrudGyl.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice

//public class GlobalExceptionHandler
public class ManejadorGlobalExcepciones {

        @ExceptionHandler(NoHandlerFoundException.class)
        public ResponseEntity<Object> handleNotFound(NoHandlerFoundException ex, WebRequest request) {

            // mapa para que el JSON sea legible
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("mensaje", ex.getMessage());
            body.put("descripcion", request.getDescription(false));
            body.put("status", "No encontrado, pero respondo OK");

            // Pasamos el cuerpo y HttpStatus.OK
            return new ResponseEntity<>(body, HttpStatus.OK);
        }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> manejarExcepcionGeneral(Exception ex) {
        return new ResponseEntity<>("Ocurrió un error inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
