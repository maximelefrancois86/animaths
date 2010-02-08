package fr.upmf.animaths.client.mvp.MathObject;

public interface IMathObjectHasValue<T> {
	
	public void setValue(T value);
	public T getValue();
}
