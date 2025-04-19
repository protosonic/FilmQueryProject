package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid";
	private static final String USERNAME = "student";
	private static final String PASSWORD = "student";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;

		try {
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			String sql = "SELECT * FROM film WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				film = new Film();
				film.setId(rs.getInt("id"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				film.setReleaseYear(rs.getInt("release_year"));
				film.setLanguage(rs.getInt("language_id"));
				film.setRentalDuration(rs.getInt("rental_duration"));
				film.setRentalRate(rs.getDouble("rental_rate"));
				film.setLength(rs.getInt("length"));
				film.setReplacementCost(rs.getDouble("replacement_cost"));
				film.setRating(rs.getString("rating"));
				film.setSpecailFeatures(rs.getString("special_features"));
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;

	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		try {
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String sql;

			sql = "SELECT * FROM actor WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				actor = new Actor();
				actor.setId(rs.getInt("id"));
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actorsList = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			String sql = "SELECT DISTINCT id, first_name, last_name FROM actor JOIN film_actor WHERE film_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Actor actor = new Actor();
				actor.setId(rs.getInt("id"));
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));
				actorsList.add(actor);
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actorsList;
	}

	@Override
	public List<Film> findFilmByKeyword(String keyword) {
		List<Film> filmsList = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			String sql = "SELECT * FROM film WHERE title LIKE ? OR description LIKE ? ";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1,"%" + keyword + "%");
			stmt.setString(2,"%" + keyword + "%");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Film film = new Film();
				film.setId(rs.getInt("id"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				film.setReleaseYear(rs.getInt("release_year"));
				film.setLanguage(rs.getInt("language_id"));
				film.setRentalDuration(rs.getInt("rental_duration"));
				film.setRentalRate(rs.getDouble("rental_rate"));
				film.setLength(rs.getInt("length"));
				film.setReplacementCost(rs.getDouble("replacement_cost"));
				film.setRating(rs.getString("rating"));
				film.setSpecailFeatures(rs.getString("special_features"));
				filmsList.add(film);
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return filmsList;
	}

	@Override
	public String findCategoryByFilmId(int filmId) {
		String category = null;

		try {
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			String sql = "SELECT c.name FROM category c JOIN film_category fc ON c.id = fc.category_id WHERE fc.film_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				category = rs.getString("c.name");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return category;
	}
	
	public List<String> findFilmCopiesByFilmId(int filmId) {
		List<String> copies = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			String sql = "SELECT i.store_id, i.media_condition, i.last_update, s.address_id, a.address " +
			             "FROM inventory_item i " +
			             "JOIN store s ON s.id = i.store_id " +
			             "JOIN address a ON s.address_id = a.id " +
			             "WHERE i.film_id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String entry = "\n----------------------\n" +
				               "Store ID: " + rs.getInt("store_id") +
				               "\nStore Address: " + rs.getString("address") +
				               "\nCondition: " + rs.getString("media_condition") +
				               "\nLast Update: " + rs.getString("last_update");
				copies.add(entry);
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return copies;
	}


}
