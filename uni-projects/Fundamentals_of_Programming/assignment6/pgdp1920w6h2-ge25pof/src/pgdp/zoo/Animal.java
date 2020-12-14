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
package pgdp.zoo;

/**
 * Animal: A Vivarium contains an array of Animals
 */
public class Animal {
    //* private attrs
    private String name;
    private int foodCosts;

    //* constructor with all its attrs
    public Animal(String name, int footCosts) {
        this.name = name;
        this.foodCosts = footCosts;
    }

    //* getters
    public String getName() {
        return this.name;
    }

    public int getFoodCosts() {
        return this.foodCosts;
    }

    //* tostring
    //e.g. "(name: Pingu, foodCosts: 231)"
    @Override
    public String toString() {
        return "(" +
            "name: " + getName()  +
            ", foodCosts: " + getFoodCosts() +
            ")";
    }

    
}
