package fr.upmf.animaths.client.mvp;

import net.customware.gwt.presenter.client.place.Place;
import net.customware.gwt.presenter.client.place.PlaceRequest;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.event.dom.client.HasMouseMoveHandlers;
import com.google.gwt.event.dom.client.HasMouseUpHandlers;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.google.gwt.user.client.ui.RootPanel;

import fr.upmf.animaths.client.mvp.MathObject.MathObjectElementPresenter;
import fr.upmf.animaths.client.mvp.events.DragEvent;
import fr.upmf.animaths.client.mvp.events.DropEvent;
import fr.upmf.animaths.client.mvp.events.FlyOverEvent;
import fr.upmf.animaths.client.mvp.events.GrabEvent;
import fr.upmf.animaths.client.mvp.events.SelectionChangeEvent;
import fr.upmf.animaths.client.mvp.events.SelectionEvent;
import fr.upmf.animaths.client.mvp.modele.SelectionElement;

public class DynamicMathObjectPresenter extends MathObjectPresenter<DynamicMathObjectPresenter.Display> {

	int clientX = 0;
	int clientY = 0;	

	public interface Display extends StaticMathObjectPresenter.Display, HasMouseMoveHandlers, HasMouseDownHandlers, HasMouseUpHandlers {
	}
	
	public DynamicMathObjectPresenter() {
		super(new DynamicMathObjectView(), AniMathsPresenter.eventBus);
		bind();
	}

	@Override
	protected void onBind() {
		// Use RootPanel.get() to get the entire body element
		// Add the widgets to the RootPanel
		RootPanel.get("view").add(display.asWidget());

		display.addMouseMoveHandler(new MouseMoveHandler(){
			public void onMouseMove(MouseMoveEvent event) {
				MathObjectElementPresenter<?> element = map.get(
						Element.as(event.getNativeEvent().getEventTarget()));
				if(element!=null) {
					eventBus.fireEvent(new FlyOverEvent(element, event));
//					System.out.println("IN  dragged !");
//					eventBus.fireEvent(new DragEvent(element, event));
				}
			}
		});

		display.addMouseDownHandler(new MouseDownHandler(){
			public void onMouseDown(MouseDownEvent event) {	
				MathObjectElementPresenter<?> element = map.get(
						Element.as(event.getNativeEvent().getEventTarget()));
				if(element!=null) {
					clientX = event.getClientX();
					clientY = event.getClientY();
					eventBus.fireEvent(new GrabEvent(element, event));
				}
			}
		});
		
		display.addMouseUpHandler(new MouseUpHandler(){
			public void onMouseUp(MouseUpEvent event) {
				MathObjectElementPresenter<?> element = map.get(
						Element.as(event.getNativeEvent().getEventTarget()));
				if(element !=null) {
					if(Math.sqrt(Math.pow(clientX-event.getClientX(),2)+Math.pow(clientY-event.getClientY(),2))<10);
						eventBus.fireEvent(new SelectionEvent(element, selectable, event));
				}
			}
		});
		
		Event.addNativePreviewHandler(new NativePreviewHandler() {
		 	public void onPreviewNativeEvent(NativePreviewEvent event) {
		 		MathObjectElementPresenter<?> element;
		 		switch(event.getTypeInt()) {
		 		case Event.ONKEYDOWN :
					eventBus.fireEvent(new SelectionChangeEvent(event.getNativeEvent().getKeyCode()));
		 			break;
		 		case Event.ONMOUSEMOVE :
					element = map.get(Element.as(event.getNativeEvent().getEventTarget()));
					eventBus.fireEvent(new DragEvent(element, event.getNativeEvent()));
					break;
		 		case Event.ONMOUSEUP :
					element = map.get(Element.as(event.getNativeEvent().getEventTarget()));
					eventBus.fireEvent(new DropEvent(element, event.getNativeEvent()));
					break;
		 		}
		 	}
		});
		
		SelectionElement.getInstance().setEnabled(true);
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
