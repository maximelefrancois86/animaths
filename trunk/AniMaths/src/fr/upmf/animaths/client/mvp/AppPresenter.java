package fr.upmf.animaths.client.mvp;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;
/**
 * Represents the main application
 * 
 * @author Maxime Lefrançois
 *
 */
public class AppPresenter {
	private HasWidgets container;
	private AniMathsPresenter aniMathsPresenter;

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