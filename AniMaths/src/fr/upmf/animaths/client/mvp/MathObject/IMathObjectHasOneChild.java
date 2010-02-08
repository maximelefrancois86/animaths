package fr.upmf.animaths.client.mvp.MathObject;


public interface IMathObjectHasOneChild {

	public MathObjectElementPresenter<?> getChild();
	public void setChild(MathObjectElementPresenter<?> child);

}
