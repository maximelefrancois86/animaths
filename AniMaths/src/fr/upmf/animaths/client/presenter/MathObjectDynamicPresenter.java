package fr.upmf.animaths.client.presenter;

import net.customware.gwt.presenter.client.place.Place;
import net.customware.gwt.presenter.client.place.PlaceRequest;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.event.dom.client.HasMouseMoveHandlers;
import com.google.gwt.event.dom.client.HasMouseUpHandlers;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.google.gwt.user.client.ui.RootPanel;

import fr.upmf.animaths.client.display.MathObjectDynamicDisplay;
import fr.upmf.animaths.client.interaction.SelectionElement;
import fr.upmf.animaths.client.interaction.events.dragndrop.DragEvent;
import fr.upmf.animaths.client.interaction.events.dragndrop.DropEvent;
import fr.upmf.animaths.client.interaction.events.dragndrop.GrabEvent;
import fr.upmf.animaths.client.interaction.events.selection.FlyOverEvent;
import fr.upmf.animaths.client.interaction.events.selection.SelectionChangeEvent;
import fr.upmf.animaths.client.interaction.events.selection.SelectionEvent;
import fr.upmf.animaths.client.interaction.process.core.AddCommutation;
import fr.upmf.animaths.client.presenter.MathObject.MathObjectElementPresenter;

public class MathObjectDynamicPresenter extends MathObjectPresenter<MathObjectDynamicPresenter.Display> {

	int clientX = 0;
	int clientY = 0;	

	public interface Display extends MathObjectStaticPresenter.Display, HasMouseMoveHandlers, HasMouseDownHandlers, HasMouseUpHandlers {
	}
	
	public MathObjectDynamicPresenter() {
		super(new MathObjectDynamicDisplay());
		bind();
	}

	@Override
	protected void onBind() {
		RootPanel.get("view").add(display.asWidget());
		display.addMouseMoveHandler(new MouseMoveHandler(){
			public void onMouseMove(MouseMoveEvent event) {
				MathObjectElementPresenter<?> element = map.get(Element.as(event.getNativeEvent().getEventTarget()));
				if(element!=null)
					eventBus.fireEvent(new FlyOverEvent(element, event));
			}
		});

		display.addMouseDownHandler(new MouseDownHandler(){
			public void onMouseDown(MouseDownEvent event) {	
				MathObjectElementPresenter<?> element = map.get(Element.as(event.getNativeEvent().getEventTarget()));
				if(element!=null) {
					clientX = event.getClientX();
					clientY = event.getClientY();
					eventBus.fireEvent(new GrabEvent(element, event));
				}
			}
		});
		
		Event.addNativePreviewHandler(new NativePreviewHandler() {
		 	public void onPreviewNativeEvent(NativePreviewEvent event) {
	 			EventTarget target = event.getNativeEvent().getEventTarget();
	 			if(target==null)
	 				System.out.println("erreur evitee");
		 		MathObjectElementPresenter<?> element = (target==null)?null:map.get(Element.as(target));
		 		switch(event.getTypeInt()) {
		 		case Event.ONKEYUP :
					eventBus.fireEvent(new SelectionChangeEvent(event.getNativeEvent().getKeyCode()));
		 			break;
		 		case Event.ONMOUSEMOVE :
					eventBus.fireEvent(new DragEvent(element, event.getNativeEvent()));
					break;
		 		case Event.ONMOUSEUP :
					MathObjectElementPresenter<?> nelement = map.get(
							Element.as(event.getNativeEvent().getEventTarget()));
					if(Math.sqrt(Math.pow(clientX-event.getNativeEvent().getClientX(),2)+Math.pow(clientY-event.getNativeEvent().getClientY(),2))<10);
						eventBus.fireEvent(new SelectionEvent(nelement, event.getNativeEvent()));
					eventBus.fireEvent(new DropEvent(element, event.getNativeEvent()));
					break;
		 		}
		 	}
		});
		
		SelectionElement.getInstance(this).setEnabled(true);
		AddCommutation.getInstance(this).setEnabled(true);
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
