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

package pgdp.strings;
/**
 * MyString: representing a string with a char array<p>
 * The big difference from the normal string is it's also of a list structure, i.e. its instance is an entry of a string list
 */
public class MyString {
    //* private attrs
    private char[] data;
    private MyString next;      //! null if the last entry of the list 
 
    //* constructor
    public MyString(char[] data) {
        // assuming data is not null
        this.data = data;
        next = null;
    }

    //* getters
    public char[] getData() {
        return this.data;
    }

    public MyString getNext() {
        return this.next;
    }

    /////////////////////////////////////////////////////// private (helper) functions //////////////////////////////////////////////////////////////////////////
    /**
     * return the length of this string (not the list of string)
     * @return
     */
    private int _length(){
        return data.length;
    }
    

    /////////////////////////////////////////////////////////// public functions /////////////////////////////////////////////////////////////////////////////
    /**
     * return the sum of lengths of all the strings in this list
     * @return
     */
    public int length() {
        // sum var
        int sum = this._length();

        // tmp MyString pointer for the iteration
        MyString tmp =next;

        // loop until the end of list
        while (tmp!=null) {
            sum += tmp._length();
            tmp = tmp.next;
        }

        // out of the while loop means over
        return sum;
    }
 
    /**
     * concatenate the MyString object constructed with the given char array (calling its constructor) at the end of this list
     * @param data
     */
    public void concat(char[] data){
        //* construct a MyString obj with the given data
        //* assume data is not null
        MyString new_MyString = new MyString(data);

        //* iterate until the last entry in this list
        MyString tmp = this;
        while (tmp.next!=null) {
            tmp = tmp.next;
        }

        //! out of the while loop meaning tmp.next is null => tmp is the last entry in this list
        //! i.e. we should append the new_MyString to it
        tmp.next = new_MyString;
    }

    /**
     * return string form, constructed with the constructor String(char[] value) of java.lang.String
     */
    @Override
    public String toString(){
        //! idea:
        //! first get the whole length of the list using length()
        //! then copy all the char to a tmp char array
        //! then create a string with this array

        //* length of list
        int len = length();

        //* tmp char array
        char tmp [] = new char[len];

        //* copy all the chars
        int pointer = 0;        // pointing the next free slot in the tmp array
        MyString temp_mMyString = this;
        while (temp_mMyString != null) {
            // copying the data of temp_mMyString
            for (char c : temp_mMyString.data) {
                tmp[pointer++] = c;
            }

            // updating
            temp_mMyString = temp_mMyString.next;
        }

        //* after copying, return a new String obj
        return new String(tmp);
    }

    /**
     * comparing if the 2 lists containg the same sequence of characters
     * @param other
     * @return true if all the same;    false if not or $(other) is null
     */
    public boolean equals(MyString other){
        //* if $(other) is null
        if(other == null) return false;

        //* otherwise comparing if the 2 lists are all the same
        //* 1. first comparing if the list length is the same
        // if list lengths already differ
        if(this.length()!=other.length()) return false;


        //* 2. otherwise comparing the characters one by one
        //* Since we can't copy any chars in this assignment,
        //* we need compare the chars in place, which is a little bit tricky,
        //! since the entries of the lists could have different length itself
        //! but the characters could be the same at the end
        //! WE NEED SOME GOOD PREPARATIONS

        //* 2.1 preparations
        int index = 0;                                      // the global index for the whole length of the list
        MyString local_string1=this;                        // the current local string in the list1
        MyString local_string2=other;                       // the current local string in the list2
        int local_length1=this.data.length;                 // the individual length of $(local_string1)
        int local_length2=other.data.length;                // the individual length of $(local_string2)
        int local_ptr1=0;                                   // the local pointer for the local_length1
        int local_ptr2=0;                                   // the local pointer for the local_length2
        char local_data1[]=this.data;                       // local data of list1
        char local_data2[]=other.data;                      // local data of list2
        
        // while index < the whole length
        while (index < this.length()) {

            //* (1): comparing the current chars in place
            // if not equal already =: false
            if(local_data1[local_ptr1]!=local_data2[local_ptr2]) return false;

            //* (2): updating
            local_ptr1++;
            local_ptr2++;
            index ++;

            //! adjusting:
            //! 1. if local pointer1 runns out of bound => we should go to the next MyString obj
            if(local_ptr1==local_length1 && local_string1.next !=null){
                local_string1 = local_string1.next;
                local_data1 = local_string1.data;
                local_length1 = local_data1.length;
                //!!! reset local pointer 
                local_ptr1 = 0;
            }

            //! 1. if local pointer2 runns out of bound => we should go to the next MyString obj
            if(local_ptr2==local_length2&&local_string2.next!=null){
                local_string2 = local_string2.next;
                local_data2 = local_string2.data;
                local_length2 = local_data2.length;
                //!!! reset local pointer 
                local_ptr2 = 0;
            }

        }

        //* out of the while loop meaning all the chars are the same => return true
        return true;
     }

