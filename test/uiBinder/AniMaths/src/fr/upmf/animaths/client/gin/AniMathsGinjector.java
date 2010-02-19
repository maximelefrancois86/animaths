package fr.upmf.animaths.client.gin;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;



@GinModules(AniMathsClientModule.class)
public interface AniMathsGinjector extends Ginjector {
	AppPresenter getAppPresenter();	
}
