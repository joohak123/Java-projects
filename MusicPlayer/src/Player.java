/**
 * @author Joohak Lee 108699180 Recitation 1
 * <dd><code>Player</code> is a main menu to navigate through to getaccess to <dd><code>SongLinkedList</code> 
 * for the <dd><code>Song</code>
 * This is the main player for the Song playlist.
 */
import java.util.Scanner;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Player {
	public static SongLinkedList list = new SongLinkedList();
	/**
	 * main method for this program(homework)
	 * @param args
	 * the argument to print out the menu and the commands.
	 */
	public static void main(String [] args) {
		boolean quit = false;
		Scanner input = new Scanner(System.in);
		while(quit == false) {
			System.out.println("Menu:");
			System.out.println(String.format("%-4s%s", "(A)", "Add Song to Playlist"));
			System.out.println(String.format("%-4s%s", "(F)", "Go to Next Song"));
			System.out.println(String.format("%-4s%s", "(B)", "Go to Previous Song"));
			System.out.println(String.format("%-4s%s", "(R)", "Remove Song from Playlist"));
			System.out.println(String.format("%-4s%s", "(L)", "Play a Song"));
			System.out.println(String.format("%-4s%s", "(C)", "Clear the Playlist"));
			System.out.println(String.format("%-4s%s", "(S)", "Shuffle Playlist"));
			System.out.println(String.format("%-4s%s", "(Z)", "Random Song"));
			System.out.println(String.format("%-4s%s", "(P)", "Print Playlist"));
			System.out.println(String.format("%-4s%s", "(T)", "Get the total amount of songs in the playlist"));
			System.out.println(String.format("%-4s%s", "(Q)", "Exit the playlist"));
			System.out.println("\n" + "Enter an option: ");
			String option = input.nextLine();
			option = option.toUpperCase();
			switch(option) {
			case "A" :
				addSong();
				option = "";
				break;
				
			case "F" :
				list.cursorForwards();
				option = "";
				break;
				
			case "B" :
				list.cursorBackwards();
				option = "";
				break;
				
			case "R":
				try{
					list.removeCursor();
				}
				catch(Exception e) {
					System.out.println("Empty List");
				}
				option = "";
				break;
				
			case "L":
				play();
				option = "";
				break;
				
			case "C":
				list.deleteAll();
				System.out.println("playlist cleared.");
				option = "";
 				break;
 				
			case "S":
				if(list.getSize() == 0) {
					System.out.println("Empty List");
				}
				else {
				list.shuffle();
				System.out.println("Playlist shuffled.");
				}
				option = "";
				break;
				
			case "Z":
				randomPlay();
				option = "";
				break;
				
			case "P":
				list.printPlaylist();
				option = "";
				break;
				
			case "T":
				int number = list.getSize();
				if(number > 0) {
					System.out.println("Your playlist contains " +  number + " song(s).");
				}
				else {
					System.out.println("Your playlist is empty");
				}
				option = "";
				break;
				
			case "Q":
				option = "";
				System.out.println("Program terminated.");
				quit = true;
				break;
			}
			System.out.println();
		}
	}
/**
 * add a <code>Song</code> into the <code>SongLinkedList</code> 
 * if there's an error when trying to get a song object with wrong length, this method will also catch the error.
 * Uses <code>SongLinkedList</code>'s <code>insertAfterCursor()</code>
 */
	public static void addSong() {
		try{
			Scanner input = new Scanner(System.in);
			System.out.println("Enter song title: ");
			String title = input.nextLine();
			System.out.println("Enter artist(s) of the song: ");
			String artist = input.nextLine();
			System.out.println("Enter album: ");
			String album = input.nextLine();
			System.out.println("Enter length (in seconds): ");
			String length = input.nextLine();
			int leng = Integer.parseInt(length);
			Song newSong = new Song(title, artist, album, leng);
			list.insertAfterCursor(newSong);
			System.out.println("'" + title + "'" + " by " + artist + " is added to your playlist.");
		}
		catch(Exception e) {
			System.out.println("invalid length input");
		}
	}
/**
 * play the music with a specific name of the song.
 * if the name is not in the <code>SongLinkedList</code> it will catch an error.
 * Uses <code>SongLinkedList</code>'s <code>Play()</code>.
 */
	public static void play() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter name of song to play:");
		String name = input.nextLine();
		try{
			Song tester = list.find(name);
			String test = tester.getName();
			list.play(test);
		}
		catch(Exception e) {
			System.out.println(name + " is not found");
		}
	}
/**
 * play a random music in the playlist
 * Uses <code>SongLinkedList</code>'s <code>random()</code> to get the random song and use <code>SongLinkedList</code>'s 
 * <code>play()</code> to play the music. 
 * If the music file is not in the directory of the program, it will also catch the error.
 */
	public static void randomPlay() {
		// if size is 0, that means there's no music in the playlist.
		if (list.getSize() == 0 ) {
			System.out.println("Empty list");
		}
		else {
			try {
				Song random = list.random();
				System.out.println("Playing a random song");
				list.play(random.getName());
			} catch (Exception e) {
				System.out.println();
			}
		}
	}
}
