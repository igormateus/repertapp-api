package repertapp.repertapp.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class NoPermissionException extends RuntimeException {
    public NoPermissionException(String resource, String name, String resource2, Object value) {
        super(String.format("%s %s dont have access to %s id: '%s'", resource, name, resource2, value));
    } 
}
