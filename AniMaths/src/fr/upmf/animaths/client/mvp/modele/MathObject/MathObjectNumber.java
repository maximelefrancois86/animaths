package fr.upmf.animaths.client.mvp.modele.MathObject;

import fr.upmf.animaths.client.mvp.widgets.MathML.MathMLElement;
import fr.upmf.animaths.client.mvp.widgets.MathML.MathMLNumber;

public class MathObjectNumber extends MathObjectElement {

	public static final short type = MathObjectElement.MATH_OBJECT_NUMBER;

	private MathMLNumber element;
	
	public MathObjectNumber(Number value) {
		element = new MathMLNumber(this,value);
	}
	
	public MathObjectNumber(String strValue) {
		element = new MathMLNumber(this,strValue);
	}

	public short getType() {
		return type;
	}

	public void pack(MathMLElement mathMLParent) {
		mathMLParent.appendChild(element);
	}

	public void setState(short state) {
		element.setState(state);		
	}

	public MathObjectElement getMathObjectFirstChild() {
		return this;
	}

	public MathObjectElement getMathObjectNextChild(MathObjectElement child) {
		return this;
	}

	public MathObjectElement getMathObjectPreviousChild(MathObjectElement child) {
		return this;
	}

	public MathObjectNumber clone() {
		return new MathObjectNumber(element.getElement().getInnerText());
	}

	public int getBoundingClientBottom() {
		return (int) (element.getBoundingClientTop()+element.getBoundingClientHeight());
	}

	public int getBoundingClientLeft() {
		return (int) element.getBoundingClientLeft();
	}

	public int getBoundingClientTop() {
		return (int) element.getBoundingClientTop();
	}

	public int getBoundingClientRight() {
		return (int) (element.getBoundingClientLeft()+element.getBoundingClientWidth());
	}
}
