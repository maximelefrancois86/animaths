package fr.upmf.animaths.client.mvp;

import java.util.List;

import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.place.Place;
import net.customware.gwt.presenter.client.place.PlaceRequest;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.google.inject.Inject;

import fr.upmf.animaths.client.mvp.events.DropEvent;
import fr.upmf.animaths.client.mvp.events.FlyOverEvent;
import fr.upmf.animaths.client.mvp.events.GrabEvent;
import fr.upmf.animaths.client.mvp.events.SelectionChangeEvent;
import fr.upmf.animaths.client.mvp.events.SelectionEvent;
import fr.upmf.animaths.client.mvp.modele.SelectableElement;
import fr.upmf.animaths.client.mvp.modele.MathObject.MathObjectAddContainer;
import fr.upmf.animaths.client.mvp.modele.MathObject.MathObjectEquation;
import fr.upmf.animaths.client.mvp.modele.MathObject.MathObjectIdentifier;
import fr.upmf.animaths.client.mvp.modele.MathObject.MathObjectMultiplyContainer;
import fr.upmf.animaths.client.mvp.modele.MathObject.MathObjectMultiplyElement;
import fr.upmf.animaths.client.mvp.modele.MathObject.MathObjectNumber;
import fr.upmf.animaths.client.mvp.modele.MathObject.MathObjectSignedElement;
import fr.upmf.animaths.client.mvp.widgets.DynamicMathObjectWidget;
import fr.upmf.animaths.client.mvp.widgets.MathWordingWidget;
import fr.upmf.animaths.client.mvp.widgets.StaticManipulationWordingWidget;
import fr.upmf.animaths.client.mvp.widgets.StaticMathObjectWidget;
import fr.upmf.animaths.client.mvp.widgets.MathML.MathMLElement;

/**
 * The business logic for AniMaths.
 * 
 * @author Maxime Lefrançois
 *
 */
public class AniMathsPresenter extends WidgetPresenter<AniMathsPresenter.Display> {

	private SelectableElement selectable = null;
	
	public interface Display extends WidgetDisplay {
		public MathWordingWidget getExerciseWordingWidget();
		public DynamicMathObjectWidget getDynamicMathObjectWidget();
		public List<StaticMathObjectWidget> getStaticMathObjectWidgets();
		public List<StaticManipulationWordingWidget> getStaticManipulationWordingWidgets();
	}

	public static final Place PLACE = new Place("a");

	int clientX = 0;
	int clientY = 0;
	
	@Inject
	public AniMathsPresenter(final Display display, final EventBus eventBus) {
		super(display, eventBus);
		bind();
	}
	
	@Override
	protected void onBind() {
		
		MathObjectIdentifier x = new MathObjectIdentifier("x");
		MathObjectEquation eq = new MathObjectEquation();
		eq.setLeftHandSide(
			new MathObjectAddContainer(
					new MathObjectSignedElement(new MathObjectIdentifier("x"),true),
					new MathObjectSignedElement(new MathObjectNumber(10)),
					new MathObjectSignedElement(
							new MathObjectMultiplyContainer(
									new MathObjectMultiplyElement(new MathObjectNumber(2)),
									new MathObjectMultiplyElement(
											new MathObjectAddContainer(
													new MathObjectSignedElement(new MathObjectIdentifier("x"),false),
													new MathObjectSignedElement(new MathObjectNumber(1),true)
											)
									)
							)
					)
			)
		);
		eq.setRightHandSide(
			new MathObjectMultiplyContainer(
					new MathObjectMultiplyElement(new MathObjectNumber(3)),
					new MathObjectMultiplyElement(
							new MathObjectAddContainer(
									new MathObjectSignedElement(
											new MathObjectMultiplyContainer(
													new MathObjectMultiplyElement(new MathObjectNumber(2)),
													new MathObjectMultiplyElement(new MathObjectIdentifier("x"))
											)
									),
									new MathObjectSignedElement(new MathObjectNumber(1))
							)
					)
			)
		);

		display.getExerciseWordingWidget().pack("Isoler ", x," dans l'équation ", eq);

		display.getDynamicMathObjectWidget().setValue(eq);
		
		selectable = new SelectableElement(eventBus);
		selectable.setEnabled(true);

		display.getDynamicMathObjectWidget().addMouseMoveHandler(new MouseMoveHandler(){
			public void onMouseMove(MouseMoveEvent event) {
				MathMLElement widget = MathMLElement.map.get(
						Element.as(event.getNativeEvent().getEventTarget()));
				if(widget!=null)
					eventBus.fireEvent(new FlyOverEvent(widget, event));
			}
		});

		display.getDynamicMathObjectWidget().addMouseDownHandler(new MouseDownHandler(){
			public void onMouseDown(MouseDownEvent event) {	
				MathMLElement widget = MathMLElement.map.get(
						Element.as(event.getNativeEvent().getEventTarget()));
				if(widget!=null) {
					clientX = event.getClientX();
					clientY = event.getClientY();
					eventBus.fireEvent(new GrabEvent(widget, event));
				}
			}
		});
		
		display.getDynamicMathObjectWidget().addMouseUpHandler(new MouseUpHandler(){
			public void onMouseUp(MouseUpEvent event) {
				MathMLElement widget = MathMLElement.map.get(
						Element.as(event.getNativeEvent().getEventTarget()));
				if(widget!=null) {
					eventBus.fireEvent(new DropEvent(widget, event));
					if(Math.sqrt(Math.pow(clientX-event.getClientX(),2)+Math.pow(clientY-event.getClientY(),2))<10);
						eventBus.fireEvent(new SelectionEvent(widget, selectable, event));
				}
			}
		});
		
		Event.addNativePreviewHandler(new NativePreviewHandler() {
		 	public void onPreviewNativeEvent(NativePreviewEvent event) {
		 		if (event.getTypeInt()==Event.ONKEYDOWN)
					eventBus.fireEvent(new SelectionChangeEvent(event.getNativeEvent().getKeyCode()));
		 	}
		});
		
	}

	@Override
	protected void onUnbind() {
		// Add unbind functionality here for more complex presenters.
	}

	public void refreshDisplay() {
		// This is called when the presenter should pull the latest data
		// from the server, etc. In this case, there is nothing to do.
	}

	public void revealDisplay() {
		// Nothing to do. This is more useful in UI which may be buried
		// in a tab bar, tree, etc.
	}

	/**
	 * Returning a place will allow this presenter to automatically trigger when
	 * '#Greeting' is passed into the browser URL.
	 */
	@Override
	public Place getPlace() {
		return PLACE;
	}

	@Override
	protected void onPlaceRequest(final PlaceRequest request) {
//		// Grab the 'name' from the request and put it into the 'name' field.
//		// This allows a tag of '#Greeting;name=Foo' to populate the name
//		// field.
//		final String name = request.getParameter("name", null);
//		
//		if (name != null) {
//			display.getName().setValue(name);
//		}
	}
}