package fr.upmf.animaths.client.mvp.events;

import com.google.gwt.event.shared.GwtEvent;

import fr.upmf.animaths.client.mvp.MathObject.MathObjectElementPresenter;

public class ChangeEvent extends GwtEvent<ChangeHandler> {

    private static final Type<ChangeHandler> TYPE = new Type<ChangeHandler>();

    private MathObjectElementPresenter<?> element;

    
    public static Type<ChangeHandler> getType() {
        return TYPE;
    }

    public ChangeEvent(MathObjectElementPresenter<?> element) {
    	this.element = element;
    }

	@Override
	protected void dispatch(ChangeHandler handler) {
		handler.onChange(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ChangeHandler> getAssociatedType() {
		return getType();
	}
	
	public MathObjectElementPresenter<?> getElement() {
		return element;
	}

}
