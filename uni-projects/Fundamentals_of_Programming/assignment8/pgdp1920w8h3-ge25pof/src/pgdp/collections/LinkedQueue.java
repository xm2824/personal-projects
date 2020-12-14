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

public class LinkedQueue<T> implements Queue<T>{
    //* private attr
    private List<T> inner_list;

    @Override
    public void enqueue(T item) {
        //!!! if inner list is null => initialising
        if(inner_list == null){
            inner_list = new List<T>(item);
        }

        // otherwise 
        else {
            inner_list.append(item);
        }

    }

    @Override
    public T dequeue() {
         //!!! if inner list is null => null
         if(inner_list == null){return null;}

         // else
         else{
             T ret = inner_list.getInfo();
             inner_list = inner_list.getNext();
             return ret;
         }
    }

    @Override
    public int size() {
        return inner_list == null? 0 : inner_list.length();
    }

    @Override
    public boolean isEmpty() {
        return inner_list == null || inner_list.length()==0;
    }

    @Override
    public String toString() {
        return inner_list.toString();
    }
}
