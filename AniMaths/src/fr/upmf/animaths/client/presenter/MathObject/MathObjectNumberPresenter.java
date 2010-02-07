package fr.upmf.animaths.client.presenter.MathObject;

import com.google.gwt.dom.client.Element;

import fr.upmf.animaths.client.display.MathML.MathMLElement;
import fr.upmf.animaths.client.display.MathML.MathMLNumber;
import fr.upmf.animaths.client.display.MathObject.MathObjectElementDisplay;
import fr.upmf.animaths.client.display.MathObject.MathObjectNumberDisplay;
import fr.upmf.animaths.client.presenter.MathObjectPresenter;
import fr.upmf.animaths.client.presenter.MathObject.Interfaces.IMathObjectHasValue;


public class MathObjectNumberPresenter extends MathObjectElementPresenter<MathObjectNumberPresenter.Display> implements IMathObjectHasValue<Number>{

	public static final short type = MathObjectElementPresenter.MATH_OBJECT_NUMBER;
	@Override
	public short getType() {
		return type;
	}

	public Number value;
	
	public interface Display extends MathObjectElementDisplay {
		public void setElement(MathMLNumber element);
		public MathMLNumber getElement();
	}

	public MathObjectNumberPresenter(Number value) {
		super(new MathObjectNumberDisplay());
		this.value = value;
	}
	
	@Override
	public void pack(MathMLElement mathMLParent, MathObjectPresenter<?> presenter) {
		display.setElement(new MathMLNumber(value));
		mathMLParent.appendChild(display.getElement());
		if(presenter!=null)
			presenter.putDOMElement(display.getElement().getElement(),this);
	}

	@Override
	public MathObjectNumberPresenter clone() {
		return new MathObjectNumberPresenter(value);
	}

	@Override
	public void setStyleClass(short styleClass) {
		this.styleClass = styleClass;
		display.getElement().setStyleClass(styleClass);
	}

	@Override
	public MathObjectElementPresenter<?> getMathObjectFirstSelectableChild() {
		return this;
	}
	
	@Override
	public MathObjectElementPresenter<?> getMathObjectNextSelectableChild(MathObjectElementPresenter<?> child) {
		return mathObjectParent.getMathObjectNextSelectableChild(this);
	}

	@Override
	public MathObjectElementPresenter<?> getMathObjectPreviousSelectableChild(MathObjectElementPresenter<?> child) {
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

	@Override
	public Element getFirstDOMElement() {
		return display.getElement().getElement();
	}

	@Override
	public Element getLastDOMElement() {
		return display.getElement().getElement();
	}

}
