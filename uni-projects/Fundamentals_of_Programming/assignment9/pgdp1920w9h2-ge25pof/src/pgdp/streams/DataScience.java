package pgdp.streams;

import java.util.*;
import java.util.stream.Stream;

public class DataScience {


  /**
   * create a stream of {@code Penguin}s according to the given stream of {@code PenguinData}<p>
   * {@code PenguinData}s that have the same trackID should belongs to the same {@code Penguin}
   * @param stream
   * @return
   */
  public static Stream<Penguin> getDataByTrackId(Stream<PenguinData> penguindata_stream) {
    

    //* 0. preparations
    //!!! since each Penguin has an unique trackID, we use a HashMap to map the penguins using their IDs
    HashMap<String,Penguin> trackid2PenguinMap = new HashMap<>();

    //* 1. iterate the penguindata stream
    penguindata_stream.forEach(penguindata ->{
      // 0. if the trackID of this penguindata is already in the map, add its info to the corresponding penguin
      if(trackid2PenguinMap.keySet().contains(penguindata.trackID)) {
        if(penguindata.geom!=null)
        trackid2PenguinMap.get(penguindata.trackID).getLocations().add(new Geo(penguindata.geom.latitude, penguindata.geom. longitude));
      }

      // 1. otherwise create a new penguin with this trackID and Geo, and put it into the map
      else{
        Penguin newPenguin = new Penguin(new ArrayList<Geo>(), penguindata.trackID);
        if(penguindata.geom!=null)
        newPenguin.getLocations().add(new Geo(penguindata.geom.latitude,penguindata.geom.longitude ));
        trackid2PenguinMap.put(penguindata.trackID, newPenguin);
      }
    });

    //* 2. transform the values in the map to stream
    return trackid2PenguinMap.values().stream();  
  
  }

  // hello friend

  /**
   * output the penguin stream returned by {@code processInputFile} in {@code CSVReading}
   */
  public static void outputPenguinStream() {

    //* 0. the penguin stream
    Stream<PenguinData> penguinDataStream;
    penguinDataStream= CSVReading.processInputFile(); // not null
    Stream<Penguin> pStream = getDataByTrackId(penguinDataStream);

    

    
    //* 1. firth output the number of penguins in the stream
    //* 2. for each penguin in this stream, call its tostring
    //!!! since $(pStream) can only be used once, we need to count penguins and call its toString at the same time

    {
      long penguin_num[] = {0};
      String tostrings[] = {""};

      //!!! sort the penguin stream according to their trackID
      pStream.sorted(new Comparator<Penguin>() {
        @Override
        public int compare(Penguin o1, Penguin o2) {
          return o1.getTrackID().compareTo(o2.getTrackID());
        }
      })

      //!!! sort the locations for each penguin => call its toStringUsingStream
      .forEach(penguin -> {
        tostrings[0] += penguin.toStringUsingStreams()+"\n";
        penguin_num[0]++;
      });

      
  
  

      // output 
      System.out.println(penguin_num[0]+"\n"+tostrings[0]);
    }
     
  }

  /**
   * output the the min, max, avg of longtitude and latitude per trackID according to the given penguin data stream
   * @param stream
   */
  public static void outputLocationRangePerTrackid(Stream<PenguinData> stream) {
    //* 0. first get the stream of penguins
    Stream<Penguin> pStream = getDataByTrackId(stream);

    //* 1. for each penguin in the stream:
    //!!! sorting
    pStream.sorted((o1, o2) -> o1.getTrackID().compareTo(o2.getTrackID())).
    
    forEach(penguin ->{
      // 0.count
      long count = penguin.getLocations().stream().count();
         
      // 1. min lat
      double min_lat = penguin.getLocations().stream().min((o1, o2) -> {
        if (o1.latitude<o2.latitude) return -1;
        if (o1.latitude==o2.latitude) return 0; 
        return 1;
      }).get().latitude;

      // 2. max lat
      double max_lat = penguin.getLocations().stream().max((o1, o2) -> {
        if (o1.latitude<o2.latitude) return -1;
        if (o1.latitude==o2.latitude) return 0;
        return 1;
      }).get().latitude;

      // 3. min long
      double min_long = penguin.getLocations().stream().min(new Comparator<Geo>() {
        @Override
        public int compare(Geo o1, Geo o2) {
          if (o1.longitude<o2.longitude) return -1;
          if (o1.longitude==o2.longitude) return 0;
          return 1;
        }
      }).get().longitude;

      // 4. max long
      double max_long = penguin.getLocations().stream().max(new Comparator<Geo>() {
        @Override
        public int compare(Geo o1, Geo o2) {
          if (o1.longitude<o2.longitude) return -1;
          if (o1.longitude==o2.longitude) return 0;
          return 1;
        }
      }).get().longitude;

      // 5. avg long
      double avg_long = penguin.getLocations().stream().mapToDouble(Geo::getLongitude).average().getAsDouble();

      // 6. avg lat
      double avg_lat = penguin.getLocations().stream().mapToDouble(Geo::getLatitude).average().getAsDouble();

     
      
      //* 3. output the info
      // 3.1 trackID
      System.out.println(penguin.getTrackID());

      // 3.2 min,max,avg
      // e.g. Min Longitude: 147.349091 Max Longitude: 147.467499 Avg Longitude: 147.400995172956 Min Latitude: -43.412323 Max Latitude: -43.270184 Avg Latitude: -43.34747790880503
      System.out.println("Min Longitude: "+min_long+" Max Longitude: "+max_long+" Avg Longitude: "+avg_long+" Min Latitude: "+min_lat+" Max Latitude: "+max_lat+" Avg Latitude: "+avg_lat);
      
    });

    //* test
    //System.out.println();

    
  }

