import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LFUTest {

    @Test
    void add() {
        CacheReplacementPolicy lfu=new LFU();
        lfu.add("a");
        lfu.add("b");
        lfu.add("c");
        lfu.add("a");
        assertEquals("b",lfu.remove());
    }

    @Test
    void remove() {
        CacheReplacementPolicy lfu=new LFU();
        lfu.add("a");
        lfu.add("b");
        lfu.add("b");
        lfu.add("c");
        lfu.add("a");
        assertEquals("c",lfu.remove());
    }
}