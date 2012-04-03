package dht;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class KeyToPositionMapTest {

    @Test
    public void shouldReturnNodePositionForKey() {
        double expected = 0.48595848595848595;
        assertEquals(expected, new KeyToPositionMap().positionFor("key"));
    }
}
