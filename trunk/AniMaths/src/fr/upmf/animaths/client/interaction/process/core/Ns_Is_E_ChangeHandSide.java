package fr.upmf.animaths.client.interaction.process.core;

import com.google.gwt.user.client.ui.FlowPanel;

import fr.upmf.animaths.client.interaction.process.MOAbstractProcess;
import fr.upmf.animaths.client.interaction.process.QuestionButton;
import fr.upmf.animaths.client.mvp.MOWordingWidget;
import fr.upmf.animaths.client.mvp.MathObject.MOAddContainer;
import fr.upmf.animaths.client.mvp.MathObject.MOElement;
import fr.upmf.animaths.client.mvp.MathObject.MOEquation;
import fr.upmf.animaths.client.mvp.MathObject.MOIdentifier;
import fr.upmf.animaths.client.mvp.MathObject.MOMultiplyContainer;
import fr.upmf.animaths.client.mvp.MathObject.MOMultiplyElement;
import fr.upmf.animaths.client.mvp.MathObject.MONumber;
import fr.upmf.animaths.client.mvp.MathObject.MOSignedElement;

public final class Ns_Is_E_ChangeHandSide extends MOAbstractProcess{

	private static final Ns_Is_E_ChangeHandSide instance = new Ns_Is_E_ChangeHandSide();
	protected Ns_Is_E_ChangeHandSide() {}
	public static void setEnabled(boolean enabled) {
		MOAbstractProcess.setEnabled(instance, enabled);
	}
	
	private MOEquation equation;
	private boolean atLeft;	
	private boolean isMinus;
	private MOElement<?> otherHandSide;
	private MOSignedElement mose;
	private MOSignedElement moseZ;
	private MOMultiplyElement mome;
	private MOMultiplyElement momeZ;
	private MOEquation moe0AZ;
	private MOEquation moe1MZ;
	
	@Override
	protected boolean isProcessInvolved() {
		MOElement<?> parentOfSelectedElement = selectedElement.getMathObjectParent();
		if( parentOfSelectedElement instanceof MOEquation
				&& (selectedElement instanceof MONumber 
						|| selectedElement instanceof MOIdentifier
						|| selectedElement instanceof MOSignedElement)) {
			equation = (MOEquation) parentOfSelectedElement;
			atLeft = selectedElement == equation.getLeftHandSide();
			otherHandSide = ((atLeft)?equation.getRightHandSide():equation.getLeftHandSide());
			System.out.println("Ns_Is_E_ChangeHandSide : Interested");
			return true;
		}
		return false;
	}

	@Override
	protected short getTagOfProcess() {
		if((zoneH==MOElement.ZONE_EE || zoneH==MOElement.ZONE_OO)) {
			if(whereElement == otherHandSide)
				return PROCESS_CAUTION;
			else if(whereElement instanceof MOSignedElement) {
				MOElement<?> parentOfWhereElement = whereElement.getMathObjectParent();
				if(parentOfWhereElement instanceof MOAddContainer && parentOfWhereElement == otherHandSide) {
					int index = ((MOAddContainer)parentOfWhereElement).indexOf((MOSignedElement)whereElement);
					if(!(  (index==0 && zoneH==MOElement.ZONE_OO)
							|| (index==((MOAddContainer)parentOfWhereElement).size()-1 && zoneH==MOElement.ZONE_EE)))
					return PROCESS_CAUTION;
				}
			}
			else if(whereElement instanceof MOMultiplyElement) {
				MOMultiplyContainer parentOfWhereElement = (MOMultiplyContainer) whereElement.getMathObjectParent();
				if(parentOfWhereElement == otherHandSide) {
					int index = parentOfWhereElement.indexOf((MOMultiplyElement)whereElement);
					if(!(  (index==0 && zoneH==MOElement.ZONE_OO)
							|| (index==parentOfWhereElement.sizeOfNumerator()-1 && zoneH==MOElement.ZONE_EE)))
					return PROCESS_CAUTION;
				}
			}
		}
		return PROCESS_NO;
	}

