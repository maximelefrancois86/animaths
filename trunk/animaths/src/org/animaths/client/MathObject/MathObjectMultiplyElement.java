package org.animaths.client.MathObject;

import org.animaths.client.MathML.MathMLFenced;
import org.animaths.client.MathML.MathMLOperator;

public class MathObjectMultiplyElement extends MathObjectElement {

	public static final short type = MathObjectElement.MATH_OBJECT_MULTIPLY_ELEMENT;

	private boolean isDivided = false;
	private boolean isFirstChild = false;
	private boolean needsSign = true;
	private MathObjectElement child;

	public MathObjectMultiplyElement() {}

	public MathObjectMultiplyElement(MathObjectElement child) {
		setChild(child);
	}
	
	public MathObjectMultiplyElement(MathObjectElement child, boolean isDivided) {
		setChild(child);
		setDivided(isDivided);
	}
	
	public boolean isDivided() {
		return isDivided;
	}

	public MathObjectElement getChild() {
		return child;
	}
	
	public void setDivided(boolean isDivided) {
		this.isDivided = isDivided;
	}		

	public void setChild(MathObjectElement child) {
		child.setMathObjectParent(this);
		this.child = child;
	}

	public void pack() {
		MathMLOperator sign;
		sign = new MathMLOperator("x");
		sign.setMathObjectElement(this);
		mathMLParent.add(sign);
		child.setMathMLParent(mathMLParent);
		child.pack();
	}

	public void setIsFirstChild(boolean isFirstChild) {
		this.isFirstChild = isFirstChild;
	}

	public void setNeedsSign(boolean needsSign) {
		this.needsSign = needsSign;
	}

	public short getType() {
		return type;
	}

}
