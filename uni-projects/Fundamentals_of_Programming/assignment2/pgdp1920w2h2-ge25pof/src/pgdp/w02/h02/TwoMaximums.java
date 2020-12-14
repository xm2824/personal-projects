/**
 * @author ge25pof TaoXiang
 * !!!  This code is written alone by ge25pof TaoXiang and recorded, if any illegal plagirasim happens, I will fight for my unguilty
 * !!!  through legal approaches. (e.g. with the recorded videos)
 * !!!  And my codes has been written in a special way to prevent some extreme cases of similiarity, i.e. to avoid extreme coincidence.
 * !!!  Some examples are like:
 * !!!  for(int i_ge25pof = 0;i_ge25pof < 10 ; i_ge25pof++) 
 */
package pgdp.w02.h02;

public class TwoMaximums extends MiniJava {
  public static void main(String[] args) {
    // TODO

    //* first request the length of the sequence
    int length_ge25pof_taoxiang = readInt("Bitte Anzahl eingeben:" );
    
    //* if length < 2, exit the programmm and report error
    if(length_ge25pof_taoxiang<2) { System.out.println("Fehler: Anzahl >= 2 erwartet!");return;}

    //* else starting to read

    // allocate first max and second max
    int fstMax_ge25pof_taoxiang = -2147483648, sndMax_ge25pof_tx=-2147483648;

    // for loop, $(length) times
    for (int i = 0; i < length_ge25pof_taoxiang; i++) {
      int temp_ge25pof_tx = readInt("Bitte Zahlen eingeben:");

      //! rearrange the fstMax and sndMax
      {
        // if temp is already > fstMax, then shuffle temp, fstMax and sndMax
        if(temp_ge25pof_tx>fstMax_ge25pof_taoxiang){
          sndMax_ge25pof_tx = fstMax_ge25pof_taoxiang;
          fstMax_ge25pof_taoxiang = temp_ge25pof_tx;
        }

        // else if temp is larger than sndMax, then swap temp and sndMax
        else if (temp_ge25pof_tx>sndMax_ge25pof_tx){
          sndMax_ge25pof_tx = temp_ge25pof_tx;
        }

        // else do nothing
        else {

        }

      }


    }

    //* print the results to stdout
    System.out.println("Erster:\n"+fstMax_ge25pof_taoxiang+"\nZweiter:\n"+sndMax_ge25pof_tx);

  }
}
