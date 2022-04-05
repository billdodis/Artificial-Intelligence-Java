import java.util.ArrayList;
//import java.util.List;
import java.util.Scanner; 
public class Main {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		ArrayList<Integer> lista = new ArrayList<Integer>();
		System.out.println("Θέλετε να δώσετε εσείς τον αριθμό των στοιχείων ; Απαντήστε με 'YES' ή 'NO'.");
		String str=sc.nextLine();
		String yes="YES";
		if(str.equals(yes)) {
			System.out.println("Παρακαλώ εισάγετε τον αριθμό των στοιχείων της λίστας.");
			int n=sc.nextInt();
			//int[] list = new int[n];
			boolean flag;
			//arr.contains(2);
			System.out.println("Παρακαλώ εισάγετε την αρχική κατάσταση της λίστας, Έναν αριθμό κάθε φορά.");
			for(int i=0;i<n;i++) {
				int num = sc.nextInt();
				flag=lista.contains(num);
				if(flag!=true) {
					lista.add(num);
				}
				else {
					System.out.println("Λάθος εισαγωγή αριθμού έχετε ξαναεισάγει τον " + num + " Συνεχίστε εισάγωντας έναν άλλον αριθμό.");
					i=i-1;
				}
			}
			//for(int y=0;y<n;y++) {
				//list[y]=lista.get(y);
			//}
		} 
		else {
			System.out.println("Παρακαλώ εισάγετε την αρχική κατάσταση της λίστας, Έναν αριθμό κάθε φορά. Όταν τελειώσουν τα στοιχεία του πίνακα απλά δώστε μας ένα 'OK' για να καταλάβουμε πότε τελειώνει η 'λίστα'.");
			int num;
			String string = sc.nextLine();
			Boolean check;
			String ok="OK";
			while(string.equals(ok)!=true) {
				Boolean flag = isInt(string);
				if(flag==true) {
			    	num=Integer.parseInt(string);
			    	check = lista.contains(num);
			    	if(check!=true) {
			    		lista.add(num);
			    	}
			    	else {
			    		System.out.println("Λάθος εισαγωγή αριθμού έχετε ξαναεισάγει τον " + num + " Συνεχίστε εισάγωντας έναν άλλον αριθμό.");
			    	}
				}
				else {
					System.out.println("Περίμενα 'OK' για να τελειώσω την λίστα ή έναν αριθμό για να τελειώσω την δημιουργία της λίστας.");
				}
				string =sc.next();
			}
			//int count = lista.size();
			//int[] list = new int[count];
			//for(int y=0;y<count;y++) {
				//list[y]=lista.get(y);
			//}
		}
		ArrayList<Integer> kappa = new ArrayList<Integer>();
		//GIA ARXIKH KATASTASH DEN THA FTIAKSUME STATE 
		ArrayList<ArrayList<Integer>> monopati = new ArrayList<ArrayList<Integer>>();
		ArrayList<State> statelist = new ArrayList<State>();
		ArrayList<State> statelist1 = new ArrayList<State>();
		ArrayList<ArrayList<State>> statelist2 = new ArrayList<ArrayList<State>>();
		ArrayList<ArrayList<State>> statelist3 = new ArrayList<ArrayList<State>>();
		monopati.add(lista);
		for(int z=1;z<lista.size()+1;z++) {
			kappa = T(lista,z);
			State child = new State(monopati,0,0,kappa);
			statelist.add(child);
			if(child.sortCheck()==true) {
				System.out.println("μονοπάτι : " + child.getMonopati() + "\n κόστος : " + child.getKostos() + "\n επεκτάσεις : " + child.getEpektaseis());
				System.exit(0);
			}
		}
		for(int z=0;z<statelist.size();z++) {
			State x = statelist.get(z);
			statelist1 = maieuthrio(x);
			statelist2.add(statelist1);
		}
		sc.close();
		while(true) {
			for(int z=0;z<statelist2.size();z++) {
				for(int i=0;i<statelist2.get(z).size();i++) {
					State x = statelist2.get(z).get(i);
					statelist1 = maieuthrio(x);
					statelist3.add(statelist1);
				}
			}
			statelist2.clear();
			statelist2=new ArrayList<ArrayList<State>>(statelist3);
			statelist3.clear();		
		}
	}
	public static boolean isInt(String str) {
		
	  	try {
	      	@SuppressWarnings("unused")
	    	int x = Integer.parseInt(str);
	      	return true; //String is an Integer
		} catch (NumberFormatException e) {
	    	return false; //String is not an Integer
		}
	}
	public static ArrayList<Integer> T(ArrayList<Integer> array,int k){
		ArrayList<Integer> alfaleft= new ArrayList<Integer>();
		ArrayList<Integer> alfaright= new ArrayList<Integer>();
		ArrayList<Integer> rotatedalfaleft= new ArrayList<Integer>();
		ArrayList<Integer> beta= new ArrayList<Integer>();
		for(int i=0;i<k;i++) {
			alfaleft.add(array.get(i));
		}
		for(int i=k;i<array.size();i++) {
			alfaright.add(array.get(i));
		}
		for(int i=0;i<alfaleft.size();i++) {
			rotatedalfaleft.add(alfaleft.get(alfaleft.size()-1-i));
		}
		for(int i=0;i<rotatedalfaleft.size();i++) {
			beta.add(rotatedalfaleft.get(i));
		}
		for(int i=0;i<alfaright.size();i++) {
			beta.add(alfaright.get(i));
		}
		return beta;
	}
	public static ArrayList<State> maieuthrio(State state){
		ArrayList<Integer> kappa = new ArrayList<Integer>();
		ArrayList<State> statelist = new ArrayList<State>();
		for(int z=1;z<state.getAnagnwristiko().size()+1;z++) {
			kappa = T(state.getAnagnwristiko(),z);
			State child = new State(state.getMonopati(),state.getKostos(),state.getEpektaseis(),kappa);
			statelist.add(child);
			if(child.sortCheck()==true) {
				System.out.println("μονοπάτι : " + child.getMonopati() + "\n κόστος : " + child.getKostos() + "\n επεκτάσεις : " + child.getEpektaseis());
				System.exit(0);
			}
		}
		return statelist;
	}
}
