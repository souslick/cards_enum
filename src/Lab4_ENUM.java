/*
 * File name: Lab4.java
 * Author: Inessa Khodak
 * Lab: Lab #4
 * Date: Oct 19, 2022
 * Note: this is outside of the Lab 4 requirement, edited Lab 4 to practice using enums.
 * Reduced functionality - full functionality is in private repository.
 */

import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Random;
//import java.util.Scanner;
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
		final int SUITS = Suits.values().length;
		final int RANKS = Ranks.values().length;
		List<Card_enum> cardDeck = new ArrayList<>(deck);// create array list for the card deck
		

		for (int i = 0; i < SUITS; i++) {// populating new card deck
			for (int j = 0; j < RANKS; j++) {
				cardDeck.add(new Card_enum(Suits.values()[i],Ranks.values()[j]));
			}
		}

		System.out.println("Welcome to Cards Game!");
		
		System.out.println("Thank you for playing!");
		System.out.println("Program by Inessa Khodak");
	}

	
}
