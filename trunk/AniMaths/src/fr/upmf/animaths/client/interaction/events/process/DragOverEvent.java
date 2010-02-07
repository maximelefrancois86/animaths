package fr.upmf.animaths.client.interaction.events.process;

import com.google.gwt.event.shared.GwtEvent;

import fr.upmf.animaths.client.interaction.events.dragndrop.DragEvent;
import fr.upmf.animaths.client.presenter.MathObjectStaticPresenter;
import fr.upmf.animaths.client.presenter.MathObject.MathObjectElementPresenter;

public class DragOverEvent extends GwtEvent<DragOverHandler>{

    private static final Type<DragOverHandler> TYPE = new Type<DragOverHandler>();

    private MathObjectElementPresenter<?> selectedElement = null;
    private MathObjectElementPresenter<?> whereElement = null;
    private MathObjectStaticPresenter copiedPresenter = null;
    private DragEvent event = null;
    
    public static Type<DragOverHandler> getType() {
        return TYPE;
    }

    public DragOverEvent(MathObjectElementPresenter<?> selectedElement, MathObjectElementPresenter<?> whereElement, MathObjectStaticPresenter copiedPresenter, DragEvent event) {
    	this.selectedElement = selectedElement;
    	this.whereElement = whereElement;
    	this.copiedPresenter = copiedPresenter;
    	this.event = event;
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

	public MathObjectElementPresenter<?> getSelectedElement() {
		return selectedElement;
	}

	public MathObjectElementPresenter<?> getWhereElement() {
		return whereElement;
	}

	public MathObjectStaticPresenter getCopiedPresenter() {
		return copiedPresenter;
	}
}