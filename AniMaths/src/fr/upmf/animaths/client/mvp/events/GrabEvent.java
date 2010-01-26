package fr.upmf.animaths.client.mvp.events;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.shared.GwtEvent;

import fr.upmf.animaths.client.mvp.modele.MathObject.MathObjectElement;
import fr.upmf.animaths.client.mvp.widgets.MathML.MathMLElement;

public class GrabEvent extends GwtEvent<GrabHandler>{

    private static final Type<GrabHandler> TYPE = new Type<GrabHandler>();

    private short state;
    private MathObjectElement element;
    private MouseDownEvent event;
    
    public static Type<GrabHandler> getType() {
        return TYPE;
    }

    public GrabEvent(MathMLElement widget, MouseDownEvent event) {
    	this.state = widget.getState();
    	this.element = widget.getMathObjectElement();
    	this.event = event;
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
	
	public MathObjectElement getElement() {
		return element;
	}

}
