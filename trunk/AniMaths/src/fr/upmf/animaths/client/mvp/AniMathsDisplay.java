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
	
	private Button loadButton = new Button("recommencer");

	@Inject
	public AniMathsDisplay() {
		initWidget(RootPanel.get("view"));

		loadButton.getElement().setId("load");
	    RootPanel.get("srv-header").add(loadButton);
		
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
	public Button getLoadButton() {
		return loadButton;
	}

	@Override
	public HandlerRegistration addMouseMoveHandler(MouseMoveHandler handler) {
		return addDomHandler(handler,MouseMoveEvent.getType());
	}

}