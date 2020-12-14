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
 * Vivarium: containing an array of Animals
 */
public class Vivarium {
    //* private attrs
    // int area, int constructionYear und Animal[] inhabitants
    private int area;
    private int constructionYear;
    private Animal[] inhabitants;

    //* construction with all its attrs
    public Vivarium( Animal[] inhabitants,int area, int constructionYear) {
        this.area = area;
        this.constructionYear = constructionYear;
        this.inhabitants = inhabitants;
    }

    //* getters
    public int getArea() {
        return this.area;
    }

    public int getConstructionYear() {
        return this.constructionYear;
    }

    public Animal[] getInhabitants() {
        return this.inhabitants;
    }

    //* tostring
    // e.g. "[area: 34, constructionYear: 1965, animals: (name: Pingu, foodCosts: 231), (name: Pinguina, foodCosts: 270)]"
    @Override
    public String toString() {
        // the string form of the inhabitants
        // output example: " (name: Pingu, foodCosts: 231), (name: Pinguina, foodCosts: 270)"
        String animals_toString="";
        for (int i=0;i<inhabitants.length;i++){

            //!!! " " before each entry !!!
            animals_toString +=" ";

            // string form of animal
            animals_toString += inhabitants[i].toString();

            //!!! if not the last item in inhabitants, appending a ','
            if(i!=inhabitants.length-1) animals_toString += ',';
        }

        //* combining together
        return "[" +
            "area: " + getArea() + 
            ", constructionYear: " + getConstructionYear() + 
            ", animals:" + animals_toString+
            "]";
    }

    //* getCosts()
    /**
     * return the operating costs of a Vivarium in euros per year
     * <p>
     * The operating costs consist of feeding costs and fixed costs<p>
     * <i>
     * feeding costs: sum of the feeding costs of each animal in this Vivarium
     * <p>
     * fixed costs:= 900 + area x 100 + area x (2019 - year of construction) x 5
     * @return costs per year
     */
    public int getCosts(){
        // part1: feeding costs
        int feeding_costs = 0;
        for (Animal animal : inhabitants) {
            feeding_costs += animal.getFoodCosts();
        }

        // part2: fixed costs
        int fixed_costs = 900 + area*100 + area * (2019 - constructionYear) * 5;

        // sum of part1 and part2
        return feeding_costs+fixed_costs;
    }



    
}