package squeezebox;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by 180502 on 10/25/2017.
 */
public class SqueezeBox {
    int cardIndex;
    ArrayList<ArrayDeque<String>> piles;
    String[] cards;

    public void run() {
        Scanner in = new Scanner(System.in);

        try {
            in = new Scanner(new File("squeezebox.dat"));
        } catch(FileNotFoundException e) {
            try {
                in = new Scanner(SqueezeBox.class.getResourceAsStream("squeezebox.dat"));
            } catch(NullPointerException o) {
                e.printStackTrace();
                o.printStackTrace();
            }
        }

        while (in.hasNext()) {
            piles = new ArrayList<>();
            cardIndex = 0;

            String line = in.nextLine();

            if (line.equals("#")) return;

            String[] line1 = line.split(" ");
            String[] line2 = in.nextLine().split(" ");
            cards = new String[line1.length + line2.length];

            for (int i = 0; i < line1.length; i++) {
                cards[i] = line1[i];
                cards[i + line1.length] = line2[i];
            }

            while(cardIndex < 52) {
                flip();

                boolean moved;
                do {
                    moved = false;
                    for(int i = 0; i < piles.size(); i++) {
                        if(i >= 3) {
                            if(topCardsMatch(i, i - 3)) {
                                moveCard(i, i - 3);
                                moved = true;
                                break;
                            }
                        }
                        if(i >= 1) {
                            if(topCardsMatch(i, i - 1)) {
                                moveCard(i, i - 1);
                                moved = true;
                                break;
                            }
                        }
                    }

                } while(moved);
            }

            System.out.print(piles.size() + " piles remaining: ");

            for (int i = 0; i < piles.size(); i++)
                System.out.print(piles.get(i).size() + (i == piles.size() - 1 ? "" : " "));

            System.out.println();
        }
    }

    public void flip() {
        ArrayDeque newArrayDeque = new ArrayDeque();
        newArrayDeque.add(cards[cardIndex]);
        cardIndex++;
        piles.add(newArrayDeque);
    }

    public boolean topCardsMatch(int pileIndex1, int pileIndex2) {
        Card card1 = new Card(piles.get(pileIndex1).getFirst());
        Card card2 = new Card(piles.get(pileIndex2).getFirst());
        return card1.matches(card2);
    }

    public void moveCard(int pileIndexRight, int pileIndexLeft) {
        Card movingCard = new Card(piles.get(pileIndexRight).removeFirst());

        piles.get(pileIndexLeft).addFirst(movingCard.toString());

        if(piles.get(pileIndexRight).isEmpty())
        {
            piles.remove(pileIndexRight);
        }
    }

    public static void main(String[] args) {
        new SqueezeBox().run();
    }
}
