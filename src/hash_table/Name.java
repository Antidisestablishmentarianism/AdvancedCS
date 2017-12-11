package hash_table;

import java.util.stream.IntStream;

/**
 * Created by Saif on 12/11/2017.
 */
public class Name {

    private String first, last;

    public Name(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public boolean equals(Name other) {
        return first.equals(other.first()) && last.equals(other.last());
    }

    public String first() {
        return first;
    }

    public String last() {
        return last;
    }

    @Override
    public int hashCode() {
        return IntStream.range(0, toString().length()).map(i -> (i + 1) * toString().charAt(i)).sum();
    }

    public String toString() {
        return first + " " + last;
    }
}
