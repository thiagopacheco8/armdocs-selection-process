package question1.basics;

import question1.exceptions.EntityNameAlreadyInUseException;
import question1.exceptions.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Network {
    private List<NetworkEntity> entities;

    public Network() {
        this.entities = new ArrayList<NetworkEntity>();
    }

    public NetworkEntity getEntityByName(String name){
        NetworkEntity entity = null;
        if(name != null){
            entity = entities.stream().filter(e->e.getName().equals(name)).findFirst().orElse(null);
        }
        return entity;
    }

    public void addNode(Node node) throws EntityNameAlreadyInUseException {
        //Check if the name is already in use
        if(this.getEntityByName(node.getName()) != null){
            throw new EntityNameAlreadyInUseException(node.getName());
        }
        this.entities.add(node);
    }

    public void addLink(Link link) throws EntityNameAlreadyInUseException, EntityNotFoundException {
        //Check if the name is already in use
        if(this.getEntityByName(link.getName()) != null){
            throw new EntityNameAlreadyInUseException(link.getName());
        }

       //Check nodes are in the network
        Node startNode = this.getNodeByName(link.getStartNode().getName());
        Node endNode = this.getNodeByName(link.getEndNode().getName());

        this.entities.add(link);
    }

    public void addCircuit(Circuit circuit) throws EntityNotFoundException {
        for(Node node:circuit.getNodes()){
            this.getNodeByName(node.getName());
        }
        this.entities.add(circuit);
    }

    public void addNIC(NIC nic, String nodeName) throws EntityNotFoundException {
        Node node = this.getNodeByName(nodeName);
        node.addNetworkInterfaceCard(nic);
        this.entities.add(nic);
    }

    public List<Integer> getPortsFromNode(String name) throws EntityNotFoundException {
        Node node = this.getNodeByName(name);
        return node.getPorts();
    }

    public List<NIC> getNICsFromNode(String name) throws EntityNotFoundException {
        Node node = this.getNodeByName(name);
        return node.getNetworkInterfaceCard();
    }

    public List<Link> getLinksFromNode(String name) throws EntityNotFoundException {
        Node node = this.getNodeByName(name);
        return this.entities.stream().filter(e -> e instanceof Link &&
                (
                        ((Link) e).getStartNode().getName().equals(name)
                                ||
                                ((Link) e).getEndNode().getName().equals(name)
                )
                ).map(Link.class::cast).collect(Collectors.toList());
    }

    private Node getNodeByName(String name) throws EntityNotFoundException {
        NetworkEntity entity = this.getEntityByName(name);
        Node node = null;
        if(entity instanceof Node){
            node = (Node) entity;
        }else{
            throw new EntityNotFoundException(name);
        }
        return node;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        List<Node> allNodes = this.entities.stream().filter(e -> e instanceof Node).map(Node.class::cast).collect(Collectors.toList());
        sb.append("Nodes: \n");
        allNodes.forEach(n -> sb.append("- " + n.getName() + "\n"));

        List<Link> allLinks = this.entities.stream().filter(e -> e instanceof Link).map(Link.class::cast).collect(Collectors.toList());
        sb.append("Links: \n");
        allLinks.forEach(n -> sb.append("- " + n.getName() + ": "
                + n.getStartNode().getName() + " -- " + n.getEndNode().getName() + "\n"));

        List<Circuit> allCircuits = this.entities.stream().filter(e -> e instanceof Circuit).map(Circuit.class::cast).collect(Collectors.toList());
        sb.append("Circuits: \n");
        allCircuits.forEach(c -> {
            sb.append("- " + c.getName() + ": ");
            c.getNodes().stream().forEach(n->sb.append(n.getName() + " "));
        });

        return sb.toString();
    }
}
