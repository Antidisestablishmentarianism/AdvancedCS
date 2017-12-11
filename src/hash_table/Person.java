package hash_table;

/**
 * Created by Saif on 12/11/2017.
 */
public class Person {
    private Name name;
    private String phone;
    private String email;
    private String ss;

    public Person(Name name, String phone, String email, String ss) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.ss = ss;
    }

    public Name name() {
        return name;
    }

    public String phone() {
        return phone;
    }

    public String email() {
        return email;
    }

    public String ss() {
        return ss;
    }

    public String toString() {
        return name.toString();
    }
}
