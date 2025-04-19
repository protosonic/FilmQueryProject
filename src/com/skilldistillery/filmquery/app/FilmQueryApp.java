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
		boolean keepGoing = true;

		do {
			printMainMenu();
			String mainChoice = input.nextLine();
			switch (mainChoice) {
			case "1": { // Show SIMPLE film data by search id
				System.out.println("\nEnter film ID to search: \n");
				int inputId = input.nextInt();

				// User story #2
				// Check if
				Film film = returnFilmByFilmId(inputId);
				if (film == null) {
					System.out.println("No film found with ID: " + inputId);
					System.out.println("\nPress Enter to continue...");
					input.nextLine();
					break;
				} else {

					System.out.println(returnFilmByFilmId(inputId).showFilmDataSimple() + "\n");
					printFilmSubMenu();
					input.nextLine();
					String filmSubChoice = input.nextLine();

					switch (filmSubChoice) {
					case "1": { // Show ALL film data by searched id
						System.out.println(returnFilmByFilmId(inputId).showFilmDataAll() + "\nGenre: "
								+ showCategoryByFilmId(inputId) + "\n-----------CAST-----------" + listActorsByFilmId(inputId) + "\n\nPress Enter to continue...\n");
						input.nextLine();
						break;
					}
					case "2": { // Return to main menu
						keepGoing = true;
						break;
					}
					}
				}
				break;
			}
			case "2": { // Show list of films matching keyword search provided by user
				System.out.println("Enter keyword to search: \n");
				String keyword = input.nextLine();
				listFilmsByKeyword(keyword);
				System.out.println("\nPress Enter to continue...");
				input.nextLine();
				break;
			}
			case "3": { // Exit the application
				printExitMessage();
				keepGoing = false;
				break;
			}
			}
		} while (keepGoing);
	}

	// User story #2
	// Returning Film object determined by film_id provided by user
	private Film returnFilmByFilmId(int filmId) { // Film return type for method chaining in startUserInterface switch
													// case
		Film film = db.findFilmById(filmId);
		return film;
	}

	// Returning film category String determined by film_id provided by user
	private String showCategoryByFilmId(int filmId) { // String return type for method chaining in starUserInterface
														// switch case
		String category = "";
		if (filmId >= 1001) {
			return "Invalid provided. Try again";

		} else {
			category = db.findCategoryByFilmId(filmId);
			category = category + "\n--------------------------";
		}
		return category;
	}

	// Printing list of films determined by keyword search provided by user
	private void listFilmsByKeyword(String keyword) {
		if (keyword == null || keyword.trim().isEmpty()) {
			System.out.println("No keyword provided. Try again");

		} else {
			// User story #3
			List<Film> filmsByKeyword = db.findFilmByKeyword(keyword);

			if (filmsByKeyword.isEmpty()) {
				System.out.println("No results found for keyword: " + keyword);

			} else {
				System.out.println("---Search Results---");
				System.out.println("Keyword: " + keyword + "\n");
				for (Film film : filmsByKeyword) {
					if (film == null) {
						System.out.println("Search returned empty...");
					}
					System.out.println(film.showFilmDataSimple());
				}

			}
		}
	}
	
	// User story #5
	// Printing list of actors determined by film_id provided by user
	private String listActorsByFilmId(Integer id) {
		List<Actor> actorByFilm = db.findActorsByFilmId(id);
		if (id == null) {
			System.out.println("Please enter an actor id to search");
		} else {
			if (actorByFilm.isEmpty()) {
				return "No actors found for this film";
			}
		}
		
		StringBuilder builder = new StringBuilder();
		for (Actor actor : actorByFilm) {
			builder.append("\n-" + actor.getFullName());
		}
		return builder.toString();
	}

	// Printing list of copies of a particular film determined by film_id provided by user
	private void listCopiesByFilmId(int filmId) {
		List<String> copies = db.findFilmCopiesByFilmId(filmId);
		System.out.println(copies);
	}

	private void printMainMenu() {
		System.out.println("-----MAIN MENU-----");
		System.out.println("1. Search Films by ID");
		System.out.println("2. Search Films by Keyword");
		System.out.println("3. Exit");
	}

	private void printFilmSubMenu() {
		System.out.println("-----FILM MENU-----");
		System.out.println("1. View all Film Details ");
		System.out.println("2. Return to Main Menu\n");
	}

	private void printWelcomeMessage(Scanner input) {
		System.out.println("  _____ __  __ _____            ____     \n"
				+ " |_   _|  \\/  |  __ \\          |  _ \\    \n" + "   | | | \\  / | |  | | ___  ___| |_) |___\n"
				+ "   | | | |\\/| | |  | |/ _ \\/ _ \\  _ <_  /\n" + "  _| | | |  | | |__| |  __/  __/ |_) / / \n"
				+ " |_____|_|  |_|_____/ \\___|\\___|____/___|\n");
		System.out.println("\t   Welcome to IMDeeBz!" + "\n\t  Live, Laugh, Le'search" + "\n\n Press Enter to continue...");
		input.nextLine();
	}
	
	private void printExitMessage() {
		System.out.println("  _____ __  __ _____            ____     \n"
				+ " |_   _|  \\/  |  __ \\          |  _ \\    \n" + "   | | | \\  / | |  | | ___  ___| |_) |___\n"
				+ "   | | | |\\/| | |  | |/ _ \\/ _ \\  _ <_  /\n" + "  _| | | |  | | |__| |  __/  __/ |_) / / \n"
				+ " |_____|_|  |_|_____/ \\___|\\___|____/___|\n");
		System.out.println("\t    Copyright \u00A9 2025 " + "\n\t  DeBruin Industries LLC. " +
				"\n\nThank you for choosing IMDeeBz for your Le'searches!" + 
				"\n\t       Goodbye! <3");
	
	}
}
