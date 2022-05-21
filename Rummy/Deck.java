//Alex A. Ortiz Cotto
//801-16-5701
//CCOM4029-002
// Prof. Patricia Ordonez

import java.util.*;

public class Deck implements DeckInterface {

   private LinkedList<Card> deck;
   private int index;


  /**
   * Creates an empty deck of cards.
   */
   public Deck() {
      deck = new LinkedList<Card>();

   }
   public Card peek(){
     if(deck.size() == 0)
			return null;
		 else
			return (Card)deck.getLast();
    }

    public void addCard( Card card ) {
      deck.add( card );
    }

    public int getSizeOfDeck() {
      return deck.size();
    }

    public Card dealCard() {
      if ( deck.size() == 0 )
           return null;
      else
        return (Card) deck.removeLast();
    }

    public Card removeCard() {
      if (deck.size() == 0)
         return null;
      else
        return (Card) deck.removeLast( );
    }

    /**
     * Shuffles the cards present in the deck.
     */
    public void shuffle() {
      Collections.shuffle( deck );
    }

    /**
     * Looks for an empty deck.
     * @return <code>true</code> if there are no cards left to be dealt from the deck.
     */
     public boolean isEmpty() {
       return deck.isEmpty();
     }

     /**
      * Restores the deck to "full deck" status.
      */
      public void restoreDeck() {
        //not sure if kosher
        deck.removeAll(deck);
     }
     
}
