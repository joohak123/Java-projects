/**
 * 
 * @author Joohak Lee 108699180 Recitation 01
 *
 *<code>SongNode </code> that is used for <code>SongLinkedList</code>.
 *This class implements codes from <code>Song</code>.
 *<code>SongNode</code> acts as a node wrapper around a Song object.
 */
public class SongNode {
	private SongNode prev;
	private SongNode next;
	private Song data;
/**
 * no argument constructor creates a SongNode Object.
 */
	public SongNode() {
	}
/**
 * constructor with a specific song data to create a SongNode object.
 * @param data
 * data in <code>Song</code> for this object.
 */
	public SongNode(Song data) {
		this.data = data;
	}
/**
 * set the previous SongNode
 * @param prev
 * sets the SongNode as a prev Songnode.
 */
	public void setPrev(SongNode prev) {
		this.prev = prev;
	}
/**
 * retrieves the next SongNode
 * @return
 * the next SongNode
 */
	public SongNode getPrev() {
		return prev;
	}
/**
 * sets the next SongNode with a given SongNode
 * @param next
 * sets the next Songnode as next
 */
	public void setNext(SongNode next) {
		this.next = next;
	}
/**
 * retrieves the next Songnode
 * @return
 * the next SongNode
 */
	public SongNode getNext() {
		return next;
	}
/**
 * sets the data of the current node as a given Song
 * @param data
 * in Song that will be the data of the current node.
 */
	public void setData(Song data) {
		this.data = data;
	}
/**
 * retrieves the node's data, <code>Song</code>
 * @return
 * the data of the node
 */
	public Song getData() {
		return data;
	}
/**
 * create a string to return the node's information.
 * @return
 * String that contains song's name, artist, album and length.
 */
	public String toString() {
		return data.toString();
	}
}
