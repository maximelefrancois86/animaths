package org.animaths.client.mathObject;

import org.jscience.mathMLImpl.MathMLElementImpl;

public interface MathObjectElement {
	
	public MathObjectElement getMathObjectParent();
	public MathMLElementImpl getMathMLParent();
	public void setMathObjectParent(MathObjectElement mathObjectParent);
	public void setMathMLParent(MathMLElementImpl mathMLParent);
	public void render();

}
