package fr.upmf.animaths.client.mvp;

import fr.upmf.animaths.client.mvp.MathObject.MOElement;

public class MOBasicPresenter extends MOAbstractPresenter<MOBasicDisplay> {

	public MOBasicPresenter() {
		super(new MOBasicDisplay());
	}

	public MOBasicPresenter(MOElement<?> element) {
		super(element, new MOBasicDisplay());
	}

	@Override
	protected void onInit() {
	}
	
	@Override
	protected void onBind() { }

}
