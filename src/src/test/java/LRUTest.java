import static org.junit.jupiter.api.Assertions.*;

class LRUTest {

    @org.junit.jupiter.api.Test
    void add() {
        CacheReplacementPolicy lru=new LRU();
        lru.add("a");
        lru.add("b");
        lru.add("c");
        lru.add("a");
        assertEquals("b",lru.remove());
        lru.add("b");
        lru.add("c");
        assertEquals("a",lru.remove());
    }

    @org.junit.jupiter.api.Test
    void remove() {
        CacheReplacementPolicy lru=new LRU();
        lru.add("a");
        lru.add("b");
        lru.add("c");

        lru.remove();
        assertEquals("a",lru.remove());
    }
}