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

package pgdp.arrays;

//import sun.security.util.Length;

public class ProbabilisticSearch extends  MiniJava {
    /**
     * Binary Search aus der Vorlesung leicht abgewandelt
     */
    public static int[] find (int[] a, int x) {
        return find0(a, x, 0, a.length-1, 1);
    }


    public static int[] find0 (int[] a, int x, int n1, int n2, int numberOfSteps) {
        int t = (n1+n2)/2;
        if (a[t] == x)
            return new int[]{t, numberOfSteps};
        else if (n1 >= n2)
            return new int[]{-1, numberOfSteps};
        else if (x > a[t])
            return find0 (a,x,t+1,n2, numberOfSteps + 1);
        else if (n1 < t)
            return find0 (a,x,n1,t-1, numberOfSteps + 1);
        else return new int[]{-1, numberOfSteps};
    }



    /**
     * helper function which uses binary search to search $(x) in the given intervall from $(n1) to $(n2) in the given array $(a)
     * <p> n1, n2 are both inclusive
     * @param a
     * @param x
     * @param n1    
     * @param n2
     * @param numberOfSteps
     * @return {index of x,number of steps}, index = -1 if not found
     */
    private static int[] helper(int[] a, int x, int n1, int n2, int numberOfSteps) {
        int t = (n1+n2)/2;
        if (a[t] == x)
            return new int[]{t, numberOfSteps+1};
        else if (n1 >= n2)
            return new int[]{-1, numberOfSteps};
        else if (x > a[t])
            return helper (a,x,t+1,n2, numberOfSteps + 1);
        else if (n1 < t)
            return helper (a,x,n1,t-1, numberOfSteps + 1);
        else return new int[]{-1, numberOfSteps};
    }



    /**   
     * probalistic search of $(value) in the given sorted array $(arr)
     * @param arr already sorted 
     * @param value
     * @return {index of x,number of steps}, index = -1 if not found
     */
    public static int[] probalisticSearch(int[] arr, int value) {
        
        //* 1. calculate the predicted position 
        //* formular = rounding of ((value - min)/((max-min)/(size-1)))
        // where 
        // min is the minimum of the array, here just arr[0] since it's sorted
        // max is the maximum of the array, here arr[size-1]
        // size is the length of the array
        //! rounding: for a integer x,[x,x+0.5) -> x; [x+0.5,x+1) -> x+1
        int predicted_position;
        {
            int size = arr.length;
            int min = arr[0];
            int max = arr[size-1];

            // tmp value before rounding
            float tmp = (((float)value) - min)/((float)(max-min)/(size-1));

            //! rounding:
            // first get the integer of the tmp
            int tmp_int = (int)tmp;
            // [x,x+0.5) -> x;
            if(tmp <(float)(tmp_int+0.5)){
                predicted_position = tmp_int;
            }
            // [x+0.5,x+1) -> x+1
            else predicted_position = tmp_int+1;

            //! if the predicted position is negative...
            if(predicted_position<0) predicted_position = 0;

            //! if the predicted position is out of bound
            if(predicted_position>=arr.length) {
                predicted_position = arr.length-1;
            }
        }
        //System.out.println(predicted_position);

        //* 2. "exponential iteration" until found an equal entry or an interval for the binary search
        // preparations
        int step_num = 0;       
        int interval_left = 404; int interval_right = 404;      // left and right index of the interval
        int running_index = predicted_position;
        int running_index_prev = 404; //! the running index in the last round, which stands for the left/right boundary of the interval, see 2.1.(2) or 2.3.(2)

        //* 2.1 if arr[predicted_position] < value
        if(arr[predicted_position]<value){
            //* running to the right until a greater or equal value
            while(running_index<arr.length &&arr[running_index]<value){
                //! the step length of this step = 2^($(step_num))
                int step_length = 1;
                {
                    for (int i = 0; i < step_num; i++) {
                        step_length *= 2;
                    }
                }
    
                //!!! updating 
                running_index_prev = running_index;
                running_index += step_length;
                step_num ++;
            }
            
            //! out ot the while loop meanning we either reached an equal value ..(1)
            //! or a greater value ..(2)
            //! or running index out of bound ..(3)
            //(3) 
            if(running_index>=arr.length){
                //! interval: [runnning index prev, arr.length-1]
                return helper(arr, value,running_index_prev , arr.length-1, step_num);
            }

            // (1)
            if(arr[running_index] == value) {
                return new int[]{running_index,step_num+1};
            } 
            // (2): starting running in reverted diretion
            else{
                //! 2.1.(2)
                //! the running index now is the right boundary for the interval!!
                //! the the running index in the last round is the left boudary since it points to the last entry which is smaller than value
                interval_right = running_index;
                interval_left = running_index_prev;

                // using binary search as Artemis requires
                return helper(arr, value, interval_left, interval_right, step_num);
            }
        }

        //* 2.2 if arr[predicted_pos] == value
        else if(arr[predicted_position] == value){
            return new int[]{predicted_position,1};
        }

        //* 2.3 else if arr[predicted_pos] > value
        else{
            //* running to the left until a less or equal value
            while(running_index>=0 &&arr[running_index]>value){
                //! the step length of this step = 2^($(step_num))
                int step_length = 1;
                {
                    for (int i = 0; i < step_num; i++) {
                        step_length *= 2;
                    }
                }
    
                //!!! updating 
                running_index_prev = running_index;
                running_index -= step_length;
                step_num ++;
            }
            
            //! out ot the while loop meanning we either reached an equal value ..(1)
            //! or a smaller value ..(2)
            //! or running index out of bound ..(3)
            //(3)
            if(running_index<0){
                //! interval : [0,running_index_prev]
                return helper(arr, value, 0, running_index_prev, step_num);
            }

            // (1)
            if(arr[running_index] == value) {
                return new int[]{running_index,step_num+1};
            } 
            // (2): starting running in reverted diretion
            else{
                //! 2.3.(2)
                //! the running index now is the left boundary for the interval!!
                //! the the running index in the right round is the left boudary since it points to the last entry which is greater than value
                interval_right = running_index_prev;
                interval_left = running_index;

                // using binary search as Artemis requires
                return helper(arr, value, interval_left, interval_right, step_num);
            }
        }
        






        
    }
    
