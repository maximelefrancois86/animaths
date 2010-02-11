package fr.upmf.animaths.client.interaction.process.core;

import fr.upmf.animaths.client.interaction.process.MOAbstractProcess;
import fr.upmf.animaths.client.mvp.MathObject.MOAddContainer;
import fr.upmf.animaths.client.mvp.MathObject.MOElement;
import fr.upmf.animaths.client.mvp.MathObject.MOEquation;
import fr.upmf.animaths.client.mvp.MathObject.MOSignedElement;

public final class SEs_AC_E_ChangeHandSide extends MOAbstractProcess{

	private static final SEs_AC_E_ChangeHandSide instance = new SEs_AC_E_ChangeHandSide();
	protected SEs_AC_E_ChangeHandSide() {}
	public static void setEnabled() {
		MOAbstractProcess.setEnabled(instance);
	}

	private boolean atLeft;
	
	@Override
	protected boolean isProcessInvolved() {
		if(selectedElement instanceof MOSignedElement) {
			MOElement<?> parentOfSelectedElement = selectedElement.getMathObjectParent();
			if(parentOfSelectedElement instanceof MOAddContainer) {
				MOElement<?> greatParentOfSelectedElement = parentOfSelectedElement.getMathObjectParent();
				if(greatParentOfSelectedElement instanceof MOEquation) {
					atLeft = parentOfSelectedElement == ((MOEquation)greatParentOfSelectedElement).getLeftHandSide();
					return true;
				}
			}
		}
		return false;
	}

	@Override
	protected int getPriorityOfProcess() {
		if(whereElement instanceof MOSignedElement) {
			MOSignedElement where = (MOSignedElement) whereElement;
			if(where.getChild()==selectedElement || where.getMathObjectParent()==selectedElement) {
				return 1;
			}
		}
		return 0;
	}

	@Override
	protected void executeProcess() {
		assert whereElement instanceof MOSignedElement;
		assert selectedElement instanceof MOSignedElement;
		MOSignedElement where = (MOSignedElement) whereElement;
		MOSignedElement selected = (MOSignedElement) selectedElement;
		if(where.getChild()==selectedElement) {
			where.setMinus(where.isMinus()^selected.isMinus());
			where.setChild(selected.getChild());
		}	
		else if(where.getMathObjectParent()==selected) {
			selected.setMinus(where.isMinus()^selected.isMinus());
			selected.setChild(where.getChild());
		}
	}

}
