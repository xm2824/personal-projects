
package pgdp.iter;

import java.util.function.Function;

public class PasswordBreaker {


    /**
     * find a password of length {@code passwordLength} using class {@code PasswordIterator}, so that after 
     * the password being concatenated with {@code salt} and then applied to {@code hashFunction}, the return value 
     * we got .=. {@code saltedPasswordHashValue} or null if no such password exits
     * @param hashFunction
     * @param passwordLength
     * @param salt
     * @param saltedPasswordHashValue
     * @return
     */
    public static String findPassword(Function<String, Integer> hashFunction, int passwordLength,
            String salt, int saltedPasswordHashValue) {
                //* if salt or hashfunction is null: bad argu
                if(salt==null || hashFunction == null) Util.badArgument("salt or hashFunction is null");

                //* iterating all the password of the given length
                PasswordIterator pi = new PasswordIterator(passwordLength);
                while (pi.hasNext()) {
                    // applying hashfuntion to concatenated password and salt
                    String pw = pi.next();
                    int returnvalue = hashFunction.apply(pw+salt);

                    // if returnvalue .=. saltedPasswordHashvalue: return this password
                    if(returnvalue == saltedPasswordHashValue) return pw;
                }

                //* if program reaches here, meaning there is no such password
                return null;

                
    }

}
