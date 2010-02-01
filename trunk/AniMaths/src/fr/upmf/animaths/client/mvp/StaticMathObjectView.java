package fr.upmf.animaths.client.mvp;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.upmf.animaths.client.mvp.widgets.MathML.MathMLMath;

public class StaticMathObjectView extends Composite implements StaticMathObjectPresenter.Display {

	protected MathMLMath wrapper;

	public StaticMathObjectView() {
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
	public void setWrapper(MathMLMath wrapper) {
		RootPanel.get("view").remove(this.wrapper);
		this.wrapper = wrapper;
		initWidget(wrapper);
		RootPanel.get("view").add(wrapper);
	}

}
