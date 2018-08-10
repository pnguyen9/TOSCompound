package model;

public class Character {

	private int id;
	private String name;

	public Character(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		boolean isEquals = false;

		if (obj instanceof Character) {
			isEquals = this.id == ((Character) obj).getId();
		}

		return isEquals;
	}

}
