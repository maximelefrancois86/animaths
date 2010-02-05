package fr.upmf.animaths.client.mvp.display.MathObject;

import fr.upmf.animaths.client.mvp.display.MathML.MathMLOperator;
import fr.upmf.animaths.client.mvp.presenter.MathObject.MathObjectSignedElementPresenter;

public class MathObjectSignedElementDisplay implements MathObjectSignedElementPresenter.Display {

	private MathMLOperator lFence;
	private MathMLOperator rFence;
	private MathMLOperator sign;

	public MathObjectSignedElementDisplay() { }

	@Override
	public MathMLOperator getLFence() {
		return lFence;
	}

	@Override
	public MathMLOperator getRFence() {
		return rFence;
	}

	@Override
	public MathMLOperator getSign() {
		return sign;
	}

	@Override
	public void setLFence(MathMLOperator lFence) {
		this.lFence = lFence;
	}

	@Override
	public void setRFence(MathMLOperator rFence) {
		this.rFence = rFence;
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
