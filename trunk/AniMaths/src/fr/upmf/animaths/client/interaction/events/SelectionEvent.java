package fr.upmf.animaths.client.interaction.events;

import com.google.gwt.event.shared.GwtEvent;

import fr.upmf.animaths.client.mvp.MathObject.MathObjectElementPresenter;

public class SelectionEvent extends GwtEvent<SelectionHandler>{

    private static final Type<SelectionHandler> TYPE = new Type<SelectionHandler>();

    private short styleClass = -1;
    
    public static Type<SelectionHandler> getType() {
        return TYPE;
    }

    public SelectionEvent(MathObjectElementPresenter<?> element) {
    	if(element!=null)
    		this.styleClass = element.getStyleClass();
    }
    
	@Override
	protected void dispatch(SelectionHandler handler) {
		handler.onSelect(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<SelectionHandler> getAssociatedType() {
		return getType();
	}
	
	public short getStyleClass() {
		return styleClass;
	}

}
