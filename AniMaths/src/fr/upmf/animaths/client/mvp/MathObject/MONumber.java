package fr.upmf.animaths.client.mvp.MathObject;

import com.google.gwt.xml.client.Element;

import fr.upmf.animaths.client.mvp.MOAbtractPresenter;
import fr.upmf.animaths.client.mvp.MathML.MMLElement;
import fr.upmf.animaths.client.mvp.MathML.MMLNumber;


public class MONumber extends MOElement<MONumber.Display> implements IMOHasValue<Number>{

	public static final short type = MOElement.MATH_OBJECT_NUMBER;
	@Override
	public short getType() {
		return type;
	}

	public Number value;
	
	public interface Display extends MOElementDisplay {
		public void setElement(MMLNumber element);
		public MMLNumber getElement();
	}

	public MONumber(Number value) {
		super(new MONumberDisplay());
		this.value = value;
	}
	
	@Override
	public void pack(MMLElement mathMLParent, MOAbtractPresenter<?> presenter) {
		display.setElement(new MMLNumber(value));
		mathMLParent.appendChild(display.getElement());
		if(presenter!=null)
			presenter.putDOMElement(display.getElement().getElement(),this);
	}

	@Override
	public MONumber clone() {
		return new MONumber(value);
	}

	@Override
	public void setStyleClass(short styleClass) {
		this.styleClass = styleClass;
		display.getElement().setStyleClass(styleClass);
	}

	@Override
	public MOElement<?> getMathObjectFirstSelectableChild() {
		return this;
	}
	
	@Override
	public MOElement<?> getMathObjectNextSelectableChild(MOElement<?> child) {
		return mathObjectParent.getMathObjectNextSelectableChild(this);
	}

	@Override
	public MOElement<?> getMathObjectPreviousSelectableChild(MOElement<?> child) {
		return mathObjectParent.getMathObjectPreviousSelectableChild(this);
	}

	@Override
	public int getBoundingClientBottom() {
		return (int) (display.getElement().getBoundingClientTop()+display.getElement().getBoundingClientHeight());
	}

	@Override
	public int getBoundingClientLeft() {
		return (int) display.getElement().getBoundingClientLeft();
	}

	@Override
	public int getBoundingClientTop() {
		return (int) display.getElement().getBoundingClientTop();
	}

	@Override
	public int getBoundingClientRight() {
		return (int) (display.getElement().getBoundingClientLeft()+display.getElement().getBoundingClientWidth());
	}

	@Override
	public void setValue(Number value) {
		this.value = value;
	}

	@Override
	public Number getValue() {
		return value;
	}

//	@Override
//	public Element getFirstDOMElement() {
//		return display.getElement().getElement();
//	}
//
//	@Override
//	public Element getLastDOMElement() {
//		return display.getElement().getElement();
//	}

	public static MOElement<?> parse(Element element) {
		return new MOIdentifier(element.getFirstChild().getNodeValue());
	}
}
