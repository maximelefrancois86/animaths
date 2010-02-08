package fr.upmf.animaths.client.interaction;

import net.customware.gwt.presenter.client.EventBus;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.RootPanel;

import fr.upmf.animaths.client.interaction.events.dragndrop.DragEvent;
import fr.upmf.animaths.client.interaction.events.dragndrop.DragHandler;
import fr.upmf.animaths.client.interaction.events.dragndrop.DropEvent;
import fr.upmf.animaths.client.interaction.events.dragndrop.DropHandler;
import fr.upmf.animaths.client.interaction.events.dragndrop.GrabEvent;
import fr.upmf.animaths.client.interaction.events.dragndrop.GrabHandler;
import fr.upmf.animaths.client.interaction.events.process.DragOverEvent;
import fr.upmf.animaths.client.interaction.events.process.DropOverEvent;
import fr.upmf.animaths.client.interaction.events.selection.FlyOverEvent;
import fr.upmf.animaths.client.interaction.events.selection.FlyOverHandler;
import fr.upmf.animaths.client.interaction.events.selection.SelectionChangeEvent;
import fr.upmf.animaths.client.interaction.events.selection.SelectionChangeHandler;
import fr.upmf.animaths.client.interaction.events.selection.SelectionEvent;
import fr.upmf.animaths.client.interaction.events.selection.SelectionHandler;
import fr.upmf.animaths.client.mvp.AniMathsPresenter;
import fr.upmf.animaths.client.mvp.MathObjectDynamicPresenter;
import fr.upmf.animaths.client.mvp.MathObjectStaticPresenter;
import fr.upmf.animaths.client.mvp.MathML.MathMLOperator;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectElementPresenter;

public class SelectionElement implements FlyOverHandler, SelectionHandler, SelectionChangeHandler, GrabHandler, DragHandler, DropHandler {
	
	private static SelectionElement instance;
	
	private EventBus eventBus = AniMathsPresenter.eventBus;	

	private MathObjectDynamicPresenter presenter;
	private MathObjectElementPresenter<?> element = null;
	private MathObjectStaticPresenter copy = new MathObjectStaticPresenter();

	private HandlerRegistration hrFlyOver;
	private HandlerRegistration hrSelection;
	private HandlerRegistration hrSelectionChange;
	private HandlerRegistration hrGrab;
	private HandlerRegistration hrDrag;
	private HandlerRegistration hrDrop;

	private SelectionElement() { }
	
	public static SelectionElement getInstance(MathObjectDynamicPresenter presenter) {
		if(instance==null)
			instance = new SelectionElement();
		instance.presenter = presenter;
		return instance;
	}

	public void setEnabled(boolean enabled) {
		hrFlyOver = eventBus.addHandler(FlyOverEvent.getType(), this);
	}

	@Override
	public void onFlyOver(FlyOverEvent event) {
		MathObjectElementPresenter<?> element = event.getElement();
		element = element.getMathObjectSelectableElement();
		if(this.element==element)
			return;
		if(this.element!=null)
			this.element.setStyleClass(MathObjectElementPresenter.STYLE_CLASS_NONE);
		this.element = element;
		if(this.element==null) {
			if(hrSelection!=null) {
				hrSelection.removeHandler();
				hrSelection = null;
			}
		}
		else {
			this.element.setStyleClass(MathObjectElementPresenter.STYLE_CLASS_SELECTABLE);
			if(hrSelection==null)
				hrSelection = eventBus.addHandler(SelectionEvent.getType(), this);
		}
	}

	@Override
	public void onSelect(SelectionEvent event) {
		if(event.getState()!=-1) {
			if(hrFlyOver!=null) {
				hrFlyOver.removeHandler();
				hrFlyOver = null;
			}
			if(hrSelection!=null) {
				hrSelection.removeHandler();
				hrSelection = null;
			}
			element.setStyleClass(MathObjectElementPresenter.STYLE_CLASS_SELECTED);
			if(hrSelectionChange==null)
				hrSelectionChange = eventBus.addHandler(SelectionChangeEvent.getType(),this);
			if(hrGrab==null)
				hrGrab = eventBus.addHandler(GrabEvent.getType(),this);
		}
	}

