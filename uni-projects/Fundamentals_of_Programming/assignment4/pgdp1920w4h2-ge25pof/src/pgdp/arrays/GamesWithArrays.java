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

public class GamesWithArrays {

  /*
  public static void main(String[] args) {
    // arr1 = {2,3,1,3,2,4,6,7,9,2,19}, arr2 = {2,1,4,3,9,6}
    int arr1[] = new int[]{2,3,1,3,2,4,6,7,9,2,19};
    int arr2 []= new int[]{2,1,4,3,9,6};
    otherSort(arr1, arr2);
  }
  */

  /**
   * helper function to check if arr contains num
   * @param arr
   * @param num
   * @return true if contains
   */
  private static Boolean array_contains(int arr[],int num) {
    for (int i = 0; i < arr.length; i++) {
      if(arr[i] == num) return true;
    }
    return false;
  }

  /**
   * helper function to return the sum of an array
   * @param arr
   * @return sum of arr
   */
  private static int sum_of_array(int arr[]) {
    int sum = 0;
    for (int i : arr) {
      sum += i;
    }
    return sum;
  }

  /**
   * sort arr1 so that the ordering of elements in arr1 is the same as that in arr2,
   * all elements in arr2 are also in arr1. There is no duplications in arr2, but possible duplications in arr1.
   * There are also elements that only apear in arr1, but not in arr2, these elements should be placed at the end of the sorted array,
   * and their ordering should remain unchanged
   * @param arr1 array to sort
   * @param arr2 template array
   * @return sorted array
   */
  public static int[] otherSort(int[] arr1, int[] arr2) {
    //* initialize the array to be return on heap, its length is equal to arr1's
    int ret [] = new int[arr1.length];

    //* an array to store the multiple times for each element of arr2 in arr1
    int counts[] = new int[arr2.length];  //? why this is allocated on heap?

    // count each elements of arr2 in arr1
    for (int i = 0; i < arr2.length; i++) {
      int temp = arr2[i];
      int count = 0;
      for (int j = 0; j < arr1.length; j++) {
        if(temp == arr1[j]) count++;
      }
      // store the count in counts
      counts[i] = count;
    }

    //* sort arr1 according to the ordering of arr2 and the counts
    // an index to store the position of the first free slot in arr1
    int index = 0;

    // for each elem in arr2
    for (int i = 0; i < arr2.length; i++) {
      int temp = arr2[i];
      int count = counts[i];

      // $(count) times duplications
      // j = index to insert
      for (int j = index; j < index+count; j++) {
        ret[j] = temp;
      }

      // increase the offset
      index += count;
    }

    //* there are still elems in arr1 but not in arr2, place them at the end of ret
    // iterate arr1
    for (int i = 0; i < arr1.length; i++) {
      int temp = arr1[i];

      // if temp is not in arr2
      if(!array_contains(arr2,temp)){
        // then insert it at the end of ret, at which $(index) points, and inc index
        ret[index++] = temp;
      }
    }

    //* return the sorted
    return ret;
  }
  /**
   * exchange one element of arr1 and arr2 so that after exchanging the sum of 
   * these 2 arrays equal
   * @param arr1 
   * @param arr2
   * @return an array of length 2: {element of arr1, element of arr2}, i.e. elements of each array to exchange
   */
  public static int[] fairFriends(int[] arr1, int[] arr2) {
    //* idea: list all possible combinations of elements in arr1 and elements in arr2,
    //* find the first one that fits the rule.
    //* for this we need
      // 1. sum of arr1 and arr2
      int sum_arr1 = sum_of_array(arr1);
      int sum_arr2 = sum_of_array(arr2);

      // 2. (e1,e2) to store the combinations
      // e1: elem of arr1
      // e2: elem of arr2

      // 3. boolean function to determine if (e1,e2) passes
      // boolean feasible = (sum_arr1 - e1 + e2) == (sum_arr2 -e2 + e1) 
      // evaluated at runtime

      //* iterate all possible combinations until the first feasible one.
      for (int e1 : arr1) {
        for (int e2 : arr2) {
          boolean feasible = (sum_arr1 - e1 + e2) == (sum_arr2 -e2 + e1);

          // if feasible, return it
          if(feasible){
            return new int[]{e1,e2};
          }
        }
      }

    
    //* since the Aufgabenstellung says we should have at least one possible combination, so I don't deal with 
    //* the case of no possible combinations


    return new int[]{};
  }


