package fr.upmf.animaths.client.mvp.modele.MathObject;

import fr.upmf.animaths.client.mvp.widgets.MathML.MathMLElement;


public abstract class MathObjectElement {

	public static final short MATH_OBJECT_WRAPPER = 0;
	public static final short MATH_OBJECT_EQUATION = 1;
	public static final short MATH_OBJECT_NUMBER = 2;
	public static final short MATH_OBJECT_IDENTIFIER = 3;
	public static final short MATH_OBJECT_SIGNED_ELEMENT = 4;
	public static final short MATH_OBJECT_ADDITION_CONTAINER = 5;
	public static final short MATH_OBJECT_MULTIPLY_ELEMENT = 6;
	public static final short MATH_OBJECT_MULTIPLY_CONTAINER = 7;
	public static final short MATH_OBJECT_POWER = 8;

	abstract public short getType();

	
	public static final short STATE_NONE = 0;
	public static final short STATE_SELECTABLE = 1;
	public static final short STATE_SELECTED = 2;

	abstract public void setState(short state);


	protected MathObjectElement mathObjectParent = null;
	
	public MathObjectElement getMathObjectParent() {
		if(mathObjectParent==null)
			return this;
		return mathObjectParent;
	}

	public void setMathObjectParent(MathObjectElement mathObjectParent) {
		this.mathObjectParent = mathObjectParent;
	}

	
	abstract public MathObjectElement getMathObjectFirstChild();
	abstract public MathObjectElement getMathObjectPreviousChild(MathObjectElement child);
	abstract public MathObjectElement getMathObjectNextChild(MathObjectElement child);
	

	abstract public MathObjectElement clone();

	
	abstract public void pack(MathMLElement mathMLParent);

	public boolean needsFence() {
		if(mathObjectParent!=null) {
			short typeP = mathObjectParent.getType();
			switch (this.getType()) {
			case MATH_OBJECT_SIGNED_ELEMENT:
				if (typeP == MATH_OBJECT_SIGNED_ELEMENT
						|| typeP == MATH_OBJECT_POWER
						|| typeP == MATH_OBJECT_MULTIPLY_ELEMENT)
					return true;
				return false;
			case MATH_OBJECT_ADDITION_CONTAINER:
				if (typeP == MATH_OBJECT_SIGNED_ELEMENT
						|| typeP == MATH_OBJECT_POWER
						|| typeP == MATH_OBJECT_MULTIPLY_ELEMENT)
					return true;
				return false;
			case MATH_OBJECT_MULTIPLY_CONTAINER:
				if (typeP == MATH_OBJECT_MULTIPLY_ELEMENT
						|| typeP == MATH_OBJECT_POWER
						|| typeP == MATH_OBJECT_MULTIPLY_ELEMENT)
					return true;
				return false;
			case MATH_OBJECT_POWER:
				if (typeP == MATH_OBJECT_POWER)
					return true;
				else
					return false;
			default:
				return false;
			}
		}
		else
			return false;
	}

	abstract public int getBoundingClientLeft();
	abstract public int getBoundingClientTop();
	abstract public int getBoundingClientRight();
	abstract public int getBoundingClientBottom();

}
