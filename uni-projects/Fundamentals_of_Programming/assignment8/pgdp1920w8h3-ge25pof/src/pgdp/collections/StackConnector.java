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

public class StackConnector<T> implements DataStructureConnector<T> {
    //* attr
    private Stack<T> inner_stack;

    //* constructor
    public StackConnector(Stack<T> stack){
        //!!! if stack is null: bad argu
        if(stack == null) ExceptionUtil.illegalArgument("stack is null");
        inner_stack = stack;
    }

    @Override
    public boolean hasNextElement() {
        return !inner_stack.isEmpty();
    }

    @Override
    public void addElement(T item) {
        inner_stack.push(item);
    }

    @Override
    public T removeNextElement() {
        return inner_stack.pop();
    }
}
