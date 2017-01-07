/*****************************************************************
*							       								 *
*					Black Jack Hand Class					     *		
*							        						     *
******************************************************************
*Author	      :	Shilpa Shinde					                 *
*																 *
*Program Name : BlackjackHand Class					             *							        
*							       					  		  	 *
*Date	      :	11/02/2013								 		 *	
*															     *
******************************************************************/
import java.util.Vector;
public class BlackjackHand{

	  private Vector<Card> hand;   

   public BlackjackHand() {
      hand = new Vector<Card>();
   }
   
   /*Remove all cards from the hand*/
   public void clear() {
      hand.setSize(0);
   }
   
   /*Add a card to the hand*/
   public void ADD(Card c) {
      if (c == null)
         throw new NullPointerException("Can't add a null card to a hand.");
      hand.addElement(c);
   }


   /*Remove a card from the hand*/
   public void Remove(Card c) {
      hand.remove(c);
   }


   /*Returns the number of cards in the hand*/
   public int getCardCount() {
      return hand.size();
   }
   
   /** Gets the card in a specified position in the hand*/

   public Card getCard(int position) {
      if (position < 0 || position >= hand.size())
         throw new IllegalArgumentException("Position does not exist in hand: "
               + position);
       return (Card)hand.elementAt(position);
   }
 
     public int getBlackjackValue() {
           
         int val;      
         boolean ace;  
                      
         int cards;    

         val = 0;
         ace = false;
         cards = getCardCount();

         for ( int i = 0;  i < cards;  i++ ) {
                 
             Card card;    
             int cardVal;  
             card = getCard(i);
             cardVal = card.getValue(); 
             if (cardVal > 10) {
                 cardVal = 10;  
             }
             if (cardVal == 1) {
                 ace = true;     
             }
             val = val + cardVal;
          }

           if ( ace == true  &&  val + 10 <= 21 )
              val = val + 10;

          return val;

     } 
 
} 


