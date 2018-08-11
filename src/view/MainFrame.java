package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Arte;
import model.Character;
import model.CharacterArte;
import model.Compound;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private JPanel mainPanel;

	private JPanel firstCharacterPanel;
	private JPanel secondCharacterPanel;
	private JPanel compoundArtePanel;

	private JLabel firstCharacterLabel;
	private JLabel secondCharacterLabel;
	private JLabel firstCharacterArteLabel;
	private JLabel secondCharacterArteLabel;
	private JLabel compoundArteLabel;

	private JComboBox<Character> firstCharacters;
	private JComboBox<Character> secondCharacters;

	private JComboBox<Arte> firstCharacterArtes;
	private JComboBox<Arte> secondCharacterArtes;

	private JComboBox<Arte> compoundArtes;

	private List<Arte> artes;
	private List<Character> characters;
	private List<Compound> compounds;

	private Map<Arte, List<Character>> charactersForArte;

	// For blank selection purpose
	private final static Arte BLANK_ARTE = new Arte(0, " ", false);
	private final static Arte BLANK_COMPOUND_ARTE = new Arte(0, " ", true);
	private final static Character BLANK_CHARACTER = new Character(0, " ");

	private boolean changeListenerIsDisabled;

	public MainFrame(List<Arte> artes, List<Character> characters, List<CharacterArte> characterArtes,
			List<Compound> compounds) {
		super("Tales of Symphonia - Compound artes");
		this.artes = artes;
		this.characters = characters;
		this.charactersForArte = new HashMap<Arte, List<Character>>();
		this.compounds = compounds;

		for (CharacterArte characterArte : characterArtes) {
			List<Character> arteCharacters = this.charactersForArte.get(characterArte.getArte());

			if (arteCharacters == null) {
				arteCharacters = new ArrayList<Character>();
			}

			arteCharacters.add(characterArte.getCharacter());
			this.charactersForArte.put(characterArte.getArte(), arteCharacters);
		}

		// For blank selection purpose
		this.artes.add(0, BLANK_ARTE);
		this.characters.add(0, BLANK_CHARACTER);

		this.changeListenerIsDisabled = false;
	}

	public void initComponents() {
		// TODO: Create a resource bundle to retrieve character label
		this.firstCharacterPanel = new JPanel();
		this.firstCharacterPanel.setLayout(new BoxLayout(this.firstCharacterPanel, BoxLayout.Y_AXIS));

		this.firstCharacterLabel = new JLabel("Personnage 1");
		this.firstCharacterLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

		this.firstCharacters = new JComboBox<Character>();
		this.firstCharacters.setRenderer(new NameListCellRenderer());
		this.firstCharacters.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!changeListenerIsDisabled) {
					loadFirstCharacterArtesList();
					loadSecondCharacterList();
					loadSecondCharacterArtesList();
					loadCompoundsList();
					pack();
				}
			}
		});

		this.firstCharacterArteLabel = new JLabel("Tech personnage 1");
		this.firstCharacterArteLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

		this.firstCharacterArtes = new JComboBox<Arte>();
		this.firstCharacterArtes.setRenderer(new NameListCellRenderer());
		this.firstCharacterArtes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!changeListenerIsDisabled) {
					loadCompoundsList();
					pack();
				}
			}
		});

		this.firstCharacterPanel.add(this.firstCharacterLabel);
		this.firstCharacterPanel.add(this.firstCharacters);
		this.firstCharacterPanel.add(this.firstCharacterArteLabel);
		this.firstCharacterPanel.add(this.firstCharacterArtes);

		this.secondCharacterPanel = new JPanel();
		this.secondCharacterPanel.setLayout(new BoxLayout(this.secondCharacterPanel, BoxLayout.Y_AXIS));

		this.secondCharacterLabel = new JLabel("Personnage 2");
		this.secondCharacterLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

		this.secondCharacters = new JComboBox<Character>();
		this.secondCharacters.setRenderer(new NameListCellRenderer());
		this.secondCharacters.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!changeListenerIsDisabled) {
					loadFirstCharacterList();
					loadFirstCharacterArtesList();
					loadSecondCharacterArtesList();
					loadCompoundsList();
					pack();
				}
			}
		});

		this.secondCharacterArteLabel = new JLabel("Tech personnage 2");
		this.secondCharacterArteLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

		this.secondCharacterArtes = new JComboBox<Arte>();
		this.secondCharacterArtes.setRenderer(new NameListCellRenderer());
		this.secondCharacterArtes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!changeListenerIsDisabled) {
					loadCompoundsList();
					pack();
				}
			}
		});

		this.secondCharacterPanel.add(this.secondCharacterLabel);
		this.secondCharacterPanel.add(this.secondCharacters);
		this.secondCharacterPanel.add(this.secondCharacterArteLabel);
		this.secondCharacterPanel.add(this.secondCharacterArtes);

		this.compoundArtePanel = new JPanel();
		this.compoundArtePanel.setLayout(new BoxLayout(this.compoundArtePanel, BoxLayout.Y_AXIS));

		this.compoundArteLabel = new JLabel("Attaque Unisson");
		this.compoundArteLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

		this.compoundArtes = new JComboBox<Arte>();
		this.compoundArtes.setRenderer(new NameListCellRenderer());

		this.compoundArtePanel.add(this.compoundArteLabel);
		this.compoundArtePanel.add(this.compoundArtes);

		this.mainPanel = new JPanel();

		this.mainPanel.add(this.firstCharacterPanel);
		this.mainPanel.add(this.secondCharacterPanel);
		this.mainPanel.add(this.compoundArtePanel);

		this.add(this.mainPanel);

		this.loadFirstCharacterList();
		this.loadFirstCharacterArtesList();

		this.loadSecondCharacterList();
		this.loadSecondCharacterArtesList();

		this.loadCompoundsList();

		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void loadFirstCharacterList() {
		this.changeListenerIsDisabled = true;

		Character selectedFirstCharacter = null;
		Character selectedSecondCharacter = null;

		if (this.firstCharacters.getSelectedItem() != null) {
			selectedFirstCharacter = (Character) this.firstCharacters.getSelectedItem();
		}

		if (this.secondCharacters.getSelectedItem() != null) {
			selectedSecondCharacter = (Character) this.secondCharacters.getSelectedItem();
		}

		this.firstCharacters.removeAllItems();

		for (Character character : characters) {
			if (character.getId() == 0) {
				this.firstCharacters.addItem(character);
				continue;
			}

			if (character != selectedSecondCharacter) {
				this.firstCharacters.addItem(character);
			}
		}

		if (selectedFirstCharacter != null) {
			this.firstCharacters.setSelectedItem(selectedFirstCharacter);
		}

		this.changeListenerIsDisabled = false;
	}

	private void loadFirstCharacterArtesList() {
		this.changeListenerIsDisabled = true;

		Character selectedFirstCharacter = null;
		Arte selectedFirstCharacterArte = null;

		if (this.firstCharacters.getSelectedItem() != null) {
			selectedFirstCharacter = (Character) this.firstCharacters.getSelectedItem();
		}

		if (this.firstCharacterArtes.getSelectedItem() != null) {
			selectedFirstCharacterArte = (Arte) this.firstCharacterArtes.getSelectedItem();
		}

		this.firstCharacterArtes.removeAllItems();

		for (Arte arte : artes) {
			if (arte.getId() == 0 && !arte.isCompound()) {
				this.firstCharacterArtes.addItem(arte);
				continue;
			}

			List<Character> arteCharacters = this.charactersForArte.get(arte);

			if (selectedFirstCharacter.equals(BLANK_CHARACTER)
					|| (arteCharacters != null && arteCharacters.contains(selectedFirstCharacter))) {
				if (!arte.isCompound()) {
					this.firstCharacterArtes.addItem(arte);
				}
			}
		}

		if (selectedFirstCharacterArte != null) {
			this.firstCharacterArtes.setSelectedItem(selectedFirstCharacterArte);
		}

		this.changeListenerIsDisabled = false;
	}

	private void loadSecondCharacterList() {
		this.changeListenerIsDisabled = true;

		Character selectedFirstCharacter = null;
		Character selectedSecondCharacter = null;

		if (this.firstCharacters.getSelectedItem() != null) {
			selectedFirstCharacter = (Character) this.firstCharacters.getSelectedItem();
		}

		if (this.secondCharacters.getSelectedItem() != null) {
			selectedSecondCharacter = (Character) this.secondCharacters.getSelectedItem();
		}

		this.secondCharacters.removeAllItems();

		for (Character character : characters) {
			if (character.getId() == 0) {
				this.secondCharacters.addItem(character);
				continue;
			}

			if (character != selectedFirstCharacter) {
				this.secondCharacters.addItem(character);
			}
		}

		if (selectedSecondCharacter != null) {
			this.secondCharacters.setSelectedItem(selectedSecondCharacter);
		}

		this.changeListenerIsDisabled = false;
	}

	private void loadSecondCharacterArtesList() {
		this.changeListenerIsDisabled = true;

		Character selectedSecondCharacter = null;
		Arte selectedSecondCharacterArte = null;

		if (this.secondCharacters.getSelectedItem() != null) {
			selectedSecondCharacter = (Character) this.secondCharacters.getSelectedItem();
		}

		if (this.secondCharacterArtes.getSelectedItem() != null) {
			selectedSecondCharacterArte = (Arte) this.secondCharacterArtes.getSelectedItem();
		}

		this.secondCharacterArtes.removeAllItems();

		for (Arte arte : artes) {
			if (arte.getId() == 0 && !arte.isCompound()) {
				this.secondCharacterArtes.addItem(arte);
				continue;
			}

			List<Character> arteCharacters = this.charactersForArte.get(arte);

			if (selectedSecondCharacter.equals(BLANK_CHARACTER)
					|| (arteCharacters != null && arteCharacters.contains(selectedSecondCharacter))) {
				if (!arte.isCompound()) {
					this.secondCharacterArtes.addItem(arte);
				}
			}
		}

		if (selectedSecondCharacterArte != null) {
			this.secondCharacterArtes.setSelectedItem(selectedSecondCharacterArte);
		}

		this.changeListenerIsDisabled = false;
	}

	private void loadCompoundsList() {
		this.changeListenerIsDisabled = true;

		this.compoundArtes.removeAllItems();

		// TODO: Get compounds by characters, and show artes for a selected compound

		Arte selectedFirstCharacterArte = (Arte) this.firstCharacterArtes.getSelectedItem();
		Arte selectedSecondCharacterArte = (Arte) this.secondCharacterArtes.getSelectedItem();

		if (!(selectedFirstCharacterArte.equals(BLANK_ARTE) || selectedSecondCharacterArte.equals(BLANK_ARTE))) {
			List<Arte> selectedArtes = new ArrayList<Arte>();

			// Sorting in ascending order
			if (selectedFirstCharacterArte.getId() > selectedSecondCharacterArte.getId()) {
				selectedArtes.add(selectedSecondCharacterArte);
				selectedArtes.add(selectedFirstCharacterArte);
			} else {
				selectedArtes.add(selectedFirstCharacterArte);
				selectedArtes.add(selectedSecondCharacterArte);
			}

			for (Compound compound : compounds) {
				// Since both arrays are sorted, the comparison works
				if (selectedArtes.equals(compound.getComponentArtes())) {
					this.compoundArtes.addItem(compound.getCompoundArte());
				}
			}
		}

		if (this.compoundArtes.getItemCount() < 1) {
			this.compoundArtes.addItem(BLANK_COMPOUND_ARTE);
		}

		this.changeListenerIsDisabled = false;
	}

}
