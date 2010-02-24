package fr.upmf.animaths.client.interaction.process.core;

import com.google.gwt.user.client.ui.FlowPanel;

import fr.upmf.animaths.client.interaction.process.MOAbstractProcess;
import fr.upmf.animaths.client.interaction.process.QuestionTextBox;
import fr.upmf.animaths.client.mvp.MOWordingWidget;
import fr.upmf.animaths.client.mvp.MathObject.IMOHasOneChild;
import fr.upmf.animaths.client.mvp.MathObject.MOAddContainer;
import fr.upmf.animaths.client.mvp.MathObject.MOElement;
import fr.upmf.animaths.client.mvp.MathObject.MOEquation;
import fr.upmf.animaths.client.mvp.MathObject.MONumber;
import fr.upmf.animaths.client.mvp.MathObject.MOSignedElement;

public final class SEs_N_Add extends MOAbstractProcess{

	private static final SEs_N_Add instance = new SEs_N_Add();
	protected SEs_N_Add() {}
	public static void setEnabled(boolean enabled) {
		MOAbstractProcess.setEnabled(instance, enabled);
	}

	MOAddContainer addContainer;
	MOSignedElement selected;
	float floatSelected;
	MOSignedElement where;
	float floatWhere;
	float floatSum;
	
	@Override
	protected boolean isProcessInvolved() {
		if(selectedElement instanceof MOSignedElement) {
			selected = (MOSignedElement) selectedElement;
			MOElement<?> parentOfSelected = selected.getMathObjectParent();
			if(selected.getChild() instanceof MONumber && parentOfSelected instanceof MOAddContainer) {
				addContainer = (MOAddContainer) parentOfSelected;
				floatSelected = ((MONumber) selected.getChild()).getValue();
				if(selected.isMinus())
					floatSelected = -floatSelected;
				System.out.println("SEs_N_Add : Interested");
				return true;
			}
		}
		return false;
	}

	@Override
	protected short getTagOfProcess() {
		if(whereElement instanceof MOSignedElement) {
			where = (MOSignedElement) whereElement;
			if(where.getChild() instanceof MONumber && where.getMathObjectParent()==addContainer) {
				floatWhere = ((MONumber) where.getChild()).getValue();
				if(where.isMinus())
					floatWhere = -floatWhere;
				floatSum = floatSelected+floatWhere;
				System.out.println("SEs_N_Add : PROCESS_CAUTION");
				return PROCESS_CAUTION;
			}
		}
		return PROCESS_NO;
	}

	@Override
	public void onAskQuestion() {
		System.out.println("SEs_N_Add : askQuestion");
		MOWordingWidget wording = new MOWordingWidget(new FlowPanel());
		if(addContainer.indexOf(where)>addContainer.indexOf(selected))
			wording.setWording("Combien font ",new MOAddContainer(selected.clone(),where.clone())," ?");
		else
			wording.setWording("Combien font ",new MOAddContainer(where.clone(),selected.clone())," ?");
		System.out.println(String.valueOf(floatSum));
		(new QuestionTextBox(this, wording, String.valueOf(floatSum))).center();
	}

	@Override
	public void onExecuteProcess(int answer) {
		System.out.println("SEs_N_Add : ExecuteProcess");
		addContainer.remove(selected);
		where.setMinus(floatSum<0);
		where.setChild(new MONumber(Math.abs(floatSum)));
		// simplification...
		if(addContainer.size()==1) {
			System.out.println("ok10");
			MOElement<?> parent = addContainer.getMathObjectParent();
			MOElement<?> element = addContainer.get(0).isMinus()?addContainer.get(0):addContainer.get(0).getChild();
			if(parent instanceof IMOHasOneChild) {
				System.out.println("ok11");
				((IMOHasOneChild) parent).setChild(element);
			}
			else if(parent instanceof MOEquation) {
				System.out.println("ok12");
				boolean atLeft = ((MOEquation)parent).getLeftHandSide()==addContainer;
				((MOEquation)parent).setHandSide(element, atLeft);
			}
			else
				assert -1==0;
		}
	}

	@Override
	public MOWordingWidget getMessage(int answer) {
		if(answer>0)
			return new MOWordingWidget("Bonne réponse !");
		else if(answer==0)
			return new MOWordingWidget("Non, réessaie et attention aux signes !");
		else if(answer==-2)
			return new MOWordingWidget("La réponse doit être un nombre ! <br/> Si tu pense que la réponse n'est pas un nombre entier, utilise un point plutôt qu'une virgule...");
		else
			return new MOWordingWidget("Non, réessaie, c'est une mauvaise réponse !");
	}
}
