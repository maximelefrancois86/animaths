package fr.upmf.animaths.client.interaction.process.core;

import java.util.List;

import net.customware.gwt.presenter.client.EventBus;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.HandlerRegistration;

import fr.upmf.animaths.client.interaction.events.dragndrop.GrabEvent;
import fr.upmf.animaths.client.interaction.events.process.DragOverEvent;
import fr.upmf.animaths.client.interaction.events.process.DropOverEvent;
import fr.upmf.animaths.client.interaction.process.MathObjectProcess;
import fr.upmf.animaths.client.mvp.AniMathsPresenter;
import fr.upmf.animaths.client.mvp.MathObjectDynamicPresenter;
import fr.upmf.animaths.client.mvp.MathML.MathMLOperator;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectAddContainerPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectElementPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectSignedElementPresenter;

public class AddCommutation extends MathObjectProcess{

	private static AddCommutation instance;
	
	private EventBus eventBus = AniMathsPresenter.eventBus;

	private MathObjectElementPresenter<?> parentOfSelected;
	private MathObjectElementPresenter<?> selectedElement;
	private boolean bool = false;
	private Element localisation = MathMLOperator.getLocalisation("green").getElement();
	private MathObjectElementPresenter<?> whereElement;
	private MathObjectElementPresenter<?> whereElementOK;
	private short zone;
	private int x;
	private int y;
	private Element domElement;

	
	private HandlerRegistration hrGrab;
	private HandlerRegistration hrDragOver;
	private HandlerRegistration hrDrop;

	private AddCommutation() {}

	public static AddCommutation getInstance(MathObjectDynamicPresenter presenter) {
		if(instance==null)
			instance = new AddCommutation();
		instance.presenter = presenter;
		return instance;
	}

	@Override
	public void setEnabled(boolean enabled) {
		if(hrGrab==null)
			eventBus.addHandler(GrabEvent.getType(),this);
	}

	@Override
	public void onGrab(GrabEvent event) {
		selectedElement = event.getElement().getMathObjectSelectableElement();
		if(selectedElement.getType()==MathObjectElementPresenter.MATH_OBJECT_SIGNED_ELEMENT) {
			parentOfSelected = selectedElement.getMathObjectFirstSelectableParent();
			if(hrDragOver==null)
				hrDragOver = eventBus.addHandler(DragOverEvent.getType(),this);
			if(hrDrop==null)
				hrDrop = eventBus.addHandler(DropOverEvent.getType(),this);
			System.out.println(selectedElement.getType()+" "+MathObjectElementPresenter.MATH_OBJECT_SIGNED_ELEMENT);
			System.out.println(parentOfSelected.getType()+" "+MathObjectElementPresenter.MATH_OBJECT_ADDITION_CONTAINER);
			System.out.println("okGrab");
		}
	}

	@Override
	public void onDragOver(DragOverEvent event) {
		whereElement = event.getWhereElement();
		x = event.getEvent().getClientX();
		y = event.getEvent().getClientY();
		bool = false;
		do{
			if(parentOfSelected == whereElement.getMathObjectFirstSelectableParent() ) {
				// OK on peut commuter... on le signal par une bordure verte à dr. ou à g.
				zone = whereElement.getZone(x,y);
				switch(zone) {
				case MathObjectElementPresenter.ZONE_IN_NO:
				case MathObjectElementPresenter.ZONE_IN_O:
				case MathObjectElementPresenter.ZONE_IN_SO:
					whereElementOK = whereElement;
					domElement = whereElement.getFirstDOMElement();
					domElement.getParentElement().insertBefore(localisation,domElement);
					bool = true;
					break;
				case MathObjectElementPresenter.ZONE_IN_NE:
				case MathObjectElementPresenter.ZONE_IN_E:
				case MathObjectElementPresenter.ZONE_IN_SE:
					whereElementOK = whereElement;
					domElement = whereElement.getLastDOMElement();
					domElement.getParentElement().insertAfter(localisation,domElement);
					bool = true;
					break;
				}
			}
			whereElement = whereElement.getMathObjectParent();
		}
		while(whereElement.getType() != MathObjectElementPresenter.MATH_OBJECT_EQUATION);
		if(bool==false)
			localisation.removeFromParent();
	}

	@Override
	public void onDropOver(DropOverEvent event) {
		hrDragOver.removeHandler();
		hrDragOver = null;
		hrDrop.removeHandler();
		hrDrop = null;
		if(bool) {
			MathObjectAddContainerPresenter parentOfSelected = (MathObjectAddContainerPresenter) this.parentOfSelected;
			List<MathObjectSignedElementPresenter> children = parentOfSelected.getChildren();
			MathObjectSignedElementPresenter selectedElement = (MathObjectSignedElementPresenter) this.selectedElement;
			MathObjectSignedElementPresenter whereElementOK = (MathObjectSignedElementPresenter) this.whereElementOK;
			children.remove(children.indexOf(selectedElement));
			int indexOfWhere = children.indexOf(whereElementOK);
			switch(zone) {
			case MathObjectElementPresenter.ZONE_IN_NO:
			case MathObjectElementPresenter.ZONE_IN_O:
			case MathObjectElementPresenter.ZONE_IN_SO:
				children.add(indexOfWhere,selectedElement);
				presenter.setElement(presenter.getElement());
				break;
			case MathObjectElementPresenter.ZONE_IN_NE:
			case MathObjectElementPresenter.ZONE_IN_E:
			case MathObjectElementPresenter.ZONE_IN_SE:
				children.add(indexOfWhere+1,selectedElement);
				presenter.setElement(presenter.getElement());
				break;
			}
		}
	}
}
