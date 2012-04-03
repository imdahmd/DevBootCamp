package dht;

import org.junit.Test;

import java.util.Arrays;

import static junit.framework.Assert.assertEquals;

public class KeyToNodeMapTest {
    @Test
    public void shouldReturnNodeForKey() {
        Node[] nodes = {new Node(0.3),new Node(0.5)};
        KeyToNodeMap keyToNodeMap = new KeyToNodeMap(Arrays.asList(nodes));
        assertEquals(0.5, keyToNodeMap.nodeFor("key").index);
    }
}
