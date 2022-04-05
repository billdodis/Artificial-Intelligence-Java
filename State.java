import java.util.ArrayList;

public class State {
	private int kostos1;
	private int epektaseis1;
	private ArrayList<Integer> anagnwristiko1 = new ArrayList<Integer>();
	private ArrayList<ArrayList<Integer>> array = new ArrayList<ArrayList<Integer>>();

	public State(ArrayList<ArrayList<Integer>> monopati , int kostos , int epektaseis , ArrayList<Integer> anagnwristiko) {
		for(int i=0;i<monopati.size();i++) {
			array.add(monopati.get(i));
		}
		array.add(anagnwristiko);
		for(int y=0;y<anagnwristiko.size();y++) {
			anagnwristiko1.add(anagnwristiko.get(y));
		}
		kostos1=kostos+1;
		epektaseis1=epektaseis+1;
	}
	public ArrayList<ArrayList<Integer>> getMonopati() {
		return array;
	}
	public int getKostos() {
		return kostos1;
	}
	public int getEpektaseis() {
		return epektaseis1;
	}
	public ArrayList<Integer> getAnagnwristiko(){
		return anagnwristiko1;
	}
	public Boolean sortCheck() {
		Boolean flag = isSorted(anagnwristiko1);
		return flag;
	}
	private boolean isSorted(ArrayList<Integer> list){
	    boolean sorted = true;        
	    for (int i = 1; i < list.size(); i++) {
	        if (list.get(i-1).compareTo(list.get(i)) > 0) sorted = false;
	    }

	    return sorted;
	}
}
