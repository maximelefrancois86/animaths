package org.animaths.client.mathObject;

import org.jscience.mathMLImpl.MathMLDocumentImpl;

public class MathObjectAtomIdentifier extends MathObjectPresentationToken {
	
	public MathObjectAtomIdentifier(MathMLDocumentImpl owner, String name) {
		super(owner,"mi");
		this.setTextContent(name);
	}
}
