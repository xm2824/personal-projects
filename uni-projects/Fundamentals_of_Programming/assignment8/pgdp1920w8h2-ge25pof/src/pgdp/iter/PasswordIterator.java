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
package pgdp.iter;

import java.util.Iterator;

public class PasswordIterator implements Iterator<String> {
    //* private attrs
    private final int length;
    private boolean case1_over,case2_over,case3_over;   // to store the states of each case, used in hasNext() and Next()
    private long total_numbers;                         // to store the total number of all the codes, i.e. 10^(length)
    private long current;                               // current index, in [0, total_numbers-1]

    //** for case 4
    private long temp;                                   // temp value between [0,max]
    private long max;                                    // maximum consisting of $(length) digits, e.g. if length =3, then max = 999

    public PasswordIterator(int passwordLength) {
        //! if length <1 or >9: bad argument
        if(passwordLength<1 || passwordLength>9) Util.badArgument("password length must be in interval [1,9]");

        length = passwordLength;
        case1_over = case2_over = case3_over = false;
        current = -1;
        total_numbers = (long)Math.pow(10, length);

        //* for case 4
        temp = -1;
        max = 0;
        for (int i = 0; i < length; i++) {
            max *=10;
            max +=9;
        }
    }

    public boolean hasNext() {
        return (current+1)<total_numbers;
    }

    public String next() {
        //!!! first updating
        current++;

        //!!! if out of bound: nosuch...
        if(current>=total_numbers){
            Util.noSuchElement("no more elements");
        }


        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! check if the cases are over !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //!!! 1. if case3 over, i.e. current > number of case 1+2+3
        if(!case3_over&&(current > 9+ (11-length)*2)){
            case1_over = true;
            case2_over = true;
            case3_over = true;
        }

        //!!! if case2 over, i.e. current >10 + number of case2 = 10 + (11-l)
        else if(!case2_over &&(current>9+(11-length))){
            case1_over = true;
            case2_over = true;
        } 

        //!!! if case1 over, i.e. current > (number of case1-1) =9
        else if(!case1_over&& current>9){
            case1_over = true;
        } 

        

        

        //* 0. if case1 not over, i.e. current in [0,9]
        if(!case1_over){
            long value = 0;
            for (int i = 0; i < length; i++) {
                value*=10;
                value += current;
            }

            return Util.longToStringWithLength(value, length);
        }

        //* 1. else if case2 not over, i.e. current in [10,21-length]
        else if (!case2_over){
            long temp = current - 10;
            long value = 0;
            for (long i = 0,j =temp; i < length; i++,j++) {
                value *= 10;
                value += j;
            }
            return Util.longToStringWithLength(value, length);
        }

        //* 2. else if case3 not over, 
        else if (!case3_over){
            long temp = (length-1)+(current - 21 + length);
            long value = 0;
            for (long i = 0,j = temp;i < length; i++,j--) {
                value *= 10;
                value += j;
            }
            return Util.longToStringWithLength(value, length);
        }

        //* 3. else case4: all other codes
        else{
            //* loop until a valid number or eof
            while(true){
                //!!! first updating
                temp ++;

                //!!! if temp > max: error
                if(temp > max) Util.noSuchElement("no more elements");


                //!!! check if conflicting with case 1-3
                //!!! 1. case1
                {
                    // if length = 3, c1 = 111 * (temp %10)
                    int c1 = 0;
                    for (int i = 0; i < length; i++) {
                        c1 *= 10;
                        c1 += 1;
                    }
                    c1 *= (temp % 10);
                    
                    //! if conflicting, continue
                    if(c1 == temp) {
                        continue;
                    }

                    // otherwise check case2
                }

                //!!! case2
                {
                    // build c2
                    int c2[] =new int[length] ;          // the digits of temp (possible 0 leading)
                    long tmp = temp;
                    for (int i = 0; i < length; i++) {
                        c2[length-1-i] = (int) (tmp % 10);
                        tmp /= 10;
                    }


                    //! if conflicting, continue
                    boolean not_conflicting = false;
                    for (int i = 0; i < length-1; i++) {
                        if(c2[i]+1 != c2[i+1]) {
                            not_conflicting = true;
                            break;
                        }
                    }

                    if(!not_conflicting)
                         continue;
                        
                    // else check case3

                }

                //!!! case3
                {
                    // build c3
                    int c3[] =new int[length] ;          // the digits of temp (possible 0 leading)
                    long tmp = temp;
                    for (int i = 0; i < length; i++) {
                        c3[length-1-i] = (int) (tmp % 10);
                        tmp /= 10;
                    }


                    //! if conflicting, continue
                    boolean not_conflicting = false;
                    for (int i = 0; i < length-1; i++) {
                        if(c3[i]-1 != c3[i+1])
                        {   
                            not_conflicting = true;
                            break;
                        }
                    }

                    if(!not_conflicting)
                         continue;
                }

                //* otherwise this value is valid
                return Util.longToStringWithLength(temp, length);
            }
            
        }

    }
    public static void main(String[] args) {
        Iterator a = new PasswordIterator(3);
        while (a.hasNext()) {
            System.out.println(a.next());
        }
    }

}
