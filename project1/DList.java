public class DList{

	private DListNode head;
	private DListNode tail;
	protected long size;

	public DList(){
		head = null;
		tail = null;
		size = 0;
	}

	public DList(int length){
		DListNode node = new DListNode(length);
		size = 1;
		head = node;
		tail = node;
	}

	public DList(short red, short green, short blue, int length){
		DListNode node = new DListNode(red, green, blue, length);
		size = 1;
		head = node;
		tail = node;
	}

	public void insertEnd(DListNode node){
		if(tail == null){
			head = node;
			tail = node;
		}else{
			tail.next = node;
			node.prev = tail;
			tail = node;
			tail.next = null;
		}
		size++;
	}

	public void insertBetween(DListNode prev, DListNode next, DListNode node){
		node.next = next;
		node.prev = prev;
		prev.next = node;
		next.prev = node;
		size++;
	}

	public void insertFront(DListNode node){
		node.next = head;
		head.prev = node;
		node.prev = null;
		head = node;
	}

	public void removeFront(){
		head.next.prev = null;
		head = head.next;
	}

	public void removeEnd(){
		if(size == 1){
			head = null;
			tail = null;
			size = 0;
		}else{
			tail.prev.next = null;
			tail = tail.prev;
			size--;
		}
	}

	public void removeBetween(DListNode node){
		node.prev.next = node.next;
		node.next.prev = node.prev;

	}

	public void remove(DListNode node){
		if(node.prev != null)
			if(node.next != null)
				removeBetween(node);
			else
				removeEnd();
		else
			removeFront();
	}

	public DListNode getHead(){
		return head;
	}

	public DListNode getTail(){
		return tail;
	}

	public void editBetween(DListNode node, DListNode newNode, int sum, int index){
		DListNode nextNode = new DListNode(node.getRed(), node.getGreen(), node.getBlue(), sum - index);
		nextNode.next = node.next;
		node.setLength(-1 - sum + index + node.getLength());
		newNode.setLength(1);
		insertBetween(node, nextNode, newNode);
	}

	public void editSingle(DListNode node, DListNode newNode){
		if(node.prev == null){
			if(node.next == null)
				node.edit(newNode);
			else{
				if(node.next.equals(newNode)){
					node.next.addLength();
					remove(node);
				}else
					node.edit(newNode);
			}
		}else{
			if(node.next == null){
				if(node.prev.equals(newNode)){
					node.prev.addLength();
					remove(node);
				}else{
					node.edit(newNode);
				}
			}else{
				if(node.prev.equals(newNode)){
					if(node.next.equals(newNode)){
						node.prev.setLength(node.prev.getLength() + 1 + node.next.getLength());
						remove(node.next);
						remove(node);
					}else{
						node.prev.addLength();
						remove(node);
					}
				}else{
					if(node.next.equals(newNode)){
						node.next.addLength();
						remove(node);
					}else{
						node.edit(newNode);
					}
				}
			}
		}
	}


	public void editFront(DListNode node, DListNode newNode){
		if(node.prev != null){
			if(node.prev.equals(newNode)){
				node.prev.addLength();
				remove(node);
			}else{
				newNode.setLength(1);
				node.deductLength();
				insertBetween(node.prev, node, newNode);
			}
		}else{
			newNode.setLength(1);
			node.deductLength();
			insertFront(newNode);
		}

	}

	public void editEnd(DListNode node, DListNode newNode){
		if(node.next != null){
			if(node.next.equals(newNode)){
				node.next.addLength();
				remove(node);
			}else{
				newNode.setLength(1);
				node.deductLength();
				insertBetween(node, node.next, newNode);
			}
		}else{
			newNode.setLength(1);
			node.deductLength();
			insertEnd(newNode);
		}

	}


	public String toString(){
		String result = "[";
		DListNode current = new DListNode();
		current = head;
		while(current != null){
			result += "[" + current.getRed() + ", " + current.getBlue() + ", " + 
					  current.getGreen() + ", [" + current.getLength() + "]" + "]";
			if(current.next != null){
				result += ", ";
			}else{
				result += "]";
			}
			current = current.next;
		}
		return result;
	}

	public static void main(String[] args){
		DList l = new DList((short)1, (short)1, (short)1, 3);
		for(short i = 1; i < 5; i++){
			DListNode node = new DListNode(i,i,i,2*i);
			l.insertEnd(node);
		}
		System.out.println(l);
		l.removeEnd();
		System.out.println(l);
		DList ll = new DList();
		DListNode node = ll.getHead();
		System.out.println(node);

	}
}


