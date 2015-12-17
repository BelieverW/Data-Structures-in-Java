/* Kruskal.java */

package graphalg;

import graph.*;
import set.*;
import queue.*;
import dict.*;

/**
 * The Kruskal class contains the method minSpanTree(), which implements
 * Kruskal's algorithm for computing a minimum spanning tree of a graph.
 */

public class Kruskal {

  /**
   * minSpanTree() returns a WUGraph that represents the minimum spanning tree
   * of the WUGraph g.  The original WUGraph g is NOT changed.
   *
   * @param g The weighted, undirected graph whose MST we want to compute.
   * @return A newly constructed WUGraph representing the MST of g.
   */ 
  public static WUGraph minSpanTree(WUGraph g) {
  	WUGraph t = new WUGraph();
  	Object[] gVertices = g.getVertices();
  	LinkedQueue edgeQueue = new LinkedQueue();
  	DisjointSets set = new DisjointSets(gVertices.length);
  	HashTableChained vertexHashTable = new HashTableChained(gVertices.length);
  	Edge edgeNode;
  	int index1, index2;
  	Object vertex1, vertex2;

  	if (gVertices == null) {
  		return t;
  	} else {
  		for (int i = 0; i < gVertices.length; i++) {
  			t.addVertex(gVertices[i]);
  			vertexHashTable.insert(gVertices[i], i);
  		}
  		for (int i = 0; i < gVertices.length; i++) {
  			Object currVertex = gVertices[i];
  			Neighbors neighbors = g.getNeighbors(currVertex);
  			Object[] neighborsList = neighbors.neighborList ;
  			int[] weightList = neighbors.weightList;
  			for (int j = 0; j < neighborsList.length; j++) {
  				edgeNode = new Edge(currVertex, neighborsList[j], weightList[j]);
  				edgeQueue.enqueue(edgeNode);
  			}
  		}
  		quickSort(edgeQueue);
  		try{
  			while (!edgeQueue.isEmpty()) {
  				edgeNode = (Edge) edgeQueue.dequeue();
  				vertex1 = ((Entry) vertexHashTable.find(edgeNode.object1())).key();
  				vertex2 = ((Entry) vertexHashTable.find(edgeNode.object2())).key();
  				index1 = (int) ((Entry) vertexHashTable.find(edgeNode.object1())).value();
  				index2 = (int) ((Entry) vertexHashTable.find(edgeNode.object2())).value();
  				if (set.find(index1) == set.find(index2)) {
  				} else {
  					t.addEdge(vertex1, vertex2, edgeNode.weight());
  					set.union(set.find(index1), set.find(index2));
  				}
  			}
  		} catch (QueueEmptyException e) {}
  		return t;

  	}
  }

  /**
   *  partition() partitions qIn using the pivot item.  On completion of
   *  this method, qIn is empty, and its items have been moved to qSmall,
   *  qEquals, and qLarge, according to their relationship to the pivot.
   *  @param qIn is a LinkedQueue of Comparable objects.
   *  @param pivot is a Comparable item used for partitioning.
   *  @param qSmall is a LinkedQueue, in which all items less than pivot
   *    will be enqueued.
   *  @param qEquals is a LinkedQueue, in which all items equal to the pivot
   *    will be enqueued.
   *  @param qLarge is a LinkedQueue, in which all items greater than pivot
   *    will be enqueued.  
   **/   
  public static void partition(LinkedQueue qIn, Comparable pivot, 
                               LinkedQueue qSmall, LinkedQueue qEquals, 
                               LinkedQueue qLarge) {
    // Your solution here.
    try {
      while (!qIn.isEmpty()) {
        if (((Comparable) qIn.front()).compareTo(pivot) < 0) {
          qSmall.enqueue(qIn.dequeue());
        } else if (((Comparable) qIn.front()).compareTo(pivot) > 0) {
          qLarge.enqueue(qIn.dequeue());
        } else {
          qEquals.enqueue(qIn.dequeue());
        }
      }
    } catch (QueueEmptyException e) {
      System.err.println(e);
    }
  }

  /**
   *  quickSort() sorts q from smallest to largest using quicksort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/  
  public static void quickSort(LinkedQueue q) {
    // Your solution here.
    int index = (int) (Math.random()*q.size());
    LinkedQueue qSmall = new LinkedQueue();
    LinkedQueue qEquals = new LinkedQueue();
    LinkedQueue qLarge = new LinkedQueue();
    Comparable pivot;

    if (q.size() <= 1) {
      return;
    } else {
      if (index == 0) {
        index = 1;
      }
      pivot = (Comparable) q.nth(index);
      partition(q, pivot, qSmall, qEquals, qLarge);
      quickSort(qSmall);
      quickSort(qLarge);
      q.append(qSmall);
      q.append(qEquals);
      q.append(qLarge);
    }
  }

}
