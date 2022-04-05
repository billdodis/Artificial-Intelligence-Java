
public class Board {
	int ni;
	int mi;
	String[][] myarray = new String[mi][ni];
	public Board(String[][] array,int m ,int n) {
		mi=m;
		ni=n;
		myarray=array;
	}
	
	public String[][] getArray() {
		return myarray;
	}
}

