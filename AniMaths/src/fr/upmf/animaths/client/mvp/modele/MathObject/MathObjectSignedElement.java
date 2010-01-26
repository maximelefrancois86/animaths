package fr.upmf.animaths.client.mvp.modele.MathObject;

import fr.upmf.animaths.client.mvp.widgets.MathML.MathMLElement;
import fr.upmf.animaths.client.mvp.widgets.MathML.MathMLFenced;
import fr.upmf.animaths.client.mvp.widgets.MathML.MathMLOperator;

public class MathObjectSignedElement extends MathObjectElement {

	public static final short type = MathObjectElement.MATH_OBJECT_SIGNED_ELEMENT;

	private boolean isMinus = false;
	private MathMLFenced fence;
	private boolean needsSign = true;
	private MathMLOperator sign;
	private MathObjectElement child;

	public MathObjectSignedElement() { }
	
	public MathObjectSignedElement(MathObjectElement child) {
		setChild(child);
	}
	
	public MathObjectSignedElement(MathObjectElement child, boolean isMinus) {
		this(child);
		setMinus(isMinus);
	}
	
	public short getType() {
		return type;
	}

	public boolean isMinus() {
		return isMinus;
	}

	public MathObjectElement getChild() {
		return child;
	}
	
	public void setMinus(boolean isMinus) {
		this.isMinus = isMinus;
	}		

	public void setChild(MathObjectElement child) {
		child.setMathObjectParent(this);
		this.child = child;
	}

	public void pack(MathMLElement mathMLParent) {
		MathMLElement mmlP = mathMLParent;
		if(needsFence()) {
			fence = new MathMLFenced(this);
			mathMLParent.appendChild(fence);
			mmlP = fence;
		}
		if(isMinus) {
			sign = new MathMLOperator(this,"-");
			mmlP.appendChild(sign);
		}
		else if(needsSign) {
			sign = new MathMLOperator(this,"+");
			mmlP.appendChild(sign);
		}
		child.pack(mmlP);			
	}

	public MathMLFenced getFence() {
		return fence;
	}

	public MathMLOperator getSign() {
		return sign;
	}

	public void setFence(MathMLFenced fence) {
		this.fence = fence;
	}

	public void setSign(MathMLOperator sign) {
		this.sign = sign;
	}

	public void setState(short state) {
		if(fence!=null)
			fence.setState(state);
		if(sign!=null)
			sign.setState(state);
		child.setState(state);		
	}

	public MathObjectElement getMathObjectFirstChild() {
		return child;
	}

	public MathObjectElement getMathObjectNextChild(MathObjectElement child) {
		return this.child;
	}

	public MathObjectElement getMathObjectPreviousChild(MathObjectElement child) {
		return this.child;
	}

	public MathObjectSignedElement clone() {
		return new MathObjectSignedElement(child.clone(),isMinus);
	}

	public int getBoundingClientLeft() {
		if(fence!=null)
			return (int) fence.getBoundingClientLeft();
		else if(sign!=null)
			return (int) sign.getBoundingClientLeft();
		else
			return child.getBoundingClientLeft();
	}

	public int getBoundingClientRight() {
		if(fence!=null)
			return (int) (fence.getBoundingClientLeft() + fence.getBoundingClientWidth());
		else
			return child.getBoundingClientRight();
	}

	public int getBoundingClientTop() {
		if(fence!=null)
			return (int) fence.getBoundingClientTop();
		else 
			return child.getBoundingClientTop();
	}

	public int getBoundingClientBottom() {
		if(fence!=null)
			return (int) (fence.getBoundingClientTop() + fence.getBoundingClientHeight());
		else 
			return child.getBoundingClientBottom();
	}

	public boolean needsSign() {
		return needsSign;
	}

	public void setNeedsSign(boolean needsSign) {
		this.needsSign = needsSign;
	}

}
