import java.util.*;
import java.util.Arrays;
import java.util.Comparator;
import java.io.*;

public class MainGenerator {
	private static String studentData = "/Users/Connor/desktop/Liberty/FakeLiberty.txt";
	private static String studentNames = "/Users/Connor/desktop/Liberty/FakeLibertyNames.txt";
	static File file = new File(studentData);
	static File nameFile = new File(studentNames);
	public static Integer[][] readKids(int size){
		// read in file
		Integer kidInfo[][] = new Integer[size-1][6];
		try {
		//	File file = new File(studentData);
			Scanner scan = new Scanner(file);
			for(int i=0; i<size-1;i++) {
				for(int j=0; j<6; j++) {
					int entry = scan.nextInt(); // this is the line
					kidInfo[i][j] = entry;
					//	System.out.println(kidInfo[i][j]);
				}
			}
		}
		// throw exception if file is not found
		catch(FileNotFoundException except) {
			System.out.println("File Not Found.");
		}
		return kidInfo; 
	}
	
	public static ArrayList<String> readNames(int size) throws FileNotFoundException {
//	File nameFile = new File(studentNames);
	Scanner scam = new Scanner(nameFile);
	ArrayList<String> list = new ArrayList<String>();
		while (scam.hasNextLine()){
			list.add(scam.nextLine());
		}
	scam.close();
	return list;
	}
	

	public static int getGirlsArraySize(Integer[][] info) {
		int girlsNum = 0;
		for(int i=0; i<info.length;i++) {
			if(info[i][4] == 1) {
				girlsNum++;
			}
		}
		return girlsNum;
	}
	public static int getBoysArraySize(Integer[][] info) {
		int boysNum = 0;
		for(int i=0; i<info.length;i++) {
			if(info[i][4] == 1) {
				boysNum++;
			}
		}
		return boysNum;
	}


	// method to divide into a group of boys in the beginning
	public static Integer[][] findBoys(Integer[][] info, int boysNum, int size) {
		int b = 0; //boy counter
		Integer[][] boys = new Integer[boysNum-1][6]; 
		for(int j=0; j < size-1; j++) {
			if((info[j][4]) == 2) {
				for(int n=0; n<6; n++) {
					if(info[j][n] == null) {
						continue;
					}
					else {
						boys[b][n] = info[j][n]; // add this boys info to the boys array
					}
				}
				b++;
			}
			else {
				continue;
			}
		}

		return boys;
	}
	// method for finding actual class size rather than just array size
	public static int GetClassSize(Integer[][] classList) {
		int size = 0;
		for(int z=0; z<classList.length; z++) {
			if(classList[z][0] != null) {
				size++;
			}
		}
		return size;
	}
	

	// Method to Combine Male and Females in teachs Classes
	public static Integer[][] Combine(Integer[][] males, Integer[][] females, int tVal) {
		Integer[][] Classlist = new Integer[31][6];
		for(int i=0; i<tVal; i++) {
			for(int j=0; j<6; j++) {
				Classlist[i][j] = females[i][j];
			}
		}
		// need to make i = female t1 so 
		int tIndex= tVal; // t1 female so next place that needs to be added
		for(int i=0; i< 15; i++) {
			for(int j=0; j<6; j++) {
				Classlist[tIndex][j] = males[i][j];
			}
			tIndex++;
		}
		return Classlist;
	}

	// method to divide into a group of girls in the beginning
	public static Integer[][] findGirls(Integer[][] info, int girlsNum, int size) {
		Integer[][] girls = new Integer[girlsNum][6]; 
		int h = 0; //girl counter
		for(int i=0; i < size-1; i++) {
			if ((info[i][4]) == 1) { // if this kid is a girl
				for(int m=0; m<6; m++) {
					girls[h][m] = info[i][m];  // add this girls info to girls 2d array		
				}
				h++;
			}
			else {
				continue; // move on if its a boy
			}
		}
		return girls;
	}
	// teacher totals
	public static int totals(Integer[][] kidsInClass, int column) {
		int total = 0;
		for(int i=0; i<kidsInClass.length; i++) {
			if(kidsInClass[i][column] != null) {
				total += kidsInClass[i][column];
			}
		}
		return total;
	}
/*	public static void Switcherooski(Integer[][] Small, Integer[][] Big, int smallGirls, int bigGirls) {
		// if small has less females than big, give a female from big to small
		
		if(smallGirls < bigGirls) {
			// find a girl where their total is 7 ~ which is a very average student
			
		}
		// give male
		else {
			
		}
		
		
	}*/
	// method to simply count girls
	public static int NumGirls(Integer[][] Group) {
		int numba = 0;
		for(int i=0; i<Group.length; i++) {
			if(Group[i][4] == 1) {
				numba++;
			}
		}
		return numba;
	}
	
