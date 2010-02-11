package fr.upmf.animaths.client.mvp.MathObject;

import fr.upmf.animaths.client.mvp.MathML.MMLOperator;

public class MOAddContainerDisplay implements MOAddContainer.Display {

	private MMLOperator lFence;
	private MMLOperator rFence;

	public MOAddContainerDisplay() { }

	@Override
	public MMLOperator getLFence() {
		return lFence;
	}

	@Override
	public MMLOperator getRFence() {
		return rFence;
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
	public void startProcessing() {
	}

	@Override
	public void stopProcessing() {
	}

}

