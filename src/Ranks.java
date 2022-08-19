/*
 * File name: Ranks.java
 * Author: Inessa Khodak
 * Lab: Lab #4 -  version with enum type
 * Date: August 19, 2022
 * Note: this is outside of the Lab 4 requirement, edited Lab 4 to practice using enums.
 */

/*
 * Ranks class contains enum type constants for a card deck faces.
 */
public enum Ranks {
	ACE ("A", 14), KING ("K", 13), QUEEN ("Q", 12), JACK ("J", 11), 
	TEN ("10", 10), NINE ("9", 9), EIGHT ("8", 8), SEVEN ("7", 7), 
	SIX ("6", 6), FIVE ("5", 5), FOUR ("4", 4), THREE ("3", 3), TWO ("2", 2);
	
	private String face;
	private int value;

	/*
	 * Rank constructor
	 */
	Ranks(String faceParam, int valueParam) {
		face = faceParam;
		value = valueParam;
	}
	
	public String getFace() {
		return face;
	}
	
	public int getValue() {
		return value;
	}
}
