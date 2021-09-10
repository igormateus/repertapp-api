package repertapp.repertapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class NoPermissionException extends RuntimeException {
    public NoPermissionException(String username, Long BandId) {
        super(String.format("User %s dont have access to Band id: '%s'", username, BandId));
    } 
}
