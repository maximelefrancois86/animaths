package fr.upmf.animaths.client.interaction.process.event;

import com.google.gwt.event.shared.GwtEvent;

public class DropSelectedEvent extends GwtEvent<DropSelectedHandler>{

    private static final Type<DropSelectedHandler> TYPE = new Type<DropSelectedHandler>();

    public static Type<DropSelectedHandler> getType() {
        return TYPE;
    }
    
    public DropSelectedEvent() {
    }
    
	@Override
	protected void dispatch(DropSelectedHandler handler) {
		handler.onDropSelected(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<DropSelectedHandler> getAssociatedType() {
		return getType();
	}
	
}