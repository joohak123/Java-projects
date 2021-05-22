/**
 * @author Joohak Lee 108699180 Recitation 1
 * <dd><code>KeyTable</code> reads codes from <dd><code>KeyTable</code><dd> and <dd><code>Phrase</code><dd>
 * This represents the key to a Playfair Cipher and contains a two-dimensional array of char variables
 */

public class KeyTable {
	private char [][] key = new char [5][5];
	/**
	 * no argument constructor to create the keyTable object
	 */
	public KeyTable() {
	}
	/**
	 * Builds a new KeyTable object from the provided string and returns it
	 * @param keyphrase
	 * The String to use as the key
	 * @return
	 * The new KeyTable object.
	 * @throws
	 * IllegalArgumentException: Thrown if keyphrase is null
	 */
	
	public static KeyTable buildFromString(String keyphrase){
		if(keyphrase == null) {
			throw new IllegalArgumentException();
		}
		else {
			keyphrase = keyphrase.toUpperCase();
			keyphrase = keyphrase.trim();
			keyphrase = keyphrase.replaceAll("J", "");
			String subphrase = removeDuplicates(keyphrase);
			subphrase = subphrase.replaceAll(" ", "");
			keyphrase = subphrase;
			KeyTable test = new KeyTable();
			char [][] table = test.getKeyTable();
			int counter = 0;
			for(int i = 0; i < 5 ; i++) {
				for(int j = 0; j < 5 ; j++) {
					if(subphrase.length() == 0) {
						break;
					}
					char letter = subphrase.charAt(0);
					if(Character.isLetter(subphrase.charAt(0))) {
						table[i][j] = letter;
						subphrase = subphrase.substring(1);
						counter ++;
					}
				}
			}
			subphrase = keyphrase;
			char filler = ((char)65);
			String stringFiller = "";
			stringFiller += filler;
			for(int i =0 ; i < 5; i++) {
				for( int j = 0; j < 5; j++) {
					while(subphrase.contains(stringFiller)) {
						filler ++;
						if(filler == 'J') {
							filler ++;
						}
						stringFiller = "";
						stringFiller += filler;
					}
					if(table[i][j] == '\0') {
						table[i][j] = filler;
						subphrase += filler;
					}
				}
			}
			return test;
		}
	}
	/**
	 * helper method to remove the duplicate letters in a given string
	 * @param code
	 * original given string to create the keyTable object
	 * @return
	 * a string with removed duplicate letters
	 */
	public static String removeDuplicates(String code) {
		char [] array = code.toCharArray();
		String nodupe = "";
		int location = 0;
		for(int i = 0; i < code.length(); i ++) {
			int j;
			for(j = 0 ; j < i ; j ++) {
				if(array[i] == array[j]) {
					break;
				}
			}
			if ( j ==  i) {
				location = i;
				nodupe += array[location];
			}
		}
		return nodupe;
	}
	/**
	 * return the key 
	 * @return
	 * 
	 */
	public char [][] getKeyTable(){
		return key;
	}
	
	/**
	 * sets the keytable with the given string by accessing the buildFromString method
	 * @param keyphrase
	 * the string keyphrase to make the keyTable
	 */
	
	public void setKeyTable(String keyphrase) {
		key = KeyTable.buildFromString(keyphrase).getKeyTable();
	}
	/**
	 *  Returns the row in which c occurs.
	 * @param c
	 * The character to locate within the key matrix
	 * @return
	 * The index of the row in which c occurs.
	 * @throws
	 * IllegalArgumentException: Thrown if c is not a valid letter in the key matrix.
	 */
	public int findRow(char c) {
		int location = 0;
		boolean found = false;
		for(int i = 0; i < 5; i ++) {
			for(int j = 0; j < 5; j ++) {
				char verify = key[i][j];
				if(verify == c) {
					location = i;
					found = true;
					break;
				}
			}
		}
		if(found == true) {
			return location;
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	/**
	 * Returns the column in which c occurs.
	 * @param c
	 * The character to locate within the key matrix
	 * @return
	 * The index of the column in which c occurs.
	 * @throws
	 * IllegalArgumentException: Thrown if c is not a valid letter in the key matrix.
	 */
	public int findCol(char c) {
		int location = 0;
		boolean found = false;
		for(int i = 0; i < 5; i ++) {
			for (int j = 0; j <5; j++) {
				char verify = key[i][j];
				if (verify == c) {
					found = true;
					location = j;
					break;
				}
			}
		}
		if (found == true) {
			return location;
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	/**
	 * prints out the keyTable in 5x5 format
	 * @return
	 * keyTable as a string
	 */
	public String toString(){
		String lines = "";
		for(int i = 0; i < 5; i ++) {
			for(int j = 0; j < 5; j++) {
				lines += Character.toString(this.getKeyTable()[i][j]);
				if( j == 4) {
					break;
				}
				lines += " ";
			}
			if(i == 4) {
				break;
			}
			lines += "\n";
		}
		return lines;
	}
}
