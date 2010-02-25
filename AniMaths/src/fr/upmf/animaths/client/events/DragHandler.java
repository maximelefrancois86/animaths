package fr.upmf.animaths.client.events;

import com.google.gwt.event.shared.EventHandler;

public interface DragHandler extends EventHandler {

	void onDrag( DragEvent event );
	
}
