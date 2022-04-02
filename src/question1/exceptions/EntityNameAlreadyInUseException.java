package question1.exceptions;

public class EntityNameAlreadyInUseException extends Exception{
    private String entityName;
    public EntityNameAlreadyInUseException(String entityName) {
        super("Entity name already in use.");
        this.entityName = entityName;
    }
}
