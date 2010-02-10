package fr.upmf.animaths.client.mvp.MathObject;

public interface IMathObjectHasSeveralChildren<T extends MathObjectElementPresenter<?>> {

	public void addChild(T child);
}
