package fr.upmf.animaths.client.mvp.MathObject;

import fr.upmf.animaths.client.mvp.widgets.MathML.MathMLElement;
import fr.upmf.animaths.client.mvp.widgets.MathML.MathMLIdentifier;


public class MathObjectIdentifierPresenter extends MathObjectElementPresenter<MathObjectIdentifierPresenter.Display> {

	public static final short type = MathObjectElementPresenter.MATH_OBJECT_IDENTIFIER;
	public short getType() {
		return type;
	}

	public String name;
	
	public interface Display extends MathObjectElementView {
		public void setElement(MathMLIdentifier element);
		public MathMLIdentifier getElement();
	}

	public MathObjectIdentifierPresenter(String name) {
		super(new MathObjectIdentifierView());
		this.name = name;
	}
	
	@Override
	public void pack(MathMLElement mathMLParent) {
		display.setElement(new MathMLIdentifier(name));
		mathMLParent.appendChild(display.getElement());
		map.put(display.getElement().getElement(),this);
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
		return this;
	}

	@Override
	public MathObjectElementPresenter<?> getMathObjectPreviousChild(MathObjectElementPresenter<?> child) {
		return this;
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
