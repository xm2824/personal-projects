
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



package pgdp.lists;

///////////////////////////////////////// sample solution of P assignment4 ///////////////////////////////////////////////////
public class IntDoubleList {
  private IntDoubleListElement head;
  private IntDoubleListElement tail;

  public IntDoubleList() {
      // Am Anfang hat die Liste keine Elemente
      head = null;
      tail = null;
  }

  // Elemente hinzufuegen
  public void append(int info) {
      IntDoubleListElement newElement = new IntDoubleListElement(info);
      // Falls Liste leer ist
      if (head == null) {
          head = newElement;
          tail = newElement;
      } else {
          // Falls List nicht leer ist, letzes Element erreichen und neues Element
          // hinzufuegen
          tail.next = newElement;
          newElement.prev = tail;
          tail = newElement;
      }
  }

  public int size() {
      IntDoubleListElement temp = head;
      int j = 0;
      // Zahlen wieviele Elemente es gibt, bis null erreicht wird
      while (temp != null) {
          temp = temp.next;
          j++;
      }
      return j;
  }

  public int get(int pos) {
      if (pos < 0 || this.size() <= pos) {
          System.out.println("Position ist groesser als Liste!");
          return 0;
      }

      IntDoubleListElement temp = head;
      for (int i = 0; i < pos; i++, temp = temp.next)
          ;

      return temp.getInfo();
  }
  public void set (int pos, int info){
    if (pos < 0 || this.size() <= pos) {
        System.out.println("Position ist groesser als Liste!");
        return ;
    }


    IntDoubleListElement temp = head;
    for (int i = 0; i < pos; i++, temp = temp.next)
        ;

    temp.setInfo(info);
  }

  public void remove(int pos) {
      IntDoubleListElement temp = head;
      // Wenn pos > size, Fehler melden
      if (this.size() <= pos || pos < 0) {
          System.out.println("Position ist groesser als Liste!");
          // Falls pos==0, muss man den Kopf der Liste verlinken
      } else if (pos == 0) {
          head = head.next;
          if (head != null)
              head.prev = null;
          else
              // Falls die Liste jetzt leer ist, muss auch das Ende gel??scht werden
              tail = null;
      } else {
          // Falls pos !=0 muss man das Elemente Nr. pos -1 mit Element Nr. pos + 1 linken
          int j = 0;
          while (j < pos - 1) {
              temp = temp.next;
              j++;
          }
          temp.next = temp.next.next;
          if (temp.next != null)
              temp.next.prev = temp;
          else
              // Wenn temp.next == null ist temp das Listenende
              tail = temp;
      }
  }

  public String toString() {
      IntDoubleListElement temp = head;
      StringBuilder ret = new StringBuilder();
      // Liste iterieren
      while (temp != null) {
          // String iterativ konkatenieren
          ret.append(temp.getInfo());
          if (temp.next != null)
              // Komma hinzufuegen falls temp nicht das letze Element der List ist
              ret.append(',');
          temp = temp.next;
      }
      return ret.toString();
  }

  public boolean isEqual(IntDoubleList other) {
      IntDoubleListElement mytemp = head;
      IntDoubleListElement othertemp = other.head;

      while (mytemp != null) {
          if (!mytemp.isEqual(othertemp))
              return false;
          mytemp = mytemp.next;
          othertemp = othertemp.next;
      }
      return othertemp == null;
  }

  public int sum() {
      IntDoubleListElement temp = head;
      int ret = 0;
      // Liste iterien
      while (temp != null) {
          // Iterativ summieren
          ret = ret + temp.getInfo();
          temp = temp.next;
      }
      return ret;
  }

  public IntDoubleListElement getFirstElement() {
      return head;
  }

  public IntDoubleListElement getLastElement() {
      return tail;
  }

  // Kopie der interne Elemente erstellen
  public IntDoubleList copy() {
      IntDoubleList ret = new IntDoubleList();

      // temp hilft ??ber die Elemente zu iterieren
      IntDoubleListElement temp = head;
      while (temp != null) {
          // Wert in Kopie speichern
          ret.append(temp.getInfo());
          temp = temp.next;
      }
      return ret;
  }

  public IntDoubleListElement[] search(int intValue) {
      IntDoubleListElement[] results = new IntDoubleListElement[this.size()];
      int j = 0;
      // temp hilft ??ber die Elemente zu iterieren
      IntDoubleListElement temp = head;
      while (temp != null) {
          // Wert in Kopie speichern
          if (temp.getInfo() == intValue) {
              results[j] = temp;
              j++;
          }
          temp = temp.next;
      }
      IntDoubleListElement[] ret = new IntDoubleListElement[j];
      for (int k = 0; k < j; k++)
          ret[k] = results[k];
      return ret;
  }


///////////////////////////////////////// end of sample solution ///////////////// ///////////////////////////////////////////////////
}