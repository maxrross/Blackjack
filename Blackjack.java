import java.util.Scanner;

public class Blackjack {
    //init global variables
    public static int game = 1;
    public static int pHand = 0;
    public static int dHand = 0;
    public static String cardName;
    public static int cardValue;
    public static int pWin;
    public static int dWin;
    public static int tie;
    public static double percentWon;
    public static P1Random rng = new P1Random();
    //starts new game by resetting score, calculating new random int, getting card name, and adding to pHand
    public static void startNew (){
        game++;
        pHand = 0;
        dHand = 0;
        cardValue = rng.nextInt(13) + 1;
        System.out.println("START GAME #"+game+"\n");
        cardName = cardName(cardValue);
        System.out.println("Your card is a "+cardName+"!");
        if (cardValue >10) {
            cardValue = 10;
        }
        pHand+=cardValue;
        checkWin(pHand);
    }
    //checks if the user has won, prints it, and starts new game if user has won
    public static void checkWin(int s){
        System.out.println("Your hand is: "+s);
        if (s>=21){
            if (s==21) {
                pWin++;
                System.out.println("BLACKJACK! You win!\n");
                startNew();
            } else {
                dWin++;
                System.out.println("You exceeded 21! You lose.\n");
                startNew();
            }
        }
    }
    //get card name method
    public static String cardName(int a) {
        String name;
        if (a == 1) {
            name = "ACE";
        } else if (a == 11) {
            name = "JACK";
        } else if (a == 12) {
            name = "QUEEN";
        } else if (a == 13) {
            name = "KING";
        } else {
            name = String.valueOf(a);
        }
        return name;
    }
        //main program
        public static void main(String[] args) {
            Scanner scan = new Scanner(System.in);
            cardValue = rng.nextInt(13) + 1;
            cardName = cardName(cardValue);
            System.out.println("START GAME #"+game+"\n");
            System.out.println("Your card is a "+cardName+"!");
            if (cardValue >10) {
                cardValue = 10;
            }
            pHand+=cardValue;
            checkWin(pHand);
            /* while loop until player has broken 21 (lost) or won the game, it keeps presenting user with the
            menu and input until a result has been decided */
            while (pHand<21){
                System.out.println("1. Get another card\n2. Hold hand\n3. Print statistics\n4. Exit\n");
                System.out.print("Choose an option: ");
                int result = scan.nextInt();
                System.out.print("\n");

                if (result == 1) {
                    //deals another card to the user and checks if they have won
                    cardValue = rng.nextInt(13) + 1;
                    cardName = cardName(cardValue);
                    System.out.println("Your card is a "+cardName+"!");
                    if (cardValue >10) {
                        cardValue = 10;
                    }
                    pHand+=cardValue;
                    checkWin(pHand);
                } else if (result == 2) {
                    //hold hand cases- calculates dHand and win cases to decide if user or dealer has won
                    dHand = rng.nextInt(11) + 16;
                    if (dHand>21) {
                        System.out.println("Dealer's hand: "+dHand);
                        System.out.println("Your hand is: "+pHand+"\n");
                        System.out.println("You win!\n");
                        pWin++;
                        startNew();
                    } else if (dHand == pHand) {
                        System.out.println("Dealer's hand: "+dHand);
                        System.out.println("Your hand is: "+pHand+"\n");
                        System.out.println("It's a tie! No one wins!\n");
                        tie++;
                        startNew();
                    } else if (pHand > dHand) {
                        System.out.println("Dealer's hand: "+dHand);
                        System.out.println("Your hand is: "+pHand+"\n");
                        System.out.println("You win!\n");
                        pWin++;
                        startNew();
                    } else {
                        System.out.println("Dealer's hand: "+dHand);
                        System.out.println("Your hand is: "+pHand+"\n");
                        System.out.println("Dealer wins!\n");
                        dWin++;
                        startNew();
                    }
                } else if (result == 3) {
                    //outputting statistics through print statements
                    System.out.println("Number of Player wins: "+pWin);
                    System.out.println("Number of Dealer wins: "+dWin);
                    System.out.println("Number of tie games: "+tie);
                    System.out.println("Total # of games played is: "+(pWin+dWin+tie));
                    //calculating percent of games won as a double
                    percentWon= ((double) pWin /(pWin+dWin+tie))*100;
                    System.out.println("Percentage of Player wins: "+percentWon+"%\n");
                } else if (result == 4) {
                    //exits game if result is 4
                    System.exit(0);
                } else {
                    //if input isn't between 1-4, returns this error
                    System.out.println("Invalid input!\nPlease enter an integer value between 1 and 4.\n");
                }
            }
        }
}
