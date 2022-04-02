package question1.basics;

import java.util.ArrayList;
import java.util.List;

public class Node extends NetworkEntity{
    private List<NIC> networkInterfaceCard;
    private List<Integer> ports;

    public Node(String name) {
        super(name);
        this.networkInterfaceCard = new ArrayList<NIC>();
        this.ports = new ArrayList<Integer>();
    }

    protected void addNetworkInterfaceCard(NIC nic){
        this.networkInterfaceCard.add(nic);
    }

    protected void addPort(int port){
        this.ports.add(port);
    }

    public List<NIC> getNetworkInterfaceCard() {
        return networkInterfaceCard;
    }

    public List<Integer> getPorts() {
        return ports;
    }

}
