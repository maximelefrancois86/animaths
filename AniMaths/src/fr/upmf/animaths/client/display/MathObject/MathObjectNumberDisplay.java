package fr.upmf.animaths.client.display.MathObject;

import fr.upmf.animaths.client.display.MathML.MathMLNumber;
import fr.upmf.animaths.client.presenter.MathObject.MathObjectNumberPresenter;

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
