package fr.upmf.animaths.client.mvp;

import java.util.List;

import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.place.Place;
import net.customware.gwt.presenter.client.place.PlaceRequest;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import com.google.gwt.event.dom.client.HasMouseMoveHandlers;
import com.google.inject.Inject;

import fr.upmf.animaths.client.mvp.MathObject.MOAddContainer;
import fr.upmf.animaths.client.mvp.MathObject.MOEquation;
import fr.upmf.animaths.client.mvp.MathObject.MOIdentifier;
import fr.upmf.animaths.client.mvp.MathObject.MOMultiplyContainer;
import fr.upmf.animaths.client.mvp.MathObject.MOMultiplyElement;
import fr.upmf.animaths.client.mvp.MathObject.MONumber;
import fr.upmf.animaths.client.mvp.MathObject.MOSignedElement;

/**
 * The business logic for AniMaths.
 * 
 * @author Maxime Lefrançois
 *
 */
public class AniMathsPresenter extends WidgetPresenter<AniMathsPresenter.Display> {

	public static EventBus eventBus;
	
	private MODynamicPresenter mODynamicPresenter;
	public List<MOStaticPresenter> mOStaticPresenters;
	public List<StaticManipulationWordingPresenter> staticManipulationWordingPresenters;
	
	public interface Display extends WidgetDisplay, HasMouseMoveHandlers{
		public MathWordingWidget getExerciseWordingWidget();
	}

	public static final Place PLACE = new Place("a");

	@Inject
	public AniMathsPresenter(final Display display, final EventBus eventBus) {
		super(display, eventBus);
		AniMathsPresenter.eventBus = eventBus;
		bind();
	}
	
	@Override
	protected void onBind() {
		
		MOIdentifier x = new MOIdentifier("x");
		MOEquation eq = new MOEquation();
		eq.setLeftHandSide(
			new MOAddContainer(
					new MOSignedElement(new MOIdentifier("x"),true),
					new MOSignedElement(new MONumber(10)),
					new MOSignedElement(
							new MOMultiplyContainer(
									new MOMultiplyElement(new MONumber(2)),
									new MOMultiplyElement(
											new MOAddContainer(
													new MOSignedElement(new MOIdentifier("x"),false),
													new MOSignedElement(new MONumber(1),true)
											)
									)
							)
					)
			)
		);
		eq.setRightHandSide(
			new MOMultiplyContainer(
					new MOMultiplyElement(
							new MOSignedElement(
									new MOSignedElement(
											new MOSignedElement(
													new MONumber(3),
											true),
									false),
							true)
					),
					new MOMultiplyElement(
							new MOAddContainer(
									new MOSignedElement(
											new MOMultiplyContainer(
													new MOMultiplyElement(new MONumber(2)),
													new MOMultiplyElement(new MOIdentifier("x"))
											)
									),
									new MOSignedElement(new MONumber(1))
							)
					)
			)
		);

		display.getExerciseWordingWidget().pack("Isoler ", x," dans l'équation ", eq);

		mODynamicPresenter = new MODynamicPresenter();
		mODynamicPresenter.setElement(eq);
		
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