import java.io.*;

class Game{
	String name;
	int price;
	
	public Game(){
		name = "unknown";
		price = 10000000;
	}

	public Game(String name, int price){
		this.name = name;
		this.price = price;
	}

	public String player(){
		return "PureBlack";
	}

	public String toString(){
		String s = new String();
		s = name + ", " + "$" + price + ", player: " + player();
		return s;	
	}
}

class RPG extends Game{
	public RPG(){
		super();
	}
	public RPG(String name, int price){
		super(name, price);
	}

	public String player(){
		return "Kirito";
	}

	public static void main(String[] args){
		RPG game1 = new RPG("Sword Art Online", 10);
		Game game2 = new Game("Call of Duty", 75);
		/*RPG game3 = new RPG();
		Game game4 = new Game();*/
		String s = new String();

		s = ((Game) game1).player();
		//game4 = game3;
		//game2 = game1;
		System.out.println(s);
		System.out.println(game1);
		System.out.println(game2);
		//System.out.println(game4.player());
	}
}