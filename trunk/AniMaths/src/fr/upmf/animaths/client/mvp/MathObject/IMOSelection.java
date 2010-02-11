package fr.upmf.animaths.client.mvp.MathObject;


public interface IMOSelection {

	public MOElement<?> getMathObjectSelectableElement();
	public MOElement<?> getMathObjectFirstSelectableParent();
	public MOElement<?> getMathObjectFirstSelectableChild();
	public MOElement<?> getMathObjectPreviousSelectableChild(MOElement<?> child);
	public MOElement<?> getMathObjectNextSelectableChild(MOElement<?> child);
}
