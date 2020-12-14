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


package pgdp.games;

public class Penguin extends MiniJava {
    /**
     * print the given 2D array (wolrd) according rules on artemis:<p><i>
     * 0 -> Landflaeche <p>
     * 1 -> Eisflaeche<p>
     * 2 -> Wasser<p>
     * 3 -> Hai<p>
     * 4 -> Fisch<p>
     * @param world
     * @param pinguRow starts with 0
     * @param pinguColumn starts with 0
     */
    public static void printWorld(int[][] world, int pinguRow, int pinguColumn) {
       //* for each row
       for (int i=0;i<world.length;i++) {
           
        //* for each item in this row
        for (int j=0;j<world[0].length;j++) {
            // tile value
            int tile = world[i][j];

            // 1. print according the value of the tile 
            switch (tile) {
                // 1.0 landflaeche
                case 0:{
                    System.out.print("L");break;
                }
                // 1.1 Eisfleache
                case 1:{
                    System.out.print("E");break;
                }
                // 1.2 Wasser
                case 2:{
                    System.out.print("W");break;
                }
                // 1.3 Hai
                case 3:{
                    System.out.print("H");break;
                }
                //1.4 Fisch
                case 4:{
                    System.out.print("F");break;
                }
            }

            // 2. print delimeter as following
            // if the pingu stands on this file: "(P)" + "    "(if not the last in the row)
            // otherwise: "\t"
            //! Java implements variadic length for '\t' according the word before so that the whole length is a multiple of 4/8
            //! so just using '\t' works fine

            boolean pingu = (i==pinguRow) && ( j==pinguColumn);
            boolean last_in_row = j==world[0].length-1;

            // 2.1 if pingu
            if(pingu){
                System.out.print("(P)");
                // 2.1.1 if not the last in the row
                if(!last_in_row){
                    System.out.print("\t");
                    
                }

                // 2.1.2 else nothing
                else {

                }
            }

            // 2.2 if not pingu
            if(!(pingu))
                {
                    // 2.2.1 if not the last in row
                    if(!last_in_row){
                        System.out.print("\t");
                    }

                    // 2.2.2 else nothing
                    else {

                    }
                }
        }
        
        // 3. break line
        System.out.println();

       }
    }


    /**
     * return if the fish is reachable
     * @param world
     * @param i
     * @param j
     * @return
     */
    public static boolean isFishReachable(int world[][],int i, int j) {
        //* calling helper function bc we need to pass extra information: 2D array of visited 

        // array of visited fileds 
        Boolean visited [][] = new Boolean[world.length][world[0].length];
        for (int k = 0; k < visited.length; k++) {
            for (int k2 = 0; k2 < visited[0].length; k2++) {
                visited[k][k2] = false;
            }
        }

        return helper(world, i, j, visited);

        
    }


