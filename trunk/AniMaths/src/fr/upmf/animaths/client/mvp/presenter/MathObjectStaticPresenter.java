package fr.upmf.animaths.client.mvp.presenter;

import fr.upmf.animaths.client.mvp.display.MathObjectDisplay;
import fr.upmf.animaths.client.mvp.display.StaticMathObjectDisplay;


public class MathObjectStaticPresenter extends MathObjectPresenter<MathObjectStaticPresenter.Display> {

	public interface Display extends MathObjectDisplay {
	}
	
	public MathObjectStaticPresenter() {
		super(new StaticMathObjectDisplay());
	}

}
