package question1.basics;

public abstract class Connection extends NetworkEntity{
    private Node startNode;
    private int startPort;
    private Node endNode;
    private int endPort;

    protected Connection(String name, Node startNode, int startPort, Node endNode, int endPort) {
        super(name);
        this.startNode = startNode;
        this.startPort = startPort;
        this.endNode = endNode;
        this.endPort = endPort;
    }

    public Node getStartNode() {
        return startNode;
    }

    public int getStartPort() {
        return startPort;
    }

    public Node getEndNode() {
        return endNode;
    }

    public int getEndPort() {
        return endPort;
    }
}
