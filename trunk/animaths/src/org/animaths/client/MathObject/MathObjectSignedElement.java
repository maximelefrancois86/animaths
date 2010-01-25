package org.animaths.client.MathObject;

import org.animaths.client.MathML.MathMLFenced;
import org.animaths.client.MathML.MathMLOperator;

public class MathObjectSignedElement extends MathObjectElement {

	public static final short type = MathObjectElement.MATH_OBJECT_SIGNED_ELEMENT;

	private boolean isMinus = false;
	private boolean isFirstChild = false;
	private MathObjectElement child;

	public MathObjectSignedElement() { }
	
	public MathObjectSignedElement(MathObjectElement child) {
		setChild(child);
	}
	
	public MathObjectSignedElement(MathObjectElement child, boolean isMinus) {
		setChild(child);
		setMinus(isMinus);
	}
	
	public boolean isMinus() {
		return isMinus;
	}

	public MathObjectElement getChild() {
		return child;
	}
	
	public void setMinus(boolean isMinus) {
		this.isMinus = isMinus;
	}		

	public void setChild(MathObjectElement child) {
		child.setMathObjectParent(this);
		this.child = child;
	}

	public void pack() {
		if(!isFirstChild || isMinus) {
			MathMLOperator sign;
			if(isMinus)
				sign = new MathMLOperator("-");
			else
				sign = new MathMLOperator("+");			
			sign.setMathObjectElement(this);
			mathMLParent.add(sign);
		}
		if(needsFence()) {
			MathMLFenced fence = new MathMLFenced();
			fence.setMathObjectElement(this);
			mathMLParent.add(fence);
			child.setMathMLParent(fence);
			child.pack();
		}
		else {
			child.setMathMLParent(mathMLParent);
			child.pack();
		}
	}

	public void setIsFirstChild(boolean isFirstChild) {
		this.isFirstChild = isFirstChild;
	}

	public short getType() {
		return type;
	}

}
