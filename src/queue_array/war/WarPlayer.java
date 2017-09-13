package queue_array.war;

import helper.InputStreamer;
import queue_array.ArrayQueue;

/**
 * Created by Saif Ahmad on 9/11/2017.
 */
public class WarPlayer {
    private final String fileName = "C:\\Users\\180502\\Desktop\\AdvancedCS\\src\\queue_array\\war\\decks.txt";
    //private final String fileName = "/Users/Saif/Documents/Repositories/AdvancedCS/src/queue_array/war/decks.txt";

    private final ArrayQueue<String> PlayerOne = new ArrayQueue<>(52);
    private final ArrayQueue<String> PlayerTwo = new ArrayQueue<>(52);
    private final ArrayQueue<String> Play = new ArrayQueue<>(52);

    public WarPlayer() {
        InputStreamer streamer = new InputStreamer(fileName);

        String currLine;
        int p = 0;

        while ((currLine = streamer.readLine()) != null) {
            String[] cards = currLine.split(" ");

            for (String card : cards) {
                if (p == 0)
                    PlayerOne.enqueue(card);
                else
                    PlayerTwo.enqueue(card);
            }

            if (++p > 1) {
                p = 0;
                play();
                PlayerOne.clear();
                PlayerTwo.clear();
                Play.clear();
            }
        }

        streamer.closeStream();
    }

    private void play() {
        int rounds = 0;

        while (!PlayerOne.isFull() && !PlayerOne.isEmpty() && !PlayerTwo.isFull() && !PlayerTwo.isEmpty() && !Play.isFull() && rounds++ < 100000) {
            Play.enqueue(PlayerOne.dequeue());
            Play.enqueue(PlayerTwo.dequeue());

            String a = Play.dequeue();
            String b = Play.dequeue();

            compare(a, b);
        }

        if (PlayerOne.isFull() || PlayerTwo.isEmpty())
            System.out.println("Player 1 wins!");
        else if (PlayerTwo.isFull() || PlayerOne.isEmpty())
            System.out.println("Player 2 wins!");
        else if (Play.isFull())
            System.out.println("Tie game stopped because both players ran out of cards.");
        else if (rounds >= 100000)
            System.out.println("Tie game stopped at 100000 rounds.");
    }

    private void war() {
        Play.enqueue(PlayerOne.dequeue());
        Play.enqueue(PlayerTwo.dequeue());

        String a = PlayerOne.dequeue();
        String b = PlayerTwo.dequeue();

        compare(a, b);
    }

    /**
     * Returns 1 if the first card has a higher value than the second card, 0 if less, and -1 if equal
     */
    private void compare(String card1, String card2) {
        int a;
        int b;

        if (card1.charAt(0) == 'T')
            a = 10;
        else if (card1.charAt(0) == 'J')
            a = 11;
        else if (card1.charAt(0) == 'Q')
            a = 12;
        else if (card1.charAt(0) == 'K')
            a = 13;
        else if (card1.charAt(0) == 'A')
            a = 14;
        else
            a = Integer.parseInt(card1.substring(0, 1));

        if (card2.charAt(0) == 'T')
            b = 10;
        else if (card2.charAt(0) == 'J')
            b = 11;
        else if (card2.charAt(0) == 'Q')
            b = 12;
        else if (card2.charAt(0) == 'K')
            b = 13;
        else if (card2.charAt(0) == 'A')
            b = 14;
        else
            b = Integer.parseInt(card2.substring(0, 1));

        if (a > b) {
            PlayerOne.enqueueAll(new String[] {card1, card2});
        } else if (a < b) {
            PlayerTwo.enqueueAll(new String[] {card1, card2});
        } else if (a == b) {
            Play.enqueueAll(new String[] {card1, card2});
            war();
        }
    }

    public static void main(String[] args) {
        new WarPlayer();
    }
}
