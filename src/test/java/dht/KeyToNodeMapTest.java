package dht;

import org.junit.Test;

import java.util.Arrays;

import static junit.framework.Assert.assertEquals;

public class KeyToNodeMapTest {
    @Test
    public void shouldReturnNodeForKey() {
        Node expectedNode = new Node(0.5);
        Node[] nodes = {new Node(0.3), expectedNode};
        KeyToNodeMap keyToNodeMap = new KeyToNodeMap(Arrays.asList(nodes));
        assertEquals(expectedNode, keyToNodeMap.nodeFor("key"));
    }

    @Test
    public void shouldWrapToFirstNodeForKeyThatIsNotEvenMappingToLargestNode() {
        Node expectedNode = new Node(0.1);
        Node[] nodes = {expectedNode,new Node(0.3)};
        KeyToNodeMap keyToNodeMap = new KeyToNodeMap(Arrays.asList(nodes));
        assertEquals(expectedNode, keyToNodeMap.nodeFor("key"));
    }
}
