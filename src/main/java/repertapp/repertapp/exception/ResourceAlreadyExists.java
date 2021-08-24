package repertapp.repertapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceAlreadyExists extends RuntimeException {
    public ResourceAlreadyExists(String resourceName, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2) {
        super(String.format("Already exists a %s with %s: '%s' and %s: '%s'", resourceName, fieldName1, fieldValue1, fieldName2, fieldValue2));
    }

    public ResourceAlreadyExists(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("Already exists a %s with %s: '%s'", resourceName, fieldName, fieldValue));
    } 
}
