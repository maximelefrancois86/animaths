package fr.upmf.animaths.client.mvp.MathObject;

import fr.upmf.animaths.client.mvp.widgets.MathML.MathMLElement;
import fr.upmf.animaths.client.mvp.widgets.MathML.MathMLOperator;

public class MathObjectMultiplyElementPresenter extends MathObjectElementPresenter<MathObjectMultiplyElementPresenter.Display> {

	public static final short type = MathObjectElementPresenter.MATH_OBJECT_MULTIPLY_ELEMENT;
	public short getType() {
		return type;
	}

	private boolean isDivided = false;
	private boolean needsSign = true;
	private MathObjectElementPresenter<?> child;

	public interface Display extends MathObjectElementView {
		abstract public void setSign(MathMLOperator sign);
		abstract public MathMLOperator getSign();
	}

	public MathObjectMultiplyElementPresenter() {
		super(new MathObjectMultiplyElementView());
	}

	public MathObjectMultiplyElementPresenter(MathObjectElementPresenter<?> child) {
		this();
		setChild(child);
	}
	
	public MathObjectMultiplyElementPresenter(MathObjectElementPresenter<?> child, boolean isDivided) {
		this(child);
		setDivided(isDivided);
	}
	
	public void pack(MathMLElement mathMLParent) {
		if(needsSign) {
			display.setSign(MathMLOperator.times());
			mathMLParent.appendChild(display.getSign());
			map.put(display.getSign().getElement(),this);
		}
		child.pack(mathMLParent);
	}

	public MathObjectMultiplyElementPresenter clone() {
		return new MathObjectMultiplyElementPresenter(child.clone(),isDivided);
	}

	public void setState(short state) {
		this.state = state;
		if(display.getSign()!=null)
			display.getSign().setState(state);
		child.setState(state);
	}

	public MathObjectElementPresenter<?> getMathObjectFirstChild() {
		return child;
	}

	public MathObjectElementPresenter<?> getMathObjectNextChild(MathObjectElementPresenter<?> child) {
		return mathObjectParent.getMathObjectNextChild(this);
	}

	public MathObjectElementPresenter<?> getMathObjectPreviousChild(MathObjectElementPresenter<?> child) {
		return mathObjectParent.getMathObjectPreviousChild(this);
	}

	public int getBoundingClientLeft() {
		if(display.getSign()!=null)
			return (int) display.getSign().getBoundingClientLeft();
		else
			return child.getBoundingClientLeft();
	}

	public int getBoundingClientRight() {
		return child.getBoundingClientRight();
	}

	public int getBoundingClientTop() {
		return child.getBoundingClientTop();
	}

	public int getBoundingClientBottom() {
			return child.getBoundingClientBottom();
	}

	public void setNeedsSign(boolean needsSign) {
		this.needsSign = needsSign;
	}

	public boolean needsSign() {
		return needsSign;
	}

	public boolean isDivided() {
		return isDivided;
	}

	public MathObjectElementPresenter<?> getChild() {
		return child;
	}
	
	public void setDivided(boolean isDivided) {
		this.isDivided = isDivided;
	}		

	public void setChild(MathObjectElementPresenter<?> child) {
		child.setMathObjectParent(this);
		this.child = child;
	}

}
