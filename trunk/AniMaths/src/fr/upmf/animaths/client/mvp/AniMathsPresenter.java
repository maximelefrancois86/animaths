package fr.upmf.animaths.client.mvp;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.place.Place;
import net.customware.gwt.presenter.client.place.PlaceRequest;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasMouseMoveHandlers;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.XMLParser;
import com.google.inject.Inject;

import fr.upmf.animaths.client.AniMathsService;
import fr.upmf.animaths.client.AniMathsServiceAsync;
import fr.upmf.animaths.client.interaction.events.NewLineEvent;
import fr.upmf.animaths.client.interaction.events.NewLineHandler;
import fr.upmf.animaths.client.mvp.MathObject.MOElement;
import fr.upmf.animaths.client.mvp.MathObject.MOIdentifier;

/**
 * The business logic for AniMaths.
 * 
 * @author Maxime Lefrançois
 *
 */
public class AniMathsPresenter extends WidgetPresenter<AniMathsPresenter.Display> {

	public static EventBus eventBus;
	private static final AniMathsServiceAsync aniMathsService = GWT.create(AniMathsService.class);

	private MODynamicPresenter mODynamicPresenter;
	public List<MOBasicPresenter> mOBasicPresenters;
//	public Map<Integer,StaticManipulationWordingPresenter> staticManipulationWordingPresenters = new HashMap<String,StaticManipulationWordingPresenter>();

	public interface Display extends WidgetDisplay, HasMouseMoveHandlers{
		public MathWordingWidget getExerciseWordingWidget();
		public Button getLoadButton();
	}

	public static final Place PLACE = new Place("a");

	/**
	 * Returning a place will allow this presenter to automatically trigger when
	 * '#exercice' is passed into the browser URL.
	 */
	@Override
	public Place getPlace() {
		return PLACE;
	}

	@Inject
	public AniMathsPresenter(final Display display, final EventBus eventBus) {
		super(display, eventBus);
		AniMathsPresenter.eventBus = eventBus;
		mODynamicPresenter = new MODynamicPresenter();
		mOBasicPresenters = new ArrayList<MOBasicPresenter>();
		eventBus.addHandler(NewLineEvent.getType(),new NewLineHandler() {
			public void onNewLine(NewLineEvent event) {
				MOBasicPresenter newLine = new MOBasicPresenter(mODynamicPresenter.getElement().clone());
				mOBasicPresenters.add(newLine);
				RootPanel.get("view").insert(newLine.getDisplay().asWidget(), RootPanel.get("view").getWidgetCount()-1);
			}
		});
		bind();
	}
	
	@Override
	protected void onBind() {	

		loadProblem("equation6");

		display.getLoadButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				loadProblem("equation");
			}
		});
	}

	@Override
	protected void onPlaceRequest(final PlaceRequest request) {
		// Grab the 'id' from the request and load the 'id' equation.
		final String id = request.getParameter("id", null);
		if(id != null) {
			loadProblem(id);
		}
	}
	
	@Override
	protected void onUnbind() {
		// Add unbind functionality here for more complex presenters.
	}

	@Override
	public void refreshDisplay() {
		// This is called when the presenter should pull the latest data
		// from the server, etc. In this case, there is nothing to do.
	}

	@Override
	public void revealDisplay() {
		// Nothing to do. This is more useful in UI which may be buried
		// in a tab bar, tree, etc.
	}

	/**
	 * @return
	 */
	private void loadProblem(String id) {
		for(MOBasicPresenter basicPresenter : mOBasicPresenters)
			basicPresenter.unbind();
		mOBasicPresenters.clear();
		while(RootPanel.get("view").getElement().getChildCount()>1)
			RootPanel.get("view").getElement().getFirstChild().removeFromParent();
	    // Set up the callback object.
	    AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {
				Window.alert("échec du chargement de l'équation...");
			}

			public void onSuccess(String result) {
				System.out.println(result);
				Element element = XMLParser.parse(result).getDocumentElement();
				MOElement<?> eq = MOElement.parse(element);
				display.getExerciseWordingWidget().setWording("Isoler ", new MOIdentifier("x")," dans l'équation ", eq);
				mODynamicPresenter.init(eq);
			}
	    };

	    // Make the call.
	    aniMathsService.loadEquation(id, callback);
	}
	
}