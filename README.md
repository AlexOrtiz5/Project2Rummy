# Project2 - Rummy

Alex A. Ortiz Cotto
801-16-5701
CCOM4029
Prof. Patricia Ordonez

Purpose: The purpose of this assignment is to teach you to identify and use design 
patterns. You will extend the GUI and incorporate it into the logic of the game.
You have an opportunity to earn extra credit if your GUI goes above and beyond 
and you are able to explain your design choices.


Usage:
1. Before compiling must Unzip cards.zip
2. Compile all using javac *.java
3. Then run java Proj2

Rules of the Game:
1. Each player is dealt 9 cards from the stock pile (i.e. Deck)
2. The next card from the Deck is turned face-up and placed in the discard pile (i.e. Stack).
3. In each turn, a player:
  a. Draws from the Deck
  b. Does some optional actions:
    i.  Lay a Set on the table
    ii. Lay a Run on the table
  c. Discards to the Stack
4. The game is over when either:
  a. One player is out of cards
    i. In which case, said player is crowned winner
  b. The cards from the stock pile are exhausted
    i. If the cards from the Deck are exhausted, all players count the points remaining in their hands, and the lowest value hand wins (ties are possible!)

Points:
1. Aces are worth 1 point
2. Face cards are worth 10 points
3. All other cards are worth their face value (i.e. Seven of Spades is worth 7 points)

Notes:
1. The project is in Java 13 SE 
2. To create the file containing your logs, use the command java Proj2 > p2-output.txt (after compiling, of course)

Extra Credits:
I didn't implement extra credits functions
