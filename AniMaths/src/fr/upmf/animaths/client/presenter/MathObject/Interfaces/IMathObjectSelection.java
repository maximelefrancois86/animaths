package fr.upmf.animaths.client.presenter.MathObject.Interfaces;

import fr.upmf.animaths.client.presenter.MathObject.MathObjectElementPresenter;

public interface IMathObjectSelection {

	public MathObjectElementPresenter<?> getMathObjectSelectableElement();
	public MathObjectElementPresenter<?> getMathObjectFirstSelectableParent();
	public MathObjectElementPresenter<?> getMathObjectFirstSelectableChild();
	public MathObjectElementPresenter<?> getMathObjectPreviousSelectableChild(MathObjectElementPresenter<?> child);
	public MathObjectElementPresenter<?> getMathObjectNextSelectableChild(MathObjectElementPresenter<?> child);
}
