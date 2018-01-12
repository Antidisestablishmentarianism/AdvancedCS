package hash_table;

import java.math.BigInteger;
import java.util.stream.Collectors;

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
        BigInteger bigInt = new BigInteger(toString().chars().mapToObj(i -> Integer.toString(i - 0)).collect(Collectors.joining("")));
        long hash = Long.parseLong(bigInt.mod(BigInteger.valueOf(Long.MAX_VALUE)).toString());
        return (int) (Long.MAX_VALUE / Integer.MAX_VALUE * hash);
    }

    public String toString() {
        return first + last;
    }
}