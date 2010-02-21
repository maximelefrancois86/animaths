package fr.upmf.animaths.client.interaction.process.core;

import fr.upmf.animaths.client.interaction.events.QuestionEvent;
import fr.upmf.animaths.client.interaction.process.MOAbstractProcess;
import fr.upmf.animaths.client.mvp.MathObject.MOElement;
import fr.upmf.animaths.client.mvp.MathObject.MOMultiplyElement;
import fr.upmf.animaths.client.mvp.MathObject.MOSignedElement;

public final class SEs_SEs_ChangeSign extends MOAbstractProcess{

	private static final SEs_SEs_ChangeSign instance = new SEs_SEs_ChangeSign();
	protected SEs_SEs_ChangeSign() {}
	public static void setEnabled() {
		MOAbstractProcess.setEnabled(instance);
	}

	@Override
	protected boolean isProcessInvolved() {
		if(selectedElement instanceof MOSignedElement) {
			if(((MOSignedElement) selectedElement).getChild() instanceof MOSignedElement)
				return true;
			if(((MOSignedElement) selectedElement).getMathObjectParent() instanceof MOSignedElement)
				return true;
		}
		if(selectedElement instanceof MOMultiplyElement) {
			MOElement<?> child = ((MOMultiplyElement) selectedElement).getChild();
			if(child instanceof MOSignedElement) {
				MOElement<?> greatChild = ((MOSignedElement) child).getChild();
				if(greatChild instanceof MOSignedElement) {
					selectedElement = child;
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
	protected short getTagOfProcess() {
		return PROCESS_CAUTION;
	}

	@Override
	protected void executeProcess() {
		assert whereElement instanceof MOSignedElement;
		assert selectedElement instanceof MOSignedElement;
		
		eventBus.fireEvent(new QuestionEvent());
		
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
