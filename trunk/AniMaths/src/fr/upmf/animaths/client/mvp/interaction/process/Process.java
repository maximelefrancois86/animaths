package fr.upmf.animaths.client.mvp.interaction.process;

import fr.upmf.animaths.client.mvp.interaction.events.process.DragOverHandler;
import fr.upmf.animaths.client.mvp.interaction.events.process.DropOverHandler;
import fr.upmf.animaths.client.mvp.presenter.MathObjectPresenter;
import fr.upmf.animaths.client.mvp.presenter.MathObjectStaticPresenter;
import fr.upmf.animaths.client.mvp.presenter.MathObject.MathObjectElementPresenter;

public abstract class Process implements DragOverHandler, DropOverHandler {

	protected MathObjectPresenter<?> presenter = null;
	protected MathObjectStaticPresenter copy = null;	
	protected MathObjectElementPresenter<?> above = null;
	protected MathObjectElementPresenter<?> under = null;

	public abstract void setEnabled(boolean enabled);
	
}
