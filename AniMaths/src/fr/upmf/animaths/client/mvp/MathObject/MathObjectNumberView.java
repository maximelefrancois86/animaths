package fr.upmf.animaths.client.mvp.MathObject;

import fr.upmf.animaths.client.mvp.widgets.MathML.MathMLNumber;

public class MathObjectNumberView implements MathObjectNumberPresenter.Display {

	private MathMLNumber element;
	
	public MathObjectNumberView() {	}
	
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
