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
import pgdp.color.RgbColor;
import pgdp.color.RgbColor8Bit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;


public final class AdventuinParty {
    //* public static functions

    /**
     * group the given {@code adventuin}s by their {@code HatType}
     * @param adventuins
     * @return map: hat type => list of adventuins, the map contains only the {@code HatType}s, which at least one {@code Adventuin} wears
     */
    public static Map<HatType,List<Adventuin>> groupByHatType(List<Adventuin> adventuins) {
        //!!! if the given list is empty
        if(adventuins.isEmpty()){
            return new HashMap<>();
        }

        //* the to return Map
        HashMap<HatType,List<Adventuin>> ret = new HashMap<>();

        //* iterate all adventuins
        adventuins.stream().forEach(adv->{
           // if the hattype of this adv is already in the map
           if(ret.containsKey(adv.getHatType())){
               // add this adv to the corresponding list of adventuins
               ret.get(adv.getHatType()).add(adv);
           }

           // otherwise add a new key
            else{
                List<Adventuin> newList = new ArrayList<>();
                newList.add(adv);
                ret.put(adv.getHatType(),newList);
           }

        });

        //* return
        return ret;    
    }

    
    /**
     * call {@code getLocalizedChristmasGreeting} for each {@code adventuin} in the given list in an ascending order w.r.t the height of the {@code adventuin}
     * <p>
     * i.e. the shortest {@code adventuin} is the first.
     * @param adventuins
     */
    public static void printLocalizedChristmasGreetings(List<Adventuin> adventuins){
        //!!! if the given list is empty
        if(adventuins.isEmpty()){
            return;
        }

        //* iterate all adventuins using stream, sorted w.r.t. the height ascendingly
        adventuins.stream().sorted((o1,o2) -> o1.getHeight()-o2.getHeight()).forEach(ad->System.out.println(ad.getLanguage().getLocalizedChristmasGreeting(ad.getName())));

    }

    /**
     * returns a map from the height of the adventuin to the average color brightness
     * <p>
     * the height should be rounded to the nearst whole number
     * <p>
     * brightness(R,G,B) =  0.2126∗R+0.7152∗G+0.0722∗B)/255
     * @param adventuins
     * @return
     */
    public static Map<Integer,Double> getAverageColorBrightnessByHeight(List<Adventuin> adventuins) {
        //!!! if the given list is empty
        if(adventuins.isEmpty()){
            return new HashMap<>();
        }

        //* the to return map
        Map<Integer,Double> ret = new HashMap<>();
        Map<Integer,List<Double>> height_2_list_of_brightness = new HashMap<>();     // maps the height to the list of all brightness

        //* iterate all adventuins using stream, add all the brightness for one height to the mapped list
        adventuins.stream().forEach(adv->{
            // 0. first round adv's height
            // the unit digit and rounded
            int unit_digit = adv.getHeight()%10;
            int rounded;
            
            // 0.1 if the unit digit is in [0,4]
            if(unit_digit<=4){
                rounded = adv.getHeight()-unit_digit;
            }

            // 0.2 else in [5,9]
            else{
                rounded = adv.getHeight() + (10-unit_digit);
            }

            
            //!!! rgb in 8 bits !!!
            RgbColor8Bit rgb = adv.getColor().toRgbColor8Bit();

            // 1.0 if the rounded height is already contained in the map
            if(height_2_list_of_brightness.containsKey(rounded)){
                // then add the brightness to the list
                // brightness(R,G,B) =  0.2126∗R+0.7152∗G+0.0722∗B)/255
                double brightness = (0.2126 * rgb.getRed()+0.7152 * rgb.getGreen()+0.0722*rgb.getBlue())/255;
                height_2_list_of_brightness.get(rounded).add(brightness);
            }

            // 1.1 else create a new pair in the map
            else{
                ArrayList<Double> newlist = new ArrayList<Double>();
                newlist.add((0.2126 * rgb.getRed()+0.7152 * rgb.getGreen()+0.0722*rgb.getBlue())/255);
                height_2_list_of_brightness.put(rounded, newlist);
            }

        });

        //* now we can calculate the average brightness for each height
        height_2_list_of_brightness.forEach((height,listBri)->{
            ret.put(height, listBri.stream().mapToDouble(a->a).average().getAsDouble());
        });

        //* return ret
        return ret;

    }



