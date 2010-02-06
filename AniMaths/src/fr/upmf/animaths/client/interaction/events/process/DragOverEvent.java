package fr.upmf.animaths.client.interaction.events.process;

import com.google.gwt.event.shared.GwtEvent;

import fr.upmf.animaths.client.interaction.events.dragndrop.DragEvent;
import fr.upmf.animaths.client.presenter.MathObjectStaticPresenter;
import fr.upmf.animaths.client.presenter.MathObject.MathObjectElementPresenter;

public class DragOverEvent extends GwtEvent<DragOverHandler>{

    private static final Type<DragOverHandler> TYPE = new Type<DragOverHandler>();

    private MathObjectStaticPresenter copy = null;
    private MathObjectElementPresenter<?> underElement = null;
    private MathObjectElementPresenter<?> aboveElement = null;
    private DragEvent event = null;
    private int clientX = -1;
    private int clientY = -1;
    
    public static Type<DragOverHandler> getType() {
        return TYPE;
    }

    public DragOverEvent(MathObjectStaticPresenter copy, DragEvent event) {
    	this.copy = copy;
    	this.aboveElement = copy.getElement();
    	this.event = event;
    	this.underElement = event.getElement();
    	this.clientX = event.getClientX();
    	this.clientY = event.getClientY();
    }
    
	@Override
	protected void dispatch(DragOverHandler handler) {
		handler.onDragOver(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<DragOverHandler> getAssociatedType() {
		return getType();
	}
	
	public DragEvent getEvent() {
		return event;
	}
	
	public MathObjectStaticPresenter getCopy() {
		return copy;
	}

	public int getClientX() {
		return clientX;
	}

	public int getClientY() {
		return clientY;
	}

	public MathObjectElementPresenter<?> getUnderElement() {
		return underElement;
	}

	public MathObjectElementPresenter<?> getAboveElement() {
		return aboveElement;
	}
}
