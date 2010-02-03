package fr.upmf.animaths.client.mvp.interaction.events;

import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.shared.GwtEvent;

import fr.upmf.animaths.client.mvp.MathObject.MathObjectElementPresenter;

public class FlyOverEvent extends GwtEvent<FlyOverHandler>{

    private static final Type<FlyOverHandler> TYPE = new Type<FlyOverHandler>();

    private short state;
    private MathObjectElementPresenter<?> element;
    private MouseMoveEvent event;
    
    public static Type<FlyOverHandler> getType() {
        return TYPE;
    }

    public FlyOverEvent(MathObjectElementPresenter<?> element, MouseMoveEvent event) {
    	this.state = element.getState();
    	this.element = element;
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
	
	public MathObjectElementPresenter<?> getElement() {
		return element;
	}

}
