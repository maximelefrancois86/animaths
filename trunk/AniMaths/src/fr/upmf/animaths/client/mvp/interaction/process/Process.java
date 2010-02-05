package fr.upmf.animaths.client.mvp.interaction.process;

import fr.upmf.animaths.client.mvp.MathObjectPresenter;
import fr.upmf.animaths.client.mvp.StaticMathObjectPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectElementPresenter;
import fr.upmf.animaths.client.mvp.interaction.events.process.DragOverHandler;
import fr.upmf.animaths.client.mvp.interaction.events.process.DropOverHandler;

public abstract class Process implements DragOverHandler, DropOverHandler {

	protected MathObjectPresenter<?> presenter = null;
	protected StaticMathObjectPresenter copy = null;	
	protected MathObjectElementPresenter<?> above = null;
	protected MathObjectElementPresenter<?> under = null;

	public abstract void setEnabled(boolean enabled);
	
}
