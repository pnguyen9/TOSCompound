package model;

public class Arte {

	private int id;
	private String name;
	private boolean isCompound;

	public Arte(int id, String name, boolean isCompound) {
		this.id = id;
		this.name = name;
		this.isCompound = isCompound;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public boolean isCompound() {
		return isCompound;
	}

	@Override
	public boolean equals(Object obj) {
		boolean isEquals = false;

		if (obj instanceof Arte) {
			isEquals = this.id == ((Arte) obj).getId();
		}

		return isEquals;
	}

}
