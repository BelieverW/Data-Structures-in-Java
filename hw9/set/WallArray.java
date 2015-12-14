package set;


public class WallArray{
	private int i;
	private int j;
	private boolean isVwalls;

	public WallArray() {
		i = 0;
		j = 0;
		isVwalls = false;
	}

	public WallArray(int i, int j, boolean b) {
		this.i = i;
		this.j = j;
		isVwalls = b;
	}

	public int I() {
		return i;
	}

	public int J() {
		return j;
	}

	public boolean isVwalls(){
		return isVwalls;
	}

	public void setI(int i) {
		this.i = i;
	}

	public void setJ(int j) {
		this.j = j;
	}

	public void setWallType(boolean b) {
		isVwalls = b;
	}

	public void setIJ(int i, int j, boolean b) {
		this.i = i;
		this.j = j;
		isVwalls = b;
	}
}