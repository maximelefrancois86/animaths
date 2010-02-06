package fr.upmf.animaths.client.presenter;

import fr.upmf.animaths.client.display.MathObjectDisplay;
import fr.upmf.animaths.client.display.StaticMathObjectDisplay;


public class MathObjectStaticPresenter extends MathObjectPresenter<MathObjectStaticPresenter.Display> {

	public interface Display extends MathObjectDisplay {
	}
	
	public MathObjectStaticPresenter() {
		super(new StaticMathObjectDisplay());
	}

}
