package fr.upmf.animaths.client.mvp.MathObject;

import fr.upmf.animaths.client.mvp.MathML.MMLOperator;

public interface IMOHasFence {

	public void setLFence(MMLOperator lFence);
	public void setRFence(MMLOperator rFence);
	public MMLOperator getLFence();
	public MMLOperator getRFence();

}
