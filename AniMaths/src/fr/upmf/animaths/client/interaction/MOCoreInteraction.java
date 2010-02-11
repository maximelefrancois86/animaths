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
import fr.upmf.animaths.client.mvp.MODynamicPresenter;
import fr.upmf.animaths.client.mvp.MOStaticPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MOElement;
import fr.upmf.animaths.client.mvp.MathObject.MOEquation;

public class MOCoreInteraction implements FlyOverHandler, SelectionHandler, SelectionChangeHandler, GrabHandler, DragHandler, DropHandler {
	
	private static MOCoreInteraction instance = new MOCoreInteraction();
	private static MODynamicPresenter presenter;
	
	private EventBus eventBus = AniMathsPresenter.eventBus;	

	private MOElement<?> selectedElement = null;
	private MOStaticPresenter copiedPresenter = new MOStaticPresenter();
	private boolean processFound = false;
	private int greatestPriorityFound = 0;
	private boolean processDone = false;

	private Map<Type<?>,HandlerRegistration> hr = new HashMap<Type<?>,HandlerRegistration>();

	private MOCoreInteraction() { }
	
	public static void setPresenterAndRun(MODynamicPresenter presenter) {
		MOCoreInteraction.presenter = presenter;
		instance.setHandler(FlyOverEvent.getType());
	}

	@Override
	public void onFlyOver(FlyOverEvent event) {		
		MOElement<?> element = event.getElement();
		if(element!=null)
			element = element.getMathObjectSelectableElement();
		if(selectedElement==element)
			return;
		if(selectedElement!=null)
			selectedElement.setStyleClass(MOElement.STYLE_CLASS_NONE);
		selectedElement = element;
		if(selectedElement==null)
			removeHandler(SelectionEvent.getType());
		else {
			selectedElement.setStyleClass(MOElement.STYLE_CLASS_SELECTABLE);
			setHandler(SelectionEvent.getType());
		}
	}

	@Override
	public void onSelect(SelectionEvent event) {
		if(event.getStyleClass()==MOElement.STYLE_CLASS_SELECTABLE) {
			removeHandler(FlyOverEvent.getType());
			removeHandler(SelectionEvent.getType());
			selectedElement.setStyleClass(MOElement.STYLE_CLASS_SELECTED);
			setHandler(SelectionChangeEvent.getType());
			setHandler(GrabEvent.getType());
		}
	}

	@Override
	public void onSelectionChange(SelectionChangeEvent event) {
		selectedElement.setStyleClass(MOElement.STYLE_CLASS_NONE);
		switch(event.getDirection()) {
		case SelectionChangeEvent.CHANGE_TO_PARENT:
			selectedElement = selectedElement.getMathObjectFirstSelectableParent();
			selectedElement.setStyleClass(MOElement.STYLE_CLASS_SELECTED);
			break;
		case SelectionChangeEvent.CHANGE_TO_FIRST_CHILD:
			selectedElement = selectedElement.getMathObjectFirstSelectableChild();
			selectedElement.setStyleClass(MOElement.STYLE_CLASS_SELECTED);
			break;
		case SelectionChangeEvent.CHANGE_TO_PREVIOUS_SIBLING:
			selectedElement = selectedElement.getMathObjectParent().getMathObjectPreviousSelectableChild(selectedElement);
			selectedElement.setStyleClass(MOElement.STYLE_CLASS_SELECTED);
			break;
		case SelectionChangeEvent.CHANGE_TO_NEXT_SIBLING:
			selectedElement = selectedElement.getMathObjectParent().getMathObjectNextSelectableChild(selectedElement);
			selectedElement.setStyleClass(MOElement.STYLE_CLASS_SELECTED);
			break;
		case SelectionChangeEvent.UNSELECT:
			removeHandler(SelectionChangeEvent.getType());
			removeHandler(GrabEvent.getType());
			selectedElement.setStyleClass(MOElement.STYLE_CLASS_SELECTABLE);
			setHandler(FlyOverEvent.getType());
			setHandler(SelectionEvent.getType());
			break;
		default:
			selectedElement.setStyleClass(MOElement.STYLE_CLASS_SELECTED);
		}
	}

