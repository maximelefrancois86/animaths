package fr.upmf.animaths.client.gin;

import net.customware.gwt.presenter.client.DefaultEventBus;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.gin.AbstractPresenterModule;

import com.google.inject.Singleton;

import fr.upmf.animaths.client.mvp.AniMathsPresenter;
import fr.upmf.animaths.client.mvp.AniMathsView;
import fr.upmf.animaths.client.mvp.AppPresenter;

public class AniMathsClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		
		bind(EventBus.class).to(DefaultEventBus.class).in(Singleton.class);
		
		bindPresenter(AniMathsPresenter.class, AniMathsPresenter.Display.class, AniMathsView.class);		
//		bindPresenter(StaticManipulationWordingPresenter.class, StaticManipulationWordingPresenter.Display.class, StaticManipulationWordingView.class);
//		bindPresenter(StaticMathObjectPresenter.class, StaticMathObjectPresenter.Display.class, StaticMathObjectView.class);
//		bindPresenter(DynamicMathObjectPresenter.class, DynamicMathObjectPresenter.Display.class, DynamicMathObjectView.class);
		
		bind(AppPresenter.class).in(Singleton.class);
	}
}