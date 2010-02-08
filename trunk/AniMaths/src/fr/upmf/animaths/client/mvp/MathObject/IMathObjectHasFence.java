package fr.upmf.animaths.client.mvp.MathObject;

import fr.upmf.animaths.client.mvp.MathML.MathMLOperator;

public interface IMathObjectHasFence {

	public void setLFence(MathMLOperator lFence);
	public void setRFence(MathMLOperator rFence);
	public MathMLOperator getLFence();
	public MathMLOperator getRFence();

}
