package fr.upmf.animaths.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

import fr.upmf.animaths.client.gin.AniMathsGinjector;
import fr.upmf.animaths.client.gin.AppPresenter;

public class AniMaths implements EntryPoint {
	private final AniMathsGinjector injector = GWT.create(AniMathsGinjector.class);	

	public void onModuleLoad() {
		AppPresenter appPresenter = injector.getAppPresenter();
		appPresenter.go(RootPanel.get());
		
	    Window.enableScrolling(false);
	    Window.setMargin("0px");
	    Window.setTitle("AniMaths › exercices");
	}
	
}
