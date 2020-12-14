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
 * Zoo: A zoo contains an array of Vivariums
 */
public class Zoo {
    //* private attrs
    private Vivarium[] vivaria;

    //* constructor with its attrs
    public Zoo(Vivarium[] vivaria) {
        this.vivaria = vivaria;
    }

    //* getters
    public Vivarium[] getVivaria() {
        return this.vivaria;
    }

    //* tostring
    //e.g. "{[area: 34, constructionYear: 1965, animals: (name: Pingu, foodCosts: 231), (name: Pinguina, foodCosts: 270)], [area: 54, constructionYear: 1968,
    //     animals: (name: Felix, foodCosts: 433), (name: Yoghi, foodCosts: 331)]}"
    @Override
    public String toString() {
        //* the opening "{"
        String ret = "{";

        //* appending the string form of each vivarium to $(ret)
        for (int i = 0; i < vivaria.length; i++) {
            ret += vivaria[i].toString();

            //! if not the last item in vavaria, append a ", "
            if(i!=vivaria.length-1) ret+= ", ";
        }

        //* the closing "}"
        ret += "}";

        // return
        return ret;
        
    }

    //* getCosts
    /**
     * return the sum of all costs per year of each Vivarium in this Zoo
     * @return
     */
    public int getCosts() {
        //* sum up the costs of all the Vivarium
        int sum = 0;
        for (Vivarium vivarium : vivaria) {
            sum += vivarium.getCosts();
        }
        return sum;
    }



    
}
