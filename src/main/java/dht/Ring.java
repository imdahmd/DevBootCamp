package dht;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Understands the distribution of nodes in the DHT
public class Ring {
    private List<Node> nodes;
    private KeyToNodeMap nodeMap;
    private int replicationOrder;

    public Ring() {
        this(0);
    }

    public Ring(int replicationOrder) {
        this.replicationOrder = replicationOrder;
        nodes = new ArrayList<Node>();
        nodeMap = new KeyToNodeMap(nodes);
    }

    public boolean empty() {
        return nodes.size() == 0;
    }

    public void addNode(Node newNode) {
        nodes.add(newNode);
        Collections.sort(nodes);

        nextTo(newNode).distributeTo(newNode, nodeMap);
    }

    private Node nextTo(Node node) {
        int curNodeI = nodes.indexOf(node);
        return nodes.get(curNodeI == nodes.size() - 1 ? 0 : curNodeI + 1);
    }

    public void store(String key, Object value) {
        if(nodes.isEmpty()){
            throw new IllegalStateException();
        }
        Node node = nodeMap.nodeFor(key);
        node.store(key, value);

        for(int i = 0; i < replicationOrder; ++i){
            node = nextTo(node);
            node.store(key, value);
        }
    }

    public Object value(String key) {
        return nodeMap.nodeFor(key).value(key);
    }

    public void remove(String key) {
        Node node = nodeMap.nodeFor(key);
        node.remove(key);

        for(int i = 0; i < replicationOrder; ++i){
            node = nextTo(node);
            node.remove(key);
        }
    }

    public void removeNode(Node node) {
        nodes.remove(node);
    }
}
