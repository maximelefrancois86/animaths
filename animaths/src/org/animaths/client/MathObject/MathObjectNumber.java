package org.animaths.client.MathObject;

import org.animaths.client.MathML.MathMLNumber;

public class MathObjectNumber extends MathObjectElement {

	public static final short type = MathObjectElement.MATH_OBJECT_NUMBER;

	Number value;
	
	public MathObjectNumber(Number value) {
		this.value = value;
	}
	
	public MathObjectNumber(String StrValue) {
		this.value = Double.valueOf(StrValue);
	}
	
	public Number getValue() {
		return value;
	}

	public void setValue(Number value) {
		this.value = value;
	}

	public void pack() {
		MathMLNumber mathMLNumber = new MathMLNumber(value);
		mathMLNumber.setMathObjectElement(this);
		mathMLParent.add(mathMLNumber);
	}

	public short getType() {
		return type;
	}

}
