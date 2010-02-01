package fr.upmf.animaths.client.mvp.events;

import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.shared.GwtEvent;

import fr.upmf.animaths.client.mvp.MathObject.MathObjectElementPresenter;

public class DragEvent extends GwtEvent<DragHandler>{

    private static final Type<DragHandler> TYPE = new Type<DragHandler>();

    private short state;
    private MathObjectElementPresenter<?> element;
    private MouseMoveEvent event;
    
    public static Type<DragHandler> getType() {
        return TYPE;
    }

    public DragEvent(MathObjectElementPresenter<?> element, MouseMoveEvent event) {
    	this.state = element.getState();
    	this.element = element;
    	this.event = event;
    }
    
	@Override
	protected void dispatch(DragHandler handler) {
		handler.onDrag(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<DragHandler> getAssociatedType() {
		return getType();
	}
	
	public MouseMoveEvent getEvent() {
		return event;
	}
	
	public short getState() {
		return state;
	}
	
	public MathObjectElementPresenter<?> getElement() {
		return element;
	}

}
