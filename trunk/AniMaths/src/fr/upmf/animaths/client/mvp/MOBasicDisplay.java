package fr.upmf.animaths.client.mvp;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.upmf.animaths.client.mvp.MathML.MMLMath;

public class MOBasicDisplay extends Composite implements MOAbstractPresenter.Display {

	protected FlowPanel panel;
	protected MMLMath wrapper;

	public MOBasicDisplay() {
		panel = new FlowPanel();
		wrapper = new MMLMath(true);
		panel.add(wrapper);
		initWidget(panel);
	}

	public void init() {
		panel.clear();
		wrapper.clear();
		wrapper = new MMLMath(true);
		panel.add(wrapper);
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
