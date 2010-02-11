package fr.upmf.animaths.client.mvp.MathObject;

import fr.upmf.animaths.client.mvp.MathML.MMLNumber;

public class MONumberDisplay implements MONumber.Display {

	private MMLNumber element;
	
	public MONumberDisplay() {	}
	
	@Override
	public void setElement(MMLNumber element) {
		this.element = element;
	}
	
	@Override
	public MMLNumber getElement() {
		return element;
	}

	@Override
	public void startProcessing() {
	}

	@Override
	public void stopProcessing() {
	}

}
