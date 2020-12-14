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

public class Penguin {
    private final int birthYear;
    private final String name;
    private final Gender gender;
    private Fish favoriteFish;

    public Penguin(int birthYear, String name, Gender gender, Fish favoriteFish) {
        this.birthYear = birthYear;
        this.name = name;
        this.gender = gender;
        this.favoriteFish = favoriteFish;
    }

    public static void main(String[] args) {
        //Penguin a = new Penguin(1920, "CAOdsaaaaasfBIzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz", Gender.MALE, null);
        //System.out.println(a.hashCode());
    }


    /**
     * overwrite the equals method of class Object <P>
     * Compare this Penguin's FINAL attrs with the other 
     */
    public boolean equals(Object other) {
        //!!! if other is null
        if(other == null){
            System.out.println("not equal: The other object is null");
            return false;
        }

        //!!! else if other object is not an instance of class Penguin
        else if (!(other instanceof Penguin)){
            System.out.println("not equal: The other object is not of class Penguin");
            return false;
        }


        // else compare the 3 final attrs
        Penguin otherPenguin = (Penguin) other;

        // 0. birthyear
        if(this.birthYear!=otherPenguin.birthYear){
            System.out.println("not equal: The other penguin's birthyear differs from this penguin's birth year");
            return false;
        }

        // 1. name
        // 1.1 if at least one attr is null
        if(this.name==null){
            System.out.println("not equal: This penguin's name is null");
            return false;
        }
        else if(otherPenguin.name==null){
            System.out.println("not equal: The other penguin's name is null");
            return false;
        }

        // 1.2 else
        else if(!this.name.equals(otherPenguin.name)){
            System.out.println("not equal: The other penguin's name differs from this penguin's name");
            return false;
        }


        // 2. gender
        // 2.1 if at least one attr is null
        if(gender==null){
            System.out.println("not equal: This penguin's gender is null");
            return false;
        }
        else if (otherPenguin.gender==null){
            System.out.println("not equal: The other penguin's gender is null");
        }

        // 2.2 else
        else if (gender!=otherPenguin.gender){
            System.out.println("not equal: The other penguin's gender differs from this penguin's gender");
        }


        // 3. else these 2 penguins are equal
        return true;


    }


    /**
     * return the hash value of this penguin with the following 3 requirements<P>
     * 1. In one execution of the program the hash value returned by this function must be consistent(It can be different in other executions though)<P>
     * 2. Equal penguins have equal hash values ("equal" determined by {@code equals} method) <P>
     * 3. (optional) Unequal penguins should have different hash values<P>
     * The implementation is naive: first map the 3 final attrs to integers with bijective functions respectively, then sum these 3 integers then mod 2147483423
     */
    public int hashCode() {
        int hash_val = 0;

        //* 1. map birthyear with bijective function y = 1024*x + 1024
        hash_val += (1024*birthYear+1024);

        //* 2. map name
        //* idea:
        //* split the string into substrings with fixed length = 2
        //* then map each substring(e.g. "ab") with function y = 2^3 *(ascii of a) + 2^15 * (ascii of b)
        //* then sum the results to $(hash_val)
        //!!! OR: null -> 0
        if(name == null){
            hash_val += 0;
        }

        // else 
        else
        {
            // preparations
            int length = name.length();         // the length of name
            int complete_turns = length / 2;
            int index = 0;

            // iterations for complete turns
            for (int i = 0; i < complete_turns; i++) {
                char a = name.charAt(index++);
                char b = name.charAt(index++);
                
                // y = 2^3 *(ascii of a) + 2^15 * (ascii of b)
                hash_val += 8 * (int) a + 32768 * (int) b; 

            }

            // for remaining char
            if(length % 2 != 0){
                hash_val += name.charAt(index) * 8;
            }
        }

        //* 3. map gender
        // null -> 404
        // male -> 666
        // female -> 999

        if(gender==null){
            hash_val += 404;
        }

        else if(gender == Gender.MALE){
            hash_val += 666;
        }

        else hash_val += 999;

        //* 4. hash_val %= 2147483423
        return hash_val>=0? hash_val % 2147483423 : (hash_val+2147483423) %2147483423 ;


    }

    public int getBirthYear() {
        return birthYear;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public Fish getFavoriteFish() {
        return favoriteFish;
    }

    public void setFavoriteFish(Fish favoriteFish) {
        this.favoriteFish = favoriteFish;
    }

}
