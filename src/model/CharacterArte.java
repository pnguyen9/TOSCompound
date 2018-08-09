package model;

public class CharacterArte {

	private Character character;
	private Arte arte;

	public CharacterArte(Character character, Arte arte) {
		this.character = character;
		this.arte = arte;
	}

	public Character getCharacter() {
		return character;
	}

	public Arte getArte() {
		return arte;
	}

}
