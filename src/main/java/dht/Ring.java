package dht;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Understands the distribution of nodes in the DHT
public class Ring {
    private List<Node> nodes;

    public Ring() {
        nodes = new ArrayList<Node>();
    }

    public boolean empty() {
        return nodes.size() == 0;
    }

    public void addNode(Node node) {
        nodes.add(node);
        Collections.sort(nodes);
    }

    public void store(String key, Object value) {
        KeyToNodeMap map = new KeyToNodeMap(this.nodes);
        map.nodeFor(key).store(key, value);
    }

    public Object value(String key) {
        KeyToNodeMap map = new KeyToNodeMap(this.nodes);
        return map.nodeFor(key).value(key);
    }
}
