package org.animaths.client.mathObject;

import org.jscience.mathMLImpl.MathMLDocumentImpl;
import org.jscience.mathMLImpl.MathMLElementImpl;

public class MathObjectMultiplyElement implements MathObjectElement {

	private MathMLDocumentImpl owner;
	private MathObjectElement mathObjectParent;	
	private MathMLElementImpl mathMLParent;
	private boolean isDivide=false;
	private boolean hasFence=false;
	private MathObjectFencedElement fence;
	private MathObjectElement child;	
	
	public MathObjectMultiplyElement(MathMLDocumentImpl owner) {
		this.owner = owner;
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

	public void setDivide(boolean isDivide) {
		this.isDivide = isDivide;
	}

	public boolean isDivide() {
		return isDivide;
	}

	public boolean hasFence() {
		return hasFence;
	}

	public void setFence(boolean hasFence) {
		this.hasFence = hasFence ;
	}
	
	public void setChild(MathObjectElement child) {
		setParents(child);
		this.child = child;
	}

	public void setParents(MathObjectElement e) {
		e.setMathObjectParent(this);
		if(hasFence)
			e.setMathMLParent(fence);
		else
			e.setMathMLParent(mathMLParent);
	}
	
	public void render() {		
		if(isDivide)
		{
			MathObjectPresentationToken sign = new MathObjectPresentationToken(owner,"mo");
			sign.setTextContent("-");
			mathMLParent.appendChild(sign);
		}
		if(hasFence)
			mathMLParent.appendChild(fence);
		child.render();
	}
	
}
