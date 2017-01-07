/*****************************************************************
*							       								 *
*					Deck Class									 *		
*							        						     *
******************************************************************
*Author	      :	Shilpa Shinde					                 *
*																 *
*Program Name : BlackjackGame Class					             *							        
*							       					  		  	 *
*Date	      :	11/02/2013								 		 *	
*															     *
******************************************************************/



public class Deck {

    private Card[] deck;   
    private int Cardused; 
    
    public Deck() {
           
       deck = new Card[52];
       int cardcount = 0; 
       for ( int suit = 0; suit <= 3; suit++ ) {
          for ( int value = 1; value <= 13; value++ ) {
             deck[cardcount] = new Card(value,suit);
             cardcount++;
          }
       }
       Cardused = 0;
    }
    
    public void shuffle() {
         
        for ( int i = 51; i > 0; i-- ) {
            int rand = (int)(Math.random()*(i+1));
            Card temp = deck[i];
            deck[i] = deck[rand];
            deck[rand] = temp;
        }
        Cardused = 0;
    }
    
    public int cardsLeft() {
         return 52 - Cardused;
    }
    
    public Card dealCard() {
         if (Cardused == 52)
           shuffle();
        Cardused++;
        return deck[Cardused - 1];
    }

} 

