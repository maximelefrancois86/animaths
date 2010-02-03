package fr.upmf.animaths.client.mvp.modele;

import net.customware.gwt.presenter.client.EventBus;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.RootPanel;

import fr.upmf.animaths.client.mvp.AniMathsPresenter;
import fr.upmf.animaths.client.mvp.StaticMathObjectPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectElementPresenter;
import fr.upmf.animaths.client.mvp.events.DragEvent;
import fr.upmf.animaths.client.mvp.events.DragHandler;
import fr.upmf.animaths.client.mvp.events.DropEvent;
import fr.upmf.animaths.client.mvp.events.DropHandler;
import fr.upmf.animaths.client.mvp.events.FlyOverEvent;
import fr.upmf.animaths.client.mvp.events.FlyOverHandler;
import fr.upmf.animaths.client.mvp.events.GrabEvent;
import fr.upmf.animaths.client.mvp.events.GrabHandler;
import fr.upmf.animaths.client.mvp.events.SelectionChangeEvent;
import fr.upmf.animaths.client.mvp.events.SelectionChangeHandler;
import fr.upmf.animaths.client.mvp.events.SelectionEvent;
import fr.upmf.animaths.client.mvp.events.SelectionHandler;

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
	private int clientX0;
	private int clientY0;

	private SelectionElement() { }
	
	public static SelectionElement getInstance() {
		if(instance==null)
			instance = new SelectionElement();
		return instance;
	}

	public void setEnabled(boolean enabled) {
		if(enabled==true)
			hrFlyOver = eventBus.addHandler(FlyOverEvent.getType(), this);
		else {
			if(this.element!=null)
				element.setState(MathObjectElementPresenter.STATE_NONE);
			element = null;
			hrFlyOver.removeHandler();
		}
	}

	@Override
	public void onFlyOver(FlyOverEvent event) {
		System.out.println("fly over !");

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
			if(hrSelection!=null)
				hrSelection.removeHandler();
		}
		else {
			this.element.setState(MathObjectElementPresenter.STATE_SELECTABLE);
			hrSelection = eventBus.addHandler(SelectionEvent.getType(), this);
		}
	}

	@Override
	public void onSelect(SelectionEvent event) {
		System.out.println("select !");
		hrFlyOver.removeHandler();
		element.setState(MathObjectElementPresenter.STATE_SELECTED);
		hrSelectionChange = eventBus.addHandler(SelectionChangeEvent.getType(),this);
		hrGrab = eventBus.addHandler(GrabEvent.getType(),this);
	}

	@Override
	public void onSelectionChange(SelectionChangeEvent event) {
		element.setState(MathObjectElementPresenter.STATE_NONE);
		switch(event.getDirection()) {
		case SelectionChangeEvent.CHANGE_TO_PARENT:
			element = element.getMathObjectParent();
			break;
		case SelectionChangeEvent.CHANGE_TO_FIRST_CHILD:
			element = element.getMathObjectFirstChild();
			break;
		case SelectionChangeEvent.CHANGE_TO_PREVIOUS_SIBLING:
			element = element.getMathObjectParent().getMathObjectPreviousChild(element);
			break;
		case SelectionChangeEvent.CHANGE_TO_NEXT_SIBLING:
			element = element.getMathObjectParent().getMathObjectNextChild(element);
			break;
		}
		element.setState(MathObjectElementPresenter.STATE_SELECTED);
	}

	@Override
	public void onGrab(GrabEvent event) {
//		if(element!=null)
//		instance.copy.setElement(element.clone());
//	RootPanel.get("drag").add(instance.copy.getDisplay().asWidget(),instance.clientX0,instance.clientY0);
//	RootPanel.get("drag").getElement().setAttribute("style","left:"+(instance.clientX0+10)+";top:"+(instance.clientY0+10)+";");
		if(element!=null) {
			System.out.println("grabbed !");
		}
	}

	@Override
	public void onDrag(DragEvent event) {
		System.out.println("dragged !");
		RootPanel.get("drag").getElement().setAttribute("style","left:"+(event.getEvent().getClientX()+10)+";top:"+(event.getEvent().getClientY()+10)+";");
	}

	@Override
	public void onDrop(DropEvent event) {
		System.out.println("dropped !");
		setEnabled(false);
	}


}
