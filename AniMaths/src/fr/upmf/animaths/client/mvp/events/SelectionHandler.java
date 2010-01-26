package fr.upmf.animaths.client.mvp.events;

import com.google.gwt.event.shared.EventHandler;

public interface SelectionHandler extends EventHandler {

	void onSelect( SelectionEvent event );
	
}
