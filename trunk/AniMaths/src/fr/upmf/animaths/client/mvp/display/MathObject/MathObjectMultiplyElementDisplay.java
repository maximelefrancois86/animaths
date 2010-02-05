package fr.upmf.animaths.client.mvp.display.MathObject;

import fr.upmf.animaths.client.mvp.display.MathML.MathMLOperator;
import fr.upmf.animaths.client.mvp.presenter.MathObject.MathObjectMultiplyElementPresenter;

public class MathObjectMultiplyElementDisplay implements MathObjectMultiplyElementPresenter.Display {

	private MathMLOperator sign;

	public MathObjectMultiplyElementDisplay() {}

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
