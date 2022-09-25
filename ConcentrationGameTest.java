import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class ConcentrationGameTest {

	// Test to check if all cards are of correct type and also ensures the grid is of size 6X5
	@Test
	void test1() {
		String[] cards = new String[] {"dog", "chicken", "cow", "duck"};
		Board b = new Board();
		b.makeGrid("animals");
		boolean check = true;
		for(int i = 0; i < 6; i++)
			for(int j = 0; j < 5; j++) {
				if(Arrays.asList(cards).contains(b.get(0, 0).getCardDdesign()) == false)
					check = false;
			}
		assertEquals(true, check);
		
		cards = new String[] {"apple", "avocado", "greenapple", "peach", "pear", "pineapple"};
		b = new Board();
		b.makeGrid("fruits");
		check = true;
		for(int i = 0; i < 6; i++)
			for(int j = 0; j < 5; j++) {
				if(Arrays.asList(cards).contains(b.get(0, 0).getCardDdesign()) == false)
					check = false;
			}
		assertEquals(true, check);
	}
	
	// test to check if all the cards are flipping properly and are getting removed
	@Test
	void test2() {
		Board b = new Board();
		b.makeGrid("animals");
		assertEquals("tile.jpg", b.get(0, 0).getImg());
		assertEquals(true, b.get(0, 0).flipCard());
		assertEquals(true, b.get(0, 0).flip);
		b.get(0, 0).remove();
		assertEquals(true, b.get(0, 0).removed());
		assertEquals("blank.jpg", b.get(0, 0).getImg());
	}
	
	// Test to check if the player score is getting updated correctly.
	@Test
	void test3() {
		Board b = new Board();
		b.makeGrid("animals");
		b.createPlayerList(5);
		assertEquals(1, b.getNextPlayer());
		b.updateScore();
		b.updateScore();
		b.updateScore();
		b.updateScore();
		b.updateScore();
		assertEquals("Player 1 won!!", b.getWinner());
		assertEquals(2, b.getNextPlayer());
		b.updateScore();
		b.updateScore();
		b.updateScore();
		b.updateScore();
		assertEquals("Player 1 won!!", b.getWinner());
		assertEquals("Player 1: 5\nPlayer 2: 4\nPlayer 3: 0\nPlayer 4: 0\nPlayer 5: 0\n", b.getScoreBoard());
		assertEquals(3, b.getNextPlayer());
		b.updateScore();
		b.updateScore();
		b.updateScore();
		assertEquals("Player 1 won!!", b.getWinner());
		assertEquals("Player 1: 5\nPlayer 2: 4\nPlayer 3: 3\nPlayer 4: 0\nPlayer 5: 0\n", b.getScoreBoard());
		assertEquals(4, b.getNextPlayer());
		b.updateScore();
		b.updateScore();
		assertEquals("Player 1 won!!", b.getWinner());
		assertEquals("Player 1: 5\nPlayer 2: 4\nPlayer 3: 3\nPlayer 4: 2\nPlayer 5: 0\n", b.getScoreBoard());
		assertEquals(5, b.getNextPlayer());
		b.updateScore();
		assertEquals("Player 1 won!!", b.getWinner());
		assertEquals("Player 1: 5\nPlayer 2: 4\nPlayer 3: 3\nPlayer 4: 2\nPlayer 5: 1\n", b.getScoreBoard());
		b.updateScore();
		b.updateScore();
		b.updateScore();
		b.updateScore();
		b.updateScore();
		assertEquals("Player 5 won!!", b.getWinner());
		assertEquals("Player 1: 5\nPlayer 2: 4\nPlayer 3: 3\nPlayer 4: 2\nPlayer 5: 6\n", b.getScoreBoard());
		assertEquals(1, b.getNextPlayer());
		b.updateScore();
		b.updateScore();
		assertEquals("Player 1 won!!", b.getWinner());
		assertEquals("Player 1: 7\nPlayer 2: 4\nPlayer 3: 3\nPlayer 4: 2\nPlayer 5: 6\n", b.getScoreBoard());
	}
}
