package fr.upmf.animaths.client.mvp.presenter.MathObject;

public interface IMathObjectHasOneChild {

	public MathObjectElementPresenter<?> getChild();
	public void setChild(MathObjectElementPresenter<?> child);

}
