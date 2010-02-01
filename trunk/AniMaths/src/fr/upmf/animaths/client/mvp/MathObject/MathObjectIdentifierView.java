package fr.upmf.animaths.client.mvp.MathObject;

import fr.upmf.animaths.client.mvp.widgets.MathML.MathMLIdentifier;

public class MathObjectIdentifierView implements MathObjectIdentifierPresenter.Display {

	MathMLIdentifier element;
	
	public MathObjectIdentifierView() { }
	
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
