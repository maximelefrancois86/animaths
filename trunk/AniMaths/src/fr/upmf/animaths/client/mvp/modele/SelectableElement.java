package fr.upmf.animaths.client.mvp.modele;

import net.customware.gwt.presenter.client.EventBus;

import com.google.gwt.event.shared.HandlerRegistration;

import fr.upmf.animaths.client.mvp.MathObject.MathObjectElementPresenter;
import fr.upmf.animaths.client.mvp.events.FlyOverEvent;
import fr.upmf.animaths.client.mvp.events.FlyOverHandler;
import fr.upmf.animaths.client.mvp.events.SelectionEvent;

public class SelectableElement implements FlyOverHandler {
	
	private EventBus eventBus = null;
	private MathObjectElementPresenter<?> element = null;
	private boolean enabled = false;
	private HandlerRegistration hrFlyOver;
	
	public SelectableElement(EventBus eventBus) {
		this.eventBus = eventBus;
	}
	
	public MathObjectElementPresenter<?> getElement() {
		return element;
	}

	public void setElement(MathObjectElementPresenter<?> element) {
		if(this.element==element)
			return;
		if(this.element!=null)
			this.element.setState(MathObjectElementPresenter.STATE_NONE);
		this.element = element;
		if(this.element!=null) {
			this.element.setState(MathObjectElementPresenter.STATE_SELECTABLE);
			if(eventBus.getHandlerCount(SelectionEvent.getType())==0) {
				SelectionElement selection = new SelectionElement(eventBus);
				selection.setEnabled(true);				
			}
		}
	}

	public void setEnabled(boolean enabled) {
		if(enabled==false) {
			if(this.element!=null)
				element.setState(MathObjectElementPresenter.STATE_NONE);
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
