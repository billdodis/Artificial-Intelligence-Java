// TILEMACHOS MARKOS BAZAKAS 3281
// BASILEIOS DODIS 3300
// ZISIS PROKOPIOS TALAMAGKAS 3340

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
public class MinimaxMain {
	public static void main(String[] args) {
		
		int num,num1,num2;
		String EndGameA = "";
		String EndGameB = "";
		String EndGame = "";
		Scanner sc=new Scanner(System.in);
		System.out.println("ΚΑΝΟΝΕΣ: Το όριο των κινήσεων κάθε παίκτη είναι μέχρι 2 θέσεις.");
		System.out.println("Παρακαλώ εισάγετε μία μία τις διαστασεις του πίνακα , π.χ για 2 (enter) 3 (enter) ο χώρος διαστάσεων θα είναι 2x3(max 4x4).");
		int m=sc.nextInt();
		int n=sc.nextInt();
		System.out.println("Οι διαστάσεις πάρθηκαν με επιτυχία. Ο πίνακας είναι ο εξής: ");
		String[][] array = new String[m][n];
		for(int x=0;x<array.length;x++) {
			for(int y=0;y<array[x].length;y++) {
				array[x][y] = "-";
			}
		}
		print(array);
		System.out.println("Θέλετε να εισάγετε απαγορευμένες περιοχές στις διαστάσεις του παιχνιδιού; Πληκτρολογείστε 'YES' για ναι αλλιώς οτιδήποτε άλλο.");
		String strong;
		String yes="YES";
		String ok="OK";
		strong=sc.next();
		if(strong.equals(yes)) {
			System.out.println("Πληκτρολογείστε μία μία τις θέσεις που θέλετε να είναι απαγορευμένες, για την πρώτη θέση πληκτρολογείστε 11 κτλ, όταν τελειώσετε με τις απαγορευμένες θέσεις πληκτρολογείστε 'OK'.");
			String get=sc.next();
			while(!get.equals(ok)) {
				num=Integer.parseInt(get);
				num1=num/10;
				num2=num%10;
				array[num1-1][num2-1] = "X";
				get=sc.next();
			}
			System.out.println("Οι απαγορευμένες θέσεις πάρθηκαν με επιτυχία.");
			print(array);
		} 
		System.out.println("Εισάγετε την θέση από την οποία θέλετε να ξεκινήσει το παιχνίδι ο Α(υπολογιστής), για την πρώτη θέση πληκτρολογείστε 11 κτλ");
		String thesiA = sc.next();
		num=Integer.parseInt(thesiA);
		num1=num/10;
		num2=num%10;
		while(array[num1-1][num2-1]=="X") {
            System.out.println("Εισάγατε μια θέση που έχετε επιλέξει ως απαγορευμένη.");
            System.out.println("Παρακαλώ εισάγετε μία έγκυρη θέση για τον παίκτη Α.");
            thesiA = sc.next();
            num=Integer.parseInt(thesiA);
            num1=num/10;
            num2=num%10;
        }
		array[num1-1][num2-1] = "A";
		print(array);
		System.out.println("Εισάγετε την θέση από την οποία θέλετε να ξεκινήσει το παιχνίδι ο Β(εσείς), για την πρώτη θέση πληκτρολογείστε 11 κτλ");
		String thesiB = sc.next();
		int num3=Integer.parseInt(thesiB);
		int num4=num3/10;
		int num5=num3%10;
		while(array[num4-1][num5-1]== "X" || array[num4-1][num5-1]=="A") {
			if(array[num4-1][num5-1]=="A") {
				System.out.println("Εισάγατε μια θέση όπου έχετε επιλέξει να ξεκινάει ο παίκτης Α.");
				System.out.println("Παρακαλώ εισάγετε μία έγκυρη θέση για τον παίκτη B.");
				thesiB = sc.next();
				num3=Integer.parseInt(thesiB);
				num4=num3/10;
				num5=num3%10;
			}
			else {
				System.out.println("Εισάγατε μια θέση που έχετε επιλέξει ως απαγορευμένη.");
	            System.out.println("Παρακαλώ εισάγετε μία έγκυρη θέση για τον παίκτη B.");
	            thesiB = sc.next();
	            num3=Integer.parseInt(thesiB);
	            num4=num3/10;
	            num5=num3%10;
			}
        }
		array[num4-1][num5-1] = "B";
		print(array);
		System.out.println(" Πληκτρολογήστε οτιδήποτε για να ξεκινήσει το παιχνίδι, αλλιώς πληκτρολογήστε 'EXIT'.");
		String startGame = sc.next();
		String exit = "EXIT";
		//ArrayList<Integer> movement1 = new ArrayList<Integer>();
		if(startGame.equals(exit)) {
			System.out.println("Γεια σας!");
			sc.close();
			System.exit(0);
		}
		else {
			while(true) {
				EndGame = checkWinner(array);
				if(EndGame == "TIE") {
					System.out.println("Αποτέλεσμα παιχνιδιού : ΙΣΟΠΑΛΊΑ!");
					sc.close();
					System.exit(0);
				}
				if(EndGame == "A") {
					System.out.println("Αποτέλεσμα παιχνιδιού : Ο ΝΙΚΗΤΉΣ ΕΊΝΑΙ Ο ΠΑΊΚΤΗΣ Α! Ο υπολογιστής κερδίζει.");
					sc.close();
					System.exit(0);
				}
				if(EndGame == "B") {
					System.out.println("Αποτέλεσμα παιχνιδιού : Ο ΝΙΚΗΤΉΣ ΕΊΝΑΙ Ο ΠΑΊΚΤΗΣ Β! Ο παίκτης κερδίζει.");
					sc.close();
					System.exit(0);
				}
				System.out.println("\nΗ κίνηση του Α είναι: ");
				boolean[] alpha = new boolean[2];
				boolean[] beta = new boolean[2];
				alpha=bestMove(array);
				EndGameA = checkWinner(array);
				if(EndGameA == "TIE") {
					System.out.println("Αποτέλεσμα παιχνιδιού : ΙΣΟΠΑΛΊΑ!");
					sc.close();
					System.exit(0);
				}
				if(EndGameA == "A") {
					System.out.println("Αποτέλεσμα παιχνιδιού : Ο ΝΙΚΗΤΉΣ ΕΊΝΑΙ Ο ΠΑΊΚΤΗΣ Α! Ο υπολογιστής κερδίζει.");
					sc.close();
					System.exit(0);
				}
				if(EndGameA == "B") {
					System.out.println("Αποτέλεσμα παιχνιδιού : Ο ΝΙΚΗΤΉΣ ΕΊΝΑΙ Ο ΠΑΊΚΤΗΣ Β! Ο παίκτης κερδίζει.");
					sc.close();
					System.exit(0);
				}
				//print(array);
				System.out.println("Παρακαλώ , εισάγετε την κίνηση που θέλετε να κάνετε σαν παίχτης Β (πχ 11 ή 12 ή 21 κλπ).");
				ArrayList<Integer> movement = new ArrayList<Integer>();
				String b = sc.next();
				int num6=Integer.parseInt(b);
				int num7=num6/10 -1;
			    int num8=num6%10 -1;
			    movement.add(num7);
			    movement.add(num8);
			    beta=playerB(array,movement);
			    //
			    //
				while(beta[0]==true && beta[1]==false) {
					ArrayList<Integer> movement2 = new ArrayList<Integer>();
					System.out.println("Δώστε μία έγκυρη κίνηση");
					String g = sc.next();
					int nume6=Integer.parseInt(g);
					int nume7=nume6/10 -1;
				    int nume8=nume6%10 -1;
				    movement2.add(nume7);
				    movement2.add(nume8);
					beta = playerB(array,movement2);
				}
				//
				EndGameB = checkWinner(array);
				if(EndGameB == "TIE") {
					System.out.println("Αποτέλεσμα παιχνιδιού : ΙΣΟΠΑΛΊΑ!");
					sc.close();
					System.exit(0);
				}
				if(EndGameB == "A") {
					System.out.println("Αποτέλεσμα παιχνιδιού : Ο ΝΙΚΗΤΉΣ ΕΊΝΑΙ Ο ΠΑΊΚΤΗΣ Α! Ο υπολογιστής κερδίζει.");
					sc.close();
					System.exit(0);
				}
				if(EndGameB == "B") {
					System.out.println("Αποτέλεσμα παιχνιδιού : Ο ΝΙΚΗΤΉΣ ΕΊΝΑΙ Ο ΠΑΊΚΤΗΣ Β! Ο παίκτης κερδίζει.");
					sc.close();
					System.exit(0);
				}
				//
			}
		}
		//SOS: OTAN PAIRNUME APO TON XRHSTH THESI,THN PAIRNUME STO PROGRAMMA ME -1 GIATI ETSI EXUME FTIAKSEI TO PROGRAMMA KAI DEN THA RWTHSUME KAI KANENA PWS THA TO GRAPSUME KAI OLAS.
	}
	public static ArrayList<ArrayList<Integer>> allowedmoves(String[][] arrayz,String AorB) {
		ArrayList<ArrayList<Integer>> allowedarray = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> finalallowedarray = new ArrayList<ArrayList<Integer>>();
		allowedarray=checkup(arrayz,AorB);
		for(int i=0;i<allowedarray.size();i++) {
			finalallowedarray.add(allowedarray.get(i));
		}
		allowedarray.clear();
		allowedarray=checkdown(arrayz,AorB);
		for(int i=0;i<allowedarray.size();i++) {
			finalallowedarray.add(allowedarray.get(i));
		}
		allowedarray.clear();
		allowedarray=checkleft(arrayz,AorB);
		for(int i=0;i<allowedarray.size();i++) {
			finalallowedarray.add(allowedarray.get(i));
		}
		allowedarray.clear();
		allowedarray=checkright(arrayz,AorB);
		for(int i=0;i<allowedarray.size();i++) {
			finalallowedarray.add(allowedarray.get(i));
		}
		allowedarray.clear();
		allowedarray=checkupleft(arrayz,AorB);
		for(int i=0;i<allowedarray.size();i++) {
			finalallowedarray.add(allowedarray.get(i));
		}
		allowedarray.clear();
		allowedarray=checkupright(arrayz,AorB);
		for(int i=0;i<allowedarray.size();i++) {
			finalallowedarray.add(allowedarray.get(i));
		}
		allowedarray.clear();
		allowedarray=checkdownleft(arrayz,AorB);
		for(int i=0;i<allowedarray.size();i++) {
			finalallowedarray.add(allowedarray.get(i));
		}
		allowedarray.clear();
		allowedarray=checkdownright(arrayz,AorB);
		for(int i=0;i<allowedarray.size();i++) {
			finalallowedarray.add(allowedarray.get(i));
		}
		return finalallowedarray;
		
	}
	public static boolean[] move(String[][] arrayz,String AorB,ArrayList<Integer> movement, String PlayerBorScore) {
		boolean id[] = new boolean[2];
		String B = "B";
		int newM = movement.get(0);
		int newN = movement.get(1);
		int MI=newM+1;
		int NI=newN+1;
		String playerB = "playerB";
		//String playerScore = "score";
		ArrayList<Integer> position = new ArrayList<Integer>();
		ArrayList<Integer> position1 = new ArrayList<Integer>();
		position = getPos(arrayz,AorB);
		//System.out.println("position is:");
		//System.out.println(position);
		int M = position.get(0);
		int N = position.get(1);
		boolean flag=false;
		position.clear();
		ArrayList<ArrayList<Integer>> allowedarray = new ArrayList<ArrayList<Integer>>();
		allowedarray = allowedmoves(arrayz,AorB);
		if(allowedarray.isEmpty()) {
			id[0]=false;
			id[1]=false;
			return id;
		}
		else {
			id[0]=true;
		}
		position1.add(newM);
		position1.add(newN);
		for(int i=0;i<allowedarray.size();i++) {
			//System.out.println("EIMAI STHN MOVE CHECKARW POSITION PRWTA KAI META STOIXEIO TIS ALLOWEDARAy");
			//System.out.println(position1);
			//System.out.println(allowedarray.get(i));
			if(position1.equals(allowedarray.get(i))) {
				flag=true;
				break;
			}
		}
		if(flag!=true) {
			if(AorB == B && PlayerBorScore == playerB) {
				System.out.println("Η κίνηση σας: "+MI+NI+", δεν επιτρέπεται.");
				id[1]=false;
				return id;
			}
			id[1]=false;
			return id;
			
			//DEN VOITHAEI GIA DEBUGGING AUTO TO SYSTEMOUT. EXUME +1 STA M KAI N GIA NA VGAINEI AUTO STON XRHSTH KAI OXI SE EMAS.KAI DEN THA RWTHSUME KAI KANENA KAI OLAS PWS THA KANUME EMEIS DEBUG.
		}
		int testM = newM-M;
		int testN = newN-N;
		arrayz[M][N] = "X";
		arrayz[newM][newN] = AorB;
		if(testM==-2){
			if(testN==0) {
				arrayz[M-1][N]="X";
			}
			if(testN==-2) {
				arrayz[M-1][N-1]="X";			
			}
			if(testN==2) {
				arrayz[M-1][N+1]="X";
			}
		}
		if(testM==2) {
			if(testN==0) {
				arrayz[M+1][N]="X";
			}
			if(testN==-2) {
				arrayz[M+1][N-1]="X";			
			}
			if(testN==2) {
				arrayz[M+1][N+1]="X";
			}
		}
		if(testM==0) {
			if(testN==-2) {
				arrayz[M][N-1]="X";			
			}
			if(testN==2) {
				arrayz[M][N+1]="X";
			}
		}
		if(AorB == B && PlayerBorScore == playerB) {
            System.out.println("Η κίνηση σας έγινε επιτυχώς.");
            id[1]=true;
            return id;
            
        }
		id[1]=true;
		return id;
	}
	//public static minimax() {
		
