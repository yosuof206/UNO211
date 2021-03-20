import java.util.*;

public class Uno {
	
	final private static int NUMCARDSHAND = 7;

	private CollectionOfUnoCards deck;
	private CollectionOfUnoCards discardPile;
	private CollectionOfUnoCards hand1;
	private CollectionOfUnoCards hand2;
	
	public Uno() {
		Random r = new Random();
		
		// Make the deck and shuffle it.
		deck = new CollectionOfUnoCards();
		deck.makeDeck();
		deck.shuffle();
		
		// Discard Pile
		discardPile = new CollectionOfUnoCards();
		
		// Create the two hands.
		hand1 = new CollectionOfUnoCards();
		hand2 = new CollectionOfUnoCards();
		
		// Deal them alternately.
		for (int i=0; i<NUMCARDSHAND; i++) {
			hand1.addCard(deck.removeFromTop());
			hand2.addCard(deck.removeFromTop());
		}
	}
	
	public void playGame() {
		
		Scanner stdin = new Scanner(System.in);
		
		System.out.println("Player 1, here is your hand:\n"+hand1);
		System.out.println("What card would you like to discard? Please give the associated number.");
		int card = stdin.nextInt();
		discardPile.addCard(hand1.remove(card));
		
		int turn = 2;
		
		// The game ends if the deck or either player's hand is empty.
		while (deck.getNumCards() > 0 && hand1.getNumCards() > 0 && hand2.getNumCards() > 0) {
			
			playTurn(turn);
		
			// Flip the player's turn.
			if (turn == 1)
				turn = 2;
			else
				turn = 1;	
		}
		
		printResult();
		
	}
	
	// Precondition: Can only be called at the end of the game!
	public void printResult() {
		if (deck.getNumCards() == 0)
			System.out.println("Sorry, the game has ended in a draw.");
		else if (hand1.getNumCards() == 0)
			System.out.println("Player 1, you win =)");
		else
			System.out.println("Player 2, you win =)");
	}
	
	// Plays one turn for the player number indicated.
	public void playTurn(int player) {
		
		Scanner stdin = new Scanner(System.in);
		
		System.out.println("The card at the top of the discard pile is "+discardPile.getTopCard());
		
		if (player == 1) {
			
			// We have a card to play!
			if (hand1.canPlay(discardPile.getTopCard())) {
				System.out.println("\nPlayer 1, here is your hand:\n"+hand1);
				System.out.println("What card would you like to discard? Please give the associated number.");
				int card = stdin.nextInt();
				
				// Only play this card if it's really valid!
				if (hand1.getCard(card).canPlay(discardPile.getTopCard()))
					discardPile.addCard(hand1.remove(card));
				else
					System.out.println("Sorry that is not a valid card. You lost your opportunity to drop a card.");
				
				// UNO =)
				if (hand1.getNumCards() == 1)
					System.out.println("Player One says UNO!!!!");
			}
			
			// Add a card and show the result.
			else {
				System.out.println("Sorry, you can't play on this card. A card has been drawn for you.");
				hand1.addCard(deck.removeFromTop());
				System.out.println("Player 1, here is your resulting hand:\n"+hand1);
			}
		}
		else {
			
			// We have a card to play!
			if (hand2.canPlay(discardPile.getTopCard())) {
				System.out.println("\nPlayer 2, here is your hand:\n"+hand2);
				System.out.println("What card would you like to discard? Please give the associated number.");
				int card = stdin.nextInt();
				
				// Only play this card if it's really valid!
				if (hand2.getCard(card).canPlay(discardPile.getTopCard()))
					discardPile.addCard(hand2.remove(card));
				else
					System.out.println("Sorry that is not a valid card. You lost your opportunity to drop a card.");
				
				// UNO =)
				if (hand2.getNumCards() == 1)
					System.out.println("Player Two says UNO!!!!");
			}
			
			// Add a card and show the result.
			else {
				System.out.println("Sorry, you can't play on this card. A card has been drawn for you.");
				hand2.addCard(deck.removeFromTop());
				System.out.println("Player 1, here is your resutling hand:\n"+hand2);
			}
			
		}
	}
	
	public static void main(String[] args) {
		Uno myGame = new Uno();
		myGame.playGame();
	}
}
