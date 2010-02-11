package fr.upmf.animaths.client.mvp.MathObject;

public interface IMOHasZones {

	public static final short ZONE_CENTER = 0;

	public static final short ZONE_NNN = -3;
	public static final short ZONE_NN = -2;
	public static final short ZONE_N = -1;
	
	public static final short ZONE_S =  1;
	public static final short ZONE_SS =  2;
	public static final short ZONE_SSS =  3;

	public static final short ZONE_OOO = -3;
	public static final short ZONE_OO = -2;
	public static final short ZONE_O = -1;

	public static final short ZONE_E =  1;
	public static final short ZONE_EE =  2;
	public static final short ZONE_EEE =  3;
	

	public short getZoneH(int x);
	public short getZoneV(int y);
}
