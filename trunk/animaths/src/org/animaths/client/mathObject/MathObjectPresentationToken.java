package org.animaths.client.mathObject;

import org.jscience.mathMLImpl.MathMLDocumentImpl;
import org.jscience.mathMLImpl.MathMLElementImpl;
import org.jscience.mathMLImpl.MathMLPresentationTokenImpl;

public class MathObjectPresentationToken extends MathMLPresentationTokenImpl implements MathObjectElement {

	private MathObjectElement mathObjectParent;	
	private MathMLElementImpl mathMLParent;
	
	public MathObjectPresentationToken(MathMLDocumentImpl owner, String qualifiedName) {
		super(owner, qualifiedName);
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

	public void render() {
		mathMLParent.appendChild(this);
	}

}