	@Override
	public void onAskQuestion() {
		System.out.println("SEs_AC_E_ChangeHandSide : askQuestion");

		MOWordingWidget wording = new MOWordingWidget(new FlowPanel());		
		wording.setWording("Après avoir déplacé ",selectedElement.clone()," de l'autre côté du signe égal, quel est le résultat ?");
		QuestionButton questionButton = new QuestionButton(this, wording);
		
		if(whereElement == otherHandSide) {
			mose = (selectedElement instanceof MOSignedElement)? ((MOSignedElement)selectedElement).clone() : new MOSignedElement(selectedElement.clone()); 
			isMinus = mose.isMinus();
			(moseZ=mose.clone()).setMinus(!mose.isMinus());
			mome = new MOMultiplyElement(selectedElement.clone());
			(momeZ=mome.clone()).setDivided(!mome.isDivided());

			MOEquation moe0A = equation.clone();
			moe0AZ = equation.clone();//correct
			MOEquation moe1A = equation.clone();
			MOEquation moe1AZ = equation.clone();
			moe0A.setHandSide(new MONumber(0),atLeft);
			moe0AZ.setHandSide(new MONumber(0),atLeft);
			moe1A.setHandSide(new MONumber(1),atLeft);
			moe1AZ.setHandSide(new MONumber(1),atLeft);
			if(whereElement instanceof MOAddContainer) {
				int index = (zoneH==MOElement.ZONE_OO)? 0 : ((MOAddContainer)whereElement).size();
				((MOAddContainer)moe0A.getHandSide(!atLeft)).add(index, mose.clone());
				((MOAddContainer)moe0AZ.getHandSide(!atLeft)).add(index, moseZ.clone());
				((MOAddContainer)moe1A.getHandSide(!atLeft)).add(index, mose.clone());
				((MOAddContainer)moe1AZ.getHandSide(!atLeft)).add(index, moseZ.clone());
			}
			else if(zoneH==MOElement.ZONE_OO) {
				moe0A.setHandSide(new MOAddContainer( mose.clone(), new MOSignedElement(moe0A.getHandSide(!atLeft)) ),!atLeft);
				moe0AZ.setHandSide(new MOAddContainer( moseZ.clone(), new MOSignedElement(moe0AZ.getHandSide(!atLeft)) ),!atLeft);
				moe1A.setHandSide(new MOAddContainer( mose.clone(), new MOSignedElement(moe1A.getHandSide(!atLeft)) ),!atLeft);
				moe1AZ.setHandSide(new MOAddContainer( moseZ.clone(), new MOSignedElement(moe1AZ.getHandSide(!atLeft)) ),!atLeft);
			}
			else {
				moe0A.setHandSide(new MOAddContainer( new MOSignedElement(moe0A.getHandSide(!atLeft)), mose.clone() ), !atLeft);
				moe0AZ.setHandSide(new MOAddContainer( new MOSignedElement(moe0AZ.getHandSide(!atLeft)), moseZ.clone() ), !atLeft);
				moe1A.setHandSide(new MOAddContainer( new MOSignedElement(moe1A.getHandSide(!atLeft)), mose.clone() ), !atLeft);
				moe1AZ.setHandSide(new MOAddContainer( new MOSignedElement(moe1AZ.getHandSide(!atLeft)), moseZ.clone() ), !atLeft);
			}

			MOEquation moe0M = equation.clone();
			MOEquation moe0MZ = equation.clone();//correct
			MOEquation moe1M = equation.clone();
			moe1MZ = equation.clone();
			moe0M.setHandSide(new MONumber(0),atLeft);
			moe0MZ.setHandSide(new MONumber(0),atLeft);
			moe1M.setHandSide(new MONumber(1),atLeft);
			moe1MZ.setHandSide(new MONumber(1),atLeft);
			if(whereElement instanceof MOMultiplyContainer) {
				int index = (zoneH==MOElement.ZONE_OO)? 0 : ((MOMultiplyContainer)whereElement).size();
				((MOMultiplyContainer)moe0M.getHandSide(!atLeft)).add(index, mome.clone());
				((MOMultiplyContainer)moe0MZ.getHandSide(!atLeft)).add(index, momeZ.clone());
				((MOMultiplyContainer)moe1M.getHandSide(!atLeft)).add(index, mome.clone());
				((MOMultiplyContainer)moe1MZ.getHandSide(!atLeft)).add(index, momeZ.clone());
			}
			else if(zoneH==MOElement.ZONE_OO) {
				moe0M.setHandSide(new MOMultiplyContainer( mome.clone(), new MOMultiplyElement(moe0M.getHandSide(!atLeft)) ),!atLeft);
				moe0MZ.setHandSide(new MOMultiplyContainer( momeZ.clone(), new MOMultiplyElement(moe0MZ.getHandSide(!atLeft)) ),!atLeft);
				moe1M.setHandSide(new MOMultiplyContainer( mome.clone(), new MOMultiplyElement(moe1M.getHandSide(!atLeft)) ),!atLeft);
				moe1MZ.setHandSide(new MOMultiplyContainer( momeZ.clone(), new MOMultiplyElement(moe1MZ.getHandSide(!atLeft)) ),!atLeft);
			}
			else {
				moe0M.setHandSide(new MOMultiplyContainer( new MOMultiplyElement(moe0M.getHandSide(!atLeft)), mome.clone() ), !atLeft);
				moe0MZ.setHandSide(new MOMultiplyContainer( new MOMultiplyElement(moe0MZ.getHandSide(!atLeft)), momeZ.clone() ), !atLeft);
				moe1M.setHandSide(new MOMultiplyContainer( new MOMultiplyElement(moe1M.getHandSide(!atLeft)), mome.clone() ), !atLeft);
				moe1MZ.setHandSide(new MOMultiplyContainer( new MOMultiplyElement(moe1MZ.getHandSide(!atLeft)), momeZ.clone() ), !atLeft);
			}

			questionButton.addAnswer(moe0A, 0);
			questionButton.addAnswer(moe0AZ, 1);
			questionButton.addAnswer(moe0M, 0);
			questionButton.addAnswer(moe0MZ, 0);
			questionButton.addAnswer(moe1A, 0);
			questionButton.addAnswer(moe1AZ, 0);
			questionButton.addAnswer(moe1M, 0);
			questionButton.addAnswer(moe1MZ, 2);
		}			

		questionButton.center();
//		good.setStyleClass(MOElement.STYLE_CLASS_FOCUS);
	}
	
