package dht;

import java.util.HashMap;
import java.util.Map;

public class Node {
    private Map<String, Object> store;

    public Node() {
        store = new HashMap<String, Object>();
    }

    public void store(String key, String value) {
        store.put(key, value);
    }

    public Object value(String key) {
        return store.get(key);
    }
}
