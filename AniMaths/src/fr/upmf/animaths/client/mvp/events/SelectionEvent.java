package fr.upmf.animaths.client.mvp.events;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.shared.GwtEvent;

import fr.upmf.animaths.client.mvp.MathObject.MathObjectElementPresenter;

public class SelectionEvent extends GwtEvent<SelectionHandler>{

    private static final Type<SelectionHandler> TYPE = new Type<SelectionHandler>();

    private short state = -1;
    private NativeEvent event;
    
    public static Type<SelectionHandler> getType() {
        return TYPE;
    }

    public SelectionEvent(MathObjectElementPresenter<?> element, NativeEvent nativeEvent) {
    	if(element!=null)
    		this.state = element.getState();
    	this.event = nativeEvent;
    }
    
	@Override
	protected void dispatch(SelectionHandler handler) {
		handler.onSelect(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<SelectionHandler> getAssociatedType() {
		return getType();
	}
	
	public short getState() {
		return state;
	}

	public NativeEvent getEvent() {
		return event;
	}
	
}
