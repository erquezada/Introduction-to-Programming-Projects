

// Class that represents the name of an artist.
// Instances of Artists can be created from this class.
public class Artist {

	// Attributes some artists have: a stage/artist name and record label.
	private String artistName;
	private String recordLabel;


	// CONSTRUCTORS

	// Default constructor.
	public Artist() {}

	// Constructor that sets the artist name and record label upon creation.
	public Artist(String artistName, String recordLabel) {
		this.artistName = artistName;
		this.recordLabel = recordLabel;
	}


	// SETTERS
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public void setRecordLabel(String recordLabel) {
		this.recordLabel = recordLabel;
	}


	// GETTERS
	public String getArtistName() {
		return this.artistName;
	}

	public String getRecordLabel() {
		return this.recordLabel;
	}


	// Print the details of this Artist.
	public void printArtist() {
		System.out.println("Artist info:");
		System.out.println("\tArtist Name: " + this.artistName);
		System.out.println("\tRecord Label: " + this.recordLabel);
	}
}