package fr.upmf.animaths.client.mvp;

import com.google.gwt.user.client.ui.RootPanel;

import net.customware.gwt.presenter.client.EventBus;

public class StaticMathObjectPresenter extends MathObjectPresenter<StaticMathObjectPresenter.Display> {

	public interface Display extends MathObjectView {
	}
	
	public StaticMathObjectPresenter(final Display display, final EventBus eventBus) {
		super(display, eventBus);
		RootPanel.get("view").add(display.asWidget());
	}

}
