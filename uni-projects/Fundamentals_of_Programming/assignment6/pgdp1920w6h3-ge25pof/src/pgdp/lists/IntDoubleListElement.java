
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


package pgdp.lists;
public class IntDoubleListElement {
///////////////////////////////////////// sample solution of P assignment4 ///////////////////////////////////////////////////
    private int info;

    public void setInfo(int inf) {
        info = inf;
    }

    public int getInfo() {
        return info;
    }

    public IntDoubleListElement next;
    public IntDoubleListElement prev;

    public IntDoubleListElement(int startInfo) {
        info = startInfo;
        next = null;
        prev = null;
    }
    
    public boolean isEqual(IntDoubleListElement other) {
        return other != null && info == other.info;
    }
///////////////////////////////////////// end of sample solution //////////////////////////////////////////////////////////
}
