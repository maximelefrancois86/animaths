package fr.upmf.animaths.client.mvp;

import java.util.List;

import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.place.Place;
import net.customware.gwt.presenter.client.place.PlaceRequest;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import com.google.gwt.event.dom.client.HasMouseMoveHandlers;
import com.google.inject.Inject;

import fr.upmf.animaths.client.MathWordingWidget;
import fr.upmf.animaths.client.StaticManipulationWordingPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectAddContainerPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectEquationPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectIdentifierPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectMultiplyContainerPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectMultiplyElementPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectNumberPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectSignedElementPresenter;

/**
 * The business logic for AniMaths.
 * 
 * @author Maxime Lefrançois
 *
 */
public class AniMathsPresenter extends WidgetPresenter<AniMathsPresenter.Display> {

	public static EventBus eventBus;
	
	private MathObjectDynamicPresenter mathObjectDynamicPresenter;
	public List<MathObjectStaticPresenter> mathObjectStaticPresenters;
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
		
		MathObjectIdentifierPresenter x = new MathObjectIdentifierPresenter("x");
		MathObjectEquationPresenter eq = new MathObjectEquationPresenter();
		eq.setLeftHandSide(
			new MathObjectAddContainerPresenter(
					new MathObjectSignedElementPresenter(new MathObjectIdentifierPresenter("x"),true),
					new MathObjectSignedElementPresenter(new MathObjectNumberPresenter(10)),
					new MathObjectSignedElementPresenter(
							new MathObjectMultiplyContainerPresenter(
									new MathObjectMultiplyElementPresenter(new MathObjectNumberPresenter(2)),
									new MathObjectMultiplyElementPresenter(
											new MathObjectAddContainerPresenter(
													new MathObjectSignedElementPresenter(new MathObjectIdentifierPresenter("x"),false),
													new MathObjectSignedElementPresenter(new MathObjectNumberPresenter(1),true)
											)
									)
							)
					)
			)
		);
		eq.setRightHandSide(
			new MathObjectMultiplyContainerPresenter(
					new MathObjectMultiplyElementPresenter(new MathObjectNumberPresenter(3)),
					new MathObjectMultiplyElementPresenter(
							new MathObjectAddContainerPresenter(
									new MathObjectSignedElementPresenter(
											new MathObjectMultiplyContainerPresenter(
													new MathObjectMultiplyElementPresenter(new MathObjectNumberPresenter(2)),
													new MathObjectMultiplyElementPresenter(new MathObjectIdentifierPresenter("x"))
											)
									),
									new MathObjectSignedElementPresenter(new MathObjectNumberPresenter(1))
							)
					)
			)
		);

		display.getExerciseWordingWidget().pack("Isoler ", x," dans l'équation ", eq);

		mathObjectDynamicPresenter = new MathObjectDynamicPresenter();
		mathObjectDynamicPresenter.setElement(eq);
		
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