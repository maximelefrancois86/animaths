package fr.upmf.animaths.client.mvp.interaction.events.process;

import com.google.gwt.event.shared.GwtEvent;

import fr.upmf.animaths.client.mvp.StaticMathObjectPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectElementPresenter;
import fr.upmf.animaths.client.mvp.interaction.events.dragndrop.DragEvent;

public class DragOverEvent extends GwtEvent<DragOverHandler>{

    private static final Type<DragOverHandler> TYPE = new Type<DragOverHandler>();

    private StaticMathObjectPresenter copy = null;
    private MathObjectElementPresenter<?> underElement = null;
    private MathObjectElementPresenter<?> aboveElement = null;
    private DragEvent event = null;
    private int clientX = -1;
    private int clientY = -1;
    
    public static Type<DragOverHandler> getType() {
        return TYPE;
    }

    public DragOverEvent(StaticMathObjectPresenter copy, DragEvent event) {
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
	
	public StaticMathObjectPresenter getCopy() {
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
