package fr.upmf.animaths.client.interaction;

import java.util.HashMap;
import java.util.Map;

import net.customware.gwt.presenter.client.EventBus;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.GwtEvent.Type;

import fr.upmf.animaths.client.interaction.events.DragEvent;
import fr.upmf.animaths.client.interaction.events.DragHandler;
import fr.upmf.animaths.client.interaction.events.DropEvent;
import fr.upmf.animaths.client.interaction.events.DropHandler;
import fr.upmf.animaths.client.interaction.events.FlyOverEvent;
import fr.upmf.animaths.client.interaction.events.FlyOverHandler;
import fr.upmf.animaths.client.interaction.events.GrabEvent;
import fr.upmf.animaths.client.interaction.events.GrabHandler;
import fr.upmf.animaths.client.interaction.events.QuestionEvent;
import fr.upmf.animaths.client.interaction.events.QuestionHandler;
import fr.upmf.animaths.client.interaction.events.SelectionChangeEvent;
import fr.upmf.animaths.client.interaction.events.SelectionChangeHandler;
import fr.upmf.animaths.client.interaction.events.SelectionEvent;
import fr.upmf.animaths.client.interaction.events.SelectionHandler;
import fr.upmf.animaths.client.interaction.process.MOAbstractProcess;
import fr.upmf.animaths.client.interaction.process.core.MEs_MC_Commutation;
import fr.upmf.animaths.client.interaction.process.core.SEs_AC_Commutation;
import fr.upmf.animaths.client.interaction.process.core.SEs_AC_E_ChangeHandSide;
import fr.upmf.animaths.client.interaction.process.core.SEs_SEs_ChangeSign;
import fr.upmf.animaths.client.interaction.process.event.DragSelectedEvent;
import fr.upmf.animaths.client.interaction.process.event.DropSelectedEvent;
import fr.upmf.animaths.client.interaction.process.event.GrabSelectedEvent;
import fr.upmf.animaths.client.mvp.AniMathsPresenter;
import fr.upmf.animaths.client.mvp.MODragPresenter;
import fr.upmf.animaths.client.mvp.MODynamicPresenter;
import fr.upmf.animaths.client.mvp.MOBasicPresenter;
import fr.upmf.animaths.client.mvp.MathObject.IMOHasStyleClass;
import fr.upmf.animaths.client.mvp.MathObject.MOAddContainer;
import fr.upmf.animaths.client.mvp.MathObject.MOElement;
import fr.upmf.animaths.client.mvp.MathObject.MOEquation;
import fr.upmf.animaths.client.mvp.MathObject.MOMultiplyContainer;
import fr.upmf.animaths.client.mvp.MathObject.MOMultiplyElement;
import fr.upmf.animaths.client.mvp.MathObject.MOSignedElement;

public class MOCoreInteraction implements FlyOverHandler, SelectionHandler, SelectionChangeHandler, GrabHandler, DragHandler, DropHandler, QuestionHandler {
	
	private static MOCoreInteraction instance = new MOCoreInteraction();
	private static MODynamicPresenter presenter;
	
	private EventBus eventBus = AniMathsPresenter.eventBus;	

	private MOElement<?> selectedElement = null;
	private MODragPresenter dragPresenter = new MODragPresenter();
	private boolean processFound = false;
	private int greatestPriorityFound = 0;
	private short processTag = MOAbstractProcess.PROCESS_NO;
	private boolean processDone = false;
	private Map<Type<?>,HandlerRegistration> hr = new HashMap<Type<?>,HandlerRegistration>();
	private static QuestionPresenter question = new QuestionPresenter();

	private MOCoreInteraction() { }
	
