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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arte == null) ? 0 : arte.hashCode());
		result = prime * result + ((character == null) ? 0 : character.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CharacterArte other = (CharacterArte) obj;
		if (arte == null) {
			if (other.arte != null)
				return false;
		} else if (!arte.equals(other.arte))
			return false;
		if (character == null) {
			if (other.character != null)
				return false;
		} else if (!character.equals(other.character))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CharacterArte [character=" + character + ", arte=" + arte + "]";
	}

}
