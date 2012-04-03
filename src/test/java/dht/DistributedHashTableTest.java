package dht;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DistributedHashTableTest {

    @Test(expected = IllegalStateException.class)
    public void shouldCryWhenTryingToStoreIntoEmptyRing() {
        new DistributedHashTable().store("key","value");
    }
}
