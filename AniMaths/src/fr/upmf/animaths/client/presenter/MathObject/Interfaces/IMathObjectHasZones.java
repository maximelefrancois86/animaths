package fr.upmf.animaths.client.presenter.MathObject.Interfaces;

public interface IMathObjectHasZones {

	public static final short ZONE_NONE = -1;
	public static final short ZONE_IN_C = 0;
	public static final short ZONE_IN_N = 1;
	public static final short ZONE_IN_NE = 2;
	public static final short ZONE_IN_E = 3;
	public static final short ZONE_IN_SE = 4;
	public static final short ZONE_IN_S = 5;
	public static final short ZONE_IN_SO = 6;
	public static final short ZONE_IN_O = 7;
	public static final short ZONE_IN_NO = 8;

	public static final short ZONE_EQ_LEFT_OUT_N = 9;
	public static final short ZONE_EQ_LEFT = 10;
	public static final short ZONE_EQ_LEFT_OUT_S = 11;
	public static final short ZONE_EQ_RIGHT_OUT_N = 12;
	public static final short ZONE_EQ_RIGHT = 13;
	public static final short ZONE_EQ_RIGHT_OUT_S = 14;

	public short getZone(int x, int y);
}
