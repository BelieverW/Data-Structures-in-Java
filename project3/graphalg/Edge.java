/* Edge.java */

package graphalg;

import graph.*;
import set.*;

public class Edge implements Comparable {
  Object object1;
  Object object2;
  int weight;

  Edge(Object o1, Object o2, int w) {
    object1 = o1;
    object2 = o2;
    weight = w;
  }

  public Object object1() {
    return object1;
  }

  public Object object2() {
    return object2;
  }

  public boolean equals(Object o) {
    if (o instanceof Edge) {
    return ((object1.equals(((Edge) o).object1)) &&
            (object2.equals(((Edge) o).object2))) ||
            ((object1.equals(((Edge) o).object2)) &&
            (object2.equals(((Edge) o).object1)));
    } else {
      return false;
    }
  }

  public int weight() {
    return weight;
  }

  public int compareTo(Object o) {
    if (o instanceof Edge) {
      if (weight < ((Edge) o).weight()) {
        return -1;
      } else if (weight == ((Edge) o).weight()) {
        return 0;
      } else {
        return 1;
      }
    } else {
      return 0;
    }      
  }


}
