package fr.upmf.animaths.client.mvp.MathObject;

public interface IMOHasSeveralChildren<T extends MOElement<?>> {

	public void addChild(T child);
}
