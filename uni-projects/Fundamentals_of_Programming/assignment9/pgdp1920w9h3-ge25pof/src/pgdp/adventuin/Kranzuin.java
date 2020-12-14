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
package pgdp.adventuin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

public final class Kranzuin {
    //* julian kranz
    public static final Kranzuin JULIAN=new Kranzuin();


    //* private constructor
    private Kranzuin(){

    }

    //* public functions

    /**
     * implement the algorithm on Artemis to calculate the number of question sheets of PGdP 
     * @param questions
     * @return
     */
    public int beantworteFragen(List<String> questions){
        //!!! if questions is null
        if(questions == null) return Integer.MAX_VALUE;

        //!!! if empty list
        if(questions.isEmpty()) return Integer.MAX_VALUE;

        //!!! list of all the numbers existing in the relevant questions
        List<Integer> all_numbers = new ArrayList<>();

        //* filer the questions to store the relevant questions
        List<String> relevant_questions = 
        questions.stream().filter(question->
        question.contains("Aufgaben") ||
        question.contains("Blätter") ||
        question.contains("Anzahl")
        ).collect(Collectors.toList());

        //!!! if there is no relevant question:
        if(relevant_questions.size()==0 ){
            // return 15 + max of all the lengths of strings
            return 15 + questions.stream().max((s1,s2) -> s1.length()-s2.length()).get().length();

        }

        

        //* iterate all the questions using stream
         relevant_questions.forEach(question ->{
           {

               //!!! abstract the integers from the question
               //!!! keeping the spaces, and !!!
               String[] result={""};
               question.chars().filter(a -> Character.isDigit(a) || a==' '  ).forEach(c->result[0]+=(char)c);

               //!!! removing the leading and tracking spaces, and reduce multiple spaces to only one for splitting
               String numbers[] = result[0].trim().replaceAll(" +", " ").split(" ");

               //!!! map the strings to integers through Integer.parseInt and collect it to list
               ArrayList<Integer> numbers_asList = (ArrayList<Integer>) new ArrayList<String>(Arrays.asList(numbers)).stream().
               map(s ->{
                   try {
                       return Integer.parseInt(s);
                   } catch (Exception e) {
                       return -1;       //!!! -1 because we only consider positive integers
                   }
                   
               })
               //!!! filter out non positive numbers
               .filter(a->a>0)
               //!!! sorted because we need to get the middle value if necessary
               .sorted()
               .collect(Collectors.toList());

               //!!! add the local number to the list of global numbers
               {

                   // if empty list
                   if(numbers_asList.isEmpty()){
                       // do nothing
                   }

                   // else if there is only one number
                   else if(numbers_asList.size()==1){
                       all_numbers.add(numbers_asList.get(0));
                   }

                   // else calculate the middle value
                   else {
                       int min = numbers_asList.get(0);
                       int max = numbers_asList.get(numbers_asList.size()-1);
                       all_numbers.add((min+max)/2);    // down rounding
                   }
               }

           }
        });

        //* iterating all the numbers
        {   
            //!!! if there is no numbers at all: return the first number that >= 15 and csan be divided by at least one length of the relevant questions
            if(all_numbers.isEmpty()){
                return
                ThreadLocalRandom.current().ints(15,Integer.MAX_VALUE)
                .dropWhile(integer ->{
                    // drop while the integer can't not be divided by ant length of relevant strings
                    return relevant_questions.stream().allMatch(q -> (integer % q.length())!=0);
                }).findFirst().getAsInt();
            }

            // 0. if any of the numbers is less than 15: return 1783
            if(all_numbers.stream().anyMatch(a->a<15)){
                return 1783;
            }

            // 1. if all numbers >= 15, return the minimum
            else {
                return all_numbers.stream().min((a,b)->a-b).get();
            }
        }
    
    }
    public static void main(String[] args) {
        List<String> questions = new ArrayList<>();
        //questions.add("Hallo, letztes Jahr gab es insgesamt ----20 Blätter, wird es dieses Jahr vielleicht nur -20 Blätter geben oder sind es wieder -20?");
        questions.add("10a10 Anzahl");
        // int a;
        //System.out.println(a=Kranzuin.JULIAN.beantworteFragen(questions));;
        //System.out.println(a % questions.get(0).length()==0 || a%questions.get(1).length()==0 );
    }

}
