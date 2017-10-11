package linked_list.IMDb;

/**
 * Created by Saif on 9/27/2017.
 */
public class Actor {
    private String name;

    public Actor() {
        this("No Name");
    }

    public Actor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        name = value;
    }

    public String toString() {
        return name;
    }
}
