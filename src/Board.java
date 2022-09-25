/**
 * Author: Aman Dwivedi
 * File: Board.java
 * Assignment: Programming Assignment 1 - Concentration Game
 * Course: CSc 335; Fall 2022
 * Description: This is the Board class. It is the main class which links the Players
 * 				and Card class. 
 */
import java.util.ArrayList;
import java.util.Random;

public class Board {
	private static Card[][] board;
	private Players playerList;
	
	/**
	 * Constructor. Creates a 6X5 2D array of Card objects.
	 */
	public Board() {
		board = new Card[6][5];
	}	
	
	/**
	 * Creates a playerList, that is, an array of all the players with their
	 * respective scores.
	 * @param numOfPlayers - Integer indicating the number of players playing.
	 */
	public void createPlayerList(int numOfPlayers) {
		playerList = new Players(numOfPlayers);
	}
	
	/**
	 * Returns integer telling whose turn it is.
	 */
	public int getNextPlayer() {
		return playerList.getNextPlayer();
	}
	
	/**
	 * Updates the score of the current player.
	 */
	public void updateScore() {
		playerList.updateScore();
	}
	
	/**
	 * Returns a string indicating which player has won the game.
	 */
	public String getWinner() {
		return playerList.getWinner();
	}
	
	/**
	 * Returns a string containing the score board. 
	 */
	public String getScoreBoard() {
		return playerList.getScoreBoard();
	}
	
	/**
	 * This method is used to add cards randomly to the 6X5 grid.
	 * @param cardType - String indicating whether the cards should be of fruits or animals.
	 */
	public void makeGrid(String cardType) {
		ArrayList<Integer> possiblePositions = new ArrayList<Integer>();
		for(int i = 0; i < 30; i++) {
			possiblePositions.add(i);
		}
		String[] cards;
		if (cardType == "fruits") {
			cards = new String[] {"apple", "avocado", "greenapple", "peach", "pear", "pineapple"};
		}
		else {
			cards = new String[] {"dog", "chicken", "cow", "duck"};
		}
		
		while(possiblePositions.size() != 0) {
			String cardDesign = cards[new Random().nextInt(cards.length)];
			int rand1 = new Random().nextInt(possiblePositions.size());
			rand1 = possiblePositions.remove(rand1);
			int rand2 = new Random().nextInt(possiblePositions.size());
			rand2 = possiblePositions.remove(rand2);
			board[rand1%6][(int)(rand1/6)] = new Card(cardDesign);
			board[rand2%6][(int)(rand2/6)] = new Card(cardDesign);
		}
	}
	
	/**
	 * Getter for the card  objects inside the board.
	 * @param col - Column of the card.
	 * @param row - Row of the card.
	 * @return Card object at the specified location.
	 */
	public Card get(Integer col, Integer row) {
		return board[col][row];
	}
}