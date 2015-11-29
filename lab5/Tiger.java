import java.io.*;

class Animals{
	int size;
	int age;
	boolean isMale;
	String s = new String();
	
	public Animals(){
		size = 5;
		age = 10;
		isMale = true;
	}
	public Animals(int size){
		this.size = size;
		age = 10;
		isMale = true;
	}
	public void outputInfo(){
		s = "The size is " + size + ", the age is " + age + ", is it a male? " + isMale;
		System.out.println(s);
	}

/*	public static void main(String[] args){
		Animals t = new Animals();
		t.outputInfo();
	}*/
}

public class Tiger extends Animals{
	
	int[] color = new int[3];

	public Tiger(){
		super();
		color[0] = 0;
		color[1] = 255;
		color[2] = 255;
	}

	public Tiger(int size){
		super(size);
		color[0] = 0;
		color[1] = 255;
		color[2] = 255;
	}

	public void outputInfo(){
		super.outputInfo();
		s = s + " and the color are " + color[0] + ", " + color[0] + ", " + color[0];
		System.out.println(s);
	}

	public static void main(String[] args){
		Tiger[] ya = new Tiger[3];
		Animals[] xa = new Animals[3];
		Animals animal1, animal2;
		
		ya[0] = new Tiger(1);
		ya[1] = new Tiger(10);
		ya[2] = new Tiger(100);
		animal1 = ya[2];
		animal1.outputInfo();
		ya[1] = (Tiger) animal1;
		animal2 = ya[1];
		animal2.outputInfo();

	}

}