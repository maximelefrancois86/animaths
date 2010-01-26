package fr.upmf.animaths.client.mvp.modele.MathObject;

import fr.upmf.animaths.client.mvp.widgets.MathML.MathMLElement;
import fr.upmf.animaths.client.mvp.widgets.MathML.MathMLOperator;


public class MathObjectEquation extends MathObjectElement {

	public static short type = MathObjectElement.MATH_OBJECT_EQUATION;

	private MathObjectElement leftHandSide;
	private MathMLOperator sign;
	private MathObjectElement rightHandSide;
	
	public MathObjectEquation() {
		sign = MathMLOperator.equality(this);
	}
	
	public MathObjectEquation(MathObjectElement leftHandSide, MathObjectElement rightHandSide) {
		this();
		setLeftHandSide(leftHandSide);
		setRightHandSide(rightHandSide);
	}

	public short getType() {
		return type;
	}

	public MathObjectElement getLeftHandSide() {
		return leftHandSide;
	}

	public MathObjectElement getRightHandSide() {
		return rightHandSide;
	}

	public void setLeftHandSide(MathObjectElement leftHandSide) {
		leftHandSide.setMathObjectParent(this);
		this.leftHandSide = leftHandSide;
	}

	public void setRightHandSide(MathObjectElement rightHandSide) {
		rightHandSide.setMathObjectParent(this);
		this.rightHandSide = rightHandSide;
	}

	public void pack(MathMLElement mathMLParent) {
		leftHandSide.pack(mathMLParent);
		mathMLParent.appendChild(sign);		
		rightHandSide.pack(mathMLParent);
	}
	
	public void setState(short state) {
		leftHandSide.setState(state);
		sign.setState(state);
		rightHandSide.setState(state);
	}
	
	public MathObjectElement getMathObjectFirstChild() {
		return leftHandSide;
	}

	public MathObjectElement getMathObjectNextChild(MathObjectElement child) {
		return (child==leftHandSide)?rightHandSide:leftHandSide;
	}

	public MathObjectElement getMathObjectPreviousChild(MathObjectElement child) {
		return (child==leftHandSide)?rightHandSide:leftHandSide;
	}

	public MathObjectEquation clone() {
		return new MathObjectEquation(leftHandSide.clone(),rightHandSide.clone());
	}


	public int getBoundingClientBottom() {
		return Math.max(leftHandSide.getBoundingClientBottom(),rightHandSide.getBoundingClientBottom());
	}

	public int getBoundingClientLeft() {
		return leftHandSide.getBoundingClientLeft();
	}

	public int getBoundingClientTop() {
		return Math.min(leftHandSide.getBoundingClientTop(),rightHandSide.getBoundingClientTop());
	}

	public int getBoundingClientRight() {
		return rightHandSide.getBoundingClientRight();
	}

}
