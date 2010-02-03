package fr.upmf.animaths.client.mvp.interaction.events.dragndrop;

import com.google.gwt.event.shared.EventHandler;

public interface DragHandler extends EventHandler {

	void onDrag( DragEvent event );
	
}
