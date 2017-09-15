package queue_array.war;

import helper.InputStreamer;
import queue_array.ArrayQueue;

/**
 * Created by Saif Ahmad on 9/11/2017.
 */
public class WarPlayer {
    private final String fileName = "C:\\Users\\180502\\Desktop\\AdvancedCS\\src\\queue_array\\war\\decks.txt";
    //private final String fileName = "/Users/Saif/Documents/Repositories/AdvancedCS/src/queue_array/war/decks.txt";

    private final ArrayQueue<String> playerOne = new ArrayQueue<>(52);
    private final ArrayQueue<String> playerTwo = new ArrayQueue<>(52);
    private final ArrayQueue<String> table = new ArrayQueue<>(52);

    public WarPlayer() {
        InputStreamer streamer = new InputStreamer(fileName);

        String currLine;
        int p = 0;

        while ((currLine = streamer.readLine()) != null) {
            String[] cards = currLine.split(" ");

            for (String card : cards) {
                if (p == 0)
                    playerOne.enqueue(card);
                else
                    playerTwo.enqueue(card);
            }

            if (++p > 1) {
                p = 0;
                play();
                playerOne.clear();
                playerTwo.clear();
                table.clear();
            }
        }

        streamer.closeStream();
    }

    public void play() {
        for (int i = 0; i < 100000; i++) {
            if (value(playerOne.peek()) == value(playerTwo.peek())) {
                table.enqueue(playerOne.dequeue());
                table.enqueue(playerTwo.dequeue());
                table.enqueue(playerOne.dequeue());
                table.enqueue(playerTwo.dequeue());
            } else if (value(playerOne.peek()) > value(playerTwo.peek())) {
                while (!table.isEmpty())
                    playerOne.enqueue(table.dequeue());

                playerOne.enqueue(playerOne.dequeue());
                playerOne.enqueue(playerTwo.dequeue());
            } else if (value(playerOne.peek()) < value(playerTwo.peek())) {
                while (!table.isEmpty())
                    playerTwo.enqueue(table.dequeue());

                playerTwo.enqueue(playerTwo.dequeue());
                playerTwo.enqueue(playerOne.dequeue());
            }

            if (playerOne.isEmpty())
                System.out.println("Player 2 wins!");
            else if (playerTwo.isEmpty())
                System.out.println("Player 1 wins!");
            else if (table.isFull())
                System.out.println("Tie game ended because both players ran out of cards.");
            else if (i == 99999)
                System.out.println("Tie game ended because rounds exceeded 100000.");
        }
    }

    public int value(String card) {
        char first = card.charAt(0);

        if (first == 'T') return 10;
        else if (first == 'J') return 11;
        else if (first == 'Q') return 12;
        else if (first == 'K') return 13;
        else if (first == 'A') return 14;
        else return Integer.parseInt(card.substring(0, 1));
    }

    public static void main(String[] args) {
        new WarPlayer();
    }
}
