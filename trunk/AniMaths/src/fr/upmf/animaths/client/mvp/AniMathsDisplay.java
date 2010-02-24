package fr.upmf.animaths.client.mvp;

import net.customware.gwt.presenter.client.widget.WidgetDisplay;

import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * The GUI components for AniMath view.
 * 
 * @author Maxime Lefran&#65533;ois
 *
 */
public class AniMathsDisplay extends Composite implements AniMathsPresenter.Display {

	private MathWordingWidget exerciseWordingWidget;
	
	private Button tutorielButton = new Button("Tutoriel");
	private Button exerciseButton = new Button("Commencer un exercice");

	private Button previousButton = new Button("Précédent");
	private Button restartButton = new Button("Recommencer");
	private Button nextButton = new Button("Suivant");

	@Inject
	public AniMathsDisplay() {
		initWidget(RootPanel.get("view"));

		tutorielButton.getElement().setId("tutoriel");
		exerciseButton.getElement().setId("exercice");
	    RootPanel.get("srv-start").add(tutorielButton);
	    RootPanel.get("srv-start").add(exerciseButton);
		
		previousButton.getElement().setId("previous");
		restartButton.getElement().setId("restart");
		nextButton.getElement().setId("next");
	    RootPanel.get("srv-navigation").add(previousButton);
	    RootPanel.get("srv-navigation").add(restartButton);
	    RootPanel.get("srv-navigation").add(nextButton);
		
		exerciseWordingWidget = new MathWordingWidget(RootPanel.get("exerciseWording"));
	}

	/**
	 * Returns this widget as the {@link WidgetDisplay#asWidget()} value.
	 */
	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void startProcessing() {
	}

	@Override
	public void stopProcessing() {
	}

	@Override
	public MathWordingWidget getExerciseWordingWidget() {
		return exerciseWordingWidget;
	}

	@Override
	public HandlerRegistration addMouseMoveHandler(MouseMoveHandler handler) {
		return addDomHandler(handler,MouseMoveEvent.getType());
	}

	@Override
	public Button getTutorielButton() {
		return tutorielButton;
	}
	@Override
	public Button getExerciseButton() {
		return exerciseButton;
	}

	@Override
	public Button getPreviousButton() {
		return previousButton;
	}

	@Override
	public Button getRestartButton() {
		return restartButton;
	}

	@Override
	public Button getNextButton() {
		return nextButton;
	}

}