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

public class FishyProduct {
    //* private attrs
    private final String name;
    private final int price;


    //* constructor
    public FishyProduct(String name, int price) {
        //! check if the parameters are valid
        if(name == null){
            ExceptionUtil.illegalArgument("name is null");
        }

        else if (price<=0){
            ExceptionUtil.illegalArgument("price is non positive");
        }

        this.name = name;
        this.price = price;
    }

    //* getters
    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }
    //* public functions


    @Override
    public String toString() {
        return "FishyProduct("+name+", "+price+")";
    }


}
