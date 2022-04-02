package question1.basics;

import java.util.List;

public class Circuit extends NetworkEntity{
    private List<Node> nodes;

    public Circuit(String name, List<Node> nodes) {
        super(name);
        this.nodes = nodes;
    }

    public List<Node> getNodes() {
        return nodes;
    }
}
