package fr.upmf.animaths.client.interaction.process.core;

import com.google.gwt.user.client.ui.FlowPanel;

import fr.upmf.animaths.client.interaction.process.MOAbstractProcess;
import fr.upmf.animaths.client.interaction.process.QuestionButton;
import fr.upmf.animaths.client.mvp.MathWordingWidget;
import fr.upmf.animaths.client.mvp.MathObject.MOAddContainer;
import fr.upmf.animaths.client.mvp.MathObject.MOElement;
import fr.upmf.animaths.client.mvp.MathObject.MOEquation;
import fr.upmf.animaths.client.mvp.MathObject.MOMultiplyContainer;
import fr.upmf.animaths.client.mvp.MathObject.MOMultiplyElement;
import fr.upmf.animaths.client.mvp.MathObject.MOSignedElement;

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
	private MOEquation moeMZ;
	
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
			return PROCESS_CAUTION;
		}
		if(whereElement==equation.getHandSide(!atLeft)
			&& (zoneH==MOElement.ZONE_EE || zoneH==MOElement.ZONE_OO)) {
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
		if(whereElement instanceof MOMultiplyElement) {
			int index = parentOfWhere.indexOf(where);
			assert index!=-1;
			if(zoneH==MOElement.ZONE_EE)
				index++;
			
			MOMultiplyElement good = selected.clone();
			good.setDivided(!good.isDivided());
			MOMultiplyContainer goodAnswer = parentOfWhere.clone();
			goodAnswer.add(index,good);
			questionButton.addAnswer(goodAnswer, 1);
	
			MOMultiplyElement bad = selected.clone();
			MOMultiplyContainer badAnswer = parentOfWhere.clone();
			badAnswer.add(index,bad);
			questionButton.addAnswer(badAnswer, 0);	
			
			questionButton.center();
			good.setStyleClass(MOElement.STYLE_CLASS_FOCUS);
			bad.setStyleClass(MOElement.STYLE_CLASS_FOCUS);
		}
		else if(whereElement==equation.getHandSide(!atLeft)) {
			MOSignedElement mose = new MOSignedElement(selected.getChild().clone()); 
			MOSignedElement moseZ; (moseZ=mose.clone()).setMinus(!mose.isMinus());
			MOMultiplyElement mome = selected.clone();
			MOMultiplyElement momeZ; (momeZ=mome.clone()).setDivided(!mome.isDivided());
			
			int index = multiplyContainer.indexOf(selected);

			MOEquation moeA = equation.clone();
			MOEquation moeAZ = equation.clone();
			MOElement<?> handSide = this.multiplyContainer.clone();
			((MOMultiplyContainer)handSide).remove(index);
			// simplification...
			if(((MOMultiplyContainer)handSide).size()==1 && !((MOMultiplyContainer)handSide).get(0).isDivided())
				handSide = ((MOMultiplyContainer)handSide).get(0).getChild();
			moeA.setHandSide(handSide.clone(),atLeft);
			moeAZ.setHandSide(handSide.clone(),atLeft);
			if(whereElement instanceof MOAddContainer) {
				index = (zoneH==MOElement.ZONE_OO)? 0 : ((MOAddContainer)whereElement).size();
				((MOAddContainer)moeA.getHandSide(!atLeft)).add(index, mose);
				((MOAddContainer)moeAZ.getHandSide(!atLeft)).add(index, moseZ);
			}
			else if(zoneH==MOElement.ZONE_OO) {
				moeA.setHandSide(new MOAddContainer( mose, new MOSignedElement(moeA.getHandSide(!atLeft)) ),!atLeft);
				moeAZ.setHandSide(new MOAddContainer( moseZ, new MOSignedElement(moeAZ.getHandSide(!atLeft)) ),!atLeft);
			}
			else {
				moeA.setHandSide(new MOAddContainer( new MOSignedElement(moeA.getHandSide(!atLeft)), mose ), !atLeft);
				moeAZ.setHandSide(new MOAddContainer( new MOSignedElement(moeAZ.getHandSide(!atLeft)), moseZ ), !atLeft);
			}

			MOEquation moeM = equation.clone();
			moeMZ = equation.clone(); // correct
			moeM.setHandSide(handSide.clone(),atLeft);
			moeMZ.setHandSide(handSide,atLeft);
			if(zoneH==MOElement.ZONE_OO) {
				moeM.setHandSide(new MOMultiplyContainer( mome, new MOMultiplyElement(moeM.getHandSide(!atLeft)) ),!atLeft);
				moeMZ.setHandSide(new MOMultiplyContainer( momeZ, new MOMultiplyElement(moeMZ.getHandSide(!atLeft)) ),!atLeft);
			}
			else {
				moeM.setHandSide(new MOMultiplyContainer( new MOMultiplyElement(moeM.getHandSide(!atLeft)), mome ), !atLeft);
				moeMZ.setHandSide(new MOMultiplyContainer( new MOMultiplyElement(moeMZ.getHandSide(!atLeft)), momeZ ), !atLeft);
			}

			questionButton.addAnswer(moeA, 0);
			questionButton.addAnswer(moeAZ, 0);
			questionButton.addAnswer(moeM, 0);
			questionButton.addAnswer(moeMZ, 1);
			
			questionButton.center();
			mose.setStyleClass(MOElement.STYLE_CLASS_FOCUS);
			moseZ.setStyleClass(MOElement.STYLE_CLASS_FOCUS);
			mome.setStyleClass(MOElement.STYLE_CLASS_FOCUS);
			momeZ.setStyleClass(MOElement.STYLE_CLASS_FOCUS);
		}
	}
	
	@Override
	public void onExecuteProcess(int answer) {
		if(whereElement instanceof MOMultiplyElement) {
			multiplyContainer.remove(selected);
			assert multiplyContainer.size()>0;
			// simplification...
			if(multiplyContainer.size()==1 && !multiplyContainer.get(0).isDivided())
				equation.setHandSide(multiplyContainer.get(0).getChild(),atLeft);
			selected.setDivided(!selected.isDivided());
			parentOfWhere.add(selected,where,zoneH==MOElement.ZONE_EE);
		}
		else if(whereElement==equation.getHandSide(!atLeft)) {
			equation.setLeftHandSide(moeMZ.getLeftHandSide());
			equation.setRightHandSide(moeMZ.getRightHandSide());
		}
	}

}
