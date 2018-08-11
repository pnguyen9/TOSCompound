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
			String query = "SELECT * FROM ARTE";
			ResultSet resultSet = statement.executeQuery(query);

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
			String query = "SELECT * FROM CHARACTER";
			ResultSet resultSet = statement.executeQuery(query);

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

	public List<CharacterArte> getCharacterArtes() {
		List<CharacterArte> characterArtes = new ArrayList<CharacterArte>();

		try {
			Statement statement = connection.createStatement();
			String query = //
					"SELECT c.id AS c_id, c.name AS c_name, " + //
							"a.id AS a_id, a.name AS a_name, a.is_compound AS a_is_compound " + //
							"FROM Character_Arte ca " + //
							"JOIN Character c ON ca.character_id = c.id " + //
							"JOIN Arte a ON ca.arte_id = a.id;"; //
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				int characterId = resultSet.getInt("c_id");
				String characterName = resultSet.getString("c_name");

				int arteId = resultSet.getInt("a_id");
				String arteName = resultSet.getString("a_name");
				boolean isCompound = resultSet.getInt("a_is_compound") == 1 ? true : false;

				Character character = new Character(characterId, characterName);
				Arte arte = new Arte(arteId, arteName, isCompound);

				characterArtes.add(new CharacterArte(character, arte));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return characterArtes;
	}

	public List<Compound> getCompounds() {
		List<Compound> compounds = new ArrayList<Compound>();

		try {
			Statement statement = connection.createStatement();
			String query = //
					"SELECT c.id AS id, a1.id AS a1_id, a1.name AS a1_name, a1.is_compound AS a1_is_compound, " + //
							"a2.id AS a2_id, a2.name AS a2_name, a2.is_compound AS a2_is_compound, " + //
							"a3.id AS a3_id, a3.name AS a3_name, a3.is_compound AS a3_is_compound " + //
							"FROM Compound c " + //
							"JOIN Compound_Component_Arte cca1 ON c.id = cca1.compound_id " + //
							"JOIN Compound_Component_Arte cca2 ON c.id = cca2.compound_id " + //
							"JOIN Arte a1 ON cca1.arte_id = a1.id " + //
							"JOIN Arte a2 ON cca2.arte_id = a2.id " + //
							"JOIN Arte a3 ON C.compound_arte_id = a3.id " + //
							"WHERE cca1.id < cca2.id " + //
							"AND cca1.compound_id = cca2.compound_id " + //
							"ORDER BY a3_id;"; //
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				int id = resultSet.getInt("id");

				int firstArteId = resultSet.getInt("a1_id");
				String firstArteName = resultSet.getString("a1_name");
				boolean firstArteIsCompound = resultSet.getInt("a1_is_compound") == 1 ? true : false;

				int secondArteId = resultSet.getInt("a2_id");
				String secondArteName = resultSet.getString("a2_name");
				boolean secondArteIsCompound = resultSet.getInt("a2_is_compound") == 1 ? true : false;

				int compoundArteId = resultSet.getInt("a3_id");
				String compoundArteName = resultSet.getString("a3_name");
				boolean compoundArteIsCompound = resultSet.getInt("a3_is_compound") == 1 ? true : false;

				List<Arte> componentArtes = new ArrayList<Arte>();

				Arte firstArte = new Arte(firstArteId, firstArteName, firstArteIsCompound);
				Arte secondArte = new Arte(secondArteId, secondArteName, secondArteIsCompound);

				componentArtes.add(firstArte);
				componentArtes.add(secondArte);

				Arte compoundArte = new Arte(compoundArteId, compoundArteName, compoundArteIsCompound);

				compounds.add(new Compound(id, componentArtes, compoundArte));
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
