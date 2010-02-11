package fr.upmf.animaths.client.mvp.MathObject;

public interface IMOHasValue<T> {
	
	public void setValue(T value);
	public T getValue();
}
