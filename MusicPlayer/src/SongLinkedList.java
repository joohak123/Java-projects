/**
 * 
 * @author Joohak Lee 108699180 Recitation 01
 *<code>SongLinkedList </code> that uses for <code>SongNode</code> to create a list of Doubly-linkeded list.
 *This class creates a playlist which contains the <code>Song</code>'s informations.
 */
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SongLinkedList {
	
	private SongNode head;
	private SongNode tail;
	private SongNode cursor;
	private int size;
	private Clip c;
/**
 * no argumen constructor to create the SongLinkedList object with null in nodes and 0 in size.
 */
	public SongLinkedList() {
		head = null;
		tail = null;
		cursor = null;
		size = 0;
	}
/**
 * Plays the song indicated by name
 * @param name
 * String that is name of the Song
 * <b>Preconditions:</b>
 * The name must match an actual song name in the playlist and there must be a file associated with it.
 * <b>Postconditions:</b>
 *  The song is now playing.
 * @throws IllegalArgumentException
 * indicates that the provided name does not correspond to a song in the playlist, or that the wav file could not be found.
 */
	public void play(String name) throws IllegalArgumentException{
		try {
		c.stop();
		}
		catch(Exception e) {
		}
		try {
			AudioInputStream AIS = AudioSystem.getAudioInputStream(new File(name+ ".wav"));
			c = AudioSystem.getClip();
			c.open(AIS);
			c.start();
			Song test = find(name);
			System.out.println("'" + name + "'" + " by " + test.getArtist() + " is now playing");
		}
		catch(Exception e) {
			System.out.println(name + " not in directory");
			throw new IllegalArgumentException();
		}
	}
/**
 * find the song in the list
 * @param name
 * String that is the song of the song
 * @return
 * the Song object when found and null when not found.
 */
	public Song find(String name) {
		SongNode newCursor = head;
		for(int i = 0; i < size ; i++) {
			if(newCursor.getData().getName().equals(name)) {
				return newCursor.getData();
			}
			newCursor = newCursor.getNext();
		}
		return null;
	}
/**
 * Moves the cursor to point at the next SongNode.
 * <b>Preconditions:</b>
 * The list is not empty (cursor is not null).
 * <b>Postconditions:</b>
 * The cursor has been advanced to the next SongNode, or has remained at the tail of the list. 
 */
	public void cursorForwards() {
		// when the list is empty cursor will aim at head which is null
		if(cursor == null) {
			System.out.println("list is empty");
		}
		else {
			// when cursor is pointing at the end fo the list can't move 
			if(cursor == tail) {
				System.out.println("already at end of playlist");
			}
			else {
				// when cursor is not at tail and list is not empty, move the cursor to the next node.
				cursor = cursor.getNext();
				System.out.println("cursor moved to the next Song");
			}
		}
	}
/**
 * Moves the cursor to point at the previous SongNode.
 * <b>Preconditions:</b>
 *   The list is not empty (cursor is not null).
 * <b>Postconditions:</b>
 * The cursor has been moved back to the previous SongNode, or has remained at the head of the list.
 */
	public void cursorBackwards() {
		//when the list is empty cursor will aim at head which is null
		if(cursor == null) {
			System.out.println("list is empty");
		}
		else {
			//when the cursor is at head, it won't move 
			if(cursor == head) {
				System.out.println("Already at beginning of playlist");
			}
			else {
				//when the cursor is not pointing at head and list is not empty, move the cursor backward
				cursor = cursor.getPrev();
				System.out.println("cursor moved to the previous Song");
			}
		}
	}
/**
 * Inserts a song into the playlist AFTER the cursor position
 * @param newSong
 * Object Song to be inserted into the playlist.
 * <b>Preconditions:</b>
 * The newSong object has been instantiated
 * <b>Postconditions:</b>
 * The new Song is inserted into the playlist after the position of the cursor.
 * The cursor now points to the inserted node.
 * @throws
 * IllegalArgumentException when the newSong is null.
 */
	public void insertAfterCursor(Song newSong) {
		if(newSong == null) {
			throw new IllegalArgumentException("newSong is empty");
		}
		
		if(newSong instanceof Song) {
			if(cursor == null) {
				SongNode test = new SongNode(newSong);
				cursor = head = tail = test;
				size++;
			}
			else if(cursor == tail) {
				SongNode test = new SongNode(newSong);
				test.setNext(cursor.getNext());
				test.setPrev(cursor);
				cursor.setNext(test);
				tail = test;
				size++;
				cursor = cursor.getNext();
			}
			else {
				SongNode test = new SongNode(newSong);
				test.setNext(cursor.getNext());
				test.setPrev(cursor);
				cursor.getNext().setPrev(test);
				cursor.setNext(test);
				size++;
				cursor = cursor.getNext();
			}
		}
	}
/**
 * Removes the SongNode referenced by the cursor and returns the Song contained within the node.
 * <b>Preconditions:</b>
 * The cursor is not null.
 * <b>Postconditions:</b>
 *  The SongNode referenced by the cursor has been removed from the playlist.
 *  The cursor now references the next node, or the previous node if no next node exists.
 * @return
 *  The Song contained within the removed SongNode.
 */
	public Song removeCursor() {
		SongNode node = cursor;
		if(cursor == null) {
			throw new IllegalArgumentException("list is empty");
		}
		else if (size == 1) {
			cursor = head = tail = null;
			size --;
		}
		else {
			if(cursor == tail) {
				cursor = tail = tail.getPrev();
				tail.setNext(null);
				size --;
			}
			else if (cursor == head) {
				cursor = head = head.getNext();
				head.setPrev(null);
				size --;
			}
			else {
				SongNode cursorPrev = cursor.getPrev();
				SongNode cursorPost = cursor.getNext();
				SongNode temporary = cursor;
				cursor.getNext().setPrev(cursorPrev);
				cursor.getPrev().setNext(cursorPost);
				cursor = cursor.getNext();
				temporary = null;
				size --;
			}
		}
		System.out.println("'" + node.getData().getName() + "'" + 
				" by " + node.getData().getArtist() + " was removed from the playlist");
		return node.getData();
	}
/**
 * Determines the number of Song objects currently in the playlist.
 * @return
 * The number of Song objects in the playlist.
 */
	public int getSize() {
		return size;
	}
/**
 * Selects one of the songs in the playlist and play it at random.
 * <b>Postconditions:</b>
 * The randomly selected song is now playing.
 * @return
 * The Song which was randomly selected.
 */
	public Song random() {
		if ( size == 0) {
			throw new IllegalArgumentException();
		}
		SongNode test = head;
		Song result = test.getData();
		int number = (int)(Math.random()*size) + 1;
		for(int i = 1; i < number ; i++) {
			test = test.getNext();
			result = test.getData();
		}
		return result;
	}

/**
 * Randomly shuffles the order of the songs contained within the playlist.
 * <b>Postconditions:</b>
 * The playlist is randomly reordered.
 */
	public void shuffle() {
		Song data = cursor.getData();
		if ( size == 0) {
			throw new IllegalArgumentException();
		}
		if( size == 1) {
			return;
		}
		SongNode current;
		SongNode editor;
		Song Temp;
		int random = (int) (Math.random() * size);
		for (int i = 0; i < random; i++) {
			for (int j = 0; j < size; j++) {
				current = head;
				editor = head;
				random = (int) (Math.random() * size);
				for (int k = 0; k < random; k++)
					editor = editor.getNext();
				Temp = current.getData();
				current.setData(editor.getData());
				editor.setData(Temp);
			}
		}
		SongNode trial = head;
		for(int i = 0; i < size ; i ++) {
			if(trial.getData().equals(data)) {
				cursor = trial;
				break;
			}
			trial = trial.getNext();
		}
	}
/**
 * This will simply delete all of the songs from the playlist.
 * <b>Postconditions:</b>
 * All songs have been removed.
 */
	public void deleteAll() {
		head = null;
		tail = null;
		cursor = null;
		size = 0;
	}
/**
 * Prints the playlist in a neatly-formatted table. 
 */
	public void printPlaylist() {
		System.out.printf("%-25s%-27s%-27s%-12s", "Song", "| Artist", "| Alubm", "| Length (s)");
		System.out.println();
		System.out.println("-------------------------------------------------------------------------------------------");
		System.out.println(this);
	}
/**
 * Returns a neatly formatted String representation of the playlist. See the Sample I/O for layout.
 * @return
 * A neatly formatted String representing the playlist in tabular form.
 */
	public String toString() {
		String test = "";
		SongNode currentSong = head;
		while(currentSong!= null) {
			test += currentSong.toString();
			if(currentSong == cursor) {
				test += "<--";
			}
			test += "\n";
			currentSong = currentSong.getNext();
		}
		return test;
	}
}
