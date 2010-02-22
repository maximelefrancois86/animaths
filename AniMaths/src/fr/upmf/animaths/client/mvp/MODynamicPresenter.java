package fr.upmf.animaths.client.mvp;

import net.customware.gwt.presenter.client.place.Place;
import net.customware.gwt.presenter.client.place.PlaceRequest;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.event.dom.client.HasMouseUpHandlers;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;

import fr.upmf.animaths.client.interaction.MOCoreInteraction;
import fr.upmf.animaths.client.interaction.events.DragEvent;
import fr.upmf.animaths.client.interaction.events.DropEvent;
import fr.upmf.animaths.client.interaction.events.FlyOverEvent;
import fr.upmf.animaths.client.interaction.events.GrabEvent;
import fr.upmf.animaths.client.interaction.events.SelectionChangeEvent;
import fr.upmf.animaths.client.interaction.events.SelectionEvent;
import fr.upmf.animaths.client.mvp.MathObject.MOElement;

public class MODynamicPresenter extends MOAbstractPresenter<MODynamicPresenter.Display> {

	private static final String id = "dynamic";
	int clientX = 0;
	int clientY = 0;	
	HandlerRegistration hrMouseDown;
	HandlerRegistration hrNativePreview;

	@Override
	public Place getPlace() {
		return PLACE;
	}

	public MODynamicPresenter() {
		super(id, new Display());
	}

	@Override
	protected void onBind() {
		registerHandler(display.addMouseDownHandler(new MouseDownHandler(){
			public void onMouseDown(MouseDownEvent event) {	
				MOElement<?> element = map.get(Element.as(event.getNativeEvent().getEventTarget()));
				if(element!=null) {
					clientX = event.getClientX();
					clientY = event.getClientY();
					eventBus.fireEvent(new GrabEvent(element.getStyleClass(), event));
				}
			}
		}));
		
		registerHandler(Event.addNativePreviewHandler(new NativePreviewHandler() {
		 	public void onPreviewNativeEvent(NativePreviewEvent event) {
		 		EventTarget target = event.getNativeEvent().getEventTarget();
		 		if(!Element.is(target))
		 			return;
		 		MOElement<?> element = map.get(Element.as(event.getNativeEvent().getEventTarget()));
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
		}));
		MOCoreInteraction.setPresenterAndRun(this);
	}

	
	@Override
	protected void onUnbind() {
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

	static class Display extends MOBasicDisplay implements HasMouseDownHandlers, HasMouseUpHandlers  {
		public Display() {
			super();
		}

		@Override
		public HandlerRegistration addMouseDownHandler(MouseDownHandler handler) {
			return addDomHandler(handler,MouseDownEvent.getType());
		}

		@Override
		public HandlerRegistration addMouseUpHandler(MouseUpHandler handler) {
			return addDomHandler(handler,MouseUpEvent.getType());
		}

	}
	
}
