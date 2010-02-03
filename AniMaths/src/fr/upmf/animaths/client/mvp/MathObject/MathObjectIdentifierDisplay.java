package fr.upmf.animaths.client.mvp.MathObject;

import fr.upmf.animaths.client.mvp.MathML.MathMLIdentifier;

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
