package question1.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import question1.basics.Link;
import question1.basics.NIC;
import question1.basics.Network;
import question1.basics.Node;
import question1.exceptions.EntityNameAlreadyInUseException;
import question1.exceptions.EntityNotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NetworkTest {

    //Exception messages
    private final String ENTITY_NOT_FOUNT_EXCEPTION_MESSAGE = "Entity not found.";
    private final String ENTITY_NAME_ALREADY_IN_USE_EXCEPTION = "Entity name already in use.";


    /*
    =======================getEntityByName tests=============================
     */

    @Test
    @DisplayName("getEntityByName - With existing entity")
    void getEntityByNameWithExistingEntity() {
        //Returns a network with 3 nodes (Node1, Node2, Node3)
        Network network = this.build3NodeNetwork();
        String existingNodeName = "Node1";

        assertTrue(network.getEntityByName(existingNodeName) != null);
    }

    @Test
    @DisplayName("getEntityByName - With non existing entity")
    void getEntityByNameWithNonExistingEntity() {
        //Returns a network with 3 nodes (Node1, Node2, Node3)
        Network network = this.build3NodeNetwork();
        String nonExistingNodeName = "Node4";

        assertTrue(network.getEntityByName(nonExistingNodeName) == null);
    }

    /*
    =======================addNode tests=============================
     */

    @Test
    @DisplayName("addNode - When network does not contains any entity")
    void addNodeEmptyNetwork() {
        final String nodeName = "Node1";
        final Network network = new Network();

        //Adding node in an empty network to test if an exception is thrown
        assertDoesNotThrow(() -> network.addNode(nodeName));

        //Checking if the node is in the network
        assertTrue(network.getEntityByName(nodeName) != null);
    }

    @Test
    @DisplayName("addNode - When network contains some entities")
    void addNodeNonEmptyNetwork() {
        final String nodeName = "Node4";
        //Returns a network with 3 nodes (Node1, Node2, Node3)
        final Network network = this.build3NodeNetwork();

        //Adding node in an empty network to test if an exception is thrown
        assertDoesNotThrow(() -> network.addNode(nodeName));

        //Checking if the node is in the network
        assertTrue(network.getEntityByName(nodeName) != null);
    }

    @Test
    @DisplayName("addNode - When network already contains a entity with same name")
    void addNodeWithRepeatedName() {
        final String nodeName = "Node1";
        //Returns a network with 3 nodes (Node1, Node2, Node3)
        final Network network = this.build3NodeNetwork();

        Exception exception = assertThrows(EntityNameAlreadyInUseException.class, () -> network.addNode(nodeName));

        String expectedMessage = ENTITY_NAME_ALREADY_IN_USE_EXCEPTION;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /*
    =======================addLink tests=============================
     */

    @Test
    @DisplayName("addLink - When network does not contains any node")
    void addLinkEmptyNetwork() {
        String linkName = "Link1";
        final Network network = new Network();

        //Adding link in an empty network to test if an exception is thrown
        Exception exception = assertThrows(EntityNotFoundException.class,() -> network.addLink(linkName, "Node1", 123, "Node2", 123));

        String expectedMessage = ENTITY_NOT_FOUNT_EXCEPTION_MESSAGE;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("addLink - When the nodes are present on the network")
    void addLinkWithExistingNodes() {
        String linkName = "Link1";
        //Returns a network with 3 nodes (Node1, Node2, Node3)
        final Network network = this.build3NodeNetwork();

        //Adding link in an network to test if an exception is thrown
        assertDoesNotThrow(() -> network.addLink(linkName,"Node1", 123, "Node2", 123));

        assertTrue(network.getEntityByName(linkName) != null);
    }

    /*
    =======================addCircuit tests=============================
     */

    @Test
    @DisplayName("addCircuit - When network does not contains any node")
    void addCircuitEmptyNetwork() {
        String circuitName = "circuit1";
        final Network network = new Network();

        //Adding circuit in an empty network to test if an exception is thrown
        Exception exception = assertThrows(EntityNotFoundException.class,() -> network.addCircuit(circuitName, "Node1", "Node2", "Node3"));

        String expectedMessage = ENTITY_NOT_FOUNT_EXCEPTION_MESSAGE;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("addCircuit - When the nodes are present on the network")
    void addCircuitWithExistingNodes() {
        String circuitName = "circuit1";
        //Returns a network with 3 nodes (Node1, Node2, Node3)
        final Network network = this.build3NodeNetwork();

        //Adding circuit in an network to test if an exception is thrown
        assertDoesNotThrow(() -> network.addCircuit(circuitName,"Node1", "Node2","Node3"));

        assertTrue(network.getEntityByName(circuitName) != null);
    }

    /*
    =======================addNIC tests=============================
     */

    @Test
    @DisplayName("addNIC - When network does not contains any node")
    void addNICEmptyNetwork() {
        String nicName = "nic1";
        final Network network = new Network();

        //Adding NIC in an empty network to test if an exception is thrown
        Exception exception = assertThrows(EntityNotFoundException.class,() -> network.addNIC(nicName, "Node1"));

        String expectedMessage = ENTITY_NOT_FOUNT_EXCEPTION_MESSAGE;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("addNIC - When the nodes are present on the network")
    void addNICWithExistingNodes() {
        String nicName = "nic1";
        //Returns a network with 3 nodes (Node1, Node2, Node3)
        final Network network = this.build3NodeNetwork();

        //Adding NIC in an network to test if an exception is thrown
        assertDoesNotThrow(() -> network.addNIC(nicName,"Node1"));

        assertTrue(network.getEntityByName(nicName) != null);
    }

    /*
    =======================getPortsFromNode tests=============================
     */

    @Test
    @DisplayName("getPortsFromNode - When network does not contains any node")
    void getPortsFromNodeEmptyNetwork() {
        String nodeName = "Node1";
        final Network network = new Network();

        Exception exception = assertThrows(EntityNotFoundException.class,() -> network.getPortsFromNode(nodeName));

        String expectedMessage = ENTITY_NOT_FOUNT_EXCEPTION_MESSAGE;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("getPortsFromNode - When the nodes are present on the network")
    void getPortsFromNodeWithExistingNodes() {
        String nodeName = "Node1";
        //Returns a network with 3 nodes (Node1, Node2, Node3)
        final Network network = this.build3NodeNetwork();

        assertDoesNotThrow(() -> {
            List<Integer> ports = network.getPortsFromNode(nodeName);
            Node node = (Node)network.getEntityByName(nodeName);
            assertTrue(ports.equals(node.getPorts()));
        });
    }

     /*
    =======================getNICsFromNode tests=============================
     */

    @Test
    @DisplayName("getNICsFromNode - When network does not contains any node")
    void getNICsFromNodeEmptyNetwork() {
        String nodeName = "Node1";
        final Network network = new Network();

        Exception exception = assertThrows(EntityNotFoundException.class,() -> network.getNICsFromNode(nodeName));

        String expectedMessage = ENTITY_NOT_FOUNT_EXCEPTION_MESSAGE;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("getPortsFromNode - When the nodes are present on the network")
    void getNICsFromNodeWithExistingNodes() {
        String nodeName = "Node1";
        //Returns a network with 3 nodes (Node1, Node2, Node3)
        final Network network = this.build3NodeNetwork();

        assertDoesNotThrow(() -> {
            List<NIC> nics = network.getNICsFromNode(nodeName);
            Node node = (Node)network.getEntityByName(nodeName);
            assertTrue(nics.equals(node.getNetworkInterfaceCard()));
        });
    }

    /*
    =======================getLinksFromNode tests=============================
     */

    @Test
    @DisplayName("getNICsFromNode - When network does not contains any node")
    void getLinksFromNodeEmptyNetwork() {
        String nodeName = "Node1";
        final Network network = new Network();

        Exception exception = assertThrows(EntityNotFoundException.class,() -> network.getLinksFromNode(nodeName));

        String expectedMessage = ENTITY_NOT_FOUNT_EXCEPTION_MESSAGE;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("getPortsFromNode - When the nodes are present on the network")
    void getLinksFromNodeWithExistingNodes() {
        String nodeName = "Node1";
        //Returns a network with 3 nodes (Node1, Node2, Node3)
        final Network network = this.build3NodeNetwork();

        assertDoesNotThrow(() -> {
            List<Link> links = network.getLinksFromNode(nodeName);
            int sizeTotal = links.size();
            int sizeContainingNode1 = (int) links.stream().filter(
                    l -> l.getStartNode().getName().equals(nodeName) || l.getEndNode().getName().equals(nodeName)).count();
            //Checks only if all links returned contains the node
            assertTrue(sizeTotal == sizeContainingNode1);
        });
    }

    /*
    =======================Auxiliar methods=============================
     */
    private Network build3NodeNetwork(){
        Network network = null;
        try {
            network = new Network();

            //Inserting additional nodes
            network.addNode("Node1");
            network.addNode("Node2");
            network.addNode("Node3");
        } catch (EntityNameAlreadyInUseException e) {
            e.printStackTrace();
        }
        return network;
    }

}