package fr.upmf.animaths.client.interaction.process.core;

import com.google.gwt.user.client.ui.FlowPanel;

import fr.upmf.animaths.client.interaction.process.MOAbstractProcess;
import fr.upmf.animaths.client.interaction.process.Question;
import fr.upmf.animaths.client.mvp.MathWordingWidget;
import fr.upmf.animaths.client.mvp.MathObject.MOElement;
import fr.upmf.animaths.client.mvp.MathObject.MOEquation;
import fr.upmf.animaths.client.mvp.MathObject.MOMultiplyElement;
import fr.upmf.animaths.client.mvp.MathObject.MONumber;
import fr.upmf.animaths.client.mvp.MathObject.MOSignedElement;

public final class SEs_SEs_ChangeSign extends MOAbstractProcess{

	private static final SEs_SEs_ChangeSign instance = new SEs_SEs_ChangeSign();
	protected SEs_SEs_ChangeSign() {}
	public static void setEnabled() {
		MOAbstractProcess.setEnabled(instance);
	}

	MOSignedElement parent;
	MOSignedElement child;
	boolean isMinus;
	
	@Override
	protected boolean isProcessInvolved() {
		if(selectedElement instanceof MOSignedElement) {
			if(((MOSignedElement) selectedElement).getChild() instanceof MOSignedElement) {
				System.out.println("SEs_SEs_ChangeSign : Interested");
				return true;
			}
			if(((MOSignedElement) selectedElement).getMathObjectParent() instanceof MOSignedElement) {
				System.out.println("SEs_SEs_ChangeSign : Interested");
				return true;
			}
		}
		if(selectedElement instanceof MOMultiplyElement) {
			MOElement<?> child = ((MOMultiplyElement) selectedElement).getChild();
			if(child instanceof MOSignedElement) {
				MOElement<?> greatChild = ((MOSignedElement) child).getChild();
				if(greatChild instanceof MOSignedElement) {
					selectedElement = child;
					System.out.println("SEs_SEs_ChangeSign : Interested");
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
				System.out.println("SEs_SEs_ChangeSign : PROCESS_CAUTION");
				return PROCESS_CAUTION;
			}
		}
		return PROCESS_NO;
	}

	@Override
	public void askQuestion() {
		System.out.println("SEs_SEs_ChangeSign : askQuestion");
		assert whereElement instanceof MOSignedElement;
		assert selectedElement instanceof MOSignedElement;		

		if(((MOSignedElement) whereElement).getChild()==selectedElement) {
			parent = (MOSignedElement) whereElement;
			child = (MOSignedElement) selectedElement;
		}	
		else {
			parent = (MOSignedElement) selectedElement;
			child = (MOSignedElement) whereElement;
		}

		isMinus = parent.isMinus()^child.isMinus();		

		MOSignedElement goodAnswer = new MOSignedElement(child.getChild().clone(),isMinus);
		goodAnswer.setNeedsSign(true);
		MOSignedElement badAnswer = new MOSignedElement(child.getChild().clone(),!isMinus);
		badAnswer.setNeedsSign(true);

		MathWordingWidget wording = new MathWordingWidget(new FlowPanel());		
		wording.setWording("Quand on multiplie ",new MOSignedElement(new MONumber(1),parent.isMinus())," par ",child.clone(),", quel est le résultat ?");
		Question question = new Question(this, wording);
		question.addAnswer(goodAnswer, true);
		question.addAnswer(badAnswer, false);	
		question.center();
	}

	@Override
	public void onExecuteProcess() {
		System.out.println("SEs_SEs_Commutation : ExecuteProcess");
		parent.setMinus(isMinus);
		parent.setChild(child.getChild());
		MOElement<?> greatParent = parent.getMathObjectParent();
		if(!isMinus) {
			if(greatParent instanceof MOEquation)
				if(parent == ((MOEquation)greatParent).getLeftHandSide())
					((MOEquation)greatParent).setLeftHandSide(parent.getChild());
				else
					((MOEquation)greatParent).setRightHandSide(parent.getChild());
			else if(greatParent instanceof MOMultiplyElement) {
				((MOMultiplyElement) greatParent).setChild(parent.getChild());
			}
		}
	}

}
