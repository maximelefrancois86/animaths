package fr.upmf.animaths.client.mvp.modele;

import com.google.gwt.event.shared.HandlerRegistration;

import net.customware.gwt.presenter.client.EventBus;
import fr.upmf.animaths.client.mvp.events.FlyOverEvent;
import fr.upmf.animaths.client.mvp.events.FlyOverHandler;
import fr.upmf.animaths.client.mvp.events.SelectionEvent;
import fr.upmf.animaths.client.mvp.modele.MathObject.MathObjectElement;

public class SelectableElement implements FlyOverHandler {
	
	private EventBus eventBus = null;
	private MathObjectElement element = null;
	private boolean enabled = false;
	private HandlerRegistration hrFlyOver;
	
	public SelectableElement(EventBus eventBus) {
		this.eventBus = eventBus;
	}
	
	public MathObjectElement getElement() {
		return element;
	}

	public void setElement(MathObjectElement element) {
		if(this.element==element)
			return;
		if(this.element!=null)
			this.element.setState(MathObjectElement.STATE_NONE);
		this.element = element;
		if(this.element!=null) {
			this.element.setState(MathObjectElement.STATE_SELECTABLE);
			if(eventBus.getHandlerCount(SelectionEvent.getType())==0) {
				SelectionElement selection = new SelectionElement(eventBus);
				selection.setEnabled(true);				
			}
		}
	}

	public void setEnabled(boolean enabled) {
		if(enabled==false) {
			if(this.element!=null)
				element.setState(MathObjectElement.STATE_NONE);
			element = null;
			hrFlyOver.removeHandler();
		}
		else
			hrFlyOver = eventBus.addHandler(FlyOverEvent.getType(), this);
		this.enabled = enabled;
	}

	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public void onFlyOver(FlyOverEvent event) {
		setElement(event.getElement());
	}

}
