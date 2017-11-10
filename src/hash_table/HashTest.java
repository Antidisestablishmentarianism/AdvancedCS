package hash_table;

/**
 * Created by Saif on 11/2/2017.
 */
public class HashTest {
    public static void main(String[] args) {
        HashTable<Integer, String> table = new HashTable<>(11);
        table.put(1, "A");
        table.put(3, "B");
        table.put(15, "C");
        table.put(8, "D");
        table.put(10, "E");
        table.put(46, "F");
        System.out.println(table);
    }
}

