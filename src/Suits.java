/*
 * File name: Suits.java
 * Author: Inessa Khodak
 * Lab: Lab #4 -  version with enum type
 * Date: August 19, 2022
 * Note: this is outside of the Lab 4 requirement, edited Lab 4 to practice using enums.
 */
/*
 * Suits class contains enum type constants for a card deck suits.
 */
public enum Suits {
	
	SPADES("s"), HEARTS("h"), DIAMONDS("d"), CLUBS("c");
	private final String suit;//short name of the suit
	
	/*
	 * Suit constructor
	 */
	Suits(String suitParam) {
		suit = suitParam;
	}
	
	public String getSuit() {
		return suit;
	}
}
