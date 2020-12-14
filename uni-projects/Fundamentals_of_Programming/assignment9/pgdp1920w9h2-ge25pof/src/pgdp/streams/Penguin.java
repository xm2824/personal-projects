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

package pgdp.streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Penguin {
  private List<Geo> locations;
  private String trackID;

  public Penguin(List<Geo> locations, String trackID) {
    this.locations = locations;
    this.trackID = trackID;
  }

  public static void main(String[] args) {
    

  }

  @Override
  public String toString() {
    return "Penguin{" +
        "locations=" + locations +
        ", trackID='" + trackID + '\'' +
        '}';
  }

  public List<Geo> getLocations() {
    return locations;
  }

  public String getTrackID() {
    return trackID;
  }

  /**
   * toString using streams <p>
   * e.g.: <p>
   * Penguin{locations=[Geo{latitude=1.0, longitude=4.0}, Geo{latitude=1.0, longitude=2.0}, Geo{latitude=-3.5, longitude=-12.0}, ], trackID='Pingu'}
   * Penguin{locations=[Geo{latitude=1.0, longitude=2.0}, Geo{latitude=-3.5, longitude=-12.0}, ], trackID='Pingu'}
   * @return
   */
  public String toStringUsingStreams() {
   
    //* the to return string
    String ret = "Penguin{locations=[";

    //* 0. list of locations
    //* 0.1 sort the locations descendingly, first compare latitude, then longtitude
    
    {
      //!!! using a String array to access inside the lambda expression !!!
      String[] tmp = {ret};
      locations.stream().sorted((new Comparator<Geo>() {
        @Override
        public int compare(Geo o1, Geo o2) {
          // 0.1.1 first compare latitude
          if(o1.latitude>o2.latitude) return -1;
          else if (o1.latitude<o2.latitude) return 1;

          // 0.1.2 then longtitude
          else if(o1.longitude>o2.longitude) return -1;
          else if (o1.longitude<o2.longitude) return 1;
          else return 0;
        }
        })).forEach(location -> {
          tmp[0] += location.toString()+", ";
        });
        
      //!!! store tmp back to ret !!!
      ret = tmp[0];

      //!!! add a closing bracket !!!
      ret += "]";
    }


    //* 1. trackID
    {
      ret += ", trackID='" + trackID+"'";
    }

    //* 2. closing brace
    {
      ret += "}";
    }

    // return ret
    return ret;
  }
}
