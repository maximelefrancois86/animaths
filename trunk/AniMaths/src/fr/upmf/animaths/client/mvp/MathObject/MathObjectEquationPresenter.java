package fr.upmf.animaths.client.mvp.MathObject;

import com.google.gwt.dom.client.Element;

import fr.upmf.animaths.client.mvp.MathObjectAbtractPresenter;
import fr.upmf.animaths.client.mvp.MathML.MathMLElement;
import fr.upmf.animaths.client.mvp.MathML.MathMLOperator;

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
	public void pack(MathMLElement mathMLParent, MathObjectAbtractPresenter<?> presenter) {
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
	public MathObjectElementPresenter<?> getMathObjectFirstSelectableChild() {
		return leftHandSide;
	}

	@Override
	public MathObjectElementPresenter<?> getMathObjectNextSelectableChild(MathObjectElementPresenter<?> child) {
		return (child==leftHandSide)?rightHandSide:leftHandSide;
	}

	@Override
	public MathObjectElementPresenter<?> getMathObjectPreviousSelectableChild(MathObjectElementPresenter<?> child) {
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

	@Override
	public short getZoneH(int x) {
		int px = 10;
		int pc = 5;
		int left = getBoundingClientLeft();
		int right = getBoundingClientRight();
		int dOut = Math.min(px,(int)((right-left)/pc));
		int leftEq = (int) (display.getSign().getBoundingClientLeft());
		int widthEq = (int) (display.getSign().getBoundingClientWidth());
		int centerEq = (int) (leftEq+widthEq/2);
		int dIn = Math.min(px,(int)(widthEq/pc));
		return getZone(x,left,centerEq,right,dIn,dOut);
	}

	@Override
	public short getZoneV(int y) {
		int px = 10;
		int pc = 5;
		int top = getBoundingClientTop();
		int bottom = getBoundingClientBottom();
		int dOut = Math.min(px,(int)((bottom-top)/pc));
		int topEq = (int) (display.getSign().getBoundingClientTop());
		int heightEq = (int) (display.getSign().getBoundingClientHeight());
		int centerEq = (int) (topEq+heightEq/2);
		int dIn = Math.min(px,(int)(heightEq/pc));
		return getZone(y,top,centerEq,bottom,dIn,dOut);
	}

	@Override
	public Element getFirstDOMElement() {
		return leftHandSide.getFirstDOMElement();
	}

	@Override
	public Element getLastDOMElement() {
		return rightHandSide.getLastDOMElement();
	}
}
