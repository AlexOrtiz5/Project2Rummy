//Alex A. Ortiz Cotto
//801-16-5701
//CCOM4029-002
// Prof. Patricia Ordonez

/* This file is a similar copy to all of the functions that are in Deck
 * with the difference that it is completely dependent of MyStack.
 */

public class Pile{

  private MyStack pile; //Calls the MyStack function

  /**
   * Creates a define Stack.
   */
  public Pile(){
    pile = new MyStack(52);
  }

  public Card peek(){
    return pile.peek();
  }

  public void addCard(Card card){
    pile.push(card);
  }

  public Card dealCard(){
    return pile.pop();
  }

  public Card removeCard(){
    return pile.pop();
  }

  /**
   * Looks for an empty deck.
   * @return <code>true</code> if there are no cards left to be dealt from the deck.
   */
  public boolean isEmpty(){
    return pile.isEmpty();
  }

  /**
   * Restores the deck to "full deck" status.
   */
  public void restoreDeck(){
    Card done = pile.pop();
    while(done != null){
      done = pile.pop();
    }
  }

}
