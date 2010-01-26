package fr.upmf.animaths.client.mvp.modele.MathObject;

import fr.upmf.animaths.client.mvp.widgets.MathML.MathMLElement;
import fr.upmf.animaths.client.mvp.widgets.MathML.MathMLOperator;

public class MathObjectMultiplyElement extends MathObjectElement {

	public static final short type = MathObjectElement.MATH_OBJECT_MULTIPLY_ELEMENT;

	private boolean isDivided = false;
	private MathMLOperator sign;
	private boolean needsSign = true;
	private MathObjectElement child;

	public MathObjectMultiplyElement() {}

	public MathObjectMultiplyElement(MathObjectElement child) {
		setChild(child);
	}
	
	public MathObjectMultiplyElement(MathObjectElement child, boolean isDivided) {
		this(child);
		setDivided(isDivided);
	}
	
	public short getType() {
		return type;
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

	public void pack(MathMLElement mathMLParent) {
		if(needsSign) {
			sign = new MathMLOperator(this,".");
			mathMLParent.appendChild(sign);
		}
		child.pack(mathMLParent);
	}

	public MathMLOperator getSign() {
		return sign;
	}

	public void setSign(MathMLOperator sign) {
		this.sign = sign;
	}

	public void setState(short state) {
		if(sign!=null)
			sign.setState(state);
		child.setState(state);		
	}

	public MathObjectElement getMathObjectFirstChild() {
		return child;
	}

	public MathObjectElement getMathObjectNextChild(MathObjectElement child) {
		return this.child;
	}

	public MathObjectElement getMathObjectPreviousChild(MathObjectElement child) {
		return this.child;
	}

	public MathObjectMultiplyElement clone() {
		return new MathObjectMultiplyElement(child.clone(),isDivided);
	}

	public int getBoundingClientLeft() {
		if(sign!=null)
			return (int) sign.getBoundingClientLeft();
		else
			return child.getBoundingClientLeft();
	}

	public int getBoundingClientRight() {
		return child.getBoundingClientRight();
	}

	public int getBoundingClientTop() {
			return child.getBoundingClientTop();
	}

	public int getBoundingClientBottom() {
			return child.getBoundingClientBottom();
	}

	public void setNeedsSign(boolean needsSign) {
		this.needsSign = needsSign;
	}

	public boolean needsSign() {
		return needsSign;
	}

}
