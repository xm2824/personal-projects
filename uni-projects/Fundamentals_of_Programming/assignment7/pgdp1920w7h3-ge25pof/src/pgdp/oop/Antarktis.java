package pgdp.oop;

import java.awt.event.WindowEvent;

public class Antarktis extends Maze {
    private static int width, height;
    private static Penguin lostPenguin;
    private static Whale[] whales = new Whale[5];
    private static LeopardSeal[] leopardSeals = new LeopardSeal[20];
    private static Fish[] fishes = new Fish[500];
    private static PlayerPenguin playerPenguin;

    public static void main(String[] args) {
        width = 41;
        height = 41;
        antarktis = generateMaze(width, height);
        

        
        setupMaze();
        
        gameLoop();

        // Close the opnend frame
        closeFrame();
    }
/////////////////////////////////////////////////////////// private/protected functions /////////////////////////////////////////////////////////////////////////

    /**
     * move all animals according to the ordering on artemis <p>
     * For the PlayerPenguin we should call its move(x,y) function to move it where x and y is related to the user's input
     * @return if this iteration is finished
     */
    private static boolean _moveAll() {
        //* 1. move the PlayerPenguin
        // 1.1 waiting for user's input
        
        //while (currentEvent==NOTHING);

        // 1.2 generate new x,y
        int newx = playerPenguin.x;
        int newy = playerPenguin.y;
        switch (currentEvent) {
            case UP:
                {
                    newy--;
                    newy%=width;
                    break;
                }
            
            case DOWN:{
                newy++;
                newy%=width;
                break;
            }

            case RIGHT:{
                newx++;
                newx%=width;
                break;
            }

            case LEFT:{
                newx--;
                newx%=width;
                break;
            }
            default: break;
        }

        // 1.3 move the PlayerPenguin, if it's finished then we return true since others should not move further
        if(currentEvent!=NOTHING)
        if(playerPenguin.move(newx, newy)) {
            return true;
        }

        // 1.4 else move others

        //!!!***********!!! check after each single move if at least one penguins is dead, if yes => finished !!!**********!!!

        //* 2. whales
        for (Whale whale : whales) {
            if(whale!=null)
                    whale.move();
            boolean penguin_dead = !(playerPenguin.alive && lostPenguin.alive);
            if(penguin_dead){
                return true;
            }
        }

        //* 3. leaopard seals
        for (LeopardSeal ls : leopardSeals) {
            if(ls!=null)
                ls.move();
            boolean penguin_dead = !(playerPenguin.alive && lostPenguin.alive);
            if(penguin_dead){
                return true;
            }
        }

        //* 4. penguins
        if(lostPenguin!=null)
            lostPenguin.move();
        {
            boolean penguin_dead = !(playerPenguin.alive && lostPenguin.alive);
            if(penguin_dead){
                return true;
            }
        }

        //* 5. fishes
        for (Fish f : fishes) {
            if(f!=null)
                f.move();
            boolean penguin_dead = !(playerPenguin.alive && lostPenguin.alive);
            if(penguin_dead){
                return true;
            }
        }

        //!!! end of this iteration: reset currentEvent to Nothing
        //??? implemented here ???
        currentEvent = NOTHING;

        //* 6. return false, not fishinised yet
        return false;
    }

    /**
     * Example Setup for easier Testing during development
     */
    private static void setupMaze() {
        int[] pos;
        playerPenguin = new PlayerPenguin(3, 3);
        antarktis[3][3] = playerPenguin;

        for (int i = 0; i < whales.length; i++) {
            pos = getRandomEmptyField();
            whales[i] = new Whale(pos[0], pos[1]);
            antarktis[pos[0]][pos[1]] = whales[i];
        }

        for (int i = 0; i < leopardSeals.length; i++) {
            pos = getRandomEmptyField();
            leopardSeals[i] = new LeopardSeal(pos[0], pos[1]);
            antarktis[pos[0]][pos[1]] = leopardSeals[i];
        }

        for (int i = 0; i < fishes.length; i++) {
            pos = getRandomEmptyField();
            fishes[i] = new Fish(pos[0], pos[1]);
            antarktis[pos[0]][pos[1]] = fishes[i];
        }

        antarktis[20][20] = new Penguin(20, 20);
        lostPenguin = (Penguin) antarktis[20][20];

        //!!!
        Animal.antarktis = antarktis;
    }

/////////////////////////////////////////////////////////// public functions /////////////////////////////////////////////////////////////////////////
/**
 * only call the private function _moveAll();
 * <p>
 * (The reason to implement another private function is to store the returning value: whether this iteration is finished, used in later GameLoop)
 */
public static void moveAll(){
    _moveAll();
}

/**
 * running the game until it's over
 */
public static void gameLoop(){
    boolean over = false;
    while (!over) {
        // 1. draw
        draw();
        // 2. move
        over = _moveAll();
        
        //
        //! notice that some other requirements like reseting currentEvent, is done within _moveAll
        currentEvent = NOTHING;

        //? 3. set frame to null        
    }
}
}
