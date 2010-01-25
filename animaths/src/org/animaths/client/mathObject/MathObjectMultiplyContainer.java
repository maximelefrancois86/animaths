package org.animaths.client.mathObject;

import java.util.Vector;

import org.jscience.mathMLImpl.MathMLElementImpl;

public class MathObjectMultiplyContainer extends Vector<MathObjectMultiplyElement> implements MathObjectFencableElement {

	private MathObjectElement mathObjectParent;	
	private MathMLElementImpl mathMLParent;
	private boolean hasFence;
	private MathObjectFencedElement fence;
	
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

	public boolean hasFence() {
		return hasFence;
	}

	public void setFence(boolean hasFence) {
		this.hasFence = hasFence ;
	}
	
	public boolean add(MathObjectMultiplyElement e) {
		setParents(e);
		boolean bool = super.add(e);
		return bool;
	}
	
	public void add(int index, MathObjectMultiplyElement e) {
		setParents(e);
		super.add(index, e);
	}

	public void addElement(MathObjectMultiplyElement obj) {
		setParents(obj);
		super.addElement(obj);				
	}

	public void setParents(MathObjectElement e) {
		e.setMathObjectParent(this);
		if(hasFence)
			e.setMathMLParent(fence);
		else
			e.setMathMLParent(mathMLParent);
	}
	
	public void render() {
		for(int i=0;i<this.size();i++)
			this.get(i).render();
	}
	
}
