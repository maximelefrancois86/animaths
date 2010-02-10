package fr.upmf.animaths.client.interaction.process.core;

import java.util.List;

import fr.upmf.animaths.client.interaction.SelectionElement;
import fr.upmf.animaths.client.interaction.process.MathObjectProcess;
import fr.upmf.animaths.client.interaction.process.event.DragSelectedEvent;
import fr.upmf.animaths.client.interaction.process.event.DropSelectedEvent;
import fr.upmf.animaths.client.interaction.process.event.GrabSelectedEvent;
import fr.upmf.animaths.client.mvp.MathObjectDynamicPresenter;
import fr.upmf.animaths.client.mvp.MathObjectStaticPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectAddContainerPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectElementPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectEquationPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectIdentifierPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectNumberPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectSignedElementPresenter;

public final class Xs_E_ChangeCopied extends MathObjectProcess{

	private static final Xs_E_ChangeCopied instance = new Xs_E_ChangeCopied();
	protected Xs_E_ChangeCopied() {}
	public static void setEnabled() {
		MathObjectProcess.setEnabled(instance);
	}

	private MathObjectElementPresenter<?> copiedElement;

	private boolean whereWasEq = false;

	private boolean atLeft;
	
	@Override
	protected boolean isProcessInvolved() {
		MathObjectElementPresenter<?> parentOfSelected = selectedElement.getMathObjectParent();
		if(parentOfSelected instanceof MathObjectEquationPresenter
			&&(selectedElement instanceof MathObjectIdentifierPresenter
				|| selectedElement instanceof MathObjectNumberPresenter
				|| selectedElement instanceof MathObjectSignedElementPresenter)) {
			atLeft = selectedElement==((MathObjectEquationPresenter) parentOfSelected).getLeftHandSide();
			return true;
		}
		return false;
	}

	@Override
	protected int getPriorityOfProcess() {
		MathObjectElementPresenter<?> where = event.getWhereElement();
		MathObjectElementPresenter<?> parentOfWhere = where.getMathObjectParent();
		MathObjectElementPresenter<?> greatParentOfWhere = parentOfWhere.getMathObjectParent();
		zoneHOld = zoneH;
		zoneVOld = zoneV;
		zoneH = event.getZoneH();
		zoneV = event.getZoneV();
		if(where instanceof MathObjectEquationPresenter) {
			if(whereWasEq)
				if(checkModif()) {
					setHandler(DropSelectedEvent.getType());
					selectionElement.setProcessFoundWithPriority(priority);
				}
					
					
					
							&& zoneVOld
						
						
				
			}
			
			whereWasEq = true;
		}
		else 
			whereWasEq = false;
		
		if(parentOfWhere instanceof MathObjectEquationPresenter) {
			
		}
		else if(greatParentOfWhere instanceof MathObjectEquationPresenter) {
			
		}
			MathObjectElementPresenter<?> element = selectionElement.getCopiedPresenter().getElement();
			zoneHOld = zoneH;
			zoneVOld = zoneV;
			zoneH = event.getZoneH();
			zoneV = event.getZoneV();
			if(zoneHOld==MathObjectElementPresenter.ZONE_CENTER && zoneH==MathObjectElementPresenter.ZONE_E 
					||zoneHOld==MathObjectElementPresenter.ZONE_CENTER && zoneH==MathObjectElementPresenter.ZONE_O)) {
			
			if(element instanceof MathObjectSignedElement)
		}
			MathObjectStaticPresenter copiedPresenter = selectionElement.getCopiedPresenter();
			MathObjectSignedElementPresenter copiedElement = ((MathObjectSignedElementPresenter) copiedPresenter.getElement());
			copiedElement.setMinus(!copiedElement.isMinus());
			copiedPresenter.setElement(copiedElement);
		}
		if(!selectionElement.getProcessFound()) {
			if(equation == greatParentOfWhere
				&& parentOfSelected != parentOfWhere
				&& parentOfWhere instanceof MathObjectAddContainerPresenter
				&& where instanceof MathObjectSignedElementPresenter) {
				this.where = (MathObjectSignedElementPresenter) where;
				this.parentOfWhere = (MathObjectAddContainerPresenter) parentOfWhere;
				if(zoneH==MathObjectElementPresenter.ZONE_OO || zoneH==MathObjectElementPresenter.ZONE_EE) {
					setHandler(DropSelectedEvent.getType());
					selectionElement.setProcessFound(true);
					return;
				}
			}
		}
		removeHandler(DropSelectedEvent.getType());
	}

	@Override
	public void onDropSelected(DropSelectedEvent event) {
		removeHandler(DragSelectedEvent.getType());
		removeHandler(DropSelectedEvent.getType());
		List<MathObjectSignedElementPresenter> childrenS = parentOfSelected.getChildren();
		List<MathObjectSignedElementPresenter> childrenW = parentOfWhere.getChildren();
		childrenS.remove(childrenS.indexOf(selected));
		int indexOfWhere = childrenW.indexOf(where);
		if(zoneH==MathObjectElementPresenter.ZONE_EE)
			indexOfWhere++;
		selected.setMinus(!selected.isMinus());
		childrenW.add(indexOfWhere,selected);
		presenter.setElement(presenter.getElement());
	}

	public boolean checkModif() {
		boolean hasModif = false;
		if(atLeft) {
			if(zoneHOld==MathObjectElementPresenter.ZONE_CENTER
					&& zoneH==MathObjectElementPresenter.ZONE_E)
				if(zoneV<=MathObjectElementPresenter.ZONE_CENTER)
					hasModif = changeSign();
				else
					hasModif = inverseSign();
			else if(zoneHOld==MathObjectElementPresenter.ZONE_E
					&& zoneH==MathObjectElementPresenter.ZONE_CENTER)
				hasModif = init();
			else if(zoneH>=MathObjectElementPresenter.ZONE_E)
				if(zoneVOld==MathObjectElementPresenter.ZONE_CENTER
					&& zoneV==MathObjectElementPresenter.ZONE_N)
					hasModif = changeSign();
				else if(zoneVOld==MathObjectElementPresenter.ZONE_CENTER
						&& zoneV==MathObjectElementPresenter.ZONE_S)
					hasModif = inverseSign();
		}
		else {
			if(zoneHOld==MathObjectElementPresenter.ZONE_CENTER
					&& zoneH==MathObjectElementPresenter.ZONE_O)
				if(zoneV<=MathObjectElementPresenter.ZONE_CENTER)
					hasModif = changeSign();
				else
					hasModif = inverseSign();
			else if(zoneHOld==MathObjectElementPresenter.ZONE_O
					&& zoneH==MathObjectElementPresenter.ZONE_CENTER)
				hasModif = init();
			else if(zoneH<=MathObjectElementPresenter.ZONE_O)
				if(zoneVOld==MathObjectElementPresenter.ZONE_CENTER
					&& zoneV==MathObjectElementPresenter.ZONE_N)
					hasModif = changeSign();
				else if(zoneVOld==MathObjectElementPresenter.ZONE_CENTER
						&& zoneV==MathObjectElementPresenter.ZONE_S)
					hasModif = inverseSign();
		}
	}

}
