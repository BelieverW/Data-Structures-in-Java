/* WUGraph.java */

package graph;

import list.*;
import dict.*;
/**
 * The WUGraph class represents a weighted, undirected graph.  Self-edges are
 * permitted.
 */

public class WUGraph {

  private HashTableChained vertexHashTable;
  private HashTableChained edgeHashTable;
  private DList vertexList;
  private DList edgeList;

  /**
   * WUGraph() constructs a graph having no vertices or edges.
   *
   * Running time:  O(1).
   */
  public WUGraph() {
    vertexHashTable = new HashTableChained();
    edgeHashTable = new HashTableChained();
    vertexList = new DList();
    edgeList = new DList();
  }

  /**
   * vertexCount() returns the number of vertices in the graph.
   *
   * Running time:  O(1).
   */
  public int vertexCount() {
    return vertexList.length();
  }

  /**
   * edgeCount() returns the total number of edges in the graph.
   *
   * Running time:  O(1).
   */
  public int edgeCount() {
    return edgeList.length();
  }

  /**
   * getVertices() returns an array containing all the objects that serve
   * as vertices of the graph.  The array's length is exactly equal to the
   * number of vertices.  If the graph has no vertices, the array has length
   * zero.
   *
   * (NOTE:  Do not return any internal data structure you use to represent
   * vertices!  Return only the same objects that were provided by the
   * calling application in calls to addVertex().)
   *
   * Running time:  O(|V|).
   */
  public Object[] getVertices() {
    Object[] vertices = new Object[vertexList.length()];
    DListNode node = (DListNode) vertexList.front();

    try {
      for (int i = 0; i < vertexList.length(); i++) {
        vertices[i] = ((VertexNode) node.item()).item();
        node = (DListNode) node.next();
      }
      return vertices;
    } catch (InvalidNodeException e) {
      return null;
    }
  }

  /**
   * addVertex() adds a vertex (with no incident edges) to the graph.
   * The vertex's "name" is the object provided as the parameter "vertex".
   * If this object is already a vertex of the graph, the graph is unchanged.
   *
   * Running time:  O(1).
   */
  public void addVertex(Object vertex) {
    if (!isVertex(vertex)) {
      VertexNode vertexNode = new VertexNode(vertex, null);
      DListNode vertexListNode;

      vertexList.insertBack(vertexNode);
      vertexListNode = (DListNode) vertexList.back();
      vertexNode.setDListNode(vertexListNode);
      vertexHashTable.insert(vertex, vertexListNode);
    }
  }

  /**
   * removeVertex() removes a vertex from the graph.  All edges incident on the
   * deleted vertex are removed as well.  If the parameter "vertex" does not
   * represent a vertex of the graph, the graph is unchanged.
   *
   * Running time:  O(d), where d is the degree of "vertex".
   */
  public void removeVertex(Object vertex) {
    if (isVertex(vertex)) {
      Entry newEntry = vertexHashTable.find(vertex);
      DListNode vertexListNode = (DListNode) newEntry.value();

      try {
        ((VertexNode) vertexListNode.item()).removeAllEdge();
        ((VertexNode) vertexListNode.item()).setDListNode(null);
        vertexListNode.remove();
        vertexHashTable.remove(vertex);
      } catch (InvalidNodeException e) {}
    }
  }

