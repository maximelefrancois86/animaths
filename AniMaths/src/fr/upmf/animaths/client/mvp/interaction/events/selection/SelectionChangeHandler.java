package fr.upmf.animaths.client.mvp.interaction.events.selection;

import com.google.gwt.event.shared.EventHandler;

public interface SelectionChangeHandler extends EventHandler {

	void onSelectionChange( SelectionChangeEvent event );
	
}
