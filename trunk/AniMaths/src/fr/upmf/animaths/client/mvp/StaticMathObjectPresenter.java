package fr.upmf.animaths.client.mvp;


public class StaticMathObjectPresenter extends MathObjectPresenter<StaticMathObjectPresenter.Display> {

	public interface Display extends MathObjectDisplay {
	}
	
	public StaticMathObjectPresenter() {
		super(new StaticMathObjectDisplay());
	}

}
