//This program creates an song with artist
// Class that represents an Song.
// All Songs can be created from this class.
public class Song {

	// Attributes all songs have a title and a artist that wrote them.
	private Artist artist;
	private String songTitle;


	// CONSTRUCTORS

	// Default constructor.
	public Song() {}

	// Constructor that sets the song title and artist of the song.
	public Song(Artist artist, String songTitle) {
		this.artist = artist;
		this.songTitle = songTitle;
	}


	// SETTERS
	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public void setSongTitle(String songTitle) {
		this.songTitle = songTitle;
	}


	// GETTERS
	public Artist getArtist() {
		return this.artist;
	}

	public String getSongTitle() {
		return this.songTitle;
	}

	// Print the details of this song.
	public void printSong() {
		System.out.println("Song details:");
		System.out.println("\t Song title: " + this.songTitle);
		//System.out.println("\t Artist: " + this.artist);
		artist.printArtist();
	}
}