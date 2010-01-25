package org.animaths.client;

import org.animaths.client.MathML.MathMLPanel;
import org.animaths.client.MathObject.MathObjectAddContainer;
import org.animaths.client.MathObject.MathObjectElement;
import org.animaths.client.MathObject.MathObjectEquation;
import org.animaths.client.MathObject.MathObjectMultiplyContainer;
import org.animaths.client.MathObject.MathObjectMultiplyElement;
import org.animaths.client.MathObject.MathObjectNumber;
import org.animaths.client.MathObject.MathObjectSignedElement;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;

public class AniMaths implements EntryPoint {

	FlexTable equations;
	public int complexity;

	public void onModuleLoad() {
		
		MathMLPanel wrapper = new MathMLPanel();
		RootPanel.get("MathDiv").add(wrapper);
		complexity = 10;
		generateEquation(wrapper);
	}
	
	public void generateEquation(MathMLPanel wrapper) {

		MathObjectEquation eq = new MathObjectEquation(wrapper);		
		eq.setLeftHandSide(generateMathObjectElement(eq,0));
		eq.setRightHandSide(generateMathObjectElement(eq,0));
		eq.pack();
	}

	public MathObjectElement generateMathObjectElement(MathObjectElement mathObjectParent,int i) {
		MathObjectElement object;
		double seuil = 1.001-Math.exp(-i/complexity);
		if(Math.random()>seuil) {
			double r = Math.random();
			if(r<1/3) {
				object = generateRandomSignedElement(mathObjectParent,i+i);
				return object;
			}
			else if(1/3<r && r<1){
//				else if(1/3<r && r<2/3){
				object = generateRandomAddContainer(mathObjectParent,i+i);
				return object;
			}
//			else if(2/3<r && r<1){
//				object = generateRandomMultiplyContainer(mathObjectParent,i+i);
//				return object;
//			}
		}
		else {
			object = new MathObjectNumber((int)(Math.random()*100));
			object.setMathObjectParent(mathObjectParent);
			return object;
		}
		return null;
	}
	
	public MathObjectSignedElement generateRandomSignedElement(MathObjectElement mathObjectParent,int i) {
		MathObjectSignedElement object = new MathObjectSignedElement();
		object.setMathObjectParent(mathObjectParent);
		if(Math.random()<0.5)
			object.setMinus(true);
		else
			object.setMinus(false);
		object.setChild(generateMathObjectElement(object,i+1));
		return object;	
	}
	
	public MathObjectAddContainer generateRandomAddContainer(MathObjectElement mathObjectParent,int i) {
		MathObjectAddContainer object = new MathObjectAddContainer();
		object.setMathObjectParent(mathObjectParent);
		int n = (int) Math.random()*4+1;
		for(int j=0;j<n;j++) {
			object.addChild(generateRandomSignedElement(object,i+1));
		}
		return object;	
	}
	
	public MathObjectMultiplyContainer generateRandomMultiplyContainer(MathObjectElement mathObjectParent,int i) {
		MathObjectMultiplyContainer object = new MathObjectMultiplyContainer();
		object.setMathObjectParent(mathObjectParent);
		int n = (int) Math.random()*4+1;
		for(int j=0;j<n;j++) {
			object.addChild(generateRandomMultiplyElement(object,i+1));
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
		object.setChild(generateMathObjectElement(object,i+1));
		return object;
	}
}
