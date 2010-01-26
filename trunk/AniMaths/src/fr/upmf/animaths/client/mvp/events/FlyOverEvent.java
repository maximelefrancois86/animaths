package fr.upmf.animaths.client.mvp.events;

import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.shared.GwtEvent;

import fr.upmf.animaths.client.mvp.modele.MathObject.MathObjectElement;
import fr.upmf.animaths.client.mvp.widgets.MathML.MathMLElement;

public class FlyOverEvent extends GwtEvent<FlyOverHandler>{

    private static final Type<FlyOverHandler> TYPE = new Type<FlyOverHandler>();

    private short state;
    private MathObjectElement element;
    private MouseMoveEvent event;
    
    public static Type<FlyOverHandler> getType() {
        return TYPE;
    }

    public FlyOverEvent(MathMLElement widget, MouseMoveEvent event) {
    	this.state = widget.getState();
    	this.element = widget.getMathObjectElement();
    	this.event = event;
    }
    
	@Override
	protected void dispatch(FlyOverHandler handler) {
		handler.onFlyOver(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<FlyOverHandler> getAssociatedType() {
		return getType();
	}
	
	public MouseMoveEvent getEvent() {
		return event;
	}
	
	public short getState() {
		return state;
	}
	
	public MathObjectElement getElement() {
		return element;
	}

}
