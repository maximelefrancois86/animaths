package fr.upmf.animaths.client.mvp.MathObject;

import fr.upmf.animaths.client.mvp.widgets.MathML.MathMLOperator;

public class MathObjectAddContainerView implements MathObjectAddContainerPresenter.Display {

	private MathMLOperator lFence;
	private MathMLOperator rFence;

	public MathObjectAddContainerView() { }

	@Override
	public MathMLOperator getLFence() {
		return lFence;
	}

	@Override
	public MathMLOperator getRFence() {
		return rFence;
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
	public void startProcessing() {
	}

	@Override
	public void stopProcessing() {
	}

}

