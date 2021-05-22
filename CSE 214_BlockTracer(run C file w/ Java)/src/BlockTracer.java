/**
 * @author Joohak Lee 108699180 Recitation 1
 * <dd><code>BlockTracer</code> read lines of a c file to save the <dd><code>Variable</code><dd> in the 
 * <dd><code>Block</code> which is stored in <code>Stack</code> 
 * This is the main method for the Stack method.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BlockTracer {
	public static void main(String[] args) {
		Stack stack = new Stack();
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter the name of the file:");
		String fileName = scan.nextLine();
		boolean error = false;
		while (error == false) { // the c file exists in the source file//
			try {
				File file = new File(fileName + ".c"); // access the file w/ given string name
				FileInputStream fis = new FileInputStream(file);
				InputStreamReader inStream = new InputStreamReader(fis);
				BufferedReader stdin = new BufferedReader(inStream);
				String data = stdin.readLine(); // saved the line into a string
				while (data != null) { //if the line is not at the end of the file
					if (data.contains("{")) { 
						Block block = new Block();
						stack.push(block);
					}
					String [] dataSplit= data.split(",");
					if(data.contains("int ") && !data.contains("$print")) {
						for(int i = 0; i < dataSplit.length; i ++) {
							Variable integer = new Variable();
							String line = dataSplit[i];
							if(line.contains("int ")||data.contains("\tint")) {
								if(line.contains("=") && line.contains(";") && !line.contains("/*$print")) {
									line = line.substring(line.indexOf("int ") + 4);
									String name = line.substring(0, line.indexOf(" "));
									integer.setName(name);
									String number = line.substring(line.indexOf(" = ") + 3, line.indexOf(";"));
									integer.setInitialValie(Integer.parseInt(number));
									line = "";
								}
								else if (line.contains("=")&& !line.contains("/*$print")) {
									line = line.substring(line.indexOf("int ") + 4);
									String name = line.substring(0, line.indexOf(" "));
									integer.setName(name);
									String number = line.substring(line.indexOf(" = ") + 3);
									integer.setInitialValie(Integer.parseInt(number));
									line = "";
								}
								else if (line.contains(";")){
									line = line.substring(line.indexOf("int ") + 4);
									String name = line.substring(0, line.indexOf(" "));
									integer.setName(name);
									name = line.substring(0, line.indexOf(";"));
									integer.setName(name);
									line = "";
								}
								else if(!line.contains("=")&&!line.contains(";")&&!line.contains("/*$print")){
									line = line.substring(line.indexOf("int ") + 4);
									String name = line.trim();
									integer.setName(name);
									line = "";
								}
							}
							else if(line.contains("=")&&!line.contains("int")) {
								line = line.substring(1);
								String name = line.substring(0, line.indexOf(" "));
								integer.setName(name);
								if(line.contains("=") && line.contains(";")) {
									String number = line.substring(line.indexOf(" = ") + 3, line.indexOf(";"));			
									try{
										integer.setInitialValie(Integer.parseInt(number));
									}
									catch(NumberFormatException e) {
										number = number.substring(0, line.indexOf(" "));
										integer.setInitialValie(Integer.parseInt(number));
									}
									line = "";
								}
								else if (line.contains("=")) {
									String number = line.substring(line.indexOf(" = ") + 3);
									integer.setInitialValie(Integer.parseInt(number));
									line = "";
								}
							}
							else if(line.contains(";") && !line.contains("=")) {
								line = line.substring(1);
								String name = line.substring(0, line.indexOf(";"));
								integer.setName(name);
								line = "";
							}
							stack.getCurrnetStack().addData(integer);
						}
					}
					if(data.contains("int ") && data.contains("$print")) {
						String [] dataSplit2 = data.split("/");
						for (int i = 0; i < dataSplit2.length; i++) {
							if (dataSplit2[i].contains(",")) {
								Variable integer = new Variable();
								String[] dataSplit3 = dataSplit2[i].split(",");
								for (int m = 0; m < dataSplit3.length; m++) {
									String line = dataSplit3[m];
									if (line.contains("int ") && !line.contains("$print")) {
										line = line.substring(line.indexOf("int ") + 4);
										if (line.contains("=")) {
											String name = line.substring(0, line.indexOf("="));
											integer.setName(name);
											String number = line.substring(line.indexOf("=") +1);
											number.trim();
											if (number.contains(" ")) {
												number = number.substring(0, number.indexOf(" "));
											}
											else if (number.contains(";")){
												number = number.substring(0, number.indexOf(";"));
											}
											integer.setInitialValie(Integer.parseInt(number));
											line = "";
										} else if (line.contains(";")) {
											String name = line.substring(0, line.indexOf(";"));
											integer.setName(name);
										}
									}
									if (integer.getName() != "") {
										stack.getCurrnetStack().addData(integer);
									}
								}
							}
							else {
								Variable integer = new Variable();
								String line = dataSplit2[i];
								if(line.contains("int ") && !line.contains("$print")) {
									line = line.substring(line.indexOf("int ") + 4);
									if(line.contains("=")) {
										String name = line.substring(0, line.indexOf("="));
										integer.setName(name);
										String number = line.substring(line.indexOf(" = ") + 3, line.indexOf(";"));
										if(number.contains(" ")) {
											number = number.substring(0, line.indexOf(" ")-2);
										}
										integer.setInitialValie(Integer.parseInt(number));
										line = "";
									}
									else if (line.contains(";")){
										String name = line.substring(0, line.indexOf(";"));
										integer.setName(name);
									}
								}
								if(integer.getName() != "") {
									stack.getCurrnetStack().addData(integer);
								}
							}
						}
					}
					if (data.contains("*$print ")) { //prints out the variable
						data = data.substring(data.indexOf("/*$print ") + 9);
						if (data.contains("LOCAL")) { //prints out the entire variables in the block
							if(stack.getCurrnetStack().getCounter() == 0) { // no variables in the block
								System.out.println("No local variables to print.");
								System.out.println();
							}
							else {
								System.out.printf("%-20s%-13s", "Variable Name", "Initial Value");
								System.out.println();
								for (int i = 0; i < stack.getCurrnetStack().getDatalist().length; i++) {
									if (stack.getCurrnetStack().getDatalist()[i] != null) {
										System.out.println(stack.getCurrnetStack().getDatalist()[i]);
									}
								}
								System.out.println();
							}
						}
						else{ // prints out the specific variabls in the block
							data = data.substring(0, data.indexOf("*"));
							int j = stack.getSize() - 1;
							boolean found = false;
							for(int i = j; i >=0; i--) {
								for(int k = 0; k < stack.getStack().get(i).getDatalist().length; k ++) {
									if(stack.getStack().get(i).getDatalist()[k] != null) {
										if(stack.getStack().get(i).getDatalist()[k].getName().equals(data)) {
											System.out.printf("%-20s%-13s", "Variable Name", "Initial Value");
											System.out.println();
											System.out.println(stack.getStack().get(i).getDatalist()[k]);
											System.out.println();
											found = true;
											break;
										}
									}
								}
								if(found == true) {
									break;
								}
							}
							if(found == false) {
								System.out.println("Variable not found: " + data);
								System.out.println();
							}
						}
					}
					if(data.contains("}")) {
						stack.pop();
					}
					data = stdin.readLine();
				}
				error = true;
			} catch (FileNotFoundException ex) {
				System.out.print("File does not exist. Please re-type the name of the file:");
				fileName = scan.nextLine();
			} catch (IOException ex) {
			}
		}
	}
}