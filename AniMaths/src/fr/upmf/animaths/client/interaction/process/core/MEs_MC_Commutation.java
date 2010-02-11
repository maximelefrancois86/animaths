package fr.upmf.animaths.client.interaction.process.core;

import java.util.List;

import fr.upmf.animaths.client.interaction.process.MOAbstractProcess;
import fr.upmf.animaths.client.mvp.MathObject.MOElement;
import fr.upmf.animaths.client.mvp.MathObject.MOMultiplyContainer;
import fr.upmf.animaths.client.mvp.MathObject.MOMultiplyElement;

public class MEs_MC_Commutation extends MOAbstractProcess{

	private static final MEs_MC_Commutation instance = new MEs_MC_Commutation();
	protected MEs_MC_Commutation() {}
	public static void setEnabled() {
		MOAbstractProcess.setEnabled(instance);
	}

	private MOMultiplyElement selected;
	private MOMultiplyContainer parentOfSelected;
	private boolean atNum;
	
	@Override
	protected boolean isProcessInvolved() {
		if(selectedElement instanceof MOMultiplyElement) {
			MOElement<?> parentOfSelectedElement = selectedElement.getMathObjectParent();
			if(parentOfSelectedElement instanceof MOMultiplyContainer) {
				selected = (MOMultiplyElement) selectedElement;
				parentOfSelected = (MOMultiplyContainer) parentOfSelectedElement;
				atNum = parentOfSelected.getNumerator().contains(selected);
				return true;
			}
		}
		return false;
	}

	@Override
	protected int getPriorityOfProcess() {
		if(parentOfSelected == whereElement.getMathObjectParent()
			&&(atNum && parentOfSelected.getNumerator().contains(whereElement) || !atNum && parentOfSelected.getDenominator().contains(whereElement))
			&&(zoneH==MOElement.ZONE_OO || zoneH==MOElement.ZONE_EE))
			return 1;
		return 0;
	}

	@Override
	protected void executeProcess() {
		assert whereElement instanceof MOMultiplyElement;
		List<MOMultiplyElement> children;
		if(atNum)
			children = parentOfSelected.getNumerator();
		else
			children = parentOfSelected.getDenominator();
		children.remove(children.indexOf(selected));
		int indexOfWhere = children.indexOf(whereElement);
		assert indexOfWhere != -1;
		if(zoneH==MOElement.ZONE_EE)
			indexOfWhere++;
		children.add(indexOfWhere,selected);
	}
}
