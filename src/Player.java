/*
 * File name: Player.java
 * Author: Inessa Khodak
 * Lab: Lab #4
 * Date: August 19, 2022
 * Note: this is outside of the Lab 4 requirement, edited Lab 4 to practice using enums.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * Player class is a public class that contains three private fields to store
 * player's name, list of cards and win's counter, one overloaded constructor, and methods
 * to simulate a simple card game.
 */
public class Player {

	private String name;//player's name
	private List<Card_enum> hand = new ArrayList<>();//list of player's cards
	private int handsWon = 0;//number of rounds won by a player

	/*
	 * Player class constructor that creates a player instance with the name
	 * received as a parameter.
	 */
	public Player(String nameParam) {
		name = nameParam;
	}

	/* 
	 * Method addCards adds a list of cards to the player's hand.
	 */
	public void addCards(List<Card_enum> cards) {
		hand.addAll(cards);
	}

	/*
	 * Method getHand creates a description of the current hand that a player holds.
	 */
	public String getHand() {
		String currentHand = "";

		for (int i = 0; i < hand.size(); i++) {
			currentHand += hand.get(i).getShortName() + " ";
		}
		return currentHand;
	}

	/*
	 * Method handWon is called each time a player wins a hand and adds the win to
	 * the counter.
	 */
	public void handWon() {
		handsWon += 1;
	}

	/*
	 * Method getScore gets all rounds won by the player in the game.
	 */
	public int getScore() {
		return handsWon;
	}

	/*
	 * Method getName gets the name of the player.
	 */
	public String getName() {
		return name;
	}

	/*
	 * Method reset deletes all cards from the player's hand and clears off the
	 * current score.
	 */
	public void reset() {
		hand.clear();
		handsWon = 0;
	}

	/*
	 * Method playCard randomly chooses a card and removes it from the player's
	 * hand.
	 */
	public Card_enum playCard() {
		Random rand = new Random();

		int randIndex = rand.nextInt(hand.size());
		Card_enum playingCard = hand.get(randIndex);
		hand.remove(randIndex);

		return playingCard;
	}

	/*
	 * Method playCardSuit chooses a card to play from the player's hand depending
	 * on a suit of the card that was played earlier in the round. Two possible
	 * moves could be made: a) if a player has any cards of the same suit, he will
	 * play with the one that has the highest value; b) if a player doesn't have a
	 * card of the same suit, he will play a random card from his hand.
	 */
	public Card_enum playCardSuit(Suits cardSuit) {
		Card_enum playingCard = null;
		List<Card_enum> playingSuit = new ArrayList<>();//list of cards with the same playing suit
		Suits suit;

		for (Card_enum card : hand) {// adding all cards with matching suit to a separate array list
			suit = card.getSuit();
			if (suit == cardSuit) {
				playingSuit.add(card);
			}
		}

		if (playingSuit.size() > 0) {// if there were cards matching the playing suit, get the card with the highest
										// value
			int maxRank = playingSuit.get(0).getCardValue();// set first card of the list as the highest
			int currentRank = 0;

			for (Card_enum card : playingSuit) {// looping over all cards of the playing suit to get the card with max value
				currentRank = card.getCardValue();
				if (currentRank >= maxRank) {
					maxRank = currentRank;
					playingCard = card;
				}
			}
			hand.remove(playingCard);
		} else {
			playingCard = playCard();// play random card if there are no cards matching the playing suit
		}

		return playingCard;
	}
}
