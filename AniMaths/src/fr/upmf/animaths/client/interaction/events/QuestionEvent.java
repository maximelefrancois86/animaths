package fr.upmf.animaths.client.interaction.events;

import com.google.gwt.event.shared.GwtEvent;

public class QuestionEvent extends GwtEvent<QuestionHandler>{

    private static final Type<QuestionHandler> TYPE = new Type<QuestionHandler>();
    
    public static Type<QuestionHandler> getType() {
        return TYPE;
    }

    public QuestionEvent() {
    }
    
	@Override
	protected void dispatch(QuestionHandler handler) {
		handler.onQuestion(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<QuestionHandler> getAssociatedType() {
		return getType();
	}
	
}
