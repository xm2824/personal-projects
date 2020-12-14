/**
 * @author ge25pof TaoXiang
 * !!!  This code is written alone by ge25pof TaoXiang and recorded, if any illegal plagirasim happens, I will fight for my unguilty
 * !!!  through legal approaches. (e.g. with the recorded videos)
 * !!!  I've tried to write my codes in an unque way to prevent similary codes, i.e. to avoid extreme coincidence.
 * !!!  E.g. 
 * !!!  1) writted javadoc for each funcion...
 * !!!  2) clear documentations or idea of the codes
 * !!!  3) some instructions are in the same line...
 * !!!
 * !!!  Since last year I gave one of my friend my codes for testing which lead to plagiarism( he forgot to delete it...),
 * !!!  so I can't take any risk this time,seriously speaking!
 * !!!  Thank you for your understanding, really!
 */


package pgdp.games;

public class PickSix extends MiniJava {
  // sorting method from the lecture
  public static int[] sort(int[] a) {
    int[] b = new int[a.length];
    for (int i = 0; i < a.length; ++i) {
      // begin of insert
      int j = 0;
      while (j < i && a[i] > b[j]) ++j;
      // end of locate
      for (int k = i - 1; k >= j; --k) b[k + 1] = b[k];
      // end of shift
      b[j] = a[i];
      // end of insert
    }
    return b;
  } // end of sort



  //! rules for placing cards onto stack:
  //! 1. card should be greater than the top element of the stack, since the stack is monotonically decreasing from top to bottom
  //! 2. if there are multiple possible stacks for 1), then the card should be pushed onto the stack, whose top elem is closest to the card
  //! if the to be pushed-onto stack is full, then all elem of this stack are lost (added to lost cards of the player), and the new card is then pushed to the stack
  //! if no stack is possible to be pushed, then all elem of the first stack are lost, and the new card is pushed onto the first stack
  public static int push_card(int card1, int stacks[][],int lostCards_p1[], int ptr_lC_p1) {
            
            // we need a pointer to point the current possible stack
            int pointer = 404;  // 404 means no possible stack
            // and a tmp var to store the current closest difference between card and the top
            int diff = 999999;


            for (int j = 0; j < stacks.length; j++) {
              int top = stacks[j][0];

              // if card is smaller equal the top, continue
              if(card1 <= top) continue;

              // else compare the current diff
              else {
                int tmp = card1 - top;
                // if current diff is smaller: continue
                if(tmp >= diff) continue;

                // else store the diff and stack
                else {
                  pointer = j;
                  diff = tmp;
                }

              }
            }

            //! if no possible stack
            if(pointer == 404){
              // clear stack1
              for (int j = 0; j < 5; j++) {
                if(stacks[0][j] ==0) continue;

                lostCards_p1[ptr_lC_p1++] = stacks[0][j];
                stacks[0][j]=0;
              }

              // push card to stack1
              stacks[0][0] = card1;
            }

            //! else if the possible stack is full
            else if(stacks[pointer][4] != 0){
              // clear this stack
              for (int j = 0; j < 5; j++) {
                if(stacks[pointer][j]==0) continue;

                lostCards_p1[ptr_lC_p1++] = stacks[pointer][j];
                stacks[pointer][j] = 0;
              }

              // push card to this stack
              stacks[pointer][0] = card1;
            }

            //! else do the normal push
            else {
              // first shift elems in this stack one step further
              for (int j = 4; j >0; j--) {
                stacks[pointer][j] = stacks[pointer][j-1];
              }

              // push 
              stacks[pointer][0] = card1;
            }

            return ptr_lC_p1;
  }

  public static void main(String[] args) throws IllegalAccessException {
    
    //* 0. prepare for the game

    // 0.1 2D array for all the cards
    int player_cards[][] = new int[2][10];
    givePlayerCards(player_cards);

    // 0.2 stacks
    int stacks[][] = new int[4][5];
    for (int i = 0; i < 4; i++) {
      stacks[i][0] = drawCard();
    }

    // 0.3 player points
    int player1_points;
    int player2_points;

    // 0.4 lost cards
    // most cards to lost: 24 = 4 + 10 +10 
    // ptr_lc_px: pointer to the first free slot in the lostCards array
    int lostCards_p1[] = new int[24]; int ptr_lC_p1 = 0;
    int lostCards_p2[] = new int[24]; int ptr_lC_p2 = 0;


    //* the game has totally 10 rounds
    for (int i = 0; i < 10; i++) {
      //* for each round
      {
        //* 0. output the stacks
        outputStapel(stacks);

        //* 1. players select cards
        // player1
        int card1= playerSelectCard(1, player_cards);

        // player2
        int card2 = playerSelectCard(2, player_cards);

        //* 2. place the cards in stack
        //* 2.1 cards should be placed in stack in an ascending ordering
        
          // if card1 <= card2
          if(card1 <= card2){
            // then card1 first
           ptr_lC_p1 = push_card(card1, stacks, lostCards_p1, ptr_lC_p1);
           ptr_lC_p2 =  push_card(card2, stacks, lostCards_p2, ptr_lC_p2);
          }

          // else card 2 first
          else {
            ptr_lC_p2 = push_card(card2, stacks, lostCards_p2, ptr_lC_p2);
            ptr_lC_p1 =  push_card(card1, stacks, lostCards_p1, ptr_lC_p1);
          }



      }
    }

    //* at the end of the game, calculate the points and print the result
    player1_points = calculatePoints(lostCards_p1);
    player2_points = calculatePoints(lostCards_p2);

    int playerPoints[] = new int[]{player1_points,player2_points};
    outputResult(playerPoints);
  

  }


