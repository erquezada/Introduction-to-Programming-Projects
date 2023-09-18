public class Runner {
	public static void main(String[] args) {

		// Create artist.
		Artist artist1 = new Artist("The Beatles", "EMI");
		Artist artist2 = new Artist("The London Symphony Orchestra", "Ode Records");

		// Create songs.
		Song song1 = new Song(artist1,"Hey Jude");
		Song song2 = new Song(artist2, "The Blue Danube");

		// Song song1 = new Song(artist1, "Hey Jude");


		// Print the details of both song and artist.
		song1.printSong();
		System.out.println();
		song2.printSong();
	}
}