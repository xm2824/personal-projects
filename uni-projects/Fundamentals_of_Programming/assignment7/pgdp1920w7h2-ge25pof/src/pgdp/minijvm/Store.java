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

package pgdp.minijvm;
/**
 * Store
 */
public class Store extends Instruction {
    //* attrs
    private int i;

    //* constructors
    public Store(int i) {
        this.i = i;
    }


/////////////////////////////////////////////////// private functions ///////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////// public functions ////////////////////////////////////////////////////////////////////////

    /**
     * pop the top element on stack and writes it to the $(i) cell of stack
     */
    @Override
    public void execute(Simulator simulator) {
        Stack s = simulator.getStack();

        //! make sure the stack has at least 1 element, i.e. sp must >= 0
        if(s.getStackPointer()<0){
            try {
                throw new Exception("stack has not enough elements, by instruction Store at"+(simulator.getProgramCounter()-1));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //* pop the top element on stack and writes it to the $(i)th cell of stack
        int top = s.pop();
        s.setValueAtIndex(i, top);
        
    }

    
}