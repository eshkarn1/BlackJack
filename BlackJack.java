package blackjack;

import java.util.*;

public class BlackJack {
    
     public static void main(String[] args) {
       
          int money;          
          int bet;            
          boolean userWins;   
          
          System.out.println("Welcome to the game of blackjack");
          System.out.println("This game is presented by Eshkarn Singh and Sahej Kaur");
          System.out.println();
          
          Scanner sc = new Scanner(System.in);
          money = 100;  
       
          while (true) {
              System.out.println("You have " + money + " dollars.");
              do {
                 System.out.println("How many dollars do you want to bet?");
                 System.out.println("? ");
                 bet = sc.nextInt();
                 if (bet < 0 || bet > money)
                     System.out.println("Your answer must be between 0 and " + money + '.');
              } while (bet < 0 || bet > money);
              if (bet == 0)
                 break;
              userWins = playBlackjack();
              if (userWins)
                 money = money + bet;
              else
                 money = money - bet;
              System.out.println();
              if (money == 0) {
                 System.out.println("Looks like you've are out of money!");
                 break;
              }
          }
          
          System.out.println();
          System.out.println("You leave with $" + money + '.');
       
       }
    static boolean playBlackjack() {
            
    
          Deck deck;                  
          BlackJackHand DHand;   
          BlackJackHand UHand;     
         
          deck = new Deck();
          DHand = new BlackJackHand();
          UHand = new BlackJackHand();
          String str = "C";
          
          Scanner sc = new Scanner(System.in);
      
          
          deck.shuffle();
          DHand.addCard( deck.dealCard() );
          DHand.addCard( deck.dealCard() );
          UHand.addCard( deck.dealCard() );
          UHand.addCard( deck.dealCard() );
          
          System.out.println();
          System.out.println();
          
          
          
          if (DHand.getBlackjackValue() == 21) {
               System.out.println("Dealer has the " + DHand.getCard(0)
                                       + " and the " + DHand.getCard(1) + ".");
               System.out.println("User has the " + UHand.getCard(0)
                                         + " and the " + UHand.getCard(1) + ".");
               System.out.println();
               System.out.println("Dealer has Blackjack.  Dealer wins.");
               return false;
          }
          
          if (UHand.getBlackjackValue() == 21) {
               System.out.println("Dealer has the " + DHand.getCard(0)
                                       + " and the " + DHand.getCard(1) + ".");
               System.out.println("User has the " + UHand.getCard(0)
                                         + " and the " + UHand.getCard(1) + ".");
               System.out.println();
               System.out.println("You have Blackjack.  You win.");
               return true;
          }
          while (true) {
              
               
               System.out.println("Your cards are:");
               for ( int i = 0; i < UHand.getCardCount(); i++ )
                  System.out.println("    " + UHand.getCard(i));
               System.out.println("Your total is " + UHand.getBlackjackValue());
               System.out.println();
               System.out.println("Dealer is showing the " + DHand.getCard(0));
               System.out.println();
               System.out.println("Hit (H) or Stand (S)? ");
               String userAction = sc.nextLine();  // User's response, 'H' or 'S'.
               do {
                  System.out.println(userAction.toUpperCase());
                  if (!userAction.equals("H") && !userAction.equals("S"))
                       System.out.println("Please respond H or S:  ");
               } while (userAction.equals("H") && userAction.equals("S"));
    
         
    
               if (userAction.equals("S")) {
                      break;
               }
               else {  
                   Card newCard = deck.dealCard();
                   UHand.addCard(newCard);
                     System.out.println();
                     System.out.println("User hits.");
                     System.out.println("Your card is the " + newCard);
                     System.out.println("Your total is now " + UHand.getBlackjackValue());
                   if (UHand.getBlackjackValue() > 21) {
                         System.out.println();
                         System.out.println("You busted by going over 21.  You lose.");
                         System.out.println("Dealer's other card was the " 
                                                          + DHand.getCard(1));
                       return false;  
                   }
               }
               
          } 
           System.out.println();
           System.out.println("User stands.");
            System.out.println("Dealer's cards are");
            System.out.println("    " + DHand.getCard(0));
            System.out.println("    " + DHand.getCard(1));
          while (DHand.getBlackjackValue() <= 16) {
             Card newCard = deck.dealCard();
              System.out.println("Dealer hits and gets the " + newCard);
             DHand.addCard(newCard);
             if (DHand.getBlackjackValue() > 21) {
                  System.out.println();
                  System.out.println("Dealer busted by going over 21.  You win.");
                return true;
             }
          }
            System.out.println("Dealer's total is " + DHand.getBlackjackValue());
          
            System.out.println();
          if (DHand.getBlackjackValue() == UHand.getBlackjackValue()) {
               System.out.println("Dealer wins on a tie.  You lose.");
             return false;
          }
          else if (DHand.getBlackjackValue() > UHand.getBlackjackValue()) {
               System.out.println("Dealer wins, " + DHand.getBlackjackValue() 
                              + " points to " + UHand.getBlackjackValue() + ".");
             return false;
          }
          else {
               System.out.println("You win, " + UHand.getBlackjackValue() 
                              + " points to " + DHand.getBlackjackValue() + ".");
             return true;
          }
    
       }  
    
    
    }
