/*
 * File name: Card_enum.java
 * Author: Inessa Khodak
 * Lab: Lab #4 -  version with enum type
 * Date: August 19, 2022
 * Note: this is outside of the Lab 4 requirement, edited Lab 4 to practice using enums
 */

/*
 * Card class contains two private fields to store card's rank and suit, 
 * a constructor to create a Card object and provides methods to get card's,
 * suit and value, short and long names of the card.
 */
public class Card_enum {

	private Suits suit;
	private Ranks rank;

	/*
	 * Card class constructor that creates a card instance with rank and suit
	 * enums as parameters.
	 */

	public Card_enum(Suits suitParam, Ranks rankParam) {
		suit = suitParam;
		rank = rankParam;
	}

	/*
	 * Method getShortName gets the short version of the card name.
	 */
	public String getShortName() {
		return rank.getFace() + suit.getSuit();
	}

	/* 
	 * Method getLongName gets the long version of the card name.
	 */
	public String getLongName() {
		return rank + " of " + suit;
	}

	/*
	 * Method getSuit gets the card suit index.
	 */
	public Suits getSuit() {
		return suit;
	}

	/*
	 * Method getCardValue get the value of the card.
	 */
	public int getCardValue() {
		return rank.getValue();
	}
}
