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

public interface DataStructureConnector<T> {

    /**
     * return if the data structure still has elements
     * @return
     */
    public abstract boolean hasNextElement();

    /**
     * add a new element to the DS
     * @param item
     */
    public abstract void addElement(T item);

    /**
     * remove the next element of this DS, or null if no more elements available
     * ? what is the next element of this DS?
     * 
     * @return
     */
    public abstract T removeNextElement();
}
