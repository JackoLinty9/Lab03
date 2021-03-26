package it.polito.tdp.spellchecker.model;

public class RichWord {
	public String parola;
	public boolean corretta;
	
	public RichWord(String parola, boolean corretta) {
		super();
		this.parola = parola;
		this.corretta = true;
	}
	
	public String getParola() {
		return parola;
	}
	
	public void setParola(String parola) {
		this.parola = parola;
	}
}