  /**
   * check if the given array is an alpen array.
   * Alpen array is an array whose elements first monotonically increase then monotonically decrease
   * So the length of alpen array must be greater equal 3. 
   * @param arr
   * @return true if arr is alpen array
   */
  public static boolean alpen(int[] arr) {
    //* 1. alpen array must have at least 3 elements
    if(arr.length<3) return false;

    //* 2. and it must first monotonically increase until an maximum
      // 2.1 first find the index of the maximum of arr
      int index2max = 0;
      for (int i = 1; i < arr.length; i++) {
        if(arr[index2max] <arr[i]) index2max = i;
      }

      // 2.2 check if arr is monotonically increasing in interval [0,index2max-1]
      //! it's possible that the index of maximum is 0 or length-1, e.g. array [3,3,3], [1,2,3] or [3,2,1], which are not alpen arrays
      if(index2max == 0|| index2max == arr.length-1) return false;  
      
      boolean fts_inc_mono = true;
      for (int i = 0; i < index2max; i++) {
        fts_inc_mono &= (arr[i]<arr[i+1]);
      }

      // return false if not first monotonically increasing
      if(!fts_inc_mono) return false;

    //* 3. then it must monotonically decrease in interval [index2max,arr.length-1]
    boolean then_dec_mono = true;
    for (int i = index2max; i < arr.length-1; i++) {
      then_dec_mono &= (arr[i]>arr[i+1]);
    }

    //* return results
    return (fts_inc_mono && then_dec_mono);

    
  }


  /**
   * find the day to buy plankton and the day to sell it so that the profit is maximal,
   * the day to buy should be before the day to sell;
   * profit = selling price - buying price;
   * days starts at day 0;
   * return (buyingday,sellingday,profit) of the maximal protit
   * @param arr array of day-prices in the following days
   * @return array of length 3 {buyingday,sellingday,profit}
   */
  public static int[] plankton(int[] arr) {
    
    //* array to return, {0,0,0} means there is no profit at all
    int ret [] = new int[]{0,0,0};


    //* iterate all possible tuples of (b,s) to find maximal profit
    // 2 pointers:
    // b : buying day
    // s : selling day, starts with b+1
    for (int b = 0; b < arr.length-1; b++) {
      // buying price
      int buy_price = arr[b];
      
      // for each possible selling days
      for (int s = b+1; s < arr.length; s++) {
        // if selling price is smaller than buying price: continue
        if(arr[s] <= buy_price) continue;

        // else comparing the current profit
        else {
          // if current profit is greater or equal: contine
          if(ret[2] >= (arr[s]-buy_price)) continue;

          // else store the new combination
          else {
            ret = new int[]{b,s,arr[s] - buy_price};
          }
        }
      }
    }

    //* return the result
    return ret;
  }
  

  /**
   * Given an array $(arr) consist of integers between 0 and 100, both exclusive.
   * Check if the equal numbers in arr can be distributed to groups with equal size and have at lease 2 elements 
   * @param arr
   * @return the biggest group size for this to work, 0 if no possible group size available
   */
  public static int pinguinFreunde(int[] arr) {
    
    //* if the arr is empty
    if(arr.length==0) return 0;

    //* an array of size 100 to store the count of equal numbers
    int counts []= new int[100];

    // iterate %(arr) to count the elements
    for (int i : arr) {
      counts[i] ++;
    }

    //* 1. all the group have at least 2 elements
        // first find the smallest count in counts, which is the potential valid group size
        int group_size = 2147483647 ; 
        for (int i : counts) {
          if(i>0 && i < group_size) group_size = i;
        }

        // if the group size is less then 2
        if(group_size<2) return 0;
      
    //* 2. all the group have the same size
    //*    means the count of each number in $(arr) must be a multiple of $(group_size)
    for (int i : counts) {
      // skip 0 count
      if(i==0) continue;

      // if not multiple of group_size, then not valid
      if(i % group_size != 0) return 0;
    }

    //* the maximal group size exits!
    return group_size;
        
        
    

    
  }
}
