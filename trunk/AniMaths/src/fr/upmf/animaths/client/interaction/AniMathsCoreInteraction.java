package fr.upmf.animaths.client.interaction;

import java.util.HashMap;
import java.util.Map;

import net.customware.gwt.presenter.client.EventBus;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

import fr.upmf.animaths.client.events.DragEvent;
import fr.upmf.animaths.client.events.DragHandler;
import fr.upmf.animaths.client.events.DragSelectedEvent;
import fr.upmf.animaths.client.events.DropEvent;
import fr.upmf.animaths.client.events.DropHandler;
import fr.upmf.animaths.client.events.DropSelectedEvent;
import fr.upmf.animaths.client.events.FlyOverEvent;
import fr.upmf.animaths.client.events.FlyOverHandler;
import fr.upmf.animaths.client.events.GrabEvent;
import fr.upmf.animaths.client.events.GrabHandler;
import fr.upmf.animaths.client.events.GrabSelectedEvent;
import fr.upmf.animaths.client.events.NewLineEvent;
import fr.upmf.animaths.client.events.ProcessDoneEvent;
import fr.upmf.animaths.client.events.ProcessDoneHandler;
import fr.upmf.animaths.client.events.ProcessInterestedEvent;
import fr.upmf.animaths.client.events.ProcessInterestedHandler;
import fr.upmf.animaths.client.events.ProcessLaunchEvent;
import fr.upmf.animaths.client.events.SelectionChangeEvent;
import fr.upmf.animaths.client.events.SelectionChangeHandler;
import fr.upmf.animaths.client.events.SelectionEvent;
import fr.upmf.animaths.client.events.SelectionHandler;
import fr.upmf.animaths.client.events.TagDeclarationEvent;
import fr.upmf.animaths.client.events.TagDeclarationHandler;
import fr.upmf.animaths.client.mvp.AniMathsPresenter;
import fr.upmf.animaths.client.mvp.MODragPresenter;
import fr.upmf.animaths.client.mvp.MODynamicPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MOElement;
import fr.upmf.animaths.client.mvp.MathObject.MOEquation;

