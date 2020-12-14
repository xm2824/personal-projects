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

public class PenguinSupermarket {
    //* private attr
    Checkout[] checkouts;

    //* constructor
    public PenguinSupermarket(int numer_of_checkouts){
        //!!! if number is non positive
        if(numer_of_checkouts<=0){
            ExceptionUtil.illegalArgument("number of checkouts is not positive");
        }

        checkouts = new Checkout[numer_of_checkouts];
        for (int i = 0; i < checkouts.length; i++) {
            checkouts[i] = new Checkout();
        }
    }

    //* getters
    public Checkout[] getCheckouts() {
        return this.checkouts;
    }

    //* public functions

    /**
     * return the {@code checkout} with a shortest queue in this supermarket at the moment <P>
     * If there are several checkouts with the same-length shortest queue, return the first one
     */
    public Checkout getCheckoutWithSmallestQueue() {
        // a tmp variable to store the current shortest length and its index
        int shortest = 999999;
        int ptr = 0;

        // iterate all checkouts
        for (int i = 0; i < checkouts.length; i++) {
            // if any checkout has smaller queue length
            if(checkouts[i].queueLength()<shortest) {
                // updating
                shortest = checkouts[i].queueLength();
                ptr = i;
            }
        }

        return checkouts[ptr];
    }

    /**
     * remove the checkout at given position from the array => the array will be shorter
     * <p>
     * The penguins at this checkout will look for a new checkout using {@code goToCheckout} in an reverse ordering,
     * i.e. the last in the queue will do it first
     * <p>
     * Error when index is out of bound or the to close checout is the last open one, i.e. there must be at least one checkout open
     * @param index
     */
    public void closeCheckout(int index) {
        //! if index if invalid
        if(index<0 || index >= checkouts.length){
            ExceptionUtil.illegalArgument("invalid index of the checkouts");
        }

        //! if there is only one open checkout
        if(checkouts.length==1){
            ExceptionUtil.unsupportedOperation("there must be at least one open checkout");
        }

        //else 
        //* 1. copy the remaining checkouts to a new array
        Checkout newCheckouts[] = new Checkout[checkouts.length-1];
        {
            int ptr = 0;
            for (int i = 0; i < checkouts.length; i++) {
                // skip i=index
                if(i == index) continue;

                newCheckouts[ptr++] = checkouts[i];
    
            }
        }

        //* 2. distribute the penguins at the closed checkout to other checkouts
        {
            // using a DataStructureLink to store the penguins in a stack
            // so that the ordering is reversed
            LinkedStack<PenguinCustomer> tmp = new LinkedStack<>();
            DataStructureLink<PenguinCustomer> dsl = new DataStructureLink<>(new QueueConnector<>(checkouts[index].getQueue()),new StackConnector<>(tmp));
            dsl.moveAllFromAToB();

            // distributing the penguins
            while (!tmp.isEmpty()) {
                tmp.pop().goToCheckout(this);
            }

        }

        //* 3. update the checkouts
        checkouts = newCheckouts;

        
    }

    /**
     * call for each checkout {@code serverNextCustomer} 
     */
    public void serveCustomers() {
        for (Checkout checkout : checkouts) {
            checkout.serveNextCustomer();
        }
    }



}
