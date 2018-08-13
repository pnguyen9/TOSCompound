package main;

import java.util.List;

import data.TOSCompoundDB;
import model.Arte;
import model.Character;
import model.CharacterArte;
import view.MainFrame;

public class TOSCompound {

	public static void main(String[] args) {
		TOSCompoundDB db = new TOSCompoundDB();

		List<Arte> artes = db.getArtes();
		List<Character> characters = db.getCharacters();
		List<CharacterArte> characterArtes = db.getCharacterArtes();

		MainFrame mainFrame = new MainFrame(db, artes, characters, characterArtes);
		mainFrame.initComponents();
	}

}
