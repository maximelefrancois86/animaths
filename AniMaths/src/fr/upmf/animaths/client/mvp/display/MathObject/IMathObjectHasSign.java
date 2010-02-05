package fr.upmf.animaths.client.mvp.display.MathObject;

import fr.upmf.animaths.client.mvp.display.MathML.MathMLOperator;

public interface IMathObjectHasSign {

	public void setSign(MathMLOperator sign);
	public MathMLOperator getSign();

}
