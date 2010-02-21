package fr.upmf.animaths.client.interaction.process.core;

import fr.upmf.animaths.client.interaction.process.MOAbstractProcess;
import fr.upmf.animaths.client.mvp.MathObject.MOAddContainer;
import fr.upmf.animaths.client.mvp.MathObject.MOElement;
import fr.upmf.animaths.client.mvp.MathObject.MOSignedElement;

public final class SEs_AC_Commutation extends MOAbstractProcess{

	private static final SEs_AC_Commutation instance = new SEs_AC_Commutation();
	protected SEs_AC_Commutation() {}
	public static void setEnabled() {
		MOAbstractProcess.setEnabled(instance);
	}

	private MOSignedElement selected;
	private MOAddContainer parentOfSelected;
	
	@Override
	protected boolean isProcessInvolved() {
		if(selectedElement instanceof MOSignedElement) {
			MOElement<?> parentOfSelectedElement = selectedElement.getMathObjectParent();
			if(parentOfSelectedElement instanceof MOAddContainer) {
				selected = (MOSignedElement) selectedElement;
				parentOfSelected = (MOAddContainer) parentOfSelectedElement;
				return true;
			}
		}
		return false;
	}

	@Override
	protected int getPriorityOfProcess() {
		if(parentOfSelected == whereElement.getMathObjectParent()
			&& (zoneH==MOElement.ZONE_OO || zoneH==MOElement.ZONE_EE))
			return 1;
		return 0;
	}
	
	@Override
	protected short getTagOfProcess() {
		return PROCESS_OK;
	}

	@Override
	protected void executeProcess() {
		parentOfSelected.remove(selected);
		parentOfSelected.add(selected,(MOSignedElement)whereElement,zoneH==MOElement.ZONE_EE);
	}
}
