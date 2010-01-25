package org.animaths.client.mathObject;

import org.jscience.mathMLImpl.MathMLDocumentImpl;
import org.jscience.mathMLImpl.MathMLPresentationTokenImpl;

public class MathObjectAtomNumber extends MathObjectPresentationToken {
	
	public MathObjectAtomNumber(MathMLDocumentImpl owner, int value) {
		super(owner,"mn");
		this.setTextContent(Integer.toString(value));
	}

}
