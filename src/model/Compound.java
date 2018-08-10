package model;

public class Compound {

	private int id;
	private Arte firstArte;
	private Arte secondArte;
	private Arte compoundArte;

	public Compound(int id, Arte firstArte, Arte secondArte, Arte compoundArte) {
		this.id = id;
		this.firstArte = firstArte;
		this.secondArte = secondArte;
		this.compoundArte = compoundArte;
	}

	public int getId() {
		return id;
	}

	public Arte getFirstArte() {
		return firstArte;
	}

	public Arte getSecondArte() {
		return secondArte;
	}

	public Arte getCompoundArte() {
		return compoundArte;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((compoundArte == null) ? 0 : compoundArte.hashCode());
		result = prime * result + ((firstArte == null) ? 0 : firstArte.hashCode());
		result = prime * result + id;
		result = prime * result + ((secondArte == null) ? 0 : secondArte.hashCode());
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
		Compound other = (Compound) obj;
		if (compoundArte == null) {
			if (other.compoundArte != null)
				return false;
		} else if (!compoundArte.equals(other.compoundArte))
			return false;
		if (firstArte == null) {
			if (other.firstArte != null)
				return false;
		} else if (!firstArte.equals(other.firstArte))
			return false;
		if (id != other.id)
			return false;
		if (secondArte == null) {
			if (other.secondArte != null)
				return false;
		} else if (!secondArte.equals(other.secondArte))
			return false;
		return true;
	}

}