package productos_jumanji.ms.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import productos_jumanji.ms.dto.ErrorResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidation(MethodArgumentNotValidException ex) {

        List<String> errors = ex.getBindingResult()
                            .getFieldErrors()
                            .stream()
                            .map(err -> err.getField() + ": " + err.getDefaultMessage())
                            .toList();

        ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                                            .mensaje("Error de validación")
                                            .error(errors.toString())
                                            .status(HttpStatus.BAD_REQUEST.value())
                                            .timestamp(LocalDateTime.now())
                                            .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    

    @ExceptionHandler({ProductoNoEncontrado.class})
    public ResponseEntity<ErrorResponseDto> handleResourceNotFound(ProductoNoEncontrado ex) {

    ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                                        .mensaje("Recurso no encontrado")
                                        .error(ex.getMessage())
                                        .status(HttpStatus.NOT_FOUND.value())
                                        .timestamp(LocalDateTime.now())
                                        .build();

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
}


    @ExceptionHandler({ProductoExistenteException.class})
    public ResponseEntity<ErrorResponseDto> handleResourceNotFound(ProductoExistenteException ex) {

    ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                                        .mensaje("Recurso no encontrado")
                                        .error(ex.getMessage())
                                        .status(HttpStatus.NOT_FOUND.value())
                                        .timestamp(LocalDateTime.now())
                                        .build();

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
}

    @ExceptionHandler({NoHandlerFoundException.class})
    public ResponseEntity<ErrorResponseDto> handleNotFound(NoHandlerFoundException ex) {

        ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                                            .mensaje("Recurso no encontrado")
                                            .error(ex.getMessage())
                                            .status(HttpStatus.NOT_FOUND.value())
                                            .timestamp(LocalDateTime.now())
                                            .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGeneralError(Exception ex) {

        ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                                            .mensaje("Error interno del servidor")
                                            .error(ex.getMessage())
                                            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                            .timestamp(LocalDateTime.now())
                                            .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}