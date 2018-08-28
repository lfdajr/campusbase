package br.com.campusbase.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    /*@ExceptionHandler({ UsuarioNaoMatriculadoException.class })
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final UsuarioNaoMatriculadoException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, 1);
        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
    }*/
    
    @ExceptionHandler({ EntidadeInexistenteException.class })
    protected ResponseEntity<Object> handleAll(final EntidadeInexistenteException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, 2767);
        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
    }

    /*@Override
    protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final List<String> errors = new ArrayList<String>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
    }*/

    
}
