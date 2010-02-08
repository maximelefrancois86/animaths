package fr.upmf.animaths.client.mvp;

import net.customware.gwt.presenter.client.place.Place;
import net.customware.gwt.presenter.client.place.PlaceRequest;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.event.dom.client.HasMouseUpHandlers;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.google.gwt.user.client.ui.RootPanel;

import fr.upmf.animaths.client.interaction.SelectionElement;
import fr.upmf.animaths.client.interaction.events.DragEvent;
import fr.upmf.animaths.client.interaction.events.DropEvent;
import fr.upmf.animaths.client.interaction.events.FlyOverEvent;
import fr.upmf.animaths.client.interaction.events.GrabEvent;
import fr.upmf.animaths.client.interaction.events.SelectionChangeEvent;
import fr.upmf.animaths.client.interaction.events.SelectionEvent;
import fr.upmf.animaths.client.interaction.process.AddCommutation;
import fr.upmf.animaths.client.interaction.process.MultCommutation;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectElementPresenter;

public class MathObjectDynamicPresenter extends MathObjectAbtractPresenter<MathObjectDynamicPresenter.Display> {

	int clientX = 0;
	int clientY = 0;	

	public interface Display extends MathObjectStaticPresenter.Display, HasMouseDownHandlers, HasMouseUpHandlers {
	}
	
	public MathObjectDynamicPresenter() {
		super(new MathObjectDynamicDisplay());
		bind();
	}

	@Override
	protected void onBind() {
		RootPanel.get("view").add(display.asWidget());
		display.addMouseDownHandler(new MouseDownHandler(){
			public void onMouseDown(MouseDownEvent event) {	
				MathObjectElementPresenter<?> element = map.get(Element.as(event.getNativeEvent().getEventTarget()));
				if(element!=null) {
					clientX = event.getClientX();
					clientY = event.getClientY();
					eventBus.fireEvent(new GrabEvent(element.getStyleClass(), event));
				}
			}
		});
		
		Event.addNativePreviewHandler(new NativePreviewHandler() {
		 	public void onPreviewNativeEvent(NativePreviewEvent event) {
		 		MathObjectElementPresenter<?> element = map.get(Element.as(event.getNativeEvent().getEventTarget()));
		 		switch(event.getTypeInt()) {
		 		case Event.ONKEYUP :
					eventBus.fireEvent(new SelectionChangeEvent(event.getNativeEvent().getKeyCode()));
		 			break;
		 		case Event.ONMOUSEMOVE :
					eventBus.fireEvent(new FlyOverEvent(element));
					eventBus.fireEvent(new DragEvent(element, event.getNativeEvent()));
					break;
		 		case Event.ONMOUSEUP :
					if(Math.sqrt(Math.pow(clientX-event.getNativeEvent().getClientX(),2)+Math.pow(clientY-event.getNativeEvent().getClientY(),2))<10);
						eventBus.fireEvent(new SelectionEvent(element));
					eventBus.fireEvent(new DropEvent(element, event.getNativeEvent()));
					break;
		 		}
		 	}
		});
		
		SelectionElement.setPresenterAndRun(this);
		AddCommutation.setEnabled();
		MultCommutation.setEnabled();
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
