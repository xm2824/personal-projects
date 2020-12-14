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

import java.awt.Toolkit;
import java.io.File;

public class Fish extends Animal {
  static String filename = "fish.png";

  public Fish(int x, int y) {
    super(x, y);

    f = new File(filename);
    image = Toolkit.getDefaultToolkit().getImage(f.getAbsolutePath());
  }

/////////////////////////////////////////////////////////// private functions /////////////////////////////////////////////////////////////////////////
@Override
protected boolean isFish() {
  // TODO Auto-generated method stub
  return true;
}

@Override
protected boolean isAnotherPenguin() {
  // TODO Auto-generated method stub
  return false;
}

@Override
protected boolean isLeopardSeal() {
  // TODO Auto-generated method stub
  return false;
}

@Override
protected boolean isWhale() {
  // TODO Auto-generated method stub
  return false;
}
/////////////////////////////////////////////////////////// public functions /////////////////////////////////////////////////////////////////////////

//* override the eatenBy functions according to the hierarchy on artemis
  public boolean canEat(Animal animal) {
    return animal.eatenBy(this);
  }

  @Override
  protected boolean eatenBy(Penguin penguin) {
    return true;
  }

  @Override
  protected boolean eatenBy(PlayerPenguin playerPenguin) {
    return true;
  }

  @Override
  protected boolean eatenBy(Whale whale) {
    return false;
  }

  @Override
  protected boolean eatenBy(LeopardSeal leopardSeal) {
    return true;
  }

  @Override
  protected boolean eatenBy(Fish fish) {
    return false;
  }



  /**
   * the overrided function for move(), the difference from the superclass's is <p>
   * 1. the ordering is here: up -> right -> down -> left <p>
   * 2. Fish can't eat any animals so it can only move to null field with all 4 further neighbours null
   */
  @Override
  public void move(){
    int width = 41;

    //***! find the first movable neighbour ****
    //* 1. up
    // 1.1 first check if the adjacent field is null 
        // yes => check 2.
        if(antarktis[x][(y-1+410)%width]== null){
          // 1.2 then check if the further 4 adjacent fields of the potential destination field are either null or its food
          int tmp = (y-1+410)%width;
          if(
              // left
              (antarktis[(x-1+410)%width][tmp] == null ) &&
              // right
              (antarktis[(x+1+410)%width][tmp] == null ) &&
              // up
              (antarktis[x][(tmp-1+410)%width] == null ) 

          )
          // yes => move to this field, eat food if needed, return
          {
            //!!! set the current place to null
            if(antarktis[(x+410)%width][(y+410)%width] == this){
              antarktis[(x+410)%width][(y+410)%width] = null;
            }
            
            y = (y-1+410)%width;
            
            //! set this field to this animal and return
            antarktis[(x+410)%width][(y+410)%width] = this;
            return;
          }
        }

    //* 2. right 
    // 2.1 first check if the adjacent field is null 
        // yes => check 2.
        if(antarktis[(x+1+410)%width][y]== null){
          // 2.2 then check if the further 4 adjacent fields of the potential destination field are either null or its food
          int tmp = (x+1+410)%width;
          if(

              // right
              (antarktis[(tmp+1+410)%width][y] == null ) &&
              // up
              (antarktis[tmp][(y-1+410)%width] == null ) &&
              // down
              (antarktis[tmp][(y+1+410)%width] == null )
          )
          // yes => move to this field, eat food if needed, return
          {
            //!!! set the current place to null
            if(antarktis[(x+410)%width][(y+410)%width] == this){
              antarktis[(x+410)%width][(y+410)%width] = null;
            }
            
            x = (x+1+410)%width;
            
            //! set this field to this animal and return
            antarktis[(x+410)%width][(y+410)%width] = this;
            return;
          }
        }

    //* 3 down
    // 3.1 first check if the adjacent field is null 
        // yes => check 2.
        if(antarktis[x][(y+1+410)%width]== null){
          // 3.2 then check if the further 4 adjacent fields of the potential destination field are either null or its food
          int tmp = (y+1+410)%width;
          if(
               // left
               (antarktis[(x-1+410)%width][tmp] == null ) &&
               // right
               (antarktis[(x+1+410)%width][tmp] == null ) &&
               // down
               (antarktis[x][(tmp+1+410)%width]== null )
          )
          // yes => move to this field, eat food if needed, return
          {
            //!!! set the current place to null
            if(antarktis[(x+410)%width][(y+410)%width] == this){
              antarktis[(x+410)%width][(y+410)%width] = null;
            }
            
            y = (y+1+410)%width;
            //! eat it if not null
            if(antarktis[(x+410)%width][(y+410)%width]!= null){
              antarktis[(x+410)%width][(y+410)%width].alive = false;
            }
            
            //! set this field to this animal and return
            antarktis[(x+410)%width][(y+410)%width] = this;
            return;
          }
        }

    
    //* 4. left
    // 4.1 first check if the adjacent field is null 
        // yes => check 2.
        if(antarktis[y][(x-1+410)%width]== null){
          // 4.2 then check if the further 4 adjacent fields of the potential destination field are either null or its food
          int tmp = (x-1+410)%width;
          if(
            
              // left
              (antarktis[(tmp-1+410)%width][y] == null ) &&
              // up
              (antarktis[tmp][(y-1+410)%width] == null ) &&
              // down
              (antarktis[tmp][(y+1+410)%width] == null )
          )
          // yes => move to this field, eat food if needed, return
          {
            //!!! set the current place to null
            if(antarktis[(x+410)%width][(y+410)%width] == this){
              antarktis[(x+410)%width][(y+410)%width] = null;
            }
            
            x = (x-1+410)%width;
            
            //! set this field to this animal and return
            antarktis[(x+410)%width][(y+410)%width] = this;
            return;
          }
        }

    //*****! if program reaches here, meaning there is no possible moves => don't move
        return;
  }
}
