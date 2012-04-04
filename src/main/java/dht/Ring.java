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
        nodeMap.nodeFor(key).store(key, value);
    }

    public Object value(String key) {
        return nodeMap.nodeFor(key).value(key);
    }

    public void remove(String key) {
        nodeMap.nodeFor(key).remove(key);
    }
}
