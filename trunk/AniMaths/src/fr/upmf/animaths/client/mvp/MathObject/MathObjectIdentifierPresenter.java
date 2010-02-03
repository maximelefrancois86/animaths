package fr.upmf.animaths.client.mvp.MathObject;

import fr.upmf.animaths.client.mvp.MathObjectPresenter;
import fr.upmf.animaths.client.mvp.MathML.MathMLElement;
import fr.upmf.animaths.client.mvp.MathML.MathMLIdentifier;


public class MathObjectIdentifierPresenter extends MathObjectElementPresenter<MathObjectIdentifierPresenter.Display> {

	public static final short type = MathObjectElementPresenter.MATH_OBJECT_IDENTIFIER;
	public short getType() {
		return type;
	}

	public String name;
	
	public interface Display extends MathObjectElementDisplay {
		public void setElement(MathMLIdentifier element);
		public MathMLIdentifier getElement();
	}

	public MathObjectIdentifierPresenter(String name) {
		super(new MathObjectIdentifierDisplay());
		this.name = name;
	}
	
	@Override
	public void pack(MathMLElement mathMLParent, MathObjectPresenter<?> presenter) {
		display.setElement(new MathMLIdentifier(name));
		mathMLParent.appendChild(display.getElement());
		if(presenter!=null)
			presenter.putDOMElement(display.getElement().getElement(),this);
	}

	@Override
	public MathObjectIdentifierPresenter clone() {
		return new MathObjectIdentifierPresenter(name);
	}

	@Override
	public void setState(short state) {
		this.state = state;
		display.getElement().setState(state);
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

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
