package dht;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;

public class KeyToNodeMap {
    public final List<Node> nodes;

    public KeyToNodeMap(List<Node> nodes) {
        this.nodes = nodes;
    }
    
    private double positionFor(String key) {
        String hexAsString = hash(key);
        String partHash = StringUtils.substring(hexAsString, hexAsString.length() - 3);
        return Integer.parseInt(partHash, 16) / (double) 0xFFF;
    }

    private String hash(String key) {
        HashFunction hashFunction = Hashing.sha1();
        HashCode hash = hashFunction.newHasher().putString(key).hash();
        return hash.toString();
    }

    public Node nodeFor(String key) {
        double positionForKey = positionFor(key);
        int place = Collections.binarySearch(nodes, new Node(positionForKey));
        if(place < 0){
            place = (place * -1) - 1;
        }
        return nodes.get(place);
    }
}
