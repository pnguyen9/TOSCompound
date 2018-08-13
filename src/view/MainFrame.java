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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import data.TOSCompoundDB;
import model.Arte;
import model.Character;
import model.CharacterArte;
import model.Compound;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private TOSCompoundDB db;

	// For blank selection purpose
	private final static Arte BLANK_ARTE = new Arte(0, " ", false);
	private final static Arte BLANK_COMPOUND_ARTE = new Arte(0, " ", true);
	private final static Character BLANK_CHARACTER = new Character(0, " ");

	private static String[] RESULT_TABLE_COLUMNS = { //
			"Personnage 1", "Tech 1", "Personnage 2", "Tech 2", "Tech Unisson" //
	}; //

	private JPanel mainPanel;

	private JPanel selectionPanel;
	private JPanel resultPanel;

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

	private JTable resultTable;
	private DefaultTableModel resultTableModel;
	private JScrollPane resultTableScrollPane;

	private List<Arte> artes;
	private List<Character> characters;
	private List<Compound> compounds;

	private Map<Arte, List<Character>> charactersForArte;

	private Character selectedFirstCharacter = BLANK_CHARACTER;
	private Arte selectedFirstCharacterArte = BLANK_ARTE;

	private Character selectedSecondCharacter = BLANK_CHARACTER;
	private Arte selectedSecondCharacterArte = BLANK_ARTE;

	private Arte selectedCompoundArte = BLANK_COMPOUND_ARTE;

	private boolean changeListenerIsDisabled;

	public MainFrame(TOSCompoundDB db, List<Arte> artes, List<Character> characters, List<CharacterArte> characterArtes,
			List<Compound> compounds) {
		super("Tales of Symphonia - Compound artes");
		this.db = db;
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
		this.artes.add(0, BLANK_COMPOUND_ARTE);
		this.characters.add(0, BLANK_CHARACTER);

		this.changeListenerIsDisabled = false;
	}

	public void initComponents() {
		// TODO: Create a resource bundle to retrieve character label
		this.firstCharacterPanel = new JPanel();
		this.firstCharacterPanel.setLayout(new BoxLayout(this.firstCharacterPanel, BoxLayout.Y_AXIS));

		this.firstCharacterLabel = new JLabel(RESULT_TABLE_COLUMNS[0]);
		this.firstCharacterLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

		this.firstCharacters = new JComboBox<Character>();
		this.firstCharacters.setRenderer(new NameListCellRenderer());
		this.firstCharacters.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!changeListenerIsDisabled) {
					selectedFirstCharacter = (Character) firstCharacters.getSelectedItem();

					loadFirstCharacterArtesList();
					loadSecondCharacterList();
					loadSecondCharacterArtesList();

					updateResultTable();

					pack();
				}
			}
		});

		this.firstCharacterArteLabel = new JLabel(RESULT_TABLE_COLUMNS[1]);
		this.firstCharacterArteLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

		this.firstCharacterArtes = new JComboBox<Arte>();
		this.firstCharacterArtes.setRenderer(new NameListCellRenderer());
		this.firstCharacterArtes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!changeListenerIsDisabled) {
					selectedFirstCharacterArte = (Arte) firstCharacterArtes.getSelectedItem();
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

		this.secondCharacterLabel = new JLabel(RESULT_TABLE_COLUMNS[2]);
		this.secondCharacterLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

		this.secondCharacters = new JComboBox<Character>();
		this.secondCharacters.setRenderer(new NameListCellRenderer());
		this.secondCharacters.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!changeListenerIsDisabled) {
					selectedSecondCharacter = (Character) secondCharacters.getSelectedItem();

					loadFirstCharacterList();
					loadFirstCharacterArtesList();
					loadSecondCharacterArtesList();

					updateResultTable();

					pack();
				}
			}
		});

		this.secondCharacterArteLabel = new JLabel(RESULT_TABLE_COLUMNS[3]);
		this.secondCharacterArteLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

		this.secondCharacterArtes = new JComboBox<Arte>();
		this.secondCharacterArtes.setRenderer(new NameListCellRenderer());
		this.secondCharacterArtes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!changeListenerIsDisabled) {
					selectedSecondCharacterArte = (Arte) secondCharacterArtes.getSelectedItem();
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
		this.compoundArtes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedCompoundArte = (Arte) compoundArtes.getSelectedItem();
			}
		});

		this.compoundArtePanel.add(this.compoundArteLabel);
		this.compoundArtePanel.add(this.compoundArtes);

		this.selectionPanel = new JPanel();

		this.selectionPanel.add(this.firstCharacterPanel);
		this.selectionPanel.add(this.secondCharacterPanel);
		this.selectionPanel.add(this.compoundArtePanel);

		this.resultPanel = new JPanel();

		this.resultTableModel = new DefaultTableModel(RESULT_TABLE_COLUMNS, 0);
		this.resultTable = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		this.resultTable.setModel(this.resultTableModel);
		this.resultTableScrollPane = new JScrollPane(this.resultTable);
		this.resultPanel.add(this.resultTableScrollPane);
		this.updateResultTable();

		this.mainPanel = new JPanel();
		this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS));

		this.mainPanel.add(selectionPanel);
		this.mainPanel.add(resultPanel);

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

		if (this.firstCharacters.getSelectedItem() != null) {
			this.selectedFirstCharacter = (Character) this.firstCharacters.getSelectedItem();
		}

		if (this.secondCharacters.getSelectedItem() != null) {
			this.selectedSecondCharacter = (Character) this.secondCharacters.getSelectedItem();
		}

		this.firstCharacters.removeAllItems();

		for (Character character : characters) {
			if (character.getId() == 0) {
				this.firstCharacters.addItem(character);
				continue;
			}

			if (character != this.selectedSecondCharacter) {
				this.firstCharacters.addItem(character);
			}
		}

		if (this.selectedFirstCharacter != null) {
			this.firstCharacters.setSelectedItem(this.selectedFirstCharacter);
		}

		this.changeListenerIsDisabled = false;
	}

	private void loadFirstCharacterArtesList() {
		this.changeListenerIsDisabled = true;

		if (this.firstCharacters.getSelectedItem() != null) {
			this.selectedFirstCharacter = (Character) this.firstCharacters.getSelectedItem();
		}

		if (this.firstCharacterArtes.getSelectedItem() != null) {
			this.selectedFirstCharacterArte = (Arte) this.firstCharacterArtes.getSelectedItem();
		}

		this.firstCharacterArtes.removeAllItems();

		for (Arte arte : artes) {
			if (arte.getId() == 0 && !arte.isCompound()) {
				this.firstCharacterArtes.addItem(arte);
				continue;
			}

			List<Character> arteCharacters = this.charactersForArte.get(arte);

			if (this.selectedFirstCharacter.equals(BLANK_CHARACTER)
					|| (arteCharacters != null && arteCharacters.contains(this.selectedFirstCharacter))) {
				if (!arte.isCompound()) {
					this.firstCharacterArtes.addItem(arte);
				}
			}
		}

		if (this.selectedFirstCharacterArte != null) {
			this.firstCharacterArtes.setSelectedItem(this.selectedFirstCharacterArte);
		}

		this.changeListenerIsDisabled = false;
	}

	private void loadSecondCharacterList() {
		this.changeListenerIsDisabled = true;

		if (this.firstCharacters.getSelectedItem() != null) {
			this.selectedFirstCharacter = (Character) this.firstCharacters.getSelectedItem();
		}

		if (this.secondCharacters.getSelectedItem() != null) {
			this.selectedSecondCharacter = (Character) this.secondCharacters.getSelectedItem();
		}

		this.secondCharacters.removeAllItems();

		for (Character character : characters) {
			if (character.getId() == 0) {
				this.secondCharacters.addItem(character);
				continue;
			}

			if (character != this.selectedFirstCharacter) {
				this.secondCharacters.addItem(character);
			}
		}

		if (this.selectedSecondCharacter != null) {
			this.secondCharacters.setSelectedItem(this.selectedSecondCharacter);
		}

		this.changeListenerIsDisabled = false;
	}

	private void loadSecondCharacterArtesList() {
		this.changeListenerIsDisabled = true;

		if (this.secondCharacters.getSelectedItem() != null) {
			this.selectedSecondCharacter = (Character) this.secondCharacters.getSelectedItem();
		}

		if (this.secondCharacterArtes.getSelectedItem() != null) {
			this.selectedSecondCharacterArte = (Arte) this.secondCharacterArtes.getSelectedItem();
		}

		this.secondCharacterArtes.removeAllItems();

		for (Arte arte : artes) {
			if (arte.getId() == 0 && !arte.isCompound()) {
				this.secondCharacterArtes.addItem(arte);
				continue;
			}

			List<Character> arteCharacters = this.charactersForArte.get(arte);

			if (this.selectedSecondCharacter.equals(BLANK_CHARACTER)
					|| (arteCharacters != null && arteCharacters.contains(this.selectedSecondCharacter))) {
				if (!arte.isCompound()) {
					this.secondCharacterArtes.addItem(arte);
				}
			}
		}

		if (this.selectedSecondCharacterArte != null) {
			this.secondCharacterArtes.setSelectedItem(this.selectedSecondCharacterArte);
		}

		this.changeListenerIsDisabled = false;
	}

	private void loadCompoundsList() {
		this.changeListenerIsDisabled = true;

		for (Arte arte : artes) {
			if (arte.isCompound()) {
				this.compoundArtes.addItem(arte);
			}
		}

		this.changeListenerIsDisabled = false;
	}

	private void updateResultTable() {
		this.resultTableModel.setRowCount(0);

		String[][] queryResults = db.queryCompoundsForSelectedConditions(this.selectedFirstCharacter.getName(),
				this.selectedSecondCharacter.getName());

		for (String[] row : queryResults) {
			this.resultTableModel.addRow(row);
		}
	}

}