	//}
	public static ArrayList<Integer> getPos(String[][]arrayz,String AorB){
		ArrayList<Integer> array = new ArrayList<Integer>();
		for(int x=0;x<arrayz.length;x++) {
			for(int y=0;y<arrayz[x].length;y++) {
				if(arrayz[x][y]==AorB) {
					array.add(x);
					array.add(y);
				}
			}
		}
		return array;
	}
	public static ArrayList<ArrayList<Integer>> checkup(String[][] arrayz,String AorB){
		ArrayList<Integer> array = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> allowedarray = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> array1 = new ArrayList<Integer>();
		String finalstr;
		if(AorB=="A") {
			finalstr="B";
		}
		else {
			finalstr="A";
		}
		array = getPos(arrayz,AorB);
		int m=array.get(0);
		int n=array.get(1);
		String xi="X";
		int x=m-1;
		int y=m-2;
		array.clear();
		if(x>=0) {
			if(arrayz[x][n]!=xi && arrayz[x][n]!=finalstr) {
				array1.add(x);
				array1.add(n);
				allowedarray.add(array1);
				if(y>=0) {
					if(arrayz[y][n]!=xi && arrayz[y][n]!=finalstr) {
						array.add(y);
						array.add(n);
						allowedarray.add(array);
					}
				}
			}
			
		}
		
		return allowedarray;
	}
	public static ArrayList<ArrayList<Integer>> checkdown(String[][] arrayz,String AorB){
		ArrayList<Integer> array = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> allowedarray = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> array1 = new ArrayList<Integer>();
		String finalstr;
		if(AorB=="A") {
			finalstr="B";
		}
		else{
			finalstr="A";
		}
		array = getPos(arrayz,AorB);
		int oldM=arrayz.length;
		//int oldN=arrayz[0].length;
		int m=array.get(0);
		int n=array.get(1);
		String xi="X";
		int x=m+1;
		int y=m+2;
		array.clear();
		if(x<=oldM-1) {
			if(arrayz[x][n]!=xi && arrayz[x][n]!=finalstr) {
				array1.add(x);
				array1.add(n);
				allowedarray.add(array1);
				if(y<=oldM-1) {
					if(arrayz[y][n]!=xi && arrayz[y][n]!=finalstr) {
						array.add(y);
						array.add(n);
						allowedarray.add(array);
					}
				}
			}
		}
		
		return allowedarray;
	}
	public static ArrayList<ArrayList<Integer>> checkleft(String[][] arrayz,String AorB){
		ArrayList<Integer> array = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> allowedarray = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> array1 = new ArrayList<Integer>();
		String finalstr;
		if(AorB=="A") {
			finalstr="B";
		}
		else {
			finalstr="A";
		}
		array = getPos(arrayz,AorB);
		int m=array.get(0);
		int n=array.get(1);
		String xi="X";
		int x=n-1;
		int y=n-2;
		array.clear();
		if(x>=0) {
			if(arrayz[m][x]!=xi && arrayz[m][x]!=finalstr) {
				array1.add(m);
				array1.add(x);
				allowedarray.add(array1);
				if(y>=0) {
					if(arrayz[m][y]!=xi && arrayz[m][y]!=finalstr) {
						array.add(m);
						array.add(y);
						allowedarray.add(array);
					}
				}
			}
		}
		
		return allowedarray;
	}
	public static ArrayList<ArrayList<Integer>> checkright(String[][] arrayz,String AorB){
		ArrayList<Integer> array = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> allowedarray = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> array1 = new ArrayList<Integer>();
		String finalstr;
		if(AorB=="A") {
			finalstr="B";
		}
		else {
			finalstr="A";
		}
		array = getPos(arrayz,AorB);
		//int oldM=arrayz.length;
		int oldN=arrayz[0].length;
		int m=array.get(0);
		int n=array.get(1);
		String xi="X";
		int x=n+1;
		int y=n+2;
		array.clear();
		if(x<=oldN-1) {
			if(arrayz[m][x]!=xi && arrayz[m][x]!=finalstr) {
				array1.add(m);
				array1.add(x);
				allowedarray.add(array1);
				if(y<=oldN-1) {
					if(arrayz[m][y]!=xi && arrayz[m][y]!=finalstr) {
						array.add(m);
						array.add(y);
						allowedarray.add(array);
					}
				}
			}
		}
		
		return allowedarray;
	}
	public static ArrayList<ArrayList<Integer>> checkupleft(String[][] arrayz,String AorB){
		ArrayList<Integer> array = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> allowedarray = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> array1 = new ArrayList<Integer>();
		String finalstr;
		if(AorB=="A") {
			finalstr="B";
		}
		else {
			finalstr="A";
		}
		array = getPos(arrayz,AorB);
		int m=array.get(0);
		int n=array.get(1);
		String xi="X";
		int xm=m-1;
		int xn=n-1;
		int ym=m-2;
		int yn=n-2;
		array.clear();
		if(xm>=0 && xn>=0) {			
			if(arrayz[xm][xn]!=xi && arrayz[xm][xn]!=finalstr) {
				array1.add(xm);
				array1.add(xn);
				allowedarray.add(array1);
				if(ym>=0 && yn>=0) {
					if(arrayz[ym][yn]!=xi && arrayz[ym][yn]!=finalstr) {
						array.add(ym);
						array.add(yn);
						allowedarray.add(array);
					}
				}
			}
		}
		
		return allowedarray;
	}
	public static ArrayList<ArrayList<Integer>> checkupright(String[][] arrayz,String AorB){
		ArrayList<Integer> array = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> allowedarray = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> array1 = new ArrayList<Integer>();
		String finalstr;
		if(AorB=="A") {
			finalstr="B";
		}
		else {
			finalstr="A";
		}
		array = getPos(arrayz,AorB);
		//int oldM=arrayz.length;
		int oldN=arrayz[0].length;
		int m=array.get(0);
		int n=array.get(1);
		String xi="X";
		int xm=m-1;
		int xn=n+1;
		int ym=m-2;
		int yn=n+2;
		array.clear();
		if(xm>=0 && xn<=oldN-1) {			
			if(arrayz[xm][xn]!=xi && arrayz[xm][xn]!=finalstr) {
				array1.add(xm);
				array1.add(xn);
				allowedarray.add(array1);
				if(ym>=0 && yn<=oldN-1) {
					if(arrayz[ym][yn]!=xi && arrayz[ym][yn]!=finalstr) {
						array.add(ym);
						array.add(yn);
						allowedarray.add(array);
					}
				}
			}
		}
		
		return allowedarray;
	}
	public static ArrayList<ArrayList<Integer>> checkdownleft(String[][] arrayz,String AorB){
		ArrayList<Integer> array = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> allowedarray = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> array1 = new ArrayList<Integer>();
		String finalstr;
		if(AorB=="A") {
			finalstr="B";
		}
		else {
			finalstr="A";
		}
		array = getPos(arrayz,AorB);
		int oldM=arrayz.length;
		//int oldN=arrayz[0].length;
		int m=array.get(0);
		int n=array.get(1);
		String xi="X";
		int xm=m+1;
		int xn=n-1;
		int ym=m+2;
		int yn=n-2;
		array.clear();
		if(xm<=oldM-1 && xn>=0) {			
			if(arrayz[xm][xn]!=xi && arrayz[xm][xn]!=finalstr) {
				array1.add(xm);
				array1.add(xn);
				allowedarray.add(array1);
				if(ym<=oldM-1 && yn>=0) {
					if(arrayz[ym][yn]!=xi && arrayz[ym][yn]!=finalstr) {
						array.add(ym);
						array.add(yn);
						allowedarray.add(array);
					}
				}
			}
		}
		
		return allowedarray;
	}
	public static ArrayList<ArrayList<Integer>> checkdownright(String[][] arrayz,String AorB){
		ArrayList<Integer> array = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> allowedarray = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> array1 = new ArrayList<Integer>();
		String finalstr;
		if(AorB=="A") {
			finalstr="B";
		}
		else {
			finalstr="A";
		}
		array = getPos(arrayz,AorB);
		int oldM=arrayz.length;
		int oldN=arrayz[0].length;
		int m=array.get(0);
		int n=array.get(1);
		String xi="X";
		int xm=m+1;
		int xn=n+1;
		int ym=m+2;
		int yn=n+2;
		array.clear();
		if(xm<=oldM-1 && xn<=oldN-1) {			
			if(arrayz[xm][xn]!=xi && arrayz[xm][xn]!=finalstr) {
				array1.add(xm);
				array1.add(xn);
				allowedarray.add(array1);
				if(ym<=oldM-1 && yn<=oldN-1) {
					if(arrayz[ym][yn]!=xi && arrayz[ym][yn]!=finalstr) {
						array.add(ym);
						array.add(yn);
						allowedarray.add(array);
					}
				}
			}
		}
		
		return allowedarray;
	}
	public static void print(String[][] arrayz) {
        int n=arrayz[0].length;
        System.out.print("  ");
        for(int i=0;i<n;i++) {
        	int ai = i+1;
            System.out.print(" "+ai +"  ");
        }
        System.out.print("\n");
        for(int z=0;z<arrayz.length;z++) {
        	int zey = z+1;
            System.out.print(zey+"| ");
            for(int y=0;y<arrayz[z].length;y++) {
                System.out.print(arrayz[z][y]+" | ");
            }
            System.out.print("\n");
        }
    }
	public static boolean[] playerB(String[][] arrayz,ArrayList<Integer> movement) {
		boolean[] id = new boolean[2];
		String B = "B";
		String playerB = "playerB";
		id=move(arrayz,B,movement,playerB);
		print(arrayz);
		return id;
	}
	public static int minimax(String[][] currentArray ,int depth,Boolean isMaximazing,int Counter) {
		ArrayList<ArrayList<Integer>> allowedmovesA = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> allowedmovesB = new ArrayList<ArrayList<Integer>>();
		String[][] mimaxArray = new String[currentArray.length][currentArray[0].length];
		String playerScore = "score";
		String A = "A";
		String B = "B";
		int score;
		int TerminalState = 0;
		boolean[] ida = new boolean[2];
		
		allowedmovesA=allowedmoves(currentArray,A);
		allowedmovesB=allowedmoves(currentArray,B);
		String result = checkWinner(currentArray);
		
		
		// terminal state
		if (result != "") {
			TerminalState = scores(currentArray,result,Counter);
			return TerminalState;
		}
		
		//helping array for startinng array
		mimaxArray=arraycopy(currentArray);
		
		if(isMaximazing == true) {
			int bestScoreA = -2147483637;
			for(int i=0;i<mimaxArray.length;i++) {
				for(int y=0;y<mimaxArray[0].length;y++) {
					//is the spot available?
					ArrayList<Integer> tempaA = new ArrayList<Integer>();
					tempaA.add(i);
					tempaA.add(y);
					if (allowedmovesA.contains(tempaA)){
						ida=move(mimaxArray,A,tempaA,playerScore);
						Counter++;
						score = minimax(mimaxArray, depth+1, false,Counter);
						mimaxArray = arraycopy(currentArray);
						if(score > bestScoreA) {
							bestScoreA = score;
						}
					}
				}
			}
			return bestScoreA;
		}
		else{
			int bestScoreB = 2147483637;
			for(int i=0;i<mimaxArray.length;i++) {
				for(int y=0;y<mimaxArray[0].length;y++) {
					ArrayList<Integer> tempaB = new ArrayList<Integer>();
					tempaB.add(i);
					tempaB.add(y);
					if (allowedmovesB.contains(tempaB)){
						ida=move(mimaxArray,B,tempaB,playerScore);
						Counter++;
						score = minimax(mimaxArray, depth+1, true,Counter);
						mimaxArray = arraycopy(currentArray);
						if(score < bestScoreB) {
							bestScoreB = score;
						}
					}
				}
			}
			return bestScoreB;
		}
	}
	public static boolean[] bestMove(String[][] currentArray) {
		int score;
		int bestScore = -2147483637;
		String A ="A";
		ArrayList<Integer> Bmove = new ArrayList<Integer>();
		Bmove.add(0);
		Bmove.add(0);
		ArrayList<ArrayList<Integer>> allowedmovesA = new ArrayList<ArrayList<Integer>>();
		String[][] testarray = new String[currentArray.length][currentArray[0].length];
		boolean[] idareal = new boolean[2];
		boolean[] ida = new boolean[2];
		String playerScore = "score";
		allowedmovesA=allowedmoves(currentArray,A);
		
		//copy array for the mini-max algorithm
		testarray = arraycopy(currentArray);
		
		/////loop for allowed moves of allowwed moves
		for(int i=0;i<testarray.length;i++) {
			for(int y=0;y<testarray[0].length;y++) {
				ArrayList<Integer> tempa = new ArrayList<Integer>();
				tempa.add(i);
				tempa.add(y);
				if (allowedmovesA.contains(tempa)){
					ida=move(testarray,A,tempa,playerScore);
					score = minimax(testarray,0,false,0);

					testarray = arraycopy(currentArray);
					//System.out.println("testarray is after: ");
					//print(testarray);
					if(score > bestScore) {
						bestScore = score;
						Bmove.set(0,i);
						Bmove.set(1,y);
					}
				}
			}
			
		}
		//System.out.println("best move is: "+Bmove+ " with score: "+bestScore);
		idareal=move(currentArray,A,Bmove,playerScore);
		print(currentArray);
		return idareal;
	}
	public static String[][] arraycopy(String[][] currentArray){
		String[][] testarray = new String[currentArray.length][currentArray[0].length];
		for(int i=0;i<testarray.length;i++) {
			for(int y=0;y<testarray[0].length;y++) {
				String temp1 = currentArray[i][y];
				testarray[i][y] = temp1;
				
			}
		}
		return testarray;
	}
	public static String checkWinner(String[][] arrazy) {
		ArrayList<ArrayList<Integer>> checkmoveA = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> checkmoveB = new ArrayList<ArrayList<Integer>>();
		String alba = "A";
		String bilba = "B";
		String kenon = "";
		checkmoveA = allowedmoves(arrazy,alba);
		checkmoveB = allowedmoves(arrazy,bilba);
		if(checkmoveA.isEmpty() && checkmoveB.isEmpty()) {
			return "TIE";
		}
		if(checkmoveA.isEmpty()) {
			return "B";
		}
		if(checkmoveB.isEmpty()) {
			return "A";
		}
		return kenon;
	}
	public static int scores(String[][] arrazy , String tempWinner, int Score) {
		tempWinner = checkWinner(arrazy);
		if(tempWinner == "TIE") {
			return 0-Score;
		}
		if(tempWinner == "A") {
			return 1000000-Score;
		}
		if(tempWinner == "B") {
			return -1000000+Score;
		}
	return 0;
	}	
}
