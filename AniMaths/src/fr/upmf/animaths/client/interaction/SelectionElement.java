package fr.upmf.animaths.client.interaction;

import java.util.HashMap;
import java.util.Map;

import net.customware.gwt.presenter.client.EventBus;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.RootPanel;

import fr.upmf.animaths.client.interaction.events.DragEvent;
import fr.upmf.animaths.client.interaction.events.DragHandler;
import fr.upmf.animaths.client.interaction.events.DropEvent;
import fr.upmf.animaths.client.interaction.events.DropHandler;
import fr.upmf.animaths.client.interaction.events.FlyOverEvent;
import fr.upmf.animaths.client.interaction.events.FlyOverHandler;
import fr.upmf.animaths.client.interaction.events.GrabEvent;
import fr.upmf.animaths.client.interaction.events.GrabHandler;
import fr.upmf.animaths.client.interaction.events.SelectionChangeEvent;
import fr.upmf.animaths.client.interaction.events.SelectionChangeHandler;
import fr.upmf.animaths.client.interaction.events.SelectionEvent;
import fr.upmf.animaths.client.interaction.events.SelectionHandler;
import fr.upmf.animaths.client.interaction.process.event.DragSelectedEvent;
import fr.upmf.animaths.client.interaction.process.event.DropSelectedEvent;
import fr.upmf.animaths.client.interaction.process.event.GrabSelectedEvent;
import fr.upmf.animaths.client.mvp.AniMathsPresenter;
import fr.upmf.animaths.client.mvp.MathObjectDynamicPresenter;
import fr.upmf.animaths.client.mvp.MathObjectStaticPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectElementPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectEquationPresenter;

public class SelectionElement implements FlyOverHandler, SelectionHandler, SelectionChangeHandler, GrabHandler, DragHandler, DropHandler {
	
	private static SelectionElement instance = new SelectionElement();
	private static MathObjectDynamicPresenter presenter;
	
	private EventBus eventBus = AniMathsPresenter.eventBus;	

	private MathObjectElementPresenter<?> selectedElement = null;
	private MathObjectStaticPresenter copiedPresenter = new MathObjectStaticPresenter();
	private boolean processFound = false;

	private Map<Type<?>,HandlerRegistration> hr = new HashMap<Type<?>,HandlerRegistration>();

	private SelectionElement() { }
	
	public static void setPresenterAndRun(MathObjectDynamicPresenter presenter) {
		SelectionElement.presenter = presenter;
		instance.setHandler(FlyOverEvent.getType());
	}

	@Override
	public void onFlyOver(FlyOverEvent event) {		
		MathObjectElementPresenter<?> element = event.getElement();
		if(element!=null)
			element = element.getMathObjectSelectableElement();
		if(selectedElement==element)
			return;
		if(selectedElement!=null)
			selectedElement.setStyleClass(MathObjectElementPresenter.STYLE_CLASS_NONE);
		selectedElement = element;
		if(selectedElement==null)
			removeHandler(SelectionEvent.getType());
		else {
			selectedElement.setStyleClass(MathObjectElementPresenter.STYLE_CLASS_SELECTABLE);
			setHandler(SelectionEvent.getType());
		}
	}

	@Override
	public void onSelect(SelectionEvent event) {
		if(event.getStyleClass()==MathObjectElementPresenter.STYLE_CLASS_SELECTABLE) {
			removeHandler(FlyOverEvent.getType());
			removeHandler(SelectionEvent.getType());
			selectedElement.setStyleClass(MathObjectElementPresenter.STYLE_CLASS_SELECTED);
			setHandler(SelectionChangeEvent.getType());
			setHandler(GrabEvent.getType());
		}
	}

	@Override
	public void onSelectionChange(SelectionChangeEvent event) {
		selectedElement.setStyleClass(MathObjectElementPresenter.STYLE_CLASS_NONE);
		switch(event.getDirection()) {
		case SelectionChangeEvent.CHANGE_TO_PARENT:
			selectedElement = selectedElement.getMathObjectFirstSelectableParent();
			selectedElement.setStyleClass(MathObjectElementPresenter.STYLE_CLASS_SELECTED);
			break;
		case SelectionChangeEvent.CHANGE_TO_FIRST_CHILD:
			selectedElement = selectedElement.getMathObjectFirstSelectableChild();
			selectedElement.setStyleClass(MathObjectElementPresenter.STYLE_CLASS_SELECTED);
			break;
		case SelectionChangeEvent.CHANGE_TO_PREVIOUS_SIBLING:
			selectedElement = selectedElement.getMathObjectParent().getMathObjectPreviousSelectableChild(selectedElement);
			selectedElement.setStyleClass(MathObjectElementPresenter.STYLE_CLASS_SELECTED);
			break;
		case SelectionChangeEvent.CHANGE_TO_NEXT_SIBLING:
			selectedElement = selectedElement.getMathObjectParent().getMathObjectNextSelectableChild(selectedElement);
			selectedElement.setStyleClass(MathObjectElementPresenter.STYLE_CLASS_SELECTED);
			break;
		case SelectionChangeEvent.UNSELECT:
			removeHandler(SelectionChangeEvent.getType());
			removeHandler(GrabEvent.getType());
			selectedElement.setStyleClass(MathObjectElementPresenter.STYLE_CLASS_SELECTABLE);
			setHandler(FlyOverEvent.getType());
			setHandler(SelectionEvent.getType());
			break;
		default:
			selectedElement.setStyleClass(MathObjectElementPresenter.STYLE_CLASS_SELECTED);
		}
	}

