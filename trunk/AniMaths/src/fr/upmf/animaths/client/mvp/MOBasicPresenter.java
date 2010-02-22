package fr.upmf.animaths.client.mvp;

import com.google.gwt.user.client.ui.RootPanel;

import fr.upmf.animaths.client.mvp.MathObject.MOElement;



public class MOBasicPresenter extends MOAbstractPresenter<MOBasicDisplay> {

	public MOBasicPresenter() {
		super(new MOBasicDisplay());
	}

	public MOBasicPresenter(MOElement<?> element) {
		super(element, new MOBasicDisplay());
	}

	@Override
	protected void onInit() {
		RootPanel.get("view").insert(display.asWidget(), RootPanel.get("view").getWidgetCount()-1);
	}
	
	@Override
	protected final void onBind() { }

}
