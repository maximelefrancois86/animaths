package fr.upmf.animaths.client.presenter.MathObject.Interfaces;

public interface IMathObjectHasValue<T> {
	
	public void setValue(T value);
	public T getValue();
}
