package linked_list;

import helper.InputStreamer;

/**
 * Created by Saif on 9/27/2017.
 */
public class ListTester {
    public static void main(String[] args) {
        CLinkedList<Actor> actors = new CLinkedList<Actor>();

        InputStreamer streamer = new InputStreamer("actors.txt");

        while (streamer.hasNext()) {
            actors.add(new Actor(streamer.readLine().trim()));
        }


        CLinkedList<Movie> movies = new CLinkedList<>();
        streamer.setFile("movies.txt");

        while (streamer.hasNext()) {
            // TODO: parse movie file
            String line = streamer.readLine();

            int date = Integer.parseInt(line.substring(0, 4));
            String title = line.substring(4, 36).trim();
            String actorsLine = line.substring(38, 85).trim();
            String[] actorsList = actorsLine.split(", ");
            String dirLine = line.substring(90).trim();
            String[] dirList = dirLine.split(", ");

            CLinkedList actorLinkedList = new CLinkedList();
            for (int i = 0; i < actorsList.length; i++)
                actorLinkedList.add(actorsList[i]);

            CLinkedList dirLinkedList = new CLinkedList();
            for (int i = 0; i < dirList.length; i++)
                dirLinkedList.add(dirList[i]);

            movies.add(new Movie(date, title, actorLinkedList, dirLinkedList));
        }

        IMDb imdb = new IMDb(actors, movies);

        System.out.println(imdb.match());

        streamer.closeStream();
    }
}
