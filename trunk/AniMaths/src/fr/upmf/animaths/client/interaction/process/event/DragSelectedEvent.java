package fr.upmf.animaths.client.interaction.process.event;

import com.google.gwt.event.shared.GwtEvent;

import fr.upmf.animaths.client.mvp.MathObject.MathObjectElementPresenter;

public class DragSelectedEvent extends GwtEvent<DragSelectedHandler>{

    private static final Type<DragSelectedHandler> TYPE = new Type<DragSelectedHandler>();

    public static Type<DragSelectedHandler> getType() {
        return TYPE;
    }

    private MathObjectElementPresenter<?> whereElement = null;    
    private short zoneH;
    private short zoneV;
    private boolean firstLevel;

    public DragSelectedEvent(MathObjectElementPresenter<?> whereElement, short zoneH, short zoneV, boolean firstLevel) {
    	this.whereElement = whereElement;
		this.zoneH = zoneH;
		this.zoneV = zoneV;
		this.firstLevel = firstLevel;
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

	public short getZoneH() {
		return zoneH;
	}
	
	public short getZoneV() {
		return zoneV;
	}
	
	public boolean isFirstLevel() {
		return firstLevel;
	}
	
}