	@Override
	public void onGrab(GrabEvent event) {
		if(event.getStyleClass()==MathObjectElementPresenter.STYLE_CLASS_SELECTED) {
			processFound = false;
			eventBus.fireEvent(new GrabSelectedEvent(this));
			if(processFound) {
				removeHandler(SelectionChangeEvent.getType());
				removeHandler(GrabEvent.getType());
				initCopiedPresenter(selectedElement.clone());
				selectedElement.setStyleClass(MathObjectElementPresenter.STYLE_CLASS_DRAGGED);
				setHandler(DragEvent.getType());
				setHandler(DropEvent.getType());
			}
			else {
				removeHandler(SelectionChangeEvent.getType());
				removeHandler(GrabEvent.getType());
				selectedElement.setStyleClass(MathObjectElementPresenter.STYLE_CLASS_NONE);
				setHandler(FlyOverEvent.getType());
				setHandler(SelectionEvent.getType());
			}
		}
	}

	@Override
	public void onDrag(DragEvent event) {
		moveCopiedPresenter(event.getClientX(), event.getClientY());
		MathObjectElementPresenter<?> whereElement = event.getElement();
		if(whereElement==null)
			whereElement = presenter.getElement();
		else
			whereElement = whereElement.getMathObjectSelectableElement();
		processFound = false;
		while(true) {
			eventBus.fireEvent(new DragSelectedEvent(whereElement,whereElement.getZone(event.getClientX(),event.getClientY())));			
			if(processFound || whereElement instanceof MathObjectEquationPresenter)
				break;
			whereElement = whereElement.getMathObjectParent();
		}
		if(processFound) {
			whereElement.setStyleClass(MathObjectElementPresenter.STYLE_CLASS_OK_DROP);
			copiedPresenter.getElement().setStyleClass(MathObjectElementPresenter.STYLE_CLASS_OK);
		}
		else {
			whereElement.setStyleClass(MathObjectElementPresenter.STYLE_CLASS_NO_DROP);
			copiedPresenter.getElement().setStyleClass(MathObjectElementPresenter.STYLE_CLASS_NO);
		}
	}

	@Override
	public void onDrop(DropEvent event) {
		eventBus.fireEvent(new DropSelectedEvent());
		removeHandler(DragEvent.getType());
		removeHandler(DropEvent.getType());
		selectedElement.setStyleClass(MathObjectElementPresenter.STYLE_CLASS_NONE);
		setHandler(SelectionEvent.getType());
		setHandler(FlyOverEvent.getType());
		clearCopiedPresenter();
	}
	
	@SuppressWarnings("unchecked")
	private <T extends EventHandler> void setHandler(Type<T> type) {
		if(hr.get(type)==null)
			hr.put(type, eventBus.addHandler(type,(T) this));
	}
	
	private <T extends EventHandler> void removeHandler(Type<T> type) {
		if(hr.get(type)!=null) {
			hr.get(type).removeHandler();
			hr.put(type,null);
		}
	}
	
	public MathObjectDynamicPresenter getPresenter() {
		return presenter;
	}

	public MathObjectElementPresenter<?> getSelectedElement() {
		return selectedElement;
	}
	
	public MathObjectStaticPresenter getCopiedPresenter() {
		return copiedPresenter;
	}
	
	public void initCopiedPresenter(MathObjectElementPresenter<?> element) {
		clearCopiedPresenter();
		copiedPresenter.setElement(selectedElement.clone());
		RootPanel.get("drag").add(copiedPresenter.getDisplay().asWidget());
		RootPanel.get("drag").getElement().setAttribute("style","visibility:hidden;");
	}
	
	public void clearCopiedPresenter() {
		copiedPresenter = new MathObjectStaticPresenter();
		RootPanel.get("drag").getElement().setAttribute("style","left:0;top:0;");
		RootPanel.get("drag").clear();
		if(RootPanel.get("drag").getElement().hasChildNodes())
			RootPanel.get("drag").getElement().getFirstChild().removeFromParent();
	}
	
	public void moveCopiedPresenter(int x, int y) {
		RootPanel.get("drag").getElement().setAttribute("style","left:"+(x+5)+";top:"+(y+10)+";");
	}
	
	public boolean getProcessFound() {
		return processFound;
	}

	public void setProcessFound(boolean processFound) {
		this.processFound = processFound;
	}

}
