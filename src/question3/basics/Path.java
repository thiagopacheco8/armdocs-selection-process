package question3.basics;

public class Path {
    private Town node;
    private int distance;

    public Path(Town node, int distance) {
        this.node = node;
        this.distance = distance;
    }

    public Town getNode() {
        return node;
    }

    public int getDistance() {
        return distance;
    }

}
