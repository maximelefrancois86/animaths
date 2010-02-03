package fr.upmf.animaths.client.mvp.MathObject;

import fr.upmf.animaths.client.mvp.MathML.MathMLOperator;

public class MathObjectSignedElementDisplay implements MathObjectSignedElementPresenter.Display {

	private MathMLOperator lFence;
	private MathMLOperator rFence;
	private MathMLOperator sign;

	public MathObjectSignedElementDisplay() { }

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
