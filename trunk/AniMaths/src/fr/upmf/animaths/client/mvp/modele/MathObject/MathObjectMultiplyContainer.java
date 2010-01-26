package fr.upmf.animaths.client.mvp.modele.MathObject;

import java.util.ArrayList;
import java.util.List;

import fr.upmf.animaths.client.mvp.widgets.MathML.MathMLElement;
import fr.upmf.animaths.client.mvp.widgets.MathML.MathMLFenced;
import fr.upmf.animaths.client.mvp.widgets.MathML.MathMLFrac;
import fr.upmf.animaths.client.mvp.widgets.MathML.MathMLRow;


public class MathObjectMultiplyContainer extends MathObjectElement {

	public static final short type = MathObjectElement.MATH_OBJECT_MULTIPLY_CONTAINER;

	private MathMLFenced fence;
	private MathMLFrac frac;
	private MathMLRow numeratorRow;
	private MathMLRow denominatorRow;
	private List<MathObjectMultiplyElement> numerator = new ArrayList<MathObjectMultiplyElement>();
	private List<MathObjectMultiplyElement> denominator = new ArrayList<MathObjectMultiplyElement>();
	
	public MathObjectMultiplyContainer() { }

	public MathObjectMultiplyContainer(MathObjectMultiplyElement ... children) {
		for(MathObjectMultiplyElement child : children)
			addChild(child);
	}

	public short getType() {
		return type;
	}

	public void addChild(MathObjectMultiplyElement child) {
		child.setMathObjectParent(this);
		if(!child.isDivided())
			numerator.add(child);
		else
			denominator.add(child);
	}
	
	public void pack(MathMLElement mathMLParent) {
		MathMLElement mml1 = mathMLParent;
		if(needsFence()) {
			fence = new MathMLFenced(this);
			mathMLParent.appendChild(fence);
			mml1 = fence;
		}
		if(denominator.size()!=0) {
			frac = new MathMLFrac(this);
			numeratorRow = new MathMLRow(this);
			denominatorRow = new MathMLRow(this);
			mml1.appendChild(frac);
			frac.appendChild(numeratorRow);
			frac.appendChild(denominatorRow);
			denominator.get(0).setNeedsSign(false);
			mml1 = numeratorRow;
			if(numerator.size()==0)
				addChild(new MathObjectMultiplyElement(new MathObjectNumber(1)));
			for(MathObjectMultiplyElement child : denominator)
				child.pack(denominatorRow);
		}
		numerator.get(0).setNeedsSign(false);
		for(MathObjectMultiplyElement child : numerator)
			child.pack(mml1);
	}

	public void setState(short state) {
		if(fence!=null)
			fence.setState(state);
		if(frac!=null)
			frac.setState(state);
		if(numeratorRow!=null)
			numeratorRow.setState(state);
		if(denominatorRow!=null)
			denominatorRow.setState(state);
		for(MathObjectMultiplyElement child : numerator)
			child.setState(state);		
		for(MathObjectMultiplyElement child : denominator)
			child.setState(state);		
	}

	public MathObjectElement getMathObjectFirstChild() {
		return numerator.get(0);
	}

	public MathObjectElement getMathObjectNextChild(MathObjectElement child) {
		int i = numerator.indexOf(child);
		if(i!=-1) {
			if(i!=numerator.size()-1)
				return numerator.get(i+1);
			else {
				if(denominator.size()!=0)
					return denominator.get(0);
				else
					return numerator.get(0);
			}
		}
		else {
			i = denominator.indexOf(child);
			if(i!=denominator.size()-1)
				return denominator.get(i+1);
			else
				return numerator.get(0);
		}
	}

	public MathObjectElement getMathObjectPreviousChild(MathObjectElement child) {
		int i = numerator.indexOf(child);
		if(i!=-1) {
			if(i!=0)
				return numerator.get(i-1);
			else {
				if(denominator.size()!=0)
					return denominator.get(denominator.size()-1);
				else
					return numerator.get(numerator.size()-1);
			}
		}
		else {
			i = denominator.indexOf(child);
			if(i!=0)
				return denominator.get(i-1);
			else
				return numerator.get(numerator.size()-1);
		}
	}

	public MathObjectMultiplyContainer clone() {
		MathObjectMultiplyContainer object = new MathObjectMultiplyContainer();
		for(MathObjectMultiplyElement child : numerator)
			object.addChild(child.clone());
		for(MathObjectMultiplyElement child : denominator)
			object.addChild(child.clone());
		return object;
	}

	public int getBoundingClientLeft() {
		if(fence!=null)
			return (int) fence.getBoundingClientLeft();
		else if(frac!=null)
			return (int) frac.getBoundingClientLeft();
		else 
			return numerator.get(0).getBoundingClientLeft();
	}

	public int getBoundingClientRight() {
		if(fence!=null)
			return (int) (fence.getBoundingClientLeft() + fence.getBoundingClientWidth());
		else if(frac!=null)
			return (int) (frac.getBoundingClientLeft() + frac.getBoundingClientWidth());
		else
			return numerator.get(numerator.size()-1).getBoundingClientRight();
	}

	public int getBoundingClientTop() {
		if(fence!=null)
			return (int) fence.getBoundingClientTop();
		else if(frac!=null)
			return (int) frac.getBoundingClientTop();
		else {
			int i=Integer.MAX_VALUE;
			for(MathObjectMultiplyElement element : numerator)
				i = Math.min(i,element.getBoundingClientTop());
			return i;
		}
	}

	public int getBoundingClientBottom() {
		if(fence!=null)
			return (int) (fence.getBoundingClientTop() + fence.getBoundingClientHeight());
		else if(frac!=null)
			return (int) (frac.getBoundingClientTop() + frac.getBoundingClientHeight());
		else {
			int i=0;
			for(MathObjectMultiplyElement element : numerator)
				i = Math.max(i,element.getBoundingClientBottom());
			return i;
		}
	}

}	

