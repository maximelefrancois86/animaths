package fr.upmf.animaths.client.interaction.process.core;

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
//			else {
//				System.out.println("SEs_AC_Commutation : PROCESS_CAUTION");
//				return PROCESS_CAUTION;
//			}
		}
		return PROCESS_NO;
	}

	@Override
	public void askQuestion() {
//		if(atNum^((MOMultiplyElement)whereElement).isDivided())
			executeProcess();
//		else {
//			System.out.println("MEs_MC_Commutation : askQuestion");
//
//			boolean isMinus = parent.isMinus()^child.isMinus();		
//	
//			MOSignedElement goodAnswer = new MOSignedElement(child.getChild().clone(),isMinus);
//			goodAnswer.setNeedsSign(true);
//			MOSignedElement badAnswer = new MOSignedElement(child.getChild().clone(),!isMinus);
//			badAnswer.setNeedsSign(true);
//	
//			MathWordingWidget wording = new MathWordingWidget("Quel est le résultat de");
//			Question question = new Question(this, "Quell");
//			question.addAnswer(goodAnswer, true);
//			question.addAnswer(badAnswer, false);	
//			question.center();
//		}
	}

	@Override
	public void onExecuteProcess() {
		parentOfSelected.remove(selected);
		parentOfSelected.add(selected,(MOMultiplyElement) whereElement,zoneH==MOElement.ZONE_EE);
	}
}
