package fr.upmf.animaths.client.mvp.MathObject;

import fr.upmf.animaths.client.mvp.MathML.MathMLOperator;

public interface IMathObjectHasSign {

	public void setSign(MathMLOperator sign);
	public MathMLOperator getSign();

}
