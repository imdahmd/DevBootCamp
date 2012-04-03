package dht;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class NodeTest {
    @Test
    public void shouldStoreAKVPair() {
        Node node = new Node(0.1);
        node.store("key", "value");

        assertNotNull(node.value("key"));
    }

    @Test
    public void shouldRetrieveValueForTheCorrespondingKey() {
        Node node = new Node(0.1);
        node.store("key", "value");

        assertEquals("value", node.value("key"));
    }

    @Test
    public void shouldBeComparable() {
        assertTrue(new Node(0.1).compareTo(new Node(0.2)) < 0);
        assertTrue(new Node(0.3).compareTo(new Node(0.2)) > 0);
        assertTrue(new Node(0.3).compareTo(new Node(0.3)) == 0);
    }
}
