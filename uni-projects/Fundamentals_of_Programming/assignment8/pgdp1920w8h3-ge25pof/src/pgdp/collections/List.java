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

public class List<T> {
    private final T info;
    private List<T> next;

    ////////////////////////////////// helper function /////////////////////////////////
    public void append(T item){

        List<T> tmp = this;
        List<T> prev_tmp = null;

        while (tmp!=null) {
            prev_tmp = tmp;
            tmp = tmp.next;
        }

        //* out of the loop meaning tmp is null, so prev_tmp is the last entry
        prev_tmp.next = new List<T>(item);

    }
    ///////////////////////////////////////////////////////////////////////////////////

    public List(T x) {
        info = x;
        next = null;
    }

    public List(T x, List l) {
        info = x;
        next = l;
    }

    public void insert(T x) {
        next = new List(x, next);
    }
    /**
     * delete the next entry of this one
     */
    public void delete() {
        if (next != null)
            next = next.next;
    }

    public int length() {
        int result = 1;
        for (List t = next; t != null; t = t.next)
            result++;
        return result;
    }

    @Override
    public String toString() {
        String result = "[" + info;
        for (List t = next; t != null; t = t.next)
            result = result + ", " + t.info;
        return result + "]";
    }

    //* getters
    public T getInfo() {
        return this.info;
    }

    public List getNext() {
        return this.next;
    }
}
