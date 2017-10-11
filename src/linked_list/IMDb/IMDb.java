package linked_list.IMDb;

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

        for (int a = 0; a < actors.size(); a++) {
            String actor = ((Actor) actors.get(a).getData()).getName();
            System.out.println(actor);

            for (int m = 0; m < movies.size(); m++) {
                Movie movie = (Movie)(movies.get(m).getData());

                if (movie.getActors().containsString(actor))
                    System.out.println("\t" + movie.getTitle() + ", " + movie.getDate());
            }
        }

        return out;
    }
}
