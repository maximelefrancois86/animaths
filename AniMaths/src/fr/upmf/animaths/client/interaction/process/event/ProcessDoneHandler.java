package fr.upmf.animaths.client.interaction.process.event;

import com.google.gwt.event.shared.EventHandler;

public interface ProcessDoneHandler extends EventHandler {

	void onProcessDone( ProcessDoneEvent event );
	
}