	@Override
	public void onGrab(GrabEvent event) {
		if(event.getStyleClass()==MOElement.STYLE_CLASS_SELECTED) {
			processFound = false;
			eventBus.fireEvent(new GrabSelectedEvent(this));
			if(processFound) {
				removeHandler(SelectionChangeEvent.getType());
				removeHandler(GrabEvent.getType());
				initCopiedPresenter(selectedElement.clone());
				selectedElement.setStyleClass(MOElement.STYLE_CLASS_DRAGGED);
				setHandler(DragEvent.getType());
				setHandler(DropEvent.getType());
			}
			else {
				removeHandler(SelectionChangeEvent.getType());
				removeHandler(GrabEvent.getType());
				selectedElement.setStyleClass(MOElement.STYLE_CLASS_NONE);
				setHandler(FlyOverEvent.getType());
				setHandler(SelectionEvent.getType());
			}
		}
	}

	@Override
	public void onDrag(DragEvent event) {
		moveCopiedPresenter(event.getClientX(), event.getClientY());
		MOElement<?> whereElement = event.getElement();
		if(whereElement==null)
			whereElement = presenter.getElement();
		if(whereElement==selectedElement) {
			whereElement.setStyleClass(MOElement.STYLE_CLASS_DRAGGED);
			copiedPresenter.getElement().setStyleClass(MOElement.STYLE_CLASS_OK);
			return;
		}
		greatestPriorityFound = 0;
		boolean firstLevel = true;
		while(true) {
			eventBus.fireEvent(new DragSelectedEvent(whereElement,whereElement.getZoneH(event.getClientX()),whereElement.getZoneV(event.getClientY()), firstLevel));
			if(whereElement instanceof MOEquation)
				break;
			whereElement = whereElement.getMathObjectParent();
			firstLevel = false;
		}
		if(greatestPriorityFound==0) {
			whereElement.setStyleClass(MOElement.STYLE_CLASS_NO_DROP);
			copiedPresenter.getElement().setStyleClass(MOElement.STYLE_CLASS_NO);
		}
		else {
			whereElement.setStyleClass(MOElement.STYLE_CLASS_OK_DROP);
			copiedPresenter.getElement().setStyleClass(MOElement.STYLE_CLASS_OK);
		}
	}

	@Override
	public void onDrop(DropEvent event) {
		processDone = false;
		eventBus.fireEvent(new DropSelectedEvent(greatestPriorityFound));
		removeHandler(DragEvent.getType());
		removeHandler(DropEvent.getType());
		selectedElement.setStyleClass(MOElement.STYLE_CLASS_NONE);
		selectedElement = null;
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
	
	public MODynamicPresenter getPresenter() {
		return presenter;
	}

	public MOElement<?> getSelectedElement() {
		return selectedElement;
	}
	
	public MOStaticPresenter getCopiedPresenter() {
		return copiedPresenter;
	}
	
	public void initCopiedPresenter(MOElement<?> element) {
		clearCopiedPresenter();
		copiedPresenter.setElement(selectedElement.clone());
		RootPanel.get("drag").add(copiedPresenter.getDisplay().asWidget());
		RootPanel.get("drag").getElement().setAttribute("style","visibility:hidden;");
	}
	
	public void clearCopiedPresenter() {
		copiedPresenter = new MOStaticPresenter();
		RootPanel.get("drag").getElement().setAttribute("style","left:0;top:0;");
		RootPanel.get("drag").clear();
		if(RootPanel.get("drag").getElement().hasChildNodes())
			RootPanel.get("drag").getElement().getFirstChild().removeFromParent();
	}
	
	public void moveCopiedPresenter(int x, int y) {
		RootPanel.get("drag").getElement().setAttribute("style","left:"+(x+5)+";top:"+(y+10)+";");
	}
	
	public int getGreatestPriorityFound() {
		return greatestPriorityFound;
	}

	public void setPriorityOfProcess(int priority) {
		greatestPriorityFound = Math.max(greatestPriorityFound,priority);
	}

	public void setProcessFound() {
		processFound = true;
	}

	public boolean isProcessDone() {
		return processDone;
	}

	public void setProcessDone() {
		processDone = true;
	}

}
