package pgdp.space;

import java.util.*;

/**
 * 1. depth first search: 1 set to store the visited beacons, 1 stack to store the routine(used to return)
 * 2. a new copied Pingu has a new stack and set, i.e. he doesn't know the previous routines
 * 3. once a certain Pingu has reached the dest, all the Pingus terminate.
 *
 */
public class Spaceuin extends Thread {
    //* the necessary containers for depth first search
    private Set<Beacon> visited_beacons;
    private Stack<Beacon> visited_routine;


    private FlightRecorder flightRecorder;
    private Beacon start, dest;
    private Beacon currentPosition;     //!!! the current position of the Pingu
    private BeaconConnection nextConnection;
    private Beacon nextPosition;

    //*** a small trick here
    // * a list of boolean variables that store if for each group/space/universe the expected goal is reached
    private static final List<Boolean> found_dest_byGroup = new ArrayList<>();

    private static Integer NextGroupID = 0; // for the next group
    private final int groupID ;           // for each single thread in a group





    public Spaceuin(Beacon start, Beacon destination, FlightRecorder flightRecorder) {
        // 0. if arguments are null
        if(start==null || destination==null || flightRecorder ==null){
            throw new IllegalArgumentException();
        }

        // else
        this.dest = destination;
        this.start = start;
        this.flightRecorder = flightRecorder;

        // initialize the containers
        visited_beacons = new HashSet<>();
        visited_routine = new Stack<>();

        currentPosition = start;

        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! The most important step !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        synchronized (found_dest_byGroup){
            found_dest_byGroup.add(false);
            synchronized (NextGroupID){
                this.groupID = NextGroupID;
                NextGroupID++;
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! without this we can only pass 1 test on Artemis, since the later Pingus will already have it true,
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! which was done by the first Pingu group !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //!!!!
        //!!!! And therefore we need a private constructor to set $(group_ID) for its own group
    }

    private Spaceuin(Beacon start,Beacon destination, FlightRecorder flightRecorder,int groupID){
        // 0. if arguments are null
        if(start==null || destination==null || flightRecorder ==null){
            throw new IllegalArgumentException();
        }

        // else
        this.dest = destination;
        this.start = start;
        this.flightRecorder = flightRecorder;

        // initialize the containers
        visited_beacons = new HashSet<>();
        visited_routine = new Stack<>();

        currentPosition = start;
        this.groupID = groupID;
    }


    /**
     * the kernel routine of the Pingu thread
     */
    @Override
    public void run() {
        //!!! while dest not found for this group
        while (!found_dest_byGroup.get(this.groupID)){
            //* 0. visit current position => synchronized
            synchronized (currentPosition){
                flightRecorder.recordArrival(currentPosition);
                visited_routine.push(currentPosition);
                visited_beacons.add(currentPosition);

                boolean found_next_normal = false;


                //!!! if currenPos == dest => found
                if(currentPosition.equals(dest)){
                    found_dest_byGroup.set(this.groupID,true);
                    flightRecorder.tellStory();
                    return;
                }

                //* loop the outcomming beacons
                for (int index = 0; index <currentPosition.connections().size() ;)
                {
                    nextConnection = currentPosition.connections().get(index);

                    //!!! skipping visited beacons
                    if(!visited_beacons.contains(nextConnection.beacon())){
                        //!!! this next beacon should be seen as visited
                        visited_beacons.add(nextConnection.beacon());

                        // if it's a wormhole
                        if(nextConnection.type().equals(ConnectionType.WORMHOLE)) {

                            
                            //* creat a copy thread and start it
                            new Spaceuin(nextConnection.beacon(), this.dest, this.flightRecorder.createCopy(),this.groupID).start();



                            // update => continue the loop
                            index++;

                        }
                        // else if it's normal one => next round
                        else{
                            nextPosition = nextConnection.beacon();
                            flightRecorder.recordDeparture(currentPosition);
                            found_next_normal = true;
                            break; //*
                        }
                    }

                    //!!! skipping visited beacons
                    else {
                        index++;
                    }

                }

                //!!! out of the loop <=> 1) currentPostion has no outcoming beacons
                //!!! OR 2) all non-visited outcoming beacons are wormhole
                //!!! OR 3) found next normal
                //!!! for 1) 2) we should return 1 step back

                if(!found_next_normal){
                    //* pop 2 elems from the stack(the first one is the current beacon we just pushed, the second one is the previous position)
                    //!!! if not enough elements in the stack => no possible ways at all => over
                    if(visited_routine.size()<2){
                        return;
                    }

                    else{
                        visited_routine.pop();
                        nextPosition = visited_routine.pop();  // over, go to next round
                        flightRecorder.recordDeparture(currentPosition);
                    }
                }
            }

            //* here means we have already determined $(nextPosition)
            currentPosition = nextPosition;

        }
    }

    @Override
    public String toString() {
        // changing that might be useful for testing
        return super.toString();
    }
}