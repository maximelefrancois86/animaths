package fr.upmf.animaths.client.mvp.MathObject;

import fr.upmf.animaths.client.mvp.MathML.MMLIdentifier;

public class MOIdentifierDisplay implements MOIdentifier.Display {

	MMLIdentifier element;
	
	public MOIdentifierDisplay() { }
	
	@Override
	public void setElement(MMLIdentifier element) {
		this.element = element;
	}
	
	@Override
	public MMLIdentifier getElement() {
		return element;
	}

	@Override
	public void startProcessing() {
	}

	@Override
	public void stopProcessing() {
	}

}
