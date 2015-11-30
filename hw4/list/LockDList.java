/* LockDList.java */

package list;

public class LockDList extends DList{
	
	public void lockNode(DListNode node){
		((LockDListNode) node).isLocked = true;
	}

	
	protected DListNode newNode(Object item, DListNode prev, DListNode next) {
    	return new LockDListNode(item, prev, next);
  	}

	public LockDList() {
    //  Your solution here.
    super();
	}

  	public void remove(DListNode node) {
    // Your solution here.
  		if (((LockDListNode) node).isLocked == false){
  			if (node == null)
      			return;
    		else{
      			node.prev.next = node.next;
      			node.next.prev = node.prev;
      			size--;
    		}
  		}else{
  			return;
  		}
  	}

}
