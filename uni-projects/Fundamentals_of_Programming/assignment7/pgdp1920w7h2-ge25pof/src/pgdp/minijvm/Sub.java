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
 * Sub
 */
public class Sub  extends Instruction{

/////////////////////////////////////////////////// private functions ///////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////// public functions ////////////////////////////////////////////////////////////////////////

    /**
     * pop 2 elemens, let the top-first is a, the top-second is b, push (b-a) 
     */
    @Override
    public void execute(Simulator simulator) {
         //!!! make sure the stack has at least 2 elements, i.e. SP must >= 1
         if(simulator.getStack().getStackPointer()<1){
             try {
                 throw new Exception("stack has not enough elements, by instruction Sub at "+(simulator.getProgramCounter()-1));
             } catch (Exception e) {
                 e.printStackTrace();
             }
         }

        //* pop 2 elemens, let the top-first is a, the top-second is b, push (b-a)
        Stack s = simulator.getStack(); // for laze
        int a = s.pop();
        int b = s.pop();
        s.push(b-a);
        
    }
    
}