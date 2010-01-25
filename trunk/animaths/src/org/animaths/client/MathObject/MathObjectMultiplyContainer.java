package org.animaths.client.MathObject;

import java.util.ArrayList;
import java.util.List;

import org.animaths.client.MathML.MathMLElement;
import org.animaths.client.MathML.MathMLFenced;
import org.animaths.client.MathML.MathMLFrac;
import org.animaths.client.MathML.MathMLRow;


public class MathObjectMultiplyContainer extends MathObjectElement {

	public static final short type = MathObjectElement.MATH_OBJECT_MULTIPLY_CONTAINER;

	private List<MathObjectMultiplyElement> children = new ArrayList<MathObjectMultiplyElement>();
	
	public MathObjectMultiplyContainer() {
	}

	public MathObjectMultiplyContainer(MathObjectMultiplyElement child) {
		addChild(child);
	}

	public MathObjectMultiplyContainer(List<MathObjectMultiplyElement> children) {
		setChildren(children);
	}

	public List<MathObjectMultiplyElement> getElements() {
		return children;
	}
	
	public void setChildren(List<MathObjectMultiplyElement> children) {
		this.children.clear();
		for(int i=0;i<children.size();i++)
			addChild(children.get(i));
		
	}
	
	public void addChild(MathObjectMultiplyElement child) {
		child.setMathMLParent(mathMLParent);
		child.setMathObjectParent(this);
		children.add(child);
	}
	
	public void setMathMLParent(MathMLElement mathMLParent) {
		super.setMathMLParent(mathMLParent);
	}
	
	public void setMathMLParentForChildren(MathMLElement mathMLParent) {
		for(int i=0;i<children.size();i++)
			children.get(i).setMathMLParent(mathMLParent);
	}
	
	public void setMathMLParentForChildren(MathMLElement numerator, MathMLElement denominator) {
		for(int i=0;i<children.size();i++)
			if(children.get(i).isDivided())
				children.get(i).setMathMLParent(denominator);
			else
				children.get(i).setMathMLParent(numerator);
	}
	
	public void pack() {
		MathMLElement pEl;
		if(needsFence()) {
			MathMLFenced fence = new MathMLFenced();
			fence.setMathObjectElement(this);
			mathMLParent.add(fence);
			pEl = fence;
		}
		else {
			pEl = mathMLParent;
		}
		if(isFrac()) {
			MathMLFrac frac = new MathMLFrac();
			frac.setMathObjectElement(this);
			pEl.add(frac);
			MathMLRow numerator = new MathMLRow();
			numerator.setMathObjectElement(this);
			frac.add(numerator);
			MathMLRow denominator = new MathMLRow();
			denominator.setMathObjectElement(this);
			frac.add(denominator);
			setMathMLParentForChildren(numerator,denominator);
		}
		else {
			setMathMLParentForChildren(pEl);
		}
		setFirstChilds();
		for(int i=0;i<children.size();i++)
			children.get(i).pack();
	}

	public boolean isFrac() {
		for(int i=0;i<children.size();i++)
			if(children.get(i).isDivided())
				return true;
		return false;
	}

	public boolean needsRow() {
		return true;
	}

	public short getType() {
		return type;
	}
	
	public void setFirstChilds() {
		boolean numeratorFirstChild = true;
		boolean denominatorFirstChild = true;
		for(int i=0;i<children.size();i++)
			if(numeratorFirstChild && !children.get(i).isDivided()) {
				numeratorFirstChild = false;
				children.get(i).setIsFirstChild(true);
			}
			else if(denominatorFirstChild && children.get(i).isDivided()) {
				denominatorFirstChild = false;
				children.get(i).setIsFirstChild(true);
			}
	}
}	

