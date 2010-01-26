package fr.upmf.animaths.client.mvp.events;

import com.google.gwt.event.shared.EventHandler;

public interface SelectionChangeHandler extends EventHandler {

	void onSelectionChange( SelectionChangeEvent event );
	
}
