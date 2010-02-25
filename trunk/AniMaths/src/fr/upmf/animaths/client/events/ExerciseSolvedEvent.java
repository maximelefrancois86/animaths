package fr.upmf.animaths.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class ExerciseSolvedEvent extends GwtEvent<ExerciseSolvedHandler>{

    private static final Type<ExerciseSolvedHandler> TYPE = new Type<ExerciseSolvedHandler>();
    
    public static Type<ExerciseSolvedHandler> getType() {
        return TYPE;
    }

    public ExerciseSolvedEvent() {
    }
    
	@Override
	protected void dispatch(ExerciseSolvedHandler handler) {
		handler.onExerciseSolved(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ExerciseSolvedHandler> getAssociatedType() {
		return getType();
	}
	
}
