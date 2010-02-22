package fr.upmf.animaths.client.mvp;

import com.google.gwt.user.client.ui.RootPanel;

import fr.upmf.animaths.client.mvp.MathObject.MOElement;

public class MODragPresenter extends MOBasicPresenter {

	static final String id = "drag";

	public MODragPresenter() {
		super(id);
	}

	public MODragPresenter(MOElement<?> element) {
		super(id, element);
	}

	@Override
	protected void onBind() {
		super.onBind();
		RootPanel.get("view").add(display.asWrapper());
		RootPanel.get(id).getElement().setAttribute("style","visibility:hidden;");
	}

	public void move(int x, int y) {
		RootPanel.get(id).getElement().setAttribute("style","left:"+(x+5)+";top:"+(y+10)+";");
	}
	
}
