package question1.exceptions;

public class EntityNotFoundException extends Exception{
    private String entityName;
    public EntityNotFoundException(String entityName) {
        super("Entity not found.");
        this.entityName = entityName;
    }
}
