package data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Arte;
import model.Character;
import model.CharacterArte;
import model.Compound;

public class TOSCompoundDB {

	private Connection connection = null;

	public TOSCompoundDB() {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:TOSCompoundArte.db");
			System.out.println("Connection established with local database.");
		} catch (Exception e) {
			System.err.println("An error occurred in TOSCompoundDB. Closing database connection.");
			e.printStackTrace();

			try {
				connection.close();
			} catch (Exception e2) {
				System.err.println("An error occurred while closing database connection.");
				e.printStackTrace();
			}
		}
	}

	public List<Arte> getArtes() {
		List<Arte> artes = new ArrayList<Arte>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM ARTE");

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				boolean isCompound = resultSet.getInt("is_compound") == 0 ? false : true;

				artes.add(new Arte(id, name, isCompound));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return artes;
	}

	public List<Character> getCharacters() {
		List<Character> characters = new ArrayList<Character>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM CHARACTER");

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");

				characters.add(new Character(id, name));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return characters;
	}

	// Maybe an ORM wouldn't have been a bad idea after all...

	public List<CharacterArte> getCharacterArtes(List<Arte> artes, List<Character> characters) {
		List<CharacterArte> characterArtes = new ArrayList<CharacterArte>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM CHARACTER_ARTE");

			while (resultSet.next()) {
				int characterId = resultSet.getInt("character_id");
				int arteId = resultSet.getInt("arte_id");

				Character character = null;
				Arte arte = null;

				for (Character tempChar : characters) {
					if (tempChar.getId() == characterId) {
						character = tempChar;
						break;
					}
				}

				for (Arte tempArte : artes) {
					if (tempArte.getId() == arteId) {
						arte = tempArte;
						break;
					}
				}

				characterArtes.add(new CharacterArte(character, arte));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return characterArtes;
	}

	public List<Compound> getCompounds(List<Arte> artes) {
		List<Compound> compounds = new ArrayList<Compound>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM COMPOUND");

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				int firstArteId = resultSet.getInt("first_arte_id");
				int secondArteId = resultSet.getInt("second_arte_id");
				int compoundArteId = resultSet.getInt("compound_arte_id");

				Arte firstArte = null;
				Arte secondArte = null;
				Arte compoundArte = null;

				for (Arte arte : artes) {
					if (arte.getId() == firstArteId) {
						firstArte = arte;
						break;
					}
				}

				for (Arte arte : artes) {
					if (arte.getId() == secondArteId) {
						secondArte = arte;
						break;
					}
				}

				for (Arte arte : artes) {
					if (arte.getId() == compoundArteId) {
						compoundArte = arte;
						break;
					}
				}

				compounds.add(new Compound(id, firstArte, secondArte, compoundArte));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return compounds;
	}

	public Connection getConnection() {
		return connection;
	}

}
