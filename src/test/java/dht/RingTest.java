package dht;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

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
    public void shouldAddNodeAndRetrieveIt() {
        Ring ring = new Ring();
        ring.addNode(new Node(0.1));
        ring.addNode(new Node(0.3));
        ring.addNode(new Node(0.5));
        ring.store("key", "value");
        assertEquals("value", ring.value("key"));
    }
}
