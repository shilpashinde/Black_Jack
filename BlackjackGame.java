/*****************************************************************
*							       								 *
*					Main Driver Class							 *		
*							        						     *
******************************************************************
*Author	      :	Shilpa Shinde					                 *
*																 *
*Program Name : BlackjackGame Class					             *							        
*							       					  		  	 *
*Date	      :	11/02/2013								 		 *	
*															     *
******************************************************************/

import java.util.Scanner;	//to get user input (choice)

//File handling imports
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
 

public class BlackjackGame 
{

	Deck deck;         
	BlackjackHand dealerHand;   
	BlackjackHand playerHand;
	boolean gameStartedStatus;
	char [][] hintTable;   

	public BlackjackGame()
	{
		gameStartedStatus = false;
		readHintFile();
	}

	public void setGameStartedStatus( boolean status )
	{
		gameStartedStatus = status;	
	}

	public boolean isGameStarted()
	{
		return gameStartedStatus;
	}

	public void processGameFinish()
	{
		printBlackjackValue();	//print result values before clearing

		dealerHand.clear();
		playerHand.clear();
		setGameStartedStatus(false);
	}

	public void printMenu() 
	{
		System.out.println("******************");
		System.out.println("1) New Hand ");
		System.out.println("2) Hit Me ");
		System.out.println("3) Stand ");
		System.out.println("4) Hint ");
		System.out.println("5) Exit ");
		System.out.println("******************\n");
	}

	public void printBlackjackValue()
	{
		if ((dealerHand.getCardCount() > 0) && (playerHand.getCardCount() > 0)) {
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			System.out.print("Dealer Value: " + dealerHand.getBlackjackValue() + ", card count: " + dealerHand.getCardCount() + " ==> " );
			for(int i = 0; i < dealerHand.getCardCount(); i++ ) {
				System.out.print( dealerHand.getCard(i).getValueAsString() + " ");
			}
			System.out.print("\n");

			System.out.print("Player Value: " + playerHand.getBlackjackValue() + ", card count: " + playerHand.getCardCount() + " ==> " );
			for(int i = 0; i < playerHand.getCardCount(); i++ ) {
				System.out.print( playerHand.getCard(i).getValueAsString() + " ");
			}
			System.out.print("\n");
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		}
	}

	public int getUserChoice(Scanner inR )
	{
		int choice = 0;
		System.out.print("Your Choice? (1-5) > ");
		choice = inR.nextInt();
		return choice;
	}

	public void processNewHand()
	{
		deck = new Deck();
		dealerHand = new BlackjackHand();
		playerHand = new BlackjackHand();


		setGameStartedStatus(true);
		deck.shuffle();

		Card dealerCard = deck.dealCard();
		Card playerCard = deck.dealCard();
		dealerHand.ADD( dealerCard );  
		playerHand.ADD( playerCard );

		System.out.println("Dealer picked the " + dealerCard.getValueAsString() + " of " + dealerCard.getSuitAsString() );
		System.out.println("Player picked the " + playerCard.getValueAsString() + " of " + playerCard.getSuitAsString() );
		

		if (dealerHand.getBlackjackValue() == 21) {
			System.out.println("Sorry, you lose.  Dealer has Blackjack.");
			processGameFinish();
		}
		else if (playerHand.getBlackjackValue() == 21) {
			System.out.println("You win!  You have Blackjack.");
			processGameFinish();
		}
	}

	public void processHitMe()
	{
		Card playerCard = deck.dealCard();
		playerHand.ADD( playerCard );

		System.out.println("Player picked the " + playerCard.getValueAsString() + " of " + playerCard.getSuitAsString() );
		
		if ( playerHand.getBlackjackValue() > 21 ) {
			System.out.println("You lose.");
			processGameFinish();
		}
		else if (playerHand.getCardCount() == 5) {
			System.out.println("You win.");
			processGameFinish();
		}

	}

