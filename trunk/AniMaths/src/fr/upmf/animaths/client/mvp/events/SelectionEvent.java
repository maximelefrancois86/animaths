package fr.upmf.animaths.client.mvp.events;

import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.shared.GwtEvent;

import fr.upmf.animaths.client.mvp.MathObject.MathObjectElementPresenter;
import fr.upmf.animaths.client.mvp.modele.SelectionElement;

public class SelectionEvent extends GwtEvent<SelectionHandler>{

    private static final Type<SelectionHandler> TYPE = new Type<SelectionHandler>();

    private short state;
    private SelectionElement selectable;
    private MouseUpEvent event;
    
    public static Type<SelectionHandler> getType() {
        return TYPE;
    }

    public SelectionEvent(MathObjectElementPresenter<?> element, SelectionElement selectable, MouseUpEvent event) {
    	this.state = element.getState();
    	this.selectable = selectable;
    	this.event = event;
    }
    
	@Override
	protected void dispatch(SelectionHandler handler) {
		handler.onSelect(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<SelectionHandler> getAssociatedType() {
		return getType();
	}
	
	public short getState() {
		return state;
	}

	public MouseUpEvent getEvent() {
		return event;
	}
	
	public SelectionElement getSelectableElement() {
		return selectable;
	}

}
