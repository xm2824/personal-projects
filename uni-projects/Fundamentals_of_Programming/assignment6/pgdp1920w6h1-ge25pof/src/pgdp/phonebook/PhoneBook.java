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
package pgdp.phonebook;

/**
 * class PhoneBook describes a phone book with an array of entries
 */
class PhoneBook{
    //* private attrs
    //! entries are assumed sorted, the first order is last name, the second order is the first name
    private Entry entries[];


    public PhoneBook(Entry[] entries) {
        this.entries = entries;
    }


    //* public functions

    /**
     * (possible NULL returning value)<p>
     * find the given first name and last name in this phone book using binary search, asccii-comparing, iteratively
     * <p>
     * The criterion for 2 names being equal is: both the first name and the last name equal
     * @param fstName
     * @param lstName
     * @return the phone number of the given name or null if not found
     */
    public String find(String fstName, String lstName) {
        //* for iterative implementation, we need 2 pointers to point the $(from) and $(to) in this.entries array
        //* as global variables
        //* (both inclusive)
        int from = 0;
        int to = this.entries.length-1;

        //! the iterations
        while (from <= to) {
            //! case1: from == to
            if(from == to){
                // so the entry we need to compare with is just pointed by from/to
                // (1) if both names equal
                if(entries[from].getFirstName().equals(fstName)&&
                    entries[from].getLastName().equals(lstName)
                )
                {
                    // found! 
                    return entries[from].getPhoneNumber();
                }

                // (2) at least one name doesn't match
                else
                        // not found!
                        return null;
                    
            }

            //! case2: from < to
            else{
                // a mid pointer pointing the middle value between from and to
                int mid = (from+to)/2;
                Entry middle = entries[mid];

                //! comparing middle with the given name
                //! (1): middle is just equal to the given name
                if(middle.getFirstName().equals(fstName)&&
                middle.getLastName().equals(lstName)){
                    // found!
                    return middle.getPhoneNumber();
                }

                //! (2): value is expected to be located in the left side of middle
                // 2.1: last name of value is already less than middle OR
                if(lstName.compareTo(middle.getLastName())<0    ||
                // 2.2 last names equal, but the first name is less then middle
                (lstName.compareTo(middle.getLastName()) == 0
                    &&
                 fstName.compareTo(middle.getFirstName())<0
                )
                ){
                    // searching the left side
                    to = mid -1;
                }
                
                //! (3): value is expected to be located in the right side of middle
                // 3.1: last name of value is already greater than middle OR
                else if(lstName.compareTo(middle.getLastName())>0    ||
                // 3.2 last names equal, but the first name is greater then middle
                (lstName.compareTo(middle.getLastName()) == 0
                    &&
                 fstName.compareTo(middle.getFirstName())>0
                )
                ){
                    // searching the right side
                    from = mid +1;
                }
            }

            
             
        }

        //! out of the iteration means: from > to, i.e. the iteration is over but we still can't find such name
        return null;


    }

    
}
