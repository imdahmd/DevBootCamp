package dht;

// Understands the distribution of nodes in the DHT
public class Ring {
    private int size;

    public boolean empty() {
        return size == 0;
    }

    public void add(Object o) {
       ++size;
    }
}
