package dht;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Understands the distribution of nodes in the DHT
public class Ring {
    private List<Node> nodes;
    private KeyToNodeMap nodeMap;

    public Ring() {
        nodes = new ArrayList<Node>();
        nodeMap = new KeyToNodeMap(nodes);
    }

    public boolean empty() {
        return nodes.size() == 0;
    }

    public void addNode(Node node) {
        nodes.add(node);
        Collections.sort(nodes);
    }

    public void store(String key, Object value) {
        nodeMap.nodeFor(key).store(key, value);
    }

    public Object value(String key) {
        return nodeMap.nodeFor(key).value(key);
    }
}
