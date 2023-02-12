import java.util.Scanner;
import java.lang.*;
public class MovieList {
  public static void main(String[] args) {
    boolean enjoy = false;
    Scanner scnr = new Scanner(System.in);
    System.out.println("What is your name?: ");
    String userName = scnr.nextLine();
    System.out.println(userName + ", welcome to the your favorite movie playlist.");
    System.out.println("I see that you have no movies in your favorite movie playlist.");
    System.out.println("Let's add some movies to your favorite movie playlist. Choose five movies to add to your favorite movie playlist.");
    //Ask user to enter five of their favorite movies.
    String[] movies = new String[5];
    for (int i = 0; i < movies.length; i++) {
      String movieName = scnr.nextLine();
      movies[i] = movieName;
    }
    System.out.println("There are 5 movies in your favorite movie playlist");
    for (int i = 0; i < movies.length; i++) {
      // Display the entered movies to user
      System.out.println(movies[i]);
    }
    // Ask user to pick a movie to watch
    System.out.println("What movie would you like to watch?");
    String pickMovie = scnr.nextLine();
    for (int i = 0; i < movies.length; i++) {
      if (movies[i].equals(pickMovie)) {
        System.out.println("This movie is listed as #" + (i+1) + " in your list");
        enjoy = true;
      } // Enjoy your movie 
      else {}
    }
    if (enjoy == true) {
      System.out.println("Enjoy your movie, " + userName + ".");
      System.exit(1);
    } else if (enjoy == false) {
      System.out.println("Sorry," + userName + " this movie is unavailable.");
      //Sorry this movie is not available
    }
  }
}