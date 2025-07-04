package auction.com.example.OnlineAucSpring.exception;

public class ResponseNotFoundException extends RuntimeException {
    String resourceName;
    String field;
    String fieldName;
    Long fieldID;

    public ResponseNotFoundException() {
    }

    public ResponseNotFoundException(String resourceName, String field, String fieldName) {
        super(String.format("%s not found with %s : %s",resourceName,field,fieldName));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldName = fieldName;
    }

    public ResponseNotFoundException(String resourceName, String field, Long fieldID) {
        super(String.format("%s not found with %s : %d",resourceName,field,fieldID));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldID = fieldID;
    }
}
