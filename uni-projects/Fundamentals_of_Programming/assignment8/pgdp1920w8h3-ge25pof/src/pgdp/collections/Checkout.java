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

public class Checkout {
    //* private attrs
    private Queue<PenguinCustomer> queue;
    private Queue<FishyProduct> bandBeforeCashier;
    private Queue<FishyProduct> bandAfterCashier;

    //* construtor without paramters
    public Checkout() {
        queue = new LinkedQueue<>();
        bandBeforeCashier = new LinkedQueue<>();
        bandAfterCashier = new LinkedQueue<>();
    }

    //* getters
    public Queue<PenguinCustomer> getQueue() {
        return this.queue;
    }

    public Queue<FishyProduct> getBandBeforeCashier() {
        return this.bandBeforeCashier;
    }

    public Queue<FishyProduct> getBandAfterCashier() {
        return this.bandAfterCashier;
    }

    //* public functions

    /**
     * return the length of the penguin customers queue
     */
    public int queueLength() {
        return queue.size();
    }

    /**
     * the procedure of serving the next customer:<P>
     * 1. the first customer in the queue will deque<P>
     * 2. he places all his products on the band before the cashier<P>
     * 3. the cashier scann the products and get the total price, and place the products on the band after the cashier<P>
     * 4. the customer takes all the products from the back band<P>
     * 5. the customer pays (exception if he has not enough money)
     */
    public void serveNextCustomer() {

        //* 1. the first customer in the queue will deque
        PenguinCustomer the_next = queue.dequeue();

        //!!! if the_next is null: no more customers
        if(the_next == null) {
            //? just return?
            return;
        }

        //otherwise
        //* 2. he places all his products on the band before the cashier
        the_next.placeAllProductsOnBand(bandBeforeCashier);

        //* 3. the cashier scann the products and get the total price, and place the products on the band after the cashier
        // 3.1 sum the prices
        int price_sum = 0;
        {
            while (!bandBeforeCashier.isEmpty()) {
                FishyProduct tmp = bandBeforeCashier.dequeue();
                price_sum += tmp.getPrice();

                // 3.2 place the product onto the back band
                bandAfterCashier.enqueue(tmp);
            }
        }

        //* 4. the customer takes all the products from the back band
        the_next.takeAllProductsFromBand(bandAfterCashier);

        //* 5. the customer pays (exception if he has not enough money)
        the_next.pay(price_sum);
    }

}
