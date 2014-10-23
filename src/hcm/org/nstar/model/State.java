package hcm.org.nstar.model;

import com.rapidminer.example.Attribute;

public class State {
	private Attribute attIdPaper;
	private Attribute attIdKeyword;
	public State(Attribute attIdPaper, Attribute attIdKeyword) {
		this.attIdPaper = attIdPaper;
		this.attIdKeyword = attIdKeyword;
	}
	public Attribute getAttIdPaper() {
		return attIdPaper;
	}
	public void setAttIdPaper(Attribute attIdPaper) {
		this.attIdPaper = attIdPaper;
	}
	public Attribute getAttIdKeyword() {
		return attIdKeyword;
	}
	public void setAttIdKeyword(Attribute attIdKeyword) {
		this.attIdKeyword = attIdKeyword;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

}
