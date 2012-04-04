package dht;

import org.junit.Test;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class RingTest {


    @Test(expected = IllegalStateException.class)
    public void shouldCryWhenTryingToStoreIamEmpty() {
        new Ring().store("key","value");
    }

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

    // TESTING USING MOCKITO
    @Test
    public void shouldRedistributeKVPairWhenANodeIsAdded1() {
        Ring ring = new Ring();
        ring.addNode(new Node(0.1));
        ring.addNode(new Node(0.3));

        Node node06 = spy(new Node(0.6));
        ring.addNode(node06);
        ring.store("key", "value");

        Node spyNode = spy(new Node(0.5));
        ring.addNode(spyNode);
        verify(node06).remove("key");
        verify(spyNode).store("key", "value");

        ring.value("key");
        verify(spyNode).value("key");
    }

    @Test
    public void shouldReplicateWithGivenOrder(){
        int replicationOrder = 3;
        Ring ring = new Ring(replicationOrder);
        ring.addNode(new Node(0.5));
        Node node06 = spy(new Node(0.6));
        ring.addNode(node06);
        Node node07 = spy(new Node(0.7));
        ring.addNode(node07);
        Node node08 = spy(new Node(0.8));
        ring.addNode(node08);
        Node node09 = spy(new Node(0.9));
        ring.addNode(node09);

        ring.store("key", "value");
        verify(node06).store("key", "value");
        verify(node07).store("key", "value");
        verify(node08).store("key", "value");
        verify(node09, never()).store("key", "value");
    }

    @Test
    public void shouldRemoveKVPairsFromReplicasAsWell() {
        int replicationOrder = 3;
        Ring ring = new Ring(replicationOrder);
        ring.addNode(new Node(0.5));
        Node node06 = spy(new Node(0.6));
        ring.addNode(node06);
        Node node07 = spy(new Node(0.7));
        ring.addNode(node07);
        Node node08 = spy(new Node(0.8));
        ring.addNode(node08);
        Node node09 = spy(new Node(0.9));
        ring.addNode(node09);

        ring.store("key", "value");
        ring.remove("key");

        verify(node06).remove("key");
        verify(node07).remove("key");
        verify(node08).remove("key");
        verify(node09, never()).remove("key");
    }

    @Test
    public void shouldFailSafeAllKVToNextNodeWhenANodeDies() {
        int replicationOrder = 2;
        Ring ring = new Ring(replicationOrder);
        Node node05 = new Node(0.5);
        ring.addNode(node05);
        Node node06 = spy(new Node(0.6));
        ring.addNode(node06);
        Node node07 = spy(new Node(0.7));
        ring.addNode(node07);

        ring.store("key", "value");
        ring.removeNode(node05);
        ring.removeNode(node06);

        assertEquals(ring.value("key"), "value");
        verify(node07).value("key");
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
