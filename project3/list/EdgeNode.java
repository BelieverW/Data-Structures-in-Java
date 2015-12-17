/* EdgeNode.java */

package list;


import graph.*;

public class EdgeNode extends VertexPair {

  

  protected EdgeNode partner;
  protected int weight;
  protected DListNode vertexEdgeNode;
  protected DListNode graphEdgeNode;

  public EdgeNode(Object i1, Object i2, EdgeNode partner, int weight) {
    super(i1, i2);
    this.partner = partner;
    this.weight = weight;
    vertexEdgeNode = null;
    graphEdgeNode = null;
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }

  public EdgeNode getPartner() {
    return partner;
  }

  public EdgeNode setPartner() {
    if (object1 == object2) {
      partner = null;
    } else {
      partner = new EdgeNode(object2, object1, this, weight);
      partner.graphEdgeNode = graphEdgeNode;
    }
    return partner;
  }

  public Object object1() {
    return object1;
  }

  public void setObject1(Object o1) {
    object1 = o1;
  }

  public Object object2() {
    return object2;
  }

  public void setObject2(Object o2) {
    object2 = o2;
  }

  public void setVertexEdgeNode(DListNode node) {
    vertexEdgeNode = node;
  } 

  public DListNode getVertexEdgeNode() {
    return vertexEdgeNode;
  }

  public void setGraphEdgeNode(DListNode node) {
    graphEdgeNode = node;
  }

  public DListNode getGraphEdgeNode() {
    return graphEdgeNode;
  }

  public void delete() {
    object1 = null;
    object2 = null;
    partner = null;
    vertexEdgeNode = null;
    graphEdgeNode = null;

  }

  public String toString() {
    String s = "";
    
    s += "(" + object1 + ", " + object2 +")";
    return s; 
  }
  

}
