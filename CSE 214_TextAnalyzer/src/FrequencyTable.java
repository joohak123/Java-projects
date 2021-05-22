/**
 * @author Joohak Lee 108699180 
 * Joohak.lee@stonybrook.edu
 * CSE 214 HW # 6
 * Recitation 1, TA: Daniel Calabria   
 * <dd><code>FrequencyTable</code><dd> contain a Collection of FrequencyLists and a static method
 *  which builds each FrequencyList from a list of Passage objects, as well as a method to access 
 *  the frequency of a word in a given Passage.
 */
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class FrequencyTable {
	/**
	 * Iterates through passages and constructs FrequencyLists with each Passage’s appropriate word frequencies
	 * @param passages
	 * a collection containing the Passage objects to be parsed through
	 * @return
	 * The FrequencyTable constructed from each Passage in passages.
	 */
	public static FrequencyTable buildTable(ArrayList<Passage> passages) {
		FrequencyTable test = new FrequencyTable();
		for(int i = 0; i < passages.size(); i ++) {
			test.addPassage(passages.get(i));
		}
		return test;
	}
	/**
	 * Adds a Passage into the FrequencyTable and updates the FrequencyLists accordingly
	 * @param p
	 *  the Passage being iterated over and integrated into the table.
	 * @throws IllegalArgumentException
	 * IllegalArgumentException: If the given Passage is null or empty.
	 */
	public void addPassage(Passage p) throws IllegalArgumentException{
		if(p == null || p.getWordCount() ==0) {
			throw new IllegalArgumentException();
		}
		else {
			Hashtable <String, Passage>result = new Hashtable<>();
			result.put(p.getTitle(), p);
		}
	}
	/**
	 *  returns the frequency of the given word in the given Passage.
	 * @param word
	 * word to search from the passage
	 * @param p
	 * passage to search the word from
	 * @return
	 * the double that is frequency of the word in the passage
	 */
	public double getFrequency(String word, Passage p) {
		return p.getWordFrequency(word);
	}
}
