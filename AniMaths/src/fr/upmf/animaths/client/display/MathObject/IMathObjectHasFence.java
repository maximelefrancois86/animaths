package fr.upmf.animaths.client.display.MathObject;

import fr.upmf.animaths.client.display.MathML.MathMLOperator;

public interface IMathObjectHasFence {

	public void setLFence(MathMLOperator lFence);
	public void setRFence(MathMLOperator rFence);
	public MathMLOperator getLFence();
	public MathMLOperator getRFence();

}