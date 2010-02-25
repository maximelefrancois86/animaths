package fr.upmf.animaths.client.events;

import com.google.gwt.event.shared.GwtEvent;

import fr.upmf.animaths.client.mvp.MODynamicPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MOElement;

public class GrabSelectedEvent extends GwtEvent<GrabSelectedHandler> {

	private static final Type<GrabSelectedHandler> TYPE = new Type<GrabSelectedHandler>();
	
    public static Type<GrabSelectedHandler> getType() {
        return TYPE;
    }

    private MODynamicPresenter presenter;
    private MOElement<?> selectedElement;

    public GrabSelectedEvent(MODynamicPresenter presenter, MOElement<?> selectedElement) {
		this.presenter = presenter;
		this.selectedElement = selectedElement;
    }
    
	@Override
	protected void dispatch(GrabSelectedHandler handler) {
		handler.onGrabSelected(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<GrabSelectedHandler> getAssociatedType() {
		return getType();
	}
	
	public MOElement<?> getSelectedElement() {
		return selectedElement;
	}
	
	public MODynamicPresenter getPresenter() {
		return presenter;
	}

}
