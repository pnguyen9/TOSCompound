package main;

import java.util.ArrayList;
import java.util.List;

import data.TOSCompoundDB;
import model.Arte;
import model.CharacterArte;
import model.Compound;
import view.MainFrame;

public class TOSCompound {

	public static void main(String[] args) {
		TOSCompoundDB db = new TOSCompoundDB();

		List<Arte> artes = new ArrayList<Arte>();
		List<Character> characters = new ArrayList<Character>();
		List<CharacterArte> characterArtes = new ArrayList<CharacterArte>();
		List<Compound> compounds = new ArrayList<Compound>();

		MainFrame mainFrame = new MainFrame(artes, characters, characterArtes, compounds);
		mainFrame.initComponents();
	}

}
