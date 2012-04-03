package dht;

import java.util.HashMap;
import java.util.Map;

public class Node implements Comparable<Node>{
    private Map<String, Object> store;
    public final double index;

    public Node(double index) {
        this.index = index;
        store = new HashMap<String, Object>();
    }

    public void store(String key, Object value) {
        store.put(key, value);
    }

    public Object value(String key) {
        return store.get(key);
    }

    public int compareTo(Node node) {
        return ((Double)index).compareTo(node.index);
    }
}
