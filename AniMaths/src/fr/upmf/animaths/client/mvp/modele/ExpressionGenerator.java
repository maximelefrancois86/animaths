package fr.upmf.animaths.client.mvp.modele;

import fr.upmf.animaths.client.mvp.modele.MathObject.MathObjectAddContainer;
import fr.upmf.animaths.client.mvp.modele.MathObject.MathObjectElement;
import fr.upmf.animaths.client.mvp.modele.MathObject.MathObjectMultiplyContainer;
import fr.upmf.animaths.client.mvp.modele.MathObject.MathObjectMultiplyElement;
import fr.upmf.animaths.client.mvp.modele.MathObject.MathObjectNumber;
import fr.upmf.animaths.client.mvp.modele.MathObject.MathObjectSignedElement;


public class ExpressionGenerator {

	private int complexity=2;
	private int nAdd=2;
	private int nMult=2;
	
	public ExpressionGenerator(){}

	public ExpressionGenerator(int complexity, int nAdd, int nMult){
		this.complexity = complexity;
		this.nAdd = nAdd;
		this.nMult = nMult;
	}

	public MathObjectElement generateExpression(MathObjectElement mathObjectParent) {
		return generateMathObjectElement(mathObjectParent, 0);
	}
	
	public MathObjectElement generateMathObjectElement(MathObjectElement mathObjectParent,int i) {
		MathObjectElement object;
//		double seuil = 1.001-Math.exp(-i/complexity);
//		if(Math.random()>seuil) {
		if(i<complexity) {
//			double r = Math.random();
//			if(r<0.2) {
//				object = generateRandomSignedElement(mathObjectParent,i+1);
//				return object;
//			}
//			else if(0.2<r && r<0.6) {
//				object = generateRandomMultiplyContainer(mathObjectParent,i+1);
//				return object;
//			}
//			else if(0.6<r && r<1.0){
//				object = generateRandomAddContainer(mathObjectParent,i+1);
//				return object;
//			}
		object = generateRandomMultiplyContainer(mathObjectParent,i+1);
		return object;
		}
		else {
			object = new MathObjectNumber((int)(Math.random()*10));
			object.setMathObjectParent(mathObjectParent);
			return object;
		}
//		return null;
	}
	
	public MathObjectSignedElement generateRandomSignedElement(MathObjectElement mathObjectParent,int i) {
		MathObjectSignedElement object = new MathObjectSignedElement();
		object.setMathObjectParent(mathObjectParent);
		if(Math.random()<0.5)
			object.setMinus(true);
		else
			object.setMinus(false);
		object.setChild(generateMathObjectElement(object,i));
		return object;	
	}
	
	public MathObjectAddContainer generateRandomAddContainer(MathObjectElement mathObjectParent,int i) {
		MathObjectAddContainer object = new MathObjectAddContainer();
		object.setMathObjectParent(mathObjectParent);
		int n = (int) (Math.random()*nAdd+2);
		for(int j=0;j<n;j++) {
			object.addChild(generateRandomSignedElement(object,i));
		}
		return object;	
	}
	
	public MathObjectMultiplyContainer generateRandomMultiplyContainer(MathObjectElement mathObjectParent,int i) {
		MathObjectMultiplyContainer object = new MathObjectMultiplyContainer();
		object.setMathObjectParent(mathObjectParent);
		int n = (int) (Math.random()*nMult+2);
		for(int j=0;j<n;j++) {
			object.addChild(generateRandomMultiplyElement(object,i));
		}
		return object;	
	}
	
	public MathObjectMultiplyElement generateRandomMultiplyElement(MathObjectElement mathObjectParent,int i) {
		MathObjectMultiplyElement object = new MathObjectMultiplyElement();
		object.setMathObjectParent(mathObjectParent);
		if(Math.random()<0.5)
			object.setDivided(true);
		else
			object.setDivided(false);
		object.setChild(generateMathObjectElement(object,i));
		return object;
	}

	public int getComplexity() {
		return complexity;
	}

	public void setComplexity(int complexity) {
		this.complexity = complexity;
	}

}
