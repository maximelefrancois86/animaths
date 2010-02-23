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
				return true;
			}
		}
		return false;
	}

	@Override
	protected short getTagOfProcess() {
		if(parentOfSelected == whereElement.getMathObjectParent()
			&&(zoneH==MOElement.ZONE_OO || zoneH==MOElement.ZONE_EE)) {
			if(atNum^((MOMultiplyElement)whereElement).isDivided())
				return PROCESS_OK;
			else
				return PROCESS_CAUTION;
		}
		return PROCESS_NO;
	}

//	protected void onExecuteProcess() {
//		parentOfSelected.remove(selected);
//		parentOfSelected.add(selected,(MOMultiplyElement) whereElement,zoneH==MOElement.ZONE_EE);
//	}
	@Override
	public void askQuestion() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void executeProcess() {
		// TODO Auto-generated method stub
		
	}
}
//public class QuestionDisplay extends DialogBox implements QuestionPresenter.Display {
//	
//	public QuestionDisplay() {
//		super(false);
//		this.setText("Quelle sera le r�sultat de cette manipulation ?");
//		this.setAnimationEnabled(true);
//		VerticalPanel dialogVPanel = new VerticalPanel();
//		dialogVPanel.addStyleName("dialogVPanel");
//		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
//		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
//		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
//		this.setWidget(dialogVPanel);
//	}
//}
