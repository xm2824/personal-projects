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

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public class PenguinColony {

    private HashSet<Penguin> penguins;

    public PenguinColony(HashSet<Penguin> penguins) {
        this.penguins = penguins;
    }

    public HashSet<Penguin> getPenguins() {
        return penguins;
    }

    public void setPenguins(HashSet<Penguin> penguins) {
        this.penguins = penguins;
    }


    /**
     * take all penguins in {@code otherColony} and add them to this penguinColony
     * <p>
     * {@code otherColony} should have no penguins after this function
     * @param otherColony
     */
    public void uniteColonies(PenguinColony otherColony) {
        
        //!!! if otherColony is null
        if(otherColony==null) return;

        //* 1. first add all penguins of otherColony to this colony
        penguins.addAll(otherColony.getPenguins());

        //* 2. otherColony should have no penguins 
        otherColony.getPenguins().clear();

    }

    /**
     * remove all penguins in this colony that meet the {@code pred},
     * and return a new {@code PenguinColony} that consists of these  just removed penguins
     * @param pred
     * @return
     */
    public PenguinColony splitColony(Predicate<? super Penguin> pred) {
        
        //* 1. the to return PenguinColony with its penguins
        HashSet<Penguin> _penguins = new HashSet<>();
        PenguinColony ret = new PenguinColony(_penguins);

        //* 2. iterate all penguins in this colony and add it to $(_penguins) if it meets the pred
        // using iterator
        java.util.Iterator<Penguin> iter = this.penguins.iterator();

        // the iterations
        while (iter.hasNext()) {
            Penguin tmp = iter.next();

            // if tmp meets the pred, 
            if(pred.test(tmp))
            {   
                // add it to the new colony
                _penguins.add(tmp);
            }
        }

        //* 3. remove all penguins in $(_penguins) from this colony
        this.penguins.removeAll(_penguins);

        //* 4. return the new colony
        return ret; 
        
    }

    /**
     * return the first {@code Penguin} in {@code penguinFriends} that belongs to this {@code PenguinColony}
     * <p>
     * nothing should be changed
     * @param penguinFriends
     * @return
     */
    public Penguin findFirstFriend(LinkedList<Penguin> penguinFriends) {
        
        //!!! if penguinFreind is null
        if(penguinFriends == null) return null;

        //* Penguin pointer
        Penguin ret = null;

        //* iterate all penguins in penguinFreinds and try to find the first one that belongs to this colony
        // using iterator
        java.util.Iterator<Penguin> iter = penguinFriends.iterator();

        // the iterations
        while (iter.hasNext()) {
            Penguin tmp = iter.next();

            // if tmp belongs to this colony, assign it to ret and break
            if(this.penguins.contains(tmp)){
                ret = tmp;
                break;
            }
        }

        //* return ret
        return ret;
    }

    /**
     * return true if all the penguins in this colony that meet the {@code pred} with their favorite fish in {@code fishes}, else false
     * @param pred
     * @param fishes
     * @return
     */
    public boolean canFeedPenguinsWithProperty(Predicate<? super Penguin> pred, Set<Fish> fishes) {
        
        //* 1. iterate all penguins in this colony
        // using iterator
        java.util.Iterator<Penguin> iter  = this.penguins.iterator();

        while (iter.hasNext()) {
            Penguin tmp = iter.next();

            // if tmp meets pred 
            {
                if (pred.test(tmp)){

                    // but tmp's favorite fish not in fishes => false
                    if(!fishes.contains(tmp.getFavoriteFish())){
                        return false;
                    }
                }
            }
        }

        //* if program reaches here, meaning there is no penguin in this colony that meets the pred but its favarite fish is not in fishes
        //* so return true
        return true;
    }


    /**
     * apply {@code fun} to each {@code Penguin} in this {@code PenguinColony} and sum the returned integers
     * @param fun
     * @return
     */
    public int computeSum(Function<? super Penguin, Integer> fun) {
        int sum = 0;

        // iterations
        java.util.Iterator<Penguin> iter = penguins.iterator();
        while (iter.hasNext()) {
            Penguin tmp = iter.next();

            // apply fun and add the return value to sum
            sum += fun.apply(tmp);
        }

        // return sum
        return sum;

    }

}
