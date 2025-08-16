package worldline.api;

import java.util.Map;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import worldline.exception.BadDataException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String, String>> handleBadRequest(BadRequestException exception) {
        Map<String, String> error = Map.of("error", exception.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(BadDataException.class)
    public ResponseEntity<Map<String, String>> handleBadRequest(BadDataException exception) {
        Map<String, String> error = Map.of("error", exception.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
}
