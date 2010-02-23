package fr.upmf.animaths.client.interaction.process.core;

import com.google.gwt.user.client.ui.FlowPanel;

import fr.upmf.animaths.client.interaction.process.MOAbstractProcess;
import fr.upmf.animaths.client.interaction.process.QuestionButton;
import fr.upmf.animaths.client.mvp.MathWordingWidget;
import fr.upmf.animaths.client.mvp.MathObject.MOElement;
import fr.upmf.animaths.client.mvp.MathObject.MOEquation;
import fr.upmf.animaths.client.mvp.MathObject.MOMultiplyContainer;
import fr.upmf.animaths.client.mvp.MathObject.MOMultiplyElement;

public final class MEs_MC_E_ChangeHandSide extends MOAbstractProcess{

	private static final MEs_MC_E_ChangeHandSide instance = new MEs_MC_E_ChangeHandSide();
	protected MEs_MC_E_ChangeHandSide() {}
	public static void setEnabled() {
		MOAbstractProcess.setEnabled(instance);
	}

	private MOEquation equation;
	private MOMultiplyContainer multiplyContainer;
	private MOMultiplyElement selected;
	private boolean atLeft;	
	private boolean atNum;
	private MOMultiplyContainer parentOfWhere;
	private MOMultiplyElement where;
	private boolean atNumWhere;
	
	@Override
	protected boolean isProcessInvolved() {
		if(selectedElement instanceof MOMultiplyElement) {
			selected = (MOMultiplyElement) selectedElement;
			atNum = !selected.isDivided();
			MOElement<?> parentOfSelectedElement = selectedElement.getMathObjectParent();
			if(parentOfSelectedElement instanceof MOMultiplyContainer) {
				multiplyContainer = (MOMultiplyContainer) parentOfSelectedElement;
				MOElement<?> greatParentOfSelectedElement = parentOfSelectedElement.getMathObjectParent();
				if(greatParentOfSelectedElement instanceof MOEquation) {
					equation = (MOEquation) greatParentOfSelectedElement;
					atLeft = parentOfSelectedElement == ((MOEquation)greatParentOfSelectedElement).getLeftHandSide();
					System.out.println("MEs_MC_E_ChangeHandside: Interested");
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
			&& parentOfWhereElement instanceof MOMultiplyContainer
			&& parentOfWhereElement != multiplyContainer
			&& (zoneH==MOElement.ZONE_EE || zoneH==MOElement.ZONE_OO)) {
			parentOfWhere = (MOMultiplyContainer) parentOfWhereElement;
			where = (MOMultiplyElement) whereElement;
			atNumWhere = !where.isDivided();
			return PROCESS_CAUTION;
		}
		return PROCESS_NO;
	}

	@Override
	public void askQuestion() {
		System.out.println("MEs_MC_E_ChangeHandSide : askQuestion");
		MathWordingWidget wording = new MathWordingWidget(new FlowPanel());
		
		if(atNum)
			wording.setWording("Après avoir déplacé ",selected.clone()," de l'autre côté du signe égal, quel est le résultat ?");
		else {
			wording.setWording("Après avoir déplacé ",new MOMultiplyContainer(selected.clone())," de l'autre côté du signe égal, quel est le résultat ?");
		}
		QuestionButton questionButton = new QuestionButton(this, wording);
		
		int index = parentOfWhere.indexOf(where);
		assert index!=-1;
		if(zoneH==MOElement.ZONE_EE)
			index++;
		
		MOMultiplyElement good = selected.clone();
		good.setDivided(!good.isDivided());
		MOMultiplyContainer goodAnswer = parentOfWhere.clone();
		goodAnswer.add(index,good);
		questionButton.addAnswer(goodAnswer, true);

		MOMultiplyElement bad = selected.clone();
		MOMultiplyContainer badAnswer = parentOfWhere.clone();
		badAnswer.add(index,bad);
		questionButton.addAnswer(badAnswer, false);	
		
		questionButton.center();
		good.setStyleClass(MOElement.STYLE_CLASS_FOCUS);
		bad.setStyleClass(MOElement.STYLE_CLASS_FOCUS);
	}
	
	@Override
	public void onExecuteProcess() {
		multiplyContainer.remove(selected);
		assert multiplyContainer.size()>0;
		// simplification...
		if(multiplyContainer.size()==1 && atNum)
			if(atLeft)
				equation.setLeftHandSide(multiplyContainer.get(0));
			else
				equation.setRightHandSide(multiplyContainer.get(0));
		selected.setDivided(!selected.isDivided());
		parentOfWhere.add(selected,where,zoneH==MOElement.ZONE_EE);
	}

}
