package org.animaths.client.MathObject;

import java.util.ArrayList;
import java.util.List;

import org.animaths.client.MathML.MathMLElement;
import org.animaths.client.MathML.MathMLFenced;


public class MathObjectAddContainer extends MathObjectElement {

	public static final short type = MathObjectElement.MATH_OBJECT_ADDITION_CONTAINER;

	private List<MathObjectSignedElement> children = new ArrayList<MathObjectSignedElement>();
	
	public MathObjectAddContainer() {
	}

	public MathObjectAddContainer(MathObjectSignedElement child) {
		addChild(child);
	}

	public MathObjectAddContainer(List<MathObjectSignedElement> children) {
		setChildren(children);
	}

	public List<MathObjectSignedElement> getElements() {
		return children;
	}
	
	public void setChildren(List<MathObjectSignedElement> children) {
		this.children.clear();
		for(int i=0;i<children.size();i++)
			addChild(children.get(i));
		
	}
	
	public void addChild(MathObjectSignedElement child) {
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
	
	public void pack() {
		if(needsFence()) {
			MathMLFenced fence = new MathMLFenced();
			fence.setMathObjectElement(this);
			mathMLParent.add(fence);
			setMathMLParentForChildren(fence);
		}
		else {
			setMathMLParentForChildren(mathMLParent);
		}
		children.get(0).setIsFirstChild(true);
		for(int i=0;i<children.size();i++)
			children.get(i).pack();
	}

	public short getType() {
		return type;
	}
}	

