package fr.upmf.animaths.client.mvp;

import net.customware.gwt.presenter.client.widget.WidgetDisplay;

import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * The GUI components for AniMath view.
 * 
 * @author Maxime Lefran�ois
 *
 */
public class AniMathsDisplay extends Composite implements AniMathsPresenter.Display {

	private MathWordingWidget exerciseWordingWidget;
	
	private Button loadButton = new Button("Commencer l'exercice");

	@Inject
	public AniMathsDisplay() {
		final AbsolutePanel panel = new AbsolutePanel();

		initWidget(panel);

		panel.add(loadButton);

		RootPanel.get().add(loadButton);
		
		exerciseWordingWidget = new MathWordingWidget();
		panel.add(exerciseWordingWidget);

		// Use RootPanel.get() to get the entire body element
		// Add the widgets to the RootPanel
		RootPanel.get("exerciseWording").add(exerciseWordingWidget);

//		// create and attach Save/Load button 
//		ActionButton ab = new ActionButton(service);
//	    RootPanel.get("srv-header").add(ab.getSaveButton());
//	    RootPanel.get("srv-header").add(ab.getLoadButton());
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
	public Button getLoadButton() {
		return loadButton;
	}

	@Override
	public HandlerRegistration addMouseMoveHandler(MouseMoveHandler handler) {
		return addDomHandler(handler,MouseMoveEvent.getType());
	}

}