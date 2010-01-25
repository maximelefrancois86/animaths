package org.animaths.client.mathObject;

import org.jscience.mathMLImpl.MathMLDocumentImpl;
import org.jscience.mathMLImpl.MathMLElementImpl;
import org.jscience.mathMLImpl.MathMLFencedElementImpl;

public class MathObjectFencedElement extends MathMLFencedElementImpl implements MathObjectElement {

	private MathObjectElement mathObjectParent;	

	private MathMLElementImpl mathMLParent;
	
	public MathObjectFencedElement(MathMLDocumentImpl owner) {
		super(owner, "mfenced");
		this.setOpen("(");
		this.setSeparators("");
		this.setClose(")");
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
