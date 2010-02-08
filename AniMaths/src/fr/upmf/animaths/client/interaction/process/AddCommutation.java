package fr.upmf.animaths.client.interaction.process;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.customware.gwt.presenter.client.EventBus;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.GwtEvent.Type;

import fr.upmf.animaths.client.interaction.SelectionElement;
import fr.upmf.animaths.client.interaction.events.DropEvent;
import fr.upmf.animaths.client.interaction.process.event.DragSelectedEvent;
import fr.upmf.animaths.client.interaction.process.event.DropSelectedEvent;
import fr.upmf.animaths.client.interaction.process.event.GrabSelectedEvent;
import fr.upmf.animaths.client.mvp.AniMathsPresenter;
import fr.upmf.animaths.client.mvp.MathObjectDynamicPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectAddContainerPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectElementPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectSignedElementPresenter;

public class AddCommutation implements MathObjectProcess{

	private static AddCommutation instance = new AddCommutation();
	private EventBus eventBus = AniMathsPresenter.eventBus;
	private SelectionElement selectionElement;
	private MathObjectDynamicPresenter presenter;
//	private MathObjectStaticPresenter copiedPresenter;
	private MathObjectSignedElementPresenter selectedElement;
	private MathObjectAddContainerPresenter parentOfSelected;
	private MathObjectSignedElementPresenter whereElement;
	private short zone;

	private Map<Type<?>,HandlerRegistration> hr = new HashMap<Type<?>,HandlerRegistration>();

	private AddCommutation() {}

	public static void setEnabled() {
		instance.setHandler(GrabSelectedEvent.getType());
	}

	@Override
	public void onGrabSelected(GrabSelectedEvent event) {
		MathObjectElementPresenter<?> element = event.getSelectionElement().getSelectedElement();
		if(element instanceof MathObjectSignedElementPresenter) {
			selectionElement = event.getSelectionElement();
			selectionElement.setProcessFound(true);
			presenter = event.getSelectionElement().getPresenter();
//			copiedPresenter = event.getSelectionElement().getCopiedPresenter();
			selectedElement = (MathObjectSignedElementPresenter) element;
			parentOfSelected = (MathObjectAddContainerPresenter) element.getMathObjectParent();
			setHandler(DragSelectedEvent.getType());
		}
	}

	@Override
	public void onDragSelected(DragSelectedEvent event) {
		MathObjectElementPresenter<?> element = event.getWhereElement();
		if(parentOfSelected == element.getMathObjectParent() ) {
			zone = event.getZone();
			switch(zone) {
			case MathObjectElementPresenter.ZONE_IN_NO:
			case MathObjectElementPresenter.ZONE_IN_O:
			case MathObjectElementPresenter.ZONE_IN_SO:
			case MathObjectElementPresenter.ZONE_IN_NE:
			case MathObjectElementPresenter.ZONE_IN_E:
			case MathObjectElementPresenter.ZONE_IN_SE:
				if(!selectionElement.getProcessFound()) {
					setHandler(DropSelectedEvent.getType());
					selectionElement.setProcessFound(true);
				}
				whereElement = (MathObjectSignedElementPresenter) element;
				break;
			}
		}
	}

	@Override
	public void onDropSelected(DropSelectedEvent event) {
		removeHandler(DragSelectedEvent.getType());
		removeHandler(DropEvent.getType());
		List<MathObjectSignedElementPresenter> children = parentOfSelected.getChildren();
		children.remove(children.indexOf(selectedElement));
		int indexOfWhere = children.indexOf(whereElement);
		switch(zone) {
		case MathObjectElementPresenter.ZONE_IN_NE:
		case MathObjectElementPresenter.ZONE_IN_E:
		case MathObjectElementPresenter.ZONE_IN_SE:
			indexOfWhere++;
		case MathObjectElementPresenter.ZONE_IN_NO:
		case MathObjectElementPresenter.ZONE_IN_O:
		case MathObjectElementPresenter.ZONE_IN_SO:
			children.add(indexOfWhere,selectedElement);
			presenter.setElement(presenter.getElement());
			break;
		}
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
	

}
