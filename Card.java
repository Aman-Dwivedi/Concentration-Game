/**
 * Author: Aman Dwivedi
 * File: Card.java
 * Assignment: Programming Assignment 1 - Concentration Game
 * Course: CSc 335; Fall 2022
 * Description: This is the Card class. It keeps track of the individual cards with
 * 				their respective images. It stores a string named 'cardDesign' 
 * 				indicating which card it is. It has various methods to keep track of
 * 				the cards: whether they are flipped or not, removed or not.
 */
public class Card {
	private String cardDesign;
	private String img;
	private String tile;
	public boolean flip;
	private boolean removed = false;
	
	/**
	 * Constructor creates the name of the files from the input. card design indicates what thing it contains.
	 * For example, apple, pear etc. Flip false means the card is face down and flip true means the card is face
	 * up.
	 * @param cardDesign - String indicating what type of card it is.
	 */
	public Card(String cardDesign) {
		this.cardDesign = cardDesign;
		img = cardDesign+".jpg";
		tile = "tile.jpg";
		flip = false;
	}
	
	/**
	 * getter for card design
	 * @return - String indicating card design.
	 */
	public String getCardDdesign() {
		return cardDesign;
	}
	
	/**
	 * getter for the image. Checks the flipped condition of the card and then returns 
	 * the appropriate image.
	 * @return - String containing the name of the image.
	 */
	public String getImg() {
		if(flipped())
			return img;
		return tile;
	}
	
	/**
	 * Returns boolean value of whether the card is flipped or not.
	 * @return - Boolean; true = face up, false = face down
	 */
	public boolean flipCard() {
		if(!removed) {
			if(flip)
				flip = false;
			else
				flip = true;
			return true;
		}
		return false;
	}
	
	/**
	 * Returns boolean indicating if the card was removed from the board or not.
	 */
	public boolean removed() {
		return removed;
	}
	
	/**
	 * Method to remove the card from the board. That is, sets the image to blank.
	 */
	public void remove() {
		img = "blank.jpg";
		removed = true;
	}
	
	/**
	 * Returns boolean indicating if the card was flipped or not.
	 */
	private boolean flipped() {
		return flip;
	}	
}