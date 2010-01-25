package org.animaths.client.mathObject;

import org.jscience.mathMLImpl.MathMLDocumentImpl;

public class Test {

	public static void main(String[] args) {

		MathMLDocumentImpl owner = new MathMLDocumentImpl();
		MathObjectEquation eq = new MathObjectEquation(owner);

		MathObjectAtomIdentifier x = new MathObjectAtomIdentifier(owner, "x");
		eq.setLeftHandSide(x);
		
		MathObjectAtomNumber x1 = new MathObjectAtomNumber(owner, 1);
		eq.setRightHandSide(x1);
		

		eq.render();
		

	}

}
