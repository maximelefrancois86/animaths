package fr.upmf.animaths.client.mvp.interaction.events;

import com.google.gwt.event.shared.EventHandler;

public interface DropHandler extends EventHandler {

	void onDrop( DropEvent event );
	
}
