//Alex A. Ortiz Cotto
//801-16-5701
//CCOM4029-002
// Prof. Patricia Ordonez

/*The importance of the this file is to define the MyStack that works along side
of Card to create a personal and function Stack tailor specificly for Card*/

public class MyStack{

  private Card[] stack; //Stack variables
  private int bottom; //The Final element of the Stack
  private int top; //The Top element of the Stack

  //MyStack constructer
  public MyStack(int size){
    this.stack = new Card[size]; //Defines a size for the stack
    this.top = size; //Defines the top variable
    this.bottom = -1; //Defines the lower variable
  }

  //Function for isEmpty
  public boolean isEmpty() {
    return (top == -1); //Returns if it is empty
  }

  //Function for isFull
  public boolean isFull() {
    return (top == (bottom - 1)); //Returns if it is full
  }

  //Function for push
  public void push(Card card) {
    //If the list is not full it will continue to push
    if (!isFull()) {
      top++; //Increases the location of the top
      stack[top] = card; //Puts the new card in the location of the top
    }
  }

  //Function for pop
  public Card pop() {
    int curr = top; //Defines a variable to establish the current location
    //If the list is not empty it will continue to pop
    if (!isEmpty()) {
      top--; //Decreases the location of the top
      return stack[curr]; //Returns the variable eliminated
    }
    return null; //If it is empty will return null
  }

  //Function for peek
  public Card peek() {
    //If the list is not empty it will continue to return the value in the location given
    if (!isEmpty()) {
      return stack[top]; //Returns the value asked
    }
    return null;//If it is empty will return null
  }

}
