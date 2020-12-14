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

public class Whale extends Animal {
  static String filename = "whale.png";

  public Whale(int x, int y) {
    super(x, y);

    f = new File(filename);
    image = Toolkit.getDefaultToolkit().getImage(f.getAbsolutePath());
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
  return true;
}


/////////////////////////////////////////////////////////// public functions /////////////////////////////////////////////////////////////////////////

//* override the eatenBy functions according to the hierarchy on artemis
  public boolean canEat(Animal animal) {
    return animal.eatenBy(this);
  }

  @Override
  protected boolean eatenBy(Penguin penguin) {
    return false;
  }

  @Override
  protected boolean eatenBy(PlayerPenguin playerPenguin) {
    return false;
  }

  @Override
  protected boolean eatenBy(Whale whale) {
    return false;
  }

  @Override
  protected boolean eatenBy(LeopardSeal leopardSeal) {
    return false;
  }

  @Override
  protected boolean eatenBy(Fish fish) {
    return false;
  }
}