	// Balance ClassList Sizes 
	public static void Balance(Integer[][] ClasslistOne, Integer[][] ClasslistTwo, Integer[][] ClasslistThree,
			Integer[][] ClasslistFour, Integer[][] ClasslistFive, Integer[][] ClasslistSix, int sizeOne, int sizeTwo,
			int sizeThree, int sizeFour, int sizeFive, int sizeSix, int average) {
		int Onediff = sizeOne - average;
		int Twodiff = sizeTwo - average;
		int Threediff = sizeThree - average;
		int Fourdiff = sizeFour - average;
		int Fivediff = sizeFive - average;
		int Sixdiff = sizeSix - average;
		// might be same value as t1
		int Onegirls = t1;
		int Twogirls = t2;
		int Threegirls = t3;
		int Fourgirls = t4;
		int Fivegirls = t5;
		int Sixgirls = t6;		
		// consider One
		while(Onediff < 0) {
			if(Twodiff >= 1) {
				// if giving a female student
				if(Onegirls < Twogirls) {
					// prepare array for element addition
					for(int b=ClasslistOne.length-2; b>-1; b--) {
						for(int c=0; c<6; c++) {		
							ClasslistOne[b+1][c] = ClasslistOne[b][c];
						}
					}
					// copy over moved student values
					for(int a=0; a<6; a++) {
						ClasslistOne[0][a] = ClasslistTwo[8][a];
					}			
					// update indexes of array that just got smaller
					for(int b=8; b<ClasslistTwo.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistTwo[b][c] = ClasslistTwo[b+1][c];
						}
					}
					
				}
				// if giving a male student
				else {
					for(int a=0; a<6; a++) {
						ClasslistOne[sizeOne][a] = ClasslistTwo[19][a];
					}		
					// update indexes of array that just got smaller
					for(int b=19; b<ClasslistTwo.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistTwo[b][c] = ClasslistTwo[b+1][c];
						}
					}
				}
				sizeOne++;
				sizeTwo--;
				Onediff = sizeOne - average;
				Twodiff = sizeTwo - average;
			}
			else if (Threediff >= 1) {
				// if giving a female student
				if(Onegirls < Threegirls) {
					// prepare array for element addition
					for(int b=ClasslistOne.length-2; b>-1; b--) {
						for(int c=0; c<6; c++) {		
							ClasslistOne[b+1][c] = ClasslistOne[b][c];
						}
					}
					// copy over moved student values
					for(int a=0; a<6; a++) {
						ClasslistOne[0][a] = ClasslistThree[8][a];
					}			
					// update indexes of array that just got smaller
					for(int b=8; b<ClasslistThree.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistThree[b][c] = ClasslistThree[b+1][c];
						}
					}
					
				}
				// if giving a male student
				else {
					for(int a=0; a<6; a++) {
						ClasslistOne[sizeOne][a] = ClasslistThree[19][a];
					}		
					// update indexes of array that just got smaller
					for(int b=19; b<ClasslistThree.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistThree[b][c] = ClasslistThree[b+1][c];
						}
					}
				}	
				sizeOne++;
				sizeThree--;
				Onediff = sizeOne - average;
				Threediff = sizeThree - average;
			}
			else if(Fourdiff >= 1) {
				// if giving a female student
				if(Onegirls < Fourgirls) {
					// prepare array for element addition
					for(int b=ClasslistOne.length-2; b>-1; b--) {
						for(int c=0; c<6; c++) {		
							ClasslistOne[b+1][c] = ClasslistOne[b][c];
						}
					}
					// copy over moved student values
					for(int a=0; a<6; a++) {
						ClasslistOne[0][a] = ClasslistFour[8][a];
					}			
					// update indexes of array that just got smaller
					for(int b=8; b<ClasslistFour.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistFour[b][c] = ClasslistFour[b+1][c];
						}
					}
					
				}
				// if giving a male student
				else {
					for(int a=0; a<6; a++) {
						ClasslistOne[sizeOne][a] = ClasslistFour[19][a];
					}		
					// update indexes of array that just got smaller
					for(int b=19; b<ClasslistFour.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistFour[b][c] = ClasslistFour[b+1][c];
						}
					}
				}
				sizeOne++;
				sizeFour--;
				Onediff = sizeOne - average;
				Fourdiff = sizeFour - average;
			}			
			else if(Fivediff >= 1) {
				// if giving a female student
				if(Onegirls < Fivegirls) {
					// prepare array for element addition
					for(int b=ClasslistOne.length-2; b>-1; b--) {
						for(int c=0; c<6; c++) {		
							ClasslistOne[b+1][c] = ClasslistOne[b][c];
						}
					}
					// copy over moved student values
					for(int a=0; a<6; a++) {
						ClasslistOne[0][a] = ClasslistFive[8][a];
					}			
					// update indexes of array that just got smaller
					for(int b=8; b<ClasslistFive.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistFive[b][c] = ClasslistFive[b+1][c];
						}
					}
					
				}
				// if giving a male student
				else {
					for(int a=0; a<6; a++) {
						ClasslistOne[sizeOne][a] = ClasslistFive[19][a];
					}		
					// update indexes of array that just got smaller
					for(int b=19; b<ClasslistFive.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistFive[b][c] = ClasslistFive[b+1][c];
						}
					}
				}	
				sizeOne++;
				sizeFive--;
				Onediff = sizeOne - average;
				Fivediff = sizeFive - average;
			}
			else if(Sixdiff >= 1) {
				// if giving a female student
				if(Onegirls < Sixgirls) {
					// prepare array for element addition
					for(int b=ClasslistOne.length-2; b>-1; b--) {
						for(int c=0; c<6; c++) {		
							ClasslistOne[b+1][c] = ClasslistOne[b][c];
						}
					}
					// copy over moved student values
					for(int a=0; a<6; a++) {
						ClasslistOne[0][a] = ClasslistSix[8][a];
					}			
					// update indexes of array that just got smaller
					for(int b=8; b<ClasslistSix.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistSix[b][c] = ClasslistSix[b+1][c];
						}
					}
					
				}
				// if giving a male student
				else {
					for(int a=0; a<6; a++) {
						ClasslistOne[sizeOne][a] = ClasslistSix[19][a];
					}		
					// update indexes of array that just got smaller
					for(int b=19; b<ClasslistSix.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistSix[b][c] = ClasslistSix[b+1][c];
						}
					}
				}	
				sizeOne++;
				sizeSix--;
				Onediff = sizeOne - average;
				Sixdiff = sizeSix - average;
			}
		}
	//	System.out.println(sizeOne);
		// consider Two
		while(Twodiff < 0) {
			if(Onediff >= 1) {
				// if giving a female student
				if(Twogirls < Onegirls) {
					// prepare array for element addition
					for(int b=ClasslistTwo.length-2; b>-1; b--) {
						for(int c=0; c<6; c++) {		
							ClasslistTwo[b+1][c] = ClasslistTwo[b][c];
						}
					}
					// copy over moved student values
					for(int a=0; a<6; a++) {
						ClasslistTwo[0][a] = ClasslistOne[8][a];
					}			
					// update indexes of array that just got smaller
					for(int b=8; b<ClasslistOne.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistOne[b][c] = ClasslistOne[b+1][c];
						}
					}
					
				}
				// if giving a male student
				else {
					for(int a=0; a<6; a++) {
						ClasslistTwo[sizeTwo][a] = ClasslistOne[19][a];
					}		
					// update indexes of array that just got smaller
					for(int b=19; b<ClasslistOne.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistOne[b][c] = ClasslistOne[b+1][c];
						}
					}
				}
				sizeTwo++;
				sizeOne--;
				Twodiff = sizeTwo - average;
				Onediff = sizeOne - average;
			}
			else if (Threediff >= 1) {
				// if giving a female student
				if(Twogirls < Threegirls) {
					// prepare array for element addition
					for(int b=ClasslistTwo.length-2; b>-1; b--) {
						for(int c=0; c<6; c++) {		
							ClasslistTwo[b+1][c] = ClasslistTwo[b][c];
						}
					}
					// copy over moved student values
					for(int a=0; a<6; a++) {
						ClasslistTwo[0][a] = ClasslistThree[8][a];
					}			
					// update indexes of array that just got smaller
					for(int b=8; b<ClasslistThree.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistThree[b][c] = ClasslistThree[b+1][c];
						}
					}
					
				}
				// if giving a male student
				else {
					for(int a=0; a<6; a++) {
						ClasslistTwo[sizeTwo][a] = ClasslistThree[19][a];
					}		
					// update indexes of array that just got smaller
					for(int b=19; b<ClasslistThree.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistThree[b][c] = ClasslistThree[b+1][c];
						}
					}
				}	
				sizeTwo++;
				sizeThree--;
				Twodiff = sizeTwo - average;
				Threediff = sizeThree - average;
			}
			else if(Fourdiff >= 1) {
				// if giving a female student
				if(Twogirls < Fourgirls) {
					// prepare array for element addition
					for(int b=ClasslistTwo.length-2; b>-1; b--) {
						for(int c=0; c<6; c++) {		
							ClasslistTwo[b+1][c] = ClasslistTwo[b][c];
						}
					}
					// copy over moved student values
					for(int a=0; a<6; a++) {
						ClasslistTwo[0][a] = ClasslistFour[8][a];
					}			
					// update indexes of array that just got smaller
					for(int b=8; b<ClasslistFour.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistFour[b][c] = ClasslistFour[b+1][c];
						}
					}
					
				}
				// if giving a male student
				else {
					for(int a=0; a<6; a++) {
						ClasslistTwo[sizeTwo][a] = ClasslistFour[19][a];
					}		
					// update indexes of array that just got smaller
					for(int b=19; b<ClasslistFour.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistFour[b][c] = ClasslistFour[b+1][c];
						}
					}
				}
				sizeTwo++;
				sizeFour--;
				Twodiff = sizeTwo - average;
				Fourdiff = sizeFour - average;
			}			
			else if(Fivediff >= 1) {
				// if giving a female student
				if(Twogirls < Fivegirls) {
					// prepare array for element addition
					for(int b=ClasslistTwo.length-2; b>-1; b--) {
						for(int c=0; c<6; c++) {		
							ClasslistTwo[b+1][c] = ClasslistTwo[b][c];
						}
					}
					// copy over moved student values
					for(int a=0; a<6; a++) {
						ClasslistTwo[0][a] = ClasslistFive[8][a];
					}			
					// update indexes of array that just got smaller
					for(int b=8; b<ClasslistFive.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistFive[b][c] = ClasslistFive[b+1][c];
						}
					}
					
				}
				// if giving a male student
				else {
					for(int a=0; a<6; a++) {
						ClasslistTwo[sizeTwo][a] = ClasslistFive[19][a];
					}		
					// update indexes of array that just got smaller
					for(int b=19; b<ClasslistFive.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistFive[b][c] = ClasslistFive[b+1][c];
						}
					}
				}		
				sizeTwo++;
				sizeFive--;
				Twodiff = sizeTwo - average;
				Fivediff = sizeFive - average;
			}
			else if(Sixdiff >= 1) {
				// if giving a female student
				if(Twogirls < Sixgirls) {
					// prepare array for element addition
					for(int b=ClasslistTwo.length-2; b>-1; b--) {
						for(int c=0; c<6; c++) {		
							ClasslistTwo[b+1][c] = ClasslistTwo[b][c];
						}
					}
					// copy over moved student values
					for(int a=0; a<6; a++) {
						ClasslistTwo[0][a] = ClasslistSix[8][a];
					}			
					// update indexes of array that just got smaller
					for(int b=8; b<ClasslistSix.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistSix[b][c] = ClasslistSix[b+1][c];
						}
					}
					
				}
				// if giving a male student
				else {
					for(int a=0; a<6; a++) {
						ClasslistTwo[sizeTwo][a] = ClasslistSix[19][a];
					}		
					// update indexes of array that just got smaller
					for(int b=19; b<ClasslistSix.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistSix[b][c] = ClasslistSix[b+1][c];
						}
					}
				}	
				sizeTwo++;
				sizeSix--;
				Twodiff = sizeTwo - average;
				Sixdiff = sizeSix - average;
			}
		}
		// consider Three
		while(Threediff < 0) {
			if(Onediff >= 1) {
				// if giving a female student
				if(Threegirls < Onegirls) {
					// prepare array for element addition
					for(int b=ClasslistThree.length-2; b>-1; b--) {
						for(int c=0; c<6; c++) {		
							ClasslistThree[b+1][c] = ClasslistThree[b][c];
						}
					}
					// copy over moved student values
					for(int a=0; a<6; a++) {
						ClasslistThree[0][a] = ClasslistOne[8][a];
					}			
					// update indexes of array that just got smaller
					for(int b=8; b<ClasslistOne.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistOne[b][c] = ClasslistOne[b+1][c];
						}
					}
					
				}
				// if giving a male student
				else {
					for(int a=0; a<6; a++) {
						ClasslistThree[sizeThree][a] = ClasslistOne[19][a];
					}		
					// update indexes of array that just got smaller
					for(int b=19; b<ClasslistOne.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistOne[b][c] = ClasslistOne[b+1][c];
						}
					}
				}
				sizeThree++;
				sizeOne--;
				Threediff = sizeThree - average;
				Onediff = sizeOne - average;
			}
			else if (Twodiff >= 1) {
				// if giving a female student
				if(Threegirls < Twogirls) {
					// prepare array for element addition
					for(int b=ClasslistThree.length-2; b>-1; b--) {
						for(int c=0; c<6; c++) {		
							ClasslistThree[b+1][c] = ClasslistThree[b][c];
						}
					}
					// copy over moved student values
					for(int a=0; a<6; a++) {
						ClasslistThree[0][a] = ClasslistTwo[8][a];
					}			
					// update indexes of array that just got smaller
					for(int b=8; b<ClasslistTwo.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistTwo[b][c] = ClasslistTwo[b+1][c];
						}
					}
					
				}
				// if giving a male student
				else {
					for(int a=0; a<6; a++) {
						ClasslistThree[sizeThree][a] = ClasslistTwo[19][a];
					}		
					// update indexes of array that just got smaller
					for(int b=19; b<ClasslistTwo.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistTwo[b][c] = ClasslistTwo[b+1][c];
						}
					}
				}
				sizeThree++;
				sizeTwo--;
				Threediff = sizeThree - average;
				Twodiff = sizeTwo - average;
			}
			else if(Fourdiff >= 1) {
				// if giving a female student
				if(Threegirls < Fourgirls) {
					// prepare array for element addition
					for(int b=ClasslistThree.length-2; b>-1; b--) {
						for(int c=0; c<6; c++) {		
							ClasslistThree[b+1][c] = ClasslistThree[b][c];
						}
					}
					// copy over moved student values
					for(int a=0; a<6; a++) {
						ClasslistThree[0][a] = ClasslistFour[8][a];
					}			
					// update indexes of array that just got smaller
					for(int b=8; b<ClasslistFour.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistFour[b][c] = ClasslistFour[b+1][c];
						}
					}
					
				}
				// if giving a male student
				else {
					for(int a=0; a<6; a++) {
						ClasslistThree[sizeThree][a] = ClasslistFour[19][a];
					}		
					// update indexes of array that just got smaller
					for(int b=19; b<ClasslistFour.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistFour[b][c] = ClasslistFour[b+1][c];
						}
					}
				}	
				sizeThree++;
				sizeFour--;
				Threediff = sizeThree - average;
				Fourdiff = sizeFour - average;
			}			
			else if(Fivediff >= 1) {
				// if giving a female student
				if(Threegirls < Fivegirls) {
					// prepare array for element addition
					for(int b=ClasslistThree.length-2; b>-1; b--) {
						for(int c=0; c<6; c++) {		
							ClasslistThree[b+1][c] = ClasslistThree[b][c];
						}
					}
					// copy over moved student values
					for(int a=0; a<6; a++) {
						ClasslistThree[0][a] = ClasslistFive[8][a];
					}			
					// update indexes of array that just got smaller
					for(int b=8; b<ClasslistFive.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistFive[b][c] = ClasslistFive[b+1][c];
						}
					}
					
				}
				// if giving a male student
				else {
					for(int a=0; a<6; a++) {
						ClasslistThree[sizeThree][a] = ClasslistFive[19][a];
					}		
					// update indexes of array that just got smaller
					for(int b=19; b<ClasslistFive.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistFive[b][c] = ClasslistFive[b+1][c];
						}
					}
				}		
				sizeThree++;
				sizeFive--;
				Threediff = sizeThree - average;
				Fivediff = sizeFive - average;
			}
			else if(Sixdiff >= 1) {
				// if giving a female student
				if(Threegirls < Sixgirls) {
					// prepare array for element addition
					for(int b=ClasslistThree.length-2; b>-1; b--) {
						for(int c=0; c<6; c++) {		
							ClasslistThree[b+1][c] = ClasslistThree[b][c];
						}
					}
					// copy over moved student values
					for(int a=0; a<6; a++) {
						ClasslistThree[0][a] = ClasslistSix[8][a];
					}			
					// update indexes of array that just got smaller
					for(int b=8; b<ClasslistSix.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistSix[b][c] = ClasslistSix[b+1][c];
						}
					}
					
				}
				// if giving a male student
				else {
					for(int a=0; a<6; a++) {
						ClasslistThree[sizeThree][a] = ClasslistSix[19][a];
					}		
					// update indexes of array that just got smaller
					for(int b=19; b<ClasslistSix.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistSix[b][c] = ClasslistSix[b+1][c];
						}
					}
				}	
				sizeThree++;
				sizeSix--;
				Threediff = sizeThree - average;
				Sixdiff = sizeSix - average;
			}
		}
		// consider four
		while(Fourdiff < 0) {
			if(Onediff >= 1) {
				// if giving a female student
				if(Fourgirls < Onegirls) {
					// prepare array for element addition
					for(int b=ClasslistFour.length-2; b>-1; b--) {
						for(int c=0; c<6; c++) {		
							ClasslistFour[b+1][c] = ClasslistFour[b][c];
						}
					}
					// copy over moved student values
					for(int a=0; a<6; a++) {
						ClasslistFour[0][a] = ClasslistOne[8][a];
					}			
					// update indexes of array that just got smaller
					for(int b=8; b<ClasslistOne.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistOne[b][c] = ClasslistOne[b+1][c];
						}
					}
					
				}
				// if giving a male student
				else {
					for(int a=0; a<6; a++) {
						ClasslistFour[sizeFour][a] = ClasslistOne[19][a];
					}		
					// update indexes of array that just got smaller
					for(int b=19; b<ClasslistOne.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistOne[b][c] = ClasslistOne[b+1][c];
						}
					}
				}
				sizeFour++;
				sizeOne--;
				Fourdiff = sizeFour - average;
				Onediff = sizeOne - average;
			}
			else if (Twodiff >= 1) {
				// if giving a female student
				if(Fourgirls < Twogirls) {
					// prepare array for element addition
					for(int b=ClasslistFour.length-2; b>-1; b--) {
						for(int c=0; c<6; c++) {		
							ClasslistFour[b+1][c] = ClasslistFour[b][c];
						}
					}
					// copy over moved student values
					for(int a=0; a<6; a++) {
						ClasslistFour[0][a] = ClasslistTwo[8][a];
					}			
					// update indexes of array that just got smaller
					for(int b=8; b<ClasslistTwo.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistTwo[b][c] = ClasslistTwo[b+1][c];
						}
					}
					
				}
				// if giving a male student
				else {
					for(int a=0; a<6; a++) {
						ClasslistFour[sizeFour][a] = ClasslistTwo[19][a];
					}		
					// update indexes of array that just got smaller
					for(int b=19; b<ClasslistTwo.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistTwo[b][c] = ClasslistTwo[b+1][c];
						}
					}
				}	
				sizeFour++;
				sizeTwo--;
				Fourdiff = sizeFour - average;
				Twodiff = sizeTwo - average;
			}
			else if(Threediff >= 1) {
				// if giving a female student
				if(Fourgirls < Threegirls) {
					// prepare array for element addition
					for(int b=ClasslistFour.length-2; b>-1; b--) {
						for(int c=0; c<6; c++) {		
							ClasslistFour[b+1][c] = ClasslistFour[b][c];
						}
					}
					// copy over moved student values
					for(int a=0; a<6; a++) {
						ClasslistFour[0][a] = ClasslistThree[8][a];
					}			
					// update indexes of array that just got smaller
					for(int b=8; b<ClasslistThree.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistThree[b][c] = ClasslistThree[b+1][c];
						}
					}
					
				}
				// if giving a male student
				else {
					for(int a=0; a<6; a++) {
						ClasslistFour[sizeFour][a] = ClasslistThree[19][a];
					}		
					// update indexes of array that just got smaller
					for(int b=19; b<ClasslistThree.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistThree[b][c] = ClasslistThree[b+1][c];
						}
					}
				};	
				sizeFour++;
				sizeThree--;
				Fourdiff = sizeFour - average;
				Threediff = sizeThree - average;
			}			
			else if(Fivediff >= 1) {
				// if giving a female student
				if(Fourgirls < Fivegirls) {
					// prepare array for element addition
					for(int b=ClasslistFour.length-2; b>-1; b--) {
						for(int c=0; c<6; c++) {		
							ClasslistFour[b+1][c] = ClasslistFour[b][c];
						}
					}
					// copy over moved student values
					for(int a=0; a<6; a++) {
						ClasslistFour[0][a] = ClasslistFive[8][a];
					}			
					// update indexes of array that just got smaller
					for(int b=8; b<ClasslistFive.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistFive[b][c] = ClasslistFive[b+1][c];
						}
					}
					
				}
				// if giving a male student
				else {
					for(int a=0; a<6; a++) {
						ClasslistFour[sizeFour][a] = ClasslistFive[19][a];
					}		
					// update indexes of array that just got smaller
					for(int b=19; b<ClasslistFive.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistFive[b][c] = ClasslistFive[b+1][c];
						}
					}
				}	
				sizeFour++;
				sizeFive--;
				Fourdiff = sizeFour - average;
				Fivediff = sizeFive - average;
			}
			else if(Sixdiff >= 1) {
				// if giving a female student
				if(Fourgirls < Sixgirls) {
					// prepare array for element addition
					for(int b=ClasslistFour.length-2; b>-1; b--) {
						for(int c=0; c<6; c++) {		
							ClasslistFour[b+1][c] = ClasslistFour[b][c];
						}
					}
					// copy over moved student values
					for(int a=0; a<6; a++) {
						ClasslistFour[0][a] = ClasslistSix[8][a];
					}			
					// update indexes of array that just got smaller
					for(int b=8; b<ClasslistSix.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistSix[b][c] = ClasslistSix[b+1][c];
						}
					}
					
				}
				// if giving a male student
				else {
					for(int a=0; a<6; a++) {
						ClasslistFour[sizeFour][a] = ClasslistSix[19][a];
					}		
					// update indexes of array that just got smaller
					for(int b=19; b<ClasslistSix.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistSix[b][c] = ClasslistSix[b+1][c];
						}
					}
				}	
				sizeFour++;
				sizeSix--;
				Fourdiff = sizeFour - average;
				Sixdiff = sizeSix - average;
			}
		}
		// consider five
		while(Fivediff < 0) {
			if(Onediff >= 1) {
				// if giving a female student
				if(Fivegirls < Onegirls) {
					// prepare array for element addition
					for(int b=ClasslistFive.length-2; b>-1; b--) {
						for(int c=0; c<6; c++) {		
							ClasslistFive[b+1][c] = ClasslistFive[b][c];
						}
					}
					// copy over moved student values
					for(int a=0; a<6; a++) {
						ClasslistFive[0][a] = ClasslistOne[8][a];
					}			
					// update indexes of array that just got smaller
					for(int b=8; b<ClasslistOne.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistOne[b][c] = ClasslistOne[b+1][c];
						}
					}
					
				}
				// if giving a male student
				else {
					for(int a=0; a<6; a++) {
						ClasslistFive[sizeFive][a] = ClasslistOne[19][a];
					}		
					// update indexes of array that just got smaller
					for(int b=19; b<ClasslistOne.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistOne[b][c] = ClasslistOne[b+1][c];
						}
					}
				}
				sizeFive++;
				sizeOne--;
				Fivediff = sizeFive - average;
				Onediff = sizeOne - average;
			}
			else if (Twodiff >= 1) {
				// if giving a female student
				if(Fivegirls < Twogirls) {
					// prepare array for element addition
					for(int b=ClasslistFive.length-2; b>-1; b--) {
						for(int c=0; c<6; c++) {		
							ClasslistFive[b+1][c] = ClasslistFive[b][c];
						}
					}
					// copy over moved student values
					for(int a=0; a<6; a++) {
						ClasslistFive[0][a] = ClasslistTwo[8][a];
					}			
					// update indexes of array that just got smaller
					for(int b=8; b<ClasslistTwo.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistTwo[b][c] = ClasslistTwo[b+1][c];
						}
					}
					
				}
				// if giving a male student
				else {
					for(int a=0; a<6; a++) {
						ClasslistFive[sizeFive][a] = ClasslistTwo[19][a];
					}		
					// update indexes of array that just got smaller
					for(int b=19; b<ClasslistTwo.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistTwo[b][c] = ClasslistTwo[b+1][c];
						}
					}
				}	
				sizeFive++;
				sizeTwo--;
				Fivediff = sizeFive - average;
				Twodiff = sizeTwo - average;
			}
			else if(Threediff >= 1) {
				// if giving a female student
				if(Fivegirls < Threegirls) {
					// prepare array for element addition
					for(int b=ClasslistFive.length-2; b>-1; b--) {
						for(int c=0; c<6; c++) {		
							ClasslistFive[b+1][c] = ClasslistFive[b][c];
						}
					}
					// copy over moved student values
					for(int a=0; a<6; a++) {
						ClasslistFive[0][a] = ClasslistThree[8][a];
					}			
					// update indexes of array that just got smaller
					for(int b=8; b<ClasslistThree.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistThree[b][c] = ClasslistThree[b+1][c];
						}
					}
					
				}
				// if giving a male student
				else {
					for(int a=0; a<6; a++) {
						ClasslistFive[sizeFive][a] = ClasslistThree[19][a];
					}		
					// update indexes of array that just got smaller
					for(int b=19; b<ClasslistThree.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistThree[b][c] = ClasslistThree[b+1][c];
						}
					}
				}	
				sizeFive++;
				sizeThree--;
				Fivediff = sizeFive - average;
				Threediff = sizeThree - average;
			}			
			else if(Fourdiff >= 1) {
				// if giving a female student
				if(Fivegirls < Fourgirls) {
					// prepare array for element addition
					for(int b=ClasslistFive.length-2; b>-1; b--) {
						for(int c=0; c<6; c++) {		
							ClasslistFive[b+1][c] = ClasslistFive[b][c];
						}
					}
					// copy over moved student values
					for(int a=0; a<6; a++) {
						ClasslistFive[0][a] = ClasslistFour[8][a];
					}			
					// update indexes of array that just got smaller
					for(int b=8; b<ClasslistFour.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistFour[b][c] = ClasslistFour[b+1][c];
						}
					}
					
				}
				// if giving a male student
				else {
					for(int a=0; a<6; a++) {
						ClasslistFive[sizeFive][a] = ClasslistFour[19][a];
					}		
					// update indexes of array that just got smaller
					for(int b=19; b<ClasslistFour.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistFour[b][c] = ClasslistFour[b+1][c];
						}
					}
				}			
				sizeFive++;
				sizeFour--;
				Fivediff = sizeFive - average;
				Fourdiff = sizeFour - average;
			}
			else if(Sixdiff >= 1) {
				// if giving a female student
				if(Fivegirls < Sixgirls) {
					// prepare array for element addition
					for(int b=ClasslistFive.length-2; b>-1; b--) {
						for(int c=0; c<6; c++) {		
							ClasslistFive[b+1][c] = ClasslistFive[b][c];
						}
					}
					// copy over moved student values
					for(int a=0; a<6; a++) {
						ClasslistFive[0][a] = ClasslistSix[8][a];
					}			
					// update indexes of array that just got smaller
					for(int b=8; b<ClasslistSix.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistSix[b][c] = ClasslistSix[b+1][c];
						}
					}
					
				}
				// if giving a male student
				else {
					for(int a=0; a<6; a++) {
						ClasslistFive[sizeFive][a] = ClasslistSix[19][a];
					}		
					// update indexes of array that just got smaller
					for(int b=19; b<ClasslistSix.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistSix[b][c] = ClasslistSix[b+1][c];
						}
					}
				}		
				sizeFive++;
				sizeSix--;
				Fivediff = sizeFive - average;
				Sixdiff = sizeSix - average;
			}
		}
		// consider six
		while(Sixdiff < 0) {
			if(Onediff >= 1) {
				// if giving a female student
				if(Sixgirls < Onegirls) {
					// prepare array for element addition
					for(int b=ClasslistSix.length-2; b>-1; b--) {
						for(int c=0; c<6; c++) {		
							ClasslistSix[b+1][c] = ClasslistSix[b][c];
						}
					}
					// copy over moved student values
					for(int a=0; a<6; a++) {
						ClasslistSix[0][a] = ClasslistOne[8][a];
					}			
					// update indexes of array that just got smaller
					for(int b=8; b<ClasslistOne.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistOne[b][c] = ClasslistOne[b+1][c];
						}
					}
					
				}
				// if giving a male student
				else {
					for(int a=0; a<6; a++) {
						ClasslistSix[sizeSix][a] = ClasslistOne[19][a];
					}		
					// update indexes of array that just got smaller
					for(int b=19; b<ClasslistOne.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistOne[b][c] = ClasslistOne[b+1][c];
						}
					}
				}
				sizeSix++;
				sizeOne--;
				Sixdiff = sizeSix - average;
				Onediff = sizeOne - average;
			}
			else if (Twodiff >= 1) {
				// if giving a female student
				if(Sixgirls < Twogirls) {
					// prepare array for element addition
					for(int b=ClasslistSix.length-2; b>-1; b--) {
						for(int c=0; c<6; c++) {		
							ClasslistSix[b+1][c] = ClasslistSix[b][c];
						}
					}
					// copy over moved student values
					for(int a=0; a<6; a++) {
						ClasslistSix[0][a] = ClasslistTwo[8][a];
					}			
					// update indexes of array that just got smaller
					for(int b=8; b<ClasslistTwo.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistTwo[b][c] = ClasslistTwo[b+1][c];
						}
					}
					
				}
				// if giving a male student
				else {
					for(int a=0; a<6; a++) {
						ClasslistSix[sizeSix][a] = ClasslistTwo[19][a];
					}		
					// update indexes of array that just got smaller
					for(int b=19; b<ClasslistTwo.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistTwo[b][c] = ClasslistTwo[b+1][c];
						}
					}
				}
				sizeSix++;
				sizeTwo--;
				Sixdiff = sizeSix - average;
				Twodiff = sizeTwo - average;
			}
			else if(Threediff >= 1) {
				// if giving a female student
				if(Sixgirls < Threegirls) {
					// prepare array for element addition
					for(int b=ClasslistSix.length-2; b>-1; b--) {
						for(int c=0; c<6; c++) {		
							ClasslistSix[b+1][c] = ClasslistSix[b][c];
						}
					}
					// copy over moved student values
					for(int a=0; a<6; a++) {
						ClasslistSix[0][a] = ClasslistThree[8][a];
					}			
					// update indexes of array that just got smaller
					for(int b=8; b<ClasslistThree.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistThree[b][c] = ClasslistThree[b+1][c];
						}
					}
					
				}
				// if giving a male student
				else {
					for(int a=0; a<6; a++) {
						ClasslistSix[sizeSix][a] = ClasslistThree[19][a];
					}		
					// update indexes of array that just got smaller
					for(int b=19; b<ClasslistThree.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistThree[b][c] = ClasslistThree[b+1][c];
						}
					}
				}
				sizeSix++;
				sizeThree--;
				Sixdiff = sizeSix - average;
				Threediff = sizeThree - average;
			}			
			else if(Fourdiff >= 1) {
				// if giving a female student
				if(Sixgirls < Fourgirls) {
					// prepare array for element addition
					for(int b=ClasslistSix.length-2; b>-1; b--) {
						for(int c=0; c<6; c++) {		
							ClasslistSix[b+1][c] = ClasslistSix[b][c];
						}
					}
					// copy over moved student values
					for(int a=0; a<6; a++) {
						ClasslistSix[0][a] = ClasslistFour[8][a];
					}			
					// update indexes of array that just got smaller
					for(int b=8; b<ClasslistFour.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistFour[b][c] = ClasslistFour[b+1][c];
						}
					}
					
				}
				// if giving a male student
				else {
					for(int a=0; a<6; a++) {
						ClasslistSix[sizeSix][a] = ClasslistFour[19][a];
					}		
					// update indexes of array that just got smaller
					for(int b=19; b<ClasslistFour.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistFour[b][c] = ClasslistFour[b+1][c];
						}
					}
				}		
				sizeSix++;
				sizeFour--;
				Sixdiff = sizeSix - average;
				Fourdiff = sizeFour - average;
			}
			else if(Fivediff >= 1) {
				// if giving a female student
				if(Sixgirls < Fivegirls) {
					// prepare array for element addition
					for(int b=ClasslistSix.length-2; b>-1; b--) {
						for(int c=0; c<6; c++) {		
							ClasslistSix[b+1][c] = ClasslistSix[b][c];
						}
					}
					// copy over moved student values
					for(int a=0; a<6; a++) {
						ClasslistSix[0][a] = ClasslistFive[8][a];
					}			
					// update indexes of array that just got smaller
					for(int b=8; b<ClasslistFive.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistFive[b][c] = ClasslistFive[b+1][c];
						}
					}
					
				}
				// if giving a male student
				else {
					for(int a=0; a<6; a++) {
						ClasslistSix[sizeSix][a] = ClasslistFive[19][a];
					}		
					// update indexes of array that just got smaller
					for(int b=19; b<ClasslistFive.length-1; b++) {
						for(int c=0; c<6; c++) {		
							ClasslistFive[b][c] = ClasslistFive[b+1][c];
						}
					}
				}	
				sizeSix++;
				sizeFive--;
				Sixdiff = sizeSix - average;
				Fivediff = sizeFive - average;
			}
		}

		
		
	}
	
	
	// overwritten comparators to focus on each column
	static Comparator<Integer[]> BehaviorComparator = new Comparator<Integer[]>() {
		@Override
		public int compare(Integer[] o1, Integer[] o2) {
			return o1[2].compareTo(o2[2]);
		}
	};
	static Comparator<Integer[]> CommunicationComparator = new Comparator<Integer[]>() {
		@Override
		public int compare(Integer[] o1, Integer[] o2) {
			return o1[1].compareTo(o2[1]);
		}
	};

	static Comparator<Integer[]> AcademicComparator = new Comparator<Integer[]>() {
		@Override
		public int compare(Integer[] o1, Integer[] o2) {
			return o1[0].compareTo(o2[0]);
		}
	};

	// method for assigning all girls to classes
	public static Integer[][] copyGirls(Integer[][] girls) {
		Integer[][] dst = new Integer[girls.length][];
		for (int i = 0; i < girls.length; i++) {
			dst[i] = Arrays.copyOf(girls[i], girls[i].length);
		}
		return dst;
	}
	// method for assigning all girls to classes
	public static Integer[][] copyBoys(Integer[][] girls) {
		Integer[][] dst = new Integer[girls.length][];
		for (int i = 0; i < girls.length; i++) {
			dst[i] = Arrays.copyOf(girls[i], girls[i].length);
		}
		return dst;
	}

	// Females: these are counters for each teacher array so they are indexed from 0 by 1
	public static int t1 = 0; 
	public static int t2 = 0;
	public static int t3 = 0;
	public static int t4 = 0;
	public static int t5 = 0;
	public static int t6 = 0;
	// Males: these are counters for each teacher array so they are indexed from 0 by 1
	public static int t1m = 0; 
	public static int t2m = 0;
	public static int t3m = 0;
	public static int t4m = 0;
	public static int t5m = 0;
	public static int t6m = 0;
	// variables to make sure not too many kids from previous teacher get added to the same new class
	
	// counters for managing new class numbers.
	// FORMAT: sameClass*teacherNum*_*oldTeacherNum*
	public static int sameClass1_1 = 0;
	public static int sameClass1_2 = 0;
	public static int sameClass1_3 = 0;
	public static int sameClass1_4 = 0;
	public static int sameClass1_5 = 0;
	public static int sameClass1_6 = 0;
	
	public static int sameClass2_1 = 0;
	public static int sameClass2_2 = 0;
	public static int sameClass2_3 = 0;
	public static int sameClass2_4 = 0;
	public static int sameClass2_5 = 0;
	public static int sameClass2_6 = 0;
	
	public static int sameClass3_1 = 0;
	public static int sameClass3_2 = 0;
	public static int sameClass3_3 = 0;
	public static int sameClass3_4 = 0;
	public static int sameClass3_5 = 0;
	public static int sameClass3_6 = 0;
	
	public static int sameClass4_1 = 0;
	public static int sameClass4_2 = 0;
	public static int sameClass4_3 = 0;
	public static int sameClass4_4 = 0;
	public static int sameClass4_5 = 0;
	public static int sameClass4_6 = 0;
	
	public static int sameClass5_1 = 0;
	public static int sameClass5_2 = 0;
	public static int sameClass5_3 = 0;
	public static int sameClass5_4 = 0;
	public static int sameClass5_5 = 0;
	public static int sameClass5_6 = 0;
	
	public static int sameClass6_1 = 0;
	public static int sameClass6_2 = 0;
	public static int sameClass6_3 = 0;
	public static int sameClass6_4 = 0;
	public static int sameClass6_5 = 0;
	public static int sameClass6_6 = 0;
	
	// index's for the 3 sorted female arrays so no girls are skipped when nobody gets added in a teachers class
	public static int ai = 0; // academic index
	public static int bi = 0; // behavior index 
	public static int ci = 0; // communication index
	// index's for the 3 sorted male arrays so no girls are skipped when nobody gets added in a teachers class
	public static int aim = 0; // academic index
	public static int bim = 0; // behavior index 
	public static int cim = 0; // communication index
	
	// FEMALE
	public static void Female(Integer[][] girls, int size, Integer[][] teacherOne, Integer[][] teacherTwo, Integer[][] teacherThree, 
			Integer[][] teacherFour, Integer[][] teacherFive, Integer[][] teacherSix, int average) {

		// make copy of girls to use for other arrays
		Integer[][] girlsV1 = copyGirls(girls);			
		Integer[][] girlsV2 = copyGirls(girls);
		Integer[][] girlsV3 = copyGirls(girls);
		Arrays.sort(girlsV1, AcademicComparator);
		Arrays.sort(girlsV2, CommunicationComparator);
		Arrays.sort(girlsV3, BehaviorComparator);
		
		int MostGirls = (average / 2) + 3;
		// sorts and stores the girls array 3 different times
		Integer[][] sortedFemaleAcademic = copyGirls(girlsV1);
		Integer[][] sortedFemaleCommunication = copyGirls(girlsV2);
		Integer[][] sortedFemaleBehavior = copyGirls(girlsV3);
	//	System.out.println(sortedFemaleBehavior.length);
		// for female categories we will add to the first teacher first (look below)
		// for male categories we will add to the last teacher first and go in backwards order (DIDNT DO..yet) 
		int i=0;
		while(i < girls.length)	 {
			// key for teacher 1		
			if (i % 6 == 0 && t1 <= MostGirls) {
				// checks if student id exists in all other teacher classes already before adding to this class
				boolean addAcademic = true;
				boolean addBehavior = true;
				boolean addCommunication = true;
				// think about making into a while (addAcademic == false) loop
				for(int r=0; r<teacherOne.length; r++) {
					if(ai >= sortedFemaleBehavior.length -1) {
						ai = sortedFemaleBehavior.length -1;
					}
					if(teacherOne[r][5] == sortedFemaleAcademic[ai][5] || teacherTwo[r][5] == sortedFemaleAcademic[ai][5] || 
						teacherThree[r][5] == sortedFemaleAcademic[ai][5] || teacherFour[r][5] == sortedFemaleAcademic[ai][5]
						|| teacherFive[r][5] == sortedFemaleAcademic[ai][5] || teacherSix[r][5] == sortedFemaleAcademic[ai][5]) {
						addAcademic = false; // wont want to add this priority academic student cause it would already be in a class
						if(ai < sortedFemaleBehavior.length -1) {
							ai++;
						}
/*								// checks next available ai -- we'll do one extra check. and extras will be added at the end
								if(teacherOne[r][5] == sortedFemaleAcademic[ai][5] || teacherTwo[r][5] == sortedFemaleAcademic[ai][5] || 
									teacherThree[r][5] == sortedFemaleAcademic[ai][5] || teacherFour[r][5] == sortedFemaleAcademic[ai][5]
									|| teacherFive[r][5] == sortedFemaleAcademic[ai][5] || teacherSix[r][5] == sortedFemaleAcademic[ai][5]) {
											ai++;
											if(teacherOne[r][5] == sortedFemaleAcademic[ai][5] || teacherTwo[r][5] == sortedFemaleAcademic[ai][5] || 
													teacherThree[r][5] == sortedFemaleAcademic[ai][5] || teacherFour[r][5] == sortedFemaleAcademic[ai][5]
													|| teacherFive[r][5] == sortedFemaleAcademic[ai][5] || teacherSix[r][5] == sortedFemaleAcademic[ai][5]) {
														ai++;
											}
											else {
												addAcademic = true;
											}
								}
								else {
									addAcademic = true;
								}*/
					}
					if(ci >= sortedFemaleBehavior.length -1) {
						ci = sortedFemaleBehavior.length -1;
					}
					if(teacherOne[r][5] == sortedFemaleCommunication[ci][5] || teacherTwo[r][5] == sortedFemaleCommunication[ci][5] || 
						teacherThree[r][5] == sortedFemaleCommunication[ci][5] || teacherFour[r][5] == sortedFemaleCommunication[ci][5]
						|| teacherFive[r][5] == sortedFemaleCommunication[ci][5] || teacherSix[r][5] == sortedFemaleCommunication[ci][5]
								|| sortedFemaleAcademic[ai][5] == sortedFemaleCommunication[ci][5]) {
							addCommunication = false; // wont want to add this priority communication student cause it would already be in a class
							if(ci < sortedFemaleBehavior.length -1) {
							ci++;
							}
					}
					if(bi >= sortedFemaleBehavior.length -1) {
						bi = sortedFemaleBehavior.length -1;
					}
					if(teacherOne[r][5] == sortedFemaleBehavior[bi][5] || teacherTwo[r][5] == sortedFemaleBehavior[bi][5] || 
						teacherThree[r][5] == sortedFemaleBehavior[bi][5] || teacherFour[r][5] == sortedFemaleBehavior[bi][5]
						|| teacherFive[r][5] == sortedFemaleBehavior[bi][5] || teacherSix[r][5] == sortedFemaleBehavior[bi][5] || 
								sortedFemaleAcademic[ai][5] == sortedFemaleBehavior[bi][5] || sortedFemaleCommunication[ci][5] == sortedFemaleBehavior[bi][5]) {
							addBehavior = false; // wont want to add this priority behavior student cause it would already be in a class
							if(bi < sortedFemaleBehavior.length -1) {
								bi++;
							}
					}
				
				}		
					// above looks good for the whole for loop, lets manage sameClass differently.
					// sameClass variable is being changed too often because its looping through here
					// checks student about to be added has old teacher in common with under 6 other students before adding
				/*	if (teacherOne[r][3] == sortedFemaleAcademic[ai][3] && addAcademic == true) {
						if(sameClass1 < 7) {
							sameClass1++;
						}
						else {
							addAcademic = false;
						//	ai--;
						}
					}
					if (teacherOne[r][3] == sortedFemaleCommunication[ci][3] && addCommunication == true) {
						if(sameClass1 < 7) {
							sameClass1++;
						}
						else {
							addCommunication = false;
						//	ci--;
						}
					}
					if (teacherOne[r][3] == sortedFemaleBehavior[bi][3] && addBehavior == true) {
						if(sameClass1 < 7) {
							sameClass1++;
						}
						else {
							addBehavior = false;
						}
					}*/					
			//	}
				
				// academic student same class check
				if(addAcademic == true) {
					if(sortedFemaleAcademic[ai][3] == 1) {
						sameClass1_1++;
						if(sameClass1_1 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
					if(sortedFemaleAcademic[ai][3] == 2) {
						sameClass1_2++;
						if(sameClass1_2 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
					if(sortedFemaleAcademic[ai][3] == 3) {
						sameClass1_3++;
						if(sameClass1_3 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
					if(sortedFemaleAcademic[ai][3] == 4) {
						sameClass1_4++;
						if(sameClass1_4 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
					if(sortedFemaleAcademic[ai][3] == 5) {
						sameClass1_5++;
						if(sameClass1_5 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
					if(sortedFemaleAcademic[ai][3] == 6) {
						sameClass1_6++;
						if(sameClass1_6 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
				}
				// communication student same class check
				if(addCommunication == true) {
					if(sortedFemaleCommunication[ci][3] == 1) {
						sameClass1_1++;
						if(sameClass1_1 > 6) {
							addCommunication = false; // or try to get next ci
						}
					}
					if(sortedFemaleCommunication[ci][3] == 2) {
						sameClass1_2++;
						if(sameClass1_2 > 6) {
							addCommunication = false; // or try to get next ci
						}
					}
					if(sortedFemaleCommunication[ci][3] == 3) {
						sameClass1_3++;
						if(sameClass1_3 > 6) {
							addCommunication = false; // or try to get next ci
						}
					}
					if(sortedFemaleCommunication[ci][3] == 4) {
						sameClass1_4++;
						if(sameClass1_4 > 6) {
							addCommunication = false; // or try to get next ci
						}
					}
					if(sortedFemaleCommunication[ci][3] == 5) {
						sameClass1_5++;
						if(sameClass1_5 > 6) {
							addCommunication = false; // or try to get next ci
						}
					}
					if(sortedFemaleCommunication[ci][3] == 6) {
						sameClass1_6++;
						if(sameClass1_6 > 6) {
							addCommunication = false; // or try to get next ci
						}
					}
				}
				// behavior student same class check
				if(addBehavior == true) {
					if(sortedFemaleBehavior[bi][3] == 1) {
						sameClass1_1++;
						if(sameClass1_1 > 6) {
							addBehavior = false; // or try to get next bi
						}
					}
					if(sortedFemaleBehavior[bi][3] == 2) {
						sameClass1_2++;
						if(sameClass1_2 > 6) {
							addBehavior = false; // or try to get next bi
						}
					}
					if(sortedFemaleBehavior[bi][3] == 3) {
						sameClass1_3++;
						if(sameClass1_3 > 6) {
							addBehavior = false; // or try to get next bi
						}
					}
					if(sortedFemaleBehavior[bi][3] == 4) {
						sameClass1_4++;
						if(sameClass1_4 > 6) {
							addBehavior = false; // or try to get next bi
						}
					}
					if(sortedFemaleBehavior[bi][3] == 5) {
						sameClass1_5++;
						if(sameClass1_5 > 6) {
							addBehavior = false; // or try to get next bi
						}
					}
					if(sortedFemaleBehavior[bi][3] == 6) {
						sameClass1_6++;
						if(sameClass1_6 > 6) {
							addBehavior = false; // or try to get next bi
						}
					}
				}
				
					if(addAcademic == true && addCommunication == true && addBehavior == true) {
						teacherOne[t1][0] = sortedFemaleAcademic[ai][0];
						teacherOne[t1][1] = sortedFemaleAcademic[ai][1];
						teacherOne[t1][2] = sortedFemaleAcademic[ai][2];
						teacherOne[t1][3] = sortedFemaleAcademic[ai][3];
						teacherOne[t1][4] = sortedFemaleAcademic[ai][4];
						teacherOne[t1][5] = sortedFemaleAcademic[ai][5];
						teacherOne[t1+1][0] = sortedFemaleCommunication[ci][0];
						teacherOne[t1+1][1] = sortedFemaleCommunication[ci][1];
						teacherOne[t1+1][2] = sortedFemaleCommunication[ci][2];
						teacherOne[t1+1][3] = sortedFemaleCommunication[ci][3];
						teacherOne[t1+1][4] = sortedFemaleCommunication[ci][4];
						teacherOne[t1+1][5] = sortedFemaleCommunication[ci][5];
						teacherOne[t1+2][0] = sortedFemaleBehavior[bi][0];
						teacherOne[t1+2][1] = sortedFemaleBehavior[bi][1];
						teacherOne[t1+2][2] = sortedFemaleBehavior[bi][2];
						teacherOne[t1+2][3] = sortedFemaleBehavior[bi][3];
						teacherOne[t1+2][4] = sortedFemaleBehavior[bi][4];
						teacherOne[t1+2][5] = sortedFemaleBehavior[bi][5];
						ai++;
						bi++;
						ci++;
						i++;
					}
					else if(addAcademic == false && addCommunication == false && addBehavior == true) {
						teacherOne[t1][0] = sortedFemaleBehavior[bi][0];
						teacherOne[t1][1] = sortedFemaleBehavior[bi][1];
						teacherOne[t1][2] = sortedFemaleBehavior[bi][2];
						teacherOne[t1][3] = sortedFemaleBehavior[bi][3];
						teacherOne[t1][4] = sortedFemaleBehavior[bi][4];
						teacherOne[t1][5] = sortedFemaleBehavior[bi][5];
						bi++;
						i++;
					}
					else if(addAcademic == false && addCommunication == true && addBehavior == false) {
						teacherOne[t1][0] = sortedFemaleCommunication[ci][0];
						teacherOne[t1][1] = sortedFemaleCommunication[ci][1];
						teacherOne[t1][2] = sortedFemaleCommunication[ci][2];
						teacherOne[t1][3] = sortedFemaleCommunication[ci][3];
						teacherOne[t1][4] = sortedFemaleCommunication[ci][4];
						teacherOne[t1][5] = sortedFemaleCommunication[ci][5];
						ci++;
						i++;
					}
					else if(addAcademic == true && addCommunication == false && addBehavior == false) {
						teacherOne[t1][0] = sortedFemaleAcademic[ai][0];
						teacherOne[t1][1] = sortedFemaleAcademic[ai][1];
						teacherOne[t1][2] = sortedFemaleAcademic[ai][2];
						teacherOne[t1][3] = sortedFemaleAcademic[ai][3];
						teacherOne[t1][4] = sortedFemaleAcademic[ai][4];
						teacherOne[t1][5] = sortedFemaleAcademic[ai][5];
						ai++;
						i++;
					}
					else if(addAcademic == false && addCommunication == true && addBehavior == true) {
						teacherOne[t1][0] = sortedFemaleCommunication[ci][0];
						teacherOne[t1][1] = sortedFemaleCommunication[ci][1];
						teacherOne[t1][2] = sortedFemaleCommunication[ci][2];
						teacherOne[t1][3] = sortedFemaleCommunication[ci][3];
						teacherOne[t1][4] = sortedFemaleCommunication[ci][4];
						teacherOne[t1][5] = sortedFemaleCommunication[ci][5];
						teacherOne[t1+1][0] = sortedFemaleBehavior[bi][0];
						teacherOne[t1+1][1] = sortedFemaleBehavior[bi][1];
						teacherOne[t1+1][2] = sortedFemaleBehavior[bi][2];
						teacherOne[t1+1][3] = sortedFemaleBehavior[bi][3];
						teacherOne[t1+1][4] = sortedFemaleBehavior[bi][4];
						teacherOne[t1+1][5] = sortedFemaleBehavior[bi][5];
						ci++;
						bi++;
						i++;
					}
					else if(addAcademic == true && addCommunication == false && addBehavior == true) {
						teacherOne[t1][0] = sortedFemaleAcademic[ai][0];
						teacherOne[t1][1] = sortedFemaleAcademic[ai][1];
						teacherOne[t1][2] = sortedFemaleAcademic[ai][2];
						teacherOne[t1][3] = sortedFemaleAcademic[ai][3];
						teacherOne[t1][4] = sortedFemaleAcademic[ai][4];
						teacherOne[t1][5] = sortedFemaleAcademic[ai][5];
						teacherOne[t1+1][0] = sortedFemaleBehavior[bi][0];
						teacherOne[t1+1][1] = sortedFemaleBehavior[bi][1];
						teacherOne[t1+1][2] = sortedFemaleBehavior[bi][2];
						teacherOne[t1+1][3] = sortedFemaleBehavior[bi][3];
						teacherOne[t1+1][4] = sortedFemaleBehavior[bi][4];
						teacherOne[t1+1][5] = sortedFemaleBehavior[bi][5];
						ai++;
						bi++;
						i++;
					}
					else if(addAcademic == true && addCommunication == true && addBehavior == false) {
						teacherOne[t1][0] = sortedFemaleCommunication[ci][0];
						teacherOne[t1][1] = sortedFemaleCommunication[ci][1];
						teacherOne[t1][2] = sortedFemaleCommunication[ci][2];
						teacherOne[t1][3] = sortedFemaleCommunication[ci][3];
						teacherOne[t1][4] = sortedFemaleCommunication[ci][4];
						teacherOne[t1][5] = sortedFemaleCommunication[ci][5];
						teacherOne[t1+1][0] = sortedFemaleAcademic[ai][0];
						teacherOne[t1+1][1] = sortedFemaleAcademic[ai][1];
						teacherOne[t1+1][2] = sortedFemaleAcademic[ai][2];
						teacherOne[t1+1][3] = sortedFemaleAcademic[ai][3];
						teacherOne[t1+1][4] = sortedFemaleAcademic[ai][4];
						teacherOne[t1+1][5] = sortedFemaleAcademic[ai][5];
						ci++;
						ai++;
						i++;
					}
					else if(addAcademic == false && addCommunication == false && addBehavior == false) {
						// go to next teacher without incrementing next best 3 students if nobody fits in this class
						i++;
					}  					
					// finding new t1 value for the index once its this teachers turn again
					if(addAcademic == true) {
						t1++;
					}
					if(addBehavior == true) {
						t1++;
					}
					if(addCommunication == true) {
						t1++;
					}
		 	}
			// key for teacher 2
		 	if (i % 6 == 1 && t2 <= MostGirls) {
		 	// checks if student id exists in all other teacher classes already before adding to this class
				boolean addAcademic = true;
				boolean addBehavior = true;
				boolean addCommunication = true;
				for(int r=0; r<teacherTwo.length; r++) {
					if(ai >= sortedFemaleBehavior.length -1) {
						ai = sortedFemaleBehavior.length -1;
					}
					if(teacherOne[r][5] == sortedFemaleAcademic[ai][5] || teacherTwo[r][5] == sortedFemaleAcademic[ai][5] || 
							teacherThree[r][5] == sortedFemaleAcademic[ai][5] || teacherFour[r][5] == sortedFemaleAcademic[ai][5]
							|| teacherFive[r][5] == sortedFemaleAcademic[ai][5] || teacherSix[r][5] == sortedFemaleAcademic[ai][5]) {
									addAcademic = false; // wont want to add this priority academic student cause it would already be in a class
									if(ai<sortedFemaleBehavior.length -1) {
										ai++;
									}
									// checks next available ai -- we'll do one extra check. and extras will be added at the end
/*									if(teacherOne[r][5] == sortedFemaleAcademic[ai][5] || teacherTwo[r][5] == sortedFemaleAcademic[ai][5] || 
										teacherThree[r][5] == sortedFemaleAcademic[ai][5] || teacherFour[r][5] == sortedFemaleAcademic[ai][5]
										|| teacherFive[r][5] == sortedFemaleAcademic[ai][5] || teacherSix[r][5] == sortedFemaleAcademic[ai][5]) {
												ai++;
												if(teacherOne[r][5] == sortedFemaleAcademic[ai][5] || teacherTwo[r][5] == sortedFemaleAcademic[ai][5] || 
														teacherThree[r][5] == sortedFemaleAcademic[ai][5] || teacherFour[r][5] == sortedFemaleAcademic[ai][5]
														|| teacherFive[r][5] == sortedFemaleAcademic[ai][5] || teacherSix[r][5] == sortedFemaleAcademic[ai][5]) {
															ai++;
												}
												else {
													addAcademic = true;
												}
									}
									else {
										addAcademic = true;
									}*/
						}
					if(ci >= sortedFemaleBehavior.length -1) {
						ci = sortedFemaleBehavior.length -1;
					}
						if(teacherOne[r][5] == sortedFemaleCommunication[ci][5] || teacherTwo[r][5] == sortedFemaleCommunication[ci][5] || 
							teacherThree[r][5] == sortedFemaleCommunication[ci][5] || teacherFour[r][5] == sortedFemaleCommunication[ci][5]
							|| teacherFive[r][5] == sortedFemaleCommunication[ci][5] || teacherSix[r][5] == sortedFemaleCommunication[ci][5]
									|| sortedFemaleAcademic[ai][5] == sortedFemaleCommunication[ci][5]) {
								addCommunication = false; // wont want to add this priority communication student cause it would already be in a class
								if(ci < sortedFemaleBehavior.length -1) {
									ci++;
								}
						}
						if(bi >= sortedFemaleBehavior.length -1) {
							bi = sortedFemaleBehavior.length -1;
						}
						if(teacherOne[r][5] == sortedFemaleBehavior[bi][5] || teacherTwo[r][5] == sortedFemaleBehavior[bi][5] || 
							teacherThree[r][5] == sortedFemaleBehavior[bi][5] || teacherFour[r][5] == sortedFemaleBehavior[bi][5]
							|| teacherFive[r][5] == sortedFemaleBehavior[bi][5] || teacherSix[r][5] == sortedFemaleBehavior[bi][5]  || 
									sortedFemaleAcademic[ai][5] == sortedFemaleBehavior[bi][5] || sortedFemaleCommunication[ci][5] == sortedFemaleBehavior[bi][5]) {
								addBehavior = false; // wont want to add this priority behavior student cause it would already be in a class
								if(bi < sortedFemaleBehavior.length -1) {
									bi++;
								}
						}
				}
					// checks student about to be added has old teacher in common with under 6 other students before adding
/*					if (teacherTwo[r][3] == sortedFemaleAcademic[ai][3] && addAcademic == true) {
						if(sameClass2 < 7) {
							sameClass2++;
						}
						else {
							addAcademic = false;
						}
					}
					if (teacherTwo[r][3] == sortedFemaleCommunication[ci][3] && addCommunication == true) {
						if(sameClass2 < 7) {
							sameClass2++;
						}
						else {
							addCommunication = false;
						}
					}
					if (teacherTwo[r][3] == sortedFemaleBehavior[bi][3] && addBehavior == true) {
						if(sameClass2 < 7) {
							sameClass2++;
						}
						else {
							addBehavior = false;
						}
					}	*/				
			//	}	public static int sameClass3_1 = 0;

				// academic student same class check
				if(addAcademic == true) {
					if(sortedFemaleAcademic[ai][3] == 1) {
						sameClass2_1++;
						if(sameClass2_1 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
					if(sortedFemaleAcademic[ai][3] == 2) {
						sameClass2_2++;
						if(sameClass2_2 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
					if(sortedFemaleAcademic[ai][3] == 3) {
						sameClass2_3++;
						if(sameClass2_3 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
					if(sortedFemaleAcademic[ai][3] == 4) {
						sameClass2_4++;
						if(sameClass2_4 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
					if(sortedFemaleAcademic[ai][3] == 5) {
						sameClass2_5++;
						if(sameClass2_5 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
					if(sortedFemaleAcademic[ai][3] == 6) {
						sameClass2_6++;
						if(sameClass2_6 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
				}
				// communication student same class check
				if(addCommunication == true) {
					if(sortedFemaleCommunication[ci][3] == 1) {
						sameClass2_1++;
						if(sameClass2_1 > 6) {
							addCommunication = false; // or try to get next ci
						}
					}
					if(sortedFemaleCommunication[ci][3] == 2) {
						sameClass2_2++;
						if(sameClass2_2 > 6) {
							addCommunication = false; // or try to get next ci
						}
					}
					if(sortedFemaleCommunication[ci][3] == 3) {
						sameClass2_3++;
						if(sameClass2_3 > 6) {
							addCommunication = false; // or try to get next ci
						}
					}
					if(sortedFemaleCommunication[ci][3] == 4) {
						sameClass2_4++;
						if(sameClass2_4 > 6) {
							addCommunication = false; // or try to get next ci
						}
					}
					if(sortedFemaleCommunication[ci][3] == 5) {
						sameClass2_5++;
						if(sameClass2_5 > 6) {
							addCommunication = false; // or try to get next ci
						}
					}
					if(sortedFemaleCommunication[ci][3] == 6) {
						sameClass2_6++;
						if(sameClass2_6 > 6) {
							addCommunication = false; // or try to get next ci
						}
					}
				}
				// behavior student same class check
				if(addBehavior == true) {
					if(sortedFemaleBehavior[bi][3] == 1) {
						sameClass2_1++;
						if(sameClass2_1 > 6) {
							addBehavior = false; // or try to get next bi
						}
					}
					if(sortedFemaleBehavior[bi][3] == 2) {
						sameClass2_2++;
						if(sameClass2_2 > 6) {
							addBehavior = false; // or try to get next bi
						}
					}
					if(sortedFemaleBehavior[bi][3] == 3) {
						sameClass2_3++;
						if(sameClass2_3 > 6) {
							addBehavior = false; // or try to get next bi
						}
					}
					if(sortedFemaleBehavior[bi][3] == 4) {
						sameClass2_4++;
						if(sameClass2_4 > 6) {
							addBehavior = false; // or try to get next bi
						}
					}
					if(sortedFemaleBehavior[bi][3] == 5) {
						sameClass2_5++;
						if(sameClass2_5 > 6) {
							addBehavior = false; // or try to get next bi
						}
					}
					if(sortedFemaleBehavior[bi][3] == 6) {
						sameClass2_6++;
						if(sameClass2_6 > 6) {
							addBehavior = false; // or try to get next bi
						}
					}
				}
				
					if(addAcademic == true && addCommunication == true && addBehavior == true) {
						teacherTwo[t2][0] = sortedFemaleAcademic[ai][0];
						teacherTwo[t2][1] = sortedFemaleAcademic[ai][1];
						teacherTwo[t2][2] = sortedFemaleAcademic[ai][2];
						teacherTwo[t2][3] = sortedFemaleAcademic[ai][3];
						teacherTwo[t2][4] = sortedFemaleAcademic[ai][4];
						teacherTwo[t2][5] = sortedFemaleAcademic[ai][5];
						teacherTwo[t2+1][0] = sortedFemaleCommunication[ci][0];
						teacherTwo[t2+1][1] = sortedFemaleCommunication[ci][1];
						teacherTwo[t2+1][2] = sortedFemaleCommunication[ci][2];
						teacherTwo[t2+1][3] = sortedFemaleCommunication[ci][3];
						teacherTwo[t2+1][4] = sortedFemaleCommunication[ci][4];
						teacherTwo[t2+1][5] = sortedFemaleCommunication[ci][5];
						teacherTwo[t2+2][0] = sortedFemaleBehavior[bi][0];
						teacherTwo[t2+2][1] = sortedFemaleBehavior[bi][1];
						teacherTwo[t2+2][2] = sortedFemaleBehavior[bi][2];
						teacherTwo[t2+2][3] = sortedFemaleBehavior[bi][3];
						teacherTwo[t2+2][4] = sortedFemaleBehavior[bi][4];
						teacherTwo[t2+2][5] = sortedFemaleBehavior[bi][5];
						ai++;
						bi++;
						ci++;
						i++;
					}
					else if(addAcademic == false && addCommunication == false && addBehavior == true) {
						teacherTwo[t2][0] = sortedFemaleBehavior[bi][0];
						teacherTwo[t2][1] = sortedFemaleBehavior[bi][1];
						teacherTwo[t2][2] = sortedFemaleBehavior[bi][2];
						teacherTwo[t2][3] = sortedFemaleBehavior[bi][3];
						teacherTwo[t2][4] = sortedFemaleBehavior[bi][4];
						teacherTwo[t2][5] = sortedFemaleBehavior[bi][5];
						bi++;
						i++;
					}
					else if(addAcademic == false && addCommunication == true && addBehavior == false) {
						teacherTwo[t2][0] = sortedFemaleCommunication[ci][0];
						teacherTwo[t2][1] = sortedFemaleCommunication[ci][1];
						teacherTwo[t2][2] = sortedFemaleCommunication[ci][2];
						teacherTwo[t2][3] = sortedFemaleCommunication[ci][3];
						teacherTwo[t2][4] = sortedFemaleCommunication[ci][4];
						teacherTwo[t2][5] = sortedFemaleCommunication[ci][5];
						ci++;
						i++;
					}
					else if(addAcademic == true && addCommunication == false && addBehavior == false) {
						teacherTwo[t2][0] = sortedFemaleAcademic[ai][0];
						teacherTwo[t2][1] = sortedFemaleAcademic[ai][1];
						teacherTwo[t2][2] = sortedFemaleAcademic[ai][2];
						teacherTwo[t2][3] = sortedFemaleAcademic[ai][3];
						teacherTwo[t2][4] = sortedFemaleAcademic[ai][4];
						teacherTwo[t2][5] = sortedFemaleAcademic[ai][5];
						ai++;
						i++;
					}
					else if(addAcademic == false && addCommunication == true && addBehavior == true) {
						teacherTwo[t2][0] = sortedFemaleCommunication[ci][0];
						teacherTwo[t2][1] = sortedFemaleCommunication[ci][1];
						teacherTwo[t2][2] = sortedFemaleCommunication[ci][2];
						teacherTwo[t2][3] = sortedFemaleCommunication[ci][3];
						teacherTwo[t2][4] = sortedFemaleCommunication[ci][4];
						teacherTwo[t2][5] = sortedFemaleCommunication[ci][5];
						teacherTwo[t2+1][0] = sortedFemaleBehavior[bi][0];
						teacherTwo[t2+1][1] = sortedFemaleBehavior[bi][1];
						teacherTwo[t2+1][2] = sortedFemaleBehavior[bi][2];
						teacherTwo[t2+1][3] = sortedFemaleBehavior[bi][3];
						teacherTwo[t2+1][4] = sortedFemaleBehavior[bi][4];
						teacherTwo[t2+1][5] = sortedFemaleBehavior[bi][5];
						ci++;
						bi++; 
						i++;
					}
					else if(addAcademic == true && addCommunication == false && addBehavior == true) {
						teacherTwo[t2][0] = sortedFemaleAcademic[ai][0];
						teacherTwo[t2][1] = sortedFemaleAcademic[ai][1];
						teacherTwo[t2][2] = sortedFemaleAcademic[ai][2];
						teacherTwo[t2][3] = sortedFemaleAcademic[ai][3];
						teacherTwo[t2][4] = sortedFemaleAcademic[ai][4];
						teacherTwo[t2][5] = sortedFemaleAcademic[ai][5];
						teacherTwo[t2+1][0] = sortedFemaleBehavior[bi][0];
						teacherTwo[t2+1][1] = sortedFemaleBehavior[bi][1];
						teacherTwo[t2+1][2] = sortedFemaleBehavior[bi][2];
						teacherTwo[t2+1][3] = sortedFemaleBehavior[bi][3];
						teacherTwo[t2+1][4] = sortedFemaleBehavior[bi][4];
						teacherTwo[t2+1][5] = sortedFemaleBehavior[bi][5];
						ai++;
						bi++;
						i++;
					}
					else if(addAcademic == true && addCommunication == true && addBehavior == false) {
						teacherTwo[t2][0] = sortedFemaleCommunication[ci][0];
						teacherTwo[t2][1] = sortedFemaleCommunication[ci][1];
						teacherTwo[t2][2] = sortedFemaleCommunication[ci][2];
						teacherTwo[t2][3] = sortedFemaleCommunication[ci][3];
						teacherTwo[t2][4] = sortedFemaleCommunication[ci][4];
						teacherTwo[t2][5] = sortedFemaleCommunication[ci][5];
						teacherTwo[t2+1][0] = sortedFemaleAcademic[ai][0];
						teacherTwo[t2+1][1] = sortedFemaleAcademic[ai][1];
						teacherTwo[t2+1][2] = sortedFemaleAcademic[ai][2];
						teacherTwo[t2+1][3] = sortedFemaleAcademic[ai][3];
						teacherTwo[t2+1][4] = sortedFemaleAcademic[ai][4];
						teacherTwo[t2+1][5] = sortedFemaleAcademic[ai][5];
						ci++;
						ai++;
						i++;
					}
					else if(addAcademic == false && addCommunication == false && addBehavior == false) {
						// go to next teacher without incrementing next best 3 students if nobody fits in this class
						i++;
					}  					
					// finding new t2 value for the index once its this teachers turn again
					if(addAcademic == true) {
						t2++;
					}
					if(addBehavior == true) {
						t2++;
					}
					if(addCommunication == true) {
						t2++;
					}
		 	}
		 	// key for teacher 3
		 	if (i % 6 == 2 && t3 <= MostGirls) {
		 	// checks if student id exists in all other teacher classes already before adding to this class
				boolean addAcademic = true;
				boolean addBehavior = true;
				boolean addCommunication = true;
				for(int r=0; r<teacherThree.length; r++) {
					if(ai >= sortedFemaleBehavior.length -1) {
						ai = sortedFemaleBehavior.length -1;
					}
					if(teacherOne[r][5] == sortedFemaleAcademic[ai][5] || teacherTwo[r][5] == sortedFemaleAcademic[ai][5] || 
							teacherThree[r][5] == sortedFemaleAcademic[ai][5] || teacherFour[r][5] == sortedFemaleAcademic[ai][5]
							|| teacherFive[r][5] == sortedFemaleAcademic[ai][5] || teacherSix[r][5] == sortedFemaleAcademic[ai][5]) {
									addAcademic = false; // wont want to add this priority academic student cause it would already be in a class
									if(ai < sortedFemaleBehavior.length -1) {
										ai++;
									}
									// checks next available ai -- we'll do one extra check. and extras will be added at the end
/*									if(teacherOne[r][5] == sortedFemaleAcademic[ai][5] || teacherTwo[r][5] == sortedFemaleAcademic[ai][5] || 
										teacherThree[r][5] == sortedFemaleAcademic[ai][5] || teacherFour[r][5] == sortedFemaleAcademic[ai][5]
										|| teacherFive[r][5] == sortedFemaleAcademic[ai][5] || teacherSix[r][5] == sortedFemaleAcademic[ai][5]) {
												ai++;
												if(teacherOne[r][5] == sortedFemaleAcademic[ai][5] || teacherTwo[r][5] == sortedFemaleAcademic[ai][5] || 
														teacherThree[r][5] == sortedFemaleAcademic[ai][5] || teacherFour[r][5] == sortedFemaleAcademic[ai][5]
														|| teacherFive[r][5] == sortedFemaleAcademic[ai][5] || teacherSix[r][5] == sortedFemaleAcademic[ai][5]) {
															ai++;
												}
												else {
													addAcademic = true;
												}
									}
									else {
										addAcademic = true;
									}*/
						}
					if(ci >= sortedFemaleBehavior.length -1) {
						ci = sortedFemaleBehavior.length -1;
					}
						if(teacherOne[r][5] == sortedFemaleCommunication[ci][5] || teacherTwo[r][5] == sortedFemaleCommunication[ci][5] || 
							teacherThree[r][5] == sortedFemaleCommunication[ci][5] || teacherFour[r][5] == sortedFemaleCommunication[ci][5]
							|| teacherFive[r][5] == sortedFemaleCommunication[ci][5] || teacherSix[r][5] == sortedFemaleCommunication[ci][5]
									|| sortedFemaleAcademic[ai][5] == sortedFemaleCommunication[ci][5]) {
								addCommunication = false; // wont want to add this priority communication student cause it would already be in a class
								if(ci < sortedFemaleBehavior.length -1) {
									ci++;
								}
						}
						if(bi >= sortedFemaleBehavior.length -1) {
							bi = sortedFemaleBehavior.length -1;
						}
						if(teacherOne[r][5] == sortedFemaleBehavior[bi][5] || teacherTwo[r][5] == sortedFemaleBehavior[bi][5] || 
							teacherThree[r][5] == sortedFemaleBehavior[bi][5] || teacherFour[r][5] == sortedFemaleBehavior[bi][5]
							|| teacherFive[r][5] == sortedFemaleBehavior[bi][5] || teacherSix[r][5] == sortedFemaleBehavior[bi][5]  || 
									sortedFemaleAcademic[ai][5] == sortedFemaleBehavior[bi][5] || sortedFemaleCommunication[ci][5] == sortedFemaleBehavior[bi][5]) {
								addBehavior = false; // wont want to add this priority behavior student cause it would already be in a class
								if(bi < sortedFemaleBehavior.length -1) {
									bi++;
								}
						}
				}
/*					// checks student about to be added has old teacher in common with under 6 other students before adding
					if (teacherThree[r][3] == sortedFemaleAcademic[ai][3] && addAcademic == true) {
						if(sameClass3 < 7) {
							sameClass3++;
						}
						else {
							addAcademic = false;
						}
					}
					if (teacherThree[r][3] == sortedFemaleCommunication[ci][3] && addCommunication == true) {
						if(sameClass3 < 7) {
							sameClass3++;
						}
						else {
							addCommunication = false;
						}
					}
					if (teacherThree[r][3] == sortedFemaleBehavior[bi][3] && addBehavior == true) {
						if(sameClass3 < 7) {
							sameClass3++;
						}
						else {
							addBehavior = false;
						}
					}					
				}*/
				// academic student same class check
				if(addAcademic == true) {
					if(sortedFemaleAcademic[ai][3] == 1) {
						sameClass3_1++;
						if(sameClass3_1 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
					if(sortedFemaleAcademic[ai][3] == 2) {
						sameClass3_2++;
						if(sameClass3_2 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
					if(sortedFemaleAcademic[ai][3] == 3) {
						sameClass3_3++;
						if(sameClass3_3 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
					if(sortedFemaleAcademic[ai][3] == 4) {
						sameClass3_4++;
						if(sameClass3_4 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
					if(sortedFemaleAcademic[ai][3] == 5) {
						sameClass3_5++;
						if(sameClass3_5 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
					if(sortedFemaleAcademic[ai][3] == 6) {
						sameClass3_6++;
						if(sameClass3_6 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
				}
				// communication student same class check
				if(addCommunication == true) {
					if(sortedFemaleCommunication[ci][3] == 1) {
						sameClass3_1++;
						if(sameClass3_1 > 6) {
							addCommunication = false; // or try to get next ci
						}
					}
					if(sortedFemaleCommunication[ci][3] == 2) {
						sameClass3_2++;
						if(sameClass3_2 > 6) {
							addCommunication = false; // or try to get next ci
						}
					}
					if(sortedFemaleCommunication[ci][3] == 3) {
						sameClass3_3++;
						if(sameClass3_3 > 6) {
							addCommunication = false; // or try to get next ci
						}
					}
					if(sortedFemaleCommunication[ci][3] == 4) {
						sameClass3_4++;
						if(sameClass3_4 > 6) {
							addCommunication = false; // or try to get next ci
						}
					}
					if(sortedFemaleCommunication[ci][3] == 5) {
						sameClass3_5++;
						if(sameClass3_5 > 6) {
							addCommunication = false; // or try to get next ci
						}
					}
					if(sortedFemaleCommunication[ci][3] == 6) {
						sameClass3_6++;
						if(sameClass3_6 > 6) {
							addCommunication = false; // or try to get next ci
						}
					}
				}
				// behavior student same class check
				if(addBehavior == true) {
					if(sortedFemaleBehavior[bi][3] == 1) {
						sameClass3_1++;
						if(sameClass3_1 > 6) {
							addBehavior = false; // or try to get next ai
						}
					}
					if(sortedFemaleBehavior[bi][3] == 2) {
						sameClass3_2++;
						if(sameClass3_2 > 6) {
							addBehavior = false; // or try to get next bi
						}
					}
					if(sortedFemaleBehavior[bi][3] == 3) {
						sameClass3_3++;
						if(sameClass3_3 > 6) {
							addBehavior = false; // or try to get next bi
						}
					}
					if(sortedFemaleBehavior[bi][3] == 4) {
						sameClass3_4++;
						if(sameClass3_4 > 6) {
							addBehavior = false; // or try to get next bi
						}
					}
					if(sortedFemaleBehavior[bi][3] == 5) {
						sameClass3_5++;
						if(sameClass3_5 > 6) {
							addBehavior = false; // or try to get next bi
						}
					}
					if(sortedFemaleBehavior[bi][3] == 6) {
						sameClass3_6++;
						if(sameClass3_6 > 6) {
							addBehavior = false; // or try to get next bi
						}
					}
				}
					if(addAcademic == true && addCommunication == true && addBehavior == true) {
						teacherThree[t3][0] = sortedFemaleAcademic[ai][0];
						teacherThree[t3][1] = sortedFemaleAcademic[ai][1];
						teacherThree[t3][2] = sortedFemaleAcademic[ai][2];
						teacherThree[t3][3] = sortedFemaleAcademic[ai][3];
						teacherThree[t3][4] = sortedFemaleAcademic[ai][4];
						teacherThree[t3][5] = sortedFemaleAcademic[ai][5];
						teacherThree[t3+1][0] = sortedFemaleCommunication[ci][0];
						teacherThree[t3+1][1] = sortedFemaleCommunication[ci][1];
						teacherThree[t3+1][2] = sortedFemaleCommunication[ci][2];
						teacherThree[t3+1][3] = sortedFemaleCommunication[ci][3];
						teacherThree[t3+1][4] = sortedFemaleCommunication[ci][4];
						teacherThree[t3+1][5] = sortedFemaleCommunication[ci][5];
						teacherThree[t3+2][0] = sortedFemaleBehavior[bi][0];
						teacherThree[t3+2][1] = sortedFemaleBehavior[bi][1];
						teacherThree[t3+2][2] = sortedFemaleBehavior[bi][2];
						teacherThree[t3+2][3] = sortedFemaleBehavior[bi][3];
						teacherThree[t3+2][4] = sortedFemaleBehavior[bi][4];
						teacherThree[t3+2][5] = sortedFemaleBehavior[bi][5];
						ai++;
						bi++;
						ci++;
						i++;
					}
					else if(addAcademic == false && addCommunication == false && addBehavior == true) {
						teacherThree[t3][0] = sortedFemaleBehavior[bi][0];
						teacherThree[t3][1] = sortedFemaleBehavior[bi][1];
						teacherThree[t3][2] = sortedFemaleBehavior[bi][2];
						teacherThree[t3][3] = sortedFemaleBehavior[bi][3];
						teacherThree[t3][4] = sortedFemaleBehavior[bi][4];
						teacherThree[t3][5] = sortedFemaleBehavior[bi][5];
						bi++;
						i++;
					}
					else if(addAcademic == false && addCommunication == true && addBehavior == false) {
						teacherThree[t3][0] = sortedFemaleCommunication[ci][0];
						teacherThree[t3][1] = sortedFemaleCommunication[ci][1];
						teacherThree[t3][2] = sortedFemaleCommunication[ci][2];
						teacherThree[t3][3] = sortedFemaleCommunication[ci][3];
						teacherThree[t3][4] = sortedFemaleCommunication[ci][4];
						teacherThree[t3][5] = sortedFemaleCommunication[ci][5];
						ci++;
						i++;
					}
					else if(addAcademic == true && addCommunication == false && addBehavior == false) {
						teacherThree[t3][0] = sortedFemaleAcademic[ai][0];
						teacherThree[t3][1] = sortedFemaleAcademic[ai][1];
						teacherThree[t3][2] = sortedFemaleAcademic[ai][2];
						teacherThree[t3][3] = sortedFemaleAcademic[ai][3];
						teacherThree[t3][4] = sortedFemaleAcademic[ai][4];
						teacherThree[t3][5] = sortedFemaleAcademic[ai][5];
						ai++;
						i++;
					}
					else if(addAcademic == false && addCommunication == true && addBehavior == true) {
						teacherThree[t3][0] = sortedFemaleCommunication[ci][0];
						teacherThree[t3][1] = sortedFemaleCommunication[ci][1];
						teacherThree[t3][2] = sortedFemaleCommunication[ci][2];
						teacherThree[t3][3] = sortedFemaleCommunication[ci][3];
						teacherThree[t3][4] = sortedFemaleCommunication[ci][4];
						teacherThree[t3][5] = sortedFemaleCommunication[ci][5];
						teacherThree[t3+1][0] = sortedFemaleBehavior[bi][0];
						teacherThree[t3+1][1] = sortedFemaleBehavior[bi][1];
						teacherThree[t3+1][2] = sortedFemaleBehavior[bi][2];
						teacherThree[t3+1][3] = sortedFemaleBehavior[bi][3];
						teacherThree[t3+1][4] = sortedFemaleBehavior[bi][4];
						teacherThree[t3+1][5] = sortedFemaleBehavior[bi][5];
						ci++;
						bi++;
						i++;
					}
					else if(addAcademic == true && addCommunication == false && addBehavior == true) {
						teacherThree[t3][0] = sortedFemaleAcademic[ai][0];
						teacherThree[t3][1] = sortedFemaleAcademic[ai][1];
						teacherThree[t3][2] = sortedFemaleAcademic[ai][2];
						teacherThree[t3][3] = sortedFemaleAcademic[ai][3];
						teacherThree[t3][4] = sortedFemaleAcademic[ai][4];
						teacherThree[t3][5] = sortedFemaleAcademic[ai][5];
						teacherThree[t3+1][0] = sortedFemaleBehavior[bi][0];
						teacherThree[t3+1][1] = sortedFemaleBehavior[bi][1];
						teacherThree[t3+1][2] = sortedFemaleBehavior[bi][2];
						teacherThree[t3+1][3] = sortedFemaleBehavior[bi][3];
						teacherThree[t3+1][4] = sortedFemaleBehavior[bi][4];
						teacherThree[t3+1][5] = sortedFemaleBehavior[bi][5];
						ai++;
						bi++;
						i++;
					}
					else if(addAcademic == true && addCommunication == true && addBehavior == false) {
						teacherThree[t3][0] = sortedFemaleCommunication[ci][0];
						teacherThree[t3][1] = sortedFemaleCommunication[ci][1];
						teacherThree[t3][2] = sortedFemaleCommunication[ci][2];
						teacherThree[t3][3] = sortedFemaleCommunication[ci][3];
						teacherThree[t3][4] = sortedFemaleCommunication[ci][4];
						teacherThree[t3][5] = sortedFemaleCommunication[ci][5];
						teacherThree[t3+1][0] = sortedFemaleAcademic[ai][0];
						teacherThree[t3+1][1] = sortedFemaleAcademic[ai][1];
						teacherThree[t3+1][2] = sortedFemaleAcademic[ai][2];
						teacherThree[t3+1][3] = sortedFemaleAcademic[ai][3];
						teacherThree[t3+1][4] = sortedFemaleAcademic[ai][4];
						teacherThree[t3+1][5] = sortedFemaleAcademic[ai][5];
						ci++;
						ai++;
						i++;
					}
					else if(addAcademic == false && addCommunication == false && addBehavior == false) {
						// go to next teacher without incrementing next best 3 students if nobody fits in this class
						i++;
					}  					
					// finding new t3 value for the index once its this teachers turn again
					if(addAcademic == true) {
						t3++;
					}
					if(addBehavior == true) {
						t3++;
					}
					if(addCommunication == true) {
						t3++;
					}
		 	}
		 	// key for teacher 4
		 	if (i % 6 == 3 && t4 <= MostGirls) {
		 	// checks if student id exists in all other teacher classes already before adding to this class
				boolean addAcademic = true;
				boolean addBehavior = true;
				boolean addCommunication = true;
				for(int r=0; r<teacherFour.length; r++) {
					if(ai >= sortedFemaleBehavior.length -1) {
						ai = sortedFemaleBehavior.length -1;
					}
					if(teacherOne[r][5] == sortedFemaleAcademic[ai][5] || teacherTwo[r][5] == sortedFemaleAcademic[ai][5] || 
							teacherThree[r][5] == sortedFemaleAcademic[ai][5] || teacherFour[r][5] == sortedFemaleAcademic[ai][5]
							|| teacherFive[r][5] == sortedFemaleAcademic[ai][5] || teacherSix[r][5] == sortedFemaleAcademic[ai][5]) {
									addAcademic = false; // wont want to add this priority academic student cause it would already be in a class
									if(ai < sortedFemaleBehavior.length -1) {
										ai++;
									}
									// checks next available ai -- we'll do one extra check. and extras will be added at the end
/*									if(teacherOne[r][5] == sortedFemaleAcademic[ai][5] || teacherTwo[r][5] == sortedFemaleAcademic[ai][5] || 
										teacherThree[r][5] == sortedFemaleAcademic[ai][5] || teacherFour[r][5] == sortedFemaleAcademic[ai][5]
										|| teacherFive[r][5] == sortedFemaleAcademic[ai][5] || teacherSix[r][5] == sortedFemaleAcademic[ai][5]) {
												ai++;
												if(teacherOne[r][5] == sortedFemaleAcademic[ai][5] || teacherTwo[r][5] == sortedFemaleAcademic[ai][5] || 
														teacherThree[r][5] == sortedFemaleAcademic[ai][5] || teacherFour[r][5] == sortedFemaleAcademic[ai][5]
														|| teacherFive[r][5] == sortedFemaleAcademic[ai][5] || teacherSix[r][5] == sortedFemaleAcademic[ai][5]) {
															ai++;
												}
												else {
													addAcademic = true;
												}
									}
									else {
										addAcademic = true;
									}*/
						}
					if(ci >= sortedFemaleBehavior.length -1) {
						ci = sortedFemaleBehavior.length -1;
					}
						if(teacherOne[r][5] == sortedFemaleCommunication[ci][5] || teacherTwo[r][5] == sortedFemaleCommunication[ci][5] || 
							teacherThree[r][5] == sortedFemaleCommunication[ci][5] || teacherFour[r][5] == sortedFemaleCommunication[ci][5]
							|| teacherFive[r][5] == sortedFemaleCommunication[ci][5] || teacherSix[r][5] == sortedFemaleCommunication[ci][5]
									|| sortedFemaleAcademic[ai][5] == sortedFemaleCommunication[ci][5]) {
								addCommunication = false; // wont want to add this priority communication student cause it would already be in a class
								if(ci < sortedFemaleBehavior.length -1) {
									ci++;
								}
						}
						if(bi >= sortedFemaleBehavior.length -1) {
							bi = sortedFemaleBehavior.length -1;
						}
						if(teacherOne[r][5] == sortedFemaleBehavior[bi][5] || teacherTwo[r][5] == sortedFemaleBehavior[bi][5] || 
							teacherThree[r][5] == sortedFemaleBehavior[bi][5] || teacherFour[r][5] == sortedFemaleBehavior[bi][5]
							|| teacherFive[r][5] == sortedFemaleBehavior[bi][5] || teacherSix[r][5] == sortedFemaleBehavior[bi][5]  || 
									sortedFemaleAcademic[ai][5] == sortedFemaleBehavior[bi][5] || sortedFemaleCommunication[ci][5] == sortedFemaleBehavior[bi][5]) {
								addBehavior = false; // wont want to add this priority behavior student cause it would already be in a class
								if(bi < sortedFemaleBehavior.length -1) {
									bi++;
								}
						}
				}
					// checks student about to be added has old teacher in common with under 6 other students before adding
		/*			if (teacherFour[r][3] == sortedFemaleAcademic[ai][3] && addAcademic == true) {
						if(sameClass4 < 7) {
							sameClass4++;
						}
						else {
							addAcademic = false;
						}
					}
					if (teacherFour[r][3] == sortedFemaleCommunication[ci][3] && addCommunication == true) {
						if(sameClass4 < 7) {
							sameClass4++;
						}
						else {
							addCommunication = false;
						}
					}
					if (teacherFour[r][3] == sortedFemaleBehavior[bi][3] && addBehavior == true) {
						if(sameClass4 < 7) {
							sameClass4++;
						}
						else {
							addBehavior = false;
						}
					}		*/			
			//	}
				// academic student same class check
				if(addAcademic == true) {
					if(sortedFemaleAcademic[ai][3] == 1) {
						sameClass4_1++;
						if(sameClass4_1 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
					if(sortedFemaleAcademic[ai][3] == 2) {
						sameClass4_2++;
						if(sameClass4_2 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
					if(sortedFemaleAcademic[ai][3] == 3) {
						sameClass4_3++;
						if(sameClass4_3 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
					if(sortedFemaleAcademic[ai][3] == 4) {
						sameClass4_4++;
						if(sameClass4_4 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
					if(sortedFemaleAcademic[ai][3] == 5) {
						sameClass4_5++;
						if(sameClass4_5 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
					if(sortedFemaleAcademic[ai][3] == 6) {
						sameClass4_6++;
						if(sameClass4_6 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
				}
				// communication student same class check
				if(addCommunication == true) {
					if(sortedFemaleCommunication[ci][3] == 1) {
						sameClass4_1++;
						if(sameClass4_1 > 6) {
							addCommunication = false; // or try to get next ci
						}
					}
					if(sortedFemaleCommunication[ci][3] == 2) {
						sameClass4_2++;
						if(sameClass4_2 > 6) {
							addCommunication = false; // or try to get next ci
						}
					}
					if(sortedFemaleCommunication[ci][3] == 3) {
						sameClass4_3++;
						if(sameClass4_3 > 6) {
							addCommunication = false; // or try to get next ci
						}
					}
					if(sortedFemaleCommunication[ci][3] == 4) {
						sameClass4_4++;
						if(sameClass4_4 > 6) {
							addCommunication = false; // or try to get next ci
						}
					}
					if(sortedFemaleCommunication[ci][3] == 5) {
						sameClass4_5++;
						if(sameClass4_5 > 6) {
							addCommunication = false; // or try to get next ci
						}
					}
					if(sortedFemaleCommunication[ci][3] == 6) {
						sameClass4_6++;
						if(sameClass4_6 > 6) {
							addCommunication = false; // or try to get next ci
						}
					}
				}
				// behavior student same class check
				if(addBehavior == true) {
					if(sortedFemaleBehavior[bi][3] == 1) {
						sameClass4_1++;
						if(sameClass4_1 > 6) {
							addBehavior = false; // or try to get next bi
						}
					}
					if(sortedFemaleBehavior[bi][3] == 2) {
						sameClass4_2++;
						if(sameClass4_2 > 6) {
							addBehavior = false; // or try to get next bi
						}
					}
					if(sortedFemaleBehavior[bi][3] == 3) {
						sameClass4_3++;
						if(sameClass4_3 > 6) {
							addBehavior = false; // or try to get next bi
						}
					}
					if(sortedFemaleBehavior[bi][3] == 4) {
						sameClass4_4++;
						if(sameClass4_4 > 6) {
							addBehavior = false; // or try to get next bi
						}
					}
					if(sortedFemaleBehavior[bi][3] == 5) {
						sameClass4_5++;
						if(sameClass4_5 > 6) {
							addBehavior = false; // or try to get next bi
						}
					}
					if(sortedFemaleBehavior[bi][3] == 6) {
						sameClass4_6++;
						if(sameClass4_6 > 6) {
							addBehavior = false; // or try to get next bi
						}
					}
				}
					if(addAcademic == true && addCommunication == true && addBehavior == true) {
						teacherFour[t4][0] = sortedFemaleAcademic[ai][0];
						teacherFour[t4][1] = sortedFemaleAcademic[ai][1];
						teacherFour[t4][2] = sortedFemaleAcademic[ai][2];
						teacherFour[t4][3] = sortedFemaleAcademic[ai][3];
						teacherFour[t4][4] = sortedFemaleAcademic[ai][4];
						teacherFour[t4][5] = sortedFemaleAcademic[ai][5];
						teacherFour[t4+1][0] = sortedFemaleCommunication[ci][0];
						teacherFour[t4+1][1] = sortedFemaleCommunication[ci][1];
						teacherFour[t4+1][2] = sortedFemaleCommunication[ci][2];
						teacherFour[t4+1][3] = sortedFemaleCommunication[ci][3];
						teacherFour[t4+1][4] = sortedFemaleCommunication[ci][4];
						teacherFour[t4+1][5] = sortedFemaleCommunication[ci][5];
						teacherFour[t4+2][0] = sortedFemaleBehavior[bi][0];
						teacherFour[t4+2][1] = sortedFemaleBehavior[bi][1];
						teacherFour[t4+2][2] = sortedFemaleBehavior[bi][2];
						teacherFour[t4+2][3] = sortedFemaleBehavior[bi][3];
						teacherFour[t4+2][4] = sortedFemaleBehavior[bi][4];
						teacherFour[t4+2][5] = sortedFemaleBehavior[bi][5];
						ai++;
						bi++;
						ci++;
						i++;
					}
					else if(addAcademic == false && addCommunication == false && addBehavior == true) {
						teacherFour[t4][0] = sortedFemaleBehavior[bi][0];
						teacherFour[t4][1] = sortedFemaleBehavior[bi][1];
						teacherFour[t4][2] = sortedFemaleBehavior[bi][2];
						teacherFour[t4][3] = sortedFemaleBehavior[bi][3];
						teacherFour[t4][4] = sortedFemaleBehavior[bi][4];
						teacherFour[t4][5] = sortedFemaleBehavior[bi][5];
						bi++;
						i++;
					}
					else if(addAcademic == false && addCommunication == true && addBehavior == false) {
						teacherFour[t4][0] = sortedFemaleCommunication[ci][0];
						teacherFour[t4][1] = sortedFemaleCommunication[ci][1];
						teacherFour[t4][2] = sortedFemaleCommunication[ci][2];
						teacherFour[t4][3] = sortedFemaleCommunication[ci][3];
						teacherFour[t4][4] = sortedFemaleCommunication[ci][4];
						teacherFour[t4][5] = sortedFemaleCommunication[ci][5];
						ci++;
						i++;
					}
					else if(addAcademic == true && addCommunication == false && addBehavior == false) {
						teacherFour[t4][0] = sortedFemaleAcademic[ai][0];
						teacherFour[t4][1] = sortedFemaleAcademic[ai][1];
						teacherFour[t4][2] = sortedFemaleAcademic[ai][2];
						teacherFour[t4][3] = sortedFemaleAcademic[ai][3];
						teacherFour[t4][4] = sortedFemaleAcademic[ai][4];
						teacherFour[t4][5] = sortedFemaleAcademic[ai][5];
						ai++;
						i++;
					}
					else if(addAcademic == false && addCommunication == true && addBehavior == true) {
						teacherFour[t4][0] = sortedFemaleCommunication[ci][0];
						teacherFour[t4][1] = sortedFemaleCommunication[ci][1];
						teacherFour[t4][2] = sortedFemaleCommunication[ci][2];
						teacherFour[t4][3] = sortedFemaleCommunication[ci][3];
						teacherFour[t4][4] = sortedFemaleCommunication[ci][4];
						teacherFour[t4][5] = sortedFemaleCommunication[ci][5];
						teacherFour[t4+1][0] = sortedFemaleBehavior[bi][0];
						teacherFour[t4+1][1] = sortedFemaleBehavior[bi][1];
						teacherFour[t4+1][2] = sortedFemaleBehavior[bi][2];
						teacherFour[t4+1][3] = sortedFemaleBehavior[bi][3];
						teacherFour[t4+1][4] = sortedFemaleBehavior[bi][4];
						teacherFour[t4+1][5] = sortedFemaleBehavior[bi][5];
						ci++;
						bi++;
						i++;
					}
					else if(addAcademic == true && addCommunication == false && addBehavior == true) {
						teacherFour[t4][0] = sortedFemaleAcademic[ai][0];
						teacherFour[t4][1] = sortedFemaleAcademic[ai][1];
						teacherFour[t4][2] = sortedFemaleAcademic[ai][2];
						teacherFour[t4][3] = sortedFemaleAcademic[ai][3];
						teacherFour[t4][4] = sortedFemaleAcademic[ai][4];
						teacherFour[t4][5] = sortedFemaleAcademic[ai][5];
						teacherFour[t4+1][0] = sortedFemaleBehavior[bi][0];
						teacherFour[t4+1][1] = sortedFemaleBehavior[bi][1];
						teacherFour[t4+1][2] = sortedFemaleBehavior[bi][2];
						teacherFour[t4+1][3] = sortedFemaleBehavior[bi][3];
						teacherFour[t4+1][4] = sortedFemaleBehavior[bi][4];
						teacherFour[t4+1][5] = sortedFemaleBehavior[bi][5];
						ai++;
						bi++;
						i++;
					}
					else if(addAcademic == true && addCommunication == true && addBehavior == false) {
						teacherFour[t4][0] = sortedFemaleCommunication[ci][0];
						teacherFour[t4][1] = sortedFemaleCommunication[ci][1];
						teacherFour[t4][2] = sortedFemaleCommunication[ci][2];
						teacherFour[t4][3] = sortedFemaleCommunication[ci][3];
						teacherFour[t4][4] = sortedFemaleCommunication[ci][4];
						teacherFour[t4][5] = sortedFemaleCommunication[ci][5];
						teacherFour[t4+1][0] = sortedFemaleAcademic[ai][0];
						teacherFour[t4+1][1] = sortedFemaleAcademic[ai][1];
						teacherFour[t4+1][2] = sortedFemaleAcademic[ai][2];
						teacherFour[t4+1][3] = sortedFemaleAcademic[ai][3];
						teacherFour[t4+1][4] = sortedFemaleAcademic[ai][4];
						teacherFour[t4+1][5] = sortedFemaleAcademic[ai][5];
						ci++;
						ai++;
						i++;
					}
					else if(addAcademic == false && addCommunication == false && addBehavior == false) {
						// go to next teacher without incrementing next best 3 students if nobody fits in this class
						i++;
					}  					
					// finding new t4 value for the index once its this teachers turn again
					if(addAcademic == true) {
						t4++;
					}
					if(addBehavior == true) {
						t4++;
					}
					if(addCommunication == true) {
						t4++;
					}
		 	}
		 	// key for teacher 5
		 	if (i % 6 == 4 && t5 <= MostGirls) {
		 	// checks if student id exists in all other teacher classes already before adding to this class
				boolean addAcademic = true;
				boolean addBehavior = true;
				boolean addCommunication = true;
				for(int r=0; r<teacherFive.length; r++) {
					if(ai >= sortedFemaleBehavior.length -1) {
						ai = sortedFemaleBehavior.length -1;
					}
					if(teacherOne[r][5] == sortedFemaleAcademic[ai][5] || teacherTwo[r][5] == sortedFemaleAcademic[ai][5] || 
							teacherThree[r][5] == sortedFemaleAcademic[ai][5] || teacherFour[r][5] == sortedFemaleAcademic[ai][5]
							|| teacherFive[r][5] == sortedFemaleAcademic[ai][5] || teacherSix[r][5] == sortedFemaleAcademic[ai][5]) {
									addAcademic = false; // wont want to add this priority academic student cause it would already be in a class
									if(ai < sortedFemaleBehavior.length -1) {
										ai++;
									}
									// checks next available ai -- we'll do one extra check. and extras will be added at the end
/*									if(teacherOne[r][5] == sortedFemaleAcademic[ai][5] || teacherTwo[r][5] == sortedFemaleAcademic[ai][5] || 
										teacherThree[r][5] == sortedFemaleAcademic[ai][5] || teacherFour[r][5] == sortedFemaleAcademic[ai][5]
										|| teacherFive[r][5] == sortedFemaleAcademic[ai][5] || teacherSix[r][5] == sortedFemaleAcademic[ai][5]) {
												ai++;
												if(teacherOne[r][5] == sortedFemaleAcademic[ai][5] || teacherTwo[r][5] == sortedFemaleAcademic[ai][5] || 
														teacherThree[r][5] == sortedFemaleAcademic[ai][5] || teacherFour[r][5] == sortedFemaleAcademic[ai][5]
														|| teacherFive[r][5] == sortedFemaleAcademic[ai][5] || teacherSix[r][5] == sortedFemaleAcademic[ai][5]) {
															ai++;
												}
												else {
													addAcademic = true;
												}
									}
									else {
										addAcademic = true;
									}*/
						}
					if(ci >= sortedFemaleBehavior.length -1) {
						ci = sortedFemaleBehavior.length -1;
					}
						if(teacherOne[r][5] == sortedFemaleCommunication[ci][5] || teacherTwo[r][5] == sortedFemaleCommunication[ci][5] || 
							teacherThree[r][5] == sortedFemaleCommunication[ci][5] || teacherFour[r][5] == sortedFemaleCommunication[ci][5]
							|| teacherFive[r][5] == sortedFemaleCommunication[ci][5] || teacherSix[r][5] == sortedFemaleCommunication[ci][5]
									|| sortedFemaleAcademic[ai][5] == sortedFemaleCommunication[ci][5]) {
									addCommunication = false; // wont want to add this priority communication student cause it would already be in a class
									if(ci < sortedFemaleBehavior.length -1) {
										ci++;
									}
						}
						if(bi >= sortedFemaleBehavior.length -1) {
							bi = sortedFemaleBehavior.length -1;
						}
						if(teacherOne[r][5] == sortedFemaleBehavior[bi][5] || teacherTwo[r][5] == sortedFemaleBehavior[bi][5] || 
							teacherThree[r][5] == sortedFemaleBehavior[bi][5] || teacherFour[r][5] == sortedFemaleBehavior[bi][5]
							|| teacherFive[r][5] == sortedFemaleBehavior[bi][5] || teacherSix[r][5] == sortedFemaleBehavior[bi][5]  || 
									sortedFemaleAcademic[ai][5] == sortedFemaleBehavior[bi][5] || sortedFemaleCommunication[ci][5] == sortedFemaleBehavior[bi][5]) {
									addBehavior = false; // wont want to add this priority behavior student cause it would already be in a class
									if(bi < sortedFemaleBehavior.length -1) {
										bi++;
									}
						}
				}
					// checks student about to be added has old teacher in common with under 6 other students before adding
				/*	if (teacherFive[r][3] == sortedFemaleAcademic[ai][3] && addAcademic == true) {
						if(sameClass5 < 7) {
							sameClass5++;
						}
						else {
							addAcademic = false;
						}
					}
					if (teacherFive[r][3] == sortedFemaleCommunication[ci][3] && addCommunication == true) {
						if(sameClass5 < 7) {
							sameClass5++;
						}
						else {
							addCommunication = false;
						}
					}
					if (teacherFive[r][3] == sortedFemaleBehavior[bi][3] && addBehavior == true) {
						if(sameClass5 < 7) {
							sameClass5++;
						}
						else {
							addBehavior = false;
						}
					}	*/				
			//	}
				// academic student same class check
				if(addAcademic == true) {
					if(sortedFemaleAcademic[ai][3] == 1) {
						sameClass5_1++;
						if(sameClass5_1 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
					if(sortedFemaleAcademic[ai][3] == 2) {
						sameClass5_2++;
						if(sameClass5_2 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
					if(sortedFemaleAcademic[ai][3] == 3) {
						sameClass5_3++;
						if(sameClass5_3 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
					if(sortedFemaleAcademic[ai][3] == 4) {
						sameClass5_4++;
						if(sameClass5_4 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
					if(sortedFemaleAcademic[ai][3] == 5) {
						sameClass5_5++;
						if(sameClass5_5 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
					if(sortedFemaleAcademic[ai][3] == 6) {
						sameClass5_6++;
						if(sameClass5_6 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
				}
				// communication student same class check
				if(addCommunication == true) {
					if(sortedFemaleCommunication[ci][3] == 1) {
						sameClass5_1++;
						if(sameClass5_1 > 6) {
							addCommunication = false; // or try to get next ci
						}
					}
					if(sortedFemaleCommunication[ci][3] == 2) {
						sameClass5_2++;
						if(sameClass5_2 > 6) {
							addCommunication = false; // or try to get next ci
						}
					}
					if(sortedFemaleCommunication[ci][3] == 3) {
						sameClass5_3++;
						if(sameClass5_3 > 6) {
							addCommunication = false; // or try to get next ci
						}
					}
					if(sortedFemaleCommunication[ci][3] == 4) {
						sameClass5_4++;
						if(sameClass5_4 > 6) {
							addCommunication = false; // or try to get next ci
						}
					}
					if(sortedFemaleCommunication[ci][3] == 5) {
						sameClass5_5++;
						if(sameClass5_5 > 6) {
							addCommunication = false; // or try to get next ci
						}
					}
					if(sortedFemaleCommunication[ci][3] == 6) {
						sameClass5_6++;
						if(sameClass5_6 > 6) {
							addCommunication = false; // or try to get next ci
						}
					}
				}
				// behavior student same class check
				if(addBehavior == true) {
					if(sortedFemaleBehavior[bi][3] == 1) {
						sameClass5_1++;
						if(sameClass5_1 > 6) {
							addBehavior = false; // or try to get next bi
						}
					}
					if(sortedFemaleBehavior[bi][3] == 2) {
						sameClass5_2++;
						if(sameClass5_2 > 6) {
							addBehavior = false; // or try to get next bi
						}
					}
					if(sortedFemaleBehavior[bi][3] == 3) {
						sameClass5_3++;
						if(sameClass5_3 > 6) {
							addBehavior = false; // or try to get next bi
						}
					}
					if(sortedFemaleBehavior[bi][3] == 4) {
						sameClass5_4++;
						if(sameClass5_4 > 6) {
							addBehavior = false; // or try to get next bi
						}
					}
					if(sortedFemaleBehavior[bi][3] == 5) {
						sameClass5_5++;
						if(sameClass5_5 > 6) {
							addBehavior = false; // or try to get next bi
						}
					}
					if(sortedFemaleBehavior[bi][3] == 6) {
						sameClass5_6++;
						if(sameClass5_6 > 6) {
							addBehavior = false; // or try to get next bi
						}
					}
				}
					if(addAcademic == true && addCommunication == true && addBehavior == true) {
						teacherFive[t5][0] = sortedFemaleAcademic[ai][0];
						teacherFive[t5][1] = sortedFemaleAcademic[ai][1];
						teacherFive[t5][2] = sortedFemaleAcademic[ai][2];
						teacherFive[t5][3] = sortedFemaleAcademic[ai][3];
						teacherFive[t5][4] = sortedFemaleAcademic[ai][4];
						teacherFive[t5][5] = sortedFemaleAcademic[ai][5];
						teacherFive[t5+1][0] = sortedFemaleCommunication[ci][0];
						teacherFive[t5+1][1] = sortedFemaleCommunication[ci][1];
						teacherFive[t5+1][2] = sortedFemaleCommunication[ci][2];
						teacherFive[t5+1][3] = sortedFemaleCommunication[ci][3];
						teacherFive[t5+1][4] = sortedFemaleCommunication[ci][4];
						teacherFive[t5+1][5] = sortedFemaleCommunication[ci][5];
						teacherFive[t5+2][0] = sortedFemaleBehavior[bi][0];
						teacherFive[t5+2][1] = sortedFemaleBehavior[bi][1];
						teacherFive[t5+2][2] = sortedFemaleBehavior[bi][2];
						teacherFive[t5+2][3] = sortedFemaleBehavior[bi][3];
						teacherFive[t5+2][4] = sortedFemaleBehavior[bi][4];
						teacherFive[t5+2][5] = sortedFemaleBehavior[bi][5];
						ai++;
						bi++;
						ci++;
						i++;
					}
					else if(addAcademic == false && addCommunication == false && addBehavior == true) {
						teacherFive[t5][0] = sortedFemaleBehavior[bi][0];
						teacherFive[t5][1] = sortedFemaleBehavior[bi][1];
						teacherFive[t5][2] = sortedFemaleBehavior[bi][2];
						teacherFive[t5][3] = sortedFemaleBehavior[bi][3];
						teacherFive[t5][4] = sortedFemaleBehavior[bi][4];
						teacherFive[t5][5] = sortedFemaleBehavior[bi][5];
						bi++;
						i++;
					}
					else if(addAcademic == false && addCommunication == true && addBehavior == false) {
						teacherFive[t5][0] = sortedFemaleCommunication[ci][0];
						teacherFive[t5][1] = sortedFemaleCommunication[ci][1];
						teacherFive[t5][2] = sortedFemaleCommunication[ci][2];
						teacherFive[t5][3] = sortedFemaleCommunication[ci][3];
						teacherFive[t5][4] = sortedFemaleCommunication[ci][4];
						teacherFive[t5][5] = sortedFemaleCommunication[ci][5];
						ci++;
						i++;
					}
					else if(addAcademic == true && addCommunication == false && addBehavior == false) {
						teacherFive[t5][0] = sortedFemaleAcademic[ai][0];
						teacherFive[t5][1] = sortedFemaleAcademic[ai][1];
						teacherFive[t5][2] = sortedFemaleAcademic[ai][2];
						teacherFive[t5][3] = sortedFemaleAcademic[ai][3];
						teacherFive[t5][4] = sortedFemaleAcademic[ai][4];
						teacherFive[t5][5] = sortedFemaleAcademic[ai][5];
						ai++;
						i++;
					}
					else if(addAcademic == false && addCommunication == true && addBehavior == true) {
						teacherFive[t5][0] = sortedFemaleCommunication[ci][0];
						teacherFive[t5][1] = sortedFemaleCommunication[ci][1];
						teacherFive[t5][2] = sortedFemaleCommunication[ci][2];
						teacherFive[t5][3] = sortedFemaleCommunication[ci][3];
						teacherFive[t5][4] = sortedFemaleCommunication[ci][4];
						teacherFive[t5][5] = sortedFemaleCommunication[ci][5];
						teacherFive[t5+1][0] = sortedFemaleBehavior[bi][0];
						teacherFive[t5+1][1] = sortedFemaleBehavior[bi][1];
						teacherFive[t5+1][2] = sortedFemaleBehavior[bi][2];
						teacherFive[t5+1][3] = sortedFemaleBehavior[bi][3];
						teacherFive[t5+1][4] = sortedFemaleBehavior[bi][4];
						teacherFive[t5+1][5] = sortedFemaleBehavior[bi][5];
						ci++;
						bi++;
						i++;
					}
					else if(addAcademic == true && addCommunication == false && addBehavior == true) {
						teacherFive[t5][0] = sortedFemaleAcademic[ai][0];
						teacherFive[t5][1] = sortedFemaleAcademic[ai][1];
						teacherFive[t5][2] = sortedFemaleAcademic[ai][2];
						teacherFive[t5][3] = sortedFemaleAcademic[ai][3];
						teacherFive[t5][4] = sortedFemaleAcademic[ai][4];
						teacherFive[t5][5] = sortedFemaleAcademic[ai][5];
						teacherFive[t5+1][0] = sortedFemaleBehavior[bi][0];
						teacherFive[t5+1][1] = sortedFemaleBehavior[bi][1];
						teacherFive[t5+1][2] = sortedFemaleBehavior[bi][2];
						teacherFive[t5+1][3] = sortedFemaleBehavior[bi][3];
						teacherFive[t5+1][4] = sortedFemaleBehavior[bi][4];
						teacherFive[t5+1][5] = sortedFemaleBehavior[bi][5];
						ai++;
						bi++;
						i++;
					}
					else if(addAcademic == true && addCommunication == true && addBehavior == false) {
						teacherFive[t5][0] = sortedFemaleCommunication[ci][0];
						teacherFive[t5][1] = sortedFemaleCommunication[ci][1];
						teacherFive[t5][2] = sortedFemaleCommunication[ci][2];
						teacherFive[t5][3] = sortedFemaleCommunication[ci][3];
						teacherFive[t5][4] = sortedFemaleCommunication[ci][4];
						teacherFive[t5][5] = sortedFemaleCommunication[ci][5];
						teacherFive[t5+1][0] = sortedFemaleAcademic[ai][0];
						teacherFive[t5+1][1] = sortedFemaleAcademic[ai][1];
						teacherFive[t5+1][2] = sortedFemaleAcademic[ai][2];
						teacherFive[t5+1][3] = sortedFemaleAcademic[ai][3];
						teacherFive[t5+1][4] = sortedFemaleAcademic[ai][4];
						teacherFive[t5+1][5] = sortedFemaleAcademic[ai][5];
						ci++;
						ai++;
						i++;
					}
					else if(addAcademic == false && addCommunication == false && addBehavior == false) {
						// go to next teacher without incrementing next best 3 students if nobody fits in this class
						i++;
					}  					
					// finding new t5 value for the index once its this teachers turn again
					if(addAcademic == true) {
						t5++;
					}
					if(addBehavior == true) {
						t5++;
					}
					if(addCommunication == true) {
						t5++;
					}
		 	}		
		 	// key for teacher 6
		 	if (i % 6 == 5 && t6 <= MostGirls) {
		 	// checks if student id exists in any other teacher classes already before adding to this class
				boolean addAcademic = true;
				boolean addBehavior = true;
				boolean addCommunication = true;
				for(int r=0; r<teacherSix.length; r++) {
					if(ai >= sortedFemaleBehavior.length -1) {
						ai = sortedFemaleBehavior.length -1;
					}
					if(teacherOne[r][5] == sortedFemaleAcademic[ai][5] || teacherTwo[r][5] == sortedFemaleAcademic[ai][5] || 
							teacherThree[r][5] == sortedFemaleAcademic[ai][5] || teacherFour[r][5] == sortedFemaleAcademic[ai][5]
							|| teacherFive[r][5] == sortedFemaleAcademic[ai][5] || teacherSix[r][5] == sortedFemaleAcademic[ai][5]) {
									addAcademic = false; // wont want to add this priority academic student cause it would already be in a class
									if(ai < sortedFemaleBehavior.length -1) {
										ai++;
									}
									// checks next available ai -- we'll do one extra check. and extras will be added at the end
/*									if(teacherOne[r][5] == sortedFemaleAcademic[ai][5] || teacherTwo[r][5] == sortedFemaleAcademic[ai][5] || 
										teacherThree[r][5] == sortedFemaleAcademic[ai][5] || teacherFour[r][5] == sortedFemaleAcademic[ai][5]
										|| teacherFive[r][5] == sortedFemaleAcademic[ai][5] || teacherSix[r][5] == sortedFemaleAcademic[ai][5]) {
												ai++;
												if(teacherOne[r][5] == sortedFemaleAcademic[ai][5] || teacherTwo[r][5] == sortedFemaleAcademic[ai][5] || 
														teacherThree[r][5] == sortedFemaleAcademic[ai][5] || teacherFour[r][5] == sortedFemaleAcademic[ai][5]
														|| teacherFive[r][5] == sortedFemaleAcademic[ai][5] || teacherSix[r][5] == sortedFemaleAcademic[ai][5]) {
															ai++;
												}
												else {
													addAcademic = true;
												}
									}
									else {
										addAcademic = true;
									}*/
						}
					if(ci >= sortedFemaleBehavior.length -1) {
						ci = sortedFemaleBehavior.length -1;
					}
						if(teacherOne[r][5] == sortedFemaleCommunication[ci][5] || teacherTwo[r][5] == sortedFemaleCommunication[ci][5] || 
							teacherThree[r][5] == sortedFemaleCommunication[ci][5] || teacherFour[r][5] == sortedFemaleCommunication[ci][5]
							|| teacherFive[r][5] == sortedFemaleCommunication[ci][5] || teacherSix[r][5] == sortedFemaleCommunication[ci][5]
									|| sortedFemaleAcademic[ai][5] == sortedFemaleCommunication[ci][5]) {
									addCommunication = false; // wont want to add this priority communication student cause it would already be in a class
									if(ci < sortedFemaleBehavior.length -1) {
										ci++;
									}
						}
						if(bi >= sortedFemaleBehavior.length -1) {
							bi = sortedFemaleBehavior.length -1;
						}
						if(teacherOne[r][5] == sortedFemaleBehavior[bi][5] || teacherTwo[r][5] == sortedFemaleBehavior[bi][5] || 
							teacherThree[r][5] == sortedFemaleBehavior[bi][5] || teacherFour[r][5] == sortedFemaleBehavior[bi][5]
							|| teacherFive[r][5] == sortedFemaleBehavior[bi][5] || teacherSix[r][5] == sortedFemaleBehavior[bi][5]  || 
									sortedFemaleAcademic[ai][5] == sortedFemaleBehavior[bi][5] || sortedFemaleCommunication[ci][5] == sortedFemaleBehavior[bi][5]) {
									addBehavior = false; // wont want to add this priority behavior student cause it would already be in a class
									if(bi < sortedFemaleBehavior.length -1) {
										bi++;
									}
						}
				}
					// checks student about to be added has old teacher in common with under 6 other students before adding
				/*	if (teacherSix[r][3] == sortedFemaleAcademic[ai][3] && addAcademic == true) {
						if(sameClass6 < 7) {
							sameClass6++;
						}
						else {
							addAcademic = false; // check next kid maybe
						}
					}
					if (teacherSix[r][3] == sortedFemaleCommunication[ci][3] && addCommunication == true) {
						if(sameClass6 < 7) {
							sameClass6++;
						}
						else {
							addCommunication = false;
						}
					}
					if (teacherSix[r][3] == sortedFemaleBehavior[bi][3] && addBehavior == true) {
						if(sameClass6 < 7) {
							sameClass6++;
						}
						else {
							addBehavior = false;
						}
					}*/					
			//	}
				// academic student same class check
				if(addAcademic == true) {
					if(sortedFemaleAcademic[ai][3] == 1) {
						sameClass6_1++;
						if(sameClass6_1 > 6) {
							addAcademic = false; // or try to get next aim
						}
					}
					if(sortedFemaleAcademic[ai][3] == 2) {
						sameClass6_2++;
						if(sameClass6_2 > 6) {
							addAcademic = false; // or try to get next aim
						}
					}
					if(sortedFemaleAcademic[ai][3] == 3) {
						sameClass6_3++;
						if(sameClass6_3 > 6) {
							addAcademic = false; // or try to get next aim
						}
					}
					if(sortedFemaleAcademic[ai][3] == 4) {
						sameClass6_4++;
						if(sameClass6_4 > 6) {
							addAcademic = false; // or try to get next aim
						}
					}
					if(sortedFemaleAcademic[ai][3] == 5) {
						sameClass6_5++;
						if(sameClass6_5 > 6) {
							addAcademic = false; // or try to get next aim
						}
					}
					if(sortedFemaleAcademic[ai][3] == 6) {
						sameClass6_6++;
						if(sameClass6_6 > 6) {
							addAcademic = false; // or try to get next aim
						}
					}
				}
				// communication student same class check
				if(addCommunication == true) {
					if(sortedFemaleCommunication[ci][3] == 1) {
						sameClass6_1++;
						if(sameClass6_1 > 6) {
							addCommunication = false; // or try to get next ci
						}
					}
					if(sortedFemaleCommunication[ci][3] == 2) {
						sameClass6_2++;
						if(sameClass6_2 > 6) {
							addCommunication = false; // or try to get next ci
						}
					}
					if(sortedFemaleCommunication[ci][3] == 3) {
						sameClass6_3++;
						if(sameClass6_3 > 6) {
							addCommunication = false; // or try to get next ci
						}
					}
					if(sortedFemaleCommunication[ci][3] == 4) {
						sameClass6_4++;
						if(sameClass6_4 > 6) {
							addCommunication = false; // or try to get next ci
						}
					}
					if(sortedFemaleCommunication[ci][3] == 5) {
						sameClass6_5++;
						if(sameClass6_5 > 6) {
							addCommunication = false; // or try to get next ci
						}
					}
					if(sortedFemaleCommunication[ci][3] == 6) {
						sameClass6_6++;
						if(sameClass6_6 > 6) {
							addCommunication = false; // or try to get next ci
						}
					}
				}
				// behavior student same class check
				if(addBehavior == true) {
					if(sortedFemaleBehavior[bi][3] == 1) {
						sameClass6_1++;
						if(sameClass6_1 > 6) {
							addBehavior = false; // or try to get next bi
						}
					}
					if(sortedFemaleBehavior[bi][3] == 2) {
						sameClass6_2++;
						if(sameClass6_2 > 6) {
							addBehavior = false; // or try to get next bi
						}
					}
					if(sortedFemaleBehavior[bi][3] == 3) {
						sameClass6_3++;
						if(sameClass6_3 > 6) {
							addBehavior = false; // or try to get next bi
						}
					}
					if(sortedFemaleBehavior[bi][3] == 4) {
						sameClass6_4++;
						if(sameClass6_4 > 6) {
							addBehavior = false; // or try to get next bi
						}
					}
					if(sortedFemaleBehavior[bi][3] == 5) {
						sameClass6_5++;
						if(sameClass6_5 > 6) {
							addBehavior = false; // or try to get next bi
						}
					}
					if(sortedFemaleBehavior[bi][3] == 6) {
						sameClass6_6++;
						if(sameClass6_6 > 6) {
							addBehavior = false; // or try to get next bi
						}
					}
				}
					if(addAcademic == true && addCommunication == true && addBehavior == true) {
						teacherSix[t6][0] = sortedFemaleAcademic[ai][0];
						teacherSix[t6][1] = sortedFemaleAcademic[ai][1];
						teacherSix[t6][2] = sortedFemaleAcademic[ai][2];
						teacherSix[t6][3] = sortedFemaleAcademic[ai][3];
						teacherSix[t6][4] = sortedFemaleAcademic[ai][4];
						teacherSix[t6][5] = sortedFemaleAcademic[ai][5];
						teacherSix[t6+1][0] = sortedFemaleCommunication[ci][0];
						teacherSix[t6+1][1] = sortedFemaleCommunication[ci][1];
						teacherSix[t6+1][2] = sortedFemaleCommunication[ci][2];
						teacherSix[t6+1][3] = sortedFemaleCommunication[ci][3];
						teacherSix[t6+1][4] = sortedFemaleCommunication[ci][4];
						teacherSix[t6+1][5] = sortedFemaleCommunication[ci][5];
						teacherSix[t6+2][0] = sortedFemaleBehavior[bi][0];
						teacherSix[t6+2][1] = sortedFemaleBehavior[bi][1];
						teacherSix[t6+2][2] = sortedFemaleBehavior[bi][2];
						teacherSix[t6+2][3] = sortedFemaleBehavior[bi][3];
						teacherSix[t6+2][4] = sortedFemaleBehavior[bi][4];
						teacherSix[t6+2][5] = sortedFemaleBehavior[bi][5];
						ai++;
						bi++;
						ci++;
						i++;
					}
					else if(addAcademic == false && addCommunication == false && addBehavior == true) {
						teacherSix[t6][0] = sortedFemaleBehavior[bi][0];
						teacherSix[t6][1] = sortedFemaleBehavior[bi][1];
						teacherSix[t6][2] = sortedFemaleBehavior[bi][2];
						teacherSix[t6][3] = sortedFemaleBehavior[bi][3];
						teacherSix[t6][4] = sortedFemaleBehavior[bi][4];
						teacherSix[t6][5] = sortedFemaleBehavior[bi][5];
						bi++;
						i++;
					}
					else if(addAcademic == false && addCommunication == true && addBehavior == false) {
						teacherSix[t6][0] = sortedFemaleCommunication[ci][0];
						teacherSix[t6][1] = sortedFemaleCommunication[ci][1];
						teacherSix[t6][2] = sortedFemaleCommunication[ci][2];
						teacherSix[t6][3] = sortedFemaleCommunication[ci][3];
						teacherSix[t6][4] = sortedFemaleCommunication[ci][4];
						teacherSix[t6][5] = sortedFemaleCommunication[ci][5];
						ci++;
						i++;
					}
					else if(addAcademic == true && addCommunication == false && addBehavior == false) {
						teacherSix[t6][0] = sortedFemaleAcademic[ai][0];
						teacherSix[t6][1] = sortedFemaleAcademic[ai][1];
						teacherSix[t6][2] = sortedFemaleAcademic[ai][2];
						teacherSix[t6][3] = sortedFemaleAcademic[ai][3];
						teacherSix[t6][4] = sortedFemaleAcademic[ai][4];
						teacherSix[t6][5] = sortedFemaleAcademic[ai][5];
						ai++;
						i++;
					}
					else if(addAcademic == false && addCommunication == true && addBehavior == true) {
						teacherSix[t6][0] = sortedFemaleCommunication[ci][0];
						teacherSix[t6][1] = sortedFemaleCommunication[ci][1];
						teacherSix[t6][2] = sortedFemaleCommunication[ci][2];
						teacherSix[t6][3] = sortedFemaleCommunication[ci][3];
						teacherSix[t6][4] = sortedFemaleCommunication[ci][4];
						teacherSix[t6][5] = sortedFemaleCommunication[ci][5];
						teacherSix[t6+1][0] = sortedFemaleBehavior[bi][0];
						teacherSix[t6+1][1] = sortedFemaleBehavior[bi][1];
						teacherSix[t6+1][2] = sortedFemaleBehavior[bi][2];
						teacherSix[t6+1][3] = sortedFemaleBehavior[bi][3];
						teacherSix[t6+1][4] = sortedFemaleBehavior[bi][4];
						teacherSix[t6+1][5] = sortedFemaleBehavior[bi][5];
						ci++;
						bi++;
						i++;
					}
					else if(addAcademic == true && addCommunication == false && addBehavior == true) {
						teacherSix[t6][0] = sortedFemaleAcademic[ai][0];
						teacherSix[t6][1] = sortedFemaleAcademic[ai][1];
						teacherSix[t6][2] = sortedFemaleAcademic[ai][2];
						teacherSix[t6][3] = sortedFemaleAcademic[ai][3];
						teacherSix[t6][4] = sortedFemaleAcademic[ai][4];
						teacherSix[t6][5] = sortedFemaleAcademic[ai][5];
						teacherSix[t6+1][0] = sortedFemaleBehavior[bi][0];
						teacherSix[t6+1][1] = sortedFemaleBehavior[bi][1];
						teacherSix[t6+1][2] = sortedFemaleBehavior[bi][2];
						teacherSix[t6+1][3] = sortedFemaleBehavior[bi][3];
						teacherSix[t6+1][4] = sortedFemaleBehavior[bi][4];
						teacherSix[t6+1][5] = sortedFemaleBehavior[bi][5];
						ai++;
						bi++;
						i++;
					}
					else if(addAcademic == true && addCommunication == true && addBehavior == false) {
						teacherSix[t6][0] = sortedFemaleCommunication[ci][0];
						teacherSix[t6][1] = sortedFemaleCommunication[ci][1];
						teacherSix[t6][2] = sortedFemaleCommunication[ci][2];
						teacherSix[t6][3] = sortedFemaleCommunication[ci][3];
						teacherSix[t6][4] = sortedFemaleCommunication[ci][4];
						teacherSix[t6][5] = sortedFemaleCommunication[ci][5];
						teacherSix[t6+1][0] = sortedFemaleAcademic[ai][0];
						teacherSix[t6+1][1] = sortedFemaleAcademic[ai][1];
						teacherSix[t6+1][2] = sortedFemaleAcademic[ai][2];
						teacherSix[t6+1][3] = sortedFemaleAcademic[ai][3];
						teacherSix[t6+1][4] = sortedFemaleAcademic[ai][4];
						teacherSix[t6+1][5] = sortedFemaleAcademic[ai][5];
						ci++;
						ai++;
						i++;
					}
					else if(addAcademic == false && addCommunication == false && addBehavior == false) {
						// go to next teacher without incrementing next best 3 students if nobody fits in this class
						i++;
					}  					
					// finding new t6 value for the index once its this teachers turn again
					if(addAcademic == true) {
						t6++;
					}
					if(addBehavior == true) {
						t6++;
					}
					if(addCommunication == true) {
						t6++;
					}
		 	}
		}
	}
	
	public static void Male(Integer[][] boys, int size, Integer[][] teachOne, Integer[][] teachTwo, Integer[][] teachThree, 
			Integer[][] teachFour, Integer[][] teachFive, Integer[][] teachSix) {

		// make copy of girls to use for other arrays
		Integer[][] boysV1 = copyBoys(boys);			
		Integer[][] boysV2 = copyBoys(boys);
		Integer[][] boysV3 = copyBoys(boys);
		Arrays.sort(boysV1, AcademicComparator);
		Arrays.sort(boysV2, CommunicationComparator);
		Arrays.sort(boysV3, BehaviorComparator);

		
		// sorts and stores the girls array 3 different times
		Integer[][] sortedMaleAcademic = copyBoys(boysV1);
		Integer[][] sortedMaleCommunication = copyBoys(boysV2);
		Integer[][] sortedMaleBehavior = copyBoys(boysV3);
/*
		int ii = 1 ;
		int once = 0;
		System.out.println("Academic ");
		for(int y=0; y<sortedMaleAcademic.length; y++) {
			for(int t=0; t<6; t++) {
				if(ii == 1 && once == 0) {
					System.out.print(ii+ ". ");
					System.out.print((sortedMaleAcademic[y][t]) + " ");
					once++;
					}
				else {
				System.out.print((sortedMaleAcademic[y][t]) + " ");
				}
			}
			System.out.println();
			ii++;
			System.out.print(ii+ ". ");
		}
		ii = 1;
		once = 0;
		System.out.println("Communication ");
		for(int y=0; y<sortedMaleCommunication.length; y++) {
			for(int t=0; t<6; t++) {
				if(ii == 1 && once == 0) {
					System.out.print(ii+ ". ");
					System.out.print((sortedMaleCommunication[y][t]) + " ");
					once++;
					}
				else {
				System.out.print((sortedMaleCommunication[y][t]) + " ");
				}
			}
			System.out.println();
			ii++;
			System.out.print(ii+ ". ");
		}
		ii = 1;
		once = 0;
		System.out.println("Behvaior ");
		for(int y=0; y<sortedMaleBehavior.length; y++) {
			for(int t=0; t<6; t++) {
				if(ii == 1 && once == 0) {
					System.out.print(ii+ ". ");
					System.out.print((sortedMaleBehavior[y][t]) + " ");
					once++;
					}
				else {
				System.out.print((sortedMaleBehavior[y][t]) + " ");
				}
			}
			System.out.println();
			ii++;
			System.out.print(ii+ ". ");
		}
		*/
		// for Male categories we will add to the first teach first (look below)
		// for male categories we will add to the last teach first and go in backwards order 
		int i=0;
		while(i < boys.length)	 {
			// key for teach 1		
			if (i % 6 == 5) {
				// checks if student id exists in all other teach classes already before adding to this class
				boolean addAcademic = true;
				boolean addBehavior = true;
				boolean addCommunication = true;
				for(int r=0; r<teachOne.length; r++) {
					if(aim >= sortedMaleBehavior.length -1) {
						aim = sortedMaleBehavior.length -1;
					}
					if(teachOne[r][5] == sortedMaleAcademic[aim][5] || teachTwo[r][5] == sortedMaleAcademic[aim][5] || 
							teachThree[r][5] == sortedMaleAcademic[aim][5] || teachFour[r][5] == sortedMaleAcademic[aim][5]
									|| teachFive[r][5] == sortedMaleAcademic[aim][5] || teachSix[r][5] == sortedMaleAcademic[aim][5]) {
						addAcademic = false; // wont want to add this priority academic student cause it would already be in a class
						if(aim < sortedMaleBehavior.length -1) {
							aim++;
						}
						// checks next avaimlable aim -- we'll do one extra check. and extras will be added at the end
//						if(teachOne[r][5] == sortedMaleAcademic[aim][5] || teachTwo[r][5] == sortedMaleAcademic[aim][5] || 
//								teachThree[r][5] == sortedMaleAcademic[aim][5] || teachFour[r][5] == sortedMaleAcademic[aim][5]
//										|| teachFive[r][5] == sortedMaleAcademic[aim][5] || teachSix[r][5] == sortedMaleAcademic[aim][5]) {
//							aim++;
//							if(teachOne[r][5] == sortedMaleAcademic[aim][5] || teachTwo[r][5] == sortedMaleAcademic[aim][5] || 
//									teachThree[r][5] == sortedMaleAcademic[aim][5] || teachFour[r][5] == sortedMaleAcademic[aim][5]
//											|| teachFive[r][5] == sortedMaleAcademic[aim][5] || teachSix[r][5] == sortedMaleAcademic[aim][5]) {
//								aim++;
//							}
//							else {
//								addAcademic = true;
//							}
//						}
//						else {
//							addAcademic = true;
//						}
					}
					if(cim >= sortedMaleBehavior.length -1) {
						cim = sortedMaleBehavior.length -1;
					}
					if(teachOne[r][5] == sortedMaleCommunication[cim][5] || teachTwo[r][5] == sortedMaleCommunication[cim][5] || 
							teachThree[r][5] == sortedMaleCommunication[cim][5] || teachFour[r][5] == sortedMaleCommunication[cim][5]
									|| teachFive[r][5] == sortedMaleCommunication[cim][5] || teachSix[r][5] == sortedMaleCommunication[cim][5] 
									|| sortedMaleAcademic[aim][5] == sortedMaleCommunication[cim][5]) {
						addCommunication = false; // wont want to add this priority communication student cause it would already be in a class
						if(cim < sortedMaleBehavior.length -1) {
							cim++;
						}
					}
					if(bim >= sortedMaleBehavior.length -1) {
						bim = sortedMaleBehavior.length -1;
					}
					if(teachOne[r][5] == sortedMaleBehavior[bim][5] || teachTwo[r][5] == sortedMaleBehavior[bim][5] || 
							teachThree[r][5] == sortedMaleBehavior[bim][5] || teachFour[r][5] == sortedMaleBehavior[bim][5]
									|| teachFive[r][5] == sortedMaleBehavior[bim][5] || teachSix[r][5] == sortedMaleBehavior[bim][5] || 
									sortedMaleAcademic[aim][5] == sortedMaleBehavior[bim][5] || sortedMaleCommunication[cim][5] == sortedMaleBehavior[bim][5]) {
						addBehavior = false; // wont want to add this priority behavior student cause it would already be in a class
						if(bim < sortedMaleBehavior.length -1) {
							bim++;
						}
					}
				}
					// checks student about to be added has old teach in common with under 6 other students before adding
/*					if (teachOne[r][3] == sortedMaleAcademic[aim][3] && addAcademic == true) {
						if(sameClass1 < 7) {
							sameClass1++;
						}
						else {
							addAcademic = false;
						}
					}
					if (teachOne[r][3] == sortedMaleCommunication[cim][3] && addCommunication == true) {
						if(sameClass1 < 7) {
							sameClass1++;
						}
						else {
							addCommunication = false;
						}
					}
					if (teachOne[r][3] == sortedMaleBehavior[bim][3] && addBehavior == true) {
						if(sameClass1 < 7) {
							sameClass1++;
						}
						else {
							addBehavior = false;
						}
					}					
				}*/
				// academic student same class check
				if(addAcademic == true) {
					if(sortedMaleAcademic[aim][3] == 1) {
						sameClass1_1++;
						if(sameClass1_1 > 6) {
							addAcademic = false; // or try to get next aim
						}
					}
					if(sortedMaleAcademic[aim][3] == 2) {
						sameClass1_2++;
						if(sameClass1_2 > 6) {
							addAcademic = false; // or try to get next aim
						}
					}
					if(sortedMaleAcademic[aim][3] == 3) {
						sameClass1_3++;
						if(sameClass1_3 > 6) {
							addAcademic = false; // or try to get next aim
						}
					}
					if(sortedMaleAcademic[aim][3] == 4) {
						sameClass1_4++;
						if(sameClass1_4 > 6) {
							addAcademic = false; // or try to get next aim
						}
					}
					if(sortedMaleAcademic[aim][3] == 5) {
						sameClass1_5++;
						if(sameClass1_5 > 6) {
							addAcademic = false; // or try to get next aim
						}
					}
					if(sortedMaleAcademic[aim][3] == 6) {
						sameClass1_6++;
						if(sameClass1_6 > 6) {
							addAcademic = false; // or try to get next aim
						}
					}
				}
				// communication student same class check
				if(addCommunication == true) {
					if(sortedMaleCommunication[cim][3] == 1) {
						sameClass1_1++;
						if(sameClass1_1 > 6) {
							addCommunication = false; // or try to get next cim
						}
					}
					if(sortedMaleCommunication[cim][3] == 2) {
						sameClass1_2++;
						if(sameClass1_2 > 6) {
							addCommunication = false; // or try to get next cim
						}
					}
					if(sortedMaleCommunication[cim][3] == 3) {
						sameClass1_3++;
						if(sameClass1_3 > 6) {
							addCommunication = false; // or try to get next cim
						}
					}
					if(sortedMaleCommunication[cim][3] == 4) {
						sameClass1_4++;
						if(sameClass1_4 > 6) {
							addCommunication = false; // or try to get next cim
						}
					}
					if(sortedMaleCommunication[cim][3] == 5) {
						sameClass1_5++;
						if(sameClass1_5 > 6) {
							addCommunication = false; // or try to get next cim
						}
					}
					if(sortedMaleCommunication[cim][3] == 6) {
						sameClass1_6++;
						if(sameClass1_6 > 6) {
							addCommunication = false; // or try to get next cim
						}
					}
				}
				// behavior student same class check
				if(addBehavior == true) {
					if(sortedMaleBehavior[bim][3] == 1) {
						sameClass1_1++;
						if(sameClass1_1 > 6) {
							addBehavior = false; // or try to get next bim
						}
					}
					if(sortedMaleBehavior[bim][3] == 2) {
						sameClass1_2++;
						if(sameClass1_2 > 6) {
							addBehavior = false; // or try to get next bim
						}
					}
					if(sortedMaleBehavior[bim][3] == 3) {
						sameClass1_3++;
						if(sameClass1_3 > 6) {
							addBehavior = false; // or try to get next bim
						}
					}
					if(sortedMaleBehavior[bim][3] == 4) {
						sameClass1_4++;
						if(sameClass1_4 > 6) {
							addBehavior = false; // or try to get next bim
						}
					}
					if(sortedMaleBehavior[bim][3] == 5) {
						sameClass1_5++;
						if(sameClass1_5 > 6) {
							addBehavior = false; // or try to get next bim
						}
					}
					if(sortedMaleBehavior[bim][3] == 6) {
						sameClass1_6++;
						if(sameClass1_6 > 6) {
							addBehavior = false; // or try to get next bim
						}
					}
				}
				if(addAcademic == true && addCommunication == true && addBehavior == true) {
					teachOne[t1m][0] = sortedMaleAcademic[aim][0];
					teachOne[t1m][1] = sortedMaleAcademic[aim][1];
					teachOne[t1m][2] = sortedMaleAcademic[aim][2];
					teachOne[t1m][3] = sortedMaleAcademic[aim][3];
					teachOne[t1m][4] = sortedMaleAcademic[aim][4];
					teachOne[t1m][5] = sortedMaleAcademic[aim][5];
					teachOne[t1m+1][0] = sortedMaleCommunication[cim][0];
					teachOne[t1m+1][1] = sortedMaleCommunication[cim][1];
					teachOne[t1m+1][2] = sortedMaleCommunication[cim][2];
					teachOne[t1m+1][3] = sortedMaleCommunication[cim][3];
					teachOne[t1m+1][4] = sortedMaleCommunication[cim][4];
					teachOne[t1m+1][5] = sortedMaleCommunication[cim][5];
					teachOne[t1m+2][0] = sortedMaleBehavior[bim][0];
					teachOne[t1m+2][1] = sortedMaleBehavior[bim][1];
					teachOne[t1m+2][2] = sortedMaleBehavior[bim][2];
					teachOne[t1m+2][3] = sortedMaleBehavior[bim][3];
					teachOne[t1m+2][4] = sortedMaleBehavior[bim][4];
					teachOne[t1m+2][5] = sortedMaleBehavior[bim][5];
					aim++;
					bim++;
					cim++;
					i++;
				}
				else if(addAcademic == false && addCommunication == false && addBehavior == true) {
					teachOne[t1m][0] = sortedMaleBehavior[bim][0];
					teachOne[t1m][1] = sortedMaleBehavior[bim][1];
					teachOne[t1m][2] = sortedMaleBehavior[bim][2];
					teachOne[t1m][3] = sortedMaleBehavior[bim][3];
					teachOne[t1m][4] = sortedMaleBehavior[bim][4];
					teachOne[t1m][5] = sortedMaleBehavior[bim][5];
					bim++;
					i++;
				}
				else if(addAcademic == false && addCommunication == true && addBehavior == false) {
					teachOne[t1m][0] = sortedMaleCommunication[cim][0];
					teachOne[t1m][1] = sortedMaleCommunication[cim][1];
					teachOne[t1m][2] = sortedMaleCommunication[cim][2];
					teachOne[t1m][3] = sortedMaleCommunication[cim][3];
					teachOne[t1m][4] = sortedMaleCommunication[cim][4];
					teachOne[t1m][5] = sortedMaleCommunication[cim][5];
					cim++;
					i++;
				}
				else if(addAcademic == true && addCommunication == false && addBehavior == false) {
					teachOne[t1m][0] = sortedMaleAcademic[aim][0];
					teachOne[t1m][1] = sortedMaleAcademic[aim][1];
					teachOne[t1m][2] = sortedMaleAcademic[aim][2];
					teachOne[t1m][3] = sortedMaleAcademic[aim][3];
					teachOne[t1m][4] = sortedMaleAcademic[aim][4];
					teachOne[t1m][5] = sortedMaleAcademic[aim][5];
					aim++;
					i++;
				}
				else if(addAcademic == false && addCommunication == true && addBehavior == true) {
					teachOne[t1m][0] = sortedMaleCommunication[cim][0];
					teachOne[t1m][1] = sortedMaleCommunication[cim][1];
					teachOne[t1m][2] = sortedMaleCommunication[cim][2];
					teachOne[t1m][3] = sortedMaleCommunication[cim][3];
					teachOne[t1m][4] = sortedMaleCommunication[cim][4];
					teachOne[t1m][5] = sortedMaleCommunication[cim][5];
					teachOne[t1m+1][0] = sortedMaleBehavior[bim][0];
					teachOne[t1m+1][1] = sortedMaleBehavior[bim][1];
					teachOne[t1m+1][2] = sortedMaleBehavior[bim][2];
					teachOne[t1m+1][3] = sortedMaleBehavior[bim][3];
					teachOne[t1m+1][4] = sortedMaleBehavior[bim][4];
					teachOne[t1m+1][5] = sortedMaleBehavior[bim][5];
					cim++;
					bim++;
					i++;
				}
				else if(addAcademic == true && addCommunication == false && addBehavior == true) {
					teachOne[t1m][0] = sortedMaleAcademic[aim][0];
					teachOne[t1m][1] = sortedMaleAcademic[aim][1];
					teachOne[t1m][2] = sortedMaleAcademic[aim][2];
					teachOne[t1m][3] = sortedMaleAcademic[aim][3];
					teachOne[t1m][4] = sortedMaleAcademic[aim][4];
					teachOne[t1m][5] = sortedMaleAcademic[aim][5];
					teachOne[t1m+1][0] = sortedMaleBehavior[bim][0];
					teachOne[t1m+1][1] = sortedMaleBehavior[bim][1];
					teachOne[t1m+1][2] = sortedMaleBehavior[bim][2];
					teachOne[t1m+1][3] = sortedMaleBehavior[bim][3];
					teachOne[t1m+1][4] = sortedMaleBehavior[bim][4];
					teachOne[t1m+1][5] = sortedMaleBehavior[bim][5];
					aim++;
					bim++;
					i++;
				}
				else if(addAcademic == true && addCommunication == true && addBehavior == false) {
					teachOne[t1m][0] = sortedMaleCommunication[cim][0];
					teachOne[t1m][1] = sortedMaleCommunication[cim][1];
					teachOne[t1m][2] = sortedMaleCommunication[cim][2];
					teachOne[t1m][3] = sortedMaleCommunication[cim][3];
					teachOne[t1m][4] = sortedMaleCommunication[cim][4];
					teachOne[t1m][5] = sortedMaleCommunication[cim][5];
					teachOne[t1m+1][0] = sortedMaleAcademic[aim][0];
					teachOne[t1m+1][1] = sortedMaleAcademic[aim][1];
					teachOne[t1m+1][2] = sortedMaleAcademic[aim][2];
					teachOne[t1m+1][3] = sortedMaleAcademic[aim][3];
					teachOne[t1m+1][4] = sortedMaleAcademic[aim][4];
					teachOne[t1m+1][5] = sortedMaleAcademic[aim][5];
					cim++;
					aim++;
					i++;
				}
				else if(addAcademic == false && addCommunication == false && addBehavior == false) {
					// go to next teach without incrementing next best 3 students if nobody fits in this class
					i++;
				}  					
				// finding new t1m value for the index once its this teachs turn agaimn
				if(addAcademic == true) {
					t1m++;
					//	teach1++;
				}
				if(addBehavior == true) {
					t1m++;
					//	teach1++;
				}
				if(addCommunication == true) {
					t1m++;
					//		teach1++;
				}
			}
			// key for teach 2
			if (i % 6 == 4) {
				// checks if student id exists in all other teach classes already before adding to this class
				boolean addAcademic = true;
				boolean addBehavior = true;
				boolean addCommunication = true;
				for(int r=0; r<teachTwo.length; r++) {
					if(aim >= sortedMaleBehavior.length -1) {
						aim = sortedMaleBehavior.length -1;
					}
					if(teachOne[r][5] == sortedMaleAcademic[aim][5] || teachTwo[r][5] == sortedMaleAcademic[aim][5] || 
							teachThree[r][5] == sortedMaleAcademic[aim][5] || teachFour[r][5] == sortedMaleAcademic[aim][5]
									|| teachFive[r][5] == sortedMaleAcademic[aim][5] || teachSix[r][5] == sortedMaleAcademic[aim][5]) {
						addAcademic = false; // wont want to add this priority academic student cause it would already be in a class
						if(aim < sortedMaleBehavior.length -1) {
							aim++;
						}
						// checks next avaimlable aim -- we'll do one extra check. and extras will be added at the end
/*						if(teachOne[r][5] == sortedMaleAcademic[aim][5] || teachTwo[r][5] == sortedMaleAcademic[aim][5] || 
								teachThree[r][5] == sortedMaleAcademic[aim][5] || teachFour[r][5] == sortedMaleAcademic[aim][5]
										|| teachFive[r][5] == sortedMaleAcademic[aim][5] || teachSix[r][5] == sortedMaleAcademic[aim][5]) {
							aim++;
							if(teachOne[r][5] == sortedMaleAcademic[aim][5] || teachTwo[r][5] == sortedMaleAcademic[aim][5] || 
									teachThree[r][5] == sortedMaleAcademic[aim][5] || teachFour[r][5] == sortedMaleAcademic[aim][5]
											|| teachFive[r][5] == sortedMaleAcademic[aim][5] || teachSix[r][5] == sortedMaleAcademic[aim][5]) {
								aim++;
							}
							else {
								addAcademic = true;
							}
						}
						else {
							addAcademic = true;
						}*/
					}
					if(cim >= sortedMaleBehavior.length -1) {
						cim = sortedMaleBehavior.length -1;
					}
					if(teachOne[r][5] == sortedMaleCommunication[cim][5] || teachTwo[r][5] == sortedMaleCommunication[cim][5] || 
							teachThree[r][5] == sortedMaleCommunication[cim][5] || teachFour[r][5] == sortedMaleCommunication[cim][5]
									|| teachFive[r][5] == sortedMaleCommunication[cim][5] || teachSix[r][5] == sortedMaleCommunication[cim][5]
											|| sortedMaleAcademic[aim][5] == sortedMaleCommunication[cim][5]) {
						addCommunication = false; // wont want to add this priority communication student cause it would already be in a class
						if(cim < sortedMaleBehavior.length -1) {
							cim++;
						}
					}
					if(bim >= sortedMaleBehavior.length -1) {
						bim = sortedMaleBehavior.length -1;
					}
					if(teachOne[r][5] == sortedMaleBehavior[bim][5] || teachTwo[r][5] == sortedMaleBehavior[bim][5] || 
							teachThree[r][5] == sortedMaleBehavior[bim][5] || teachFour[r][5] == sortedMaleBehavior[bim][5]
									|| teachFive[r][5] == sortedMaleBehavior[bim][5] || teachSix[r][5] == sortedMaleBehavior[bim][5] || 
											sortedMaleAcademic[aim][5] == sortedMaleBehavior[bim][5] || sortedMaleCommunication[cim][5] == sortedMaleBehavior[bim][5]) {
						addBehavior = false; // wont want to add this priority behavior student cause it would already be in a class
						if(bim < sortedMaleBehavior.length -1) {
							bim++;
						}
					}
				}
/*					// checks student about to be added has old teach in common with under 6 other students before adding
					if (teachTwo[r][3] == sortedMaleAcademic[aim][3] && addAcademic == true) {
						if(sameClass2 < 7) {
							sameClass2++;
						}
						else {
							addAcademic = false;
						}
					}
					if (teachTwo[r][3] == sortedMaleCommunication[cim][3] && addCommunication == true) {
						if(sameClass2 < 7) {
							sameClass2++;
						}
						else {
							addCommunication = false;
						}
					}
					if (teachTwo[r][3] == sortedMaleBehavior[bim][3] && addBehavior == true) {
						if(sameClass2 < 7) {
							sameClass2++;
						}
						else {
							addBehavior = false;
						}
					}					
				}*/
				// academic student same class check
				if(addAcademic == true) {
					if(sortedMaleAcademic[aim][3] == 1) {
						sameClass2_1++;
						if(sameClass2_1 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
					if(sortedMaleAcademic[aim][3] == 2) {
						sameClass2_2++;
						if(sameClass2_2 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
					if(sortedMaleAcademic[aim][3] == 3) {
						sameClass2_3++;
						if(sameClass2_3 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
					if(sortedMaleAcademic[aim][3] == 4) {
						sameClass2_4++;
						if(sameClass2_4 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
					if(sortedMaleAcademic[aim][3] == 5) {
						sameClass2_5++;
						if(sameClass2_5 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
					if(sortedMaleAcademic[aim][3] == 6) {
						sameClass2_6++;
						if(sameClass2_6 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
				}
				// communication student same class check
				if(addCommunication == true) {
					if(sortedMaleCommunication[cim][3] == 1) {
						sameClass2_1++;
						if(sameClass2_1 > 6) {
							addCommunication = false; // or try to get next cim
						}
					}
					if(sortedMaleCommunication[cim][3] == 2) {
						sameClass2_2++;
						if(sameClass2_2 > 6) {
							addCommunication = false; // or try to get next cim
						}
					}
					if(sortedMaleCommunication[cim][3] == 3) {
						sameClass2_3++;
						if(sameClass2_3 > 6) {
							addCommunication = false; // or try to get next cim
						}
					}
					if(sortedMaleCommunication[cim][3] == 4) {
						sameClass2_4++;
						if(sameClass2_4 > 6) {
							addCommunication = false; // or try to get next cim
						}
					}
					if(sortedMaleCommunication[cim][3] == 5) {
						sameClass2_5++;
						if(sameClass2_5 > 6) {
							addCommunication = false; // or try to get next cim
						}
					}
					if(sortedMaleCommunication[cim][3] == 6) {
						sameClass2_6++;
						if(sameClass2_6 > 6) {
							addCommunication = false; // or try to get next cim
						}
					}
				}
				// behavior student same class check
				if(addBehavior == true) {
					if(sortedMaleBehavior[bim][3] == 1) {
						sameClass2_1++;
						if(sameClass2_1 > 6) {
							addBehavior = false; // or try to get next bim
						}
					}
					if(sortedMaleBehavior[bim][3] == 2) {
						sameClass2_2++;
						if(sameClass2_2 > 6) {
							addBehavior = false; // or try to get next bim
						}
					}
					if(sortedMaleBehavior[bim][3] == 3) {
						sameClass2_3++;
						if(sameClass2_3 > 6) {
							addBehavior = false; // or try to get next bim
						}
					}
					if(sortedMaleBehavior[bim][3] == 4) {
						sameClass2_4++;
						if(sameClass2_4 > 6) {
							addBehavior = false; // or try to get next bim
						}
					}
					if(sortedMaleBehavior[bim][3] == 5) {
						sameClass2_5++;
						if(sameClass2_5 > 6) {
							addBehavior = false; // or try to get next bim
						}
					}
					if(sortedMaleBehavior[bim][3] == 6) {
						sameClass2_6++;
						if(sameClass2_6 > 6) {
							addBehavior = false; // or try to get next bim
						}
					}
				}

				if(addAcademic == true && addCommunication == true && addBehavior == true) {
					teachTwo[t2m][0] = sortedMaleAcademic[aim][0];
					teachTwo[t2m][1] = sortedMaleAcademic[aim][1];
					teachTwo[t2m][2] = sortedMaleAcademic[aim][2];
					teachTwo[t2m][3] = sortedMaleAcademic[aim][3];
					teachTwo[t2m][4] = sortedMaleAcademic[aim][4];
					teachTwo[t2m][5] = sortedMaleAcademic[aim][5];
					teachTwo[t2m+1][0] = sortedMaleCommunication[cim][0];
					teachTwo[t2m+1][1] = sortedMaleCommunication[cim][1];
					teachTwo[t2m+1][2] = sortedMaleCommunication[cim][2];
					teachTwo[t2m+1][3] = sortedMaleCommunication[cim][3];
					teachTwo[t2m+1][4] = sortedMaleCommunication[cim][4];
					teachTwo[t2m+1][5] = sortedMaleCommunication[cim][5];
					teachTwo[t2m+2][0] = sortedMaleBehavior[bim][0];
					teachTwo[t2m+2][1] = sortedMaleBehavior[bim][1];
					teachTwo[t2m+2][2] = sortedMaleBehavior[bim][2];
					teachTwo[t2m+2][3] = sortedMaleBehavior[bim][3];
					teachTwo[t2m+2][4] = sortedMaleBehavior[bim][4];
					teachTwo[t2m+2][5] = sortedMaleBehavior[bim][5];
					aim++;
					bim++;
					cim++;
					i++;
				}
				else if(addAcademic == false && addCommunication == false && addBehavior == true) {
					teachTwo[t2m][0] = sortedMaleBehavior[bim][0];
					teachTwo[t2m][1] = sortedMaleBehavior[bim][1];
					teachTwo[t2m][2] = sortedMaleBehavior[bim][2];
					teachTwo[t2m][3] = sortedMaleBehavior[bim][3];
					teachTwo[t2m][4] = sortedMaleBehavior[bim][4];
					teachTwo[t2m][5] = sortedMaleBehavior[bim][5];
					bim++;
					i++;
				}
				else if(addAcademic == false && addCommunication == true && addBehavior == false) {
					teachTwo[t2m][0] = sortedMaleCommunication[cim][0];
					teachTwo[t2m][1] = sortedMaleCommunication[cim][1];
					teachTwo[t2m][2] = sortedMaleCommunication[cim][2];
					teachTwo[t2m][3] = sortedMaleCommunication[cim][3];
					teachTwo[t2m][4] = sortedMaleCommunication[cim][4];
					teachTwo[t2m][5] = sortedMaleCommunication[cim][5];
					cim++;
					i++;
				}
				else if(addAcademic == true && addCommunication == false && addBehavior == false) {
					teachTwo[t2m][0] = sortedMaleAcademic[aim][0];
					teachTwo[t2m][1] = sortedMaleAcademic[aim][1];
					teachTwo[t2m][2] = sortedMaleAcademic[aim][2];
					teachTwo[t2m][3] = sortedMaleAcademic[aim][3];
					teachTwo[t2m][4] = sortedMaleAcademic[aim][4];
					teachTwo[t2m][5] = sortedMaleAcademic[aim][5];
					aim++;
					i++;
				}
				else if(addAcademic == false && addCommunication == true && addBehavior == true) {
					teachTwo[t2m][0] = sortedMaleCommunication[cim][0];
					teachTwo[t2m][1] = sortedMaleCommunication[cim][1];
					teachTwo[t2m][2] = sortedMaleCommunication[cim][2];
					teachTwo[t2m][3] = sortedMaleCommunication[cim][3];
					teachTwo[t2m][4] = sortedMaleCommunication[cim][4];
					teachTwo[t2m][5] = sortedMaleCommunication[cim][5];
					teachTwo[t2m+1][0] = sortedMaleBehavior[bim][0];
					teachTwo[t2m+1][1] = sortedMaleBehavior[bim][1];
					teachTwo[t2m+1][2] = sortedMaleBehavior[bim][2];
					teachTwo[t2m+1][3] = sortedMaleBehavior[bim][3];
					teachTwo[t2m+1][4] = sortedMaleBehavior[bim][4];
					teachTwo[t2m+1][5] = sortedMaleBehavior[bim][5];
					cim++;
					bim++; 
					i++;
				}
				else if(addAcademic == true && addCommunication == false && addBehavior == true) {
					teachTwo[t2m][0] = sortedMaleAcademic[aim][0];
					teachTwo[t2m][1] = sortedMaleAcademic[aim][1];
					teachTwo[t2m][2] = sortedMaleAcademic[aim][2];
					teachTwo[t2m][3] = sortedMaleAcademic[aim][3];
					teachTwo[t2m][4] = sortedMaleAcademic[aim][4];
					teachTwo[t2m][5] = sortedMaleAcademic[aim][5];
					teachTwo[t2m+1][0] = sortedMaleBehavior[bim][0];
					teachTwo[t2m+1][1] = sortedMaleBehavior[bim][1];
					teachTwo[t2m+1][2] = sortedMaleBehavior[bim][2];
					teachTwo[t2m+1][3] = sortedMaleBehavior[bim][3];
					teachTwo[t2m+1][4] = sortedMaleBehavior[bim][4];
					teachTwo[t2m+1][5] = sortedMaleBehavior[bim][5];
					aim++;
					bim++;
					i++;
				}
				else if(addAcademic == true && addCommunication == true && addBehavior == false) {
					teachTwo[t2m][0] = sortedMaleCommunication[cim][0];
					teachTwo[t2m][1] = sortedMaleCommunication[cim][1];
					teachTwo[t2m][2] = sortedMaleCommunication[cim][2];
					teachTwo[t2m][3] = sortedMaleCommunication[cim][3];
					teachTwo[t2m][4] = sortedMaleCommunication[cim][4];
					teachTwo[t2m][5] = sortedMaleCommunication[cim][5];
					teachTwo[t2m+1][0] = sortedMaleAcademic[aim][0];
					teachTwo[t2m+1][1] = sortedMaleAcademic[aim][1];
					teachTwo[t2m+1][2] = sortedMaleAcademic[aim][2];
					teachTwo[t2m+1][3] = sortedMaleAcademic[aim][3];
					teachTwo[t2m+1][4] = sortedMaleAcademic[aim][4];
					teachTwo[t2m+1][5] = sortedMaleAcademic[aim][5];
					cim++;
					aim++;
					i++;
				}
				else if(addAcademic == false && addCommunication == false && addBehavior == false) {
					// go to next teach without incrementing next best 3 students if nobody fits in this class
					i++;
				}  					
				// finding new t2m value for the index once its this teachs turn agaimn
				if(addAcademic == true) {
					t2m++;
				}
				if(addBehavior == true) {
					t2m++;
				}
				if(addCommunication == true) {
					t2m++;
				}
			}
			// key for teach 3
			if (i % 6 == 3) {
				// checks if student id exists in all other teach classes already before adding to this class
				boolean addAcademic = true;
				boolean addBehavior = true;
				boolean addCommunication = true;
				for(int r=0; r<teachThree.length; r++) {
					if(aim >= sortedMaleBehavior.length -1) {
						aim = sortedMaleBehavior.length -1;
					}
					if(teachOne[r][5] == sortedMaleAcademic[aim][5] || teachTwo[r][5] == sortedMaleAcademic[aim][5] || 
							teachThree[r][5] == sortedMaleAcademic[aim][5] || teachFour[r][5] == sortedMaleAcademic[aim][5]
									|| teachFive[r][5] == sortedMaleAcademic[aim][5] || teachSix[r][5] == sortedMaleAcademic[aim][5]) {
						addAcademic = false; // wont want to add this priority academic student cause it would already be in a class
						if(aim < sortedMaleBehavior.length -1) {
							aim++;
						}
						// checks next avaimlable aim -- we'll do one extra check. and extras will be added at the end
/*						if(teachOne[r][5] == sortedMaleAcademic[aim][5] || teachTwo[r][5] == sortedMaleAcademic[aim][5] || 
								teachThree[r][5] == sortedMaleAcademic[aim][5] || teachFour[r][5] == sortedMaleAcademic[aim][5]
										|| teachFive[r][5] == sortedMaleAcademic[aim][5] || teachSix[r][5] == sortedMaleAcademic[aim][5]) {
							aim++;
							if(teachOne[r][5] == sortedMaleAcademic[aim][5] || teachTwo[r][5] == sortedMaleAcademic[aim][5] || 
									teachThree[r][5] == sortedMaleAcademic[aim][5] || teachFour[r][5] == sortedMaleAcademic[aim][5]
											|| teachFive[r][5] == sortedMaleAcademic[aim][5] || teachSix[r][5] == sortedMaleAcademic[aim][5]) {
								aim++;
							}
							else {
								addAcademic = true;
							}
						}
						else {
							addAcademic = true;
						}*/
					}
					if(cim >= sortedMaleBehavior.length -1) {
						cim = sortedMaleBehavior.length -1;
					}
					if(teachOne[r][5] == sortedMaleCommunication[cim][5] || teachTwo[r][5] == sortedMaleCommunication[cim][5] || 
							teachThree[r][5] == sortedMaleCommunication[cim][5] || teachFour[r][5] == sortedMaleCommunication[cim][5]
									|| teachFive[r][5] == sortedMaleCommunication[cim][5] || teachSix[r][5] == sortedMaleCommunication[cim][5]
											|| sortedMaleAcademic[aim][5] == sortedMaleCommunication[cim][5]) {
						addCommunication = false; // wont want to add this priority communication student cause it would already be in a class
						if(cim < sortedMaleBehavior.length -1) {
							cim++;
						}
					}
					if(bim >= sortedMaleBehavior.length -1) {
						bim = sortedMaleBehavior.length -1;
					}
					if(teachOne[r][5] == sortedMaleBehavior[bim][5] || teachTwo[r][5] == sortedMaleBehavior[bim][5] || 
							teachThree[r][5] == sortedMaleBehavior[bim][5] || teachFour[r][5] == sortedMaleBehavior[bim][5]
									|| teachFive[r][5] == sortedMaleBehavior[bim][5] || teachSix[r][5] == sortedMaleBehavior[bim][5] || 
											sortedMaleAcademic[aim][5] == sortedMaleBehavior[bim][5] || sortedMaleCommunication[cim][5] == sortedMaleBehavior[bim][5]) {
						addBehavior = false; // wont want to add this priority behavior student cause it would already be in a class
						if(bim < sortedMaleBehavior.length -1) {
							bim++;
						}
					}
				}
					// checks student about to be added has old teach in common with under 6 other students before adding
/*					if (teachThree[r][3] == sortedMaleAcademic[aim][3] && addAcademic == true) {
						if(sameClass3 < 7) {
							sameClass3++;
						}
						else {
							addAcademic = false;
						}
					}
					if (teachThree[r][3] == sortedMaleCommunication[cim][3] && addCommunication == true) {
						if(sameClass3 < 7) {
							sameClass3++;
						}
						else {
							addCommunication = false;
						}
					}
					if (teachThree[r][3] == sortedMaleBehavior[bim][3] && addBehavior == true) {
						if(sameClass3 < 7) {
							sameClass3++;
						}
						else {
							addBehavior = false;
						}
					}					
				}*/
				// academic student same class check
				if(addAcademic == true) {
					if(sortedMaleAcademic[aim][3] == 1) {
						sameClass3_1++;
						if(sameClass3_1 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
					if(sortedMaleAcademic[aim][3] == 2) {
						sameClass3_2++;
						if(sameClass3_2 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
					if(sortedMaleAcademic[aim][3] == 3) {
						sameClass3_3++;
						if(sameClass3_3 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
					if(sortedMaleAcademic[aim][3] == 4) {
						sameClass3_4++;
						if(sameClass3_4 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
					if(sortedMaleAcademic[aim][3] == 5) {
						sameClass3_5++;
						if(sameClass3_5 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
					if(sortedMaleAcademic[aim][3] == 6) {
						sameClass3_6++;
						if(sameClass3_6 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
				}
				// communication student same class check
				if(addCommunication == true) {
					if(sortedMaleCommunication[cim][3] == 1) {
						sameClass3_1++;
						if(sameClass3_1 > 6) {
							addCommunication = false; // or try to get next cim
						}
					}
					if(sortedMaleCommunication[cim][3] == 2) {
						sameClass3_2++;
						if(sameClass3_2 > 6) {
							addCommunication = false; // or try to get next cim
						}
					}
					if(sortedMaleCommunication[cim][3] == 3) {
						sameClass3_3++;
						if(sameClass3_3 > 6) {
							addCommunication = false; // or try to get next cim
						}
					}
					if(sortedMaleCommunication[cim][3] == 4) {
						sameClass3_4++;
						if(sameClass3_4 > 6) {
							addCommunication = false; // or try to get next cim
						}
					}
					if(sortedMaleCommunication[cim][3] == 5) {
						sameClass3_5++;
						if(sameClass3_5 > 6) {
							addCommunication = false; // or try to get next cim
						}
					}
					if(sortedMaleCommunication[cim][3] == 6) {
						sameClass3_6++;
						if(sameClass3_6 > 6) {
							addCommunication = false; // or try to get next cim
						}
					}
				}
				// behavior student same class check
				if(addBehavior == true) {
					if(sortedMaleBehavior[bim][3] == 1) {
						sameClass3_1++;
						if(sameClass3_1 > 6) {
							addBehavior = false; // or try to get next bim
						}
					}
					if(sortedMaleBehavior[bim][3] == 2) {
						sameClass3_2++;
						if(sameClass3_2 > 6) {
							addBehavior = false; // or try to get next bim
						}
					}
					if(sortedMaleBehavior[bim][3] == 3) {
						sameClass3_3++;
						if(sameClass3_3 > 6) {
							addBehavior = false; // or try to get next bim
						}
					}
					if(sortedMaleBehavior[bim][3] == 4) {
						sameClass3_4++;
						if(sameClass3_4 > 6) {
							addBehavior = false; // or try to get next bim
						}
					}
					if(sortedMaleBehavior[bim][3] == 5) {
						sameClass3_5++;
						if(sameClass3_5 > 6) {
							addBehavior = false; // or try to get next bim
						}
					}
					if(sortedMaleBehavior[bim][3] == 6) {
						sameClass3_6++;
						if(sameClass3_6 > 6) {
							addBehavior = false; // or try to get next bim
						}
					}
				}
			
				if(addAcademic == true && addCommunication == true && addBehavior == true) {
					teachThree[t3m][0] = sortedMaleAcademic[aim][0];
					teachThree[t3m][1] = sortedMaleAcademic[aim][1];
					teachThree[t3m][2] = sortedMaleAcademic[aim][2];
					teachThree[t3m][3] = sortedMaleAcademic[aim][3];
					teachThree[t3m][4] = sortedMaleAcademic[aim][4];
					teachThree[t3m][5] = sortedMaleAcademic[aim][5];
					teachThree[t3m+1][0] = sortedMaleCommunication[cim][0];
					teachThree[t3m+1][1] = sortedMaleCommunication[cim][1];
					teachThree[t3m+1][2] = sortedMaleCommunication[cim][2];
					teachThree[t3m+1][3] = sortedMaleCommunication[cim][3];
					teachThree[t3m+1][4] = sortedMaleCommunication[cim][4];
					teachThree[t3m+1][5] = sortedMaleCommunication[cim][5];
					teachThree[t3m+2][0] = sortedMaleBehavior[bim][0];
					teachThree[t3m+2][1] = sortedMaleBehavior[bim][1];
					teachThree[t3m+2][2] = sortedMaleBehavior[bim][2];
					teachThree[t3m+2][3] = sortedMaleBehavior[bim][3];
					teachThree[t3m+2][4] = sortedMaleBehavior[bim][4];
					teachThree[t3m+2][5] = sortedMaleBehavior[bim][5];
					aim++;
					bim++;
					cim++;
					i++;
				}
				else if(addAcademic == false && addCommunication == false && addBehavior == true) {
					teachThree[t3m][0] = sortedMaleBehavior[bim][0];
					teachThree[t3m][1] = sortedMaleBehavior[bim][1];
					teachThree[t3m][2] = sortedMaleBehavior[bim][2];
					teachThree[t3m][3] = sortedMaleBehavior[bim][3];
					teachThree[t3m][4] = sortedMaleBehavior[bim][4];
					teachThree[t3m][5] = sortedMaleBehavior[bim][5];
					bim++;
					i++;
				}
				else if(addAcademic == false && addCommunication == true && addBehavior == false) {
					teachThree[t3m][0] = sortedMaleCommunication[cim][0];
					teachThree[t3m][1] = sortedMaleCommunication[cim][1];
					teachThree[t3m][2] = sortedMaleCommunication[cim][2];
					teachThree[t3m][3] = sortedMaleCommunication[cim][3];
					teachThree[t3m][4] = sortedMaleCommunication[cim][4];
					teachThree[t3m][5] = sortedMaleCommunication[cim][5];
					cim++;
					i++;
				}
				else if(addAcademic == true && addCommunication == false && addBehavior == false) {
					teachThree[t3m][0] = sortedMaleAcademic[aim][0];
					teachThree[t3m][1] = sortedMaleAcademic[aim][1];
					teachThree[t3m][2] = sortedMaleAcademic[aim][2];
					teachThree[t3m][3] = sortedMaleAcademic[aim][3];
					teachThree[t3m][4] = sortedMaleAcademic[aim][4];
					teachThree[t3m][5] = sortedMaleAcademic[aim][5];
					aim++;
					i++;
				}
				else if(addAcademic == false && addCommunication == true && addBehavior == true) {
					teachThree[t3m][0] = sortedMaleCommunication[cim][0];
					teachThree[t3m][1] = sortedMaleCommunication[cim][1];
					teachThree[t3m][2] = sortedMaleCommunication[cim][2];
					teachThree[t3m][3] = sortedMaleCommunication[cim][3];
					teachThree[t3m][4] = sortedMaleCommunication[cim][4];
					teachThree[t3m][5] = sortedMaleCommunication[cim][5];
					teachThree[t3m+1][0] = sortedMaleBehavior[bim][0];
					teachThree[t3m+1][1] = sortedMaleBehavior[bim][1];
					teachThree[t3m+1][2] = sortedMaleBehavior[bim][2];
					teachThree[t3m+1][3] = sortedMaleBehavior[bim][3];
					teachThree[t3m+1][4] = sortedMaleBehavior[bim][4];
					teachThree[t3m+1][5] = sortedMaleBehavior[bim][5];
					cim++;
					bim++;
					i++;
				}
				else if(addAcademic == true && addCommunication == false && addBehavior == true) {
					teachThree[t3m][0] = sortedMaleAcademic[aim][0];
					teachThree[t3m][1] = sortedMaleAcademic[aim][1];
					teachThree[t3m][2] = sortedMaleAcademic[aim][2];
					teachThree[t3m][3] = sortedMaleAcademic[aim][3];
					teachThree[t3m][4] = sortedMaleAcademic[aim][4];
					teachThree[t3m][5] = sortedMaleAcademic[aim][5];
					teachThree[t3m+1][0] = sortedMaleBehavior[bim][0];
					teachThree[t3m+1][1] = sortedMaleBehavior[bim][1];
					teachThree[t3m+1][2] = sortedMaleBehavior[bim][2];
					teachThree[t3m+1][3] = sortedMaleBehavior[bim][3];
					teachThree[t3m+1][4] = sortedMaleBehavior[bim][4];
					teachThree[t3m+1][5] = sortedMaleBehavior[bim][5];
					aim++;
					bim++;
					i++;
				}
				else if(addAcademic == true && addCommunication == true && addBehavior == false) {
					teachThree[t3m][0] = sortedMaleCommunication[cim][0];
					teachThree[t3m][1] = sortedMaleCommunication[cim][1];
					teachThree[t3m][2] = sortedMaleCommunication[cim][2];
					teachThree[t3m][3] = sortedMaleCommunication[cim][3];
					teachThree[t3m][4] = sortedMaleCommunication[cim][4];
					teachThree[t3m][5] = sortedMaleCommunication[cim][5];
					teachThree[t3m+1][0] = sortedMaleAcademic[aim][0];
					teachThree[t3m+1][1] = sortedMaleAcademic[aim][1];
					teachThree[t3m+1][2] = sortedMaleAcademic[aim][2];
					teachThree[t3m+1][3] = sortedMaleAcademic[aim][3];
					teachThree[t3m+1][4] = sortedMaleAcademic[aim][4];
					teachThree[t3m+1][5] = sortedMaleAcademic[aim][5];
					cim++;
					aim++;
					i++;
				}
				else if(addAcademic == false && addCommunication == false && addBehavior == false) {
					// go to next teach without incrementing next best 3 students if nobody fits in this class
					i++;
				}  					
				// finding new t3m value for the index once its this teachs turn agaimn
				if(addAcademic == true) {
					t3m++;
				}
				if(addBehavior == true) {
					t3m++;
				}
				if(addCommunication == true) {
					t3m++;
				}
			}
			// key for teach 4
			if (i % 6 == 2) {
				// checks if student id exists in all other teach classes already before adding to this class
				boolean addAcademic = true;
				boolean addBehavior = true;
				boolean addCommunication = true;
				for(int r=0; r<teachFour.length; r++) {
					if(aim >= sortedMaleBehavior.length -1) {
						aim = sortedMaleBehavior.length -1;
					}
					if(teachOne[r][5] == sortedMaleAcademic[aim][5] || teachTwo[r][5] == sortedMaleAcademic[aim][5] || 
							teachThree[r][5] == sortedMaleAcademic[aim][5] || teachFour[r][5] == sortedMaleAcademic[aim][5]
									|| teachFive[r][5] == sortedMaleAcademic[aim][5] || teachSix[r][5] == sortedMaleAcademic[aim][5]) {
						addAcademic = false; // wont want to add this priority academic student cause it would already be in a class
						if(aim < sortedMaleBehavior.length -1) {
							aim++;
						}
						// checks next avaimlable aim -- we'll do one extra check. and extras will be added at the end
/*						if(teachOne[r][5] == sortedMaleAcademic[aim][5] || teachTwo[r][5] == sortedMaleAcademic[aim][5] || 
								teachThree[r][5] == sortedMaleAcademic[aim][5] || teachFour[r][5] == sortedMaleAcademic[aim][5]
										|| teachFive[r][5] == sortedMaleAcademic[aim][5] || teachSix[r][5] == sortedMaleAcademic[aim][5]) {
							aim++;
							if(teachOne[r][5] == sortedMaleAcademic[aim][5] || teachTwo[r][5] == sortedMaleAcademic[aim][5] || 
									teachThree[r][5] == sortedMaleAcademic[aim][5] || teachFour[r][5] == sortedMaleAcademic[aim][5]
											|| teachFive[r][5] == sortedMaleAcademic[aim][5] || teachSix[r][5] == sortedMaleAcademic[aim][5]) {
								aim++;
							}
							else {
								addAcademic = true;
							}
						}
						else {
							addAcademic = true;
						}*/
					}
					if(cim >= sortedMaleBehavior.length -1) {
						cim = sortedMaleBehavior.length -1;
					}
					if(teachOne[r][5] == sortedMaleCommunication[cim][5] || teachTwo[r][5] == sortedMaleCommunication[cim][5] || 
							teachThree[r][5] == sortedMaleCommunication[cim][5] || teachFour[r][5] == sortedMaleCommunication[cim][5]
									|| teachFive[r][5] == sortedMaleCommunication[cim][5] || teachSix[r][5] == sortedMaleCommunication[cim][5]
											|| sortedMaleAcademic[aim][5] == sortedMaleCommunication[cim][5]) {
						addCommunication = false; // wont want to add this priority communication student cause it would already be in a class
						if(cim < sortedMaleBehavior.length -1) {
							cim++;
						}
					}
					if(bim >= sortedMaleBehavior.length -1) {
						bim = sortedMaleBehavior.length -1;
					}
					if(teachOne[r][5] == sortedMaleBehavior[bim][5] || teachTwo[r][5] == sortedMaleBehavior[bim][5] || 
							teachThree[r][5] == sortedMaleBehavior[bim][5] || teachFour[r][5] == sortedMaleBehavior[bim][5]
									|| teachFive[r][5] == sortedMaleBehavior[bim][5] || teachSix[r][5] == sortedMaleBehavior[bim][5] || 
											sortedMaleAcademic[aim][5] == sortedMaleBehavior[bim][5] || sortedMaleCommunication[cim][5] == sortedMaleBehavior[bim][5]) {
						addBehavior = false; // wont want to add this priority behavior student cause it would already be in a class
						if(bim < sortedMaleBehavior.length -1) {
							bim++;
						}
					}
				}
/*					// checks student about to be added has old teach in common with under 6 other students before adding
					if (teachFour[r][3] == sortedMaleAcademic[aim][3] && addAcademic == true) {
						if(sameClass4 < 7) {
							sameClass4++;
						}
						else {
							addAcademic = false;
						}
					}
					if (teachFour[r][3] == sortedMaleCommunication[cim][3] && addCommunication == true) {
						if(sameClass4 < 7) {
							sameClass4++;
						}
						else {
							addCommunication = false;
						}
					}
					if (teachFour[r][3] == sortedMaleBehavior[bim][3] && addBehavior == true) {
						if(sameClass4 < 7) {
							sameClass4++;
						}
						else {
							addBehavior = false;
						}
					}					
				}*/
				// academic student same class check
				if(addAcademic == true) {
					if(sortedMaleAcademic[aim][3] == 1) {
						sameClass4_1++;
						if(sameClass4_1 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
					if(sortedMaleAcademic[aim][3] == 2) {
						sameClass4_2++;
						if(sameClass4_2 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
					if(sortedMaleAcademic[aim][3] == 3) {
						sameClass4_3++;
						if(sameClass4_3 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
					if(sortedMaleAcademic[aim][3] == 4) {
						sameClass4_4++;
						if(sameClass4_4 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
					if(sortedMaleAcademic[aim][3] == 5) {
						sameClass4_5++;
						if(sameClass4_5 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
					if(sortedMaleAcademic[aim][3] == 6) {
						sameClass4_6++;
						if(sameClass4_6 > 6) {
							addAcademic = false; // or try to get next ai
						}
					}
				}
				// communication student same class check
				if(addCommunication == true) {
					if(sortedMaleCommunication[cim][3] == 1) {
						sameClass4_1++;
						if(sameClass4_1 > 6) {
							addCommunication = false; // or try to get next ai
						}
					}
					if(sortedMaleCommunication[cim][3] == 2) {
						sameClass4_2++;
						if(sameClass4_2 > 6) {
							addCommunication = false; // or try to get next ai
						}
					}
					if(sortedMaleCommunication[cim][3] == 3) {
						sameClass4_3++;
						if(sameClass4_3 > 6) {
							addCommunication = false; // or try to get next ai
						}
					}
					if(sortedMaleCommunication[cim][3] == 4) {
						sameClass4_4++;
						if(sameClass4_4 > 6) {
							addCommunication = false; // or try to get next ai
						}
					}
					if(sortedMaleCommunication[cim][3] == 5) {
						sameClass4_5++;
						if(sameClass4_5 > 6) {
							addCommunication = false; // or try to get next ai
						}
					}
					if(sortedMaleCommunication[cim][3] == 6) {
						sameClass4_6++;
						if(sameClass4_6 > 6) {
							addCommunication = false; // or try to get next ai
						}
					}
				}
				// behavior student same class check
				if(addBehavior == true) {
					if(sortedMaleBehavior[bim][3] == 1) {
						sameClass4_1++;
						if(sameClass4_1 > 6) {
							addBehavior = false; // or try to get next bim
						}
					}
					if(sortedMaleBehavior[bim][3] == 2) {
						sameClass4_2++;
						if(sameClass4_2 > 6) {
							addBehavior = false; // or try to get next bim
						}
					}
					if(sortedMaleBehavior[bim][3] == 3) {
						sameClass4_3++;
						if(sameClass4_3 > 6) {
							addBehavior = false; // or try to get next bim
						}
					}
					if(sortedMaleBehavior[bim][3] == 4) {
						sameClass4_4++;
						if(sameClass4_4 > 6) {
							addBehavior = false; // or try to get next bim
						}
					}
					if(sortedMaleBehavior[bim][3] == 5) {
						sameClass4_5++;
						if(sameClass4_5 > 6) {
							addBehavior = false; // or try to get next bim
						}
					}
					if(sortedMaleBehavior[bim][3] == 6) {
						sameClass4_6++;
						if(sameClass4_6 > 6) {
							addBehavior = false; // or try to get next bim
						}
					}
				}
	
				if(addAcademic == true && addCommunication == true && addBehavior == true) {
					teachFour[t4m][0] = sortedMaleAcademic[aim][0];
					teachFour[t4m][1] = sortedMaleAcademic[aim][1];
					teachFour[t4m][2] = sortedMaleAcademic[aim][2];
					teachFour[t4m][3] = sortedMaleAcademic[aim][3];
					teachFour[t4m][4] = sortedMaleAcademic[aim][4];
					teachFour[t4m][5] = sortedMaleAcademic[aim][5];
					teachFour[t4m+1][0] = sortedMaleCommunication[cim][0];
					teachFour[t4m+1][1] = sortedMaleCommunication[cim][1];
					teachFour[t4m+1][2] = sortedMaleCommunication[cim][2];
					teachFour[t4m+1][3] = sortedMaleCommunication[cim][3];
					teachFour[t4m+1][4] = sortedMaleCommunication[cim][4];
					teachFour[t4m+1][5] = sortedMaleCommunication[cim][5];
					teachFour[t4m+2][0] = sortedMaleBehavior[bim][0];
					teachFour[t4m+2][1] = sortedMaleBehavior[bim][1];
					teachFour[t4m+2][2] = sortedMaleBehavior[bim][2];
					teachFour[t4m+2][3] = sortedMaleBehavior[bim][3];
					teachFour[t4m+2][4] = sortedMaleBehavior[bim][4];
					teachFour[t4m+2][5] = sortedMaleBehavior[bim][5];
					aim++;
					bim++;
					cim++;
					i++;
				}
				else if(addAcademic == false && addCommunication == false && addBehavior == true) {
					teachFour[t4m][0] = sortedMaleBehavior[bim][0];
					teachFour[t4m][1] = sortedMaleBehavior[bim][1];
					teachFour[t4m][2] = sortedMaleBehavior[bim][2];
					teachFour[t4m][3] = sortedMaleBehavior[bim][3];
					teachFour[t4m][4] = sortedMaleBehavior[bim][4];
					teachFour[t4m][5] = sortedMaleBehavior[bim][5];
					bim++;
					i++;
				}
				else if(addAcademic == false && addCommunication == true && addBehavior == false) {
					teachFour[t4m][0] = sortedMaleCommunication[cim][0];
					teachFour[t4m][1] = sortedMaleCommunication[cim][1];
					teachFour[t4m][2] = sortedMaleCommunication[cim][2];
					teachFour[t4m][3] = sortedMaleCommunication[cim][3];
					teachFour[t4m][4] = sortedMaleCommunication[cim][4];
					teachFour[t4m][5] = sortedMaleCommunication[cim][5];
					cim++;
					i++;
				}
				else if(addAcademic == true && addCommunication == false && addBehavior == false) {
					teachFour[t4m][0] = sortedMaleAcademic[aim][0];
					teachFour[t4m][1] = sortedMaleAcademic[aim][1];
					teachFour[t4m][2] = sortedMaleAcademic[aim][2];
					teachFour[t4m][3] = sortedMaleAcademic[aim][3];
					teachFour[t4m][4] = sortedMaleAcademic[aim][4];
					teachFour[t4m][5] = sortedMaleAcademic[aim][5];
					aim++;
					i++;
				}
				else if(addAcademic == false && addCommunication == true && addBehavior == true) {
					teachFour[t4m][0] = sortedMaleCommunication[cim][0];
					teachFour[t4m][1] = sortedMaleCommunication[cim][1];
					teachFour[t4m][2] = sortedMaleCommunication[cim][2];
					teachFour[t4m][3] = sortedMaleCommunication[cim][3];
					teachFour[t4m][4] = sortedMaleCommunication[cim][4];
					teachFour[t4m][5] = sortedMaleCommunication[cim][5];
					teachFour[t4m+1][0] = sortedMaleBehavior[bim][0];
					teachFour[t4m+1][1] = sortedMaleBehavior[bim][1];
					teachFour[t4m+1][2] = sortedMaleBehavior[bim][2];
					teachFour[t4m+1][3] = sortedMaleBehavior[bim][3];
					teachFour[t4m+1][4] = sortedMaleBehavior[bim][4];
					teachFour[t4m+1][5] = sortedMaleBehavior[bim][5];
					cim++;
					bim++;
					i++;
				}
				else if(addAcademic == true && addCommunication == false && addBehavior == true) {
					teachFour[t4m][0] = sortedMaleAcademic[aim][0];
					teachFour[t4m][1] = sortedMaleAcademic[aim][1];
					teachFour[t4m][2] = sortedMaleAcademic[aim][2];
					teachFour[t4m][3] = sortedMaleAcademic[aim][3];
					teachFour[t4m][4] = sortedMaleAcademic[aim][4];
					teachFour[t4m][5] = sortedMaleAcademic[aim][5];
					teachFour[t4m+1][0] = sortedMaleBehavior[bim][0];
					teachFour[t4m+1][1] = sortedMaleBehavior[bim][1];
					teachFour[t4m+1][2] = sortedMaleBehavior[bim][2];
					teachFour[t4m+1][3] = sortedMaleBehavior[bim][3];
					teachFour[t4m+1][4] = sortedMaleBehavior[bim][4];
					teachFour[t4m+1][5] = sortedMaleBehavior[bim][5];
					aim++;
					bim++;
					i++;
				}
				else if(addAcademic == true && addCommunication == true && addBehavior == false) {
					teachFour[t4m][0] = sortedMaleCommunication[cim][0];
					teachFour[t4m][1] = sortedMaleCommunication[cim][1];
					teachFour[t4m][2] = sortedMaleCommunication[cim][2];
					teachFour[t4m][3] = sortedMaleCommunication[cim][3];
					teachFour[t4m][4] = sortedMaleCommunication[cim][4];
					teachFour[t4m][5] = sortedMaleCommunication[cim][5];
					teachFour[t4m+1][0] = sortedMaleAcademic[aim][0];
					teachFour[t4m+1][1] = sortedMaleAcademic[aim][1];
					teachFour[t4m+1][2] = sortedMaleAcademic[aim][2];
					teachFour[t4m+1][3] = sortedMaleAcademic[aim][3];
					teachFour[t4m+1][4] = sortedMaleAcademic[aim][4];
					teachFour[t4m+1][5] = sortedMaleAcademic[aim][5];
					cim++;
					aim++;
					i++;
				}
				else if(addAcademic == false && addCommunication == false && addBehavior == false) {
					// go to next teach without incrementing next best 3 students if nobody fits in this class
					i++;
				}  					
				// finding new t4m value for the index once its this teachs turn agaimn
				if(addAcademic == true) {
					t4m++;
				}
				if(addBehavior == true) {
					t4m++;
				}
				if(addCommunication == true) {
					t4m++;
				}
			}
			// key for teach 5
			if (i % 6 == 1) {
				// checks if student id exists in all other teach classes already before adding to this class
				boolean addAcademic = true;
				boolean addBehavior = true;
				boolean addCommunication = true;
				for(int r=0; r<teachFive.length; r++) {
					if(aim >= sortedMaleBehavior.length -1) {
						aim = sortedMaleBehavior.length -1;
					}
					if(teachOne[r][5] == sortedMaleAcademic[aim][5] || teachTwo[r][5] == sortedMaleAcademic[aim][5] || 
							teachThree[r][5] == sortedMaleAcademic[aim][5] || teachFour[r][5] == sortedMaleAcademic[aim][5]
									|| teachFive[r][5] == sortedMaleAcademic[aim][5] || teachSix[r][5] == sortedMaleAcademic[aim][5]) {
						addAcademic = false; // wont want to add this priority academic student cause it would already be in a class
						if(aim < sortedMaleBehavior.length -1) {
							aim++;
						}
						// checks next avaimlable aim -- we'll do one extra check. and extras will be added at the end
/*						if(teachOne[r][5] == sortedMaleAcademic[aim][5] || teachTwo[r][5] == sortedMaleAcademic[aim][5] || 
								teachThree[r][5] == sortedMaleAcademic[aim][5] || teachFour[r][5] == sortedMaleAcademic[aim][5]
										|| teachFive[r][5] == sortedMaleAcademic[aim][5] || teachSix[r][5] == sortedMaleAcademic[aim][5]) {
							aim++;
							if(teachOne[r][5] == sortedMaleAcademic[aim][5] || teachTwo[r][5] == sortedMaleAcademic[aim][5] || 
									teachThree[r][5] == sortedMaleAcademic[aim][5] || teachFour[r][5] == sortedMaleAcademic[aim][5]
											|| teachFive[r][5] == sortedMaleAcademic[aim][5] || teachSix[r][5] == sortedMaleAcademic[aim][5]) {
								aim++;
							}
							else {
								addAcademic = true;
							}
						}
						else {
							addAcademic = true;
						}*/
					}
					if(cim >= sortedMaleBehavior.length -1) {
						cim = sortedMaleBehavior.length -1;
					}
					if(teachOne[r][5] == sortedMaleCommunication[cim][5] || teachTwo[r][5] == sortedMaleCommunication[cim][5] || 
							teachThree[r][5] == sortedMaleCommunication[cim][5] || teachFour[r][5] == sortedMaleCommunication[cim][5]
									|| teachFive[r][5] == sortedMaleCommunication[cim][5] || teachSix[r][5] == sortedMaleCommunication[cim][5]
											|| sortedMaleAcademic[aim][5] == sortedMaleCommunication[cim][5]) {
						addCommunication = false; // wont want to add this priority communication student cause it would already be in a class
						if(cim < sortedMaleBehavior.length -1) {
							cim++;
						}
					}
					if(bim >= sortedMaleBehavior.length -1) {
						bim = sortedMaleBehavior.length -1;
					}
					if(teachOne[r][5] == sortedMaleBehavior[bim][5] || teachTwo[r][5] == sortedMaleBehavior[bim][5] || 
							teachThree[r][5] == sortedMaleBehavior[bim][5] || teachFour[r][5] == sortedMaleBehavior[bim][5]
									|| teachFive[r][5] == sortedMaleBehavior[bim][5] || teachSix[r][5] == sortedMaleBehavior[bim][5] || 
											sortedMaleAcademic[aim][5] == sortedMaleBehavior[bim][5] || sortedMaleCommunication[cim][5] == sortedMaleBehavior[bim][5]) {
						addBehavior = false; // wont want to add this priority behavior student cause it would already be in a class
						if(bim < sortedMaleBehavior.length -1) {
							bim++;
						}
					}
				}
				// academic student same class check
				if(addAcademic == true) {
					if(sortedMaleAcademic[aim][3] == 1) {
						sameClass5_1++;
						if(sameClass5_1 > 6) {
							addAcademic = false; // or try to get next aim
						}
					}
					if(sortedMaleAcademic[aim][3] == 2) {
						sameClass5_2++;
						if(sameClass5_2 > 6) {
							addAcademic = false; // or try to get next aim
						}
					}
					if(sortedMaleAcademic[aim][3] == 3) {
						sameClass5_3++;
						if(sameClass5_3 > 6) {
							addAcademic = false; // or try to get next aim
						}
					}
					if(sortedMaleAcademic[aim][3] == 4) {
						sameClass5_4++;
						if(sameClass5_4 > 6) {
							addAcademic = false; // or try to get next aim
						}
					}
					if(sortedMaleAcademic[aim][3] == 5) {
						sameClass5_5++;
						if(sameClass5_5 > 6) {
							addAcademic = false; // or try to get next aim
						}
					}
					if(sortedMaleAcademic[aim][3] == 6) {
						sameClass5_6++;
						if(sameClass5_6 > 6) {
							addAcademic = false; // or try to get next aim
						}
					}
				}
				// communication student same class check
				if(addCommunication == true) {
					if(sortedMaleCommunication[cim][3] == 1) {
						sameClass5_1++;
						if(sameClass5_1 > 6) {
							addCommunication = false; // or try to get next cim
						}
					}
					if(sortedMaleCommunication[cim][3] == 2) {
						sameClass5_2++;
						if(sameClass5_2 > 6) {
							addCommunication = false; // or try to get next cim
						}
					}
					if(sortedMaleCommunication[cim][3] == 3) {
						sameClass5_3++;
						if(sameClass5_3 > 6) {
							addCommunication = false; // or try to get next cim
						}
					}
					if(sortedMaleCommunication[cim][3] == 4) {
						sameClass5_4++;
						if(sameClass5_4 > 6) {
							addCommunication = false; // or try to get next cim
						}
					}
					if(sortedMaleCommunication[cim][3] == 5) {
						sameClass5_5++;
						if(sameClass5_5 > 6) {
							addCommunication = false; // or try to get next cim
						}
					}
					if(sortedMaleCommunication[cim][3] == 6) {
						sameClass5_6++;
						if(sameClass5_6 > 6) {
							addCommunication = false; // or try to get next cim
						}
					}
				}
				// behavior student same class check
				if(addBehavior == true) {
					if(sortedMaleBehavior[bim][3] == 1) {
						sameClass5_1++;
						if(sameClass5_1 > 6) {
							addBehavior = false; // or try to get next bim
						}
					}
					if(sortedMaleBehavior[bim][3] == 2) {
						sameClass5_2++;
						if(sameClass5_2 > 6) {
							addBehavior = false; // or try to get next bim
						}
					}
					if(sortedMaleBehavior[bim][3] == 3) {
						sameClass5_3++;
						if(sameClass5_3 > 6) {
							addBehavior = false; // or try to get next bim
						}
					}
					if(sortedMaleBehavior[bim][3] == 4) {
						sameClass5_4++;
						if(sameClass5_4 > 6) {
							addBehavior = false; // or try to get next bim
						}
					}
					if(sortedMaleBehavior[bim][3] == 5) {
						sameClass5_5++;
						if(sameClass5_5 > 6) {
							addBehavior = false; // or try to get next bim
						}
					}
					if(sortedMaleBehavior[bim][3] == 6) {
						sameClass5_6++;
						if(sameClass5_6 > 6) {
							addBehavior = false; // or try to get next bim
						}
					}
				}
	
				if(addAcademic == true && addCommunication == true && addBehavior == true) {
					teachFive[t5m][0] = sortedMaleAcademic[aim][0];
					teachFive[t5m][1] = sortedMaleAcademic[aim][1];
					teachFive[t5m][2] = sortedMaleAcademic[aim][2];
					teachFive[t5m][3] = sortedMaleAcademic[aim][3];
					teachFive[t5m][4] = sortedMaleAcademic[aim][4];
					teachFive[t5m][5] = sortedMaleAcademic[aim][5];
					teachFive[t5m+1][0] = sortedMaleCommunication[cim][0];
					teachFive[t5m+1][1] = sortedMaleCommunication[cim][1];
					teachFive[t5m+1][2] = sortedMaleCommunication[cim][2];
					teachFive[t5m+1][3] = sortedMaleCommunication[cim][3];
					teachFive[t5m+1][4] = sortedMaleCommunication[cim][4];
					teachFive[t5m+1][5] = sortedMaleCommunication[cim][5];
					teachFive[t5m+2][0] = sortedMaleBehavior[bim][0];
					teachFive[t5m+2][1] = sortedMaleBehavior[bim][1];
					teachFive[t5m+2][2] = sortedMaleBehavior[bim][2];
					teachFive[t5m+2][3] = sortedMaleBehavior[bim][3];
					teachFive[t5m+2][4] = sortedMaleBehavior[bim][4];
					teachFive[t5m+2][5] = sortedMaleBehavior[bim][5];
					aim++;
					bim++;
					cim++;
					i++;
				}
				else if(addAcademic == false && addCommunication == false && addBehavior == true) {
					teachFive[t5m][0] = sortedMaleBehavior[bim][0];
					teachFive[t5m][1] = sortedMaleBehavior[bim][1];
					teachFive[t5m][2] = sortedMaleBehavior[bim][2];
					teachFive[t5m][3] = sortedMaleBehavior[bim][3];
					teachFive[t5m][4] = sortedMaleBehavior[bim][4];
					teachFive[t5m][5] = sortedMaleBehavior[bim][5];
					bim++;
					i++;
				}
				else if(addAcademic == false && addCommunication == true && addBehavior == false) {
					teachFive[t5m][0] = sortedMaleCommunication[cim][0];
					teachFive[t5m][1] = sortedMaleCommunication[cim][1];
					teachFive[t5m][2] = sortedMaleCommunication[cim][2];
					teachFive[t5m][3] = sortedMaleCommunication[cim][3];
					teachFive[t5m][4] = sortedMaleCommunication[cim][4];
					teachFive[t5m][5] = sortedMaleCommunication[cim][5];
					cim++;
					i++;
				}
				else if(addAcademic == true && addCommunication == false && addBehavior == false) {
					teachFive[t5m][0] = sortedMaleAcademic[aim][0];
					teachFive[t5m][1] = sortedMaleAcademic[aim][1];
					teachFive[t5m][2] = sortedMaleAcademic[aim][2];
					teachFive[t5m][3] = sortedMaleAcademic[aim][3];
					teachFive[t5m][4] = sortedMaleAcademic[aim][4];
					teachFive[t5m][5] = sortedMaleAcademic[aim][5];
					aim++;
					i++;
				}
				else if(addAcademic == false && addCommunication == true && addBehavior == true) {
					teachFive[t5m][0] = sortedMaleCommunication[cim][0];
					teachFive[t5m][1] = sortedMaleCommunication[cim][1];
					teachFive[t5m][2] = sortedMaleCommunication[cim][2];
					teachFive[t5m][3] = sortedMaleCommunication[cim][3];
					teachFive[t5m][4] = sortedMaleCommunication[cim][4];
					teachFive[t5m][5] = sortedMaleCommunication[cim][5];
					teachFive[t5m+1][0] = sortedMaleBehavior[bim][0];
					teachFive[t5m+1][1] = sortedMaleBehavior[bim][1];
					teachFive[t5m+1][2] = sortedMaleBehavior[bim][2];
					teachFive[t5m+1][3] = sortedMaleBehavior[bim][3];
					teachFive[t5m+1][4] = sortedMaleBehavior[bim][4];
					teachFive[t5m+1][5] = sortedMaleBehavior[bim][5];
					cim++;
					bim++;
					i++;
				}
				else if(addAcademic == true && addCommunication == false && addBehavior == true) {
					teachFive[t5m][0] = sortedMaleAcademic[aim][0];
					teachFive[t5m][1] = sortedMaleAcademic[aim][1];
					teachFive[t5m][2] = sortedMaleAcademic[aim][2];
					teachFive[t5m][3] = sortedMaleAcademic[aim][3];
					teachFive[t5m][4] = sortedMaleAcademic[aim][4];
					teachFive[t5m][5] = sortedMaleAcademic[aim][5];
					teachFive[t5m+1][0] = sortedMaleBehavior[bim][0];
					teachFive[t5m+1][1] = sortedMaleBehavior[bim][1];
					teachFive[t5m+1][2] = sortedMaleBehavior[bim][2];
					teachFive[t5m+1][3] = sortedMaleBehavior[bim][3];
					teachFive[t5m+1][4] = sortedMaleBehavior[bim][4];
					teachFive[t5m+1][5] = sortedMaleBehavior[bim][5];
					aim++;
					bim++;
					i++;
				}
				else if(addAcademic == true && addCommunication == true && addBehavior == false) {
					teachFive[t5m][0] = sortedMaleCommunication[cim][0];
					teachFive[t5m][1] = sortedMaleCommunication[cim][1];
					teachFive[t5m][2] = sortedMaleCommunication[cim][2];
					teachFive[t5m][3] = sortedMaleCommunication[cim][3];
					teachFive[t5m][4] = sortedMaleCommunication[cim][4];
					teachFive[t5m][5] = sortedMaleCommunication[cim][5];
					teachFive[t5m+1][0] = sortedMaleAcademic[aim][0];
					teachFive[t5m+1][1] = sortedMaleAcademic[aim][1];
					teachFive[t5m+1][2] = sortedMaleAcademic[aim][2];
					teachFive[t5m+1][3] = sortedMaleAcademic[aim][3];
					teachFive[t5m+1][4] = sortedMaleAcademic[aim][4];
					teachFive[t5m+1][5] = sortedMaleAcademic[aim][5];
					cim++;
					aim++;
					i++;
				}
				else if(addAcademic == false && addCommunication == false && addBehavior == false) {
					// go to next teach without incrementing next best 3 students if nobody fits in this class
					i++;
				}  					
				// finding new t5m value for the index once its this teachs turn agaimn
				if(addAcademic == true) {
					t5m++;
				}
				if(addBehavior == true) {
					t5m++;
				}
				if(addCommunication == true) {
					t5m++;
				}
			}		
			// key for teach 6
			if (i % 6 == 0) {
				// checks if student id exists in any other teach classes already before adding to this class
				boolean addAcademic = true;
				boolean addBehavior = true;
				boolean addCommunication = true;
				for(int r=0; r<teachSix.length; r++) {
					if(aim >= sortedMaleBehavior.length -1) {
						aim = sortedMaleBehavior.length -1;
					}
					if(teachOne[r][5] == sortedMaleAcademic[aim][5] || teachTwo[r][5] == sortedMaleAcademic[aim][5] || 
							teachThree[r][5] == sortedMaleAcademic[aim][5] || teachFour[r][5] == sortedMaleAcademic[aim][5]
									|| teachFive[r][5] == sortedMaleAcademic[aim][5] || teachSix[r][5] == sortedMaleAcademic[aim][5]) {
						addAcademic = false; // wont want to add this priority academic student cause it would already be in a class
						if(aim < sortedMaleBehavior.length -1) {
							aim++;
						}
						// checks next avaimlable aim -- we'll do one extra check. and extras will be added at the end
/*						if(teachOne[r][5] == sortedMaleAcademic[aim][5] || teachTwo[r][5] == sortedMaleAcademic[aim][5] || 
								teachThree[r][5] == sortedMaleAcademic[aim][5] || teachFour[r][5] == sortedMaleAcademic[aim][5]
										|| teachFive[r][5] == sortedMaleAcademic[aim][5] || teachSix[r][5] == sortedMaleAcademic[aim][5]) {
							aim++;
							if(teachOne[r][5] == sortedMaleAcademic[aim][5] || teachTwo[r][5] == sortedMaleAcademic[aim][5] || 
									teachThree[r][5] == sortedMaleAcademic[aim][5] || teachFour[r][5] == sortedMaleAcademic[aim][5]
											|| teachFive[r][5] == sortedMaleAcademic[aim][5] || teachSix[r][5] == sortedMaleAcademic[aim][5]) {
								aim++;
							}
							else {
								addAcademic = true;
							}
						}
						else {
							addAcademic = true;
						}*/
					}
					if(cim >= sortedMaleBehavior.length -1) {
						cim = sortedMaleBehavior.length -1;
					}
					if(teachOne[r][5] == sortedMaleCommunication[cim][5] || teachTwo[r][5] == sortedMaleCommunication[cim][5] || 
							teachThree[r][5] == sortedMaleCommunication[cim][5] || teachFour[r][5] == sortedMaleCommunication[cim][5]
									|| teachFive[r][5] == sortedMaleCommunication[cim][5] || teachSix[r][5] == sortedMaleCommunication[cim][5]
											|| sortedMaleAcademic[aim][5] == sortedMaleCommunication[cim][5]) {
						addCommunication = false; // wont want to add this priority communication student cause it would already be in a class
						if(cim < sortedMaleBehavior.length -1) {
							cim++;
						}
					}
					if(bim >= sortedMaleBehavior.length -1) {
						bim = sortedMaleBehavior.length -1;
					}
					if(teachOne[r][5] == sortedMaleBehavior[bim][5] || teachTwo[r][5] == sortedMaleBehavior[bim][5] || 
							teachThree[r][5] == sortedMaleBehavior[bim][5] || teachFour[r][5] == sortedMaleBehavior[bim][5]
									|| teachFive[r][5] == sortedMaleBehavior[bim][5] || teachSix[r][5] == sortedMaleBehavior[bim][5] || 
											sortedMaleAcademic[aim][5] == sortedMaleBehavior[bim][5] || sortedMaleCommunication[cim][5] == sortedMaleBehavior[bim][5]) {
						addBehavior = false; // wont want to add this priority behavior student cause it would already be in a class
						if(bim < sortedMaleBehavior.length -1) {
							bim++;
						}
					}
				}
				// academic student same class check
				if(addAcademic == true) {
					if(sortedMaleAcademic[aim][3] == 1) {
						sameClass6_1++;
						if(sameClass6_1 > 6) {
							addAcademic = false; // or try to get next aim
						}
					}
					if(sortedMaleAcademic[aim][3] == 2) {
						sameClass6_2++;
						if(sameClass6_2 > 6) {
							addAcademic = false; // or try to get next aim
						}
					}
					if(sortedMaleAcademic[aim][3] == 3) {
						sameClass6_3++;
						if(sameClass6_3 > 6) {
							addAcademic = false; // or try to get next aim
						}
					}
					if(sortedMaleAcademic[aim][3] == 4) {
						sameClass6_4++;
						if(sameClass6_4 > 6) {
							addAcademic = false; // or try to get next aim
						}
					}
					if(sortedMaleAcademic[aim][3] == 5) {
						sameClass6_5++;
						if(sameClass6_5 > 6) {
							addAcademic = false; // or try to get next aim
						}
					}
					if(sortedMaleAcademic[aim][3] == 6) {
						sameClass6_6++;
						if(sameClass6_6 > 6) {
							addAcademic = false; // or try to get next aim
						}
					}
				}
				// communication student same class check
				if(addCommunication == true) {
					if(sortedMaleCommunication[cim][3] == 1) {
						sameClass6_1++;
						if(sameClass6_1 > 6) {
							addCommunication = false; // or try to get next cim
						}
					}
					if(sortedMaleCommunication[cim][3] == 2) {
						sameClass6_2++;
						if(sameClass6_2 > 6) {
							addCommunication = false; // or try to get next cim
						}
					}
					if(sortedMaleCommunication[cim][3] == 3) {
						sameClass6_3++;
						if(sameClass6_3 > 6) {
							addCommunication = false; // or try to get next cim
						}
					}
					if(sortedMaleCommunication[cim][3] == 4) {
						sameClass6_4++;
						if(sameClass6_4 > 6) {
							addCommunication = false; // or try to get next cim
						}
					}
					if(sortedMaleCommunication[cim][3] == 5) {
						sameClass6_5++;
						if(sameClass6_5 > 6) {
							addCommunication = false; // or try to get next cim
						}
					}
					if(sortedMaleCommunication[cim][3] == 6) {
						sameClass6_6++;
						if(sameClass6_6 > 6) {
							addCommunication = false; // or try to get next cim
						}
					}
				}
				if(addBehavior == true) {
					if(sortedMaleBehavior[bim][3] == 1) {
						sameClass6_1++;
						if(sameClass6_1 > 6) {
							addBehavior = false; // or try to get next bim
						}
					}
					if(sortedMaleBehavior[bim][3] == 2) {
						sameClass6_2++;
						if(sameClass6_2 > 6) {
							addBehavior = false; // or try to get next bim
						}
					}
					if(sortedMaleBehavior[bim][3] == 3) {
						sameClass6_3++;
						if(sameClass6_3 > 6) {
							addBehavior = false; // or try to get next bim
						}
					}
					if(sortedMaleBehavior[bim][3] == 4) {
						sameClass6_4++;
						if(sameClass6_4 > 6) {
							addBehavior = false; // or try to get next bim
						}
					}
					if(sortedMaleBehavior[bim][3] == 5) {
						sameClass6_5++;
						if(sameClass6_5 > 6) {
							addBehavior = false; // or try to get next bim
						}
					}
					if(sortedMaleBehavior[bim][3] == 6) {
						sameClass6_6++;
						if(sameClass6_6 > 6) {
							addBehavior = false; // or try to get next bim
						}
					}
				}

				if(addAcademic == true && addCommunication == true && addBehavior == true) {
					teachSix[t6m][0] = sortedMaleAcademic[aim][0];
					teachSix[t6m][1] = sortedMaleAcademic[aim][1];
					teachSix[t6m][2] = sortedMaleAcademic[aim][2];
					teachSix[t6m][3] = sortedMaleAcademic[aim][3];
					teachSix[t6m][4] = sortedMaleAcademic[aim][4];
					teachSix[t6m][5] = sortedMaleAcademic[aim][5];
					teachSix[t6m+1][0] = sortedMaleCommunication[cim][0];
					teachSix[t6m+1][1] = sortedMaleCommunication[cim][1];
					teachSix[t6m+1][2] = sortedMaleCommunication[cim][2];
					teachSix[t6m+1][3] = sortedMaleCommunication[cim][3];
					teachSix[t6m+1][4] = sortedMaleCommunication[cim][4];
					teachSix[t6m+1][5] = sortedMaleCommunication[cim][5];
					teachSix[t6m+2][0] = sortedMaleBehavior[bim][0];
					teachSix[t6m+2][1] = sortedMaleBehavior[bim][1];
					teachSix[t6m+2][2] = sortedMaleBehavior[bim][2];
					teachSix[t6m+2][3] = sortedMaleBehavior[bim][3];
					teachSix[t6m+2][4] = sortedMaleBehavior[bim][4];
					teachSix[t6m+2][5] = sortedMaleBehavior[bim][5];
					aim++;
					bim++;
					cim++;
					i++;
				}
				else if(addAcademic == false && addCommunication == false && addBehavior == true) {
					teachSix[t6m][0] = sortedMaleBehavior[bim][0];
					teachSix[t6m][1] = sortedMaleBehavior[bim][1];
					teachSix[t6m][2] = sortedMaleBehavior[bim][2];
					teachSix[t6m][3] = sortedMaleBehavior[bim][3];
					teachSix[t6m][4] = sortedMaleBehavior[bim][4];
					teachSix[t6m][5] = sortedMaleBehavior[bim][5];
					bim++;
					i++;
				}
				else if(addAcademic == false && addCommunication == true && addBehavior == false) {
					teachSix[t6m][0] = sortedMaleCommunication[cim][0];
					teachSix[t6m][1] = sortedMaleCommunication[cim][1];
					teachSix[t6m][2] = sortedMaleCommunication[cim][2];
					teachSix[t6m][3] = sortedMaleCommunication[cim][3];
					teachSix[t6m][4] = sortedMaleCommunication[cim][4];
					teachSix[t6m][5] = sortedMaleCommunication[cim][5];
					cim++;
					i++;
				}
				else if(addAcademic == true && addCommunication == false && addBehavior == false) {
					teachSix[t6m][0] = sortedMaleAcademic[aim][0];
					teachSix[t6m][1] = sortedMaleAcademic[aim][1];
					teachSix[t6m][2] = sortedMaleAcademic[aim][2];
					teachSix[t6m][3] = sortedMaleAcademic[aim][3];
					teachSix[t6m][4] = sortedMaleAcademic[aim][4];
					teachSix[t6m][5] = sortedMaleAcademic[aim][5];
					aim++;
					i++;
				}
				else if(addAcademic == false && addCommunication == true && addBehavior == true) {
					teachSix[t6m][0] = sortedMaleCommunication[cim][0];
					teachSix[t6m][1] = sortedMaleCommunication[cim][1];
					teachSix[t6m][2] = sortedMaleCommunication[cim][2];
					teachSix[t6m][3] = sortedMaleCommunication[cim][3];
					teachSix[t6m][4] = sortedMaleCommunication[cim][4];
					teachSix[t6m][5] = sortedMaleCommunication[cim][5];
					teachSix[t6m+1][0] = sortedMaleBehavior[bim][0];
					teachSix[t6m+1][1] = sortedMaleBehavior[bim][1];
					teachSix[t6m+1][2] = sortedMaleBehavior[bim][2];
					teachSix[t6m+1][3] = sortedMaleBehavior[bim][3];
					teachSix[t6m+1][4] = sortedMaleBehavior[bim][4];
					teachSix[t6m+1][5] = sortedMaleBehavior[bim][5];
					cim++;
					bim++;
					i++;
				}
				else if(addAcademic == true && addCommunication == false && addBehavior == true) {
					teachSix[t6m][0] = sortedMaleAcademic[aim][0];
					teachSix[t6m][1] = sortedMaleAcademic[aim][1];
					teachSix[t6m][2] = sortedMaleAcademic[aim][2];
					teachSix[t6m][3] = sortedMaleAcademic[aim][3];
					teachSix[t6m][4] = sortedMaleAcademic[aim][4];
					teachSix[t6m][5] = sortedMaleAcademic[aim][5];
					teachSix[t6m+1][0] = sortedMaleBehavior[bim][0];
					teachSix[t6m+1][1] = sortedMaleBehavior[bim][1];
					teachSix[t6m+1][2] = sortedMaleBehavior[bim][2];
					teachSix[t6m+1][3] = sortedMaleBehavior[bim][3];
					teachSix[t6m+1][4] = sortedMaleBehavior[bim][4];
					teachSix[t6m+1][5] = sortedMaleBehavior[bim][5];
					aim++;
					bim++;
					i++;
				}
				else if(addAcademic == true && addCommunication == true && addBehavior == false) {
					teachSix[t6m][0] = sortedMaleCommunication[cim][0];
					teachSix[t6m][1] = sortedMaleCommunication[cim][1];
					teachSix[t6m][2] = sortedMaleCommunication[cim][2];
					teachSix[t6m][3] = sortedMaleCommunication[cim][3];
					teachSix[t6m][4] = sortedMaleCommunication[cim][4];
					teachSix[t6m][5] = sortedMaleCommunication[cim][5];
					teachSix[t6m+1][0] = sortedMaleAcademic[aim][0];
					teachSix[t6m+1][1] = sortedMaleAcademic[aim][1];
					teachSix[t6m+1][2] = sortedMaleAcademic[aim][2];
					teachSix[t6m+1][3] = sortedMaleAcademic[aim][3];
					teachSix[t6m+1][4] = sortedMaleAcademic[aim][4];
					teachSix[t6m+1][5] = sortedMaleAcademic[aim][5];
					cim++;
					aim++;
					i++;
				}
				else if(addAcademic == false && addCommunication == false && addBehavior == false) {
					// go to next teach without incrementing next best 3 students if nobody fits in this class
					i++;
				}  					
				// finding new t6m value for the index once its this teachs turn again
				if(addAcademic == true) {
					t6m++;
				}
				if(addBehavior == true) {
					t6m++;
				}
				if(addCommunication == true) {
					t6m++;
				}
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		int size = 0;
		if(args.length > 0) {
			size = Integer.valueOf(args[0]);
    //        File file = new File(args[1]);
    //        File nameFile = new File(args[2]);
        //	static File nameFile = new File(studentNames);
            // Work with your 'file' object here
        }
		Integer info[][] = readKids(size);
		ArrayList<String> names = new ArrayList<String>();
		names = readNames(size);
		int girlsNum = getGirlsArraySize(info);
		//System.out.println(girlsNum);
		int boysNum = size - girlsNum;
		Integer girls[][]= findGirls(info, girlsNum, size);
		Integer boys[][] = findBoys(info, boysNum, size);
		int average = (size) / 6; // average will be the floored min value of what each teacher should have
		// each double teacher array will contain student info; for Males
		Integer[][] teacherOne = new Integer[(size/6)+6][6]; // plus six for a big cushion, i is number of students in a class
		Integer[][] teacherTwo = new Integer[(size/6)+6][6]; // j is for each property of information associated with each student
		Integer[][] teacherThree = new Integer[(size/6)+6][6];
		Integer[][] teacherFour = new Integer[(size/6)+6][6];
		Integer[][] teacherFive = new Integer[(size/6)+6][6];
		Integer[][] teacherSix = new Integer[(size/6)+6][6];
		// for teacher male lists
		Integer[][] teachOne = new Integer[(size/6)+6][6]; // plus six for a big cushion, i is number of students in a class
		Integer[][] teachTwo = new Integer[(size/6)+6][6]; // j is for each property of information associated with each student
		Integer[][] teachThree = new Integer[(size/6)+6][6];
		Integer[][] teachFour = new Integer[(size/6)+6][6];
		Integer[][] teachFive = new Integer[(size/6)+6][6];
		Integer[][] teachSix = new Integer[(size/6)+6][6];
		Female(girls, size, teacherOne, teacherTwo, teacherThree, teacherFour, teacherFive, teacherSix, average);
		Male(boys, size, teachOne, teachTwo, teachThree, teachFour, teachFive, teachSix);
		Integer[][] ClasslistOne = Combine(teachOne, teacherOne, t1);
		Integer[][] ClasslistTwo = Combine(teachTwo, teacherTwo, t2);
		Integer[][] ClasslistThree = Combine(teachThree, teacherThree, t3);
		Integer[][] ClasslistFour = Combine(teachFour, teacherFour, t4);
		Integer[][] ClasslistFive = Combine(teachFive, teacherFive, t5);
		Integer[][] ClasslistSix = Combine(teachSix, teacherSix, t6);
	//	System.out.println();
		// area to even out class sizes
		
		// variables to represent number of kids in class
		int classOneSize = GetClassSize(ClasslistOne);
	//	System.out.println(classOneSize);
		int classTwoSize = GetClassSize(ClasslistTwo);
	//	System.out.println(classTwoSize);
		int classThreeSize = GetClassSize(ClasslistThree);
	//	System.out.println(classThreeSize);
		int classFourSize = GetClassSize(ClasslistFour);
	//	System.out.println(classFourSize);
		int classFiveSize = GetClassSize(ClasslistFive);
	//	System.out.println(classFiveSize);
		int classSixSize = GetClassSize(ClasslistSix);
	//	System.out.println(classSixSize);
		
		
//		System.out.println(size); // size (input number) = one more than number of kids
	//	System.out.println(average);
		
		// figure out what to balance .. then call balance function
		// this balance balances classList size
		// balancing boys and girls still needs to be done
		Balance(ClasslistOne, ClasslistTwo, ClasslistThree, ClasslistFour, ClasslistFive, ClasslistSix,
				classOneSize, classTwoSize, classThreeSize, classFourSize, classFiveSize, classSixSize, average);
		
// get rid of nulls at the end, and then print
		// variables to represent number of kids in class
		int FinalclassOneSize = GetClassSize(ClasslistOne);
	//	System.out.println(classOneSize);
		int FinalclassTwoSize = GetClassSize(ClasslistTwo);
	//	System.out.println(classTwoSize);
		int FinalclassThreeSize = GetClassSize(ClasslistThree);
	//	System.out.println(classThreeSize);
		int FinalclassFourSize = GetClassSize(ClasslistFour);
	//	System.out.println(classFourSize);
		int FinalclassFiveSize = GetClassSize(ClasslistFive);
	//	System.out.println(classFiveSize);
		int FinalclassSixSize = GetClassSize(ClasslistSix);
	//	System.out.println(classSixSize);
		
		Integer[][] FinalClassListOne = new Integer[FinalclassOneSize][6]; // plus six for a big cushion, i is number of students in a class
		Integer[][] FinalClassListTwo = new Integer[FinalclassTwoSize][6]; // j is for each property of information associated with each student
		Integer[][] FinalClassListThree = new Integer[FinalclassThreeSize][6];
		Integer[][] FinalClassListFour = new Integer[FinalclassFourSize][6];
		Integer[][] FinalClassListFive = new Integer[FinalclassFiveSize][6];
		Integer[][] FinalClassListSix = new Integer[FinalclassSixSize][6];
		// new arrays without nulls at the end
		for(int i=0; i<FinalClassListOne.length; i++) {
			for(int j=0; j<6; j++) {
				FinalClassListOne[i][j] = ClasslistOne[i][j];
			}		
		}
		for(int i=0; i<FinalClassListTwo.length; i++) {
			for(int j=0; j<6; j++) {
				FinalClassListTwo[i][j] = ClasslistTwo[i][j];
			}		
		}
		for(int i=0; i<FinalClassListThree.length; i++) {
			for(int j=0; j<6; j++) {
				FinalClassListThree[i][j] = ClasslistThree[i][j];
			}		
		}
		for(int i=0; i<FinalClassListFour.length; i++) {
			for(int j=0; j<6; j++) {
				FinalClassListFour[i][j] = ClasslistFour[i][j];
			}		
		}
		for(int i=0; i<FinalClassListFive.length; i++) {
			for(int j=0; j<6; j++) {
				FinalClassListFive[i][j] = ClasslistFive[i][j];
			}		
		}
		for(int i=0; i<FinalClassListSix.length; i++) {
			for(int j=0; j<6; j++) {
				FinalClassListSix[i][j] = ClasslistSix[i][j];
			}		
		}

		int ii = 1 ;
	//	int once = 0;
	//	System.out.println("Teacher 1 ClassList: ");
	//	for(int y=0; y<FinalClassListOne.length; y++) {
		//	for(int t=0; t<6; t++) {
		//		if(ii == 1 && once == 0) {
			//		System.out.print(ii+ ". ");
		//			System.out.print((FinalClassListOne[y][t]) + " ");
		//			once++;
		//			}
	//			else {
		//		System.out.print((FinalClassListOne[y][t]) + " ");
//				System.out.println(names.get(FinalClassListOne[y][5]));
	//			}
	//		}
	//		System.out.println();
	//		ii++;
	//		if(ii <= FinalClassListOne.length) {
	//			System.out.print(ii+ ". ");
	//		}
	//	}
		System.out.println("Teacher 1 ClassList: ");
		for(int y=0; y<FinalClassListOne.length; y++) {
			System.out.println(ii+ ". " + names.get(FinalClassListOne[y][5]));
			ii++;
		}
		System.out.println("");
		ii = 1;
		System.out.println("Teacher 2 ClassList: ");
		for(int y=0; y<FinalClassListTwo.length; y++) {
			System.out.println(ii+ ". " + names.get(FinalClassListTwo[y][5]));
			ii++;
		}
		System.out.println("");
		ii = 1;
		System.out.println("Teacher 3 ClassList: ");
		for(int y=0; y<FinalClassListThree.length; y++) {
			System.out.println(ii+ ". " + names.get(FinalClassListThree[y][5]));
			ii++;
		}
		System.out.println("");
		ii = 1;
		System.out.println("Teacher 4 ClassList: ");
		for(int y=0; y<FinalClassListFour.length; y++) {
			System.out.println(ii+ ". " + names.get(FinalClassListFour[y][5]));
			ii++;
		}
		System.out.println("");
		ii = 1;
		System.out.println("Teacher 5 ClassList: ");
		for(int y=0; y<FinalClassListFive.length; y++) {
			System.out.println(ii+ ". " + names.get(FinalClassListFive[y][5]));
			ii++;
		}
		System.out.println("");
		ii = 1;
		System.out.println("Teacher 6 ClassList: ");
		for(int y=0; y<FinalClassListSix.length; y++) {
			System.out.println(ii+ ". " + names.get(FinalClassListSix[y][5]));
			ii++;
		}	
//		System.out.println();
/*		ii = 1 ;
		once = 0;
		System.out.println("Teacher 2 ClassList: ");
		for(int y=0; y<FinalClassListTwo.length; y++) {
			for(int t=0; t<6; t++) {
				if(ii == 1 && once == 0) {
					System.out.print(ii+ ". ");
					System.out.print((FinalClassListTwo[y][t]) + " ");
					once++;
					}
				else {
				System.out.print((FinalClassListTwo[y][t]) + " ");
				}
			}
			System.out.println();
			ii++;
			if(ii <= FinalClassListTwo.length) {
				System.out.print(ii+ ". ");
			}
		}
		System.out.println();
		ii = 1 ;
		once = 0;
		System.out.println("Teacher 3 ClassList: ");
		for(int y=0; y<FinalClassListThree.length; y++) {
			for(int t=0; t<6; t++) {
				if(ii == 1 && once == 0) {
					System.out.print(ii+ ". ");
					System.out.print((FinalClassListThree[y][t]) + " ");
					once++;
					}
				else {
				System.out.print((FinalClassListThree[y][t]) + " ");
				}
			}
			System.out.println();
			ii++;
			if(ii <= FinalClassListThree.length) {
				System.out.print(ii+ ". ");
			}
		}
		System.out.println();
		ii = 1 ;
	    once = 0;
	    System.out.println("Teacher 4 ClassList: ");
		for(int y=0; y<FinalClassListFour.length; y++) {
			for(int t=0; t<6; t++) {
				if(ii == 1 && once == 0) {
					System.out.print(ii+ ". ");
					System.out.print((FinalClassListFour[y][t]) + " ");
					once++;
					}
				else {
				System.out.print((FinalClassListFour[y][t]) + " ");
				}
			}
			System.out.println();
			ii++;
			if(ii <= FinalClassListFour.length) {
				System.out.print(ii+ ". ");
			}
		}
		System.out.println();
		ii = 1 ;
		once = 0;
		System.out.println("Teacher 5 ClassList: ");
		for(int y=0; y<FinalClassListFive.length; y++) {
			for(int t=0; t<6; t++) {
				if(ii == 1 && once == 0) {
					System.out.print(ii+ ". ");
					System.out.print((FinalClassListFive[y][t]) + " ");
					once++;
					}
				else {
				System.out.print((FinalClassListFive[y][t]) + " ");
				}
			}
			System.out.println();
			ii++;
			if(ii <= FinalClassListFive.length) {
				System.out.print(ii+ ". ");
			}
		}
		System.out.println();
		ii = 1 ;
		once = 0;
		System.out.println("Teacher 6 ClassList: ");
		for(int y=0; y<FinalClassListSix.length; y++) {
			for(int t=0; t<6; t++) {
				if(ii == 1 && once == 0) {
					System.out.print(ii+ ". ");
					System.out.print((FinalClassListSix[y][t]) + " ");
					once++;
					}
				else {
				System.out.print((FinalClassListSix[y][t]) + " ");
				}
			}
			System.out.println();
			ii++;
			if(ii <= FinalClassListSix.length) {
				System.out.print(ii+ ". ");
			}
		}*/
/*	//	System.out.println(t2);
	/////////////////////////// female prints /////////////////////////////	
		System.out.println();
		ii = 1 ;
		once = 0;
		System.out.println("Female Teacher 1 ClassList: ");
		for(int y=0; y<teacherOne.length; y++) {
			for(int t=0; t<6; t++) {
				if(ii == 1 && once == 0) {
					System.out.print(ii+ ". ");
					System.out.print((teacherOne[y][t]) + " ");
					once++;
					}
				else {
				System.out.print((teacherOne[y][t]) + " ");
				}
			}
			System.out.println();
			ii++;
			System.out.print(ii+ ". ");
		}
		System.out.println();
		ii = 1 ;
		once = 0;
		System.out.println("Female Teacher 2 ClassList: ");
		for(int y=0; y<teacherTwo.length; y++) {
			for(int t=0; t<6; t++) {
				if(ii == 1 && once == 0) {
					System.out.print(ii+ ". ");
					System.out.print((teacherTwo[y][t]) + " ");
					once++;
					}
				else {
				System.out.print((teacherTwo[y][t]) + " ");
				}
			}
			System.out.println();
			ii++;
			System.out.print(ii+ ". ");
		}
		System.out.println();
		ii = 1 ;
		once = 0;
		System.out.println("Female Teacher 3 ClassList: ");
		for(int y=0; y<teacherThree.length; y++) {
			for(int t=0; t<6; t++) {
				if(ii == 1 && once == 0) {
					System.out.print(ii+ ". ");
					System.out.print((teacherThree[y][t]) + " ");
					once++;
					}
				else {
				System.out.print((teacherThree[y][t]) + " ");
				}
			}
			System.out.println();
			ii++;
			System.out.print(ii+ ". ");
		}
		System.out.println();
		ii = 1 ;
	    once = 0;
	    System.out.println("Female Teacher 4 ClassList: ");
		for(int y=0; y<teacherFour.length; y++) {
			for(int t=0; t<6; t++) {
				if(ii == 1 && once == 0) {
					System.out.print(ii+ ". ");
					System.out.print((teacherFour[y][t]) + " ");
					once++;
					}
				else {
				System.out.print((teacherFour[y][t]) + " ");
				}
			}
			System.out.println();
			ii++;
			System.out.print(ii+ ". ");
		}
		System.out.println();
		ii = 1 ;
		once = 0;
		System.out.println("Female Teacher 5 ClassList: ");
		for(int y=0; y<teacherFive.length; y++) {
			for(int t=0; t<6; t++) {
				if(ii == 1 && once == 0) {
					System.out.print(ii+ ". ");
					System.out.print((teacherFive[y][t]) + " ");
					once++;
					}
				else {
				System.out.print((teacherFive[y][t]) + " ");
				}
			}
			System.out.println();
			ii++;
			System.out.print(ii+ ". ");
		}
		System.out.println();
		ii = 1 ;
		once = 0;
		System.out.println("Female Teacher 6 ClassList: ");
		for(int y=0; y<teacherSix.length; y++) {
			for(int t=0; t<6; t++) {
				if(ii == 1 && once == 0) {
					System.out.print(ii+ ". ");
					System.out.print((teacherSix[y][t]) + " ");
					once++;
					}
				else {
				System.out.print((teacherSix[y][t]) + " ");
				}
			}
			System.out.println();
			ii++;
			System.out.print(ii+ ". ");
		}
		System.out.println(t2);
		
*/
	
		/////////////////////////// male prints /////////////////////////////////
//		System.out.println();
/*		int ii = 1 ;
		int once = 0;
		System.out.println("Male teach 1 ClassList: ");
		for(int y=0; y<teachOne.length; y++) {
			for(int t=0; t<6; t++) {
				if(ii == 1 && once == 0) {
					System.out.print(ii+ ". ");
					System.out.print((teachOne[y][t]) + " ");
					once++;
				}
				else {
					System.out.print((teachOne[y][t]) + " ");
				}
			}
			System.out.println();
			ii++;
			System.out.print(ii+ ". ");
		}
		System.out.println();
		ii = 1 ;
		once = 0;
		System.out.println("Male teach 2 ClassList: ");
		for(int y=0; y<teachTwo.length; y++) {
			for(int t=0; t<6; t++) {
				if(ii == 1 && once == 0) {
					System.out.print(ii+ ". ");
					System.out.print((teachTwo[y][t]) + " ");
					once++;
				}
				else {
					System.out.print((teachTwo[y][t]) + " ");
				}
			}
			System.out.println();
			ii++;
			System.out.print(ii+ ". ");
		}
		System.out.println();
		ii = 1 ;
		once = 0;
		System.out.println("Male teach 3 ClassList: ");
		for(int y=0; y<teachThree.length; y++) {
			for(int t=0; t<6; t++) {
				if(ii == 1 && once == 0) {
					System.out.print(ii+ ". ");
					System.out.print((teachThree[y][t]) + " ");
					once++;
				}
				else {
					System.out.print((teachThree[y][t]) + " ");
				}
			}
			System.out.println();
			ii++;
			System.out.print(ii+ ". ");
		}
		System.out.println();
		ii = 1 ;
		once = 0;
		System.out.println("Male teach 4 ClassList: ");
		for(int y=0; y<teachFour.length; y++) {
			for(int t=0; t<6; t++) {
				if(ii == 1 && once == 0) {
					System.out.print(ii+ ". ");
					System.out.print((teachFour[y][t]) + " ");
					once++;
				}
				else {
					System.out.print((teachFour[y][t]) + " ");
				}
			}
			System.out.println();
			ii++;
			System.out.print(ii+ ". ");
		}
		System.out.println();
		ii = 1 ;
		once = 0;
		System.out.println("Male teach 5 ClassList: ");
		for(int y=0; y<teachFive.length; y++) {
			for(int t=0; t<6; t++) {
				if(ii == 1 && once == 0) {
					System.out.print(ii+ ". ");
					System.out.print((teachFive[y][t]) + " ");
					once++;
				}
				else {
					System.out.print((teachFive[y][t]) + " ");
				}
			}
			System.out.println();
			ii++;
			System.out.print(ii+ ". ");
		}
		System.out.println();
		ii = 1 ;
		once = 0;
		System.out.println("Male teach 6 ClassList: ");
		for(int y=0; y<teachSix.length; y++) {
			for(int t=0; t<6; t++) {
				if(ii == 1 && once == 0) {
					System.out.print(ii+ ". ");
					System.out.print((teachSix[y][t]) + " ");
					once++;
				}
				else {
					System.out.print((teachSix[y][t]) + " ");
				}
			}
			System.out.println();
			ii++;
			System.out.print(ii+ ". ");
		}
		System.out.println(t2);
*/

		
		// ///////////////////////////print totals///////////////////////////////////////////////////
		System.out.println("");
		System.out.print("Class Totals: ");
		System.out.println(" 			" + "Aca" +"	"+ "Com"+"	"+ "Beh"+"	"+"Size" +"	" + "All-Three Total");
		System.out.println("______________________________________________________________________________________");
		 int T1Academic = totals(ClasslistOne, 0);
		 int T1Communication = totals(ClasslistOne, 1);
		 int T1Behavior = totals(ClasslistOne, 2);
		 int OneFinalTotal = T1Academic + T1Communication + T1Behavior;
		 System.out.println("Teacher One Class Stats: "+"	"+ T1Academic +"	"+ T1Communication +"	"+T1Behavior +"	"+ FinalclassOneSize+"	"+OneFinalTotal );
//		 System.out.println("	Academic Total: " + T1Academic);
//		 System.out.println("	Communication Total: " + T1Communication);
//		 System.out.println("	Behavior Total: " + T1Behavior);
		 
		 int T2Academic = totals(ClasslistTwo, 0);
		 int T2Communication = totals(ClasslistTwo, 1);
		 int T2Behavior = totals(ClasslistTwo, 2);
		 int TwoFinalTotal = T2Academic + T2Communication + T2Behavior;
		 System.out.println("Teacher Two Class Stats: " +"	"+ T2Academic	+"	"+	T2Communication	+"	"+ 	T2Behavior +"	"+ FinalclassTwoSize+"	"+TwoFinalTotal );
//		 System.out.println("	Academic Total: " + T2Academic);
//		 System.out.println("	Communication Total: " + T2Academic);
//		 System.out.println("	Behavior Total: " + T2Academic);
		 
		 int T3Academic = totals(ClasslistThree, 0);
		 int T3Communication = totals(ClasslistThree, 1);
		 int T3Behavior = totals(ClasslistThree, 2);
		 int ThreeFinalTotal = T3Academic + T3Communication + T3Behavior;
		 System.out.println("Teacher Three Class Stats: " +"	"+	T3Academic	+"	"+	T3Communication	+"	"+	T3Behavior+"	"+ FinalclassThreeSize+"	"+ThreeFinalTotal );
//		 System.out.println("	Academic Total: " + T3Academic);
//		 System.out.println("	Communication Total: " + T3Communication);
//		 System.out.println("	Behavior Total: " + T3Communication);
		 
		 int T4Academic = totals(ClasslistFour, 0);
		 int T4Communication = totals(ClasslistFour, 1);
		 int T4Behavior = totals(ClasslistFour, 2);
		 int FourFinalTotal = T4Academic + T4Communication + T4Behavior;
		 System.out.println("Teacher Four Class Stats: " +"	"+ T4Academic	+"	"+	T4Communication	+"	"+ 	T4Behavior+"	"+ FinalclassFourSize+"	"+FourFinalTotal );
//		 System.out.println("	Academic Total: " + T4Academic);
//		 System.out.println("	Communication Total: " + T4Communication);
//		 System.out.println("	Behavior Total: " + T4Behavior);
		 
		 int T5Academic = totals(ClasslistFive, 0);
		 int T5Communication = totals(ClasslistFive, 1);
		 int T5Behavior = totals(ClasslistFive, 2);
		 int FiveFinalTotal = T5Academic + T5Communication + T5Behavior;
		 System.out.println("Teacher Five Class Stats: " +"	"+ T5Academic	+"	"+	T5Communication	+"	"+ 	T5Behavior+"	"+ FinalclassFiveSize+"	"+FiveFinalTotal );
//		 System.out.println("	Academic Total: " + T5Academic);
//		 System.out.println("	Communication Total: " + T5Communication);
//		 System.out.println("	Behavior Total: " + T5Behavior);
		 
		 int T6Academic = totals(ClasslistSix, 0);
		 int T6Communication = totals(ClasslistSix, 1);
		 int T6Behavior = totals(ClasslistSix, 2);
		 int SixFinalTotal = T6Academic + T6Communication + T6Behavior;
		 System.out.println("Teacher Six Class Stats: " +"	"+ T6Academic	+"	"+	T6Communication	+"	"+ 	T6Behavior+"	"+ FinalclassSixSize+"	"+SixFinalTotal );
//		 System.out.println("	Academic Total: " + T6Academic);
//		 System.out.println("	Communication Total: " + T6Communication);
//		 System.out.println("	Behavior Total: " + T6Behavior);
		// do male part, make it print pretty, and check averages
		 
	}

}
