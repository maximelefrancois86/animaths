package fr.upmf.animaths.client.mvp;



public class MathObjectStaticPresenter extends MathObjectPresenter<MathObjectStaticPresenter.Display> {

	public interface Display extends MathObjectDisplay {
	}
	
	public MathObjectStaticPresenter() {
		super(new MathObjectStaticDisplay());
	}

}
