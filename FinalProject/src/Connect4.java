import java.util.InputMismatchException;
import java.util.Scanner;

public class Connect4 {
	//static int player1 = 1;
	//static int player2 = 2;
	public static void main(String [] args) {
			String [][] board = new String [6][7];
			for(int i = 0; i < 6;i ++) {
				for(int k = 0; k < 7; k ++) {
					board[i][k] = "| ";
				}board[i][6] = "| |";
			}
			int round = 0;
			printboard(board);
			while(round < 22){
				dropR(board);
				printboard(board);
				System.out.println("---------------");
				boolean winRR = checkrowR(board);
				boolean winRC = checkcolumnR(board);
				boolean winRD1 = checkdiagonalR(board);
				boolean winRD2 = checkdiagonalRReverse(board);
				if( winRR == true || winRC == true || winRD1 == true || winRD2 == true) {
					System.out.println("The Red player won.");
					break;
				}
				dropY(board);
				printboard(board);
				System.out.println("---------------");
				boolean winYR = checkrowY(board);
				boolean winYC = checkcolumnY(board);
				boolean winYD1 = checkdiagonalY(board);
				boolean winYD2 = checkdiagonalYReverse(board);
				if( winYR == true || winYC == true || winYD1 == true || winYD2 == true) {
					System.out.println("The Yellow player won.");
					break;
				}
				round ++;
				if(round == 21) {
					System.out.println("No winner, all slots filled it's a draw");
					break;
				}
			}
	}public static void dropR(String [][] board) {
		Scanner stdin = new Scanner(System.in);
		int location;
		int starter = 5;
		boolean test = false;
		boolean exist = false;
		boolean rowtest = false;
		while(!test) {
			try {
				System.out.print("Drop a Red disk at column (0-6): ");
				location = stdin.nextInt();
				while(!exist) {
					try{if(board[starter][location].equals("| |") || board[starter][location].equals("| ")) {
							if(location == 6) {
								board[starter][location] = "|R|";
								exist = true;
							}else {
								board[starter][location] = "|R";
								exist = true;
							}
						}else{
							starter --;
						}
					}catch(ArrayIndexOutOfBoundsException ex) {
						System.out.println("Array index out of bounds error, please enter a number that is different than " + location + " and within 0 -6 :");
						location = stdin.nextInt();
						starter = 5;
					}
				}test = true;
			}catch(InputMismatchException ex) {
				stdin.next();
				System.out.println("Input mismatch exception, please enter an integer from 0-6!!!");
			}
		}
	}public static void dropY(String [][] board) {
		Scanner stdin = new Scanner(System.in);
		int location;
		int starter = 5;
		boolean test = false;
		boolean exist = false;
		boolean rowtest = false;
		while(!test) {
			try {
				System.out.print("Drop a Yellow disk at column (0-6): ");
				location = stdin.nextInt();
				while(!exist) {
					try{if(board[starter][location].equals("| |") || board[starter][location].equals("| ")) {
						if(location == 6) {
							board[starter][location] = "|Y|";
							exist = true;
						}else {
							board[starter][location] = "|Y";
							exist = true;
						}
					}else{
						starter --;
					}
				}catch(ArrayIndexOutOfBoundsException ex) {
					System.out.println("Array index out of bounds error, please enter a number that is different than " + location + " and within 0 -6 :");
					location = stdin.nextInt();
					starter = 5;
				}
				}
				test = true;
			}catch(InputMismatchException ex) {
				stdin.next();
				System.out.println("Input mismatch exception, please enter an integer from 0-6!!!");
			}
		}
	}public static void printboard(String [][] board) {
		for(int i = 0; i < 6;i ++) {
			for(int k = 0; k < 7; k ++) {
				System.out.print(board[i][k]);
			}System.out.println();
		}
	}public static boolean checkrowR(String [][] board) {
		for(int i = 0; i < 6; i ++) {
			for(int k = 0; k < 4; k ++) {
				if(board[i][k].equals("|R") && board[i][k+1].equals("|R") && board[i][k+2].equals("|R") && 
						board[i][k+3].equals("|R")) {
					return true;
				}else if( k == 3) {
					if(board[i][k].equals("|R") && board[i][k+1].equals("|R") && board[i][k+2].equals("|R") && 
							board[i][k+3].equals("|R|")) {
						return true;
					}
				}
			}
		}return false;
	}public static boolean checkcolumnR(String [][]board) {
		for(int i = 0; i < 3; i ++) {
			for(int k = 0; k < 7; k ++) {
				if(board[i][k].equals("|R") && board[i+1][k].equals("|R") && board[i+2][k].equals("|R") && 
						board[i+3][k].equals("|R")) {
					return true;
				}else if( k == 6) {
					if(board[i][k].equals("|R|") && board[i+1][k].equals("|R|") && board[i+2][k].equals("|R|") && 
							board[i+3][k].equals("|R|")) {
						return true;
					}
				}
			}
		}return false;
	}public static boolean checkdiagonalR(String [][] board) {
		for(int i = 0; i < 3; i ++) {
			for(int k = 0; k < 4; k ++) {
				if(board[i][k].equals("|R") && board[i+1][k+1].equals("|R") && board[i+2][k+2].equals("|R") && 
						board[i+3][k+3].equals("|R")) {
					return true;
				}else if( k == 3) {
					if(board[i][k].equals("|R") && board[i+1][k+1].equals("|R") && board[i+2][k+2].equals("|R") && 
							board[i+3][k+3].equals("|R|")) {
						return true;
					}
				}
			}
		}return false;
	}public static boolean checkdiagonalRReverse(String [][] board) {
		for(int i = 0; i < 3; i ++) {
			for(int k = 3; k < 7 ; k ++) {
				if(board[i][k].equals("|R") && board[i+1][k-1].equals("|R") && board[i+2][k-2].equals("|R") && 
						board[i+3][k-3].equals("|R")) {
					return true;
				}else if( k == 6) {
					if(board[i][k].equals("|R|") && board[i+1][k-1].equals("|R") && board[i+2][k-2].equals("|R") && 
							board[i+3][k-3].equals("|R")) {
						return true;
					}
				}
			}
		}return false;
	}public static boolean checkrowY(String [][] board) {
		for(int i = 0; i < 6; i ++) {
			for(int k = 0; k < 4; k ++) {
				if(board[i][k].equals("|Y") && board[i][k+1].equals("|Y") && board[i][k+2].equals("|Y") && 
						board[i][k+3].equals("|Y")) {
					return true;
				}else if( k == 3) {
					if(board[i][k].equals("|Y") && board[i][k+1].equals("|Y") && board[i][k+2].equals("|Y") && 
							board[i][k+3].equals("|Y|")) {
						return true;
					}
				}
			}
		}return false;
	}public static boolean checkcolumnY(String [][]board) {
		for(int i = 0; i < 3; i ++) {
			for(int k = 0; k < 7; k ++) {
				if(board[i][k].equals("|Y") && board[i+1][k].equals("|Y") && board[i+2][k].equals("|Y") && 
						board[i+3][k].equals("|Y")) {
					return true;
				}else if( k == 6) {
					if(board[i][k].equals("|Y|") && board[i+1][k].equals("|Y|") && board[i+2][k].equals("|Y|") && 
							board[i+3][k].equals("|Y|")) {
						return true;
					}
				}
			}
		}return false;
	}public static boolean checkdiagonalY(String [][] board) {
		for(int i = 0; i < 3; i ++) {
			for(int k = 0; k < 4; k ++) {
				if(board[i][k].equals("|Y") && board[i+1][k+1].equals("|Y") && board[i+2][k+2].equals("|Y") && 
						board[i+3][k+3].equals("|Y")) {
					return true;
				}else if( k == 3) {
					if(board[i][k].equals("|Y") && board[i+1][k+1].equals("|Y") && board[i+2][k+2].equals("|Y") && 
							board[i+3][k+3].equals("|Y|")) {
						return true;
					}
				}
			}
		}return false;
	}public static boolean checkdiagonalYReverse(String [][] board) {
		for(int i = 0; i < 3; i ++) {
			for(int k = 3; k < 7 ; k ++) {
				if(board[i][k].equals("|Y") && board[i+1][k-1].equals("|Y") && board[i+2][k-2].equals("|Y") && 
						board[i+3][k-3].equals("|Y")) {
					return true;
				}else if( k == 6) {
					if(board[i][k].equals("|Y|") && board[i+1][k-1].equals("|Y") && board[i+2][k-2].equals("|Y") && 
							board[i+3][k-3].equals("|Y")) {
						return true;
					}
				}
			}
		}return false;
	}
}
