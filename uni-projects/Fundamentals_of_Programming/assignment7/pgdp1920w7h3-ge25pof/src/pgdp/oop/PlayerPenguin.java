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

package pgdp.oop;

public class PlayerPenguin extends Penguin {
    public PlayerPenguin(int x, int y) {
        super(x, y);
    }

    public boolean canEat(Animal animal) {
        return animal.eatenBy(this);
    }

/////////////////////////////////////////////////////////// private functions /////////////////////////////////////////////////////////////////////////
@Override
protected boolean isAnotherPenguin() {
    // TODO Auto-generated method stub
    return false;
}
@Override
protected boolean isFish() {
    // TODO Auto-generated method stub
    return super.isFish();
}
@Override
protected boolean isLeopardSeal() {
    // TODO Auto-generated method stub
    return super.isLeopardSeal();
}
@Override
protected boolean isWhale() {
    // TODO Auto-generated method stub
    return super.isWhale();
}
/////////////////////////////////////////////////////////// public functions /////////////////////////////////////////////////////////////////////////

/**

 * check if the playerpenguin can be finished if move to the given position <p>
 * A penguin can be finished if and only if:<p>
 * 1) at the position it's a Penguin(win) or LeopardSeal(lose) or Whale(lose)<p>
 * OR<p>
 * 2) the playerpenguin is already dead or the other penguin is dead (lose)
 * <p>
 * 
 * If not finished, i.e. at the given position it's null, then the player has to move there
 * @param newX2
 * @param newY2
 * @return
 */
    public boolean move(int newX2, int newY2){
        int newX = (newX2+410) % 41;
        int newY = (newY2+410) % 41;

        //* 1. if the playerpenguin is dead => finished
        if(!this.alive) return true;

        //* 2. else if at the given position it's the other penguin => finished
        if(antarktis[newX][newY]!=null &&antarktis[newX][newY].isAnotherPenguin()){
            return true;
        }

        //* 3. else if at the given position it's whale or leopard seal => finished
        if(antarktis[newX][newY]!=null)
        if((antarktis[newX][newY].isWhale()) || (antarktis[newX][newY].isLeopardSeal())) {
            //!!! then the playerpenguin should be eaten => dead
            if((antarktis[newX][newY].isWhale()))
                    alive = false;
            return true;
        }

        //* 4. else if the other Penguin is not dead => not finished
        //??? looping the 2D array, if there is an instance of Penguin that is not the PlayerPenguin => not dead; otherwise dead,finished
        //!!! TODO: I IMPLEMENTED THIS IN CLASS Antarktis
        /*
        {

            for (int i = 0; i < antarktis.length; i++) {
                for (int j = 0; j < antarktis[0].length; j++) {
                    
                    // not dead => not finished
                    if((antarktis[i][j] instanceof Penguin) && antarktis[newX][newY] !=this){
                        //!!! the playerpenguin should move to the given position
                        // 1) if it's null
                        if(antarktis[newX][newY]== null){
                            antarktis[newX][newY] = this;
                        }

                        // 2) if it's fish
                        else {
                            antarktis[newX][newY].alive = false;
                            antarktis[newX][newY] = this;
                        }
                        return false;
                    }
                }
            }
        }
        */

        //* otherwise not finished
        //!!! the playerpenguin should move to the given position
            
        
                        //!!! set the current place to null
                        if(antarktis[x%41][y%41] == this){
                            antarktis[x%41][y%41] = null;
                        }
              
                        // 1) if it's null
                        if(antarktis[newX][newY]== null){
                            antarktis[newX][newY] = this;
                        }

                        // 2) if it's fish
                        else {
                            antarktis[newX][newY].alive = false;
                            antarktis[newX][newY] = this;
                        }

                        //!!! store the new x,y
                        x = newX;
                        y = newY;
        return false;
        
    }
    
}
