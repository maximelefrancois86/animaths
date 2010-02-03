package fr.upmf.animaths.client.mvp.modele;

import net.customware.gwt.presenter.client.EventBus;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.RootPanel;

import fr.upmf.animaths.client.mvp.StaticMathObjectPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectElementPresenter;
import fr.upmf.animaths.client.mvp.events.DragEvent;
import fr.upmf.animaths.client.mvp.events.DragHandler;
import fr.upmf.animaths.client.mvp.events.DropEvent;
import fr.upmf.animaths.client.mvp.events.DropHandler;
import fr.upmf.animaths.client.mvp.events.GrabEvent;

public class DraggedElement implements DragHandler, DropHandler {

	private EventBus eventBus = null;
	private MathObjectElementPresenter<?> element = null;
	private StaticMathObjectPresenter copy = new StaticMathObjectPresenter();
	private HandlerRegistration hrDrag;
	private HandlerRegistration hrDrop;
	private boolean enabled = false;
	private int clientX0;
	private int clientY0;

	public DraggedElement(EventBus eventBus, MathObjectElementPresenter<?> element, GrabEvent event) {
		this.eventBus = eventBus;
		this.element = element;
		this.clientX0 = event.getEvent().getClientX();
		this.clientY0 = event.getEvent().getClientY();
		copy.setElement(element.clone());
		RootPanel.get("drag").add(copy.getDisplay().asWidget(),clientX0,clientY0);
		RootPanel.get("drag").getElement().setAttribute("style","left:"+(clientX0+10)+";top:"+(clientY0+10)+";");
	}
		
	public void setEnabled(boolean enabled) {
		if(enabled==false) {
			if(this.element!=null)
				element.setState(MathObjectElementPresenter.STATE_SELECTABLE);
			element = null;
			hrDrag.removeHandler();
			hrDrop.removeHandler();
		}
		else {
			hrDrag = eventBus.addHandler(DragEvent.getType(), this);
			hrDrop = eventBus.addHandler(DropEvent.getType(), this);
		}
		this.enabled = enabled;
	}

	@Override
	public void onDrag(DragEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDrop(DropEvent event) {
		setEnabled(false);
	}

	public boolean isEnabled() {
		return enabled;
	}

	public MathObjectElementPresenter<?> getElement() {
		return element;
	}

}
