/**
 * @author Joohak Lee 108699180 
 * Joohak.lee@stonybrook.edu
 * CSE 214 HW # 6
 * Recitation 1, TA: Daniel Calabria   
 * <dd><code>FrequencyList</code><dd> The frequencies of each given word in each text will be stored in an extension of ArrayList<Integer>. 
 */

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;


public class FrequencyList extends HashMap{
	private String word;
	private ArrayList<Double> frequencies;
	private Hashtable<String, Integer> passageIndices;
	/**
	 * search through the passed ArrayList of Passages and find the occurrences
	 *  of its given word in each Passage object, constructing an Integer list as well as a Hashtable
	 * @param word
	 * to search
	 * @param passages
	 * arraylist of passages to search the word from
	 */
	public FrequencyList(String word, ArrayList<Passage>passages) {
		this.word = word;
		passageIndices = new Hashtable<>();
		for(int i = 0; i < passages.size(); i++) {
			addPassage(passages.get(i));
		}
	}
	/**
	 * Adds a Passage to this FrequencyList
	 * @param p
	 * passage to add to the frequencyList and passage Indices
	 */
	public void addPassage(Passage p) {
		passageIndices.put(p.getTitle(), passageIndices.size());
		frequencies.add(p.getSimilarTitles().get(this.word));
	}
	/**
	 *  returns the frequency of “word” in the given Passage, 0 if the Passage is not contained in this FrequencyList
	 * @param p
	 * passage to search the word in
	 * @return
	 * return the double that is requency of the word if found, if not 0.
	 */
	public Double getFrequency(Passage p) {
		if(p.getSimilarTitles().containsKey(word)) {
			return p.getWordFrequency(word);
		}
		else {
			return 0.0;
		}
	}
	
}
