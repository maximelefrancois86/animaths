package fr.upmf.animaths.client.interaction.process;

import fr.upmf.animaths.client.interaction.process.event.DragSelectedHandler;
import fr.upmf.animaths.client.interaction.process.event.DropSelectedHandler;
import fr.upmf.animaths.client.interaction.process.event.GrabSelectedHandler;

public abstract interface MathObjectProcess extends GrabSelectedHandler, DragSelectedHandler, DropSelectedHandler {
	
}
