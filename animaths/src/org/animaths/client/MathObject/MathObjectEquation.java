package org.animaths.client.MathObject;

import org.animaths.client.MathML.MathMLOperator;
import org.animaths.client.MathML.MathMLPanel;


public class MathObjectEquation extends MathObjectElement {

	public static short type = MathObjectElement.MATH_OBJECT_EQUATION;

	private MathObjectElement leftHandSide;
	private MathObjectElement rightHandSide;
	
	public MathObjectEquation(MathMLPanel mathMLParent) {
		setMathMLParent(mathMLParent);
	}
	
	public MathObjectEquation(MathMLPanel mathMLParent, MathObjectElement leftHandSide, MathObjectElement rightHandSide) {
		this(mathMLParent);
		setLeftHandSide(leftHandSide);
		setRightHandSide(rightHandSide);
	}

	public MathObjectElement getLeftHandSide() {
		return leftHandSide;
	}

	public MathObjectElement getRightHandSide() {
		return rightHandSide;
	}

	public void setLeftHandSide(MathObjectElement leftHandSide) {
		leftHandSide.setMathMLParent(mathMLParent);
		leftHandSide.setMathObjectParent(this);
		this.leftHandSide = leftHandSide;
	}

	public void setRightHandSide(MathObjectElement rightHandSide) {
		rightHandSide.setMathMLParent(mathMLParent);
		rightHandSide.setMathObjectParent(this);
		this.rightHandSide = rightHandSide;
	}

	public void pack() {

		leftHandSide.pack();

		MathMLOperator eq = MathMLOperator.equality();
		eq.setMathObjectElement(this);
		mathMLParent.add(eq);
		
		rightHandSide.pack();
	}

	public short getType() {
		return type;
	}

}
