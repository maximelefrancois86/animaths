package fr.upmf.animaths.client.display.MathObject;

import fr.upmf.animaths.client.display.MathML.MathMLOperator;

public interface IMathObjectHasSign {

	public void setSign(MathMLOperator sign);
	public MathMLOperator getSign();

}
