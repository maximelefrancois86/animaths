package fr.upmf.animaths.client.mvp;

import net.customware.gwt.presenter.client.widget.WidgetDisplay;

import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.gen2.logging.handler.client.RemoteLogHandler.ServiceAsync;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import fr.upmf.animaths.client.ActionButton;

/**
 * The GUI components for AniMath view.
 * 
 * @author Maxime Lefran�ois
 *
 */
public class AniMathsDisplay extends Composite implements AniMathsPresenter.Display {

	private MathWordingWidget exerciseWordingWidget;
	
	@Inject
	public AniMathsDisplay(ServiceAsync service) {
		final AbsolutePanel panel = new AbsolutePanel();

		initWidget(panel);

		exerciseWordingWidget = new MathWordingWidget();
		panel.add(exerciseWordingWidget);
		
		// create and attach Save/Load button 
		ActionButton ab = new ActionButton(service);
	    RootPanel.get("srv-header").add(ab.getSaveButton());
	    RootPanel.get("srv-header").add(ab.getLoadButton());

		// Use RootPanel.get() to get the entire body element
		// Add the widgets to the RootPanel
		RootPanel.get("exerciseWording").add(exerciseWordingWidget);
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

}