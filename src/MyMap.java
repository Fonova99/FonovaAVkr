import java.util.List;
import java.util.Set;

public interface MyMap {
    void put (String key, String value);
    String get(String key);
    boolean remove (String key);
    Set<String> keySet();
    List<String> values();
    int size();
    void clear();
}
