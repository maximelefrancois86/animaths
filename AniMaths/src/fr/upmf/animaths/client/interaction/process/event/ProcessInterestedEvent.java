package fr.upmf.animaths.client.interaction.process.event;

import com.google.gwt.event.shared.GwtEvent;

public class ProcessInterestedEvent extends GwtEvent<ProcessInterestedHandler> {

	private static final Type<ProcessInterestedHandler> TYPE = new Type<ProcessInterestedHandler>();
	
    public static Type<ProcessInterestedHandler> getType() {
        return TYPE;
    }

    public ProcessInterestedEvent() {
    }
    
	@Override
	protected void dispatch(ProcessInterestedHandler handler) {
		handler.onProcessInterested(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ProcessInterestedHandler> getAssociatedType() {
		return getType();
	}
	
}
