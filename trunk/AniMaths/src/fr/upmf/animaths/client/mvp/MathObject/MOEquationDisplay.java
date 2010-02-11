package fr.upmf.animaths.client.mvp.MathObject;

import fr.upmf.animaths.client.mvp.MathML.MMLOperator;

public class MOEquationDisplay implements MOEquation.Display {

	private MMLOperator sign;
	
	public MOEquationDisplay() { }
	
	@Override
	public MMLOperator getSign() {
		return sign;
	}
	
	@Override
	public void setSign(MMLOperator sign) {
		this.sign = sign;
	}

	@Override
	public void startProcessing() {
	}

	@Override
	public void stopProcessing() {
	}
}
