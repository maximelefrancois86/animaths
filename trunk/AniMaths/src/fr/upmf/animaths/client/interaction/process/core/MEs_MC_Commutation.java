package fr.upmf.animaths.client.interaction.process.core;

import java.util.List;

import fr.upmf.animaths.client.interaction.SelectionElement;
import fr.upmf.animaths.client.interaction.process.MathObjectProcess;
import fr.upmf.animaths.client.interaction.process.event.DragSelectedEvent;
import fr.upmf.animaths.client.interaction.process.event.DropSelectedEvent;
import fr.upmf.animaths.client.interaction.process.event.GrabSelectedEvent;
import fr.upmf.animaths.client.mvp.MathObjectDynamicPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectElementPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectMultiplyContainerPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectMultiplyElementPresenter;

public final class MEs_MC_Commutation extends MathObjectProcess{

	// instancier 'instance' au chargement de la classe
	private static final MEs_MC_Commutation instance = new MEs_MC_Commutation();
	protected MEs_MC_Commutation() {}
	public static void setEnabled() {
		MathObjectProcess.setEnabled(instance);
	}

	private SelectionElement selectionElement;
	private MathObjectDynamicPresenter presenter;

	private MathObjectMultiplyElementPresenter selected;
	private MathObjectMultiplyContainerPresenter parentOfSelected;
	private boolean atNum;
	
	private MathObjectMultiplyElementPresenter where;
	private short zone;

	@Override
	public void onGrabSelected(GrabSelectedEvent event) {
		selectionElement = event.getSelectionElement();
		presenter = event.getSelectionElement().getPresenter();
		MathObjectElementPresenter<?> selected = selectionElement.getSelectedElement();
		MathObjectElementPresenter<?> parentOfSelected = selected.getMathObjectParent();
		if(selected instanceof MathObjectMultiplyElementPresenter
				&& parentOfSelected instanceof MathObjectMultiplyContainerPresenter) {
			this.selected = (MathObjectMultiplyElementPresenter) selected;
			this.parentOfSelected = (MathObjectMultiplyContainerPresenter) parentOfSelected;
			atNum = this.parentOfSelected.getNumerator().contains(selected);
			setHandler(DragSelectedEvent.getType());
			selectionElement.setProcessFound(true);
			return;
		}
		removeHandler(DragSelectedEvent.getType());
	}

	@Override
	public void onDragSelected(DragSelectedEvent event) {
		MathObjectElementPresenter<?> element = event.getWhereElement();
		if(!selectionElement.getProcessFound() 
				&& parentOfSelected == element.getMathObjectParent()
				&&(atNum && parentOfSelected.getNumerator().contains(element)
					|| !atNum && parentOfSelected.getDenominator().contains(element))) {			
			where = (MathObjectMultiplyElementPresenter) element;
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
		List<MathObjectMultiplyElementPresenter> children;
		if(atNum)
			children = parentOfSelected.getNumerator();
		else
			children = parentOfSelected.getDenominator();
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
