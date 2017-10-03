package linked_list;

/**
 * Created by Saif on 10/3/2017.
 */
public class IMDb {
    private CLinkedList<Actor> actors;
    private CLinkedList<Movie> movies;

    public IMDb() {
        actors = new CLinkedList<>();
        movies = new CLinkedList<>();
    }

    public IMDb(CLinkedList<Actor> actors, CLinkedList<Movie> movies) {
       this.actors = actors;
       this.movies = movies;
    }

    public String match() {
        String out = "";

        for (int i = 0; i < actors.size(); i++) {
            // TODO: Search movies for actor
        }

        return out;
    }
}
