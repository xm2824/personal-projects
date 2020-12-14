package pgdp.lists;

public class GamesWithLists {
  /**
   * reunite an array of lists which are assumed sorted ascendingly, the resulting list is also required to be monotonically increasing
   * <p>
   * The implementation is similar to the mering phase of merge sort
   * @param arrayOfLists
   * @return
   */
  public static IntDoubleList reuniteLists(IntDoubleList[] arrayOfLists) {
    //!!! similar to the merging phase of merge sort, we need $(arrayOfLists.length) pointers pointing
    //!!! the remaining min. element in each list
    // int pointers[] = new int [arrayOfLists.length];     //? this array should be initially {0,...,0}
    // replace [] with list
    IntDoubleList pointers = new IntDoubleList();
    for (int i = 0; i < arrayOfLists.length; i++) {
      pointers.append(0);
    }

    //* the returning list obj
    IntDoubleList ret = new IntDoubleList();

    //* preparations
    int size_of_arr = arrayOfLists.length;
    //! extrally to the normal merging of merge sort:
    //! Since we have an array of length probably larger than 2, we have case that
    //! some pointers are finished (meaning they point out of bound of the list), while others are not
    //! In such case, we should skip those finished lists and compare the others
    //! So I think we need an array to record if the pointer is finished or not
    // boolean finished_list[] = new boolean[size_of_arr];
    // replace [] with list
    IntDoubleList finished_list = new IntDoubleList();
    for (int i = 0; i < arrayOfLists.length; i++) {
      finished_list.append(0);
    }

    //* start merging
    //* iterating all the lists to find the top minimum among them
    //! breaking the loop when all pointers are finished
    while (true) {
      // 1. all finished?
      boolean all_finished = true;
      {
        for (int i = 0; i < size_of_arr; i++) {
          all_finished = all_finished && finished_list.get(i)==1;

          // if not all finished already, break
          if(!all_finished) break;
        }
      }

      // 1.1 if all finished: we are done
      if(all_finished) break;

      // 1.2 else: continue to merge
      else {
        //* 2. iterating all the lists to find the top minimum among them, skipping the finished list
        // tmp variable to store the temporary min
        int tmp_min = 9999;
        // tmp pointer pointing the list which contains $(tmp_min)
        int list_of_min = 404;
        {
          for (int i = 0; i < size_of_arr; i++) {
            //! if this list is empty, i.e. head == null: this list is finished
            if(arrayOfLists[i].size()==0) finished_list.set(i, 1);;

            //! if this list is finished: continue
            if(finished_list.get(i)==1) continue;

            //! otherwise: comparing the tmp_min with the top element of this list (pointed by pointer)
            // if tmp_min is smaller or equal: continue
            if(tmp_min<= arrayOfLists[i].get(pointers.get(i))) continue;

            // else: updating tmp_min and the pointer to its belonging list
            else {
              tmp_min = arrayOfLists[i].get(pointers.get(i));
              list_of_min = i;
            }
          }
        }

        //* 3. out of the iterations of all the lists meaning we have found the current min, append it to $(ret)
        //*    And we need to inc the pointer 
        ret.append(tmp_min);
        pointers.set(list_of_min, pointers.get(list_of_min)+1);
        //!!! check if this pointer is finished !!!
        if(pointers.get(list_of_min) == arrayOfLists[list_of_min].size()) finished_list.set(list_of_min, 1);
      }
    }

    //* 4. out of the while loop meaning all lists are finished, return ret
    return ret;
  }


  /**
   * returning a new list that partitions the given list with the given value as "boundary line".<p>
   * i.e. all list elements that is less than $(value) stand before the elements that are greater equal $(value),
   * <p>Within the both partitions the ordering of elements should remain unchanged.
   * @param list
   * @param value
   * @return
   */
  public static IntDoubleList partTheList(IntDoubleList list, int value) {
    //!!! idea:
    //!!! iterating the given list only once, appending the smaller value to tmp_list1, the larger or equal value to tmp_list2
    //!!! Then reunite these 2 lists
    
    //* tmp lists
    IntDoubleList tmp1,tmp2;
    tmp1 = new IntDoubleList();
    tmp2 = new IntDoubleList();

    //* iterating only once
    for (int i = 0; i < list.size(); i++) {
      // smaller value:
      if(list.get(i)<value) tmp1.append(list.get(i));

      // larger or equal value:
      else tmp2.append(list.get(i));
    }

    //* reunite the 2 tmp lists and return
    return reuniteLists(new IntDoubleList[]{tmp1,tmp2});
  }


  /**
   * return a new list using the mixing rule on Artemis for the given list
   */
  public static IntDoubleList mixedList(IntDoubleList list) {
    
    //!!! idea:
    //!!! using 2 pointers: front_ptr and back_ptr which iterate respectively from the front and end of the list to the middle,
    //!!! adding respectively (to be exact: first add element pointed by front_ptr, then element pointed by back_ptr)
    //!!! repeating until the new list has equal size with the given list: no need to care about the odd/even issues...

    //* the 2 pointers
    int front_ptr = 0;
    int back_ptr = list.size()-1;

    //* the new list
    IntDoubleList ret = new IntDoubleList();

    //* loop until the 2 lists have equal size
    while (true) {
      // 1. first adding the front element and inc the pointer
      ret.append(list.get(front_ptr++));

      //! check if equal size
      if(ret.size()==list.size()) break;

      // 2. then adding the back element and dec the pointer
      ret.append(list.get(back_ptr--));

      //! check if equal size
      if(ret.size()==list.size()) break;
    }

    //* out of the while loop meaning 2 lists have equal size: done
    return ret;
  }
}
