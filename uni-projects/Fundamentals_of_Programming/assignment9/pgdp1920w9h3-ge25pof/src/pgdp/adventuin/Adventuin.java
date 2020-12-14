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

package pgdp.adventuin;
import pgdp.color.*;
public class Adventuin {
    //* private attrs
    final private String name;
    final private int height;
    final private RgbColor color;
    final private HatType hatType;
    final private Language language;

    //* getters
    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public RgbColor getColor() {
        return color;
    }

    public HatType getHatType() {
        return hatType;
    }

    public Language getLanguage() {
        return language;
    }



    //* constructor
    public Adventuin(String name, int height, RgbColor color, HatType hatType, Language language) {
        this.name = name;
        this.height = height;
        this.color = color;
        this.hatType = hatType;
        this.language = language;
    }

    //* public functions
    @Override
    public String toString() {
        return "Adventuin{" +
                "name='" + name + '\'' +
                ", height=" + height +
                ", color=" + color +
                ", hatType=" + hatType +
                ", language=" + language +
                '}';
    }
}
