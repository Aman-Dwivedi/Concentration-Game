/**
 * Author: Aman Dwivedi
 * File: UI.java
 * Assignment: Programming Assignment 1 - Concentration Game
 * Course: CSc 335; Fall 2022
 * Description: This is the UI class. It uses SWT to create the user interface 
 * 				of the game. It starts with the home page asking the user for
 * 				the number of players and what type of cards they want to play
 * 				with. Then it moves to the game page. This class controls all
 * 				the front end of the game and calls the Board class for the 
 * 				actual functionality of the game.
 */
import java.util.ArrayList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;

public class UI {
	static Board board;
	
	public static void main(String[] args) {
		board = new Board();
		
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Concentration Game");
		shell.setSize(700,700);
		
		homePage(shell, display);
		
		shell.open(); 
		while( !shell.isDisposed())
		{
			if(!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();	
	}
	
	/**
	 * It creates the home page of the game. The user enters the number of players here and what 
	 * type of cards he wants to play with.
	 * @param shell - window of the gui.
	 * @param display - SWT display. Used for event loop.
	 */
	private static void homePage(Shell shell, Display display) {
		Image background = new Image(display, "back.png");
		shell.setBackgroundImage(background);
		Text text = new Text(shell, SWT.BORDER);
		text.setBounds(415, 408, 122, 21);
		text.setText("1");
		
		Label lblNumOfPlayers = new Label(shell, SWT.TRANSPARENT);
		Font boldFont = new Font(display, new FontData( "Arial", 12, SWT.BOLD ));
		lblNumOfPlayers.setFont(boldFont);
		lblNumOfPlayers.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
		lblNumOfPlayers.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
		lblNumOfPlayers.setBounds(45, 408, 200, 31);
		lblNumOfPlayers.setText("Number of Players");
		
		Label lblChooseTypeOf = new Label(shell, SWT.NONE);
		lblChooseTypeOf.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
		lblChooseTypeOf.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
		lblChooseTypeOf.setFont(boldFont);
		lblChooseTypeOf.setBounds(45, 465, 250, 37);
		lblChooseTypeOf.setText("Choose type of cards");
		
		Button fruits = new Button(shell, SWT.RADIO);
		fruits.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
		fruits.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
		fruits.setFont(boldFont);
		fruits.setBounds(84, 518, 90, 31);
		fruits.setText("Fruits");
		
		Button animals = new Button(shell, SWT.RADIO);
		animals.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
		animals.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
		animals.setFont(boldFont);
		animals.setBounds(259, 518, 90, 31);
		animals.setText("Animal");
		
		Button oneflip = new Button(shell, SWT.CHECK);
		oneflip.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
		oneflip.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
		oneflip.setFont(boldFont);
		oneflip.setBounds(465, 524, 111, 20);
		oneflip.setText("One-Flip");
		
		Button btnStart = new Button(shell, SWT.PUSH);
		btnStart.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
		btnStart.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
		btnStart.setFont(boldFont);
		btnStart.setBounds(284, 592, 75, 37);
		btnStart.setText("Start");
		btnStart.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent event) {
				int numOfPlayers = Integer.parseInt(text.getText());
		        if(fruits.getSelection()) {
		        	if(oneflip.getSelection())
		        		startGame(shell, "fruits", numOfPlayers, display, true);
		        	else
		        		startGame(shell, "fruits", numOfPlayers, display, false);
		        }
		        else {
		        	if(oneflip.getSelection())
		        		startGame(shell, "animals", numOfPlayers, display, true);
		        	else
		        		startGame(shell, "animals", numOfPlayers, display, false);
		        }
		      }

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
			}
		});	
	}
	
	/**
	 * This function is used to play the actual game. It takes care of all the mouse clicks and button on 
	 * the game page. It creates a canvas and draws all the cards onto the canvas. It also adds a quit button
	 * and a label indicating whose turn it is.
	 * @param cardType - String indicating if the user chose animals or fruits.
	 * @param numOfPlayers - Integer indicating the number of players playing the game.
	 * @param shell - window of the gui.
	 * @param display - SWT display. Used for event loop.
	 */
	private static void startGame(Shell shell, String cardType, int numOfPlayers, Display display, boolean oneflip) {
		deleteWidgets(shell);
		board.makeGrid(cardType);
		board.createPlayerList(numOfPlayers);

		Canvas canvas = new Canvas(shell, SWT.NONE);
		canvas.setSize(700,600);
		canvas.setBackground(display.getSystemColor(SWT.COLOR_MAGENTA));

		Font boldFont = new Font(display, new FontData( "Arial", 12, SWT.BOLD ) );
		Label playerNumber = new Label(shell, SWT.NONE);
		playerNumber.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
		playerNumber.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
		playerNumber.setFont(boldFont);
		playerNumber.setBounds(350, 625, 100, 50);
		playerNumber.setText("Player " + board.getNextPlayer() + " turn");
		
		// List to keep track of which two cards are flipped at a time
		ArrayList<Integer> coords = new ArrayList<Integer>();
		canvas.addMouseListener(new MouseListener() {
			
			/**
			 * This method checks which card is flipped and makes sure that the same
			 * card is not flipped twice.
			 */
			public void mouseDown(MouseEvent event) {
				int col = (event.x - 10)/110;
				int row = (event.y - 10)/110;
				if(col < 6 && row < 5) {
					if(board.get(col, row).flipCard()) {
						if(coords.size() == 2) {
							if(!(col == coords.get(0) && row == coords.get(1))) {
								coords.add(col);
								coords.add(row);
								canvas.redraw();
								canvas.update();
							}
						}
						else {
							coords.add(col);
							coords.add(row);
							canvas.redraw();
							canvas.update();
						}
					}
				}
			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			/**
			 * This method does the actual check of whether the two flipped cards were a match or not.
			 * It also checks if the game has ended or not.
			 */
			public void mouseUp(MouseEvent event) {
				if(coords.size() == 4) {
					int row2 = coords.remove(3);
					int col2 = coords.remove(2);
					int row1 = coords.remove(1);
					int col1 = coords.remove(0);
					boolean check = true;
					if(board.get(col1, row1).getCardDdesign() == board.get(col2, row2).getCardDdesign()) {
						board.get(col1, row1).remove();
						board.get(col2, row2).remove();
						board.updateScore();
						check = true;  // Boolean to check if all cards have paired up or not
						for(int i = 0; i < 6; i++)
							for(int j = 0; j < 5; j++)
								if(!board.get(i, j).removed()) {
									check = false;
								}
						if (check) {
							canvas.setVisible(false);
							canvas.setEnabled(false);
							gameOver(board);
						}
						if(oneflip)
							playerNumber.setText("Player " + board.getNextPlayer() + " turn");
					}
					else {
						check = false;
						board.get(col1, row1).flipCard();
						board.get(col2, row2).flipCard();
						playerNumber.setText("Player " + board.getNextPlayer() + " turn");
					}
					if(!check)
						try {
							Thread.sleep(500);  // Puts a pause in the game for the user to see the flipped cards
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					canvas.redraw();
					canvas.update();
				}
			}
			
			/**
			 * This method is called when all the cards have matched up in pairs. It displays the leader board and the 
			 * game over sign.
			 * @param board - Instance of the Board class. Takes care of the back end of the game.
			 */
			private void gameOver(Board board) {
				Canvas canvasNew = new Canvas(shell, SWT.NONE);
				canvasNew.setBounds(0, 0, 700, 600);
				canvasNew.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
				playerNumber.setVisible(false);
				
				Label gameOver = new Label(canvasNew, SWT.NONE);
				gameOver.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
				gameOver.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
				gameOver.setFont(boldFont);
				gameOver.setBounds(300, 200, 100, 50);
				gameOver.setText("Game Over!!!");
				
				Label winner = new Label(canvasNew, SWT.NONE);
				winner.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
				winner.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
				winner.setFont(boldFont);
				winner.setBounds(300, 270, 100, 50);
				winner.setText(board.getWinner());
				
				Label scoreBoard = new Label(canvasNew, SWT.NONE);
				scoreBoard.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
				scoreBoard.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
				scoreBoard.setFont(boldFont);
				scoreBoard.setBounds(300, 400, 100, 200);
				scoreBoard.setText(board.getScoreBoard());
			}
		});
		canvas.addPaintListener(new PaintListener() {	
			
			/**
			 * This method is used to draw out all the cards on the canvas.
			 */
			public void paintControl(PaintEvent event) {
					Image tile = new Image(display, "tile.jpg");
					Image blank = new Image(display, "blank.jpg");
			        for (int i = 0; i < 6; i++)
			        	for(int j = 0; j < 5; j++) {
			        		if(board.get(i, j).getImg() == "tile.jpg")
								event.gc.drawImage(tile, (i*110)+10, (j*110)+10);
			        		else if(board.get(i, j).getImg() == "blank.jpg")
			        			event.gc.drawImage(blank, (i*110)+10, (j*110)+10);
			        		else
			        			event.gc.drawImage(new Image(display, board.get(i, j).getImg()), (i*110)+10, (j*110)+10);
			        	}
			        		
			        }
		    });	

		Button quitButton = new Button(shell, SWT.PUSH);
		quitButton.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
		quitButton.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
		quitButton.setFont(boldFont);;
		quitButton.setText("Quit");
		quitButton.setSize(100, 50);
		quitButton.setBounds(150, 610, 100, 50);
		quitButton.addSelectionListener(new SelectionListener() {
			
			/**
			 * quitButton is used to exit the program.
			 */
			public void widgetSelected(SelectionEvent event) {
		        System.exit(0);
		      }

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
			}
		});
	}
	
	/**
	 * This function is used to remove all the widgets from the home screen.
	 */
	private static void deleteWidgets(Shell shell) {
		for(Control c:shell.getChildren()) {
			c.setEnabled(false);
			c.setVisible(false);
		}
		
	}
}