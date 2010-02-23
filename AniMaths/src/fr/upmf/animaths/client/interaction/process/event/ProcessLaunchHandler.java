package fr.upmf.animaths.client.interaction.process.event;

import com.google.gwt.event.shared.EventHandler;

public interface ProcessLaunchHandler extends EventHandler {

	void onProcessLaunch( ProcessLaunchEvent event );
	
}
