package fr.upmf.animaths.client.interaction.process.event;

import com.google.gwt.event.shared.GwtEvent;

public class ProcessDoneEvent extends GwtEvent<ProcessDoneHandler> {

	private static final Type<ProcessDoneHandler> TYPE = new Type<ProcessDoneHandler>();
	
    public static Type<ProcessDoneHandler> getType() {
        return TYPE;
    }

    public ProcessDoneEvent() {
    }
    
	@Override
	protected void dispatch(ProcessDoneHandler handler) {
		handler.onProcessDone(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ProcessDoneHandler> getAssociatedType() {
		return getType();
	}
	
}
