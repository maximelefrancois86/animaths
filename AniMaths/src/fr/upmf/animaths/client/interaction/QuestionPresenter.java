package fr.upmf.animaths.client.interaction;

import net.customware.gwt.presenter.client.place.Place;
import net.customware.gwt.presenter.client.place.PlaceRequest;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;
import fr.upmf.animaths.client.mvp.AniMathsPresenter;

public class QuestionPresenter extends WidgetPresenter<QuestionPresenter.Display>{

	public static final Place PLACE = new Place("Question");

	/**
	 * Returning a place will allow this presenter to automatically trigger when
	 * '#Greeting' is passed into the browser URL.
	 */
	@Override
	public Place getPlace() {
		return PLACE;
	}

	public QuestionPresenter() {
        super(new QuestionDisplay(), AniMathsPresenter.eventBus );
	}
    
	public interface Display extends WidgetDisplay {
		public void initializeAndCenter();
	}

	@Override
	protected void onBind() {
		// TODO Auto-generated method stub
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
