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
//		app.test();
    app.launch();
	}

	private void test() {
		
	}

	private void launch() {
		Scanner input = new Scanner(System.in);
		
		printWelcomeMessage(input);
		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		boolean keepGoin = true;
		
		while (keepGoin) {
			String choice = input.nextLine();
			printMainMenu();
			switch(choice) {
				case "1": {
					System.out.println("\nEnter film ID to search: \n");
					break;
					}
				case "2": {
					break;
				}
				case "3": {
					break;
				}
			}
		}
	}

	private Film showFilmByFilmId(int id) {
		Film film = db.findFilmById(id);
		return film;
	}

	private void showCategoryByFilmId(int film_id) {
		String category = db.findCategoryByFilmId(film_id);
		System.out.println(category);
	}

	private void listFilmsByKeyword(String keyword) {
		List<Film> filmsByKeyword = db.findFilmByKeyword(keyword);
		if (keyword == null) {
			System.out.println("Please enter a keyword to search");
		}
		System.out.println("---Search Results---");
		System.out.println("Keyword: " + keyword + "\n");
		for (Film film : filmsByKeyword) {
			if (film == null) {
				System.out.println("Search returned empty...");
			}
			System.out.println(film.showFilmDataSimple());
		}
	}

	private void listActorsByFilmId(Integer id) {
		List<Actor> actorByFilm = db.findActorsByFilmId(id);
		if (id == null) {
			System.out.println("Please enter an actor id to search");
		}
		System.out.println("---Search Results---");
		System.out.println("ID: " + id + "\n");
		for (Actor actor : actorByFilm) {
			System.out.println(actor.showActorDataSimple());
		}
	}

	private List<String> listCopiesByFilmId(int filmId) {
		List<String> copies = db.findFilmCopiesByFilmId(filmId);
//		System.out.println(copies);
		return copies;
	}
	
	private void printMainMenu() {
		System.out.println("-----MAIN MENU-----");
		System.out.println("1. Search Films by ID");
		System.out.println("2. Search Films by Keyword");
		System.out.println("3. Exit");
	}
	
	private void printFilmSubMenu() {
		System.out.println("-----FILM DETAILS-----");
		System.out.println("1. View all Film Details ");
		System.out.println("2. Return to Main Menu");
	}
	
	private void printWelcomeMessage(Scanner input) {
		System.out.println(
			    "  _____ __  __ _____            ____     \n" +
			    " |_   _|  \\/  |  __ \\          |  _ \\    \n" +
			    "   | | | \\  / | |  | | ___  ___| |_) |___\n" +
			    "   | | | |\\/| | |  | |/ _ \\/ _ \\  _ <_  /\n" +
			    "  _| | | |  | | |__| |  __/  __/ |_) / / \n" +
			    " |_____|_|  |_|_____/ \\___|\\___|____/___|\n");
	    System.out.println("\t    Copyright \u00A9 2025 "
	    		+ "\n\t  DeBruin Industries LLC. "
	    		+ "\n\n\t   Welcome to IMDeeBz!"
	    		+ "\n\t  Live, Laugh, Le'search"
	    		+ "\n\n Press Enter to continue...");
	    input.nextLine();
	    
	}
}
