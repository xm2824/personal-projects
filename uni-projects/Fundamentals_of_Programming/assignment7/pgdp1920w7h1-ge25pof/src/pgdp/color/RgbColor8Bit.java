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

 package pgdp.color;
 
 /**
  * RgbColor8Bit: subclass of RgbColor, with the attribute $(bitsDepth) fixed = 8
  */
 public class RgbColor8Bit extends RgbColor{
    
    //* constructor
    public RgbColor8Bit(int red, int green, int blue)
    {
        super(8, red, green, blue);
    }

    /**
     * override superclass function to avoid unecessary object creation
     */
    @Override
    public RgbColor8Bit toRgbColor8Bit() {
        return this;
    }
     
 }