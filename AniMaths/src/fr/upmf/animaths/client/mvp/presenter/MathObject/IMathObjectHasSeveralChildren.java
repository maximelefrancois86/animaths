package fr.upmf.animaths.client.mvp.presenter.MathObject;

public interface IMathObjectHasSeveralChildren<T extends MathObjectElementPresenter<?>> {

	public void addChild(T child);
}
