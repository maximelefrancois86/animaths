package fr.upmf.animaths.client.mvp.MathObject;

import fr.upmf.animaths.client.mvp.MathML.MathMLOperator;

public class MathObjectEquationDisplay implements MathObjectEquationPresenter.Display {

	private MathMLOperator sign;
	
	public MathObjectEquationDisplay() { }
	
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
