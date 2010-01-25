package org.animaths.client.mathObject;

import org.jscience.mathMLImpl.MathMLDocumentImpl;
import org.jscience.mathMLImpl.MathMLElementImpl;

public class MathObjectEquation implements MathObjectElement {

	private MathObjectElement leftHandSide;
	private MathObjectElement rightHandSide;
	private MathObjectPresentationToken equalSign;
	private MathObjectElement mathObjectParent;	
	private MathMLElementImpl mathMLParent;
	
	public MathObjectEquation(MathMLDocumentImpl owner) {
		(equalSign = new MathObjectPresentationToken(owner,"mo")).setTextContent("=");
	}

	public void setLeftHandSide(MathObjectElement leftHandSide) {
		leftHandSide.setMathObjectParent(this);
		leftHandSide.setMathMLParent(mathMLParent);		
		this.leftHandSide = leftHandSide;		
	}

	public MathObjectElement getLeftHandSide() {
		return leftHandSide;
	}

	public void setRightHandSide(MathObjectElement rightHandSide) {
		rightHandSide.setMathObjectParent(this);
		rightHandSide.setMathMLParent(mathMLParent);		
		this.rightHandSide = rightHandSide;
	}

	public MathObjectElement getRightHandSide() {
		return rightHandSide;
	}

	public void render() {
		leftHandSide.render();
		mathMLParent.appendChild(equalSign);
		rightHandSide.render();
	}

	public MathMLElementImpl getMathMLParent() {
		return mathMLParent;
	}

	public MathObjectElement getMathObjectParent() {
		return mathObjectParent;
	}

	public void setMathMLParent(MathMLElementImpl mathMLParent) {
		this.mathMLParent = mathMLParent;
	}

	public void setMathObjectParent(MathObjectElement mathObjectParent) {
		this.mathObjectParent = mathObjectParent;
	}
}
