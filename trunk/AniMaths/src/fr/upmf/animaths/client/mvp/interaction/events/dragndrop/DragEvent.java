package fr.upmf.animaths.client.mvp.interaction.events.dragndrop;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.shared.GwtEvent;

import fr.upmf.animaths.client.mvp.MathObject.MathObjectElementPresenter;

public class DragEvent extends GwtEvent<DragHandler>{

    private static final Type<DragHandler> TYPE = new Type<DragHandler>();

    private short state = -1;
    private MathObjectElementPresenter<?> element = null;
    private NativeEvent event = null;
    private int clientX = -1;
    private int clientY = -1;
    
    public static Type<DragHandler> getType() {
        return TYPE;
    }

    public DragEvent(MathObjectElementPresenter<?> element, NativeEvent event) {
    	if(element!=null) {
    		this.state = element.getStyleClass();
        	this.element = element;
    	}
    	if(event!=null) {
	    	this.event = event;
	    	this.clientX = event.getClientX();
	    	this.clientY = event.getClientY();
    	}
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

	public int getClientX() {
		return clientX;
	}

	public int getClientY() {
		return clientY;
	}
}
