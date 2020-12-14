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
  * RgbColor: describes a rgb value with attrs: R, G, B, bitDepth
  */
 public class RgbColor {
     //* private and final attrs
     //* bitDepth: denotes how many bits are used to store a single rgb value, in (0,31]
     private final int r,g,b;
     private final int bitDepth;   

     //* constructor
     /**
      * creates a RgbColor object with given parameters <p>
      * each rgb value shoulbe be in [0,2^$(bitDepth)-1]
      * @param bitDepth must be in (0,31]
      * @param red
      * @param green
      * @param blue
      */
     public RgbColor(int bitDepth, int red, int green, int blue){
         //* 1. first check if the given bitDepth is allowed
         {
             // if not valid
             if(bitDepth<=0 || bitDepth>31){
                 // throw the exception
                 ExceptionUtil.unsupportedOperation("the bitDepth must be between 0(exclusive) and 31(inclusive)\n");
             }
         }
         
         //* 2. if valid bitDepth, then check if the given rgb values are valid
         //* i.e. if they are in [0,2^$(bitDepth)-1]
         {
            int upperbound = IntMath.powerOfTwo(bitDepth)-1;
            int lowerbound = 0;

            // iterate 3 rgb values
            int rgbs[] = {red,green,blue};
            for (int i : rgbs) {
                // if out of bound
                if(i<lowerbound || i >upperbound){
                    ExceptionUtil.unsupportedOperation("the RGB value is out of bound: "+i+"\n");
                }
            }
         }

         //* 3. if all valid: assigning
         this.bitDepth = bitDepth;
         r=red;
         g=green;
         b=blue;
     }

     //* getters
    public int getRed() {
        return this.r;
    }

    public int getGreen() {
        return this.g;
    }

    public int getBlue() {
        return this.b;
    }

    public int getBitDepth() {
        return this.bitDepth;
    }


    ///////////////////////////////////////////////// private functions ///////////////////////////////////////////////////////////////////////


    ///////////////////////////////////////////////// public functions ///////////////////////////////////////////////////////////////////////

    /**
     * convert this object to a RgbColor*Bit, (returns a new object)<p>
     * According the rules on Artemis 
     * @return
     */
    public RgbColor8Bit toRgbColor8Bit(){
        //! valid value domain of rgb values: [0,2^8-1]
        int lowerbound = 0;
        int upperbound = 255;

        //! Case of the current bits depth:
        //* 1. == 8 
        if(bitDepth==8){
            // then just return a new obj
            return new RgbColor8Bit(r, g, b);
        }

        //* 2. > 8
        else if (bitDepth>8){
            //* struct for rgb
            int rgbs[]={r,g,b};
            
            //* diff
            int diff = bitDepth - 8;

            // 1. first divide each rgb value by 2^(difference-1)
            for (int i = 0; i < 3; i++) {
                rgbs[i] = rgbs[i]>>(diff-1);
            }

            // 2. store the last bit
            int last_bits[]={0,0,0};
            for (int i = 0; i < 3; i++) {
                last_bits[i] = rgbs[i]%2;
            }

            // 3. divide rbs[i] by 2 again, and add the lastbits to them
            for (int i = 0; i < 3; i++) {
                rgbs[i] /= 2;
                rgbs[i] += last_bits[i];
            }

            //! 4. check if the rgbs are out of bound
            for (int i = 0; i < 3  ; i++) {
                // if less than lowerbound, set it to lowerbound
                if(rgbs[i]<lowerbound) rgbs[i] = lowerbound;

                // if greater than upperbound, set it to upperbound
                else if (rgbs[i]>upperbound) rgbs[i] = upperbound;
            }

            // 5. return a new obj
            return new RgbColor8Bit(rgbs[0], rgbs[1], rgbs[2]);
        }

        //* 3. < 8
        else{
            //!!! rules:
            //!!! (unformal): texturally repeating until 8 bits
            //!!! example:
            //!!! red: 10 for 2 bits    ==>     10101010 for 8 bits

            // 0. preparaitons
            // 0.1: rgbs for later returning and the originals
            int rgbs[] = {r,g,b};
            final int template[] = {r,g,b};

            // 0.2: the maximal complete repeating number
            int com_num = 8/bitDepth-1;

            // 0.3: the remaining bits after all complete repeating
            int rem_bits = 8 % bitDepth;

            // 1. the ealier complete repeating:
            // for each rgb
            for (int i = 0; i < 3; i++) {
                //$(com_num) times
                for (int j = 0; j < com_num; j++) {
                    //! first shift to the left by $(bitDepth)
                    rgbs[i] = rgbs[i]<<bitDepth;

                    //! then add r/g/b/ to itself
                    rgbs[i] += template[i];
                    
                }
            }

            // 2. the later remaining bits:
            // for each rgb
            for (int i = 0; i < 3; i++) {
                //! first shift to the left by $(rem_bits)
                rgbs[i] = rgbs[i] << rem_bits;

                //! add the remaining bits of the original rgbs to it
                int remaining = template[i] >> (bitDepth-rem_bits);
                rgbs[i] += remaining;
            }

            //! 3. check if valid
            for (int i = 0; i < 3  ; i++) {
                // if less than lowerbound, set it to lowerbound
                if(rgbs[i]<lowerbound) rgbs[i] = lowerbound;

                // if greater than upperbound, set it to upperbound
                else if (rgbs[i]>upperbound) rgbs[i] = upperbound;
            }

            // 4. return
            return new RgbColor8Bit(rgbs[0], rgbs[1], rgbs[2]);
        }

    }


     
 }