package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
  
  DatabaseAccessor db = new DatabaseAccessorObject();

  public static void main(String[] args) {
    FilmQueryApp app = new FilmQueryApp();
    app.test();
//    app.launch();
  }

  private void test() {
//    Film film = db.findFilmById(34);
//    System.out.println(film.showByFilmData());
//    System.out.println(film.showAllByFilmData());
	  listActorsByFilmId(21);
	  listFilmsByKeyword("academy");  
	  }

  private void launch() {
    Scanner input = new Scanner(System.in);
    
    startUserInterface(input);
    
    input.close();
  }

  private void startUserInterface(Scanner input) {
//  User story #1
//	  The user is presented with a menu in which they can choose to:
//		  Look up a film by its id.
//		  Look up a film by a search keyword.
//		  Exit the application.
	  
//	  User Story 2
//	  	If the user looks up a film by id, they are prompted to enter the film id. 
//	  	If the film is not found, they see a message saying so. 
//	 	If the film is found, its title, year, rating, and description are displayed.
	  
//	  User Story 3
//	  	If the user looks up a film by search keyword, they are prompted to enter it.
//	 	If no matching films are found, they see a message saying so. 
//	  	Otherwise, they see a list of films for which the search term was found anywhere in 
//	  	the title or description, with each film displayed exactly as it is for User Story 2.
	  
//	  User Story 4
//		When a film is displayed, its language (English,Japanese, etc.) is displayed, 
//		in addition to the film's title, year, rating, and description.
	  
//	  User Story 5
//	  	When a film is displayed, the list of actors in its cast is displayed, 
//	  	in addition to the film's title, year, rating, description, and language.
  }
  
  public void listFilmsByKeyword(String keyword) {
	  List<Film> filmsByKeyword = db.findFilmByKeyword(keyword);
	  		if (keyword == null) {
	  			System.out.println("Please enter a keyword to search");
	  		} 
	  		System.out.println("---Search Results---");
	  		System.out.println("Keyword: " + keyword + "\n");
	  		for (Film film : filmsByKeyword) {
	  				if (film == null) {
	  					System.out.println("Search returned empty...");
	  				} System.out.println(film.showByFilmData());
		}
  }
  
  public void listActorsByFilmId(Integer id) {
	  List<Actor> actorByFilm = db.findActorsByFilmId(id);   
		   if (id == null ) {
			   System.out.println("Please enter an actor id to search");
		   } 
		   System.out.println("---Search Results---");
		   System.out.println("ID: " + id + "\n");
		   for (Actor actor : actorByFilm) {
				   System.out.println(actor.showActorAll());
	}
  }

}