  /**
   * isVertex() returns true if the parameter "vertex" represents a vertex of
   * the graph.
   *
   * Running time:  O(1).
   */
  public boolean isVertex(Object vertex) {
    Entry newEntry = vertexHashTable.find(vertex);
    
    if (newEntry != null) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * degree() returns the degree of a vertex.  Self-edges add only one to the
   * degree of a vertex.  If the parameter "vertex" doesn't represent a vertex
   * of the graph, zero is returned.
   *
   * Running time:  O(1).
   */
  public int degree(Object vertex) {
    if (isVertex(vertex)) {
      Entry newEntry = vertexHashTable.find(vertex);
      DListNode vertexListNode = (DListNode) newEntry.value();
      
      try {
        return ((VertexNode) vertexListNode.item()).degree();
      } catch (InvalidNodeException e) {
        return 0;
      }
    } else {
      return 0;
    }
  }

  /**
   * getNeighbors() returns a new Neighbors object referencing two arrays.  The
   * Neighbors.neighborList array contains each object that is connected to the
   * input object by an edge.  The Neighbors.weightList array contains the
   * weights of the corresponding edges.  The length of both arrays is equal to
   * the number of edges incident on the input vertex.  If the vertex has
   * degree zero, or if the parameter "vertex" does not represent a vertex of
   * the graph, null is returned (instead of a Neighbors object).
   *
   * The returned Neighbors object, and the two arrays, are both newly created.
   * No previously existing Neighbors object or array is changed.
   *
   * (NOTE:  In the neighborList array, do not return any internal data
   * structure you use to represent vertices!  Return only the same objects
   * that were provided by the calling application in calls to addVertex().)
   *
   * Running time:  O(d), where d is the degree of "vertex".
   */
  public Neighbors getNeighbors(Object vertex) {
    if (isVertex(vertex)) {
      Entry newEntry = vertexHashTable.find(vertex);
      DListNode vertexListNode = (DListNode) newEntry.value();
      Neighbors neighbors = new Neighbors();

      try {
        VertexNode node = (VertexNode) vertexListNode.item();
        DListNode edgeListNode = (DListNode) node.getEdges().front();

        if (node.getEdges().length() == 0) {
          return null;
        } else {
          neighbors.neighborList = new Object[node.getEdges().length()];
          neighbors.weightList = new int[node.getEdges().length()];
          for (int i = 0; i < node.getEdges().length(); i++) {
            neighbors.neighborList[i] = ((VertexNode) ((EdgeNode) edgeListNode.item()).object2()).item();
            neighbors.weightList[i] = ((EdgeNode) edgeListNode.item()).getWeight();
            edgeListNode = (DListNode) edgeListNode.next();
          }
          return neighbors;
        }
      } catch (InvalidNodeException e) {
        return null;
      }
    }
    return null;
  }

  /**
   * addEdge() adds an edge (u, v) to the graph.  If either of the parameters
   * u and v does not represent a vertex of the graph, the graph is unchanged.
   * The edge is assigned a weight of "weight".  If the graph already contains
   * edge (u, v), the weight is updated to reflect the new value.  Self-edges
   * (where u.equals(v)) are allowed.
   *
   * Running time:  O(1).
   */
  public void addEdge(Object u, Object v, int weight) {

    if (isVertex(u) && isVertex(v)) {
      if (isEdge(u, v)) {
        VertexPair edgeKey = new VertexPair(u, v);
        Entry newEntry = edgeHashTable.find(edgeKey);
        DListNode edgeListNode = (DListNode) newEntry.value();
        
        try {
          EdgeNode partner = ((EdgeNode) edgeListNode.item()).getPartner();
          ((EdgeNode) edgeListNode.item()).setWeight(weight);

          if (partner != null) {
            partner.setWeight(weight);
          }
        } catch (InvalidNodeException e) {}
      } else {
        try {
          Entry uEntry = vertexHashTable.find(u);
          Entry vEntry = vertexHashTable.find(v);
          DListNode uListNode = (DListNode) uEntry.value();
          DListNode vListNode = (DListNode) vEntry.value();
          VertexNode uNode = (VertexNode) uListNode.item();
          VertexNode vNode = (VertexNode) vListNode.item();
          VertexPair edgeKey = new VertexPair(u, v);
          EdgeNode edge = new EdgeNode(uNode, vNode, null, weight);
          EdgeNode partner = edge.setPartner();
          /*System.out.println(edge);
          System.out.println(partner);*/
          DListNode edgeListNode;

          edgeList.insertBack(edge);
          edgeListNode = (DListNode) edgeList.back();
          //System.out.println(((EdgeNode) edgeListNode.item()) == edge);
          edge.setGraphEdgeNode(edgeListNode);
          uNode.addEdge(edge);
         // System.out.println(uNode);
          if (partner != null) {
            partner.setGraphEdgeNode(edgeListNode);
            vNode.addEdge(partner);
          }
          edgeHashTable.insert(edgeKey, edgeListNode);
        } catch (InvalidNodeException e) {}
      }
    }
  }

  /**
   * removeEdge() removes an edge (u, v) from the graph.  If either of the
   * parameters u and v does not represent a vertex of the graph, the graph
   * is unchanged.  If (u, v) is not an edge of the graph, the graph is
   * unchanged.
   *
   * Running time:  O(1).
   */
  public void removeEdge(Object u, Object v) {
    if (isVertex(u) && isVertex(v)) {
      if (isEdge(u, v)) {
        VertexPair edgeKey = new VertexPair(u, v);
        Entry newEntry = edgeHashTable.find(edgeKey);
        DListNode edgeListNode = (DListNode) newEntry.value();
        
        try {
          VertexNode node = (VertexNode) ((EdgeNode) edgeListNode.item()).object1();
          node.removeEdge((EdgeNode) edgeListNode.item());
          edgeHashTable.remove(edgeKey);
        } catch (InvalidNodeException e) {}
      }  
    }
  }

  /**
   * isEdge() returns true if (u, v) is an edge of the graph.  Returns false
   * if (u, v) is not an edge (including the case where either of the
   * parameters u and v does not represent a vertex of the graph).
   *
   * Running time:  O(1).
   */
  public boolean isEdge(Object u, Object v) {
    if (isVertex(u) && isVertex(v)) {
      VertexPair edgeKey = new VertexPair(u, v);
      Entry newEntry = edgeHashTable.find(edgeKey);
    
      if (newEntry != null) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }

  }

  /**
   * weight() returns the weight of (u, v).  Returns zero if (u, v) is not
   * an edge (including the case where either of the parameters u and v does
   * not represent a vertex of the graph).
   *
   * (NOTE:  A well-behaved application should try to avoid calling this
   * method for an edge that is not in the graph, and should certainly not
   * treat the result as if it actually represents an edge with weight zero.
   * However, some sort of default response is necessary for missing edges,
   * so we return zero.  An exception would be more appropriate, but also more
   * annoying.)
   *
   * Running time:  O(1).
   */
  public int weight(Object u, Object v) {
    int weight = 0;
    
    if (isEdge(u, v)) {
      VertexPair edgeKey = new VertexPair(u, v);
      Entry newEntry = edgeHashTable.find(edgeKey);
      DListNode edgeListNode = (DListNode) newEntry.value();

      try {
        EdgeNode edge = (EdgeNode) edgeListNode.item();
        weight = edge.getWeight();
      } catch (InvalidNodeException e) {}
    }
    return weight;
  }

}
