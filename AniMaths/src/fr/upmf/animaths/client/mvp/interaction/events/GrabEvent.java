package fr.upmf.animaths.client.mvp.interaction.events;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.shared.GwtEvent;

import fr.upmf.animaths.client.mvp.MathObject.MathObjectElementPresenter;

public class GrabEvent extends GwtEvent<GrabHandler>{

    private static final Type<GrabHandler> TYPE = new Type<GrabHandler>();

    private short state;
    private MathObjectElementPresenter<?> element;
    private MouseDownEvent event;
    private int clientX;
    private int clientY;
    
    public static Type<GrabHandler> getType() {
        return TYPE;
    }

    public GrabEvent(MathObjectElementPresenter<?> object, MouseDownEvent event) {
    	this.state = object.getState();
    	this.element = object;
    	this.event = event;
    	this.clientX = event.getClientX();
    	this.clientY = event.getClientY();
    }
    
	@Override
	protected void dispatch(GrabHandler handler) {
		handler.onGrab(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<GrabHandler> getAssociatedType() {
		return getType();
	}
	
	public MouseDownEvent getEvent() {
		return event;
	}
	
	public short getState() {
		return state;
	}
	
	public MathObjectElementPresenter<?> getElement() {
		return element;
	}

	public int getClientX() {
		return clientX;
	}

	public int getClientY() {
		return clientY;
	}
}
