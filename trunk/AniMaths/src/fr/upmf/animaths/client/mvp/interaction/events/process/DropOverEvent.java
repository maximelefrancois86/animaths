package fr.upmf.animaths.client.mvp.interaction.events.process;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.shared.GwtEvent;

import fr.upmf.animaths.client.mvp.presenter.MathObject.MathObjectElementPresenter;

public class DropOverEvent extends GwtEvent<DropOverHandler>{

    private static final Type<DropOverHandler> TYPE = new Type<DropOverHandler>();

    private short state;
    private MathObjectElementPresenter<?> element;
    private NativeEvent event;
    
    public static Type<DropOverHandler> getType() {
        return TYPE;
    }

    public DropOverEvent(MathObjectElementPresenter<?> object, NativeEvent event) {
    	if(object!=null)
    		this.state = object.getStyleClass();
    	this.element = object;
    	this.event = event;
    }
    
	@Override
	protected void dispatch(DropOverHandler handler) {
		handler.onDropOver(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<DropOverHandler> getAssociatedType() {
		return getType();
	}
	
	public NativeEvent getEvent() {
		return event;
	}
	
	public short getState() {
		return state;
	}
	
	public MathObjectElementPresenter<?> getElement() {
		return element;
	}

}
