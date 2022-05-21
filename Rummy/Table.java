import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
/**
*	This GUI assumes that you are using a 52 card deck and that you have 13 sets in the deck.
*	The GUI is simulating a playing table
	@author Patti Ordonez
*/

public class Table extends JFrame implements ActionListener
{
	final static int numDealtCards = 9;
	JPanel player1;
	JPanel player2;
	JPanel deckPiles;
	JLabel deck;
	JLabel stack;
	JList p1HandPile;
	JList p2HandPile;
	Deck cardDeck;
	Deck stackDeck;

	SetPanel [] setPanels = new SetPanel[13];
	JLabel topOfStack;
	JLabel deckPile;
	JButton p1Stack;
	JButton p2Stack;

	JButton p1Deck;
	JButton p2Deck;

	JButton p1Lay;
	JButton p2Lay;

	JButton p1LayOnStack;
	JButton p2LayOnStack;

	JButton p1ChangeTurn; //Added
	JButton p2ChangeTurn; //Added

	Hand p1Hand;
	Hand p2Hand;

	boolean p1Turn = true; //Added
	boolean p2Turn = false; //Added
	boolean FinalTurn = false; //Added

	private void deal(Card [] cards){
		for(int i = 0; i < cards.length; i ++){
			cards[i] = (Card)cardDeck.dealCard();
		}

	}

	public Table()
	{
		super("The Card Game of the Century");

		setLayout(new BorderLayout());
		setSize(1200,700);

		cardDeck = new Deck();

		for(int i = 0; i < Card.suit.length; i++){
			for(int j = 0; j < Card.rank.length; j++){
				Card card = new Card(Card.suit[i],Card.rank[j]);
				cardDeck.addCard(card);
			}
		}
		cardDeck.shuffle();
		stackDeck = new Deck();

		JPanel top = new JPanel();

		for (int i = 0; i < Card.rank.length;i++)
			setPanels[i] = new SetPanel(Card.getRankIndex(Card.rank[i]));

		top.add(setPanels[0]);
		top.add(setPanels[1]);
		top.add(setPanels[2]);
		top.add(setPanels[3]);

		player1 = new JPanel();
		player1.add(top);

		add(player1, BorderLayout.NORTH);
		JPanel bottom = new JPanel();

		bottom.add(setPanels[4]);
		bottom.add(setPanels[5]);
		bottom.add(setPanels[6]);
		bottom.add(setPanels[7]);
		bottom.add(setPanels[8]);

		player2 = new JPanel();

		player2.add(bottom);
		add(player2, BorderLayout.SOUTH);

		JPanel middle = new JPanel(new GridLayout(1,3));

		p1Stack = new JButton("Stack");
		p1Stack.addActionListener(this);
		p1Deck = new JButton("Deck ");
		p1Deck.addActionListener(this);
		p1Lay = new JButton("Lay  ");
		p1Lay.addActionListener(this);
		p1LayOnStack = new JButton("LayOnStack");
		p1LayOnStack.addActionListener(this);
		//Added a JButton to end the turn of the player
		p1ChangeTurn = new JButton("End-Turn");
		p1ChangeTurn.addActionListener(this);

		Card [] cardsPlayer1 = new Card[numDealtCards];
		deal(cardsPlayer1);
		//The p1Hand now is part of the Hand Class
		p1Hand = new Hand();
		for(int i = 0; i < cardsPlayer1.length; i++)
			p1Hand.addCard(cardsPlayer1[i]);
		//In order the get a JList it is important the getArray function from the Hand Class
		p1HandPile = new JList(p1Hand.getArray());
		//Returns the p1Hands before the games begin
		System.out.println("Initial Player 1: " + p1Hand.toString());

		middle.add(new HandPanel("Player 1", p1HandPile, p1Stack, p1Deck, p1Lay, p1LayOnStack, p1ChangeTurn));

		deckPiles = new JPanel();
		deckPiles.setLayout(new BoxLayout(deckPiles, BoxLayout.Y_AXIS));
		deckPiles.add(Box.createGlue());
		JPanel left = new JPanel();
		left.setAlignmentY(Component.CENTER_ALIGNMENT);

		stack = new JLabel("Stack");
		stack.setAlignmentY(Component.CENTER_ALIGNMENT);

		left.add(stack);
		topOfStack = new JLabel();
		topOfStack.setIcon(new ImageIcon(Card.directory + "blank.gif"));
		topOfStack.setAlignmentY(Component.CENTER_ALIGNMENT);
		left.add(topOfStack);
		deckPiles.add(left);
		deckPiles.add(Box.createGlue());

		JPanel right = new JPanel();
		right.setAlignmentY(Component.CENTER_ALIGNMENT);

		deck = new JLabel("Deck");

		deck.setAlignmentY(Component.CENTER_ALIGNMENT);
		right.add(deck);
		deckPile = new JLabel();
		deckPile.setIcon(new ImageIcon(Card.directory + "b.gif"));
		deckPile.setAlignmentY(Component.CENTER_ALIGNMENT);
		right.add(deckPile);
		deckPiles.add(right);
		deckPiles.add(Box.createGlue());
		middle.add(deckPiles);

		p2Stack = new JButton("Stack");
		p2Stack.addActionListener(this);
		p2Deck = new JButton("Deck ");
		p2Deck.addActionListener(this);
		p2Lay = new JButton("Lay  ");
		p2Lay.addActionListener(this);
		p2LayOnStack = new JButton("LayOnStack");
		p2LayOnStack.addActionListener(this);
		//Added a JButton to end the turn of the player
		p2ChangeTurn = new JButton("End-Turn");
		p2ChangeTurn.addActionListener(this);

		Card [] cardsPlayer2 = new Card[numDealtCards];
		deal(cardsPlayer2);
		//The p2Hand now is part of the Hand Class
		p2Hand = new Hand();
		for(int i = 0; i < cardsPlayer2.length; i++)
			p2Hand.addCard(cardsPlayer2[i]);
		//In order the get a JList it is important the getArray function from the Hand Class
		p2HandPile = new JList(p2Hand.getArray());
		//Returns the p2Hands before the games begin
    System.out.println("Initial Player 2: " + p2Hand.toString());

		middle.add(new HandPanel("Player 2", p2HandPile, p2Stack, p2Deck, p2Lay, p2LayOnStack, p2ChangeTurn));

		add(middle, BorderLayout.CENTER);

		JPanel leftBorder = new JPanel(new GridLayout(2,1));
		setPanels[9].setLayout(new BoxLayout(setPanels[9], BoxLayout.Y_AXIS));
		setPanels[10].setLayout(new BoxLayout(setPanels[10], BoxLayout.Y_AXIS));
		leftBorder.add(setPanels[9]);
		leftBorder.add(setPanels[10]);
		add(leftBorder, BorderLayout.WEST);

		JPanel rightBorder = new JPanel(new GridLayout(2,1));
		setPanels[11].setLayout(new BoxLayout(setPanels[11], BoxLayout.Y_AXIS));
		setPanels[12].setLayout(new BoxLayout(setPanels[12], BoxLayout.Y_AXIS));
		rightBorder.add(setPanels[11]);
		rightBorder.add(setPanels[12]);
		add(rightBorder, BorderLayout.EAST);

	}