	@Override
	public void onExecuteProcess(int answer) {
		if(answer==1) {
			equation.setLeftHandSide(moe0AZ.getLeftHandSide());
			equation.setRightHandSide(moe0AZ.getRightHandSide());
		}
		else {
			equation.setLeftHandSide(moe1MZ.getLeftHandSide());
			equation.setRightHandSide(moe1MZ.getRightHandSide());
		}
	}

	@Override
	public MOWordingWidget getMessage(int answer) {
		if(answer==1)
			return new MOWordingWidget("Oui ! on a ",isMinus?"soustrait":"ajouté", selectedElement.clone()," des deux côtés du signe égal.<br/>"+
			"il reste donc ",new MOEquation(new MOAddContainer(mose.clone(),moseZ.clone()) , new MONumber(0))," du côté ",atLeft?"gauche":"droit",
			"<br/><em>c'est une des opérations qu'on sait faire sur une équation.</em>");
		else if(answer==2)
			return new MOWordingWidget("Oui ! on a divisé", selectedElement.clone()," des deux côtés du signe égal.<br/>"+
			"il reste donc ",new MOEquation(new MOMultiplyContainer(mome.clone(),momeZ.clone()) , new MONumber(1))," du côté ",atLeft?"gauche":"droit",
			"<br/><em>c'est une des opérations qu'on sait faire sur une équation.</em>");
		else
			return new MOWordingWidget("Attention ! Ici, on veut déplacer le terme d'une addition !<br/>" +
					"On a le droit de faire des opérations <u>des deux côtés du signe égal</u>... mais laquelle utiliser ?");
	}

}
