package fr.upmf.animaths.client.mvp.MathObject;

import fr.upmf.animaths.client.mvp.MathML.MathMLNumber;

public class MathObjectNumberDisplay implements MathObjectNumberPresenter.Display {

	private MathMLNumber element;
	
	public MathObjectNumberDisplay() {	}
	
	@Override
	public void setElement(MathMLNumber element) {
		this.element = element;
	}
	
	@Override
	public MathMLNumber getElement() {
		return element;
	}

	@Override
	public void startProcessing() {
	}

	@Override
	public void stopProcessing() {
	}

}
