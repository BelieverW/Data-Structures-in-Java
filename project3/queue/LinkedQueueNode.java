/* LinkedQueueNode.java */

package queue;

/**
 *  LinkedQueueNode is a class used internally by the SList class.  An SList object
 *  is a singly-linked list, and an LinkedQueueNode is a node of a singly-linked
 *  list.  Each LinkedQueueNode has two references:  one to an object, and one to
 *  the next node in the list.
 */

class LinkedQueueNode {
  Object item;
  LinkedQueueNode next;

  /**
   *  LinkedQueueNode() (with one parameter) constructs a list node referencing the
   *  item "obj".
   */

  LinkedQueueNode(Object obj) {
    item = obj;
    next = null;
  }

  /**
   *  LinkedQueueNode() (with two parameters) constructs a list node referencing the
   *  item "obj", whose next list node is to be "next".
   */

  LinkedQueueNode(Object obj, LinkedQueueNode next) {
    item = obj;
    this.next = next;
  }

}
