package fr.upmf.animaths.client.gin;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

import fr.upmf.animaths.client.mvp.AppPresenter;


@GinModules(AniMathsClientModule.class)
public interface AniMathsGinjector extends Ginjector {
	AppPresenter getAppPresenter();	
}
