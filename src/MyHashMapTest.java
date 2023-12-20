import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyHashMapTest {
    private MyHashMap map;

    @Before
    public void setUp() throws Exception {
        map = new MyHashMap();
    }

    @Test
    public void whenPut100ElementsThenSizeBecome100() {
        for (int i = 0; i < 100; i++) {
            String str1 = new String("Word" + i);
            String str2 = new String("Meaning" + i);
            map.put(str1, str2);
        }
        assertEquals(100, map.size());
    }

    @Test
    public void whenPut100ElementsWith10DifferentKeysThenSize10() {
        for (int i = 0; i < 100; i++) {
            int index = i % 10;
            String str1 = new String("Word" + index);
            String str2 = new String("Meaning" + index);
            map.put(str1, str2);
        }
        assertEquals(10, map.size());
    }

    @Test
    public void removeReturnTrueOnlyOnce() {
        for (int i = 0; i < 10; i++) {
            String str1 = new String("Word" + i);
            String str2 = new String("Meaning" + i);
            map.put(str1, str2);
        }
        assertEquals(10, map.size());

        String elementForDeleting = new String("Word" + 5);
        assertTrue(map.remove(elementForDeleting));
        assertEquals(9, map.size());
        assertFalse(map.remove(elementForDeleting));
    }

    @Test
    public void countOfKeysMustBeEqualsToCountOfValues() {
        for (int i = 0; i < 100; i++) {
            String str1 = new String("Word" + i);
            String str2 = new String("Meaning" + i);
            map.put(str1, str2);
        }
        assertEquals(100, map.size());
        assertEquals(100, map.keySet().size());
        assertEquals(100, map.values().size());
    }
}