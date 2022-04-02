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
            String node1 = "node_1";
            String node2 = "node_2";
            String node3 = "node_3";
            String link1 = "link_1";

            String circuit1 = "circuit_1";

            network1.addNode(node1);
            network1.addNode(node2);
            network1.addNode(node3);
            network1.addLink(link1, node1, 80, node2, 80);
            network1.addCircuit(circuit1, node1, node2, node3);

            System.out.println(network1);

        }catch(EntityNameAlreadyInUseException e1){
            e1.printStackTrace();
        }catch(EntityNotFoundException e2){
            e2.printStackTrace();
        }
    }
}
