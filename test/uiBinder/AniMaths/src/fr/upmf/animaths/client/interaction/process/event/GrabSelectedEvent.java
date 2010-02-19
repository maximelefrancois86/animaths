package fr.upmf.animaths.client.interaction.process.event;

import com.google.gwt.event.shared.GwtEvent;

import fr.upmf.animaths.client.interaction.MOCoreInteraction;

public class GrabSelectedEvent extends GwtEvent<GrabSelectedHandler> {

	private static final Type<GrabSelectedHandler> TYPE = new Type<GrabSelectedHandler>();
	
    public static Type<GrabSelectedHandler> getType() {
        return TYPE;
    }

    private MOCoreInteraction mOCoreInteraction;

    public GrabSelectedEvent(MOCoreInteraction mOCoreInteraction) {
		this.mOCoreInteraction = mOCoreInteraction;
    }
    
	@Override
	protected void dispatch(GrabSelectedHandler handler) {
		handler.onGrabSelected(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<GrabSelectedHandler> getAssociatedType() {
		return getType();
	}
	
	public MOCoreInteraction getSelectionElement() {
		return mOCoreInteraction;
	}

}
