package fr.upmf.animaths.client.mvp.MathObject;

import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NodeList;

import fr.upmf.animaths.client.mvp.MOAbstractPresenter;
import fr.upmf.animaths.client.mvp.MathML.MMLElement;
import fr.upmf.animaths.client.mvp.MathML.MMLOperator;

public class MOEquation extends MOElement<MOEquation.Display> {

	public static short type = MOElement.MATH_OBJECT_EQUATION;
	@Override
	public short getType() {
		return type;
	}

	private MOElement<?> leftHandSide;
	private MOElement<?> rightHandSide;
	
	public interface Display extends MOElementDisplay, IMOHasSign {
	}

	public MOEquation() {
		super(new MOEquationDisplay());
	}
	
	public MOEquation(MOElement<?> leftHandSide, MOElement<?> rightHandSide) {
		this();
		setLeftHandSide(leftHandSide);
		setRightHandSide(rightHandSide);
	}

	@Override
	public void pack(MMLElement mathMLParent, MOAbstractPresenter<?> presenter) {
		leftHandSide.pack(mathMLParent, presenter);
		display.setSign(MMLOperator.equality());
		mathMLParent.appendChild(display.getSign());
		if(presenter!=null)
			presenter.putDOMElement(display.getSign().getElement(),this);
		rightHandSide.pack(mathMLParent, presenter);
	}

	@Override
	public MOEquation clone() {
		return new MOEquation(leftHandSide.clone(),rightHandSide.clone());
	}

	@Override
	public void setStyleClass(short styleClass) {
		this.styleClass = styleClass;
		leftHandSide.setStyleClass(styleClass);
		display.getSign().setStyleClass(styleClass);
		rightHandSide.setStyleClass(styleClass);
	}

	@Override
	public MOElement<?> getMathObjectFirstSelectableChild() {
		return leftHandSide;
	}

	@Override
	public MOElement<?> getMathObjectNextSelectableChild(MOElement<?> child) {
		return (child==leftHandSide)?rightHandSide:leftHandSide;
	}

	@Override
	public MOElement<?> getMathObjectPreviousSelectableChild(MOElement<?> child) {
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

	public MOElement<?> getLeftHandSide() {
		return leftHandSide;
	}

	public MOElement<?> getRightHandSide() {
		return rightHandSide;
	}

	public MOElement<?> getHandSide(boolean atLeft) {
		return (atLeft)?leftHandSide:rightHandSide;
	}

	public void setLeftHandSide(MOElement<?> leftHandSide) {
		leftHandSide.setMathObjectParent(this);
		this.leftHandSide = leftHandSide;
	}

	public void setRightHandSide(MOElement<?> rightHandSide) {
		rightHandSide.setMathObjectParent(this);
		this.rightHandSide = rightHandSide;
	}

	public void setHandSide(MOElement<?> handSide, boolean atLeft) {
		handSide.setMathObjectParent(this);
		if(atLeft)
			this.leftHandSide = handSide;
		else
			this.rightHandSide = handSide;
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

//	@Override
//	public Element getFirstDOMElement() {
//		return leftHandSide.getFirstDOMElement();
//	}
//
//	@Override
//	public Element getLastDOMElement() {
//		return rightHandSide.getLastDOMElement();
//	}
		
	public static MOEquation parse(Element element) {
		assert element.getTagName().equals("moe");
		MOEquation moe = new MOEquation();
		NodeList children = element.getChildNodes();
		short k=0;
		for(int i=0;i<children.getLength();i++) {
			Node n = children.item(i);
			if(n.getNodeType() == Node.ELEMENT_NODE) {
				k++;
				assert k<=2;
				if(k==1)
					moe.setLeftHandSide(MOElement.parse((Element) n));
				else
					moe.setRightHandSide(MOElement.parse((Element) n));
			}
		}
		return moe;
	}

}
