/* VertexNode.java */

package list;



public class VertexNode {

  private Object item;
  private DList edges;
  private DListNode node;

  public VertexNode(Object i, DListNode node) {
    item = i;
    this.node = node;
    edges = new DList();
  }

  public Object item() {
    return item;
  }

  public DList getEdges() {
    return edges;
  }

  public void setDListNode(DListNode node) {
    this.node = node;
  }

  public DListNode getDListNode() {
    return node;
  }

  
  public int degree()  {
    return edges.length();
  }

  public void addEdge(EdgeNode e) {
    //e.setvertexEdgeNode(this);
    edges.insertBack(e);
    DListNode node = (DListNode) edges.back();
    e.setVertexEdgeNode(node);
  }

  public void removeAllEdge() {
    DListNode node1, node2;

    node1 = (DListNode) edges.front();
    try {
      while (node1.isValidNode()) {
        node2 = (DListNode) node1.next();
        removeEdge((EdgeNode) node1.item());
        node1 = node2;
      }
    } catch (InvalidNodeException e) {}
  }

  public void removeEdge(EdgeNode e) {
    if (this == (VertexNode) e.object1() || this == (VertexNode) e.object2()) {
      DListNode node1 = e.getVertexEdgeNode();
      DListNode node2 = e.getGraphEdgeNode();
      EdgeNode partner = e.getPartner();

      try {
        node1.remove();
        e.delete();
      } catch (InvalidNodeException el) {}
      
      if (partner != null) {
        node1 = partner.getVertexEdgeNode();
        try {
          node1.remove();
          partner.delete();
        } catch (InvalidNodeException ell) {}
      }

      try {
        node2.remove();
      } catch (InvalidNodeException elll) {}
    }
  }

  public String toString() {
    String s = "";
    DListNode node = (DListNode) edges.front();

    s = s + item;
    try {
      while (node.isValidNode()) {
        s += " -- " + ((VertexNode) ((EdgeNode) node.item()).object1()).item() +
             ", " + ((VertexNode) ((EdgeNode) node.item()).object2()).item();
      }
    } catch (InvalidNodeException elll) {}
    return s;
  }

}
