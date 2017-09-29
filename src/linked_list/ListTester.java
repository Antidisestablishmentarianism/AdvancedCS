package linked_list;

import helper.InputStreamer;

/**
 * Created by Saif on 9/27/2017.
 */
public class ListTester {
    public static void main(String[] args) {
        CLinkedList actors = new CLinkedList();

        InputStreamer streamer = new InputStreamer("actors.txt");

        while (streamer.hasNext()) {
            actors.add(new Actor(streamer.readLine().trim()));
        }

        System.out.println(actors);

        /*CLinkedList<Movie> movies = new CLinkedList<>();
        streamer.setFile("movies.txt");

        while (streamer.hasNext()) {
            // TODO: parse movie file
            String line = streamer.readLine();


        }

        System.out.println(movies);*/

        streamer.closeStream();
    }
}
