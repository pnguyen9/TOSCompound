package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

			String databasePath = "./TOSCompoundArte.db";

			connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath);

			System.out.println("Connection established with local database.");

			this.initDatabase();
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

	public void initDatabase() {
		this.createArtes();
		this.createCharacters();
		this.createCharacterArtes();
		this.createCompounds();
		this.createCompoundComponentArtes();

		System.out.println("Database initialised");
	}

	private void createArtes() {
		try {
			Statement statement = connection.createStatement();

			String query = //
					"CREATE TABLE IF NOT EXISTS `Arte` (\r\n" + //
							"	`id`	INTEGER PRIMARY KEY AUTOINCREMENT,\r\n" + //
							"	`name`	TEXT NOT NULL,\r\n" + //
							"	`is_compound`	INTEGER\r\n" + //
							");\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (1,'Anneau cyclonique',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (2,'Anneau tourbillonant',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (3,'Archi-vent',1);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (4,'Bête',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (5,'Bête chasseuse',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (6,'Bête enragée',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (7,'Bête féroce',1);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (8,'Canon lance de lumière',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (9,'Cyclone',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (10,'Dévastation',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (11,'Dévastation Boing',1);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (12,'Dévastation limitée',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (13,'Dévastation massive',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (14,'Double lame du tigre',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (15,'Double punition',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (16,'Elan terrestre',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (17,'Eruption',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (18,'Estocade de la croix',1);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (19,'Estocade du mirage',1);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (20,'Estocade ouragan',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (21,'Estocade sonique',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (22,'Estocade supersonique',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (23,'Explosion',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (24,'Explosion photonique',1);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (25,'Faisceaux',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (26,'Foudre',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (27,'Foudre punitive',1);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (28,'Indignation',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (29,'Lame Boing',1);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (30,'Lame de fond',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (31,'Lame de tigre',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (32,'Lame de tigre démoniaque',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (33,'Lame de tigre L.',1);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (34,'Lame de tigre profonde',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (35,'Lame de tigre T.',1);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (36,'Lame de tonnerre',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (37,'Lame foudroyante',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (38,'Lame plasma',1);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (39,'Lance Boing',1);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (40,'Lance de flamme',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (41,'Lance de lumière',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (42,'Marteau Boing',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (43,'Marteau Boing Boing',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (44,'Mjollnir',1);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (45,'Photon',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (46,'Pluie de marteaux',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (47,'Pluie d''épées : alpha',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (48,'Pluie d''épées soniques',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (49,'Pluie d''étoiles',1);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (50,'Prisme d''étoiles',1);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (51,'Puissante estocade',1);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (52,'Punition',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (53,'Punition finale',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (54,'Punition vrillée',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (55,'Pyro-enfer',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (56,'Rage de tigre',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (57,'Ruée tourbillonante',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (58,'Sceau mirage',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (59,'Sceau mirage absolu',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (60,'Sceau mirage limité',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (61,'Sceau puissant',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (62,'Sceau puissant absolu',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (63,'Sceau puissant limité',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (64,'Sceau serpent',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (65,'Sceau serpent absolu',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (66,'Sceau serpent limité',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (67,'Sombre serpent',1);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (68,'Super lame foudroyante',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (69,'Super lance de lumière',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (70,'Tempête',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (71,'Tempête oméga',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (72,'Tempête photonique',1);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (73,'Tempête psy',0);\r\n" + //
							"INSERT INTO `Arte` (id,name,is_compound) VALUES (74,'Vague d''étincelles',0);";

			statement.executeUpdate(query);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void createCharacters() {
		try {
			Statement statement = connection.createStatement();

			String query = //
					"CREATE TABLE IF NOT EXISTS `Character` (\r\n" + //
							"	`id`	INTEGER PRIMARY KEY AUTOINCREMENT,\r\n" + //
							"	`name`	TEXT\r\n" + //
							");\r\n" + //
							"INSERT INTO `Character` (id,name) VALUES (1,'Lloyd Irving');\r\n" + //
							"INSERT INTO `Character` (id,name) VALUES (2,'Colette Brunel');\r\n" + //
							"INSERT INTO `Character` (id,name) VALUES (3,'Genis Sage');\r\n" + //
							"INSERT INTO `Character` (id,name) VALUES (4,'Kratos Aurion');\r\n" + //
							"INSERT INTO `Character` (id,name) VALUES (5,'Raine Sage');\r\n" + //
							"INSERT INTO `Character` (id,name) VALUES (6,'Sheena Fujibayashi');\r\n" + //
							"INSERT INTO `Character` (id,name) VALUES (7,'Zelos Wilder');\r\n" + //
							"INSERT INTO `Character` (id,name) VALUES (8,'Presea Combatir');\r\n" + //
							"INSERT INTO `Character` (id,name) VALUES (9,'Regal Bryant');";

			statement.executeUpdate(query);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void createCharacterArtes() {
		try {
			Statement statement = connection.createStatement();

			String query = //
					"CREATE TABLE IF NOT EXISTS `Character_Arte` (\r\n" + //
							"	`character_id`	INTEGER,\r\n" + //
							"	`arte_id`	INTEGER,\r\n" + //
							"	PRIMARY KEY(`character_id`,`arte_id`)\r\n" + //
							");\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (2,1);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (2,2);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (1,4);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (1,5);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (1,6);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (4,8);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (7,8);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (3,9);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (8,10);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (8,12);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (8,13);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (1,14);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (8,15);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (3,16);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (3,17);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (4,17);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (7,17);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (1,20);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (4,20);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (7,20);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (1,21);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (4,21);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (7,21);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (1,22);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (4,22);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (7,22);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (3,23);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (5,25);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (3,26);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (4,26);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (7,26);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (3,28);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (3,30);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (1,31);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (1,32);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (1,34);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (3,36);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (4,36);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (7,36);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (4,37);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (7,37);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (3,40);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (4,41);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (7,41);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (2,42);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (2,43);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (5,45);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (2,46);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (1,47);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (1,48);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (8,52);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (8,53);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (8,54);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (4,55);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (7,55);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (1,56);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (2,57);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (6,58);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (6,59);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (6,60);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (6,61);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (6,62);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (6,63);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (6,64);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (6,65);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (6,66);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (4,68);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (7,68);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (4,69);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (7,69);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (1,70);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (1,71);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (1,73);\r\n" + //
							"INSERT INTO `Character_Arte` (character_id,arte_id) VALUES (3,74);";

			statement.executeUpdate(query);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void createCompounds() {
		try {
			Statement statement = connection.createStatement();

			String query = //
					"CREATE TABLE IF NOT EXISTS `Compound` (\r\n" + //
							"	`id`	INTEGER PRIMARY KEY AUTOINCREMENT,\r\n" + //
							"	`compound_arte_id`	INTEGER\r\n" + //
							");\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (1,3);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (2,3);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (3,3);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (4,3);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (5,7);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (6,7);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (7,7);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (8,7);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (9,11);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (10,11);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (11,11);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (12,11);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (13,11);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (14,18);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (15,18);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (16,18);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (17,19);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (18,19);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (19,19);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (20,24);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (21,24);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (22,24);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (23,27);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (24,27);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (25,27);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (26,27);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (27,29);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (28,29);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (29,29);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (30,29);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (31,33);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (32,33);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (33,33);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (34,33);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (35,33);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (36,33);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (37,35);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (38,35);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (39,35);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (40,35);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (41,35);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (42,38);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (43,38);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (44,39);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (45,39);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (46,39);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (47,39);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (48,39);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (49,44);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (50,44);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (51,44);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (52,44);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (53,49);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (54,49);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (55,50);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (56,50);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (57,50);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (58,50);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (59,50);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (60,51);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (61,51);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (62,51);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (63,67);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (64,67);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (65,67);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (66,72);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (67,72);\r\n" + //
							"INSERT INTO `Compound` (id,compound_arte_id) VALUES (68,72);";

			statement.executeUpdate(query);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void createCompoundComponentArtes() {
		try {
			Statement statement = connection.createStatement();

			String query = //
					"CREATE TABLE IF NOT EXISTS `Compound_Component_Arte` (\r\n" + //
							"	`id`	INTEGER PRIMARY KEY AUTOINCREMENT,\r\n" + //
							"	`compound_id`	INTEGER,\r\n" + //
							"	`arte_id`	INTEGER\r\n" + //
							");\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (1,1,52);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (2,2,15);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (3,3,53);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (4,4,54);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (5,5,4);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (6,6,4);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (7,7,6);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (8,8,5);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (9,9,10);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (10,10,12);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (11,11,13);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (12,12,12);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (13,13,13);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (14,14,20);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (15,15,21);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (16,16,22);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (17,17,58);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (18,18,60);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (19,19,59);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (20,20,45);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (21,21,45);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (22,22,45);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (23,23,52);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (24,24,15);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (25,25,53);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (26,26,54);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (27,27,31);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (28,28,56);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (29,29,34);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (30,30,14);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (31,31,31);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (32,32,56);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (33,33,56);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (34,34,34);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (35,35,34);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (36,36,14);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (37,37,31);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (38,38,56);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (39,39,34);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (40,40,14);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (41,41,32);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (42,42,45);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (43,43,45);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (44,44,41);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (45,45,69);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (46,46,8);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (47,47,69);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (48,48,8);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (49,49,42);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (50,50,43);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (51,51,43);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (52,52,46);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (53,53,46);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (54,54,46);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (55,55,25);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (56,56,25);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (57,57,25);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (58,58,25);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (59,59,25);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (60,60,62);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (61,61,61);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (62,62,63);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (63,63,65);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (64,64,66);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (65,65,64);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (66,66,45);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (67,67,45);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (68,68,45);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (69,1,55);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (70,2,55);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (71,3,55);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (72,4,55);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (73,5,17);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (74,6,40);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (75,7,23);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (76,8,23);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (77,9,42);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (78,10,43);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (79,11,43);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (80,12,46);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (81,13,46);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (82,14,20);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (83,15,21);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (84,16,22);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (85,17,21);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (86,18,20);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (87,19,22);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (88,20,2);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (89,21,1);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (90,22,57);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (91,23,26);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (92,24,36);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (93,25,74);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (94,26,28);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (95,27,42);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (96,28,43);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (97,29,43);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (98,30,42);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (99,31,26);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (100,32,36);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (101,33,74);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (102,34,36);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (103,35,74);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (104,36,28);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (105,37,37);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (106,38,37);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (107,39,37);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (108,40,68);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (109,41,68);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (110,42,37);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (111,43,68);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (112,44,42);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (113,45,43);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (114,46,43);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (115,47,46);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (116,48,46);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (117,49,26);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (118,50,36);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (119,51,74);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (120,52,28);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (121,53,47);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (122,54,48);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (123,55,23);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (124,56,30);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (125,57,9);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (126,58,28);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (127,59,16);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (128,60,22);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (129,61,21);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (130,62,20);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (131,63,22);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (132,64,20);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (133,65,21);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (134,66,70);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (135,67,71);\r\n" + //
							"INSERT INTO `Compound_Component_Arte` (id,compound_id,arte_id) VALUES (136,68,73);";

			statement.executeUpdate(query);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
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

			statement.close();
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

			statement.close();
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

			statement.close();
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

			statement.close();
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
					"AND ((a1.name LIKE '" + this.escapeQuote(selectedFirstCharacterArte) + "') " + //
							"OR (a2.name LIKE '" + this.escapeQuote(selectedFirstCharacterArte) + "')) ";//

			String selectedSecondCharacterArteCondition = //
					"AND ((a2.name LIKE '" + this.escapeQuote(selectedSecondCharacterArte) + "') " + //
							"OR (a1.name LIKE '" + this.escapeQuote(selectedSecondCharacterArte) + "')) ";//

			String selectedArtesCondition = //
					"AND ((a1.name LIKE '" + this.escapeQuote(selectedFirstCharacterArte) + "' " + //
							"AND a2.name LIKE '" + this.escapeQuote(selectedSecondCharacterArte) + "') " + //
							"OR (a2.name LIKE '" + this.escapeQuote(selectedFirstCharacterArte) + "' " + //
							"AND a1.name LIKE '" + this.escapeQuote(selectedSecondCharacterArte) + "')) "; //

			String selectedCompoundArteCondition = //
					"AND a3.name LIKE '" + this.escapeQuote(selectedCompoundArte) + "' ";

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

			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		queryResults = new String[results.size()][5];

		for (int i = 0; i < results.size(); ++i) {
			queryResults[i] = results.get(i);
		}

		return queryResults;
	}

	// Ugly fix for parameter with a quote, because preparedStatement can't work
	// with the way it was done
	private String escapeQuote(String parameter) {
		if (parameter.contains("'")) {
			return parameter.replace("'", "''");
		}
		return parameter;
	}

	public Connection getConnection() {
		return connection;
	}

}
