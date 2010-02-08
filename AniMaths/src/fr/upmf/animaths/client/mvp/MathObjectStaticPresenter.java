package fr.upmf.animaths.client.mvp;



public class MathObjectStaticPresenter extends MathObjectAbtractPresenter<MathObjectStaticPresenter.Display> {

	public interface Display extends MathObjectAbstractDisplay {
	}
	
	public MathObjectStaticPresenter() {
		super(new MathObjectStaticDisplay());
	}

}
