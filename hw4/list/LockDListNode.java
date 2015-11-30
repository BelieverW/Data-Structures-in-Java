/* LockDListNode.java */

package list;

public class LockDListNode extends DListNode{
	boolean isLocked;

	LockDListNode(Object i, DListNode p, DListNode n){
		super(i, p, n);
		isLocked = false;
	}
}