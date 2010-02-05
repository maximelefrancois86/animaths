package fr.upmf.animaths.client.mvp.presenter.MathObject;

import fr.upmf.animaths.client.mvp.display.MathML.MathMLElement;
import fr.upmf.animaths.client.mvp.display.MathML.MathMLOperator;
import fr.upmf.animaths.client.mvp.display.MathObject.IMathObjectHasSign;
import fr.upmf.animaths.client.mvp.display.MathObject.MathObjectElementDisplay;
import fr.upmf.animaths.client.mvp.display.MathObject.MathObjectEquationDisplay;
import fr.upmf.animaths.client.mvp.presenter.MathObjectPresenter;

public class MathObjectEquationPresenter extends MathObjectElementPresenter<MathObjectEquationPresenter.Display> {

	public static short type = MathObjectElementPresenter.MATH_OBJECT_EQUATION;
	@Override
	public short getType() {
		return type;
	}

	private MathObjectElementPresenter<?> leftHandSide;
	private MathObjectElementPresenter<?> rightHandSide;
	
	public interface Display extends MathObjectElementDisplay, IMathObjectHasSign {
	}

	public MathObjectEquationPresenter() {
		super(new MathObjectEquationDisplay());
	}
	
	public MathObjectEquationPresenter(MathObjectElementPresenter<?> leftHandSide, MathObjectElementPresenter<?> rightHandSide) {
		this();
		setLeftHandSide(leftHandSide);
		setRightHandSide(rightHandSide);
	}

	@Override
	public void pack(MathMLElement mathMLParent, MathObjectPresenter<?> presenter) {
		leftHandSide.pack(mathMLParent, presenter);
		display.setSign(MathMLOperator.equality());
		mathMLParent.appendChild(display.getSign());
		if(presenter!=null)
			presenter.putDOMElement(display.getSign().getElement(),this);
		rightHandSide.pack(mathMLParent, presenter);
	}

	@Override
	public MathObjectEquationPresenter clone() {
		return new MathObjectEquationPresenter(leftHandSide.clone(),rightHandSide.clone());
	}

	@Override
	public void setStyleClass(short styleClass) {
		this.styleClass = styleClass;
		leftHandSide.setStyleClass(styleClass);
		display.getSign().setStyleClass(styleClass);
		rightHandSide.setStyleClass(styleClass);
	}

	@Override
	public MathObjectElementPresenter<?> getMathObjectFirstChild() {
		return leftHandSide;
	}

	@Override
	public MathObjectElementPresenter<?> getMathObjectNextChild(MathObjectElementPresenter<?> child) {
		return (child==leftHandSide)?rightHandSide:leftHandSide;
	}

	@Override
	public MathObjectElementPresenter<?> getMathObjectPreviousChild(MathObjectElementPresenter<?> child) {
		return (child==leftHandSide)?rightHandSide:leftHandSide;
	}

	@Override
	public int getBoundingClientBottom() {
		return Math.max(leftHandSide.getBoundingClientBottom(),rightHandSide.getBoundingClientBottom());
	}

	@Override
	public int getBoundingClientLeft() {
		return leftHandSide.getBoundingClientLeft();
	}

	@Override
	public int getBoundingClientTop() {
		return Math.min(leftHandSide.getBoundingClientTop(),rightHandSide.getBoundingClientTop());
	}

	@Override
	public int getBoundingClientRight() {
		return rightHandSide.getBoundingClientRight();
	}

	public MathObjectElementPresenter<?> getLeftHandSide() {
		return leftHandSide;
	}

	public MathObjectElementPresenter<?> getRightHandSide() {
		return rightHandSide;
	}

	public void setLeftHandSide(MathObjectElementPresenter<?> leftHandSide) {
		leftHandSide.setMathObjectParent(this);
		this.leftHandSide = leftHandSide;
	}

	public void setRightHandSide(MathObjectElementPresenter<?> rightHandSide) {
		rightHandSide.setMathObjectParent(this);
		this.rightHandSide = rightHandSide;
	}

}
