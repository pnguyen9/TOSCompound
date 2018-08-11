package model;

import java.util.List;

public class Compound {

	private int id;
	private List<Arte> componentArtes;
	private Arte compoundArte;

	public Compound(int id, List<Arte> componentArtes, Arte compoundArte) {
		this.id = id;
		this.componentArtes = componentArtes;
		this.compoundArte = compoundArte;
	}

	public int getId() {
		return id;
	}

	public List<Arte> getComponentArtes() {
		return componentArtes;
	}

	public Arte getCompoundArte() {
		return compoundArte;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((componentArtes == null) ? 0 : componentArtes.hashCode());
		result = prime * result + ((compoundArte == null) ? 0 : compoundArte.hashCode());
		result = prime * result + id;
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
		if (componentArtes == null) {
			if (other.componentArtes != null)
				return false;
		} else if (!componentArtes.equals(other.componentArtes))
			return false;
		if (compoundArte == null) {
			if (other.compoundArte != null)
				return false;
		} else if (!compoundArte.equals(other.compoundArte))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

}