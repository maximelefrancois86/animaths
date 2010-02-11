package fr.upmf.animaths.client.mvp.MathObject;

public interface IMOHasStyleClass {

	public static final short STYLE_CLASS_NONE = 0;
	public static final short STYLE_CLASS_SELECTABLE = 1;
	public static final short STYLE_CLASS_SELECTED = 2;
	public static final short STYLE_CLASS_DRAGGED = 3;
	public static final short STYLE_CLASS_OK_ADD = 4;
	public static final short STYLE_CLASS_OK_DROP = 5;
	public static final short STYLE_CLASS_NO_DROP = 6;
	public static final short STYLE_CLASS_OK = 7;
	public static final short STYLE_CLASS_CAUTION = 8;
	public static final short STYLE_CLASS_NO = 9;
	
	public void setStyleClass(short styleClass);
	public short getStyleClass();

}
