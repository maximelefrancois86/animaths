package fr.upmf.animaths.client.mvp;

import com.google.gwt.user.client.ui.RootPanel;

import fr.upmf.animaths.client.mvp.MathObject.MOElement;



public class MOBasicPresenter extends MOAbstractPresenter<MOBasicDisplay> {

	public MOBasicPresenter(String id) {
		super(id, new MOBasicDisplay());
	}

	public MOBasicPresenter(String id, MOElement<?> element) {
		super(id, element, new MOBasicDisplay());
	}

	@Override
	protected void onBind() {
		RootPanel rootPanel = RootPanel.get(id);
		if(rootPanel!=null) {
			RootPanel.get("view").add(display.asWrapper());
		}
		else {
			RootPanel dyamicRootPanel = RootPanel.get(MODynamicPresenter.id);
			rootPanel.add(display.asWrapper());
		}
	}

}