	//Separeted all of the actions in order to better highlight where each part is located on
	public  void actionPerformed(ActionEvent e){
		Object src = e.getSource();

		//Deals with all Deck related actions
		if(p1Deck == src|| p2Deck == src){
			Card card = cardDeck.dealCard();

			if (card != null){
				//If player1 turn
				if(p1Deck == src && p1Turn == true){
					System.out.println("Player 1");
					p1Hand.addCard(card);
					p1HandPile.setListData(p1Hand.getArray());
					System.out.println("Added: " + card.toString());
				}
				//If player2 turn
				if(p2Deck == src && p2Turn == true){
					System.out.println("Player 2");
					p2Hand.addCard(card);
					p2HandPile.setListData(p2Hand.getArray());
					System.out.println("Added: " + card.toString());
				}

			}
			if(cardDeck.getSizeOfDeck() == 0)
				deckPile.setIcon(new ImageIcon(Card.directory + "blank.gif"));

		}
		//////////////////////////////////////////////////////////////////
		//Deals with all Stack related actions
		if(p1Stack == src || p2Stack == src){
			Card card = stackDeck.removeCard();

			if(card != null){
				Card topCard = stackDeck.peek();
				if (topCard != null)
					topOfStack.setIcon(topCard.getCardImage());
				else
					topOfStack.setIcon(new ImageIcon(Card.directory + "blank.gif"));

				//If player1 turn
				if(p1Stack == src && p1Turn == true) {
					p1Hand.addCard(card);
					p1HandPile.setListData(p1Hand.getArray());
				}
				//If player2 turn
				if(p2Stack == src && p2Turn == true) {
					p2Hand.addCard(card);
					p2HandPile.setListData(p2Hand.getArray());
				}
			}

		}
		//////////////////////////////////////////////////////////////////////
		//Deals with all Lay related actions
		//If player1 turn
		if(p1Lay == src && p1Turn == true){
			Object [] cards = p1HandPile.getSelectedValues();
			if (cards != null)
				for(int i = 0; i < cards.length; i++)
				{
					Card card = (Card)cards[i];
					layCard(card);
					p1Hand.removeCard(card);
					p1HandPile.setListData(p1Hand.getArray());
				}
		}
		//If player2 turn
		if(p2Lay == src && p2Turn == true){
			Object [] cards = p2HandPile.getSelectedValues();
			if (cards != null)
				for(int i = 0; i < cards.length; i++)
				{
					Card card = (Card)cards[i];
					layCard(card);
					p2Hand.removeCard(card);
					p2HandPile.setListData(p2Hand.getArray());
				}
		}
		////////////////////////////////////////////////////////////////////
		//Deals with all LayOnStack related actions
		//If player1 turn
		if(p1LayOnStack == src && p1Turn == true){
			int [] num  = p1HandPile.getSelectedIndices();
			if (num.length == 1)
			{
				Object obj = p1HandPile.getSelectedValue();
				if (obj != null)
				{
					p1Hand.removeCard(obj);
					p1HandPile.setListData(p1Hand.getArray());
					Card card = (Card)obj;
					stackDeck.addCard(card);
					topOfStack.setIcon(card.getCardImage());
					System.out.println("Discard: " + card.toString());
				}
			}
		}
		//If player2 turn
		if(p2LayOnStack == src && p2Turn == true){
			int [] num  = p2HandPile.getSelectedIndices();
			if (num.length == 1)
			{
				Object obj = p2HandPile.getSelectedValue();
				if (obj != null)
				{
					p2Hand.removeCard(obj);
					p2HandPile.setListData(p2Hand.getArray());
					Card card = (Card)obj;
					stackDeck.addCard(card);
					topOfStack.setIcon(card.getCardImage());
					System.out.println("Discard: " + card.toString());
				}
			}
		}
		////////////////////////////////////////////////////////////////////////
		//Deals with all changeTurn related actions
		//If player1 turn
		if(p1ChangeTurn == src && p1Turn == true){
			System.out.println("Hand now: " + p1Hand.toString());
			System.out.println("Cards left in deck: " + cardDeck.getSizeOfDeck());
			p1Turn = false;
			p2Turn = true;
			//If the Deck Size reaches 0 before the turn is finish end after discarding
			if(cardDeck.getSizeOfDeck() == 0){
				FinalTurn = true;
			}
		}
		//If player2 turn
		if(p2ChangeTurn == src && p2Turn == true){
			System.out.println("Hand now: " + p2Hand.toString());
			System.out.println("Cards left in deck: " + cardDeck.getSizeOfDeck());
			p1Turn = true;
			p2Turn = false;
			//If the Deck Size reaches 0 before the turn is finish end after discarding
			if(cardDeck.getSizeOfDeck() == 0){
				FinalTurn = true;
			}
		}
		////////////////////////////////////////////////////////////////////////
		//Deals with all FinalTurn related actions
		if(FinalTurn == true){
			int p1Value = 0; //Stores the values of the player1 after the deck reaches 0
			for (int i = 0; i < p1Hand.getNumberOfCards(); i++) {
				Card c = p1Hand.getCard(i);
				int p1CardValue = Card.getRankIndex(c.getRank()) - Card.getRankIndex('a') + 1;
				if (p1CardValue > 10) {
					p1CardValue = 10;
				}
				p1Value += p1CardValue;
			}
			//If the player1 gets to discard the whole hand before the deck is 0 return that he won
			if(p1Hand.getNumberOfCards() == 0){
				p1Turn = false;
				p2Turn = false;
				System.out.println("Player 1 Wins");
			}

			int p2Value = 0;//Stores the values of the player2 after the deck reaches 0
			for (int i = 0; i < p2Hand.getNumberOfCards(); i++) {
				Card s = p2Hand.getCard(i);
				int p2CardValue = Card.getRankIndex(s.getRank()) - Card.getRankIndex('a') + 1;
				if (p2CardValue > 10) {
					p2CardValue = 10;
				}
				p2Value += p2CardValue;
			}
			//If the player2 gets to discard the whole hand before the deck is 0 return that he won
			if(p2Hand.getNumberOfCards() == 0){
				p1Turn = false;
				p2Turn = false;
				System.out.println("Player 2 Wins");
			}

			System.out.println("Points: " + p1Value + " to " + p2Value);
			if(p1Value < p2Value){
				p1Turn = false;
				p2Turn = false;
				System.out.println("Player 1 Wins");
			}
			else{
				p1Turn = false;
				p2Turn = false;
				System.out.println("Player 2 Wins");
			}

		}
		////////////////////////////////////////////////////////////////////////

	}

