import java.io.*;

interface friend{
	public abstract void play(String s);
}

class people{
	int age;
	boolean isMale;
	boolean isSingle;
	String newString;

	public people(){
		age = 20;
		isMale = true;
		isSingle = true;
	}

	public people(int age, boolean isMale){
		this.age = age;
		this.isMale = isMale;
		isSingle = true;
	}

	public void play(String newString){
		String s = new String();
		s = "we are family!" + newString;
		System.out.println(s);
	}
}

public class classmates extends people implements friend{

	public classmates(){
		super();
	}

	public classmates(int age, boolean isMale){
		super(age, isMale);
	}
	
	public void play(String newString){
		String s = new String();
		s = "Yes! We are " + newString;
		System.out.println(s);
	}


	public static void main(String[] args){
		classmates A = new classmates(22, false);
		classmates B = new classmates(25, false);
		String newString = new String();
		String newString2 = new String();
		newString = "伐木累！";
		newString2 = "family!";
		A.play(newString);
		B.play(newString2);

	}
}