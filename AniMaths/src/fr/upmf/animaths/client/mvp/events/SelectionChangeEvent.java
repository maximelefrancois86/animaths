package fr.upmf.animaths.client.mvp.events;

import com.google.gwt.event.shared.GwtEvent;

public class SelectionChangeEvent extends GwtEvent<SelectionChangeHandler>{

    private static final Type<SelectionChangeHandler> TYPE = new Type<SelectionChangeHandler>();

    public static final int CHANGE_TO_PREVIOUS_SIBLING = 81;
    public static final int CHANGE_TO_PARENT = 90;
    public static final int CHANGE_TO_NEXT_SIBLING = 68;    
    public static final int CHANGE_TO_FIRST_CHILD = 83;
    private int direction;
    
    public static Type<SelectionChangeHandler> getType() {
        return TYPE;
    }

    public SelectionChangeEvent(int direction) {
    	this.direction = direction;    	
    }
    
	@Override
	protected void dispatch(SelectionChangeHandler handler) {
		handler.onSelectionChange(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<SelectionChangeHandler> getAssociatedType() {
		return getType();
	}
	
	public int getDirection() {
		return direction;
	}

}