	void layCard(Card card){
		char rank = card.getRank();
		char suit = card.getSuit();
		int suitIndex =  Card.getSuitIndex(suit);
		int rankIndex =  Card.getRankIndex(rank);
		//setPanels[rankIndex].array[suitIndex].setText(card.toString());
		System.out.println("laying " + card);
		setPanels[rankIndex].array[suitIndex].setIcon(card.getCardImage());
	}
}

class HandPanel extends JPanel{

	public HandPanel(String name,JList<Card> hand, JButton stack, JButton deck, JButton lay, JButton layOnStack, JButton changeTurn){
		//model = hand.createSelectionModel();

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//		add(Box.createGlue());
		JLabel label = new JLabel(name);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(label);
		stack.setAlignmentX(Component.CENTER_ALIGNMENT);
//		add(Box.createGlue());
		add(stack);
		deck.setAlignmentX(Component.CENTER_ALIGNMENT);
//		add(Box.createGlue());
		add(deck);
		lay.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lay);
		layOnStack.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(layOnStack);
		changeTurn.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(changeTurn);
		add(Box.createGlue());
		add(hand);
		add(Box.createGlue());
	}

}

class SetPanel extends JPanel{
	private Set data;
	JButton [] array = new JButton[4];

	public SetPanel(int index){
		super();
		data = new Set(Card.rank[index]);

		for(int i = 0; i < array.length; i++){
			array[i] = new JButton("   ");
			add(array[i]);
		}
	}

}
