package fr.upmf.animaths.client.mvp.events;

import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.shared.GwtEvent;

import fr.upmf.animaths.client.mvp.MathObject.MathObjectElementPresenter;

public class DropEvent extends GwtEvent<DropHandler>{

    private static final Type<DropHandler> TYPE = new Type<DropHandler>();

    private short state;
    private MathObjectElementPresenter<?> element;
    private MouseUpEvent event;
    
    public static Type<DropHandler> getType() {
        return TYPE;
    }

    public DropEvent(MathObjectElementPresenter<?> object, MouseUpEvent event) {
    	this.state = object.getState();
    	this.element = object;
    	this.event = event;
    }
    
	@Override
	protected void dispatch(DropHandler handler) {
		handler.onDrop(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<DropHandler> getAssociatedType() {
		return getType();
	}
	
	public MouseUpEvent getEvent() {
		return event;
	}
	
	public short getState() {
		return state;
	}
	
	public MathObjectElementPresenter<?> getElement() {
		return element;
	}

}
