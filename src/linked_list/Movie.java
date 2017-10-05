package linked_list;

/**
 * Created by Saif on 9/27/2017.
 */
public class Movie {
    private int date;
    private String title;
    private CLinkedList<Actor> actors;
    private CLinkedList<Actor> directors;

    public Movie() {

    }

    public Movie(int date, String title, CLinkedList<Actor> actors, CLinkedList<Actor> directors) {
        this.date = date;
        this.title = title;
        this.actors = actors;
        this.directors = directors;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int value) {
        date = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String value) {
        title = value;
    }

    public CLinkedList<Actor> getActors() {
        return actors;
    }

    public void setActors(CLinkedList<Actor> value) {
        actors = value;
    }

    public CLinkedList<Actor> getDirectors() {
        return directors;
    }

    public void setDirectors(CLinkedList<Actor> value) {
        directors = value;
    }

    public String toString() {
        return title;
    }
}
