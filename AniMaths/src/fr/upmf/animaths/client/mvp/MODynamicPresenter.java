package fr.upmf.animaths.client.mvp;

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
import com.google.gwt.user.client.ui.RootPanel;

import fr.upmf.animaths.client.interaction.MOCoreInteraction;
import fr.upmf.animaths.client.interaction.events.DragEvent;
import fr.upmf.animaths.client.interaction.events.DropEvent;
import fr.upmf.animaths.client.interaction.events.FlyOverEvent;
import fr.upmf.animaths.client.interaction.events.GrabEvent;
import fr.upmf.animaths.client.interaction.events.SelectionChangeEvent;
import fr.upmf.animaths.client.interaction.events.SelectionEvent;
import fr.upmf.animaths.client.mvp.MathObject.MOElement;

public class MODynamicPresenter extends MOAbstractPresenter<MODynamicPresenter.Display> {

	static final String id = "dynamic";
	int clientX = 0;
	int clientY = 0;	

	public MODynamicPresenter() {
		super(id, new Display());
	}

	@Override
	public void init(MOElement<?> element) {
		unbind();
		this.element = element.clone();
		this.element.pack(display.asWrapper(),this);
		display.asWrapper().getElement().setId(id);
		RootPanel.get("view").add(display.asWidget());
		bind();
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
