package question1.basics;

public abstract class NetworkEntity {
    private String name;

    public NetworkEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
