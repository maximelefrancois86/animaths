package fr.upmf.animaths.client.mvp.MathObject;


public interface IMOHasOneChild {

	public MOElement<?> getChild();
	public void setChild(MOElement<?> child);

}
