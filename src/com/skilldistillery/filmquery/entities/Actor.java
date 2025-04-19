package com.skilldistillery.filmquery.entities;

public class Actor {
	private int id;
	private String firstName;
	private String lastName;
	
	public Actor() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Actor [id=").append(id).append(", firstName=").append(firstName).append(", lastName=")
				.append(lastName).append("]");
		return builder.toString();
	}
	
	public String getFullName() {
		String fullName = firstName + lastName;
		return fullName;
		
	}
	
	public String showActorDataSimple() {
		StringBuilder builder = new StringBuilder();
		builder.append("Actor: ")
		.append(firstName + " " + lastName);
		return builder.toString();
	}
	
	public String showActorDataAll() {
		StringBuilder builder = new StringBuilder();
		builder.append("---------------------\n")
		.append(firstName + " " + lastName)
		.append("\nID: " + id)
		.append("\n---------------------\n");
		return builder.toString();
	}
	
}
