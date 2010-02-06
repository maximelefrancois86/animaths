package fr.upmf.animaths.client.display.MathObject;

import fr.upmf.animaths.client.display.MathML.MathMLOperator;
import fr.upmf.animaths.client.presenter.MathObject.MathObjectAddContainerPresenter;

public class MathObjectAddContainerDisplay implements MathObjectAddContainerPresenter.Display {

	private MathMLOperator lFence;
	private MathMLOperator rFence;

	public MathObjectAddContainerDisplay() { }

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

