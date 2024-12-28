public class Runner {

    public static void main(String[] args) {
        try {
            // Create artists with valid data
            Artist artist1 = new Artist("The Beatles", "EMI");
            Artist artist2 = new Artist("The London Symphony Orchestra", "Ode Records");

            // Create songs with valid data
            Song song1 = new Song(artist1, "Hey Jude");
            Song song2 = new Song(artist2, "The Blue Danube");

            // Print the details of both songs and artists
            System.out.println("Song 1 details:");
            song1.printSong();
            System.out.println();  // Space between outputs
            System.out.println("Song 2 details:");
            song2.printSong();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
