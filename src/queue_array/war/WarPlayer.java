package queue_array.war;

import helper.InputStreamer;

/**
 * Created by Saif Ahmad on 9/11/2017.
 */
public class WarPlayer {
    private final String fileName = "C:\\Users\\180502\\Desktop\\AdvancedCS\\src\\queue_array\\war\\decks.txt";
    //private final String fileName = "/Users/Saif/Documents/Repositories/AdvancedCS/src/queue_array/war/decks.txt";

    private final Deck playerOne = new Deck(52);
    private final Deck playerTwo = new Deck(52);
    private final Deck table = new Deck(52);

    private boolean isPlaying = false;

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
                isPlaying = true;
                play();
                playerOne.clear();
                playerTwo.clear();
                table.clear();
            }
        }

        streamer.closeStream();
    }

    public void play() {
        int rounds = 0;
        while (isPlaying) {
            if (value(playerOne.dequeue()) == value(playerTwo.dequeue())) {
                war(rounds);
            } else if (value(playerOne.dequeue()) > value(playerTwo.dequeue())) {
                while (!table.isEmpty())
                    playerOne.enqueue(table.dequeue());

                playerOne.enqueue(playerOne.dequeue());
                playerOne.enqueue(playerTwo.dequeue());
            } else if (value(playerOne.dequeue()) < value(playerTwo.dequeue())) {
                while (!table.isEmpty())
                    playerTwo.enqueue(table.dequeue());

                playerTwo.enqueue(playerTwo.dequeue());
                playerTwo.enqueue(playerOne.dequeue());
            }

            if (isPlaying)
                checkEnd(rounds++);
        }
    }

    public void checkEnd(int rounds) {
        if (playerOne.isEmpty()) {
            System.out.println("Player 2 wins!");
            isPlaying = false;
        } else if (playerTwo.isEmpty()) {
            System.out.println("Player 1 wins!");
            isPlaying = false;
        } else if (table.isFull()) {
            System.out.println("Tie game ended because both players ran out of cards.");
            isPlaying = false;
        } else if (rounds >= 100000) {
            System.out.println("Tie game ended because rounds exceeded 100000.");
            isPlaying = false;
        }
    }

    public int value(String card) {
        if (card == null) return 0;

        char first = card.charAt(0);

        if (first == 'T') return 10;
        else if (first == 'J') return 11;
        else if (first == 'Q') return 12;
        else if (first == 'K') return 13;
        else if (first == 'A') return 14;
        else return Integer.parseInt(card.substring(0, 1));
    }

    public void war(int rounds) {
        if (!playerOne.isEmpty() && !playerTwo.isEmpty()) {
            table.enqueue(playerOne.dequeue());
            table.enqueue(playerTwo.dequeue());
        } else {
            checkEnd(rounds);
        }

        if (!playerOne.isEmpty() && !playerTwo.isEmpty()) {
            table.enqueue(playerOne.dequeue());
            table.enqueue(playerTwo.dequeue());
        } else {
            checkEnd(rounds);
        }
    }

    public static void main(String[] args) {
        new WarPlayer();
    }
}
