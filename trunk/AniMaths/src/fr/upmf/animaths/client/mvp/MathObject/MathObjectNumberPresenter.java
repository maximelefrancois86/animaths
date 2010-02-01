package fr.upmf.animaths.client.mvp.MathObject;

import fr.upmf.animaths.client.mvp.widgets.MathML.MathMLElement;
import fr.upmf.animaths.client.mvp.widgets.MathML.MathMLNumber;


public class MathObjectNumberPresenter extends MathObjectElementPresenter<MathObjectNumberPresenter.Display> {

	public static final short type = MathObjectElementPresenter.MATH_OBJECT_NUMBER;
	public short getType() {
		return type;
	}

	public Number value;
	
	public interface Display extends MathObjectElementView {
		public void setElement(MathMLNumber element);
		public MathMLNumber getElement();
	}

	public MathObjectNumberPresenter(Number value) {
		super(new MathObjectNumberView());
		this.value = value;
	}
	
	@Override
	public void pack(MathMLElement mathMLParent) {
		display.setElement(new MathMLNumber(value));
		mathMLParent.appendChild(display.getElement());
		map.put(display.getElement().getElement(),this);
	}

	@Override
	public MathObjectNumberPresenter clone() {
		return new MathObjectNumberPresenter(value);
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

	public void setValue(Number value) {
		this.value = value;
	}

	public Number getValue() {
		return value;
	}

}
