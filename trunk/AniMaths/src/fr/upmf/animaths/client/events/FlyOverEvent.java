package fr.upmf.animaths.client.events;

import com.google.gwt.event.shared.GwtEvent;

import fr.upmf.animaths.client.mvp.MathObject.MOElement;

public class FlyOverEvent extends GwtEvent<FlyOverHandler>{

    private static final Type<FlyOverHandler> TYPE = new Type<FlyOverHandler>();

    private MOElement<?> element = null;
    
    public static Type<FlyOverHandler> getType() {
        return TYPE;
    }

    public FlyOverEvent(MOElement<?> element) {
    	this.element = element;
    }
    
	@Override
	protected void dispatch(FlyOverHandler handler) {
		handler.onFlyOver(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<FlyOverHandler> getAssociatedType() {
		return getType();
	}
	
	public MOElement<?> getElement() {
		return element;
	}

}