	public static void setPresenterAndRun(MODynamicPresenter presenter) {
		MOCoreInteraction.presenter = presenter;
		instance.setHandler(FlyOverEvent.getType());
		instance.setHandler(QuestionEvent.getType());
		SEs_AC_Commutation.setEnabled();
		MEs_MC_Commutation.setEnabled();
		SEs_SEs_ChangeSign.setEnabled();
		SEs_AC_E_ChangeHandSide.setEnabled();
//		MEs_MC_E_ChangeHandSide.setEnabled();
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
				dragPresenter.init(selectedElement);
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
		dragPresenter.move(event.getClientX(), event.getClientY());
		MOElement<?> whereElement = event.getElement();
		if(whereElement==null)
			whereElement = presenter.getElement();
		if(whereElement==selectedElement) {
			whereElement.setStyleClass(MOElement.STYLE_CLASS_DRAGGED);
			dragPresenter.getElement().setStyleClass(MOElement.STYLE_CLASS_OK);
			return;
		}
		greatestPriorityFound = 0;
		processTag = MOAbstractProcess.PROCESS_NO;
		boolean firstLevel = true;
		while(true) {
			eventBus.fireEvent(new DragSelectedEvent(whereElement,whereElement.getZoneH(event.getClientX()),whereElement.getZoneV(event.getClientY()), firstLevel));
			if(whereElement instanceof MOEquation)
				break;
			whereElement = whereElement.getMathObjectParent();
			firstLevel = false;
		}
		switch(processTag) {
		case MOAbstractProcess.PROCESS_NO:
			whereElement.setStyleClass(MOElement.STYLE_CLASS_NO_DROP);
			dragPresenter.getElement().setStyleClass(MOElement.STYLE_CLASS_NO);
			break;
		case MOAbstractProcess.PROCESS_CAUTION:
			whereElement.setStyleClass(MOElement.STYLE_CLASS_OK_DROP);
			dragPresenter.getElement().setStyleClass(MOElement.STYLE_CLASS_CAUTION);
			break;
		case MOAbstractProcess.PROCESS_OK:
			whereElement.setStyleClass(MOElement.STYLE_CLASS_OK_DROP);
			dragPresenter.getElement().setStyleClass(MOElement.STYLE_CLASS_OK);
			break;
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
		dragPresenter.unbind();
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
	
	public int getGreatestPriorityFound() {
		return greatestPriorityFound;
	}

	public void setPriorityAndTag(int priority, short tag) {
		if(priority>greatestPriorityFound) {
			greatestPriorityFound = priority;
			processTag = tag;
		}
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

	public MOBasicPresenter getDragPresenter() {
		return dragPresenter;
	}
	
	public void changeSign() {
		MOElement<?> element = dragPresenter.getElement();
		if(element instanceof MOSignedElement)
			((MOSignedElement) element).setMinus(!((MOSignedElement) element).isMinus());
		else if(element instanceof MOAddContainer)
			((MOAddContainer)element).changeSign();
		else
			element = new MOSignedElement(element,true);
		dragPresenter.init(element);
		if(element instanceof MOSignedElement)
			((MOSignedElement) element).getDisplay().getSign().setStyleClass(IMOHasStyleClass.STYLE_CLASS_FOCUS);
		else if (element instanceof MOAddContainer)
			for(int i=0;i<((MOAddContainer)element).size();i++)
				((MOAddContainer)element).get(i).getDisplay().getSign().setStyleClass(IMOHasStyleClass.STYLE_CLASS_FOCUS);
	}

	public void inverseSign() {
		MOElement<?> element = dragPresenter.getElement();
		if(element instanceof MOMultiplyElement) {
			((MOMultiplyElement) element).setDivided(!((MOMultiplyElement) element).isDivided());
			element = new MOMultiplyContainer((MOMultiplyElement) element);
		}
		else if(element instanceof MOMultiplyContainer)
			((MOMultiplyContainer)element).inverseSign();
		else
			element = new MOMultiplyElement(element,true);
		dragPresenter.init(element);
	}

	@Override
	public void onQuestion(QuestionEvent event) {
		question.getDisplay().initializeAndCenter();
	}

}
