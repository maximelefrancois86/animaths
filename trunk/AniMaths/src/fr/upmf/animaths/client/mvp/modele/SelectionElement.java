package fr.upmf.animaths.client.mvp.modele;

import net.customware.gwt.presenter.client.EventBus;

import com.google.gwt.event.shared.HandlerRegistration;

import fr.upmf.animaths.client.mvp.MathObject.MathObjectElementPresenter;
import fr.upmf.animaths.client.mvp.events.GrabEvent;
import fr.upmf.animaths.client.mvp.events.GrabHandler;
import fr.upmf.animaths.client.mvp.events.SelectionChangeEvent;
import fr.upmf.animaths.client.mvp.events.SelectionChangeHandler;
import fr.upmf.animaths.client.mvp.events.SelectionEvent;
import fr.upmf.animaths.client.mvp.events.SelectionHandler;

public class SelectionElement implements SelectionHandler, SelectionChangeHandler, GrabHandler {

	private EventBus eventBus = null;
	private MathObjectElementPresenter<?> element = null;
	private HandlerRegistration hrSelection;
	private HandlerRegistration hrSelectionChange;
	private HandlerRegistration hrGrab;
	private boolean enabled = false;

	public SelectionElement(EventBus eventBus) {
		this.eventBus = eventBus;
	}

	public void setElement(MathObjectElementPresenter<?> element) {
		if(this.element==element)
			return;
		if(this.element!=null)
			this.element.setState(MathObjectElementPresenter.STATE_NONE);
		this.element = element;
		if(this.element!=null)
			this.element.setState(MathObjectElementPresenter.STATE_SELECTED);
	}

	public void setEnabled(boolean enabled) {
		if(enabled==false) {
			if(this.element!=null)
				element.setState(MathObjectElementPresenter.STATE_SELECTABLE);
			element = null;
			hrSelection.removeHandler();
			hrSelectionChange.removeHandler();
			hrGrab.removeHandler();
		}
		else {
			hrSelection = eventBus.addHandler(SelectionEvent.getType(), this);
			hrSelectionChange = eventBus.addHandler(SelectionChangeEvent.getType(), this);
			hrGrab = eventBus.addHandler(GrabEvent.getType(),this);
		}
		this.enabled = enabled;
	}

	@Override
	public void onSelect(SelectionEvent event) {
		SelectableElement selectable = event.getSelectableElement();
		switch(event.getState()) {
		case MathObjectElementPresenter.STATE_SELECTABLE:
			MathObjectElementPresenter<?> element = selectable.getElement();
			selectable.setEnabled(false);
			setElement(element);
			break;
		case MathObjectElementPresenter.STATE_SELECTED:
			selectable.setElement(this.element);
			setEnabled(false);
			selectable.setEnabled(true);
			break;
		}
	}

	@Override
	public void onSelectionChange(SelectionChangeEvent event) {
		switch(event.getDirection()) {
		case SelectionChangeEvent.CHANGE_TO_PARENT:
			setElement(element.getMathObjectParent());
			break;
		case SelectionChangeEvent.CHANGE_TO_FIRST_CHILD:
			setElement(element.getMathObjectFirstChild());
			break;
		case SelectionChangeEvent.CHANGE_TO_PREVIOUS_SIBLING:
			setElement(element.getMathObjectParent().getMathObjectPreviousChild(element));
			break;
		case SelectionChangeEvent.CHANGE_TO_NEXT_SIBLING:
			setElement(element.getMathObjectParent().getMathObjectNextChild(element));
			break;
		}
	}

	@Override
	public void onGrab(GrabEvent event) {
		DraggedElement dragged = new DraggedElement(eventBus, element, event);
		dragged.setEnabled(true);
	}

	public boolean isEnabled() {
		return enabled;
	}

	public MathObjectElementPresenter<?> getElement() {
		return element;
	}

}
