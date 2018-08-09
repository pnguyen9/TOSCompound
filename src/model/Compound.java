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

}