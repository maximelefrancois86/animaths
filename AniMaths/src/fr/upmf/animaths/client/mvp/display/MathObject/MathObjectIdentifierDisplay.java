package fr.upmf.animaths.client.mvp.display.MathObject;

import fr.upmf.animaths.client.mvp.display.MathML.MathMLIdentifier;
import fr.upmf.animaths.client.mvp.presenter.MathObject.MathObjectIdentifierPresenter;

public class MathObjectIdentifierDisplay implements MathObjectIdentifierPresenter.Display {

	MathMLIdentifier element;
	
	public MathObjectIdentifierDisplay() { }
	
	@Override
	public void setElement(MathMLIdentifier element) {
		this.element = element;
	}
	
	@Override
	public MathMLIdentifier getElement() {
		return element;
	}

	@Override
	public void startProcessing() {
	}

	@Override
	public void stopProcessing() {
	}

}
