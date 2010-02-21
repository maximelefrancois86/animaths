package fr.upmf.animaths.client.gin;

import com.google.gwt.gen2.logging.handler.client.RemoteLogHandler.ServiceAsync;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;

import fr.upmf.animaths.client.mvp.AniMathsPresenter;
/**
 * Represents the main application
 * 
 * @author Maxime Lefran�ois
 *
 */
public class AppPresenter {
	private HasWidgets container;
	private AniMathsPresenter aniMathsPresenter;
	private ServiceAsync service;

	@Inject
	public AppPresenter(final AniMathsPresenter aniMathsPresenter, ServiceAsync service) {
		this.aniMathsPresenter = aniMathsPresenter;	
		this.service = service;
	}
	
	public void go(final HasWidgets container) {
		this.container = container;		
		showMain();
	}

	private void showMain() {
		container.clear();
		container.add(aniMathsPresenter.getDisplay().asWidget());
	}

		
}