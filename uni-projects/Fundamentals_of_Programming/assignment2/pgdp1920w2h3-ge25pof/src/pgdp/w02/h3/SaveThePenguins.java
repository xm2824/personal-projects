/**
 * @author ge25pof TaoXiang
 * !!!  This code is written alone by ge25pof TaoXiang and recorded, if any illegal plagirasim happens, I will fight for my unguilty
 * !!!  through legal approaches. (e.g. with the recorded videos)
 * !!!  And my codes has been written in a special way to prevent some extreme cases of similiarity, i.e. to avoid extreme coincidence.
 * !!!  Some examples are like:
 * !!!  for(int i_ge25pof = 0;i_ge25pof < 10 ; i_ge25pof++) 
 */
package pgdp.w02.h3;

public class SaveThePenguins extends MiniJava {
  public static void main(String[] args) {
    // TODO Save them all! => ofc, I love penguin more than you guys!

    //* first request the number of time steps
    int times_ge25pof_taoxiang;
    times_ge25pof_taoxiang = readInt("Bitte Geben Sie die Anzahl an Zeitschritten ein (>= 1):");

    // if times < 1, then exit the program and report error
    if(times_ge25pof_taoxiang < 1){
      System.out.println("Zeitschritte >= 1 erforderlich");
      return;
    }

    //* then request the start population of the 5 age groups
    // for young boys:
    int puppy_num_ge25pof_taoxiang = readInt("Startpopulation Jungtiere");

    // for yound adults:
    int youndAdults_num_ge25pof_taoxiang = readInt("Startpopulation Junge Erwachsene");

    // for adults:
    int adults_num_ge25pof_taoxiang = readInt("Startpopulation Erwachsene");

    // for old adults:
    int oldAdults_num_ge25pof_tx = readInt("Startpopulation Alte Erwachsene");

    // for wise:
    int wise_num_ge25pof_tx = readInt("Startpopulation Weise");

    //* then starting simulation
    // $(times) times
    for (int i_ge25pof_tx = 0; i_ge25pof_tx < times_ge25pof_taoxiang; i_ge25pof_tx++) {

      // 1. collect foods
      int food_num_ge25pof_tx=0;
      food_num_ge25pof_tx += youndAdults_num_ge25pof_taoxiang * 3;  // young adults collect 3 food per time step
      food_num_ge25pof_tx += adults_num_ge25pof_taoxiang * 2;       // adults collect 2 food per time step


      // 2. distribute the food
          /* //?first idea is kinda hard to write....
          // 2.1 food number < puppy number: sad! penguins almost die out!!
          if (food_num< puppy_num){
             puppy_num = food_num;
             youndAdults_num = 0;
             adults_num = 0;
             oldAdults_num = 0;
             wise_num = 0;
          }

          // 2.2 puppy number <= food number <puppy number + young adults number: better! puppy stay!
          else if (puppy_num<= food_num && food_num< (youndAdults_num+puppy_num)){
            puppy_num = puppy_num; food_num -= puppy_num;
            youndAdults_num = food_num;
            adults_num = 0;
            oldAdults_num = 0;
            wise_num = 0;
          }

          // 2.3 puppy number + yound adults number <= food number < puppy number + yound adults number
          */

          //! better approach: Heuristic/decision tree
          // 2.1 food already not enough for puppies 
          if(food_num_ge25pof_tx < puppy_num_ge25pof_taoxiang){
             puppy_num_ge25pof_taoxiang = food_num_ge25pof_tx;
             youndAdults_num_ge25pof_taoxiang = 0;
             adults_num_ge25pof_taoxiang = 0;
             oldAdults_num_ge25pof_tx = 0;
             wise_num_ge25pof_tx = 0;
          }

          // otherwise
          else {
            puppy_num_ge25pof_taoxiang = puppy_num_ge25pof_taoxiang;    // puppies stay
            food_num_ge25pof_tx -= puppy_num_ge25pof_taoxiang;

            // 2.2 remaining food not enough for young adults
            if(food_num_ge25pof_tx < youndAdults_num_ge25pof_taoxiang){
              youndAdults_num_ge25pof_taoxiang = food_num_ge25pof_tx;
              adults_num_ge25pof_taoxiang = 0;
              oldAdults_num_ge25pof_tx = 0;
              wise_num_ge25pof_tx = 0;
            }

            // otherwise
            else {
              youndAdults_num_ge25pof_taoxiang = youndAdults_num_ge25pof_taoxiang;  // young adults stay
              food_num_ge25pof_tx -= youndAdults_num_ge25pof_taoxiang;

              // 2.3 remainding foor not enough for adults
              if(food_num_ge25pof_tx < adults_num_ge25pof_taoxiang){
                adults_num_ge25pof_taoxiang = food_num_ge25pof_tx;
                oldAdults_num_ge25pof_tx = 0;
                wise_num_ge25pof_tx=0;
              }

              // otherwise
              else {
                adults_num_ge25pof_taoxiang = adults_num_ge25pof_taoxiang;  // adults stay
                food_num_ge25pof_tx -= adults_num_ge25pof_taoxiang;

                // 2.4 remaining food not enough for old adults
                if(food_num_ge25pof_tx< oldAdults_num_ge25pof_tx){
                  oldAdults_num_ge25pof_tx = food_num_ge25pof_tx;
                  wise_num_ge25pof_tx = 0;
                }

                // otherwise
                else {
                  oldAdults_num_ge25pof_tx = oldAdults_num_ge25pof_tx;  // old adults stay
                  food_num_ge25pof_tx -= oldAdults_num_ge25pof_tx;

                  // 2.5 last food directly assigned to wise, 
                  wise_num_ge25pof_tx = food_num_ge25pof_tx>=wise_num_ge25pof_tx? wise_num_ge25pof_tx:food_num_ge25pof_tx;

                  // remaining food expire...
                  food_num_ge25pof_tx = 0;
                }
              }
            }

          }

      // 3. mating...
          int newPupies_num_ge25pof_tx=0;
          // 3.1 young adults:
          newPupies_num_ge25pof_tx += youndAdults_num_ge25pof_taoxiang/4;

          //3.2 adults:
          newPupies_num_ge25pof_tx += adults_num_ge25pof_taoxiang/2;

          //3.3 old adults:
          newPupies_num_ge25pof_tx += oldAdults_num_ge25pof_tx/3;
          

      // 4. updrade...
          // 4.1 puppies
          int newYoungAdults_num_ge25pof = puppy_num_ge25pof_taoxiang;
          puppy_num_ge25pof_taoxiang = newPupies_num_ge25pof_tx;

          // 4.2 young adults
          int newAdults_num_ge25pof = youndAdults_num_ge25pof_taoxiang;
          youndAdults_num_ge25pof_taoxiang = newYoungAdults_num_ge25pof;

          // 4.3 adults
          int newOldAdults_num_ge25pof_tx = adults_num_ge25pof_taoxiang;
          adults_num_ge25pof_taoxiang = newAdults_num_ge25pof;

          // 4.4 old adults
          int newWise_num_ge25pof_tx = oldAdults_num_ge25pof_tx;
          oldAdults_num_ge25pof_tx = newOldAdults_num_ge25pof_tx;

          // 4.5 wise
          wise_num_ge25pof_tx = newWise_num_ge25pof_tx;
    }


    //* finally: print results
    System.out.println("Anzahl Jungtiere:\n"+puppy_num_ge25pof_taoxiang+"\nAnzahl Junge Erwachsene:\n"+youndAdults_num_ge25pof_taoxiang+"\nAnzahl Erwachsene:\n"+adults_num_ge25pof_taoxiang+"\nAnzahl Alte Erwachsene:\n"+oldAdults_num_ge25pof_tx+"\nAnzahl Weise:\n"+wise_num_ge25pof_tx);
  }
}