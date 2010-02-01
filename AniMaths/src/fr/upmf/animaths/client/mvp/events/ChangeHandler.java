package fr.upmf.animaths.client.mvp.events;

import com.google.gwt.event.shared.EventHandler;

public interface ChangeHandler extends EventHandler {

	void onChange( ChangeEvent event );

}
