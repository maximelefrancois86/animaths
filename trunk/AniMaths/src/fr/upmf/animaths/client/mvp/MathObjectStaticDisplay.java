package fr.upmf.animaths.client.mvp;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import fr.upmf.animaths.client.mvp.MathML.MathMLMath;

public class MathObjectStaticDisplay extends Composite implements MathObjectStaticPresenter.Display {

	protected MathMLMath wrapper;

	public MathObjectStaticDisplay() {
		wrapper = new MathMLMath(true);
		initWidget(wrapper);
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void startProcessing() {
	}

	@Override
	public void stopProcessing() {
	}
	
	@Override
	public MathMLMath getWrapper() {
		return wrapper;
	}

	@Override
	public void clearWrapper() {
		while(getElement().hasChildNodes())
			getElement().removeChild(getElement().getFirstChildElement());
	}

}
