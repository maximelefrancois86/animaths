package fr.upmf.animaths.client.interaction.process.event;

import com.google.gwt.event.shared.EventHandler;

public interface ProcessInterestedHandler extends EventHandler {

	void onProcessInterested( ProcessInterestedEvent event );
	
}
