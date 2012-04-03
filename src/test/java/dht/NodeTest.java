package dht;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class NodeTest {
    @Test
    public void shouldStoreAKVPair() {
        Node node = new Node();
        node.store("key", "value");

        assertNotNull(node.value("key"));
    }

    @Test
    public void shouldRetrieveValueForTheCorrespondingKey() {
        Node node = new Node();
        node.store("key", "value");

        assertEquals("value", node.value("key"));
    }
}
