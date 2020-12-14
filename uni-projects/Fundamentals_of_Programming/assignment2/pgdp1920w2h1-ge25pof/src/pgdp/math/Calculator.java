/**
 * @author ge25pof TaoXiang
 * !!!  This code is written alone by ge25pof TaoXiang and recorded, if any illegal plagirasim happens, I will fight for my unguilty
 * !!!  through legal approaches. (e.g. with the recorded videos)
 * !!!  And my codes has been written in a special way to prevent some extreme cases of similiarity, i.e. to avoid extreme coincidence.
 * !!!  Some examples are like:
 * !!!  for(int i_ge25pof = 0;i_ge25pof < 10 ; i_ge25pof++) 
 */

package pgdp.math;

public class Calculator extends MiniJava {

    public static void main(String[] args) {
        /*
            first get input from console: 1 ~ 6, 
            repeating the request until the user gives a valid input
            if the input is 6, exit

            ? for that reading char is enough, but MiniJava doesn't provide it...
        */

        //* loop until input is 6
        while ( true){
        
            //* loop until a valid input is given
            int operation_ge25pof_taoxiang;                                                                                      // operation for the calculation
            String prompt_ge25pof_taoxiang = "Wählen Sie eine Operation:\n1) +\n2) -\n3) *\n4) /\n5) %\n6) Programm beenden";    // prompt for getting input from console
            while (true) {
            if((operation_ge25pof_taoxiang=readInt(prompt_ge25pof_taoxiang))<=6 && operation_ge25pof_taoxiang>=1) break;                                        // only break the loop if the input is integer and between 1-6
            }

            //* if the input is 6, then exit the programmm
            if(operation_ge25pof_taoxiang==6){
                break;
            }

            //* get the first operand
            int fst_ge25pof_tx;
            fst_ge25pof_tx = readInt("Ersten Operand eingeben:");

            //* the second operand
            int snd_ge25pof_tx;
            snd_ge25pof_tx = readInt("Zweiten Operand eingeben:");

            //* pinrt the result
            // use switch statement for laze...
            switch (operation_ge25pof_taoxiang) {
            

                // 1 for plus
                case 1 :{
                    System.out.println(fst_ge25pof_tx+snd_ge25pof_tx);  // print result and break
                    break;
                }

                // 2 for minus
                case 2 :{
                    System.out.println(fst_ge25pof_tx-snd_ge25pof_tx);// print result and break
                    break;
                }

                // 3 for *
                case 3 :{
                    System.out.println(fst_ge25pof_tx*snd_ge25pof_tx);  // print result and break
                    break;
                }

                // 4 for /
                case 4 :{
                    //! if not divided by 0 ...
                    if(snd_ge25pof_tx !=0)
                        {System.out.println(fst_ge25pof_tx/snd_ge25pof_tx);break;}  // print result and break
                    else 
                        {System.out.println("Fehler: Division durch 0!");break;}  // print error and break


                }

                // 5 for %
                case 5 :{
                    //! if not divided by 0 ...
                    if(snd_ge25pof_tx != 0)
                        {System.out.println(fst_ge25pof_tx%snd_ge25pof_tx);break;}  // print result and break
                    else 
                        {System.out.println("Fehler: Division durch 0!");break;}  // print error and break
                }
            
            }
            
           
        }


    }

}
