package fr.upmf.animaths.client.interaction.process.core;

import fr.upmf.animaths.client.interaction.process.MOAbstractProcess;
import fr.upmf.animaths.client.interaction.process.Question;
import fr.upmf.animaths.client.interaction.process.event.ProcessDoneEvent;
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
	protected short getTagOfProcess() {
		if(whereElement instanceof MOSignedElement) {
			MOSignedElement where = (MOSignedElement) whereElement;
			if(where.getChild()==selectedElement || where.getMathObjectParent()==selectedElement) {
				return PROCESS_CAUTION;
			}
		}
		return PROCESS_NO;
	}

	@Override
	public void askQuestion() {
		assert whereElement instanceof MOSignedElement;
		assert selectedElement instanceof MOSignedElement;		
		MOSignedElement parent;
		MOSignedElement child;

		if(((MOSignedElement) whereElement).getChild()==selectedElement) {
			parent = (MOSignedElement) whereElement;
			child = (MOSignedElement) selectedElement;
		}	
		else {
			parent = (MOSignedElement) selectedElement;
			child = (MOSignedElement) whereElement;
		}

		boolean isMinus = parent.isMinus()^child.isMinus();		

		MOSignedElement goodAnswer = new MOSignedElement(child.getChild().clone(),isMinus);
		goodAnswer.setNeedsSign(true);
		MOSignedElement badAnswer = new MOSignedElement(child.getChild().clone(),!isMinus);
		badAnswer.setNeedsSign(true);

		Question question = new Question(this);
		question.addAnswer(goodAnswer, true);
		question.addAnswer(badAnswer, false);	
		question.center();
	}

	@Override
	public void executeProcess() {
		eventBus.fireEvent(new ProcessDoneEvent());
		MOSignedElement where = (MOSignedElement) whereElement;
		MOSignedElement selected = (MOSignedElement) selectedElement;
		if(where.getChild()==selected) {
			where.setMinus(where.isMinus()^selected.isMinus());
			where.setChild(selected.getChild());
		}	
		else if(where.getMathObjectParent()==selected) {
			selected.setMinus(where.isMinus()^selected.isMinus());
			selected.setChild(where.getChild());
		}
		presenter.init(presenter.getElement());
	}

}