public class AniMathsCoreInteraction implements FlyOverHandler, SelectionHandler, SelectionChangeHandler, GrabHandler, DragHandler, DropHandler,
											ProcessInterestedHandler, TagDeclarationHandler, ProcessDoneHandler {
	
	private static AniMathsCoreInteraction instance = new AniMathsCoreInteraction();
	private static MODynamicPresenter presenter;
	private static final MODragPresenter dragPresenter = new MODragPresenter();
	
	private static EventBus eventBus = AniMathsPresenter.eventBus;	
	
	private static MOEquation equation;
	private static boolean interactWithEquation = false;
	private static boolean interactWithLeftHand = false;

	private MOElement<?> selectedElement = null;
	MOElement<?> whereElement;
	private short tag = -1;
	private AniMathsAbstractProcess process;
	//	private boolean processDone = false;
	private Map<Type<?>,HandlerRegistration> hr = new HashMap<Type<?>,HandlerRegistration>();

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
	
	private AniMathsCoreInteraction() { }
	
	public static void setPresenterAndRun(MODynamicPresenter presenter, int level) {
		AniMathsCoreInteraction.presenter = presenter;
		equation = (presenter.getElement() instanceof MOEquation)?(MOEquation) presenter.getElement():null;

		SEs_AC_Commutation.setEnabled(false);
		SEs_SEs_ChangeSign.setEnabled(false);
		MEs_MC_Commutation.setEnabled(false);
		SEs_AC_E_ChangeHandSide.setEnabled(false);
		MEs_MC_E_ChangeHandSide.setEnabled(false);
		SEs_N_Add.setEnabled(false);
		MEs_N_Multiply.setEnabled(false);
		Ns_Is_E_ChangeHandSide.setEnabled(false);

		AniMathsCoreInteraction.interactWithEquation = false;
		AniMathsCoreInteraction.interactWithLeftHand = false;
		
		if(level>=1) {
			instance.setHandler(FlyOverEvent.getType());
			if(level>=2) {
				SEs_AC_Commutation.setEnabled(true);
				SEs_N_Add.setEnabled(true);
				if(level>=3) {
					if(level>=4) {
						SEs_SEs_ChangeSign.setEnabled(true);
						if(level>=5) {
							MEs_MC_Commutation.setEnabled(true);
							if(level>=6) {
								MEs_N_Multiply.setEnabled(true);
								if(level>=7) {
									AniMathsCoreInteraction.interactWithLeftHand = true;
									SEs_AC_E_ChangeHandSide.setEnabled(true);
									MEs_MC_E_ChangeHandSide.setEnabled(true);
									Ns_Is_E_ChangeHandSide.setEnabled(true);
								}
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void onFlyOver(FlyOverEvent event) {		
		MOElement<?> element = event.getElement();
		if(!interactWithEquation && element instanceof MOEquation)
			return;
		if(!interactWithLeftHand && element!=null && equation!=null && element.hasMathObjectAncestor(equation.getLeftHandSide())) {
			System.out.println("okancestor");
			return;
		}
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
			setHandler(ProcessInterestedEvent.getType());
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
			System.out.println("MOCoreInteraction : Grab");
			removeHandler(SelectionChangeEvent.getType());
			removeHandler(GrabEvent.getType());
			setHandler(FlyOverEvent.getType());
			setHandler(SelectionEvent.getType());
			selectedElement.setStyleClass(MOElement.STYLE_CLASS_NONE);
			eventBus.fireEvent(new GrabSelectedEvent(presenter, selectedElement));
		}
	}

	@Override
	public void onProcessInterested(ProcessInterestedEvent event) {
		System.out.println("MOCoreInteraction : ProcessInterested");
		removeHandler(ProcessInterestedEvent.getType());
		removeHandler(FlyOverEvent.getType());
		removeHandler(SelectionEvent.getType());
		dragPresenter.init(selectedElement.clone());
		selectedElement.setStyleClass(MOElement.STYLE_CLASS_DRAGGED);
        setHandler(DragEvent.getType());
        setHandler(DropEvent.getType());
        setHandler(TagDeclarationEvent.getType());
		setHandler(ProcessDoneEvent.getType());
	}

	@Override
	public void onDrag(DragEvent event) {
		dragPresenter.move(event.getClientX()+Window.getScrollLeft(), event.getClientY()+Window.getScrollTop());
		whereElement = event.getElement();
		if(whereElement==null)
			whereElement = presenter.getElement();
		if(whereElement==selectedElement) {
			dragPresenter.getElement().setStyleClass(MOElement.STYLE_CLASS_OK);
			return;
		}
		tag = - 1;
		boolean firstLevel = true;
		if(process!=null) {
			process.removeHandler(ProcessLaunchEvent.getType());
			process = null;
		}
		while(true) {
			eventBus.fireEvent(new DragSelectedEvent(whereElement,whereElement.getZoneH(event.getClientX()),whereElement.getZoneV(event.getClientY()), firstLevel));
			if(whereElement instanceof MOEquation)
				break;
			whereElement = whereElement.getMathObjectParent();
			firstLevel = false;
		}
		selectedElement.setStyleClass(MOElement.STYLE_CLASS_DRAGGED);
	}

	@Override
	public void onTagDeclaration(TagDeclarationEvent event) {
		if(event.getTag()>tag) {
			tag = event.getTag();
			if(tag==AniMathsAbstractProcess.PROCESS_NO) {
				whereElement.setStyleClass(MOElement.STYLE_CLASS_NO_DROP);
				dragPresenter.getElement().setStyleClass(MOElement.STYLE_CLASS_NO);
			}
			else {
				if(process!=null)
					process.removeHandler(ProcessLaunchEvent.getType());
				process = event.getProcess();
				process.setHandler(ProcessLaunchEvent.getType());
				switch(tag) {
				case AniMathsAbstractProcess.PROCESS_CAUTION:
					System.out.println("MOCoreInteraction : PROCESS_CAUTION");
					whereElement.setStyleClass(MOElement.STYLE_CLASS_OK_DROP);
					dragPresenter.getElement().setStyleClass(MOElement.STYLE_CLASS_CAUTION);
					break;
				case AniMathsAbstractProcess.PROCESS_OK:
					System.out.println("MOCoreInteraction : PROCESS_OK");
					whereElement.setStyleClass(MOElement.STYLE_CLASS_OK_DROP);
					dragPresenter.getElement().setStyleClass(MOElement.STYLE_CLASS_OK);
					break;
				}
			}
		}
	}

	@Override
	public void onDrop(DropEvent event) {
		System.out.println("MOCoreInteraction : Drop");
		removeHandler(DragEvent.getType());
		removeHandler(DropEvent.getType());
        removeHandler(TagDeclarationEvent.getType());
		eventBus.fireEvent(new DropSelectedEvent());
		eventBus.fireEvent(new ProcessLaunchEvent());
		dragPresenter.unbind();
		selectedElement.setStyleClass(MOElement.STYLE_CLASS_NONE);
		selectedElement = null;
		setHandler(SelectionEvent.getType());
		setHandler(FlyOverEvent.getType());
		if(process!=null) {
			process.removeHandler(ProcessLaunchEvent.getType());
			process = null;
		}
	}

	@Override
	public void onProcessDone(ProcessDoneEvent event) {
		System.out.println("MOCoreInteraction : ProcessDone");
		eventBus.fireEvent(new NewLineEvent());
		Element view = RootPanel.get("view").getElement();
		view.setScrollTop(view.getScrollHeight());
	}

}
