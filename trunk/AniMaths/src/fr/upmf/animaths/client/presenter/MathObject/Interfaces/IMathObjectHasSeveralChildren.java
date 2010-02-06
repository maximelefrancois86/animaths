package fr.upmf.animaths.client.presenter.MathObject.Interfaces;

import fr.upmf.animaths.client.presenter.MathObject.MathObjectElementPresenter;

public interface IMathObjectHasSeveralChildren<T extends MathObjectElementPresenter<?>> {

	public void addChild(T child);
}
