package fr.upmf.animaths.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class NewLineEvent extends GwtEvent<NewLineHandler>{

    private static final Type<NewLineHandler> TYPE = new Type<NewLineHandler>();
    
    public static Type<NewLineHandler> getType() {
        return TYPE;
    }

    public NewLineEvent() {
    }
    
	@Override
	protected void dispatch(NewLineHandler handler) {
		handler.onNewLine(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<NewLineHandler> getAssociatedType() {
		return getType();
	}
	
}
