package fr.upmf.animaths.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;

import fr.upmf.animaths.client.gin.AniMathsGinjector;
import fr.upmf.animaths.client.gin.AppPresenter;

public class AniMaths implements EntryPoint {
	@UiTemplate("Exercice.ui.xml")
	interface Binder extends UiBinder<SplitLayoutPanel, AniMaths> { }
	private static final Binder binder = GWT.create(Binder.class);
	@UiField Button saveButton;
	@UiField TextBox exerciseWording;

	private final AniMathsGinjector injector = GWT.create(AniMathsGinjector.class);
	private ExerciceServiceAsync exerciceService = (ExerciceServiceAsync) GWT.create(ExerciceService.class);

	
	public void onModuleLoad() {	
		SplitLayoutPanel slp = binder.createAndBindUi(this);
		RootPanel.get().add(slp);
		RootPanel.get().add(new HTML("<div>TOTOOOOOOOOOOOOOOOO</div>"));
		AppPresenter appPresenter = injector.getAppPresenter();		
		
		exerciseWording.setText("Toto part en vacances!");
		
		appPresenter.go(RootPanel.get());
		//testRPC();
	}
	
    void testRPC () {
    	exerciceService.GetHint(1, new AsyncCallback<Void>() {

          public void onFailure(Throwable caught) {
            Window.alert("RPC to getHint() failed.");
          }

		public void onSuccess(Void result) {
            Window.alert("RPC SUCCESS.");		
            }
        });

}
}
