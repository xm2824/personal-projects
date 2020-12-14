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

package pgdp.iter;

import java.util.Iterator;

/**
 * Range
 */
public class Range implements Iterable<Integer> {
    //* private attrs
    private final int begin,end,stride;

    //* constructors
    public Range(int begin,int end, int stride){
        this.begin = begin;
        this.end = end;

        //! if stride <= 0: bad argument
        if(stride<=0) Util.badArgument("stride is <= 0");

        // else store it
        this.stride = stride;
    }

    public Range(int begin, int end){
        this.begin = begin;
        this.end = end;
        stride = 1;
    }
        
    /**
     * return an iterator which iterates from $(begin) to $(end) (asc) or $(end) to $(from) (dsc),<p>iterating step length = $(stride) <p>
     * 
     */
    //* public functions
    @Override
    public Iterator<Integer> iterator() {
        // ascending or descending?
        boolean ascending = begin <= end;

        
        // the iterator class
        class myIterator implements Iterator<Integer>{
            //* 0.1 use a temp var to store the current value
            int temp;

            myIterator(){
                //* 0.2 if ascendingly, temp = begin - stride
                //* "- stride": so that we can first add stride (updating), before we return it
                if(ascending) temp = begin - stride;

                //* 0.3 if descendingly, temp = begin + stride
                else temp = begin + stride;
            }

            @Override
            public boolean hasNext() {
                //! if ascendingly
                if(ascending)
                    return (temp +stride) <= end;

                //! if descendingly
                else 
                    return (temp - stride) >= end;
            }

            @Override
            public Integer next() {
                //* ascendingly:
                if(ascending){
                    //!!! first updating
                    temp += stride;

                    //!!! if temp is already larger than end: error
                    if(temp > end) Util.noSuchElement("no more items");

                    // else return it
                    return temp;
                }

                //* descendingly:
                else {
                    //!!! first updating
                    temp -= stride;

                    //!!! if temp is already larger than end: error
                    if(temp < end) Util.noSuchElement("no more items");

                    // else return it
                    return temp;
                }


            }
            
        }
        //* return this iterator
        return new myIterator();
    }


    public static void main(String[] args) {
        Range a = new Range(10, 15);
        Iterator b = a.iterator();
        for (int i = 0; i < 10; i++) {
            System.out.println(b.next());
        }
    }
}
