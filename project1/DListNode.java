public class DListNode{

	private short red;
	private short green;
	private short blue;
	private int length;

	public DListNode prev;
	public DListNode next;

	public DListNode(){
		red = 0;
		green = 0;
		blue = 0;
		length = 0;
		prev = null;
		next = null;
	}

	public DListNode(int length){
		this.length = length;
		red = 0;
		green = 0;
		blue = 0;
		prev = null;
		next = null;
	}

	public DListNode(short red, short green, short blue){
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.length = 0;
		prev = null;
		next = null;
	}

	public DListNode(short red, short green, short blue, int length){
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.length = length;
		prev = null;
		next = null;
	}

	public void setLength(int length){
		this.length = length;
	}

	public void setRed(short red){
		this.red = red;
	}

	public void setBlue(short blue){
		this.blue = blue;
	}

	public void setGreen(short green){
		this.green = green;
	}

	public void addLength(){
		this.length ++;
	}

	public void deductLength(){
		this.length--;
	}

	public short getRed(){
		return red;
	}

	public short getBlue(){
		return blue;
	}


	public short getGreen(){
		return green;
	}


	public int getLength(){
		return length;
	}

	public void edit(DListNode other){
		this.red = other.red;
		this.green = other.green;
		this.blue = other.blue;
	}
	
	public boolean equals(DListNode other){
		if(this.red == other.red && this.green == other.green && this.blue == other.blue)
			return true;
		else
			return false;
	}

	public String toString(){
		String result = new String();
		result = "[" + red + ", " + blue + ", " + green + ", [" + length + "]" + "]";
		return result;
	}
}