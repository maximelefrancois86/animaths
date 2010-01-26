package fr.upmf.animaths.client.mvp;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.presenter.client.widget.WidgetDisplay;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.upmf.animaths.client.mvp.widgets.DynamicMathObjectWidget;
import fr.upmf.animaths.client.mvp.widgets.MathWordingWidget;
import fr.upmf.animaths.client.mvp.widgets.StaticManipulationWordingWidget;
import fr.upmf.animaths.client.mvp.widgets.StaticMathObjectWidget;
/**
 * The GUI components for AniMath view.
 * 
 * @author Maxime Lefrançois
 *
 */
public class AniMathsView extends Composite implements AniMathsPresenter.Display {

	private MathWordingWidget exerciseWordingWidget;
	private DynamicMathObjectWidget dynamicMathObjectWidget;
	private List<StaticMathObjectWidget> staticMathObjectWidgets = new ArrayList<StaticMathObjectWidget>();
	private List<StaticManipulationWordingWidget> staticManipulationWordingWidgets = new ArrayList<StaticManipulationWordingWidget>();

	
	public AniMathsView() {
		final AbsolutePanel panel = new AbsolutePanel();

		initWidget(panel);

		exerciseWordingWidget = new MathWordingWidget();
		panel.add(exerciseWordingWidget);
		
		dynamicMathObjectWidget = new DynamicMathObjectWidget();

		// Add the widgets to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("exerciseWording").add(exerciseWordingWidget);
		RootPanel.get("view").add(dynamicMathObjectWidget);
		
		reset();
	}

	public void reset() {
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
	public DynamicMathObjectWidget getDynamicMathObjectWidget() {
		return dynamicMathObjectWidget;
	}

	@Override
	public MathWordingWidget getExerciseWordingWidget() {
		return exerciseWordingWidget;
	}

	@Override
	public List<StaticManipulationWordingWidget> getStaticManipulationWordingWidgets() {
		return staticManipulationWordingWidgets;
	}

	@Override
	public List<StaticMathObjectWidget> getStaticMathObjectWidgets() {
		return staticMathObjectWidgets;
	}

}