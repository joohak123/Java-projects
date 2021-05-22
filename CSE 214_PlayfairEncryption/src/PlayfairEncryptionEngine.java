/**
 * @author Joohak Lee 108699180 Recitation 1
 * <dd><code>PlayfairEncryptionEngine</code> reads codes from <dd><code>KeyTable</code><dd> and <dd><code>Phrase</code><dd>
 * This is the menu driver for the program.
 *  The driver will allow the user to change the current key, print the current key, 
 *  enter a plaintext input and receive an encrypted version, or enter an encrypted input and receive a plaintext version
 */

import java.util.Scanner;
public class PlayfairEncryptionEngine {
	public static KeyTable keyTable = new KeyTable();
	public static Phrase phrase = new Phrase();
	public static void main(String [] args) {
		boolean quit = false;
		Scanner input = new Scanner(System.in);
		System.out.print("Enter key phrase: ");
		String key = input.nextLine();
		keyTable.setKeyTable(key);
		System.out.println("Generation success!");
		while(quit == false) {
			System.out.println("\n"+ "Menu:");
			System.out.println(String.format("%-4s%s", "(CK)", " - Change key"));
			System.out.println(String.format("%-4s%s", "(PK)", " - Print key"));
			System.out.println(String.format("%-4s%s", "(EN)", " - Encrypt"));
			System.out.println(String.format("%-4s%s", "(DE)", " - Decrypt"));
			System.out.println(String.format("%-3s%s", "(Q)", " - Quit"));
			System.out.print("\n" + "Please select an option: ");
			String option = input.nextLine();
			option = option.toUpperCase();
			switch(option) {
			case "CK":
				System.out.print("Enter key phrase: ");
				String newKey = input.nextLine();
				keyTable.setKeyTable(newKey);
				System.out.println("Generation success!");
				option = "";
				break;
			case "PK":
				System.out.println();
				System.out.println(keyTable);
				option = "";
				break;
			case "EN":
				System.out.print("Please enter a phrase to encrypt: ");
				String stringEncrypt = input.nextLine();
				System.out.println("Encrypted text is: " + phrase.buildPhraseFromStringforEnc(stringEncrypt).encrypt(keyTable));
				option = "";
				break;
			case "DE":
				System.out.print("Please enter a phrase to decrypt: ");
				String stringDecrypt = input.nextLine();
				System.out.println("Decrypted text is: " + phrase.buildPhraseFromStringforEnc(stringDecrypt).decrpyt(keyTable));
				option = "";
				break;
			case "Q":
				System.out.println();
				option = "";
				System.out.println("Program terminating....");
				quit = true;
				break;
			}
		}
	}
}
