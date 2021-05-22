/**
 * @author Joohak Lee 108699180 
 * Joohak.lee@stonybrook.edu
 * CSE 214 HW # 6
 * Recitation 1, TA: Daniel Calabria   
 * <dd><code>Passage</code><dd> hash table which maps a String (word) to an Integer value (occurences of that word). 
 */

import java.io.File;
import java.util.Hashtable;
import java.util.Set;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.math.*;

public class Passage {
	/**
	 * Calculates the similarity between two Passage objects using the formula above and modifies their similarTitles accordingly
	 * @param p1
	 * First passage to compare from
	 * @param p2
	 * Second passage to compare to
	 * @return
	 * double which is the relative closeness of the two passages
	 */
	static ArrayList<String> stopWords = new ArrayList<>();
	public static double cosineSimiliartiy(Passage p1, Passage p2) {
		double numerator = 0.0, denominatorP1 = 0.0, denominatorP2 = 0.0;
		for(String key: p1.getWords()) {
			if(p2.getWords().contains(key)) {
				numerator += (p1.getWordFrequency(key) * p2.getWordFrequency(key));
				denominatorP1 += (p1.getWordFrequency(key) * p1.getWordFrequency(key));
				denominatorP2 += (p2.getWordFrequency(key) * p2.getWordFrequency(key));
			}
			if(!p2.getWords().contains(key)) {
				denominatorP1 += (p1.getWordFrequency(key) * p1.getWordFrequency(key));
			}
		}
		for(String key2 : p2.getWords()) {
			if(!p1.getWords().contains(key2)) {
				denominatorP2 += (p2.getWordFrequency(key2) * p2.getWordFrequency(key2));
			}
		}
		denominatorP1 = Math.sqrt(denominatorP1);
		denominatorP2 = Math.sqrt(denominatorP2);
		double testing = (numerator / (denominatorP1*denominatorP2));
		return testing;
	}
	
	private String title;
	private int wordCount = 0;
	private int swords;
	private Hashtable<String, Double> similarTitles;
	public Passage() {}
	/**
	 * constructor which sets the title of the Passage and calls the parseFile() method
	 * @param title
	 * title of the passage
	 * @param file
	 * file of the passage
	 */
	
	public Passage(String title, File file) {
		similarTitles = new Hashtable<>();
		this.setTitle(title);
		wordCount = 0;
		parseFile(file);
	}
	/**
	 * helper method to create an array with stopwords
	 * @return
	 * return the array with the stop words in it
	 */
	public ArrayList <String> createStopWords(File i){
		try {
			FileInputStream stopWordsfoo = new FileInputStream(i);
			InputStreamReader inputStream = new InputStreamReader(stopWordsfoo);
			BufferedReader reader = new BufferedReader(inputStream);
			String words = reader.readLine();
			while(words != null) {
				words = words.toLowerCase();
				stopWords.add(words);
				words = reader.readLine();
			}
		} catch (IOException e) {
		}
		return stopWords;
	}
	/**
	 * Reads the given text file and counts each word’s occurrence, excluding stop words, and inserts them into the table
	 * @param file
	 * file which the code is going to read from
	 */

	public void parseFile(File file) {
		ArrayList<Character> punctuation = new ArrayList<>();
		punctuation.add(',');
		punctuation.add('.');
		punctuation.add(' ');
		punctuation.add('\n');
		punctuation.add('\t');
		punctuation.add('!');
		punctuation.add(':');
		punctuation.add(';');
		punctuation.add('"');
		punctuation.add('?');
		punctuation.add('/');
		punctuation.add('(');
		punctuation.add(')');
		punctuation.add('\u201D');
		punctuation.add('\u201C');
		punctuation.add('\u201B');
		punctuation.add('\u201A');
		try {
			FileInputStream foo = new FileInputStream(file);
			InputStreamReader inStream = new InputStreamReader(foo);
			BufferedReader stdin = new BufferedReader(inStream);
			String data = stdin.readLine();
			while(data != null) {
				int location = 0;
				data = data.replaceAll("[^a-zA-Z ]", "");
				data = data.toLowerCase();
				while(data.length() > 0) {
					if(location >= data.length()) {
						String word = data;
						word = word.toLowerCase();
						if(!stopWords.contains(word) && word.length () >= 1 ) {
							if(similarTitles.containsKey(word)) {
								double testing = similarTitles.get(word);
								similarTitles.put(word, testing +1);
							}
							else {
								similarTitles.put(word, 1.0);
							}
							wordCount++;
						}
						data = "";
						location = 0;
					}else if(punctuation.contains(data.charAt(location))) {
						String word = data.substring(0, data.indexOf(data.charAt(location)));
						word = word.toLowerCase();
						if(word.length() > 0 && word.charAt(0) == '\'') {
							word = word.substring(1);
						}
						if(!stopWords.contains(word) && !word.equals("")) {
							if(similarTitles.containsKey(word)) {
								double testing = similarTitles.get(word);
								similarTitles.put(word, testing +1);
							}
							else {
								similarTitles.put(word, 1.0);
							}
							wordCount++;
						
						}
						data = data.substring(data.indexOf(data.charAt(location))+1);
						location = 0;
					}
					else {
						location ++;
					}
				}
				data = stdin.readLine();
			}
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
	}
	/**
	 * returns the relative frequency of the given word in this Passage (occurrences/wordCount)
	 * @param word
	 * word to use the key as
	 * @return
	 * the frequency of a word
	 */
	public double getWordFrequency(String word) {
		Double number = similarTitles.get(word);
		return (number / wordCount);
	}
	/**
	 * returns a Set containing all of the words in this Passage
	 * @return
	 * a set of string of words that is in the passage
	 */
	public Set<String> getWords(){
		return similarTitles.keySet();
	}
	
	/**
	 * gets the title of the passage
	 * @return
	 * the string which is the title of the passage
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * sets the title of the passage
	 * @param title
	 * sets the title of the passage as a given title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * gets the total word count of the passage
	 * @return
	 * the total word count in int
	 */
	public int getWordCount() {
		return wordCount;
	}
	/**
	 * sets the total wordcount as a given int
	 * @param count
	 * the int that is total word count in a passage
	 */
	public void setWordCount(int count) {
		this.wordCount = count;
	}
	/**
	 * gets hash table which contains all the words and frequency of the words
	 * @return
	 * the similarTitles
	 */
	public Hashtable<String, Double> getSimilarTitles(){
		return similarTitles;
	}
	/**
	 * sets the similarTitles with a given hash table
	 * @param similarTitles
	 * hashtable to set the SimilarTitles to
	 */
	public void setSimilarTitles(Hashtable<String, Double> similarTitles) {
		this.similarTitles = similarTitles;
	}
	/**
	 * returns string of similar titles and their percentage similarity
	 */
	public String toString() {
		String a = this.getTitle();
		return a;
	}
}
