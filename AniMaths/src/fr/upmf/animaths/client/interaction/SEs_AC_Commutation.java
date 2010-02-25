package fr.upmf.animaths.client.interaction;

import fr.upmf.animaths.client.mvp.MOWordingWidget;
import fr.upmf.animaths.client.mvp.MathObject.MOAddContainer;
import fr.upmf.animaths.client.mvp.MathObject.MOElement;
import fr.upmf.animaths.client.mvp.MathObject.MOSignedElement;

public final class SEs_AC_Commutation extends MOAbstractProcess{

	private static final SEs_AC_Commutation instance = new SEs_AC_Commutation();
	protected SEs_AC_Commutation() {}
	public static void setEnabled(boolean enabled) {
		MOAbstractProcess.setEnabled(instance, enabled);
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
				System.out.println("SEs_AC_Commutation : Interested");
				return true;
			}
		}
		return false;
	}

	@Override
	protected short getTagOfProcess() {
		if(parentOfSelected == whereElement.getMathObjectParent()
			&& (zoneH==MOElement.ZONE_OO || zoneH==MOElement.ZONE_EE)) {
			System.out.println("SEs_AC_Commutation : PROCESS_OK");
			return PROCESS_OK;
		}
		return PROCESS_NO;
	}
	
	@Override
	public void onAskQuestion() {
		assert parentOfSelected == whereElement.getMathObjectParent()
		&& (zoneH==MOElement.ZONE_OO || zoneH==MOElement.ZONE_EE);
		System.out.println("SEs_AC_Commutation : askQuestion");
		executeProcess(1);
	}
	
	@Override
	public void onExecuteProcess(int answer) {
		System.out.println("SEs_AC_Commutation : ExecuteProcess");
		parentOfSelected.remove(selected);
		parentOfSelected.add(selected,(MOSignedElement)whereElement,zoneH==MOElement.ZONE_EE);
	}

	@Override
	public MOWordingWidget getMessage(int answer) {
		return new MOWordingWidget("On peut déplacer ",selected.clone(),(zoneH==MOElement.ZONE_OO?" avant ":" après "),whereElement.clone(),"<br>"+
				"car on a le droit d'échanger les termes d'une addition ! <br>"+
				"<em>c'est la commutation dans l'addition !</em>");
	}
}
