package fr.upmf.animaths.client.interaction.events.process;

import com.google.gwt.event.shared.GwtEvent;

public class DropOverEvent extends GwtEvent<DropOverHandler>{

    private static final Type<DropOverHandler> TYPE = new Type<DropOverHandler>();
    
    public static Type<DropOverHandler> getType() {
        return TYPE;
    }

    public DropOverEvent() {
    }
    
	@Override
	protected void dispatch(DropOverHandler handler) {
		handler.onDropOver(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<DropOverHandler> getAssociatedType() {
		return getType();
	}
	
}
