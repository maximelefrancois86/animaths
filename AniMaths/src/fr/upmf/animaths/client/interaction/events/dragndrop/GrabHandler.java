package fr.upmf.animaths.client.interaction.events.dragndrop;

import com.google.gwt.event.shared.EventHandler;

public interface GrabHandler extends EventHandler {

	void onGrab( GrabEvent event );
	
}
