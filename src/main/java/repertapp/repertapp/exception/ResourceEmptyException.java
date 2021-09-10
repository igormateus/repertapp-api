package repertapp.repertapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceEmptyException extends RuntimeException {
    public ResourceEmptyException() {
        super(String.format("The band must have at least one user"));
    }
}
