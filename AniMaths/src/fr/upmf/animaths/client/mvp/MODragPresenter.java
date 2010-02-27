package fr.upmf.animaths.client.mvp;

import com.google.gwt.user.client.ui.RootPanel;

import fr.upmf.animaths.client.mvp.MathObject.MOElement;

public class MODragPresenter extends MOBasicPresenter {

	public MODragPresenter() {
		super();
	}

	public MODragPresenter(MOElement<?> element) {
		super(element);
	}

	@Override
	protected void onInit() {
		System.out.println("INIT");
		RootPanel.get("drag").add(display.asWidget());
		RootPanel.get("drag").getElement().setAttribute("style","visibility:hidden;");
	}
	
	public void move(int x, int y) {
		System.out.println("MOVE");
		RootPanel.get("drag").getElement().setAttribute("style","left:"+(x+5)+";top:"+(y+10)+";");
	}

	@Override
	protected final void onUnbind() {
		System.out.println("UNBIND");
		super.onUnbind();
		RootPanel.get("drag").getElement().removeAttribute("style");
	}


}
