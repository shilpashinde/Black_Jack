/*****************************************************************
*							       								 *
*					Card Class									 *		
*							        						     *
******************************************************************
*Author	      :	Shilpa Shinde					                 *
*																 *
*Program Name :Card  Class							             *							        
*							       					  		  	 *
*Date	      :	11/02/2013								 		 *	
*															     *
******************************************************************/


public class Card {

    public final static int SPADES = 0,       
                            HEARTS = 1,
                            DIAMONDS = 2,
                            CLUBS = 3;
                            
    public final static int ACE = 1,         
                            JACK = 11,        
                            QUEEN = 12,      
                            KING = 13;
                            
    private final int suit;   
                             
                              
    private final int value;  
                             
    public Card(int theValue, int theSuit) {
            
        value = theValue;
        suit = theSuit;
    }
        
    public int getSuit() {
            
        return suit;
    }
    
    public int getValue() {
            
        return value;
    }
    
    public String getSuitAsString() {
           
        switch ( suit ) {
           case SPADES:   return "Spades";
           case HEARTS:   return "Hearts";
           case DIAMONDS: return "Diamonds";
           case CLUBS:    return "Clubs";
           default:       return " ";
        }
    }
    
    public String getValueAsString() {
            
        switch ( value ) {
           case 1:   return "_ACE_";
           case 2:   return "_2_";
           case 3:   return "_3_";
           case 4:   return "_4_";
           case 5:   return "_5_";
           case 6:   return "_6_";
           case 7:   return "_7_";
           case 8:   return "_8_";
           case 9:   return "_9_";
           case 10:  return "_10_";
           case 11:  return "_JACK_";
           case 12:  return "_QUEEN_";
           case 13:  return "_KING_";
           default:  return "_ _";
        }
    }
    
    public String toString() {
           
        return getValueAsString() + " of " + getSuitAsString();
    }


} 

