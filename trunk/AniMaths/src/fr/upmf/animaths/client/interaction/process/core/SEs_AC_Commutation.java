package fr.upmf.animaths.client.interaction.process.core;

import java.util.List;

import fr.upmf.animaths.client.interaction.SelectionElement;
import fr.upmf.animaths.client.interaction.process.MathObjectProcess;
import fr.upmf.animaths.client.interaction.process.event.DragSelectedEvent;
import fr.upmf.animaths.client.interaction.process.event.DropSelectedEvent;
import fr.upmf.animaths.client.interaction.process.event.GrabSelectedEvent;
import fr.upmf.animaths.client.mvp.MathObjectDynamicPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectAddContainerPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectElementPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectSignedElementPresenter;

public final class SEs_AC_Commutation extends MathObjectProcess{

	// instancier 'instance' au chargement de la classe
	private static final SEs_AC_Commutation instance = new SEs_AC_Commutation();
	protected SEs_AC_Commutation() {}
	public static void setEnabled() {
		MathObjectProcess.setEnabled(instance);
	}

	private SelectionElement selectionElement;
	private MathObjectDynamicPresenter presenter;

	private MathObjectSignedElementPresenter selected;
	private MathObjectAddContainerPresenter parentOfSelected;
	
	private MathObjectSignedElementPresenter where;
	private short zone;

	@Override
	public void onGrabSelected(GrabSelectedEvent event) {
		selectionElement = event.getSelectionElement();
		presenter = event.getSelectionElement().getPresenter();
		MathObjectElementPresenter<?> selected = selectionElement.getSelectedElement();
		MathObjectElementPresenter<?> parentOfSelected = selected.getMathObjectParent();
		if(selected instanceof MathObjectSignedElementPresenter
				&& parentOfSelected instanceof MathObjectAddContainerPresenter) {
			this.selected = (MathObjectSignedElementPresenter) selected;
			this.parentOfSelected = (MathObjectAddContainerPresenter) parentOfSelected;
			setHandler(DragSelectedEvent.getType());
			selectionElement.setProcessFound(true);
			return;
		}
		removeHandler(DropSelectedEvent.getType());
	}

	@Override
	public void onDragSelected(DragSelectedEvent event) {
		MathObjectElementPresenter<?> element = event.getWhereElement();
		if(!selectionElement.getProcessFound() 
				&& parentOfSelected == element.getMathObjectParent()) {
			where = (MathObjectSignedElementPresenter) element;
			zone = event.getZone();
			switch(zone) {
			case MathObjectElementPresenter.ZONE_IN_NO:
			case MathObjectElementPresenter.ZONE_IN_O:
			case MathObjectElementPresenter.ZONE_IN_SO:
			case MathObjectElementPresenter.ZONE_IN_NE:
			case MathObjectElementPresenter.ZONE_IN_E:
			case MathObjectElementPresenter.ZONE_IN_SE:
				setHandler(DropSelectedEvent.getType());
				selectionElement.setProcessFound(true);
				return;
			}
		}
		removeHandler(DropSelectedEvent.getType());
	}

	@Override
	public void onDropSelected(DropSelectedEvent event) {
		removeHandler(DragSelectedEvent.getType());
		removeHandler(DropSelectedEvent.getType());
		List<MathObjectSignedElementPresenter> children = parentOfSelected.getChildren();
		children.remove(children.indexOf(selected));
		int indexOfWhere = children.indexOf(where);
		switch(zone) {
		case MathObjectElementPresenter.ZONE_IN_NE:
		case MathObjectElementPresenter.ZONE_IN_E:
		case MathObjectElementPresenter.ZONE_IN_SE:
			indexOfWhere++;
		case MathObjectElementPresenter.ZONE_IN_NO:
		case MathObjectElementPresenter.ZONE_IN_O:
		case MathObjectElementPresenter.ZONE_IN_SO:
			children.add(indexOfWhere,selected);
			presenter.setElement(presenter.getElement());
			break;
		}
	}

}