    /**
     * compare the probalistic search and binary search 
     * <p> search all elements between $(min) and $(max), both inclusive, using these 2 algorithms
     * , and print out respectively for binary search and probalistic search
     * <p>
     * 1. the maximal step number<p>
     * 2. searched element for this maximal step number<p>
     * 3. the sum of all step numbers<p>
     * 
     * @param arr
     * @param min
     * @param max
     */
    public static void compareApproaches(int[] arr, int min, int max) {
         //* 1. binary search
         {
            // preparations
            long sum_step_nums=0;
            int value_when_max_step_num = 404;
            long max_step_num = -1;

            for (int i = min; i < max+1; i++) {
                // get the step number when search $(i) then add it to sum
                long step_num = find(arr, i)[1];
                sum_step_nums += step_num;

                //! if step_num is greater than $(max_step_num): update
                if(step_num>max_step_num){
                    max_step_num = step_num;
                    value_when_max_step_num = i;
                }
            }

            //* print result
            System.out.println("Binäre Suche:");
            System.out.println("Maximale Anzahl an Aufrufen:");
            System.out.println(max_step_num);
            System.out.println("Wert bei dem die maximale Anzahl an Aufrufen auftritt:");
            System.out.println(value_when_max_step_num);
            System.out.println("Anzahl der gesamten Aufrufe:");
            System.out.println(sum_step_nums);
        }

        //* 2. probalistic search
        {
            // preparations
            long sum_step_nums=0;
            int value_when_max_step_num = 404;
            long max_step_num = -1;

            for (int i = min; i < max+1; i++) {
                // get the step number when search $(i) then add it to sum
                long step_num = probalisticSearch(arr, i)[1];
                sum_step_nums += step_num;

                //! if step_num is greater than $(max_step_num): update
                if(step_num>max_step_num){
                    max_step_num = step_num;
                    value_when_max_step_num = i;
                }
            }

            //* print result
            System.out.println("Probabilistische Suche:");
            System.out.println("Maximale Anzahl an Aufrufen:");
            System.out.println(max_step_num);
            System.out.println("Wert bei dem die maximale Anzahl an Aufrufen auftritt:");
            System.out.println(value_when_max_step_num);
            System.out.println("Anzahl der gesamten Aufrufe:");
            System.out.println(sum_step_nums);
        }
        
        
    }

    public static void main(String[] args) {
        /*
        // Not part of the exercise but can be helpful for debugging purposes
         int[] arr = new int[]{6, 20, 22, 35, 51, 54, 59, 74, 77, 80, 87, 94, 97};
         int tmp [] = probalisticSearch(arr, 22);
         System.err.println(tmp[1]);

         //compareApproaches(arr, -10, 10);

         int arr1[] = new int[]{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,100};
         int tmp1[] = probalisticSearch(arr1, 3);
         //System.out.println(tmp1[0]+" "+tmp1[1]);
         */
        int n;
        int x, t;
        n = readInt();
        t = n / 2 + 1;
        x = t + 2;
        while (1 < x - t) {
            x = t;
            t = ((n / x) + x) / 2;
        }
        if (t * t <= n)
            x = t;
        else
            x = x - 2;
        write(x);
    }
}
