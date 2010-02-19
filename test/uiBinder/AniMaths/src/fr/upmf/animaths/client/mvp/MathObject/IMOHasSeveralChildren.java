package fr.upmf.animaths.client.mvp.MathObject;

public interface IMOHasSeveralChildren<T extends MOElement<?>> {

	public void add(T child);
	public void add(T child, T refChild, boolean after);
	public void remove(T child);
}
