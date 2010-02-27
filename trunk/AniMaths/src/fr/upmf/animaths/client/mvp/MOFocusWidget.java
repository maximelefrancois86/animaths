package fr.upmf.animaths.client.mvp;

import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.HTML;

public class MOFocusWidget extends FocusWidget {

	public MOFocusWidget(MOBasicPresenter presenter) {
		super(presenter.getDisplay().asWidget().getElement());
		setStyleName("gwt-Button");
	}

	public MOFocusWidget(String html) {
		super((new HTML("").getElement()));
		setStyleName("gwt-Button");
	}

}
