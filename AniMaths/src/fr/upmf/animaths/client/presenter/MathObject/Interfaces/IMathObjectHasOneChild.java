package fr.upmf.animaths.client.presenter.MathObject.Interfaces;

import fr.upmf.animaths.client.presenter.MathObject.MathObjectElementPresenter;

public interface IMathObjectHasOneChild {

	public MathObjectElementPresenter<?> getChild();
	public void setChild(MathObjectElementPresenter<?> child);

}
