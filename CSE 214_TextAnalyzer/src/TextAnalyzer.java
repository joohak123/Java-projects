/**
 * @author Joohak Lee 108699180 
 * Joohak.lee@stonybrook.edu
 * CSE 214 HW # 6
 * Recitation 1, TA: Daniel Calabria   
 * <dd><code>TextAnalyzer</code><dd> The user should be prompted to enter the directory of a folder on startup.
 */
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class TextAnalyzer {
	static FrequencyTable table = new FrequencyTable();
	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		System.out.println("Enter the directory of a folder of text files: ");
		String directory = stdin.nextLine();
		boolean testing = false;
		while (testing == false) {
			try {
				File[] directoryOfFiles = new File(directory).listFiles();
				ArrayList<Passage> passages = new ArrayList<>();
				for(File i : directoryOfFiles) {
					if(i.getName().equals("StopWords.txt")) {
						Passage stopWords = new Passage();
						stopWords.createStopWords(i);
						break;
					}
				}
				for (File i : directoryOfFiles) {
					if(i.getName().equalsIgnoreCase("StopWords.txt")) {
						continue;
					}
					Passage passage = new Passage(i.getName(), i);
					passages.add(passage);
				}
				table.buildTable(passages);
				System.out.printf("%-25s%-40s", "Text (title)", "|Similarities(%)");
				System.out.println("\n---------------------------------------------------------------------------------------");
				for (int i = 0; i < passages.size(); i++) {
					System.out.printf("%-25s", passages.get(i).getTitle().substring(0, passages.get(i).getTitle().length()-4)) ;
					System.out.print("|");
					int skip = 0;
					for (int k = 0; k < passages.size(); k++) {
						if (passages.get(i).equals(passages.get(k))) {
							continue;
						}
						if(skip > 0 && skip % 2 == 0) {
							System.out.printf("\n%-25s", " ");
							System.out.print("|");
						}
						double percentage = Passage.cosineSimiliartiy(passages.get(i), passages.get(k))* 100;
						System.out.print(passages.get(k).getTitle().substring(0, passages.get(k).getTitle().length() - 4) 
								+ "(" + (int)(percentage) + "%), ");
						skip ++;
					}
					System.out.println("\n---------------------------------------------------------------------------------------");
				}
				System.out.println("\nSuspected Texts With Same Authors");
				System.out.println("---------------------------------------------------------------------------------------");
				for (int i = 0; i < passages.size(); i++) {
					for (int k = 0 + i; k < passages.size() ; k++) {
						if (passages.get(i).equals(passages.get(k))) {
							continue;
						}
						double percentage = Passage.cosineSimiliartiy(passages.get(i), passages.get(k))* 100;
						if(percentage > 60.0) {
							System.out.println("'" + passages.get(i).getTitle().substring(0, passages.get(i).getTitle().length()-4)+ "' and '" + passages.get(k).getTitle().substring(0, passages.get(k).getTitle().length()-4) + "' may have the same author (" + Math.round(percentage) + "% similar).");
						}
					}
				}
				System.out.println("\nProgram terminating...");
				testing = true;
			} catch (NullPointerException | IllegalArgumentException e) {
				System.out.println("please re enter the directory location.");
				System.out.println("Enter the directory of a folder of text files: ");
				directory = stdin.nextLine();
			}
		}
	}
}
