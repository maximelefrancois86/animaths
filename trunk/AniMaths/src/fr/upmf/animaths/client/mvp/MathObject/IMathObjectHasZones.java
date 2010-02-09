package fr.upmf.animaths.client.mvp.MathObject;

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

	public static final short ZONE_EQ_LEFT_IN = 9;
	public static final short ZONE_EQ_LEFT_OUT = 10;
	public static final short ZONE_EQ_RIGHT_IN = 11;
	public static final short ZONE_EQ_RIGHT_OUT = 12;

	public short getZone(int x, int y);
}
