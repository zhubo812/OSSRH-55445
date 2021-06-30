package org.bhu.commons.lang.analyzer.bean;

public class Entity {

	String expression;
	int startIndx;
	int endIndex;
	TermNatures termNatures;
	public TermNatures getTermNatures() {
		return termNatures;
	}
	public void setTermNatures(TermNatures termNatures) {
		this.termNatures = termNatures;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public int getStartIndx() {
		return startIndx;
	}
	public void setStartIndx(int startIndx) {
		this.startIndx = startIndx;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	public Entity(String expression, int startIndx, int endIndex) {
		super();
		this.expression = expression;
		this.startIndx = startIndx;
		this.endIndex = endIndex;
	}
	
	public Entity(String expression, int startIndx, int endIndex, TermNatures termNatures) {
		super();
		this.expression = expression;
		this.startIndx = startIndx;
		this.endIndex = endIndex;
		this.termNatures = termNatures;
	}
	public Entity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
