package pgdp.oop;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.io.File;

public abstract class Animal {
  protected int x, y;
  static String filename;
  protected File f;
  protected Image image;

  //* alive attr
  protected boolean alive;

  protected static Animal[][] antarktis;

  public Animal(int x, int y) {
    this.x = x;
    this.y = y;
    alive = true;
  }




/////////////////////////////////////////////////////////// private/protected functions /////////////////////////////////////////////////////////////////////////
protected abstract boolean isFish();
protected abstract boolean isLeopardSeal();
protected abstract boolean isAnotherPenguin();
protected abstract boolean isWhale();
/////////////////////////////////////////////////////////// public functions /////////////////////////////////////////////////////////////////////////


  /**
   * the move implementation for all animal types except fish <p>
   * <i>
   * Checking Order: left -> up -> right -> down <p>
   * basically one can only move to an empty field or a field with its food 
   * UNDER the restriction that the 4 neighbouring fields of this field doesn't have a hunter for this animal
   */
  public void move() {
    
    //TODO: this implementation is not for class Fish, so class Fish has to overwrite this function
    
    //!!! checking ordering of this implementation:
    //!!! 1. first check if the adjacent field is null or has its food
            // yes => check 2.
            // no =>  next neighbour
    //!!! 2. then check if the further 4 adjacent fields of the potential destination field are either null or its food
            // yes => move to this field, eat food if needed, return
            // no  => next neighbour

        
    // width = heigh = 41
    int width = 41;

    //*****************************! first try to find the first possible neighbour with food *************************************************************************8
    //* 1.left
    // 1.1 first check if the adjacent field is not null && can be eaten
        // yes => check 2.
        if((antarktis[(x-1+410)%width][y]!= null))
        if(canEat(antarktis[(x-1+410)%width][y])){
          // 1.2 then check if the further 4 adjacent fields of the potential destination field are either null or its food
          int tmp = (x-1+410)%width;
          if(
              // left
              (antarktis[(tmp-1+410)%width][y] == null || canEat(antarktis[(tmp-1+410)%width][y])) &&
              // up
              (antarktis[tmp][(y-1+410)%width] == null || canEat(antarktis[tmp][(y-1+410)%width])) &&
              // down
              (antarktis[tmp][(y+1+410)%width] == null || canEat(antarktis[tmp][(y+1+410)%width]))
          )
          // yes => move to this field, eat food if needed, return
          {
            //!!! set the current place to null
            if(antarktis[(x+410)%width][(y+410)%width] == this){
              antarktis[(x+410)%width][(y+410)%width] = null;
            }

            x = (x-1+410)%width;
            //! eat it if not null
            if(antarktis[(x+410)%width][(y+410)%width]!= null){
              antarktis[(x+410)%width][(y+410)%width].alive = false;
            }
            
            //! set this field to this animal and return
            antarktis[(x+410)%width][(y+410)%width] = this;
            return;
          }
        }
    
    //* 2. up
    // 2.1 first check if the adjacent field is not null && can be eaten 
        // yes => check 2.
        if(antarktis[x][(y-1+410)%width]!= null && canEat(antarktis[x][(y-1+410)%width])){
          // 2.2 then check if the further 4 adjacent fields of the potential destination field are either null or its food
          int tmp = (y-1+410)%width;
          if(
              // left
              (antarktis[(x-1+410)%width][tmp] == null || canEat(antarktis[(x-1+410)%width][tmp])) &&
              // right
              (antarktis[(x+1+410)%width][tmp] == null || canEat(antarktis[(x+1+410)%width][tmp])) &&
              // up
              (antarktis[x][(tmp-1+410)%width] == null || canEat(antarktis[x][(tmp-1+410)%width])) 

          )
          // yes => move to this field, eat food if needed, return
          {
            //!!! set the current place to null
            if(antarktis[(x+410)%width][(y+410)%width] == this){
              antarktis[(x+410)%width][(y+410)%width] = null;
            }
            
            y = (y-1+410)%width;
            //! eat it if not null
            if(antarktis[(x+410)%width][(y+410)%width]!= null){
              antarktis[(x+410)%width][(y+410)%width].alive = false;
            }
            
            //! set this field to this animal and return
            antarktis[(x+410)%width][(y+410)%width] = this;
            return;
          }
        }
    
    //* 3. right
    // 3.1 first check if the adjacent field is not null && can be eaten 
        // yes => check 2.
        if(antarktis[(x+1+410)%width][y]!= null && canEat(antarktis[(x+1+410)%width][y]) ){
          // 3.2 then check if the further 4 adjacent fields of the potential destination field are either null or its food
          int tmp = (x+1+410)%width;
          if(

              // right
              (antarktis[(tmp+1+410)%width][y] == null || canEat(antarktis[(tmp+1+410)%width][y])) &&
              // up
              (antarktis[tmp][(y-1+410)%width] == null || canEat(antarktis[tmp][(y-1+410)%width])) &&
              // down
              (antarktis[tmp][(y+1+410)%width]== null || canEat(antarktis[tmp][(y+1+410)%width]))
          )
          // yes => move to this field, eat food if needed, return
          {
            //!!! set the current place to null
            if(antarktis[(x+410)%width][(y+410)%width] == this){
              antarktis[(x+410)%width][(y+410)%width] = null;
            }
            
            x = (x+1+410)%width;
            //! eat it if not null
            if(antarktis[(x+410)%width][(y+410)%width]!= null){
              antarktis[(x+410)%width][(y+410)%width].alive = false;
            }
            
            //! set this field to this animal and return
            antarktis[(x+410)%width][(y+410)%width] = this;
            return;
          }
        }

    //* 4. down
    // 4.1 first check if the adjacent field is not null && can be eaten 
        // yes => check 2.
        if(antarktis[x][(y+1+410)%width]!= null && canEat(antarktis[x][(y+1+410)%width])){
          // 4.2 then check if the further 4 adjacent fields of the potential destination field are either null or its food
          int tmp = (y+1+410)%width;
          if(
              // left
              (antarktis[(x-1+410)%width][tmp] == null || canEat(antarktis[(x-1+410)%width][tmp])) &&
              // right
              (antarktis[(x+1+410)%width][tmp] == null || canEat(antarktis[(x+1+410)%width][tmp])) &&
              // down
              (antarktis[x][(tmp+1+410)%width] == null || canEat(antarktis[x][(tmp+1+410)%width]))
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
     

    //***! if there is no movable neighbour with food, then find the first movable neighbour(in this case no need to check the neighbours with food)****
    //* 1. left
    // 1.1 first check if the adjacent field is null 
        // yes => check 2.
        if(antarktis[(x-1+410)%width][y]== null){
          // 1.2 then check if the further 4 adjacent fields of the potential destination field are either null or its food
          int tmp = (x-1+410)%width;
          if(
             // left
             (antarktis[(tmp-1+410)%width][y] == null || canEat(antarktis[(tmp-1+410)%width][y])) &&
             // up
             (antarktis[tmp][(y-1+410)%width] == null || canEat(antarktis[tmp][(y-1+410)%width])) &&
             // down
             (antarktis[tmp][(y+1+410)%width] == null || canEat(antarktis[tmp][(y+1+410)%width]))
         )
         // yes => move to this field, eat food if needed, return
         {
           //!!! set the current place to null
           if(antarktis[(x+410)%width][(y+410)%width] == this){
            antarktis[(x+410)%width][(y+410)%width] = null;
          }
          
           x = (x-1+410)%width;
           //! eat it if not null
           if(antarktis[(x+410)%width][(y+410)%width]!= null){
             antarktis[(x+410)%width][(y+410)%width].alive = false;
           }
           
           //! set this field to this animal and return
           antarktis[(x+410)%width][(y+410)%width] = this;
           return;
          }
        }

        // no =>  next neighbour
    //* 2. up
    // 2.1 first check if the adjacent field is null 
        // yes => check 2.
        if(antarktis[x][(y-1+410)%width]== null){
          // 2.2 then check if the further 4 adjacent fields of the potential destination field are either null or its food
          int tmp = (y-1+410)%width;
          if(
               // left
               (antarktis[(x-1+410)%width][tmp] == null || canEat(antarktis[(x-1+410)%width][tmp])) &&
               // right
               (antarktis[(x+1+410)%width][tmp] == null || canEat(antarktis[(x+1+410)%width][tmp])) &&
               // up
               (antarktis[x][(tmp-1+410)%width] == null || canEat(antarktis[x][(tmp-1+410)%width])) 
 
           )
           // yes => move to this field, eat food if needed, return
           {
             //!!! set the current place to null
            if(antarktis[(x+410)%width][(y+410)%width] == this){
              antarktis[(x+410)%width][(y+410)%width] = null;
            }
            
             y = (y-1+410)%width;
             //! eat it if not null
             if(antarktis[(x+410)%width][(y+410)%width]!= null){
               antarktis[(x+410)%width][(y+410)%width].alive = false;
             }
             
             //! set this field to this animal and return
             antarktis[(x+410)%width][(y+410)%width] = this;
             return;
          }
        }

    //* 3. right 
    // 3.1 first check if the adjacent field is null 
        // yes => check 2.
        if(antarktis[(x+1+410)%width][y]== null){
          // 3.2 then check if the further 4 adjacent fields of the potential destination field are either null or its food
          int tmp = (x+1+410)%width;
          if(
              // right
              (antarktis[(tmp+1+410)%width][y] == null || canEat(antarktis[(tmp+1+410)%width][y])) &&
              // up
              (antarktis[tmp][(y-1+410)%width] == null || canEat(antarktis[tmp][(y-1+410)%width])) &&
              // down
              (antarktis[tmp][(y+1+410)%width]== null || canEat(antarktis[tmp][(y+1+410)%width]))
          )
          // yes => move to this field, eat food if needed, return
          {
            //!!! set the current place to null
            if(antarktis[(x+410)%width][(y+410)%width] == this){
              antarktis[(x+410)%width][(y+410)%width] = null;
            }
            
            x = (x+1+410)%width;
            //! eat it if not null
            if(antarktis[(x+410)%width][(y+410)%width]!= null){
              antarktis[(x+410)%width][(y+410)%width].alive = false;
            }
            
            //! set this field to this animal and return
            antarktis[(x+410)%width][(y+410)%width] = this;
            return;
          }
        }

    //* 4 down
    // 4.1 first check if the adjacent field is null 
        // yes => check 2.
        if(antarktis[x][(y+1+410)%width]== null){
          // 4.2 then check if the further 4 adjacent fields of the potential destination field are either null or its food
          int tmp = (y+1+410)%width;
          if(
                // left
                (antarktis[(x-1+410)%width][tmp] == null || canEat(antarktis[(x-1+410)%width][tmp])) &&
                // right
                (antarktis[(x+1+410)%width][tmp] == null || canEat(antarktis[(x+1+410)%width][tmp])) &&
                // down
                (antarktis[x][(tmp+1+410)%width] == null || canEat(antarktis[x][(tmp+1+410)%width]))
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

    //*****! if program reaches here, meaning there is no possible moves => don't move
        return;
 } 

  public abstract boolean canEat(Animal animal);

  protected abstract boolean eatenBy(Penguin penguin);
  protected abstract boolean eatenBy(PlayerPenguin playerPenguin);
  protected abstract boolean eatenBy(Whale whale);
  protected abstract boolean eatenBy(LeopardSeal leopardSeal);
  protected abstract boolean eatenBy(Fish fish);

  public static void setAntarktis(Animal[][] antarktis) {
    Animal.antarktis = antarktis;
  }
  // Graphics Stuff - You don't have to do anything here

  private void paintSymbol(Graphics g, Color c, int height, int width) {
    GradientPaint gradient = new GradientPaint(15, 0, c, width, 0, Color.LIGHT_GRAY);
    ((Graphics2D) g).setPaint(gradient);
    ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g.fillOval((int) (width * 0.3), (int) (height * 0.3), (int) (width * 0.5),
        (int) (height * 0.5));
  }

  public void draw(Graphics g, int height, int width) {
    if (image == null) {
      paintSymbol(g, Color.YELLOW, height, width);
      return;
    }
    ((Graphics2D) g).drawImage(image, 0, 0, width, height, 0, 0, image.getWidth(null),
        image.getHeight(null), null);
  }
}
