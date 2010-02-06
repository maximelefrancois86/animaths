package fr.upmf.animaths.client.presenter.MathObject.Interfaces;

public interface IMathObjectHasStyleClass {

	public static final short STYLE_CLASS_NONE = 0;
	public static final short STYLE_CLASS_SELECTABLE = 1;
	public static final short STYLE_CLASS_SELECTED = 2;
	public static final short STYLE_CLASS_DRAGGED = 3;
	
	public void setStyleClass(short styleClass);
	public short getStyleClass();

}
