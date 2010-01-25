package org.animaths.client.MathObject;

import org.animaths.client.MathML.MathMLIdentifier;

public class MathObjectIdentifier extends MathObjectElement {

	public static final short type = MathObjectElement.MATH_OBJECT_IDENTIFIER;

	String name;
	
	public MathObjectIdentifier(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void pack() {
		MathMLIdentifier mathMLIdentifier = new MathMLIdentifier(name);
		mathMLIdentifier.setMathObjectElement(this);
		mathMLParent.add(mathMLIdentifier);
	}

	public short getType() {
		return type;
	}
}