    /**
     * Given a list of adventuins, do the followoing things:<P>
     * 1. group the {@code Adventuin}s w.r.t. their {@code HatType}s <P>
     * 2. for each group:<p>
     * <ul>
     * <li> calculate the height differences for each {@code Adventuin} in this group(diff = this - predecessor) </li>
     * <li> group these values w.r.t their signs, i.e. positive, negative, 0 </li>
     * <li> calculate the absolute distance between positive average diff and negative average diff </li>
     * </ul>
     * <P>OVER
     * 
     * @return
     */
    public static Map<HatType, Double> getDiffOfAvgHeightDiffsToPredecessorByHatType(List<Adventuin> adventuins) {
        //!!! if the given list is empty
        if(adventuins.isEmpty()){
            return new HashMap<>();
        }

        //* 0. preparations
        Map<HatType,List<Adventuin>> hat2advs = new HashMap<>();      // bijective mapping: hat type ==> list of Adventuins that wear this hat

        final int POS = 1;
        final int NEG = -1;
        final int ZERO = 0;                                           // signs
                                                                 

        Map<Integer,List<Integer>>  sign2diff = new HashMap<>();      // bijective mapping: signs  ==> list of differences
        sign2diff.put(POS, new ArrayList<>());
        sign2diff.put(NEG, new ArrayList<>());
        sign2diff.put(ZERO, new ArrayList<>());

        Map<HatType,Double> ret = new HashMap<>();                    // the required mapping

        //* 1. group the adventuins w.r.t their hattype
        adventuins.stream().forEach(adv->{
           // if the hattype of this adv is already contained in the map
           if(hat2advs.containsKey(adv.getHatType())){
               // add this adv to the mapped list
               hat2advs.get(adv.getHatType()).add(adv);
           }

           // otherwise create a new pair in the map
           else{
               List<Adventuin> newlist = new ArrayList<>();
               newlist.add(adv);
               hat2advs.put(adv.getHatType(), newlist);
           }
        });

        //* 2. for each group:
        hat2advs.forEach((hattype,list_advs)->{
            // 2.0 clear the deprecated data
            {
                sign2diff.get(POS).clear();
                sign2diff.get(NEG).clear();
                sign2diff.get(ZERO).clear();
            }

            // 2.1 calculate the height diff for each adventuin in this group, diff = this - predecessor            
            IntStream.range(0, list_advs.size()).forEach(i->{
                // index of the predecessor
                int prev = (i-1+2*list_advs.size()) % list_advs.size();

                // diff
                int diff = list_advs.get(i).getHeight() - list_advs.get(prev).getHeight();
                
                // 2.2 group diff w.r.t the sign
                if(diff>0) sign2diff.get(POS).add(diff);
                else if (diff <0) sign2diff.get(NEG).add(diff);
                else sign2diff.get(ZERO).add(diff);
            });

            // 2.3 calculate the absolute distance between postive average diff and negative average diff
            // goal = |a-b|
            {
                // a = postive average diff or 0.0 if empty list
                double a;
                if(sign2diff.get(POS).isEmpty()){
                    a = 0.0;
                }
                else{
                    a = sign2diff.get(POS).stream().mapToInt(z->z).average().getAsDouble();
                }

                // b = negative average diff or 0.0 if empty list
                double b;
                if(sign2diff.get(NEG).isEmpty()){
                    b = 0.0;
                }
                else{
                    b = sign2diff.get(NEG).stream().mapToInt(z->z).average().getAsDouble();
                }

                // add it the ret
                ret.put(hattype, ((a-b)>0? (a-b): (b-a)));
                
            }
        });

        //* 3. return 
        return ret;



        
    }


    public static void main(String[] args) {
        List<Adventuin> adventuins = new ArrayList<>();
        adventuins.add(new Adventuin("FanXian",180,new RgbColor(8,0,1,2),HatType.FISHY_HAT,Language.CHINESE));
        adventuins.add(new Adventuin("FanSizhe", 175, new RgbColor(8,0,127,255), HatType.REINDEER ,Language.ENGLISH));
        adventuins.add(new Adventuin("ChenPingping",175,new RgbColor(8,255,0,0),HatType.FISHY_HAT,Language.GERMAN));
        
        System.out.println(groupByHatType(adventuins));
        
        printLocalizedChristmasGreetings(adventuins);

        System.out.println(getAverageColorBrightnessByHeight(adventuins));
        {
            double avg = 0;
            // brightness(R,G,B) =  0.2126∗R+0.7152∗G+0.0722∗B)/255
            int R,B,G;

            {
                R = 0;G=1;B=2;
                avg += (0.2126 * R+0.7152 * G+0.0722 * B)/255;

                R = 0;G=127;B=255;
                avg += (0.2126 * R+0.7152 * G+0.0722 * B)/255;   

                R = 255;G=0;B=0;
                avg += (0.2126 * R+0.7152 * G+0.0722 * B)/255;

                avg /= 3;
                System.out.println(avg);
            }
        }

        List<Adventuin> adventuins2 = new ArrayList<>();
        adventuins2.add(new Adventuin(null, 100, null, HatType.SANTA_CLAUS, Language.CHINESE));
        adventuins2.add(new Adventuin(null, 120, null, HatType.SANTA_CLAUS, Language.CHINESE));
        adventuins2.add(new Adventuin(null, 100, null, HatType.NO_HAT, Language.CHINESE));
        adventuins2.add(new Adventuin(null, 120, null, HatType.NO_HAT, Language.CHINESE));
        adventuins2.add(new Adventuin(null, 100, null, HatType.NO_HAT, Language.CHINESE));
        adventuins2.add(new Adventuin(null, 100, null, HatType.FISHY_HAT, Language.CHINESE));
        adventuins2.add(new Adventuin(null, 120, null, HatType.FISHY_HAT, Language.CHINESE));
        adventuins2.add(new Adventuin(null, 100, null, HatType.FISHY_HAT, Language.CHINESE));
        adventuins2.add(new Adventuin(null, 110, null, HatType.FISHY_HAT, Language.CHINESE));

        {
            System.out.println(getDiffOfAvgHeightDiffsToPredecessorByHatType(adventuins2));;
        }
        
        
    }

       


    
}
