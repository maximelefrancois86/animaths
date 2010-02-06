package fr.upmf.animaths.client.presenter.MathObject.Interfaces;

public interface IMathObjectHasType {

	public static final short MATH_OBJECT_WRAPPER = 0;
	public static final short MATH_OBJECT_EQUATION = 1;
	public static final short MATH_OBJECT_NUMBER = 2;
	public static final short MATH_OBJECT_IDENTIFIER = 3;
	public static final short MATH_OBJECT_SIGNED_ELEMENT = 4;
	public static final short MATH_OBJECT_ADDITION_CONTAINER = 5;
	public static final short MATH_OBJECT_MULTIPLY_ELEMENT = 6;
	public static final short MATH_OBJECT_MULTIPLY_CONTAINER = 7;
	public static final short MATH_OBJECT_POWER = 8;

	public short getType();
}
