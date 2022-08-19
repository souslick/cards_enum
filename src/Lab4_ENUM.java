/*
 * File name: Lab4.java
 * Author: Inessa Khodak
 * Lab: Lab #4
 * Date: August 19, 2022
 * Professor: Daniel Cormier
 * Notee: this is outside of the Lab 4 requirement, edited Lab 4 to practice using enums.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.List;

/*
 * Lab4 class provides methods that control a simple game of cards using Card
 * and Player classes.
 */
public class Lab4_ENUM {

	/*
	 * Program entry point.
	 */
	public static void main(String[] args) {
		Lab4_ENUM myGame = new Lab4_ENUM();
	}

	/*
	 * Lab4 contains the card game flow.
	 */
	Lab4_ENUM() {
		final int deck = 52;//number of cards in a card deck
		final int MAX_PLAYERS = 4;
		final int SUITS = Suits.values().length;
		final int RANKS = Ranks.values().length;
		List<Card_enum> cardDeck = new ArrayList<>(deck);// create array list for the card deck
		

		for (int i = 0; i < SUITS; i++) {// populating new card deck
			for (int j = 0; j < RANKS; j++) {
				cardDeck.add(new Card_enum(Suits.values()[i],Ranks.values()[j]));
			}
		}

		System.out.println("Welcome to Cards Game!");
		System.out.println("Before we start, let's name the four players");

		List<Player> players = new ArrayList<>(MAX_PLAYERS);// creating an array list to store players
		for (int i = 0; i < MAX_PLAYERS; i++) {// ask user for players' names, create players
			String message = "Enter player " + (i + 1) + "'s name: ";
			players.add(new Player(askUser(message)));
		}

		String continueGame = "y";
		String choice = "";
		Scanner input = new Scanner(System.in);

		do {// looping until user wants to quit the game
			System.out.println("Dealing cards...\n");
			Collections.shuffle(cardDeck);// shuffle cards

			int maxHand = cardDeck.size() / MAX_PLAYERS;// max number of cards per player

			for (Player player : players) {// dealing cards
				int firstCard = 0;
				List<Card_enum> deal = cardDeck.subList(firstCard, maxHand);// get first 13 cards from the deck
				player.addCards(deal);
				deal.clear();
			}

			int round = 0;// set round counter to zero before game starts
			int winnerIndex = 0;// set winner index pointing to the first player before game starts
			boolean isQuit = false;
			Player roundWinner = null;
			List<Player> playersTemp = new ArrayList<>(MAX_PLAYERS);//to store rotated list of players

			while (!isQuit && round < maxHand) {// playing rounds unless user wants to quit the game
				System.out.println("This is round " + (round + 1) + ":");

				printHands(players);// printing players hands

				System.out.println("Enter q to quit, anything else to play out this round: ");
				choice = input.nextLine();

				if (choice.equalsIgnoreCase("q")) {
					isQuit = true;
				} 
				else {
					if (winnerIndex !=0 ) {//if winner of previous round is not the first player, rotate the list of players 
						playersTemp = getCurrentQueue(winnerIndex, players);// get current order of players
					}
					else {
						playersTemp = players;
					}
					roundWinner = playCards(playersTemp, cardDeck);// get round winner
					winnerIndex = players.indexOf(roundWinner);// get winner's index in the players' list
					round += 1;
				}
				System.out.println();
			}

			if (isQuit != true) {// choosing a winner if game continued all rounds
				int max = 0;
				int score = 0;
				Player winner = null;
				List<Player> winners = new ArrayList<>(round / MAX_PLAYERS);// to store players that have even max results
																			// after the game was played

				for (Player player : players) {// looping over players' list to get max score
					score = player.getScore();
					if (score > max) {
						max = score;
					}
				}

				for (Player player : players) {// looping over players' list to get winner(s)
					score = player.getScore();
					if (score == max) {
						winners.add(player);
					}
				}

				System.out.println("Game Over!");
				System.out.println("Final scores after " + round + " rounds played:");

				for (Player player : players) {// print final results of the game for each player
					System.out.printf("%s with %d hands won.%n", player.getName(), player.getScore());
				}

				if (winners.size() > 1) {// if there is more than one winner in the game
					winner = chooseWinner(winners);// choose a winner randomly from the list
					System.out.print("We had to toss a coin to choose a winner! ");
				} else {
					winner = winners.get(0);
				}
				System.out.printf("%s has won!%n%n", winner.getName());

				// reset score to all players
				for (Player player : players) {
					player.reset();
				}

				System.out.println("Another game? y to continue: ");
				choice = input.nextLine();
			}
		} while (continueGame.equalsIgnoreCase(choice));

		System.out.println("Thank you for playing!");
		System.out.println("Program by Inessa Khodak");
	}