    /**
     * return the index of the first occurence of the given char $(c) or -1 if no occurence
     * @param c
     * @return
     */
    public int indexOf(char c){
        //* preparations
        MyString local_string1=this;                        // the current local string in the list1
        int local_length1=this.data.length;                 // the individual length of $(local_string1)
        int local_ptr1=0;                                   // the local pointer for the local_length1
        char local_data1[]=this.data;                       // local data of list1


        //* iteraing the list to find the first occurence of c
        for (int i = 0; i < this.length(); i++) {

            //* (1): comparing the current chars in place
            // if found: return its index
            if(local_data1[local_ptr1]==c) return i;

            //* (2): updating
            local_ptr1++;

            //! adjusting:
            //! 1. if local pointer1 runns out of bound => we should go to the next MyString obj
            if(local_ptr1==local_length1 && local_string1.next !=null){
                local_string1 = local_string1.next;
                local_data1 = local_string1.data;
                local_length1 = local_data1.length;
                //!!! reset local pointer 
                local_ptr1 = 0;
            }
        }

        //* out of the for loop meaning there is no 'c' in the list
        return -1;
    }
    

    /**
     * return the index of the last occurence of the given char $(c) or -1 if no occurence
     * @param c
     * @return
     */
    public int lastIndexOf(char c) {
        //* preparations
        MyString local_string1=this;                        // the current local string in the list1
        int local_length1=this.data.length;                 // the individual length of $(local_string1)
        int local_ptr1=0;                                   // the local pointer for the local_length1
        char local_data1[]=this.data;                       // local data of list1
        //!!! we need a tmp variable to store the pretential last index !!!
        int pretential_last_index = -1; 

        //* iteraing the list to find the first occurence of c
        for (int i = 0; i < this.length(); i++) {

            //* (1): comparing the current chars in place
            // if found: store it to $(pretential_last_index)
            if(local_data1[local_ptr1]==c) {
                pretential_last_index = i;
            }

            //* (2): updating
            local_ptr1++;

            //! adjusting:
            //! 1. if local pointer1 runns out of bound => we should go to the next MyString obj
            if(local_ptr1==local_length1 && local_string1.next !=null){
                local_string1 = local_string1.next;
                local_data1 = local_string1.data;
                local_length1 = local_data1.length;
                //!!! reset local pointer 
                local_ptr1 = 0;
            }
        }

        //* out of the for loop: just return $(pretential_last_index) since it's initially -1, so if no occurence => no assignment => it's still -1
        return pretential_last_index;
    }

    /*
    public static void main(String[] args) {
        MyString str1 = new MyString(new char[]{'a','b','c','d'});
    str1.concat(new char[]{'e','f'});
    //System.out.println(str1.next);
    MyString str2 = new MyString(new char[]{'a','b','c','d','e','f'});
    boolean a=str1.equals(str2); // true 
    System.out.println(a);
    }
    */
    
}
