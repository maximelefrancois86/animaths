//package fr.upmf.animaths.client;
//
//import com.google.gwt.core.client.GWT;
//import com.google.gwt.event.dom.client.ClickEvent;
//import com.google.gwt.event.dom.client.ClickHandler;
//import com.google.gwt.gen2.logging.handler.client.RemoteLogHandler.ServiceAsync;
//import com.google.gwt.user.client.Window;
//import com.google.gwt.user.client.ui.Button;
//import com.google.gwt.user.client.ui.Widget;
//import com.google.inject.Inject;
//
//public class ActionButton implements ClickHandler {
//
//	private Button saveButton = new Button("Save");
//	private Button loadButton = new Button("Load");
////	/** An rpc proxy for making calls to the server. */
//	
//	ServiceAsync service = (ServiceAsync) GWT.create(Service.class);
//	
//	((ServiceDefTarget) service).setServiceEntryPoint( GWT.getModuleBaseURL() + "/AniMaths/exercice");
//
//	@Inject
//	public ActionButton(ServiceAsync service) {
//		super();
////		this.service = service;
//		saveButton.getElement().setId("save");
//		saveButton.addClickHandler(this);
//		loadButton.getElement().setId("load");
//		loadButton.addClickHandler(this);					
//	}
//
//	@Override
//	public void onClick(ClickEvent event) {
//	    // Initialize the service proxy.
//	    if (service == null) {
//	    	service = (ServiceAsync) GWT.create(Service.class);
//	    }
//		Widget sender = (Widget) event.getSource();
//
//		if (sender == saveButton) {
//		    // Set up the callback object.
//			AsyncCallback<Equation> savecallback = new AsyncCallback<Equation>() {
//		      public void onFailure(Throwable caught) {
//		    	  Window.alert("Save Fail");
//		      }
//		      public void onSuccess(Equation result) {
//		    	  Window.alert("Save is Successful");
//		      }
//		    };	
//		    
//		    service.saveExercice(savecallback);
//			Window.alert("save");
//		} else if (sender == loadButton) {
//		    // Set up the callback object.
//			AsyncCallback<Boolean> loadcallback = new AsyncCallback<Boolean>() {
//		      public void onFailure(Throwable caught) {
//		    	  Window.alert("Fail");
//		      }
//
//		      public void onSuccess(Boolean result) {
//		    	  Window.alert("Success");
//		      }
//		    };
//
//		    service.loadExercice("test", loadcallback);
//		}
//	}
//
//	public Widget getSaveButton() {
//		// TODO Auto-generated method stub
//		return saveButton;
//	}
//
//	public Widget getLoadButton() {
//		// TODO Auto-generated method stub
//		return loadButton;
//	}
//}
