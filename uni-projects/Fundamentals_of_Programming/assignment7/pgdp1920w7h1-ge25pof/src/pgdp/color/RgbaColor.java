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
  * RgbaColor: subclass of RgbColor, with an extra attribute: opacity  
  */
 public class RgbaColor extends RgbColor {
    //* one more attribute: opacity
    //* should be in [0,2^$(bitDepth)-1]
    private final int opacity;

    //* constructor
    public RgbaColor(int bitDepth, int red, int green, int blue, int alpha){
        super(bitDepth, red, green, blue);

        //! if program reaches here, meaning the 4 arguments above are valid
        //! Need to check alpha though
        // if opacity not in [0,2^$(bitDepth)-1]:
        if(alpha<0 || alpha >(IntMath.powerOfTwo(bitDepth)-1)){
            ExceptionUtil.unsupportedOperation("Alpha(opacity) is out of bound: "+alpha+"\n");
        }

        opacity = alpha;
    }        

    //* getters
    public int getAlpha(){
        return opacity;
    }

    /**
     * convert the object to 8 bit according to the rules on artemis
     */
    @Override
    public RgbColor8Bit toRgbColor8Bit() {
        
        int upperbound = (1<<getBitDepth())-1;

        //* if alpha  != upperbound
        if(upperbound != getAlpha() ){
            ExceptionUtil.unsupportedOperation("alpha is not maximal: "+getAlpha()+"\n");
        }

        //* else
        return super.toRgbColor8Bit();
    }
 }