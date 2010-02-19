package fr.upmf.animaths.client.mvp;



public class MOStaticPresenter extends MOAbtractPresenter<MOStaticPresenter.Display> {

	public interface Display extends MOAbstractDisplay {
	}
	
	public MOStaticPresenter() {
		super(new MOStaticDisplay());
	}

}
