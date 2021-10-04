/**
 * @author Joohak Lee 108699180 Recitation 1
 * <dd><code>Phrase</code> implements <dd><code>Queue</code><dd>
 * act as a wrapper for a queue.
 * 
 */
public class Phrase implements Queue{
	private int count;
	private Bigram [] data;
	/**
	 * adds a new Bigram to the end of the Phrase
	 * @param b
	 * Bigram to be added to the array
	 * implemented from <dd><code>Queue</code><dd>
	 */
	public void enqueue(Bigram b) {
		this.data[count] = b;
		this.count ++;
	}
	/**
	 *  removes and returns the first Bigram in the Phrase
	 *  @return the removed Bigram
	 *  implemented from <dd><code>Queue</code><dd>
	 */
	public Bigram dequeue() {
		Bigram remove = this.data[0];
		for(int i = 0; i < this.count; i ++) {
			this.data[i] = this.data[i+1];
		}
		this.count --;
		return remove;
	}
	/**
	 *  returns (without removing) the first Bigram in the phrase
	 *  @return 
	 *  the first Bigram
	 *  implemented from <dd><code>Queue</code><dd>
	 */
	public Bigram peek() {
		return this.data[0];
	}
	/**
	 * retrieves the number of Bigrams in the phrase in int
	 * @return 
	 * the number of Bigrams in the Phrase
	 * implemented from <dd><code>Queue</code><dd>
	 */
	public int size() {
		return this.count;
	}
	/**
	 * checks if the phrase is empty or not
	 * @return
	 * true if the queue is empty, false otherwise
	 * implemented from <dd><code>Queue</code><dd>
	 */
	public boolean isEmpty() {
		if(this.count == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * constructor for the phrase with 13 elements in it
	 */
	public Phrase () {
		this.data = new Bigram [1000000];
		this.count = 0;
	}
	
	/**
	 * retrieves the Bigram array
	 * @return
	 * the array
	 */
	public Bigram[] getPhrase() {
		return data;
	}
	/**
	 * sets the array data by accessing the buildPhraseFromStringforEnc method
	 * @param s
	 * the string which is used to create the code phrase from
	 */
	public void setData(String s ) {
		data = Phrase.buildPhraseFromStringforEnc(s).getPhrase();
	}
	/**
	 * builds the phrase from the given string
	 * @param s
	 * given string 
	 * @return
	 * the phrase queue which contains Bigrams
	 */
	public static Phrase buildPhraseFromStringforEnc(String s) {
		Phrase queue = new Phrase();
		s = s.toUpperCase();
		if(s.contains("J")) {
			s = s.replaceAll("J", "I");
		}
		if(s.contains(" ")) {
			s = s.replaceAll(" ", "");
		}
		for(int i = 0; i < s.length(); i++) {
			if(!Character.isLetter(s.charAt(i))) {
				String test = "";
				test += s.charAt(i);
				s = s.replace(test, "");
			}
		}
		while(s.length() > 0) {
			Bigram object = new Bigram();
			if(s.length() == 1) {
				object.setFirst(s.charAt(0));
				object.setSecond('X');
				queue.enqueue(object);
				s = "";
			}
			else if(s.charAt(0) == (s.charAt(1))) {
				object.setFirst(s.charAt(0));
				object.setSecond('X');
				queue.enqueue(object);
				s = s.substring(1);
			}
			else {
				object.setFirst(s.charAt(0));
				object.setSecond(s.charAt(1));
				queue.enqueue(object);
				if(s.length() == 2) {
					s = "";
				}
				else {
					s = s.substring(2);
				}
			}
		}
		return queue;
	}
	/**
	 * encrypts the given queue with the given keytable
	 * @param key
	 * keyTable which is made from another given string
	 * @return
	 * a new phrase which is encrypted by using the keyTable
	 */
	public Phrase encrypt(KeyTable key) {
		if(key == null) {
			throw new IllegalArgumentException();
		}
		else {
			Phrase p = new Phrase();
			for(int i = 0; i < this.count; i++) {
				Bigram test = this.getPhrase()[i];
				p.enqueue(test);
			}
			Phrase e = new Phrase();
			while(p.count > 0 && !(p.peek() == null)) {
				Bigram test = p.dequeue();
				test = rowTest(test,key);
				test = colTest(test,key);
				test = lastTest(test,key);
				e.enqueue(test);
			}
			return e;
		}
	}
	/**
	 * decrypt the given queue with the given keytable
	 * @param key
	 * keyTable which is made from another given string
	 * @return
	 * a new phrase which is decrypted by using the keyTable
	 */
	public Phrase decrpyt(KeyTable key) {
		if(key == null) {
			throw new IllegalArgumentException();
		}
		else {
			Phrase p = new Phrase();
			for(int i = 0; i < this.count; i++) {
				Bigram test = this.getPhrase()[i];
				p.enqueue(test);
			}
			Phrase e = new Phrase();
			while(p.count > 0 && !(p.peek() == null)) {
				Bigram test = p.dequeue();
				test = rowTestDecrpyt(test,key);
				test = colTestDecrpyt(test,key);
				test = lastTestDecrpyt(test,key);
				e.enqueue(test);
			}
			return e;
		}
	}
	/**
	 * check if the bigram's first and second character are in the same row and decrypt if it is 
	 * @param test
	 * a bigram that will be tested
	 * @param key
	 * keyTable to decrypt the Bigram
	 * @return
	 * a decrypted Bigram or unchanged Bigram
	 */
	public Bigram rowTestDecrpyt(Bigram test, KeyTable key) {
		try {
			if(key.findRow(test.getFirst()) == key.findRow(test.getSecond())) {
				int firstx = 0, firsty = 0, secondx = 0, secondy = 0;
				boolean foundfirst = false;
				boolean foundsecond = false;
				for(int i = 0; i < 5; i ++) {
					for(int j = 0; j < 5; j++) {
						if(key.getKeyTable()[i][j] == test.getFirst()) {
							firstx = i;
							firsty = j;
							foundfirst = true;
						}
						if(key.getKeyTable()[i][j] == test.getSecond()) {
							secondx = i;
							secondy = j;
							foundsecond = true;
						}
					}
				}
				if( foundsecond == true && foundfirst == true) {
					if(firsty == 0) {
						test.setFirst(key.getKeyTable()[firstx][4]);
						test.setSecond(key.getKeyTable()[secondx][secondy-1]);
					}
					else if (secondy == 0) {
						test.setFirst(key.getKeyTable()[firstx][firsty-1]);
						test.setSecond(key.getKeyTable()[secondx][4]);
					}
					else {
						test.setFirst(key.getKeyTable()[firstx][firsty-1]);
						test.setSecond(key.getKeyTable()[secondx][secondy-1]);
					}
				}
			}
		}catch(IllegalArgumentException e) {
		}
		return test;
	}
	/**
	 * check if the bigram's first and second character are in the same row and encrypt if it is 
	 * @param test
	 * a bigram that will be tested
	 * @param key
	 * keyTable to encrypt the Bigram
	 * @return
	 * an encrypted Bigram or unchanged Bigram
	 */
	public Bigram rowTest(Bigram test, KeyTable key) {
		try {
			if(key.findRow(test.getFirst()) == key.findRow(test.getSecond())) {
				int firstx = 0, firsty = 0, secondx = 0, secondy = 0;
				boolean foundfirst = false;
				boolean foundsecond = false;
				for(int i = 0; i < 5; i ++) {
					for(int j = 0; j < 5; j++) {
						if(key.getKeyTable()[i][j] == test.getFirst()) {
							firstx = i;
							firsty = j;
							foundfirst = true;
						}
						if(key.getKeyTable()[i][j] == test.getSecond()) {
							secondx = i;
							secondy = j;
							foundsecond = true;
						}
					}
				}
				if( foundsecond == true && foundfirst == true) {
					if(firsty == 4) {
						test.setFirst(key.getKeyTable()[firstx][0]);
						test.setSecond(key.getKeyTable()[secondx][secondy+1]);
					}
					else if (secondy == 4) {
						test.setFirst(key.getKeyTable()[firstx][firsty+1]);
						test.setSecond(key.getKeyTable()[secondx][0]);
					}
					else {
						test.setFirst(key.getKeyTable()[firstx][firsty+1]);
						test.setSecond(key.getKeyTable()[secondx][secondy+1]);
					}
				}
			}
		}catch(IllegalArgumentException e) {
		}
		return test;
	}
	/**
	 * check if the bigram's first and second character are in the same column and decrypt if it is 
	 * @param test
	 * a bigram that will be tested
	 * @param key
	 * keyTable to encrypt the Bigram
	 * @return
	 * a decrypted Bigram or unchanged Bigram
	 */
	public Bigram colTestDecrpyt(Bigram test, KeyTable key) {
		try {
		if(key.findCol(test.getFirst()) == key.findCol(test.getSecond())) {
			int firstx = 0, firsty = 0, secondx = 0, secondy = 0;
			boolean foundfirst = false;
			boolean foundsecond = false;
				for(int i = 0; i < 5; i ++) {
					for(int j = 0; j < 5; j++) {
						if(key.getKeyTable()[i][j] == test.getFirst()) {
							firstx = i;
							firsty = j;
							foundfirst = true;
						}
						if(key.getKeyTable()[i][j] == test.getSecond()) {
							secondx = i;
							secondy = j;
							foundsecond = true;
						}
					}
				}
				if( foundsecond == true && foundfirst == true) {
					if(firstx == 0) {
						test.setFirst(key.getKeyTable()[4][firsty]);
						test.setSecond(key.getKeyTable()[secondx-1][secondy]);
					}
					else if (secondx == 0) {
						test.setFirst(key.getKeyTable()[firstx-1][firsty]);
						test.setSecond(key.getKeyTable()[4][secondy]);
					}
					else {
						test.setFirst(key.getKeyTable()[firstx-1][firsty]);
						test.setSecond(key.getKeyTable()[secondx-1][secondy]);
					}
				}
			}
		}catch(IllegalArgumentException e) {
		}
		return test;
	}
	/**
	 * check if the bigram's first and second character are in the same column and encrypt if it is 
	 * @param test
	 * a bigram that will be tested
	 * @param key
	 * keyTable to encrypt the Bigram
	 * @return
	 * an encrypted Bigram or unchanged Bigram
	 */
	public Bigram colTest(Bigram test, KeyTable key) {
		try {
		if(key.findCol(test.getFirst()) == key.findCol(test.getSecond())) {
			int firstx = 0, firsty = 0, secondx = 0, secondy = 0;
			boolean foundfirst = false;
			boolean foundsecond = false;
				for(int i = 0; i < 5; i ++) {
					for(int j = 0; j < 5; j++) {
						if(key.getKeyTable()[i][j] == test.getFirst()) {
							firstx = i;
							firsty = j;
							foundfirst = true;
						}
						if(key.getKeyTable()[i][j] == test.getSecond()) {
							secondx = i;
							secondy = j;
							foundsecond = true;
						}
					}
				}
				if( foundsecond == true && foundfirst == true) {
					if(firstx == 4) {
						test.setFirst(key.getKeyTable()[0][firsty]);
						test.setSecond(key.getKeyTable()[secondx+1][secondy]);
					}
					else if (secondx == 4) {
						test.setFirst(key.getKeyTable()[firstx+1][firsty]);
						test.setSecond(key.getKeyTable()[0][secondy]);
					}
					else {
						test.setFirst(key.getKeyTable()[firstx+1][firsty]);
						test.setSecond(key.getKeyTable()[secondx+1][secondy]);
					}
				}
			}
		}catch(IllegalArgumentException e) {
		}
		return test;
	}
	/**
	 * when the bigram's first and second chars are not in the same column nor same row, but at the
	 *  opposite corners of the rectangle formed by the positions of the original letters.
	 * @param test
	 * a bigram that will be tested
	 * @param key
	 * keyTable to encrypt the Bigram
	 * @return
	 * a decrypted Bigram or unchanged Bigram
	 */
	public Bigram lastTestDecrpyt(Bigram test, KeyTable key) {
		try {
			if (!(key.findCol(test.getFirst()) == key.findCol(test.getSecond()))
					&& !(key.findRow(test.getFirst()) == key.findRow(test.getSecond()))) {
				int firstx = 0, firsty = 0, secondx = 0, secondy = 0;
				boolean foundfirst = false;
				boolean foundsecond = false;
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 5; j++) {
						if (key.getKeyTable()[i][j] == test.getFirst()) {
							firstx = i;
							firsty = j;
							foundfirst = true;
						}
						if (key.getKeyTable()[i][j] == test.getSecond()) {
							secondx = i;
							secondy = j;
							foundsecond = true;
						}
					}
				}
				if( foundsecond == true && foundfirst == true) {
					test.setFirst(key.getKeyTable()[firstx][secondy]);
					test.setSecond(key.getKeyTable()[secondx][firsty]);
				}
			}
		} catch (IllegalArgumentException e) {
		}
		return test;
	}
	/**
	 * when the bigram's first and second chars are not in the same column nor same row, but at the
	 *  opposite corners of the rectangle formed by the positions of the original letters.
	 * @param test
	 * a bigram that will be tested
	 * @param key
	 * keyTable to encrypt the Bigram
	 * @return
	 * an encrypted Bigram or unchanged Bigram
	 */
	public Bigram lastTest(Bigram test, KeyTable key) {
		try {
			if (!(key.findCol(test.getFirst()) == key.findCol(test.getSecond()))
					&& !(key.findRow(test.getFirst()) == key.findRow(test.getSecond()))) {
				int firstx = 0, firsty = 0, secondx = 0, secondy = 0;
				boolean foundfirst = false;
				boolean foundsecond = false;
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 5; j++) {
						if (key.getKeyTable()[i][j] == test.getFirst()) {
							firstx = i;
							firsty = j;
							foundfirst = true;
						}
						if (key.getKeyTable()[i][j] == test.getSecond()) {
							secondx = i;
							secondy = j;
							foundsecond = true;
						}
					}
				}
				if( foundsecond == true && foundfirst == true) {
					test.setFirst(key.getKeyTable()[firstx][secondy]);
					test.setSecond(key.getKeyTable()[secondx][firsty]);
				}
			}
		} catch (IllegalArgumentException e) {
		}
		return test;
	}
	
	/**
	 * prints out the characters in the phrase
	 * @return
	 * the phrase as a string
	 */
	public String toString() {
		String s = "";
		for(int i = 0 ; i < count ; i++) {
			s += data[i];
		}
		return s;
	}
}