  public static void main(String[] args) {
    //** template data
    //* 0. two penguins
    //* 1. and the corresponding four penguin data
    Penguin MeiChangsu;
    Penguin MengDaTongLing;
    PenguinData data1,data2,data3,data4;
    
    // initialising
    { // 0. MeiChangsu
      List<Geo> locs= new ArrayList<>();
      locs.add(new Geo(1.0,2.0));
      locs.add(new Geo(-3.5, -12.0));
      MeiChangsu = new Penguin(locs, "MeiChangsu");
      data1 = new PenguinData(null, 1, null, locs.get(0).latitude, locs.get(0).longitude, null, null, "MeiChangsu", new Geo(locs.get(0).latitude,locs.get(0).longitude));
      data2 = new PenguinData(null, 2, null, locs.get(1).latitude, locs.get(1).longitude, null, null, "MeiChangsu", new Geo(locs.get(1).latitude,locs.get(1).longitude));
      

      // 1.MengDaTongLing
      List<Geo> locs2= new ArrayList<>();
      locs2.add(new Geo(1.1210,21123.0));
      locs2.add(new Geo(-3.5221, -12221.0));
      MengDaTongLing = new Penguin(locs2, "MengDaTongLing");
      data3 = new PenguinData(null, 3, null, locs2.get(0).latitude, locs2.get(0).longitude, null, null, "MengDaTongLing", new Geo(locs2.get(0).latitude,locs2.get(0).longitude));
      data4 = new PenguinData(null, 4, null, locs2.get(1).latitude, locs2.get(1).longitude, null, null, "MengDaTongLing", new Geo(locs2.get(1).latitude,locs2.get(1).longitude));
    }

    //* 2. TEST: getDataByTrackId
    {
      List<PenguinData> pd_list = new ArrayList<PenguinData>();
      pd_list.add(data1);pd_list.add(data2);pd_list.add(data3);pd_list.add(data4);

      boolean getDataByTrackId_test_pass[] = {true};
      getDataByTrackId(pd_list.stream()).forEach(penguin->{
        // check the trackID and the string form with the template
        if(penguin.getTrackID().equals("MeiChangsu")){
          getDataByTrackId_test_pass[0] = getDataByTrackId_test_pass[0] && penguin.toString().equals(MeiChangsu.toString());
        }
        else if(penguin.getTrackID().equals("MengDaTongLing")){
          getDataByTrackId_test_pass[0] = getDataByTrackId_test_pass[0] && penguin.toString().equals(MengDaTongLing.toString());
        }
        else{
          getDataByTrackId_test_pass[0] = false;
        }

        // if false
        if(!getDataByTrackId_test_pass[0]) try {
          throw new Exception("getDataByTrackId() test failed");
        } catch (Exception e) {
          System.out.println(e);
        }
        else{
          System.out.println("getDataByTrackId() test passed");
        }

      });
    }

    //* 3. TEST: outputLocationRangePerTrackid
    {
      List<PenguinData> pd_list = new ArrayList<PenguinData>();
      pd_list.add(data1);pd_list.add(data2);pd_list.add(data3);pd_list.add(data4);

      // check the outputs in console
      // the right one should be: 
      /**
       *
MeiChangsu
Min Longitude: -12.0 Max Longitude: 2.0 Avg Longitude: -5.0 Min Latitude: -3.5 Max Latitude: 1.0 Avg Latitude: -1.25
MengDaTongLing
Min Longitude: -12221.0 Max Longitude: 21123.0 Avg Longitude: 4451.0 Min Latitude: -3.5221 Max Latitude: 1.121 Avg Latitude: -1.20055
       */
      outputLocationRangePerTrackid(pd_list.stream());
      
      
         
      
    }

    //* 4. TEST: outputPenguinStream
    {
      outputPenguinStream();
      
    }

    

  }
}