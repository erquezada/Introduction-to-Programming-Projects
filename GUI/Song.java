// Class that represents a Song.
// All Songs can be created from this class.
public class Song {

    // Attributes: an artist and a song title
    private Artist artist;
    private String songTitle;

    // CONSTRUCTORS

    // Default constructor.
    public Song() {
        this.artist = null;  // Default to null if no artist is set
        this.songTitle = "Unknown Title";  // Default title if not set
    }

    // Constructor that sets the song title and artist of the song.
    public Song(Artist artist, String songTitle) {
        setArtist(artist);
        setSongTitle(songTitle);
    }

    // SETTERS

    // Sets the artist with validation to avoid null values.
    public void setArtist(Artist artist) {
        if (artist == null) {
            throw new IllegalArgumentException("Artist cannot be null.");
        }
        this.artist = artist;
    }

    // Sets the song title with validation to avoid empty titles.
    public void setSongTitle(String songTitle) {
        if (songTitle == null || songTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Song title cannot be empty or null.");
        }
        this.songTitle = songTitle;
    }

    // GETTERS

    public Artist getArtist() {
        return this.artist;
    }

    public String getSongTitle() {
        return this.songTitle;
    }

    // Prints the details of this song.
    public void printSong() {
        System.out.println("Song details:");
        System.out.println("\tSong Title: " + this.songTitle);
        if (this.artist != null) {
            this.artist.printArtist();
        } else {
            System.out.println("\tArtist: No artist specified.");
        }
    }

    // Provides a string representation of the Song object.
    @Override
    public String toString() {
        return "Song Title: " + this.songTitle + ", Artist: " + (this.artist != null ? this.artist.getArtistName() : "No artist specified");
    }

    // Compares two Song objects for equality based on the artist and song title.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Song song = (Song) obj;
        return songTitle.equals(song.songTitle) && artist.equals(song.artist);
    }

    // Hashcode method for consistent hashing when used in collections like HashSet.
    @Override
    public int hashCode() {
        return 31 * songTitle.hashCode() + (artist != null ? artist.hashCode() : 0);
    }

    // Main method for testing purposes.
    public static void main(String[] args) {
        Artist artist1 = new Artist("Adele", "XL Recordings");
        Song song1 = new Song(artist1, "Someone Like You");
        Song song2 = new Song(artist1, "Rolling in the Deep");

        song1.printSong();
        song2.printSong();

        // Test equality
        System.out.println("\nSong1 equals Song2: " + song1.equals(song2));  // Should print false

        // Test toString()
        System.out.println("\nSong1 toString: " + song1.toString());
    }
}
