package queue_array.war;

import helper.InputStreamer;
import queue_array.ArrayQueue;

/**
 * Created by Saif Ahmad on 9/11/2017.
 */
public class WarPlayer {
    private final String fileName = "C:\\Users\\180502\\Desktop\\AdvancedCS\\src\\queue_array\\war\\decks.txt";

    private final ArrayQueue<String> PlayerOne = new ArrayQueue<>(52);
    private final ArrayQueue<String> PlayerTwo = new ArrayQueue<>(52);
    private final ArrayQueue<String> Play = new ArrayQueue<>(52);

    private InputStreamer streamer;

    public WarPlayer() {
        streamer = new InputStreamer(fileName);

        String currLine;
        int p = 0;

        while ((currLine = streamer.readLine()) != null) {
            String[] cards = currLine.split(" ");

            for (int i = 0; i < cards.length; i++) {
                if (p == 0)
                    PlayerOne.enqueue(cards[i]);
                else
                    PlayerTwo.enqueue(cards[i]);
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

    public void play() {
        int rounds = 0;

        while (PlayerOne.size() < 52 && PlayerTwo.size() < 52 && Play.size() < 52 && rounds++ < 100000) {
            Play.enqueue(PlayerOne.dequeue());
            Play.enqueue(PlayerTwo.dequeue());

            String a = Play.dequeue();
            String b = Play.dequeue();

            int result = compare(a, b);

            if (result == 1) {
                PlayerOne.enqueue(a);
                PlayerOne.enqueue(b);
            } else if (result == 0) {
                PlayerTwo.enqueue(a);
                PlayerTwo.enqueue(b);
            } else if (result == -1) {
                war(new String[]{a, b});
            }
        }

        if (PlayerOne.size() == 52)
            System.out.println("Player 1 wins!");
        else if (PlayerTwo.size() == 52)
            System.out.println("Player 2 wins!");
        else if (Play.size() == 52)
            System.out.println("Tie game stopped because both players ran out of cards.");
        else if (rounds >= 100000)
            System.out.println("Tie game stopped at 100000 rounds.");
    }

    public void war(String[] cards) {
        String[] newCards = new String[cards.length + 2];

        newCards[newCards.length - 2] = PlayerOne.dequeue();
        newCards[newCards.length - 1] = PlayerTwo.dequeue();

        int result = compare(newCards[newCards.length - 2], newCards[newCards.length - 1]);

        if (result == 1)
            PlayerOne.enqueue(newCards);
        else if (result == 0)
            PlayerTwo.enqueue(newCards);
        else if (result == -1)
            war(newCards);
    }

    /**
     * Returns 1 if the first card has a higher value than the second card, 0 if less, and -1 if equal
     */
    public int compare(String card1, String card2) {
        int a;
        int b;

        if (card1.charAt(0) == 'J')
            a = 11;
        else if (card1.charAt(0) == 'Q')
            a = 12;
        else if (card1.charAt(0) == 'K')
            a = 13;
        else if (card1.charAt(0) == 'A')
            a = 14;
        else
            a = Integer.parseInt(card1.substring(0, 1));

        if (card2.charAt(0) == 'J')
            b = 11;
        else if (card2.charAt(0) == 'Q')
            b = 12;
        else if (card2.charAt(0) == 'K')
            b = 13;
        else if (card2.charAt(0) == 'A')
            b = 14;
        else
            b = Integer.parseInt(card2.substring(0, 1));

        if (a > b)
            return 1;
        else if (a < b)
            return 0;
        else
            return -1;
    }

    public static void main(String[] args) {
        new WarPlayer();
    }
}
