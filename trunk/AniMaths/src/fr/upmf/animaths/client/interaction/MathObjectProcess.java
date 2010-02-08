package fr.upmf.animaths.client.interaction;

import net.customware.gwt.presenter.client.EventBus;
import fr.upmf.animaths.client.interaction.events.DragOverHandler;
import fr.upmf.animaths.client.interaction.events.DropOverHandler;
import fr.upmf.animaths.client.interaction.events.GrabHandler;
import fr.upmf.animaths.client.mvp.AniMathsPresenter;
import fr.upmf.animaths.client.mvp.MathObjectPresenter;

public abstract class MathObjectProcess implements GrabHandler, DragOverHandler, DropOverHandler {

	@SuppressWarnings("unused")
	private static MathObjectProcess instance;
	
	@SuppressWarnings("unused")
	private EventBus eventBus = AniMathsPresenter.eventBus;

	protected MathObjectPresenter<?> presenter = null;
		
	public abstract void setEnabled(boolean enabled);
	
}