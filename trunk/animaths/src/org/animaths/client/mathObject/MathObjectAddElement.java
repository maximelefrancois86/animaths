package org.animaths.client.mathObject;

import org.jscience.mathMLImpl.MathMLDocumentImpl;
import org.jscience.mathMLImpl.MathMLElementImpl;


public class MathObjectAddElement implements MathObjectFencableElement {

	private MathMLDocumentImpl owner;
	private MathObjectElement mathObjectParent;	
	private MathMLElementImpl mathMLParent;
	private boolean isMinus=false;
	private boolean showSign=false;
	private boolean hasFence=false;
	private MathObjectFencedElement fence;
	private MathObjectElement child;	
	
	public MathObjectAddElement(MathMLDocumentImpl owner) {
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

	public boolean getShowSign() {
		return showSign;
	}

	public void setShowSign(boolean showSign) {
		this.showSign = showSign;
	}

	public boolean isMinus() {
		return isMinus;
	}

	public void setMinus(boolean isMinus) {
		this.isMinus = isMinus ;
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
		if(isMinus)
		{
			MathObjectPresentationToken sign = new MathObjectPresentationToken(owner,"mo");
			sign.setTextContent("-");
			mathMLParent.appendChild(sign);
		}
		else if(showSign)
		{
			MathObjectPresentationToken sign = new MathObjectPresentationToken(owner,"mo");
			sign.setTextContent("+");
			mathMLParent.appendChild(sign);
		}
		if(hasFence)
			mathMLParent.appendChild(fence);
		child.render();
	}
	
}
