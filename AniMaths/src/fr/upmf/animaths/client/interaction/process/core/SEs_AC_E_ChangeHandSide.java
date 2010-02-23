package fr.upmf.animaths.client.interaction.process.core;

import com.google.gwt.user.client.ui.FlowPanel;

import fr.upmf.animaths.client.interaction.process.MOAbstractProcess;
import fr.upmf.animaths.client.interaction.process.QuestionButton;
import fr.upmf.animaths.client.mvp.MathWordingWidget;
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
	
	private MOEquation equation;
	private MOAddContainer addContainer;
	private MOSignedElement selected;
	private boolean atLeft;	
	private MOAddContainer parentOfWhere;
	private MOSignedElement where;
	
	
	
	@Override
	protected boolean isProcessInvolved() {
		if(selectedElement instanceof MOSignedElement) {
			selected = (MOSignedElement) selectedElement;
			MOElement<?> parentOfSelectedElement = selectedElement.getMathObjectParent();
			if(parentOfSelectedElement instanceof MOAddContainer) {
				addContainer = (MOAddContainer) parentOfSelectedElement;
				MOElement<?> greatParentOfSelectedElement = parentOfSelectedElement.getMathObjectParent();
				if(greatParentOfSelectedElement instanceof MOEquation) {
					equation = (MOEquation) greatParentOfSelectedElement;
					atLeft = parentOfSelectedElement == ((MOEquation)greatParentOfSelectedElement).getLeftHandSide();
					System.out.println("SEs_AC_E_ChangeHandside: Interested");
					return true;
				}
			}
		}
		return false;
	}

	@Override
	protected short getTagOfProcess() {
		MOElement<?> parentOfWhereElement = whereElement.getMathObjectParent();
		MOElement<?> greatParentOfWhereElement = parentOfWhereElement.getMathObjectParent();
		if(greatParentOfWhereElement==equation
			&& parentOfWhereElement instanceof MOAddContainer
			&& parentOfWhereElement != addContainer
			&& (zoneH==MOElement.ZONE_EE || zoneH==MOElement.ZONE_OO)) {
			parentOfWhere = (MOAddContainer) parentOfWhereElement;
			where = (MOSignedElement) whereElement;
			return PROCESS_CAUTION;
		}
		return PROCESS_NO;
	}

	@Override
	public void askQuestion() {
		System.out.println("SEs_AC_E_ChangeHandSide : askQuestion");
		MathWordingWidget wording = new MathWordingWidget(new FlowPanel());		
		wording.setWording("Après avoir déplacé ",selectedElement.clone()," de l'autre côté du signe égal, quel est le résultat ?");
		QuestionButton questionButton = new QuestionButton(this, wording);
		
		int index = parentOfWhere.indexOf(where);
		assert index!=-1;
		if(zoneH==MOElement.ZONE_EE)
			index++;
		
		MOSignedElement good = selected.clone();
		good.setMinus(!good.isMinus());
		MOAddContainer goodAnswer = parentOfWhere.clone();
		goodAnswer.add(index,good);
		questionButton.addAnswer(goodAnswer, true);

		MOSignedElement bad = selected.clone();
		MOAddContainer badAnswer = parentOfWhere.clone();
		badAnswer.add(index,bad);
		questionButton.addAnswer(badAnswer, false);	
		
		questionButton.center();
		good.setStyleClass(MOElement.STYLE_CLASS_FOCUS);
		bad.setStyleClass(MOElement.STYLE_CLASS_FOCUS);
	}
	
	@Override
	public void onExecuteProcess() {
		addContainer.remove(selected);
		assert addContainer.size()>0;
		// simplification...
		if(addContainer.size()==1)
			if(atLeft) {
				equation.setLeftHandSide(addContainer.get(0));
				if(equation.getLeftHandSide() instanceof MOSignedElement
						&& !((MOSignedElement) equation.getLeftHandSide()).isMinus())
					equation.setLeftHandSide(((MOSignedElement) equation.getLeftHandSide()).getChild());
			}
			else {
				equation.setRightHandSide(addContainer.get(0));
				if(equation.getRightHandSide() instanceof MOSignedElement
						&& !((MOSignedElement) equation.getRightHandSide()).isMinus())
					equation.setRightHandSide(((MOSignedElement) equation.getRightHandSide()).getChild());
			}
		selected.setMinus(!selected.isMinus());
		parentOfWhere.add(selected,where,zoneH==MOElement.ZONE_EE);
	}

}
