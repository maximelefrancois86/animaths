package fr.upmf.animaths.client.interaction.events;

import com.google.gwt.event.shared.EventHandler;

public interface QuestionHandler extends EventHandler {

	void onQuestion( QuestionEvent event );
	
}
