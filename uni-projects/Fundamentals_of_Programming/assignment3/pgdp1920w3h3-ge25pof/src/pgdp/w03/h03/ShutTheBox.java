/**
 * @author ge25pof TaoXiang
 * !!!  This code is written alone by ge25pof TaoXiang and recorded, if any illegal plagirasim happens, I will fight for my unguilty
 * !!!  through legal approaches. (e.g. with the recorded videos)
 * !!!  And my codes has been written in a special way to prevent some extreme cases of similiarity, i.e. to avoid extreme coincidence.
 * !!!  Some examples are like:
 * !!!  for(int i_ge25pof = 0;i_ge25pof < 10 ; i_ge25pof++) 
 * !!!
 * !!!  Since last year I gave one of my friend my codes for testing which lead to plagiarism( he forgot to delete it...),
 * !!!  so I can't take any risk this time,seriously speaking!
 * !!!  Sorry if some variable names are too long to read, I would try to write clear documentations to help the tutors to read it.
 * !!!  Thank you for your understanding, really!
 */
package pgdp.w03.h03;

public class ShutTheBox extends MiniJava {
  public static void main(String[] args) throws IllegalAccessException {
    boolean[] boxes = {true, true, true, true, true, true, true, true};

    //* player points as global vars
    int player1_points_ge25pof_taoxiang = 0;
    int player2_points_ge25pof_taoxiang = 0;

    //* global flag for winner
    // 404 for undefined
    // 0 for Unentschied
    // 1 for player1
    // 2 for player2
    int winner_ge25pof_taoxiang = 404;

    //* the game has totally 8 rounds
    for (int i_ge25pof_taoxiang = 0; i_ge25pof_taoxiang < 8; i_ge25pof_taoxiang++) {

      //* for each round:
      // for player1
      {
            // 1.0 player1 dices
            int dice1_ge25pof_tx,dice2_ge25pof_tx;
            dice1_ge25pof_tx = dice();
            dice2_ge25pof_tx = dice();

            // loop until player1 gives a valid combination of 2 boxes
            boolean valid_ge25pof_tx = false;
            while(!valid_ge25pof_tx){
            System.out.println("Spieler 1 hat die folgenden Zahlen gewürfelt:");
            System.out.println(dice1_ge25pof_tx); // print dicing results( * 2)
            System.out.println(dice2_ge25pof_tx);

            // 1.1 player1 gives combinations
            outputBoxes(boxes); // print out all the boxes with their status
            System.out.println("Welche Boxen möchte Spieler 1 schliessen? (0 für keine valide Kombination)");
            int box1_ge25pof_tx = readInt("Box 1:");
            int box2_ge25pof_tx = readInt("Box 2:");
            
            //? negative input? => invalid! jump to next loop
            if(box1_ge25pof_tx<0 || box2_ge25pof_tx <0 || box1_ge25pof_tx>8 || box2_ge25pof_tx>8){
              continue;
            }

            //! 1.2 check if valid combinations
            if((box1_ge25pof_tx==0 || box2_ge25pof_tx == 0)||((box1_ge25pof_tx!=box2_ge25pof_tx)&& boxes[box1_ge25pof_tx-1] && boxes[box2_ge25pof_tx-1] && ((box1_ge25pof_tx+box2_ge25pof_tx)==(dice1_ge25pof_tx+dice2_ge25pof_tx)) )){valid_ge25pof_tx = true;}
           

            //! 1.3 check if 0 is input: just credit points, not closing boxes
            if(box1_ge25pof_tx==0 || box2_ge25pof_tx==0){
              // then credit the points and break the loop

              // sum all open boxes
              int points_ge25pof_tx = 0;
              for (int j_ge25pof_tx = 0; j_ge25pof_tx < 8; j_ge25pof_tx++) {
                if(boxes[j_ge25pof_tx]) points_ge25pof_tx+=(j_ge25pof_tx+1);
              }

              // credit the points
              player1_points_ge25pof_taoxiang += points_ge25pof_tx;

              // break the loop => player2's turn
              break;
            }
            if(valid_ge25pof_tx)
              {
                //! 1.4 if program reaches here, meaning input is valid and not 0, we need to close the boxes 
                boxes[box1_ge25pof_tx-1] =boxes[box2_ge25pof_tx-1] =false;

                //! 1.5 if the player shut the final boxes, he wins directly
                boolean all_shut = !(boxes[0] || boxes[1] || boxes[2] || boxes[3] || boxes[4] || boxes[5] || boxes[6] || boxes[7]);
                if(all_shut){
                  winner_ge25pof_taoxiang = 1;
                }
                break;

              }
            
          }
      }

      //! if player1 has already won...
      if(winner_ge25pof_taoxiang == 1){
        break;
      }

      // for player2
      {
            // 2.0 player2 dices
            int dice1_ge25pof_tx,dice2_ge25pof_tx;
            dice1_ge25pof_tx = dice();
            dice2_ge25pof_tx = dice();

            // loop until player2 gives a valid combination of 2 boxes
            boolean valid_ge25pof_tx = false;
            while(!valid_ge25pof_tx){
            System.out.println("Spieler 2 hat die folgenden Zahlen gewürfelt:");
            System.out.println(dice1_ge25pof_tx); // print dicing results( * 2)
            System.out.println(dice2_ge25pof_tx);

            // 2.1 player1 gives combinations
            outputBoxes(boxes); // print out all the boxes with their status
            System.out.println("Welche Boxen möchte Spieler 2 schliessen? (0 für keine valide Kombination)");
            int box1_ge25pof_tx = readInt("Box 1:");
            int box2_ge25pof_tx = readInt("Box 2:");
            
            //? negative input? => invalid! jump to next loop
            if(box1_ge25pof_tx<0 || box2_ge25pof_tx <0 || box1_ge25pof_tx>8 || box2_ge25pof_tx>8){
              continue;
            }

            //! 2.2 check if valid combinations
            if((box1_ge25pof_tx==0 || box2_ge25pof_tx == 0)||((box1_ge25pof_tx!=box2_ge25pof_tx) && boxes[box1_ge25pof_tx-1] && boxes[box2_ge25pof_tx-1] && ((box1_ge25pof_tx+box2_ge25pof_tx)==(dice1_ge25pof_tx+dice2_ge25pof_tx)) )){valid_ge25pof_tx = true;}

            //! 2.3 check if 0 is input
            if(box1_ge25pof_tx==0 || box2_ge25pof_tx==0){
              // then credit the points and break the loop

              // sum all open boxes
              int points_ge25pof_tx = 0;
              for (int j_ge25pof_tx = 0; j_ge25pof_tx < 8; j_ge25pof_tx++) {
                if(boxes[j_ge25pof_tx]) points_ge25pof_tx+=(j_ge25pof_tx+1);
              }

              // credit the points
              player2_points_ge25pof_taoxiang += points_ge25pof_tx;

              // break the loop => next round
              break;
            }

            if(valid_ge25pof_tx)
              {
                //! 2.4 if program reaches here, meaning input is valid and not 0, we need to close the boxes and break 
                boxes[box1_ge25pof_tx-1] =boxes[box2_ge25pof_tx-1] =false;

                //! 2.5 if the player shut the final boxes, he wins directly
                boolean all_shut_ge25pof_tx = !(boxes[0] || boxes[1] || boxes[2] || boxes[3] || boxes[4] || boxes[5] || boxes[6] || boxes[7]);
                if(all_shut_ge25pof_tx){
                  winner_ge25pof_taoxiang = 2;
                }
                break;

              }
            
          }
      }


      
      //* at the end of each round, check if the winner is already decided
      if(winner_ge25pof_taoxiang != 404){
        break;
      }


    }
    //* game over, check the winner
    switch (winner_ge25pof_taoxiang) {

      // 1. not decided yet, count points
      case 404:
        {
          System.out.println("Spieler 1 Punktzahl");
          System.out.println(player1_points_ge25pof_taoxiang);
          System.out.println("Spieler 2 Punktzahl");
          System.out.println(player2_points_ge25pof_taoxiang);

          // player with less points wins
          if(player1_points_ge25pof_taoxiang<player2_points_ge25pof_taoxiang) System.out.println("Spieler 1 gewinnt!");
          else if (player1_points_ge25pof_taoxiang>player2_points_ge25pof_taoxiang) System.out.println("Spieler 2 gewinnt!");
          else System.out.println("Unentschieden!");
          break;
        }

      // player1 wins
      case 1:{
        System.out.println("Spieler 1 schliesst alle Boxen! Spieler 1 gewinnt!");
        break;
      }
      
      // player2 wins
      case 2:{
        System.out.println("Spieler 2 schliesst alle Boxen! Spieler 2 gewinnt!");
        break;
      }

      // undecided
      case 0:{
        System.out.println("Unentschieden!");
        break;
      }
        
    }
  
    
  }

  /**
   * Do not modify this !
   * @param boxes
   */
  private static void outputBoxes(boolean[] boxes) {
    StringBuilder sb = new StringBuilder("Geöffnete Boxen: 1:");
    sb.append(boxes[0]);
    for (int i = 1; i < boxes.length; i++) {
      sb.append(" ").append(i + 1).append(":").append(boxes[i]);
    }
    write(sb.toString());
  }
}
