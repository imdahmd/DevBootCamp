package dht;

import org.junit.Test;

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
        ring.add(new Object());
        assertFalse(ring.empty());
    }
}
