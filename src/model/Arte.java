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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + (isCompound ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Arte other = (Arte) obj;
		if (id != other.id)
			return false;
		if (isCompound != other.isCompound)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
