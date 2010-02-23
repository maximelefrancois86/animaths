package fr.upmf.animaths.client.interaction.process.event;

import com.google.gwt.event.shared.GwtEvent;

public class ProcessLaunchEvent extends GwtEvent<ProcessLaunchHandler> {

	private static final Type<ProcessLaunchHandler> TYPE = new Type<ProcessLaunchHandler>();
	
    public static Type<ProcessLaunchHandler> getType() {
        return TYPE;
    }

    public ProcessLaunchEvent() {
    }
    
	@Override
	protected void dispatch(ProcessLaunchHandler handler) {
		handler.onProcessLaunch(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ProcessLaunchHandler> getAssociatedType() {
		return getType();
	}
	
}
