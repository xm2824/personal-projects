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

public class PenguinCustomer {
    //* private attrs
    private final  String name;
    private int money;
    private Stack<FishyProduct> products;

    //* constructors
    public PenguinCustomer(String name, int initialMoney){
        //! check the validity
        if(name == null || initialMoney <0){
            ExceptionUtil.illegalArgument("name is null or initialMoney is negative");
        }


        this.name = name;
        money = initialMoney;
        products = new LinkedStack<>();
    }
    //* getters
    public String getName() {
        return this.name;
    }

    public int getMoney() {
        return this.money;
    }

    public Stack<FishyProduct> getProducts() {
        return this.products;
    }

    //* public functions

    /**
     * add a new {@code FishyProduct} to the {@code products} of this PenguinCustomer
     * @param fp
     */
    public void addProductToBasket(FishyProduct fp) {
        this.products.push(fp);
    }

    /**
     * place all the {@code products} of this PenguinCustomer onto band of the checkout
     * @param band
     */
    public void placeAllProductsOnBand(Queue<FishyProduct> band) {
        //* using a DataStructurelink
        DataStructureLink<FishyProduct> dsl = new DataStructureLink<>(new StackConnector<>(products), new QueueConnector<>(band));

        dsl.moveAllFromAToB();
    }

    /**
     * take all {@code products} from {@code band}   
     * @param band
     */
    public void takeAllProductsFromBand(Queue<FishyProduct> band) {
        //* using a DataStructurelink
        DataStructureLink<FishyProduct> dsl = new DataStructureLink<>(new QueueConnector<>(band), new StackConnector<>(products));

        dsl.moveAllFromAToB();
    }

    public void pay(int topay) {
        //* check the validity of topay
        if(topay<0){
            ExceptionUtil.illegalArgument("the money to pay is negative");
        }

        if(topay>money){
            ExceptionUtil.unsupportedOperation("the money to pay is larger than the penguin has");
        }

        // else
        money -= topay;
    }


    @Override
    public String toString() {
        return "PenguinCustomer{" +
            " name='" + getName() + "'" +
            ", money=" + getMoney() +
            ", products=" + getProducts().toString()  +
            "}";
    }

    /**
     * go to the checkout with shortest queue
     * @param sm
     */
    public void goToCheckout(PenguinSupermarket sm) {
        sm.getCheckoutWithSmallestQueue().getQueue().enqueue(this);
    }
    

    

}
