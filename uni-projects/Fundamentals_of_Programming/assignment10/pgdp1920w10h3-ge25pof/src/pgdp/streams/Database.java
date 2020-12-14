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

// TODO Import the stuff you need

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

public class Database {
    private static Path baseDataDirectory = Paths.get("data");

    public static void setBaseDataDirectory(Path baseDataDirectory) {
        Database.baseDataDirectory = baseDataDirectory;
    }

    public static Stream<Customer> processInputFileCustomer() {
        //*** the List of LineItem
        List<Customer> customers= new ArrayList<>();

        //* 0. first open a reader for the input file
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(baseDataDirectory.toAbsolutePath()+"/customer.tbl")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        //* 1. for each line
        reader.lines().forEach(line->{
            // 1.1 split the line to string array
            String[] data = line.split("\\|");


            // 1.2 parse each string
            /**
             *  int custKey;
             *     String mktsegment;
             *     private char[] varChar;
             *     private int nationkey;
             *     private char[] phone;
             *     private float acctbal;
             *     private char[] comment;
             */

                // 0. custKey
                int custKey = -1;
                {
                    try {
                        custKey = Integer.parseInt(data[0]);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                // 1. mktsegment
                String mktsegment = data[6];

                // 2. varChar
                char[] varChar = data[2].toCharArray();

                // 3. nationkey
                int nationkey = -1;
                {
                    try {
                        nationkey = Integer.parseInt(data[3]);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                // 4. phone
                char[] phone = data[4].toCharArray();

                // 5. acctbal
                float acctbal = -1;
                {
                    try{
                        acctbal = Float.parseFloat(data[5]);
                    }catch (Exception e){
                        e.printStackTrace();;
                    }
                }

                // 6. comment
                char[] comment = data[7].toCharArray();


            // 1.3 create an new obj
            customers.add(new Customer(custKey, varChar, nationkey, phone, acctbal, mktsegment, comment));
        });

        //* return the stream
        return customers.stream();
    }

    public static Stream<LineItem> processInputFileLineItem() {
        //*** the List of LineItem
        List<LineItem> lineItems= new ArrayList<>();

        //* 0. first open a reader for the input file
        BufferedReader reader = null;
        try {
             reader = new BufferedReader(new InputStreamReader(new FileInputStream(baseDataDirectory.toAbsolutePath()+"/lineitem.tbl")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        //* 1. then for each line of the file, parsing a lineItem object
        reader.lines().forEach(line->{

            // 1.1 splite the line to string array
            String data[] = line.split("\\|");

            // 1.2 parse each string
            {
                /**
                 * int orderKey;
                    int quantity;
                    private int partKey;
                    private int suppKey;
                    private int lineItem;
                    private float extendedPrice;
                    private float discount;
                    private float tax;
                    private char returnFlag;
                    private char lineStatus;
                    private LocalDate shipDate;
                    private LocalDate commitDate;
                    private LocalDate receiptDate;
                    private char[] sh  //*** the List of LineItem
         List<Order> orders= new ArrayList<>();

         //* 0. first open a reader for the input file
         BufferedReader reader = null;
         try {
              reader = new BufferedReader(new InputStreamReader(new FileInputStream(baseDataDirectory.toAbsolutePath()+"/orders.tbl")));
         } catch (FileNotFoundException e) {
             e.printStackTrace();
             return null;
         }

         //* 1. for each lineipInstruct;
                    private char[] shipMode;
                    private char[] comment;
                 */

                 // 0.order key
                 int orderKey;
                 {
                     try {
                         orderKey = Integer.parseInt(data[0]);
                     } catch (Exception e) {
                         e.printStackTrace();
                         orderKey = -1;
                     }
                 }

                 // 1. quantity
                int quantity;
                {
                    try{
                        quantity = Integer.parseInt(data[4])*100;
                    }catch (Exception e){
                        quantity = -1;
                    }
                }

                // 2. partKey
                int partKey;
                {
                    try {
                        partKey = Integer.parseInt(data[1]);
                    }catch (Exception e){
                        partKey = -1;
                    }
                }

                // 3.suppKey
                int suppKey;
                {
                    try {
                        suppKey = Integer.parseInt(data[2]);
                    }catch (Exception e){
                        e.printStackTrace();
                        suppKey=-1;
                    }
                }
                // 4. line item
                int lineItem = -1;
                {
                    try {
                        lineItem = Integer.parseInt(data[3]);}
                        catch(Exception e){
                            e.printStackTrace();
                        }

                // 5. extendedPrice
                float extendedPrice = -1;
                {
                    try {
                        extendedPrice = Float.parseFloat(data[5]);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                // 6. discount
                float discount = -1;
                {
                    try {
                        discount = Float.parseFloat(data[6]);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                // 7. tax
                float tax = -1;
                {
                    try {
                        tax = Float.parseFloat(data[7]);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                // 8.returnFlag
                char returnFlag;
                {
                    returnFlag = data[8].charAt(0);
                }

                // 9. lineStatus
                char lineStatus = data[9].charAt(0);

                // 10. shipDate
                LocalDate shipDate = null;
                {
                    try{
                        shipDate= LocalDate.parse(data[10]);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                // 11. commit date
                LocalDate commitDate = null;
                {
                    try{
                        commitDate= LocalDate.parse(data[11]);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                // 12. receiptDate
                LocalDate receiptDate = null;
                {
                    try {
                        receiptDate = LocalDate.parse(data[12]);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                // 13. shipInstruct
                char[] shipInstruct = data[13].toCharArray();

                // 14. shipMode
                char[] shipMode = data[14].toCharArray();

                // 15. comment
                char[] comment = data[15].toCharArray();

                //* creating an lineitem object and append it to the list
                lineItems.add(new LineItem(orderKey, partKey, suppKey, lineItem,
                 quantity, extendedPrice, discount, tax, returnFlag,
                  lineStatus, shipDate, commitDate, receiptDate, shipInstruct, shipMode, comment));
            }}
        });

        //* close the reader
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //* finally return the stream of the list
        return lineItems.stream();
        

        // Für die Quantity der Lineitems bitte Integer.parseInt(str) * 100 verwenden !
    }

    public static Stream<Order> processInputFileOrders() {
         //*** the List of LineItem
         List<Order> orders= new ArrayList<>();

         //* 0. first open a reader for the input file
         BufferedReader reader = null;
         try {
              reader = new BufferedReader(new InputStreamReader(new FileInputStream(baseDataDirectory.toAbsolutePath()+"/orders.tbl")));
         } catch (FileNotFoundException e) {
             e.printStackTrace();
             return null;
         }

         //* 1. for each line
         reader.lines().forEach(line->{
             // 1.1 parse it to string array
             String[] data = line.split("\\|");

             // 1.2 parse each string
             /**
              * int orderKey;
                int custKey;
                private char orderStatus;
                private float totalPrice;
                private LocalDate orderDate;
                private char[] orderPriority;
                private char[] clerk;
                private int shippingPriority;
                private char[] comment;
              */

              // 0. orderKey
              int orderKey = -1;
              {
                  try {
                      orderKey = Integer.parseInt(data[0]); 
                  } catch (Exception e) {
                      e.printStackTrace();
                  }
              }

              // 1. custKey
              int custKey = -1;
             {
                 try {
                     custKey = Integer.parseInt(data[1]);
                 }catch (Exception e){
                     e.printStackTrace();
                 }
             }

              // 2. orderStatus
              char orderStatus = data[2].charAt(0);

              // 3. totalPrice
              float totalPrice = -1;
             {
                 try {
                     totalPrice = Float.parseFloat(data[3]);
                 }catch (Exception e){
                     e.printStackTrace();
                 }
             }

              // 4. orderDate
              LocalDate orderDate = null;
             {
                 try {
                     orderDate = LocalDate.parse(data[4]);
                 }catch (Exception e){
                     e.printStackTrace();
                 }
             }

              // 5. orderPriority
                char[] orderPriority = data[5].toCharArray();

              // 6. clerk
                char[] clerk = data[6].toCharArray();

              // 7. shippingPriority
                int shippingPriority = -1;
             {
                 try {
                     shippingPriority = Integer.parseInt(data[7]);
                 }catch (Exception e){
                     e.printStackTrace();
                 }
             }
              // 8. comment
             char[] comment = data[8].toCharArray();

             // 1.3 create an Order obj
             orders.add(new Order(orderKey, custKey, orderStatus, totalPrice, orderDate, orderPriority, clerk, shippingPriority, comment));
         });

         //* return the stream
        return orders.stream();
    }

    public Database() {
        
    }


    /**
     * get the average quantity of the given market segment
     * @param marketsegment
     * @return
     */
    public long getAverageQuantityPerMarketSegment(String marketsegment){
        //* 0. preparations
        Stream<Customer> customerStream = processInputFileCustomer();
        Stream<Order> orderStream = processInputFileOrders();
        Stream<LineItem> lineItemStream = processInputFileLineItem();
        Map<Integer,String> custKey_2_marketSgm = new HashMap<>();
        Map<Integer,String> order_2_mksgm = new HashMap<>();


        //* 1. build the map
        customerStream.forEach(cust ->{
            if(!custKey_2_marketSgm.containsKey(cust.custKey)){
                custKey_2_marketSgm.put(cust.custKey,cust.mktsegment);
            }
        });

        orderStream.forEach(order -> {
            int custkey = order.custKey;
            if(!order_2_mksgm.containsKey(order.orderKey)){
                order_2_mksgm.put(order.orderKey,custKey_2_marketSgm.get(custkey));
            }
        });

        //* 2. get the order keys of the given marketSegment
        ArrayList<Integer> orderKeys = new ArrayList<>();
        order_2_mksgm.forEach((order,mksgm)->{
            if(mksgm.equals(marketsegment)){
                orderKeys.add(order);
            }
        });

        //* 3. process the lineItem w.r.t. the specific orders above
        long sum[] = {0};
        long count[] = {0};
        lineItemStream.filter(lineItem -> orderKeys.contains(lineItem.orderKey))
                .forEach(lineItem ->{
                    sum[0] += lineItem.quantity;
                    count[0] ++;
                } );

        //* return the result
        return sum[0]/count[0];

        
    }

    public static void main(String[] args) throws FileNotFoundException {
      BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(Database.baseDataDirectory+"/customer.tbl")));
       String line =   reader.lines().findFirst().get();
       System.out.println(Arrays.toString(line.split("\\|")));
        
        Database a = new Database();
        long d =a.getAverageQuantityPerMarketSegment("HOUSEHOLD");
        System.out.println(d);
    }
}
