package fr.upmf.animaths.client.interaction.process.core;

import fr.upmf.animaths.client.interaction.process.MOAbstractProcess;
import fr.upmf.animaths.client.mvp.MOWordingWidget;
import fr.upmf.animaths.client.mvp.MathObject.MOElement;
import fr.upmf.animaths.client.mvp.MathObject.MOMultiplyContainer;
import fr.upmf.animaths.client.mvp.MathObject.MOMultiplyElement;

public class MEs_MC_Commutation extends MOAbstractProcess{

	private static final MEs_MC_Commutation instance = new MEs_MC_Commutation();
	protected MEs_MC_Commutation() {}
	public static void setEnabled(boolean enabled) {
		MOAbstractProcess.setEnabled(instance, enabled);
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
				atNum = !selected.isDivided();
				System.out.println("MEs_MC_Commutation : Interested");
				return true;
			}
		}
		return false;
	}

	@Override
	protected short getTagOfProcess() {
		if(parentOfSelected == whereElement.getMathObjectParent()
			&&(zoneH==MOElement.ZONE_OO || zoneH==MOElement.ZONE_EE)) {
			if(atNum^((MOMultiplyElement)whereElement).isDivided()) {
				System.out.println("SEs_AC_Commutation : PROCESS_OK");
				return PROCESS_OK;
			}
		}
		return PROCESS_NO;
	}

	@Override
	public void onAskQuestion() {
		executeProcess(1);
	}

	@Override
	public void onExecuteProcess(int answer) {
		parentOfSelected.remove(selected);
		parentOfSelected.add(selected,(MOMultiplyElement) whereElement,zoneH==MOElement.ZONE_EE);
	}

	@Override
	public MOWordingWidget getMessage(int answer) {
		return new MOWordingWidget("<div class='large'>On peut déplacer ",selected.clone(),zoneH==MOElement.ZONE_OO?" avant ":" après ",whereElement.clone(),"<br/>"+
				"car on a le droit d'échanger les termes d'une multiplication ! <br/>"+
				"<em>c'est la commutation dans la multiplication !</em></div>");
	}
}
