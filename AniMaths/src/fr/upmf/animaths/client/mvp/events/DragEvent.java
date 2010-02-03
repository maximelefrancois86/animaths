package fr.upmf.animaths.client.mvp.events;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.shared.GwtEvent;

import fr.upmf.animaths.client.mvp.MathObject.MathObjectElementPresenter;

public class DragEvent extends GwtEvent<DragHandler>{

    private static final Type<DragHandler> TYPE = new Type<DragHandler>();

    private short state;
    private MathObjectElementPresenter<?> element;
    private NativeEvent event;
    
    public static Type<DragHandler> getType() {
        return TYPE;
    }

    public DragEvent(MathObjectElementPresenter<?> element, NativeEvent event) {
//    	this.state = element.getState();
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
	
	public NativeEvent getEvent() {
		return event;
	}
	
	public short getState() {
		return state;
	}
	
	public MathObjectElementPresenter<?> getElement() {
		return element;
	}

}