  /**
   * print the given 2D array to stdout, using the specified format on artemis<p>
   * <i>the first dimension is the stacks, the second dimension is the cards of this stack, for each stack the printing process will
   * terminate immediately after encounting an not positive number and jump to next stack
   * 
   * @param stapel
   */
  public static void outputStapel(int[][] stapel) {
    //* for each stack
    for (int stack = 0; stack < stapel.length; stack++) {
      System.out.print("Stapel "+(stack+1)+":");
      
      //* for each card of this stack
      for (int card_index = 0; card_index < stapel[stack].length; card_index++) {
        int card = stapel[stack][card_index];

        //* if card <= 0, jump to next stack
        if(card <= 0) {
          break;
        }

        //* otherwise print it to stdout
        System.out.print(" "+card);
      }

      //* break line for next stack
      System.out.println(); 
    }
    
  }
  

  /**
   * request the player the card from his cards
   * @param player starts with 1 (first player)
   * @param playerCards
   * @return the selected card
   */
  public static int playerSelectCard(int player, int[][] playerCards) {
    //* loop until the player gives a valid card
    //* A valid card means it is in the player's cards
    
    while(true){
      // 1. request the player
      System.out.print("Spieler "+player+", du hast die folgenden Karten:");
      for(int card: playerCards[player-1]){
        // skip 0
        if(card == 0) continue;

        // else print
        System.out.print(" "+card);
      }
      System.out.println("\nWelche Karte möchtest du ausspielen?");

      // 2. get the user input
      int input = readInt("");

      // 3. find first card with this value and set it to 0 if found
      //    else continue the loop

      boolean found = false;
      for (int i = 0; i < playerCards[player-1].length; i++) {
        if(input == playerCards[player-1][i]) {
          found = true;
          playerCards[player-1][i] = 0;
          break;
        }
      }
      // 4. if found: break the loop
      if(found) return input;

    }
  }

  
  /**
   * sum up all the points of the cards using getValueOfCard
   * @param lostCards array of cards, each card is an integer between 1 and 105, both inclusive
   * @return sum of all the points of the cards
   */
  public static int calculatePoints(int[] lostCards) {
    int sum = 0;
    for (int i : lostCards) {
      sum += getValueOfCard(i);
    }
    return sum;
  }


  /**
   * print the result to stdout
   * <p><i>
   * Given an array of size 2, containing respectively the points of player1 and player2.
   * Player with less points win
   * @param playerPoints
   */
  public static void outputResult(int[] playerPoints) {
    // points of p1 and p2
    int points_p1, points_p2;
    points_p1 = playerPoints[0];
    points_p2 = playerPoints[1];

    // player with less points win
    if(points_p1<points_p2) {
      System.out.println("Spieler 1 gewinnt mit "+points_p1+" gegen Spieler 2 mit "+points_p2+" Punkten.");
    }
    else if(points_p1 > points_p2){
      System.out.println("Spieler 2 gewinnt mit "+points_p2+" gegen Spieler 1 mit "+points_p1+" Punkten.");
    }
    else System.out.println("Unentschieden! Beide Spieler haben "+points_p1+" Punkte.");
  }

   /**
   * calculate the points of the given card.
   * <p> 
   * <i> A card is an integer between 1 and 105, both inclusive.
   * Each card has initially 1 point, and has possible extra points.<p>
   * Card ends with <p>
   * 5: one extra point <p>
   * 0: two extra points <p>
   * Scnapp number(at least 2 digits and all the digits are the same): five extra points 
   * @param card
   * @return points if card != 0, else 0
   */
  public static int getValueOfCard(int card) {
    
    //* first check if the given card is 0
    if(card == 0) return 0;

    //* otherwise
    //* 1. initially 1 point
    int points = 1;

    //* 2. ends with 5 or 0?
    int last_digit = card % 10;
    
    // 5 => 1 more point
    if(last_digit == 5){
      points += 1;
    }
    
    // 0 => 2 more points
    else if (last_digit == 0){
      points += 2;
    }

    // else no more points
    else {
      points += 0;
    }

    //* 3. Scnapp number?
      // 3.1 at least 2 digits <=> greater than 9
      // if not, no need to explore further
      if(card <= 9) return points; 

      // 3.2 all the digits are the same?
      boolean same = true;
      int const_digit = card % 10 ;

      // compare all the digits with the constant digit
      int remaining_digits = card;
      int next_digit = remaining_digits %10;
      while (remaining_digits != 0) {
        same &= (const_digit == next_digit);

        // if not same already, return the points
        if(!same) return points;

        // otherwise going further
        remaining_digits /= 10;
        next_digit = remaining_digits % 10;
      }

      // schnapp number => 5 more points
      points += 5;

    //* return the points  
    return points;
  }


  /**
   * fill the given 2D array with drawCard()
   * <p>
   * the first dimension is the players, the second dimension is the cards
   * @param playerCards
   * @throws IllegalAccessException
   */
  public static void givePlayerCards(int[][] playerCards) throws IllegalAccessException {
    //* for each player
    for (int player = 0; player < playerCards.length; player++) {
      //* for each card of this player
      for (int card = 0; card < playerCards[player].length ; card++) {
        playerCards[player][card] = drawCard();
      }
    }
  }
}
