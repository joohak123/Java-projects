/**
 * @author Joohak Lee 108699180 Recitation 1
 *<dd><code>Queue</code> Interface to be used in <dd><code>Phrase</code>
 *
 */
public interface Queue {
	/**
	 * adds a new Bigram to the end of the Phrase
	 * @param b
	 * Bigram to be added to the array
	 */
	public void enqueue(Bigram b);
	/**
	 * removes and returns the first Bigram in the Phrase
	 * @return
	 * the removed Bigram
	 */
	public Bigram dequeue();
	/**
	 * returns (without removing) the first Bigram in the phrase
	 * @return
	 * the first Bigram
	 */
	public Bigram peek();
	/**
	 *  returns the number of Bigrams in the Phrase
	 * @return
	 * the number of Bigrams in the Phrase
	 */
	public int size();
	/**
	 * checks if the phrase is empty or not
	 * @return
	 * true if the queue is empty, false otherwise
	 */
	public boolean isEmpty();
	
}
