package fr.upmf.animaths.client.events;

import com.google.gwt.event.shared.EventHandler;

public interface ExerciseSolvedHandler extends EventHandler {

	void onExerciseSolved( ExerciseSolvedEvent event );
	
}
