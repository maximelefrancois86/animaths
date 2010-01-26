package fr.upmf.animaths.client.mvp.modele.MathObject;

import java.util.ArrayList;
import java.util.List;

import fr.upmf.animaths.client.mvp.widgets.MathML.MathMLElement;
import fr.upmf.animaths.client.mvp.widgets.MathML.MathMLFenced;

public class MathObjectAddContainer extends MathObjectElement {

	public static final short type = MathObjectElement.MATH_OBJECT_ADDITION_CONTAINER;

	private MathMLFenced fence=null;
	private List<MathObjectSignedElement> children = new ArrayList<MathObjectSignedElement>();
	
	public MathObjectAddContainer() { }

	public MathObjectAddContainer(MathObjectSignedElement ... children) {
		for(MathObjectSignedElement child : children)
			addChild(child);
	}

	public short getType() {
		return type;
	}

	public void addChild(MathObjectSignedElement child) {
		child.setMathObjectParent(this);
		children.add(child);
	}
	
	public void pack(MathMLElement mathMLParent) {
		MathMLElement mmlP = mathMLParent;
		if(needsFence()) {
			fence = new MathMLFenced(this);
			mathMLParent.appendChild(fence);
			mmlP = fence;
		}
		children.get(0).setNeedsSign(false);
		for(MathObjectSignedElement child : children)
			child.pack(mmlP);
	}
	
	public void setState(short state) {
		if(fence!=null)
			fence.setState(state);
		for(MathObjectSignedElement child : children)
			child.setState(state);
	}

	public MathObjectElement getMathObjectFirstChild() {
		return children.get(0);
	}

	public MathObjectElement getMathObjectNextChild(MathObjectElement child) {
		int i = children.indexOf(child);
		if(i!=children.size()-1)
			return children.get(i+1);
		return children.get(0);
	}

	public MathObjectElement getMathObjectPreviousChild(MathObjectElement child) {
		int i = children.indexOf(child);
		if(i!=0)
			return children.get(i-1);
		return children.get(children.size()-1);
	}

	public MathObjectAddContainer clone() {
		MathObjectAddContainer object = new MathObjectAddContainer();
		for(MathObjectSignedElement child : children)
			object.addChild(child.clone());
		return object;
	}

	public int getBoundingClientLeft() {
		if(fence!=null)
			return (int) fence.getBoundingClientLeft();
		else 
			return children.get(0).getBoundingClientLeft();
	}

	public int getBoundingClientRight() {
		if(fence!=null)
			return (int) (fence.getBoundingClientLeft() + fence.getBoundingClientWidth());
		else
			return children.get(children.size()-1).getBoundingClientRight();
	}

	public int getBoundingClientTop() {
		if(fence!=null)
			return (int) fence.getBoundingClientTop();
		else {
			int i=Integer.MAX_VALUE;
			for(MathObjectSignedElement element : children)
				i = Math.min(i,element.getBoundingClientTop());
			return i;
		}
	}

	public int getBoundingClientBottom() {
		if(fence!=null)
			return (int) (fence.getBoundingClientTop() + fence.getBoundingClientHeight());
		else {
			int i=0;
			for(MathObjectSignedElement element : children)
				i = Math.max(i,element.getBoundingClientBottom());
			return i;
		}
	}

}	

