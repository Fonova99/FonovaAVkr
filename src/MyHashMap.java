import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyHashMap implements MyMap {

    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    Entry[] array = new Entry[INITIAL_CAPACITY];
    int size = 0;

    @Override
    public void put(String key, String value) {
        if (size >= (array.length * LOAD_FACTOR)) {
            increaseArrays();
        }
        boolean put = put(key, value, array);
        if (put) {
            size++;
        }
    }

    public boolean put(String key, String value, Entry[] dst) {
        int position = getPosition(key, dst.length); //получаем позицию элемента
        Entry existedElement = dst[position];

        // если по данной позиции ничего нет, то создаем новый объект
        if (existedElement == null) {
            Entry entry = new Entry(key, value, null);
            dst[position] = entry;
            return true;

            //если по данной позиции что-то есть...
        } else {
            while (true) {
                //...сравниваем ключи
                if (existedElement.key.equals(key)) {
                    existedElement.value = value;
                    return false;
                }

                //...либо записываем в конец
                if (existedElement.next == null) {
                    existedElement.next = new Entry(key, value, null);
                    return true;
                }
                existedElement = existedElement.next;
            }
        }
    }

    @Override
    public String get(String key) {
        int position = getPosition(key, array.length); //получаем позицию элемента
        Entry existedElement = array[position];

        while (existedElement != null) {
            if (existedElement.key.equals(key)) {
                return existedElement.value;
            } else {
                existedElement = existedElement.next;
            }
        }
        return null;
    }

    @Override
    public boolean remove(String key) {
        int position = getPosition(key, array.length); //получаем позицию элемента
        Entry existedElement = array[position];

        if (existedElement != null && existedElement.key.equals(key)) { //если элемент существует и равен ключу
            array[position] = existedElement.next;
            size--;
            return true;

        } else {
            while (existedElement != null) { //если элемент существует...
                Entry nextElement = existedElement.next; //следующий элемент
                if (nextElement == null) { //...и следующий элемент равен null
                    return false;
                }
                if (nextElement.key.equals(key)) { //...и следующий элемент равен ключу
                    existedElement.next = nextElement.next;
                    size--;
                    return true;
                } else {
                    existedElement = existedElement.next;
                }
            }
        }
        return false;
    }

    private int getPosition(String key, int arrayLength) { //получение позиции по хеш-коду
        return Math.abs(key.hashCode() % arrayLength);
    }

    @Override
    public Set<String> keySet() {
        Set<String> result = new HashSet<>();
        for (Entry entry : array) {
            while (entry != null) {
                result.add(entry.key);
                entry = entry.next;
            }
        }
        return result;
    }

    @Override
    public List<String> values() {
        List<String> result = new ArrayList<>();
        for (Entry entry : array) {
            while (entry != null) {
                result.add(entry.value);
                entry = entry.next;
            }
        }
        return result;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        array = new Entry[INITIAL_CAPACITY];
        size = 0;
    }

    private void increaseArrays() {
        Entry[] newArray = new Entry[array.length * 2];
        for (Entry entry : array) {
            while (entry != null) {
                put(entry.key, entry.value, newArray);
                entry = entry.next;
            }
        }
        array = newArray;
    }

    private class Entry {
        private String key;
        private String value;
        private Entry next;

        public Entry(String key, String value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }


}

