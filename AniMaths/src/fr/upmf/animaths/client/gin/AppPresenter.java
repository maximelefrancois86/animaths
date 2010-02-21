package fr.upmf.animaths.client.gin;

import com.google.gwt.user.client.ui.HasWidgets;
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

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	@Inject
	public AppPresenter(final AniMathsPresenter aniMathsPresenter) {
		this.aniMathsPresenter = aniMathsPresenter;	
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