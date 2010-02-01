package fr.upmf.animaths.client.mvp.MathObject;

import fr.upmf.animaths.client.mvp.widgets.MathML.MathMLOperator;

public class MathObjectEquationView implements MathObjectEquationPresenter.Display {

	private MathMLOperator sign;
	
	public MathObjectEquationView() { }
	
	@Override
	public MathMLOperator getSign() {
		return sign;
	}
	
	@Override
	public void setSign(MathMLOperator sign) {
		this.sign = sign;
	}

	@Override
	public void startProcessing() {
	}

	@Override
	public void stopProcessing() {
	}
}
