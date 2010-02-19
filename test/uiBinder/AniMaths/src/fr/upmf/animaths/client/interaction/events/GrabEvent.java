package fr.upmf.animaths.client.interaction.events;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.shared.GwtEvent;

public class GrabEvent extends GwtEvent<GrabHandler>{

    private static final Type<GrabHandler> TYPE = new Type<GrabHandler>();

    private short styleClass;
    private int clientX;
    private int clientY;
    
    public static Type<GrabHandler> getType() {
        return TYPE;
    }

    public GrabEvent(short styleClass, MouseDownEvent event) {
    	this.styleClass = styleClass;
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
	
	public short getStyleClass() {
		return styleClass;
	}
	
	public int getClientX() {
		return clientX;
	}

	public int getClientY() {
		return clientY;
	}
}
