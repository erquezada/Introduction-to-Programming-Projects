// Class that represents the name of an artist.
// Instances of Artists can be created from this class.
public class Artist {

    // Attributes: artist name and record label
    private String artistName;
    private String recordLabel;

    // CONSTRUCTORS

    // Default constructor.
    public Artist() {
        this.artistName = "Unknown Artist";  // Default value
        this.recordLabel = "Unknown Label";  // Default value
    }

    // Constructor that sets the artist name and record label upon creation.
    public Artist(String artistName, String recordLabel) {
        setArtistName(artistName);
        setRecordLabel(recordLabel);
    }

    // SETTERS

    // Sets the artist's name with validation to avoid empty names.
    public void setArtistName(String artistName) {
        if (artistName == null || artistName.trim().isEmpty()) {
            throw new IllegalArgumentException("Artist name cannot be empty or null.");
        }
        this.artistName = artistName;
    }

    // Sets the record label with validation to avoid empty labels.
    public void setRecordLabel(String recordLabel) {
        if (recordLabel == null || recordLabel.trim().isEmpty()) {
            throw new IllegalArgumentException("Record label cannot be empty or null.");
        }
        this.recordLabel = recordLabel;
    }

    // GETTERS
    public String getArtistName() {
        return this.artistName;
    }

    public String getRecordLabel() {
        return this.recordLabel;
    }

    // Prints the details of this Artist in a formatted manner.
    public void printArtist() {
        System.out.println("Artist info:");
        System.out.println("\tArtist Name: " + this.artistName);
        System.out.println("\tRecord Label: " + this.recordLabel);
    }

    // Provides a string representation of the Artist object.
    @Override
    public String toString() {
        return "Artist Name: " + this.artistName + ", Record Label: " + this.recordLabel;
    }

    // Compares two Artist objects for equality based on their name and record label.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Artist artist = (Artist) obj;
        return artistName.equals(artist.artistName) && recordLabel.equals(artist.recordLabel);
    }

    // Hashcode method for consistent hashing when used in collections like HashSet.
    @Override
    public int hashCode() {
        return 31 * artistName.hashCode() + recordLabel.hashCode();
    }

    // Main method for testing purposes.
    public static void main(String[] args) {
        Artist artist1 = new Artist("Adele", "XL Recordings");
        Artist artist2 = new Artist("Ed Sheeran", "Atlantic Records");
        Artist artist3 = new Artist("Adele", "XL Recordings");

        artist1.printArtist();
        artist2.printArtist();

        // Test equality
        System.out.println("\nArtist1 equals Artist3: " + artist1.equals(artist3));  // Should print true
        System.out.println("Artist1 equals Artist2: " + artist1.equals(artist2));  // Should print false

        // Test toString()
        System.out.println("\nArtist1 toString: " + artist1.toString());
    }
}
