package com.skilldistillery.filmquery.entities;

public class Film {
	// Field for each of the tables columns
	private int id;
	private String title;
	private String description;
	private int releaseYear;
	private int language;
	private int rentalDuration;
	private double rentalRate;
	private int length;
	private double replacementCost;
	private String rating;
	private String specailFeatures;

	// no-arg ctor
	public Film() {
		super();
	}

	// get/set
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public int getLanguage() {
		return language;
	}

	public void setLanguage(int language) {
		this.language = language;
	}

	public int getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public double getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public double getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(double replacementCost) {
		this.replacementCost = replacementCost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getSpecailFeatures() {
		return specailFeatures;
	}

	public void setSpecailFeatures(String specailFeatures) {
		this.specailFeatures = specailFeatures;
	}

	// toString
	@Override
	public String toString() { // fix format
		StringBuilder builder = new StringBuilder();
		builder.append("Film [id=").append(id).append(", title=").append(title).append(", description=")
				.append(description).append(", releaseYear=").append(releaseYear).append(", language=").append(language)
				.append(", rentalDuration=").append(rentalDuration).append(", rentalRate=").append(rentalRate)
				.append(", length=").append(length).append(", replacementCost=").append(replacementCost)
				.append(", rating=").append(rating).append(", specailFeatures=").append(specailFeatures).append("]");
		return builder.toString();
	}

	
	// methods
	public String showByFilmData() {
		String data = null;
		
		StringBuilder builder = new StringBuilder();
		builder.append("---------FILM--------\n" + title)
				.append("\n(" + releaseYear + ")")
				.append("\n\n" + description)
				.append("\n\n---------------------");
		return builder.toString();
	}
	
	public String showAllByFilmData() {
		String data = null;
		
		StringBuilder builder = new StringBuilder();
		builder.append("---------FILM--------\n" + title)
				.append("\n(" + releaseYear + ")")
				.append("\n\n" + description)
				.append("\n\n---------------------")
				
				.append("\n\n---------INFO---------").append("\nID: " + id).append("\nLanguage_ID: " + language)
				.append("\nDuration: ").append(rentalDuration).append("\nRental Rate: ").append(rentalRate)
				.append("\nLength: ").append(length).append("\nReplacement Cost: $").append(replacementCost)
				.append("\nRating: ").append(rating).append("\nSpecial Features: ").append(specailFeatures);
		return builder.toString();
	}


}