    /**
     * helper function which recursively probe if Pingu can reach a fish tile or not<p>
     * Pingu moves according the tile type he stands on
     * <p> The tile value are between 0 and 4, both inclusive<p>
     * <i>
     * 0 -> {up,down,left,right}, 1 tile <p>
     * 1 -> {4 diagonal directions}, 1 tile <p>
     * 2 -> {4 diagonal directions}, exact 3 tiles<p>
     * 3 -> dead<p>
     * 4 -> fish 
     * @param world
     * @param pinguRow
     * @param pinguColumn
     * @return
     */
    private static boolean helper(int[][] world, int pinguRow, int pinguColumn,Boolean visited [][]){
        //* if Pingu stands out of the world, return false directly
        if(pinguRow<0 || pinguRow >= world.length || pinguColumn <0 || pinguColumn>=world[0].length) return false;

        // i = pingurow
        // j = pingucolumn for laze ...
        int i = pinguRow;
        int j = pinguColumn;


        //* check the tile value Pingu stands on
        // tile value
        int tile_val = world[pinguRow][pinguColumn];

        //* this position is visited
        visited[pinguRow][pinguColumn] = true;


        //* make possible moves according tile value

        
        switch (tile_val){

            //////////////////!!! we should skip the fileds which we already visited before !!!////////////////////////


            //* {up,down,left,right}, 1 tile 
            case 0:{
                // 0.1 i+1,j
                try{
                if(!visited[i+1][j])
                if(helper(world, i+1, j,visited)) return true;
                }
                // if stackoverflow or index out of bound: continue
                catch(Exception e){}

                // 0.2 i-1,j
                try{
                    if(!visited[i-1][j])
                if( helper(world, i-1, j,visited)) return true;}
                // if stackoverflow or index out of bound: continue
                catch(Exception e){}

                // 0.3 i,j+1
                try{
                    if(!visited[i][j+1])
                if (helper(world, i, j+1,visited)) return true;}
                 // if stackoverflow or index out of bound: continue
                 catch(Exception e){}

                // 0.4 i,j-1
                try{
                    if(!visited[i][j-1])
                  if( helper(world, i, j-1,visited))  return true;
                }
                 // if stackoverflow or index out of bound: continue
                 catch(Exception e){}

                 //! no possible steps
                 return false;
            }

            //* {4 diagonal directions}, 1 tile
            case 1:{
               

                // check if the 4 diagonals by 1 tile is valid indexex

                //! I suddenly found that we don't need to check if the indexes would be valid since the function return false for invalid indexes already at the very beginning...
                
                // 1.1 i-1,j-1
                try{
                    if(!visited[i-1][j-1])
                if(helper(world, i-1, j-1,visited)) return true;
                } // if stackoverflow or index out of bound: continue
                catch(Exception e){}

                // 1.2 i-1,j+1
                try{
                    if(!visited[i-1][j+1])
                if(helper(world, i-1, j+1,visited)) return true;
                }
                 // if stackoverflow or index out of bound: continue
                 catch(Exception e){}

                // 1.3 i+1,j+1
                try{
                    if(!visited[i+1][j+1])
                if(helper(world, i+1, j+1,visited)) return true;
                }
                 // if stackoverflow or index out of bound: continue
                 catch(Exception e){}

                // 1.4 i+1,j-1
                try{
                    if(!visited[i+1][j-1])
                if (helper(world, i+1, j-1,visited)) return true;
                }
                 // if stackoverflow or index out of bound: continue
                 catch(Exception e){}

                 //! no possible steps
                 return false;
            }
            
            //* 2 -> {4 diagonal directions}, exact 3 tiles
            case 2:{

                // 1.1 i-3,j-3
                try{
                    if(!visited[i-3][j-3])
                if(helper(world, i-3, j-3,visited)) return true;
                }
                 // if stackoverflow or index out of bound: continue
                 catch(Exception e){}

                // 1.2 i-3,j+3
                try{
                    if(!visited[i-3][j+3])
                if(helper(world, i-3, j+3,visited)) return true;
                } // if stackoverflow or index out of bound: continue
                catch(Exception e){}

                // 1.3 i+3,j+3
                try{
                    if(!visited[i+3][j+3])
                if(helper(world, i+3, j+3,visited)) return true;}
                 // if stackoverflow or index out of bound: continue
                 catch(Exception e){}

                // 1.4 i+3,j-3
                try{
                    if(!visited[i+3][j-3])
                 if(helper(world, i+3, j-3,visited)) return true;}
                 // if stackoverflow or index out of bound: continue
                 catch(Exception e){}
                
                 //! no possible steps
                 return false;
            }


            //* dead
            case 3:{
                return false;
            }

            //* fish
            case 4:{
                return true;
            }
        }

        //* return for invalid tile 
        return false;
    }

    /**
     * Gibt die Beispielwelt Nr. 1 zurück.
     * Modifizieren Sie diese Methode nicht.
     * @return Eine Beispielwelt
     */
    public static int[][] generateExampleWorldOne(){
        return new int[][] {{2,3,3,3,3,3}, {3,0,3,3,4,3}, {3,3,3,3,3,1}, {3,3,3,0,1,3}, {3,3,3,3,3,3}};
    }

    /**
     * Gibt die Beispielwelt Nr. 2 zurück.
     * Modifizieren Sie diese Methode nicht.
     * @return Eine Beispielwelt
     */
    public static int[][] generateExampleWorldTwo(){
        return new int[][] {{0,0,0,2}, {0,0,0,1}, {0,1,3,4}, {3,4,3,3}};
    }

    /** 
     *  Sie können die main-Methode verwenden, um Ihr Programm zu testen. 
     */
    public static void main(String[] args){
        int pinguRow = 0;
        int pinguColumn = 0;
        int[][] world = generateExampleWorldTwo();
        printWorld(world, pinguRow, pinguColumn);
        write(""+isFishReachable(world, pinguRow, pinguColumn));
    }

}