	public void processStand()
	{
		Card dealerCard;
		while (dealerHand.getBlackjackValue() <= 16 && dealerHand.getCardCount() < 5) {
			dealerCard = deck.dealCard();
			dealerHand.ADD( dealerCard );
			System.out.println("Dealer picked the " + dealerCard.getValueAsString() + " of " + dealerCard.getSuitAsString() );
		}
	
		if (dealerHand.getBlackjackValue() > 21) {
			System.out.println("You win! ");
			processGameFinish();
		}
		else if (dealerHand.getCardCount() == 5) {
			System.out.println("You lose. ");
			processGameFinish();
		}
		else if (dealerHand.getBlackjackValue() > playerHand.getBlackjackValue())	{
			System.out.println("You lose. ");
			processGameFinish();
		}
		else if (dealerHand.getBlackjackValue() == playerHand.getBlackjackValue())	{
			System.out.println("It's a tie. ");
			processGameFinish();
		} else {
			System.out.println("You win. ");
			processGameFinish();
		}
	}


	public void printHintTable()
	{
		int row = 0, col = 0;

		for (row = 0 ; row < 11; row++){
			for (col = 0 ; col < 10; col++){
				System.out.print(hintTable[row][col] + " " );
			}
			System.out.print("\n");
		}
	}

	public void readHintFile()
	{
		try {

			Scanner s = new Scanner(new FileInputStream("blackJack-play-suggestion.txt"));
			hintTable = new char[11][10]; 
			int row = 0, col = 0;

			while (s.hasNextLine()) {
				String line = s.nextLine();
				for (col = 0 ; col < line.length(); col++){
					hintTable[row][col] = line.charAt(col);
					//System.out.print(hintTable[row][col] + " " );
				}
				row++;
				//System.out.print("\n");
			}

		} catch (IOException e) {
			System.out.println("Hint file read failed, error message: " + e.getMessage() + ", cause: " + e.getCause());
		}
	}
	
	public int processHint()
	{
		int playerValue = playerHand.getBlackjackValue();
		int dealerValue = dealerHand.getBlackjackValue();
		int suggestion = 2;	//default value Hit-me

		printHintTable();

		if (playerValue <= 9 ) {
			suggestion = 2;
		} else {
			int row = ((playerValue % 10) + 3);
			int col = (dealerValue - 2);
			
			//make sure to access valid chart entries to avoid index-out-of-bound error
			if (((row >= 0) && (row <=10)) && ((col >=0) && (col <=9))) {
				if (( hintTable[row][col] == 'H') || ( hintTable[row][col] == 'D')) {
					suggestion = 2;
				} else {
					suggestion = 3;
				}
			}
		}

		return suggestion;
	}


	public static void main(String arg[])
	{
		int choice = 0;
		Scanner inputReader= new Scanner(System.in);
		BlackjackGame game = new BlackjackGame();	
		while (true) {
				
			game.printMenu();
			choice = game.getUserChoice(inputReader);

			if ((choice < 1 ) || (choice > 5)) {
				System.out.println("Invalid Choice!!! Please enter correct choice (1-5) ");
				choice = 0;
			} else {

				switch (choice) {
					case 1:
					{
						if (game.isGameStarted()) {
							System.out.println("Please FINISH previous game before starting new one!!!!");
						} else {
							game.processNewHand();
						}
						game.printBlackjackValue();
					}
					break;
					case 2:
					{
						if (game.isGameStarted()) {
							game.processHitMe();
							game.printBlackjackValue();
						} else {
							System.out.println("Game is not Started....Please start a new game by selecting choice 1 (New Hand) ");
						}
					}
					break;
					case 3:
					{
						if (game.isGameStarted()) {
							game.processStand();
							game.printBlackjackValue();
							
						} else {
							System.out.println("Game is not Started....Please start a new game by selecting choice 1 (New Hand) ");
						}

					}
					break;
					case 4:
					{
						if (game.isGameStarted()) {
							System.out.println("Suggestion: " + game.processHint());
						} else {
							System.out.println("Game is not Started....Please start a new game by selecting choice 1 (New Hand) ");
						}
					}
					break;
					case 5:
					{
						System.out.println("EXIT... Game Aborted.. \n");
					}
					break;
					default:
					{
						System.out.println("Invalid choice \n");
					}

				}

				if (choice == 5) {
					return;
				}
			}
		}
	}
}

