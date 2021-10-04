/**
 * 
 * @author Joohak Lee 108699180 Recitation 01
 *
 *<code>Song </code> that is used for <code>SongNode</code>
 *Song has a name, artist, album, and length.
 */
public class Song {
	private String name;
	private String artist;
	private String album;
	private int length;
	
/**
 * no argument constructor to create a song object.
 */
	public Song() {
	}
	
/**
 * creates a song object with specific parameters.
 * @param name 
 * The name of the song in String.
 * @param artist 
 * The performer of the song in String.
 * @param album 
 * The album the song was released on in String.
 * @param length 
 * The length of the song in seconds in int.
 */
	public Song(String name, String artist, String album, int length) {
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.length = length;
	}
	
/**
 * sets the name of the song.
 * @param name 
 * the string of which is the name of the song.
 */
	public void setName(String name) {
		this.name = name;
	}
/**
 * retrieves the name of the song.
 * @return
 * the name of the song in String.
 */
	public String getName() {
		return name;
	}
/**
 * sets the name of the artist
 * @param artist
 * set the artist of the song in String.
 */
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
/**
 * retrieves the name of the Artist.	
 * @return
 * a string which is the artist of the song.
 */
	public String getArtist() {
		return artist;
	}
	
/**
 * sets the album of the song.
 * @param album
 * String that is the album of the song.
 */
	public void setAlbum(String album) {
		this.album = album;
	}
/**
 * retrieve the album of the song.
 * @return
 * a string that is album of the song.
 */
	public String getAlbum() {
		return album;
	}
/**
 * sets the length of the song.
 * @param length
 * integer that is length of the song in seconds.
 */
	public void setLength(int length) {
		this.length = length;
	}
/**
 * retrieve the length of the song.
 * @return
 * an integer that is length of the song.
 */
	public int getLength() {
		return length;
	}
/**
 * prints out the Song's name, artist, album and length in a neatly formatted look.
 * @return
 * a String that contains song's name, artist, album and length.
 */
	public String toString() {
		String test = String.format("%-25s%-27s%-27s%-9s", this.getName(), this.getArtist(), 
				this.getAlbum(), "    " + this.getLength());
		return test;
	}
}
