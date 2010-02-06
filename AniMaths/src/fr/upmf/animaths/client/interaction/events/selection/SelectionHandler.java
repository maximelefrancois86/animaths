package fr.upmf.animaths.client.interaction.events.selection;

import com.google.gwt.event.shared.EventHandler;

public interface SelectionHandler extends EventHandler {

	void onSelect( SelectionEvent event );
	
}