	/*
	 * Method askUser provides a check for the minimum number of letters in the
	 * input.
	 */
	String askUser(String message) {
		Scanner input = new Scanner(System.in);
		boolean isCorrect = false;
		String playerName = "";
		System.out.print(message);

		while (!isCorrect) {// looping until user inputs the correct name for a player, should have at least
							// 2 symbols
			playerName = input.nextLine();
			if (playerName.length() > 1) {
				isCorrect = true;
			} else {
				System.out.print("Player's name should have at least 2 letters. Please enter the name: ");
			}
		}

		return playerName;
	}

	/*
	 * Method printHands gets each player's hand and statistics and displays it.
	 */
	void printHands(List<Player> players) {
		String playerName;
		int handsWon;
		String playerHand;

		for (Player player : players) {// looping over players' list to print current hands for each player
			playerName = player.getName();
			handsWon = player.getScore();
			playerHand = player.getHand();
			System.out.printf("%s (%d hands won) : %s", playerName, handsWon, playerHand);
			System.out.println();
		}
	}

	/*
	 * Method getCurrentQueue creates a copy of the players' list and sets the winner of the previous 
	 * round to the first position.
	 */
	List<Player> getCurrentQueue(int winnerIndex, List<Player> players) {
		List<Player> currentQueue = new ArrayList<>(players.size());
		int rotated;
		for (int i = 0; i < players.size(); i++) {
			rotated = (i + winnerIndex)%players.size();
			currentQueue.add(players.get(rotated));
		}
		
		return currentQueue;

	}

	/*
	 * Method playCards controls the players' moves in each round and defines the
	 * round winner. After the card is played it's returned to the card deck.
	 * Players have two options: a) move a random card in case it's the first move
	 * in the round; b) move a card based on the card suit played by the first
	 * player of the round.
	 */
	Player playCards(List<Player> playersParam, List<Card_enum> cardDeck) {
		String playerName;
		Card_enum card;
		String cardLongName;
		Player roundWinner = null;
		Suits playingSuit = null;
		int maxCard = 0;

		for (Player player : playersParam) {// looping over the players' list and play the round
			if (playersParam.indexOf(player)==0) {// if it's a first card playing, choose a card randomly
												// and set a playing suit for this round
				card = player.playCard();
				playingSuit = card.getSuit();
				maxCard = card.getCardValue();
				roundWinner = player;
			} else {// any other player is playing according to the card suit set for the round
				card = player.playCardSuit(playingSuit);
			}
			

			if ((card.getSuit() == playingSuit) && (card.getCardValue() >= maxCard)) {//check for the max value of played card
				maxCard = card.getCardValue();
				roundWinner = player;
			}

			playerName = player.getName();
			cardLongName = card.getLongName();
			System.out.printf("%s plays %s%n", playerName, cardLongName);
			cardDeck.add(card);// played card added back  to the deck
		}

		roundWinner.handWon();//add the win to the player
		System.out.println(roundWinner.getName() + " wins this hand!");
		return roundWinner;
	}

	/*
	 * Method chooseWinner randomly chooses a winner of the game in case of a tie.
	 */
	Player chooseWinner(List<Player> players) {
		Random rand = new Random();
		int randomWinner = rand.nextInt(players.size());
		Player winner = players.get(randomWinner);

		return winner;
	}

}
