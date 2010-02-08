package fr.upmf.animaths.client.mvp.MathObject;


public interface IMathObjectSelection {

	public MathObjectElementPresenter<?> getMathObjectSelectableElement();
	public MathObjectElementPresenter<?> getMathObjectFirstSelectableParent();
	public MathObjectElementPresenter<?> getMathObjectFirstSelectableChild();
	public MathObjectElementPresenter<?> getMathObjectPreviousSelectableChild(MathObjectElementPresenter<?> child);
	public MathObjectElementPresenter<?> getMathObjectNextSelectableChild(MathObjectElementPresenter<?> child);
}
