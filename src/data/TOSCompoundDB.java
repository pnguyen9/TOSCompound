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

	private static boolean isBlankString(String string) {
		return string == null || string.trim().length() < 1;
	}

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
							"AND cca1.compound_id = cca2.compound_id;"; //
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

				// Sorting in ascending order
				if (firstArte.getId() > secondArte.getId()) {
					componentArtes.add(secondArte);
					componentArtes.add(firstArte);
				} else {
					componentArtes.add(firstArte);
					componentArtes.add(secondArte);
				}

				Arte compoundArte = new Arte(compoundArteId, compoundArteName, compoundArteIsCompound);

				compounds.add(new Compound(id, componentArtes, compoundArte));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return compounds;
	}

	public String[][] queryCompoundsForSelectedConditions(String selectedFirstCharacter, String selectedSecondCharacter,
			String selectedFirstCharacterArte, String selectedSecondCharacterArte, String selectedCompoundArte) {
		String[][] queryResults;
		List<String[]> results = new ArrayList<String[]>();

		try {
			Statement statement = connection.createStatement();
			String query = //
					"SELECT c1.name AS char_1, a1.name AS tech_1, " + //
							"c2.name AS char_2, a2.name AS tech_2, a3.name AS compound " + //
							"FROM Compound c " + //
							"JOIN Compound_Component_Arte cca1 ON c.id = cca1.compound_id " + //
							"JOIN Compound_Component_Arte cca2 ON c.id = cca2.compound_id " + //
							"JOIN Arte a1 ON cca1.arte_id = a1.id " + //
							"JOIN Arte a2 ON cca2.arte_id = a2.id " + //
							"JOIN Arte a3 ON C.compound_arte_id = a3.id " + //
							"JOIN Character_Arte ca1 ON ca1.arte_id = a1.id " + //
							"JOIN Character_Arte ca2 ON ca2.arte_id = a2.id " + //
							"JOIN Character c1 ON ca1.character_id = c1.id " + //
							"JOIN Character c2 ON ca2.character_id = c2.id " + //
							"WHERE cca1.id < cca2.id " + //
							"AND cca1.compound_id = cca2.compound_id " + //
							"AND c1.id != c2.id ";

			String selectedFirstCharacterCondition = //
					"AND ((c1.name LIKE '" + selectedFirstCharacter + "') " + //
							"OR (c2.name LIKE '" + selectedFirstCharacter + "')) ";//

			String selectedSecondCharacterCondition = //
					"AND ((c2.name LIKE '" + selectedSecondCharacter + "') " + //
							"OR (c1.name LIKE '" + selectedSecondCharacter + "')) ";//

			String selectedCharactersCondition = //
					"AND ((c1.name LIKE '" + selectedFirstCharacter + "' " + //
							"AND c2.name LIKE '" + selectedSecondCharacter + "') " + //
							"OR (c2.name LIKE '" + selectedFirstCharacter + "' " + //
							"AND c1.name LIKE '" + selectedSecondCharacter + "')) "; //

			String selectedFirstCharacterArteCondition = //
					"AND ((a1.name LIKE '" + selectedFirstCharacterArte + "') " + //
							"OR (a2.name LIKE '" + selectedFirstCharacterArte + "')) ";//

			String selectedSecondCharacterArteCondition = //
					"AND ((a2.name LIKE '" + selectedSecondCharacterArte + "') " + //
							"OR (a1.name LIKE '" + selectedSecondCharacterArte + "')) ";//

			String selectedArtesCondition = //
					"AND ((a1.name LIKE '" + selectedFirstCharacterArte + "' " + //
							"AND a2.name LIKE '" + selectedSecondCharacterArte + "') " + //
							"OR (a2.name LIKE '" + selectedFirstCharacterArte + "' " + //
							"AND a1.name LIKE '" + selectedSecondCharacterArte + "')) "; //

			String selectedCompoundArteCondition = //
					"AND a3.name LIKE '" + selectedCompoundArte + "' ";

			if (!(isBlankString(selectedFirstCharacter) || isBlankString(selectedSecondCharacter))) {
				query += selectedCharactersCondition;
			} else if (!isBlankString(selectedFirstCharacter) && isBlankString(selectedSecondCharacter)) {
				query += selectedFirstCharacterCondition;
			} else if (isBlankString(selectedFirstCharacter) && !isBlankString(selectedSecondCharacter)) {
				query += selectedSecondCharacterCondition;
			}

			if (!(isBlankString(selectedFirstCharacterArte) || isBlankString(selectedSecondCharacterArte))) {
				query += selectedArtesCondition;
			} else if (!isBlankString(selectedFirstCharacterArte) && isBlankString(selectedSecondCharacterArte)) {
				query += selectedFirstCharacterArteCondition;
			} else if (isBlankString(selectedFirstCharacterArte) && !isBlankString(selectedSecondCharacterArte)) {
				query += selectedSecondCharacterArteCondition;
			}

			if (!isBlankString(selectedCompoundArte)) {
				query += selectedCompoundArteCondition;
			}

			query += ";";

			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				String firstCharacterName = resultSet.getString("char_1");
				String firstcharacterArteName = resultSet.getString("tech_1");
				String secondCharacterName = resultSet.getString("char_2");
				String secondcharacterArteName = resultSet.getString("tech_2");
				String compoundName = resultSet.getString("compound");

				String[] result = { //
						firstCharacterName, //
						firstcharacterArteName, //
						secondCharacterName, //
						secondcharacterArteName, //
						compoundName //
				};

				results.add(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		queryResults = new String[results.size()][5];

		for (int i = 0; i < results.size(); ++i) {
			queryResults[i] = results.get(i);
		}

		return queryResults;
	}

	public Connection getConnection() {
		return connection;
	}

}
