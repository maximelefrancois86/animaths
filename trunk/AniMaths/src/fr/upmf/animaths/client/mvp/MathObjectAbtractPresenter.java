package fr.upmf.animaths.client.mvp;

import java.util.HashMap;
import java.util.Map;

import net.customware.gwt.presenter.client.place.Place;
import net.customware.gwt.presenter.client.place.PlaceRequest;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import com.google.gwt.dom.client.Element;

import fr.upmf.animaths.client.mvp.MathObject.MathObjectElementPresenter;

public abstract class MathObjectAbtractPresenter<D extends MathObjectAbstractDisplay> extends WidgetPresenter<D>  {

	public Map<Element,MathObjectElementPresenter<?>> map = new HashMap<Element,MathObjectElementPresenter<?>>();
	protected MathObjectElementPresenter<?> element;

	public MathObjectAbtractPresenter(D display) {
		super(display, AniMathsPresenter.eventBus);
	}

	public void setElement(MathObjectElementPresenter<?> element) {
		this.element = element;
		display.clearWrapper();
		element.pack(display.getWrapper(),this);
	}	

	public void putDOMElement(Element domElement, MathObjectElementPresenter<?> mathObjectElement) {
		map.put(domElement, mathObjectElement);
	}
	
	public MathObjectElementPresenter<?> getElement() {
		return element;
	}

	public static final Place PLACE = new Place("s");

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
