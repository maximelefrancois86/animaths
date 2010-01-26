package fr.upmf.animaths.client.mvp.events;

import com.google.gwt.event.shared.EventHandler;

public interface DropHandler extends EventHandler {

	void onDrop( DropEvent event );
	
}