	@Override
	public void onSelectionChange(SelectionChangeEvent event) {
		element.setStyleClass(MathObjectElementPresenter.STYLE_CLASS_NONE);
		switch(event.getDirection()) {
		case SelectionChangeEvent.CHANGE_TO_PARENT:
			element = element.getMathObjectFirstSelectableParent();
			element.setStyleClass(MathObjectElementPresenter.STYLE_CLASS_SELECTED);
			break;
		case SelectionChangeEvent.CHANGE_TO_FIRST_CHILD:
			element = element.getMathObjectFirstSelectableChild();
			element.setStyleClass(MathObjectElementPresenter.STYLE_CLASS_SELECTED);
			break;
		case SelectionChangeEvent.CHANGE_TO_PREVIOUS_SIBLING:
			element = element.getMathObjectParent().getMathObjectPreviousSelectableChild(element);
			element.setStyleClass(MathObjectElementPresenter.STYLE_CLASS_SELECTED);
			break;
		case SelectionChangeEvent.CHANGE_TO_NEXT_SIBLING:
			element = element.getMathObjectParent().getMathObjectNextSelectableChild(element);
			element.setStyleClass(MathObjectElementPresenter.STYLE_CLASS_SELECTED);
			break;
		case SelectionChangeEvent.UNSELECT:
			if(hrSelectionChange!=null) {
				hrSelectionChange.removeHandler();
				hrSelectionChange = null;
			}			
			if(hrGrab!=null) {
				hrGrab.removeHandler();
				hrGrab = null;
			}
			element.setStyleClass(MathObjectElementPresenter.STYLE_CLASS_SELECTABLE);
			if(hrFlyOver==null)
				hrFlyOver = eventBus.addHandler(FlyOverEvent.getType(),this);
			if(hrSelection==null)
				hrSelection = eventBus.addHandler(SelectionEvent.getType(),this);
			break;
		default:
			element.setStyleClass(MathObjectElementPresenter.STYLE_CLASS_SELECTED);
		}
	}

	@Override
	public void onGrab(GrabEvent event) {
		if(event.getElement().getStyleClass()==MathObjectElementPresenter.STYLE_CLASS_SELECTED) {
			if(hrSelectionChange!=null) {
				hrSelectionChange.removeHandler();
				hrSelectionChange = null;
			}
			if(hrGrab!=null) {
				hrGrab.removeHandler();
				hrGrab = null;
			}
			copy.setElement(element.clone());
			RootPanel.get("drag").add(copy.getDisplay().asWidget());
			RootPanel.get("drag").getElement().setAttribute("style","visibility:hidden;");
			element.setStyleClass(MathObjectElementPresenter.STYLE_CLASS_DRAGGED);
			if(hrDrag==null)
				hrDrag = eventBus.addHandler(DragEvent.getType(),this);
			if(hrDrop==null)
				hrDrop = eventBus.addHandler(DropEvent.getType(),this);
		}
	}

	@Override
	public void onDrag(DragEvent event) {
		RootPanel.get("drag").getElement().setAttribute("style","left:"+(event.getClientX()+5)+";top:"+(event.getClientY()+10)+";");
		MathObjectElementPresenter<?> element = event.getElement();
		if(element==null)
			element = presenter.getElement();
		eventBus.fireEvent(new DragOverEvent(this.element, element.getMathObjectSelectableElement(), copy, event));
	}

	@Override
	public void onDrop(DropEvent event) {
		eventBus.fireEvent(new DropOverEvent());
		finalizeDrop();
	}
	
	public void finalizeDrop() {
		MathMLOperator.getLocalisation(null).getElement().removeFromParent();
		if(hrDrag!=null) {
			hrDrag.removeHandler();
			hrDrag = null;
		}
		if(hrDrop!=null) {
			hrDrop.removeHandler();
			hrDrop = null;
		}
		element.setStyleClass(MathObjectElementPresenter.STYLE_CLASS_NONE);
		if(hrSelection==null)
			hrSelection = eventBus.addHandler(SelectionEvent.getType(), this);
		if(hrFlyOver==null)
			hrFlyOver = eventBus.addHandler(FlyOverEvent.getType(),this);
		copy = new MathObjectStaticPresenter();
		RootPanel.get("drag").getElement().setAttribute("style","left:0;top:0;");
		RootPanel.get("drag").clear();
		if(RootPanel.get("drag").getElement().hasChildNodes())
			RootPanel.get("drag").getElement().getFirstChild().removeFromParent();
	}
}
