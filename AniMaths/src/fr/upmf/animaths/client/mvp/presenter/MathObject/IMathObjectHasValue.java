package fr.upmf.animaths.client.mvp.presenter.MathObject;

public interface IMathObjectHasValue<T> {
	
	public void setValue(T value);
	public T getValue();
}
