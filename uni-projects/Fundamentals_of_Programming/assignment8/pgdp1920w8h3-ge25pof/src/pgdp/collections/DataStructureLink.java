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
package pgdp.collections;

public class DataStructureLink<T>{
    //* attrs
    private DataStructureConnector<T> A;
    private DataStructureConnector<T> B;

    //* constructor
    public DataStructureLink(DataStructureConnector<T> A, DataStructureConnector<T> B){
        //!!! if A or B is null
        if(A == null || B == null){
            ExceptionUtil.illegalArgument("arguments is null");
        }

        // else
        this.A = A;
        this.B = B;
    }

    //* public functions

    /**
     * move the next element of A to B
     * 
     * @return true on success, false on failed
     */
    public boolean moveNextFromAToB(){
        //* 1. remove next element from A
        T next = A.removeNextElement();

        //!!! if next is null
        if(next == null){
            return false;
        }

        // else
        //* 2. add this element to B
        else{
            B.addElement(next);
            return true;
        }
    }

    /**
     * move all elements of A to B
     */
    public void moveAllFromAToB(){
        while (A.hasNextElement()) {
            T next = A.removeNextElement();
            B.addElement(next);
        }
    }

    public static void main(String[] args) {
        LinkedStack<String> s = new LinkedStack<String>();
        s.push("xyz");s.push("Niuguip");s.push("Pinguin");
        LinkedStack<String> s2 = new LinkedStack<>();
        s2.push("NiuPinguin");

        DataStructureLink<Integer> test = new DataStructureLink<>(new StackConnector(s), new StackConnector<>(s2));
        test.moveAllFromAToB();

        System.out.println(s2.getInner_list().toString());
    }
}
