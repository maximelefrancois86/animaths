package fr.upmf.animaths.client.interaction.events;

import com.google.gwt.event.shared.GwtEvent;

import fr.upmf.animaths.client.mvp.MathObject.MathObjectElementPresenter;

public class FlyOverEvent extends GwtEvent<FlyOverHandler>{

    private static final Type<FlyOverHandler> TYPE = new Type<FlyOverHandler>();

    private MathObjectElementPresenter<?> element = null;
    
    public static Type<FlyOverHandler> getType() {
        return TYPE;
    }

    public FlyOverEvent(MathObjectElementPresenter<?> element) {
    	this.element = element;
    }
    
	@Override
	protected void dispatch(FlyOverHandler handler) {
		handler.onFlyOver(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<FlyOverHandler> getAssociatedType() {
		return getType();
	}
	
	public MathObjectElementPresenter<?> getElement() {
		return element;
	}

}
