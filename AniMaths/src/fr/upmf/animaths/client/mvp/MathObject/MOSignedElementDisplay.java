package fr.upmf.animaths.client.mvp.MathObject;

import fr.upmf.animaths.client.mvp.MathML.MMLOperator;

public class MOSignedElementDisplay implements MOSignedElement.Display {

	private MMLOperator lFence;
	private MMLOperator rFence;
	private MMLOperator sign;

	public MOSignedElementDisplay() { }

	@Override
	public MMLOperator getLFence() {
		return lFence;
	}

	@Override
	public MMLOperator getRFence() {
		return rFence;
	}

	@Override
	public MMLOperator getSign() {
		return sign;
	}

	@Override
	public void setLFence(MMLOperator lFence) {
		this.lFence = lFence;
	}

	@Override
	public void setRFence(MMLOperator rFence) {
		this.rFence = rFence;
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
