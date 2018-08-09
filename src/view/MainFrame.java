package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Arte;
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
	private List<CharacterArte> characterArtes;
	private List<Compound> compounds;

	public MainFrame(List<Arte> artes, List<Character> characters, List<CharacterArte> characterArtes,
			List<Compound> compounds) {
		super("TSCompound");
		this.artes = artes;
		this.characters = characters;
		this.characterArtes = characterArtes;
		this.compounds = compounds;
	}

	public void initComponents() {
		// TODO: Create a resource bundle to retrieve character label
		this.firstCharacterPanel = new JPanel();
		this.firstCharacterPanel.setLayout(new BoxLayout(this.firstCharacterPanel, BoxLayout.Y_AXIS));

		this.firstCharacterLabel = new JLabel("Personnage 1");

		this.firstCharacters = new JComboBox<Character>(this.characters.toArray(new Character[0]));
		this.firstCharacters.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO
			}
		});

		this.firstCharacterArteLabel = new JLabel("Tech personnage 1");

		this.firstCharacterArtes = new JComboBox<Arte>(this.artes.toArray(new Arte[0]));
		this.firstCharacterArtes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO
			}
		});

		this.firstCharacterPanel.add(this.firstCharacterLabel);
		this.firstCharacterPanel.add(this.firstCharacters);
		this.firstCharacterPanel.add(this.firstCharacterArteLabel);
		this.firstCharacterPanel.add(this.firstCharacterArtes);

		this.secondCharacterPanel = new JPanel();
		this.secondCharacterPanel.setLayout(new BoxLayout(this.secondCharacterPanel, BoxLayout.Y_AXIS));

		this.secondCharacterLabel = new JLabel("Personnage 2");

		this.secondCharacters = new JComboBox<Character>(this.characters.toArray(new Character[0]));
		this.secondCharacters.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO
			}
		});

		this.secondCharacterArteLabel = new JLabel("Tech personnage 2");

		this.secondCharacterArtes = new JComboBox<Arte>(this.artes.toArray(new Arte[0]));
		this.secondCharacterArtes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO
			}
		});

		this.secondCharacterPanel.add(this.secondCharacterLabel);
		this.secondCharacterPanel.add(this.secondCharacters);
		this.secondCharacterPanel.add(this.secondCharacterArteLabel);
		this.secondCharacterPanel.add(this.secondCharacterArtes);

		this.compoundArtePanel = new JPanel();
		this.compoundArtePanel.setLayout(new BoxLayout(this.compoundArtePanel, BoxLayout.Y_AXIS));

		this.compoundArteLabel = new JLabel("Attaque Unisson");

		this.compoundArtes = new JComboBox<Arte>();
		this.compoundArtes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO
			}
		});
		for (Arte arte : artes) {
			if (arte.isCompound()) {
				this.compoundArtes.addItem(arte);
			}
		}

		this.compoundArtePanel.add(this.compoundArteLabel);
		this.compoundArtePanel.add(this.compoundArtes);

		this.mainPanel = new JPanel();

		this.mainPanel.add(this.firstCharacterPanel);
		this.mainPanel.add(this.secondCharacterPanel);
		this.mainPanel.add(this.compoundArtePanel);

		this.add(this.mainPanel);

		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
