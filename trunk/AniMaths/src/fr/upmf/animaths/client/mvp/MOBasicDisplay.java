package fr.upmf.animaths.client.mvp;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import fr.upmf.animaths.client.mvp.MathML.MMLMath;

public class MOBasicDisplay extends Composite implements MOAbstractPresenter.Display {

	protected MMLMath wrapper;

	public MOBasicDisplay() {
		wrapper = new MMLMath(true);
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
	public MMLMath asWrapper() {
		return wrapper;
	}

}
