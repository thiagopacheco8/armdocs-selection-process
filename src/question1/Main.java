package question1;

import question1.basics.Circuit;
import question1.basics.Link;
import question1.basics.Network;
import question1.basics.Node;
import question1.exceptions.EntityNameAlreadyInUseException;
import question1.exceptions.EntityNotFoundException;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Network network1 = new Network();
        try {
            Node node1 = new Node("node_1");
            Node node2 = new Node("node_2");
            Node node3 = new Node("node_3");
            Link link1 = new Link("link_1", node1, 80, node2, 80);

            Circuit circuit1 = new Circuit("circuit_1", Arrays.asList(node1, node2, node3));

            network1.addNode(node1);
            network1.addNode(node2);
            network1.addNode(node3);
            network1.addLink(link1);
            network1.addCircuit(circuit1);

            System.out.println(network1);

        }catch(EntityNameAlreadyInUseException e1){
            e1.printStackTrace();
        }catch(EntityNotFoundException e2){
            e2.printStackTrace();
        }
    }
}
