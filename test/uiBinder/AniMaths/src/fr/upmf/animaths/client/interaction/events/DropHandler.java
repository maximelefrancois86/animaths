package fr.upmf.animaths.client.interaction.events;

import com.google.gwt.event.shared.EventHandler;

public interface DropHandler extends EventHandler {

	void onDrop( DropEvent event );
	
}
