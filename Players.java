/**
 * Author: Aman Dwivedi
 * File: Players.java
 * Assignment: Programming Assignment 1 - Concentration Game
 * Course: CSc 335; Fall 2022
 * Description: This is the Players class. It uses an array of integers to keep track 
 * 				of all the players that are playing and their respective score.
 */
public class Players {
	private int[] score;
	private int playerTurn;
	
	/**
	 * Constructor creates an array of length equal to the number of players. The array holds the 
	 * score for each player.
	 * @param num - Number of players playing the game.
	 */
	public Players(int num) {
		score = new int[num];
		for(int i = 0; i < num; i++) {
			score[i] = 0;
		}
		playerTurn = 0;
	}
	
	/**
	 * This method is used to check whose turn it is.
	 * @return - Integer indicating player number whose turn it is.
	 */
	public int getNextPlayer() {
		playerTurn++;
		if(playerTurn > score.length)
			playerTurn = 1;
		return playerTurn;
	}
	
	/**
	 * Updates score of the current player playing the game. Finding one pair of cards gives you one point.
	 */
	public void updateScore() {
		this.score[playerTurn-1] += 1;
	}
	
	/**
	 * This method is used to check who won the game.
	 * @return - Returns a string containing the player who won.
	 */
	public String getWinner() {
		int winner = 0;
		int highScore = 0;
		for(int i = 0; i < this.score.length; i++) {
			if(this.score[i] > highScore) {
				winner = i + 1;
				highScore = score[i];
			}
		}
		return "Player " + String.valueOf(winner) + " won!!";
	}
	
	/**
	 * This method is used to get a scoreBoard containing all the scores of all the players.
	 * @return - Returns a string containing the Score Board.
	 */
	public String getScoreBoard() {
		String scoreBoard = "";
		for(int i = 0; i < this.score.length; i++) {
			scoreBoard += "Player " + String.valueOf(i+1) + ": " + String.valueOf(score[i]) + "\n";
		}
		return scoreBoard;
	}
}