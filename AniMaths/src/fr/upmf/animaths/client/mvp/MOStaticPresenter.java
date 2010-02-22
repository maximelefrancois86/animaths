package fr.upmf.animaths.client.mvp;

import com.google.gwt.user.client.ui.RootPanel;

import fr.upmf.animaths.client.mvp.MathObject.MOElement;



public class MOStaticPresenter extends MOAbstractPresenter<MOBasicDisplay> {

	public MOStaticPresenter(String id) {
		super(id,new MOBasicDisplay());
	}

	@Override
	protected void onBind() {
		RootPanel.get(id).add(display.asWrapper());
	}

	@Override
	protected void onUnbind() {
		RootPanel.get(id).clear();
		while(RootPanel.get(id).getElement().hasChildNodes())
			RootPanel.get(id).getElement().getFirstChild().removeFromParent();
		RootPanel.get(id).getElement().removeAttribute("style");
	}

	public void init(MOElement<?> element) {
		unbind();
		setElement(element.clone());
		bind();
	}
	
}
