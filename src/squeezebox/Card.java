package squeezebox;

/**
 * Created by 180502 on 10/25/2017.
 */
public class Card {
    private int rank;
    private Suit suit;

    public Card(int rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Card(String card) {
        char rank = card.charAt(0);
        char suit = card.charAt(1);

        switch (rank) {
            case 'T':
                this.rank = 10;
                break;
            case 'J':
                this.rank = 11;
                break;
            case 'Q':
                this.rank = 12;
                break;
            case 'K':
                this.rank = 13;
                break;
            case 'A':
                this.rank = 1;
                break;
            default:
                this.rank = Integer.parseInt(rank + "");
        }

        switch (suit) {
            case 'H':
                this.suit = Suit.Hearts;
                break;
            case 'D':
                this.suit = Suit.Diamonds;
                break;
            case 'C':
                this.suit = Suit.Clubs;
                break;
            case 'S':
                this.suit = Suit.Spades;
                break;
        }
    }

    public int rank() {
        return rank;
    }

    public Suit suit() {
        return suit;
    }

    public boolean matches(Card card) {
        return card.suit() == this.suit || card.rank == this.rank;
    }

    public String toString() {
        return rank + "" + suit;
    }
}

enum Suit {
    Hearts,
    Diamonds,
    Clubs,
    Spades
}
