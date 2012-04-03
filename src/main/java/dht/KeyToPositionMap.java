package dht;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import org.apache.commons.lang3.StringUtils;

public class KeyToPositionMap {
    public double positionFor(String key) {
        String hexAsString = hash(key);
        String partHash = StringUtils.substring(hexAsString, hexAsString.length() - 3);
        return Integer.parseInt(partHash, 16) / (double) 0xFFF;
    }

    private String hash(String key) {
        HashFunction hashFunction = Hashing.sha1();
        HashCode hash = hashFunction.newHasher().putString(key).hash();
        return hash.toString();
    }
}
