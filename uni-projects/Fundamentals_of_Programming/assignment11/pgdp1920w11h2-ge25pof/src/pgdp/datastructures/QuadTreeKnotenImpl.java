package pgdp.datastructures;

import java.util.NoSuchElementException;
import java.util.Optional;

public class QuadTreeKnotenImpl implements QuadTreeKnoten {
    //* private attribute
    private int[][] image;

    //* private constructor
    private QuadTreeKnotenImpl(int[][] image){
        // assume the argument is not null
        this.image = image;
    }


    public static QuadTreeKnoten buildFromIntArray(int[][] image) {
        return new QuadTreeKnotenImpl(image);
    }

    @Override
    public QuadTreeKnoten getTopLeft() {
        //!!! if this is a leaf => exception
        if(isLeaf()) throw new NoSuchElementException();

        //!!! else return a new QuadTreeKnoetenImpl
        //!!! with an new 2D integers array on heap
        int[][] tl = new int[image.length/2][image.length/2];
        for (int x = 0; x < tl.length; x++) {
            for (int y = 0; y < tl.length; y++) {
                tl[x][y] = image[x][y];
            }
        }
        return new QuadTreeKnotenImpl(tl);
    }

    @Override
    public QuadTreeKnoten getTopRight() {
        //!!! if this is a leaf => exception
        if(isLeaf()) throw new NoSuchElementException();

        //!!! else return a new QuadTreeKnoetenImpl
        //!!! with an new 2D integers array on heap
        int[][] tr = new int[image.length/2][image.length/2];
        for (int x = 0; x < tr.length; x++) {
            for (int y = 0; y < tr.length; y++) {
                tr[x][y] = image[x][y+image.length/2];
            }
        }
        return new QuadTreeKnotenImpl(tr);
    }

    @Override
    public QuadTreeKnoten getBottomLeft() {
        //!!! if this is a leaf => exception
        if(isLeaf()) throw new NoSuchElementException();

        //!!! else return a new QuadTreeKnoetenImpl
        //!!! with an new 2D integers array on heap
        int[][] bl = new int[image.length/2][image.length/2];
        for (int x = 0; x < bl.length; x++) {
            for (int y = 0; y < bl.length; y++) {
                bl[x][y] = image[x+image.length/2][y];
            }
        }
        return new QuadTreeKnotenImpl(bl);
    }

    @Override
    public QuadTreeKnoten getBottomRight() {
        //!!! if this is a leaf => exception
        if(isLeaf()) throw new NoSuchElementException();

        //!!! else return a new QuadTreeKnoetenImpl
        //!!! with an new 2D integers array on heap
        int[][] br = new int[image.length/2][image.length/2];
        for (int x = 0; x < br.length; x++) {
            for (int y = 0; y < br.length; y++) {
                br[x][y] = image[x+image.length/2][y+image.length/2];
            }
        }
        return new QuadTreeKnotenImpl(br);
    }

    @Override
    public int getRelativeColor(int x, int y) {
        return image[x][y];
    }

    @Override
    public void setRelativeColor(int x, int y, int color) {
        image[x][y] = color;
    }

    @Override
    public int getDimension() {
        return image.length;
    }


    /**
     * return the size of this tree where a leaf is 1 unit and a not-leaf node is also 1 unit.The thing is,
     * the inner nodes should recursively call this on its children while a leaf should terminate.
     * @return
     */
    @Override
    public int getSize() {
        //!!! if this is a leaf => return 1
        if(isLeaf()) return 1;

        //!!! else recursively call this on child pixels
        int size_of_tl = getTopLeft().getSize();
        int size_of_tr = getTopRight().getSize();
        int size_of_bl = getBottomLeft().getSize();
        int size_of_br = getBottomRight().getSize();        //!!! in this way we can avoid stack overflow a little bit


        return 1+size_of_bl+size_of_br+size_of_tl+size_of_tr;
    }

    /**
     * a QuadTreeKnotenImpl is a leaf when all pixels in this square have same color/values
     * @return
     */
    @Override
    public boolean isLeaf() {
        // 0. first read one pixel
        int tmp = image[0][0];

        // 1. then compare it with other pixels
        //* iterate all pixels
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                if(image[i][j]!=tmp){
                    return false;
                }
            }
        }

        //* out of the loop means all the pixels have same color => leaf
        return true;
    }

    @Override
    public int[][] toArray() {
        return image;
    }


}
