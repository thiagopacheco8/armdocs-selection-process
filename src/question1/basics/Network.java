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

    public void addNode(String nodeName) throws EntityNameAlreadyInUseException {
        //Check if the name is already in use
        if(this.getEntityByName(nodeName) != null){
            throw new EntityNameAlreadyInUseException(nodeName);
        }
        this.entities.add(new Node(nodeName));
    }

    public void addLink(String linkName, String sourceNodeName, int sourcePort, String targetNodeName, int targetPort)
            throws EntityNameAlreadyInUseException, EntityNotFoundException {
        //Check if the name is already in use
        if(this.getEntityByName(linkName) != null){
            throw new EntityNameAlreadyInUseException(linkName);
        }

       //Check nodes are in the network
        Node startNode = this.getNodeByName(sourceNodeName);
        Node endNode = this.getNodeByName(targetNodeName);

        this.entities.add(new Link(linkName, startNode, sourcePort, endNode, targetPort));
    }

    public void addCircuit(String circuitName, String... nodeName) throws EntityNotFoundException {
        List<Node> nodes = new ArrayList<Node>();
        for(String node:nodeName){
            nodes.add(this.getNodeByName(node));
        }
        this.entities.add(new Circuit(circuitName, nodes));
    }

    public void addNIC(String nicName, String nodeName) throws EntityNotFoundException, EntityNameAlreadyInUseException {
        //Check if the name is already in use
        if(this.getEntityByName(nicName) != null){
            throw new EntityNameAlreadyInUseException(nicName);
        }

        Node node = this.getNodeByName(nodeName);
        NIC nic = new NIC(nicName);

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
