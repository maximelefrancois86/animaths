package fr.upmf.animaths.client.mvp.display.MathObject;

import fr.upmf.animaths.client.mvp.display.MathML.MathMLNumber;
import fr.upmf.animaths.client.mvp.presenter.MathObject.MathObjectNumberPresenter;

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
