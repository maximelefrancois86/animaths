package org.animaths.client.mathObject;

public interface MathObjectFencableElement extends MathObjectElement {
	
	public boolean hasFence();

	public void setFence(boolean hasFence);
	
	public void setParents(MathObjectElement e);
	
}
