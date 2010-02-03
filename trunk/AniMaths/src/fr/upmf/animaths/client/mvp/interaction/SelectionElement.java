package fr.upmf.animaths.client.mvp.interaction;

import net.customware.gwt.presenter.client.EventBus;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.RootPanel;

import fr.upmf.animaths.client.mvp.AniMathsPresenter;
import fr.upmf.animaths.client.mvp.StaticMathObjectPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectElementPresenter;
import fr.upmf.animaths.client.mvp.interaction.events.dragndrop.DragEvent;
import fr.upmf.animaths.client.mvp.interaction.events.dragndrop.DragHandler;
import fr.upmf.animaths.client.mvp.interaction.events.dragndrop.DropEvent;
import fr.upmf.animaths.client.mvp.interaction.events.dragndrop.DropHandler;
import fr.upmf.animaths.client.mvp.interaction.events.dragndrop.GrabEvent;
import fr.upmf.animaths.client.mvp.interaction.events.dragndrop.GrabHandler;
import fr.upmf.animaths.client.mvp.interaction.events.selection.FlyOverEvent;
import fr.upmf.animaths.client.mvp.interaction.events.selection.FlyOverHandler;
import fr.upmf.animaths.client.mvp.interaction.events.selection.SelectionChangeEvent;
import fr.upmf.animaths.client.mvp.interaction.events.selection.SelectionChangeHandler;
import fr.upmf.animaths.client.mvp.interaction.events.selection.SelectionEvent;
import fr.upmf.animaths.client.mvp.interaction.events.selection.SelectionHandler;

public class SelectionElement implements FlyOverHandler, SelectionHandler, SelectionChangeHandler, GrabHandler, DragHandler, DropHandler {
	
	private static SelectionElement instance;
	private EventBus eventBus = AniMathsPresenter.eventBus;	
	private MathObjectElementPresenter<?> element = null;
	private StaticMathObjectPresenter copy = new StaticMathObjectPresenter();
	private HandlerRegistration hrFlyOver;
	private HandlerRegistration hrSelection;
	private HandlerRegistration hrSelectionChange;
	private HandlerRegistration hrGrab;
	private HandlerRegistration hrDrag;
	private HandlerRegistration hrDrop;

	private SelectionElement() { }
	
	public static SelectionElement getInstance() {
		if(instance==null)
			instance = new SelectionElement();
		return instance;
	}

	public void setEnabled(boolean enabled) {
		hrFlyOver = eventBus.addHandler(FlyOverEvent.getType(), this);
	}

	@Override
	public void onFlyOver(FlyOverEvent event) {
		MathObjectElementPresenter<?> element = event.getElement();
		if((element.getType()==MathObjectElementPresenter.MATH_OBJECT_NUMBER
				||element.getType()==MathObjectElementPresenter.MATH_OBJECT_IDENTIFIER)
				&& element.getMathObjectParent().getType()!=MathObjectElementPresenter.MATH_OBJECT_EQUATION)
			element = element.getMathObjectParent();
		if(this.element==element)
			return;
		if(this.element!=null)
			this.element.setState(MathObjectElementPresenter.STATE_NONE);
		this.element = element;
		if(this.element==null) {
			if(hrSelection!=null) {
				hrSelection.removeHandler();
				hrSelection = null;
			}
		}
		else {
			this.element.setState(MathObjectElementPresenter.STATE_SELECTABLE);
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
			element.setState(MathObjectElementPresenter.STATE_SELECTED);
			if(hrSelectionChange==null)
				hrSelectionChange = eventBus.addHandler(SelectionChangeEvent.getType(),this);
			if(hrGrab==null)
				hrGrab = eventBus.addHandler(GrabEvent.getType(),this);
		}
	}

	@Override
	public void onSelectionChange(SelectionChangeEvent event) {
		element.setState(MathObjectElementPresenter.STATE_NONE);
		switch(event.getDirection()) {
		case SelectionChangeEvent.CHANGE_TO_PARENT:
			element = element.getMathObjectParent();
			element.setState(MathObjectElementPresenter.STATE_SELECTED);
			break;
		case SelectionChangeEvent.CHANGE_TO_FIRST_CHILD:
			element = element.getMathObjectFirstChild();
			element.setState(MathObjectElementPresenter.STATE_SELECTED);
			break;
		case SelectionChangeEvent.CHANGE_TO_PREVIOUS_SIBLING:
			element = element.getMathObjectParent().getMathObjectPreviousChild(element);
			element.setState(MathObjectElementPresenter.STATE_SELECTED);
			break;
		case SelectionChangeEvent.CHANGE_TO_NEXT_SIBLING:
			element = element.getMathObjectParent().getMathObjectNextChild(element);
			element.setState(MathObjectElementPresenter.STATE_SELECTED);
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
			element.setState(MathObjectElementPresenter.STATE_SELECTABLE);
			if(hrFlyOver==null)
				hrFlyOver = eventBus.addHandler(FlyOverEvent.getType(),this);
			if(hrSelection==null)
				hrSelection = eventBus.addHandler(SelectionEvent.getType(),this);
			
			
			break;
		}
	}

	@Override
	public void onGrab(GrabEvent event) {
		if(event.getElement().getState()==MathObjectElementPresenter.STATE_SELECTED) {
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
			element.setState(MathObjectElementPresenter.STATE_DRAGGED);
			if(hrDrag==null)
				hrDrag = eventBus.addHandler(DragEvent.getType(),this);
			if(hrDrop==null)
				hrDrop = eventBus.addHandler(DropEvent.getType(),this);
		}
	}

	@Override
	public void onDrag(DragEvent event) {
		RootPanel.get("drag").getElement().setAttribute("style","left:"+(event.getClientX()+5)+";top:"+(event.getClientY()+10)+";");
	}

	@Override
	public void onDrop(DropEvent event) {
		if(hrDrag!=null) {
			hrDrag.removeHandler();
			hrDrag = null;
		}
		if(hrDrop!=null) {
			hrDrop.removeHandler();
			hrDrop = null;
		}
		copy = new StaticMathObjectPresenter();
		element.setState(MathObjectElementPresenter.STATE_SELECTABLE);
		if(hrSelection==null)
			hrSelection = eventBus.addHandler(SelectionEvent.getType(), this);
		if(hrFlyOver==null)
			hrFlyOver = eventBus.addHandler(FlyOverEvent.getType(),this);
		RootPanel.get("drag").getElement().setAttribute("style","left:0;top:0;");
		RootPanel.get("drag").clear();
		if(RootPanel.get("drag").getElement().hasChildNodes())
			RootPanel.get("drag").getElement().getFirstChild().removeFromParent();
	}
}
