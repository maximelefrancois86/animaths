package fr.upmf.animaths.client.mvp.presenter.MathObject;

import fr.upmf.animaths.client.mvp.display.MathML.MathMLElement;
import fr.upmf.animaths.client.mvp.display.MathML.MathMLIdentifier;
import fr.upmf.animaths.client.mvp.display.MathObject.MathObjectElementDisplay;
import fr.upmf.animaths.client.mvp.display.MathObject.MathObjectIdentifierDisplay;
import fr.upmf.animaths.client.mvp.presenter.MathObjectPresenter;


public class MathObjectIdentifierPresenter extends MathObjectElementPresenter<MathObjectIdentifierPresenter.Display> implements IMathObjectHasValue<String>{

	public static final short type = MathObjectElementPresenter.MATH_OBJECT_IDENTIFIER;
	@Override
	public short getType() {
		return type;
	}

	public String value;
	
	public interface Display extends MathObjectElementDisplay {
		public void setElement(MathMLIdentifier element);
		public MathMLIdentifier getElement();
	}

	public MathObjectIdentifierPresenter(String value) {
		super(new MathObjectIdentifierDisplay());
		this.value = value;
	}
	
	@Override
	public void pack(MathMLElement mathMLParent, MathObjectPresenter<?> presenter) {
		display.setElement(new MathMLIdentifier(value));
		mathMLParent.appendChild(display.getElement());
		if(presenter!=null)
			presenter.putDOMElement(display.getElement().getElement(),this);
	}

	@Override
	public MathObjectIdentifierPresenter clone() {
		return new MathObjectIdentifierPresenter(value);
	}

	@Override
	public void setStyleClass(short styleClass) {
		this.styleClass = styleClass;
		display.getElement().setStyleClass(styleClass);
	}

	@Override
	public MathObjectElementPresenter<?> getMathObjectFirstChild() {
		return this;
	}
	
	@Override
	public MathObjectElementPresenter<?> getMathObjectNextChild(MathObjectElementPresenter<?> child) {
		return mathObjectParent.getMathObjectNextChild(this);
	}

	@Override
	public MathObjectElementPresenter<?> getMathObjectPreviousChild(MathObjectElementPresenter<?> child) {
		return mathObjectParent.getMathObjectPreviousChild(this);
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
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}

}
