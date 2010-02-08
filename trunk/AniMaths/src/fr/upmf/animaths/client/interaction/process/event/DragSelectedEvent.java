package fr.upmf.animaths.client.interaction.process.event;

import com.google.gwt.event.shared.GwtEvent;

import fr.upmf.animaths.client.mvp.MathObject.MathObjectElementPresenter;

public class DragSelectedEvent extends GwtEvent<DragSelectedHandler>{

    private static final Type<DragSelectedHandler> TYPE = new Type<DragSelectedHandler>();

    public static Type<DragSelectedHandler> getType() {
        return TYPE;
    }

    private MathObjectElementPresenter<?> whereElement = null;    
    private short zone;

    public DragSelectedEvent(MathObjectElementPresenter<?> whereElement, short zone) {
    	this.whereElement = whereElement;
		this.zone = zone;
    }
    
	@Override
	protected void dispatch(DragSelectedHandler handler) {
		handler.onDragSelected(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<DragSelectedHandler> getAssociatedType() {
		return getType();
	}
	
	public MathObjectElementPresenter<?> getWhereElement() {
		return whereElement;
	}

	public short getZone() {
		return zone;
	}
	
}