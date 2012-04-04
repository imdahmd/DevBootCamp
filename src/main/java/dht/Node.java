package dht;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Node implements Comparable<Node>{
    private Map<String, Object> store;
    private final double index;

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

    public void remove(String key) {
        store.put(key, null);
    }

    public void distributeTo(Node newNode, KeyToNodeMap nodeMap) {
        Iterator<String> keys = store.keySet().iterator();
        while(keys.hasNext()){
            String key = keys.next();
            Node nodeForKey = nodeMap.nodeFor(key);
            if(nodeForKey.equals(newNode)){
                newNode.store(key, value(key));
                remove(key);
            }
        }
    }

    public double index() {
        return index;
    }
}
