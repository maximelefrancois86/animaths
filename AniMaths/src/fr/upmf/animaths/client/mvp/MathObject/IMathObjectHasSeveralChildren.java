package fr.upmf.animaths.client.mvp.MathObject;

import java.util.List;


public interface IMathObjectHasSeveralChildren<T extends MathObjectElementPresenter<?>> {

	public void addChild(T child);
	public List<T> getChildren();
}
