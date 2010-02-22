package fr.upmf.animaths.client.mvp;

import com.google.gwt.user.client.ui.RootPanel;

public class MODragPresenter extends MOStaticPresenter {

	public MODragPresenter() {
		super("drag");
	}

	@Override
	protected void onBind() {
		super.onBind();
		RootPanel.get(id).getElement().setAttribute("style","visibility:hidden;");
	}

	public void move(int x, int y) {
		RootPanel.get("drag").getElement().setAttribute("style","left:"+(x+5)+";top:"+(y+10)+";");
	}
	
}
