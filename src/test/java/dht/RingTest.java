package dht;

import org.junit.Test;

import static junit.framework.Assert.*;

public class RingTest {
    @Test
    public void shouldBeEmptyInBeginning() {
        Ring ring = new Ring();
        assertTrue(ring.empty());
    }

    @Test
    public void shouldAddANode() {
        Ring ring = new Ring();
        ring.addNode(new Node(0.5));
        assertFalse(ring.empty());
    }

    @Test
    public void shouldStoreKVPairAndRetrieveValue() {
        Ring ring = new Ring();
        ring.addNode(new Node(0.1));
        ring.addNode(new Node(0.3));
        ring.addNode(new Node(0.5));
        ring.store("key", "value");
        assertEquals("value", ring.value("key"));
    }

    @Test
    public void shouldRemoveKVPairFromTheCorrectNode() {
        Ring ring = new Ring();
        ring.addNode(new Node(0.1));
        ring.addNode(new Node(0.3));
        ring.addNode(new Node(0.5));
        ring.store("key", "value");
        ring.store("key2", "value2");

        ring.remove("key");
        assertNull(ring.value("key"));
    }

    @Test
    public void shouldRedistributeKVPairWhenANodeIsAdded() {
        Ring ring = new Ring();
        ring.addNode(new Node(0.1));
        ring.addNode(new Node(0.3));

        MockNode node06 = new MockNode(0.6);
        ring.addNode(node06);
        
        ring.store("key", "value");

        MockNode node05 = new MockNode(0.5);
        ring.addNode(node05);

        assertTrue(node06.assertRemove() && node05.assertStore());
    }
}

class MockNode extends Node {
    private boolean assertStore = false;
    private boolean assertRemove = false;
    public MockNode(double index) {
        super(index);
    }

    public void store(String key, Object value) {
        super.store(key, value);
        assertStore = true;
    }

    public void remove(String key) {
        super.remove(key);
        assertRemove = true;
    }

    public boolean assertStore(){
        return assertStore;
    }

    public boolean assertRemove(){
        return assertRemove;
    }
}
