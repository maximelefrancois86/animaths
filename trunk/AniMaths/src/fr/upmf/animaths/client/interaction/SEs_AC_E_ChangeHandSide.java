package fr.upmf.animaths.client.interaction;

import com.google.gwt.user.client.ui.FlowPanel;

import fr.upmf.animaths.client.mvp.MOWordingWidget;
import fr.upmf.animaths.client.mvp.MathObject.MOAddContainer;
import fr.upmf.animaths.client.mvp.MathObject.MOElement;
import fr.upmf.animaths.client.mvp.MathObject.MOEquation;
import fr.upmf.animaths.client.mvp.MathObject.MOMultiplyContainer;
import fr.upmf.animaths.client.mvp.MathObject.MOMultiplyElement;
import fr.upmf.animaths.client.mvp.MathObject.MOSignedElement;

public final class SEs_AC_E_ChangeHandSide extends AniMathAbstractProcess{

	private static final SEs_AC_E_ChangeHandSide instance = new SEs_AC_E_ChangeHandSide();
	protected SEs_AC_E_ChangeHandSide() {}
	public static void setEnabled(boolean enabled) {
		AniMathAbstractProcess.setEnabled(instance, enabled);
	}
	
	private MOEquation equation;
	private MOAddContainer addContainer;
	private MOSignedElement selected;
	private boolean atLeft;	
	private boolean isMinus;
	private MOAddContainer parentOfWhere;
	private MOSignedElement where;
	private MOEquation moeAZ;
	
	
	
	@Override
	protected boolean isProcessInvolved() {
		if(selectedElement instanceof MOSignedElement) {
			selected = (MOSignedElement) selectedElement;
			isMinus = selected.isMinus();
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
		if(whereElement==equation.getHandSide(!atLeft)
				&& (zoneH==MOElement.ZONE_EE || zoneH==MOElement.ZONE_OO)) {
				return PROCESS_CAUTION;
			}
		return PROCESS_NO;
	}

	@Override
	public void onAskQuestion() {
		System.out.println("SEs_AC_E_ChangeHandSide : askQuestion");
		MOWordingWidget wording = new MOWordingWidget(new FlowPanel());		
		wording.setWording("Après avoir déplacé ",selectedElement.clone()," de l'autre côté du signe égal, quel est le résultat ?");
		AniMathQuestionButton aniMathQuestionButton = new AniMathQuestionButton(this, wording);
		
		if(whereElement.getMathObjectParent() instanceof MOAddContainer) {
			int index = parentOfWhere.indexOf(where);
			assert index!=-1;
			if(zoneH==MOElement.ZONE_EE)
				index++;
			
			MOSignedElement good = selected.clone();
			good.setMinus(!good.isMinus());
			MOAddContainer goodAnswer = parentOfWhere.clone();
			goodAnswer.add(index,good);
			aniMathQuestionButton.addAnswer(goodAnswer, 1);
	
			MOSignedElement bad = selected.clone();
			MOAddContainer badAnswer = parentOfWhere.clone();
			badAnswer.add(index,bad);
			aniMathQuestionButton.addAnswer(badAnswer, 0);	
			
			aniMathQuestionButton.center();
			good.setStyleClass(MOElement.STYLE_CLASS_FOCUS);
			bad.setStyleClass(MOElement.STYLE_CLASS_FOCUS);
		}
		else if(whereElement==equation.getHandSide(!atLeft)) {
			MOSignedElement mose = selected.clone(); 
			MOSignedElement moseZ; (moseZ=mose.clone()).setMinus(!mose.isMinus());
			MOMultiplyElement mome = new MOMultiplyElement(selected.isMinus()?selected.clone():selected.getChild().clone());
			MOMultiplyElement momeZ; (momeZ=mome.clone()).setDivided(!mome.isDivided());
			
			int index = addContainer.indexOf(selected);

			MOEquation moeA = equation.clone();
			moeAZ = equation.clone();  // correct
			MOElement<?> handSide = this.addContainer.clone();
			((MOAddContainer)handSide).remove(index);
			// simplification...
			if(((MOAddContainer)handSide).size()==1)
				handSide = ((MOAddContainer)handSide).get(0).isMinus()? ((MOAddContainer)handSide).get(0):((MOAddContainer)handSide).get(0).getChild();
			moeA.setHandSide(handSide.clone(),atLeft);
			moeAZ.setHandSide(handSide,atLeft);
			if(zoneH==MOElement.ZONE_OO) {
				moeA.setHandSide(new MOAddContainer( mose, new MOSignedElement(moeA.getHandSide(!atLeft)) ),!atLeft);
				moeAZ.setHandSide(new MOAddContainer( moseZ, new MOSignedElement(moeAZ.getHandSide(!atLeft)) ),!atLeft);
			}
			else {
				moeA.setHandSide(new MOAddContainer( new MOSignedElement(moeA.getHandSide(!atLeft)), mose ), !atLeft);
				moeAZ.setHandSide(new MOAddContainer( new MOSignedElement(moeAZ.getHandSide(!atLeft)), moseZ ), !atLeft);
			}

			MOEquation moeM = equation.clone();
			MOEquation moeMZ = equation.clone();
			moeM.setHandSide(handSide.clone(),atLeft);
			moeMZ.setHandSide(handSide.clone(),atLeft);
			if(whereElement instanceof MOMultiplyContainer) {
				index = (zoneH==MOElement.ZONE_OO)? 0 : ((MOMultiplyContainer)whereElement).size();
				((MOMultiplyContainer)moeM.getHandSide(!atLeft)).add(index, mome);
				((MOMultiplyContainer)moeMZ.getHandSide(!atLeft)).add(index, momeZ);
			}
			else if(zoneH==MOElement.ZONE_OO) {
				moeM.setHandSide(new MOMultiplyContainer( mome, new MOMultiplyElement(moeM.getHandSide(!atLeft)) ),!atLeft);
				moeMZ.setHandSide(new MOMultiplyContainer( momeZ, new MOMultiplyElement(moeMZ.getHandSide(!atLeft)) ),!atLeft);
			}
			else {
				moeM.setHandSide(new MOMultiplyContainer( new MOMultiplyElement(moeM.getHandSide(!atLeft)), mome ), !atLeft);
				moeMZ.setHandSide(new MOMultiplyContainer( new MOMultiplyElement(moeMZ.getHandSide(!atLeft)), momeZ ), !atLeft);
			}

			aniMathQuestionButton.addAnswer(moeA, 0);
			aniMathQuestionButton.addAnswer(moeAZ, 2);
			aniMathQuestionButton.addAnswer(moeM, 0);
			aniMathQuestionButton.addAnswer(moeMZ, 0);
			
			aniMathQuestionButton.center();
			mose.setStyleClass(MOElement.STYLE_CLASS_FOCUS);
			moseZ.setStyleClass(MOElement.STYLE_CLASS_FOCUS);
			mome.setStyleClass(MOElement.STYLE_CLASS_FOCUS);
			momeZ.setStyleClass(MOElement.STYLE_CLASS_FOCUS);
		}
	}
	
	@Override
	public void onExecuteProcess(int answer) {
		if(whereElement.getMathObjectParent() instanceof MOAddContainer) {
			addContainer.remove(selected);
			assert addContainer.size()>0;
			// simplification...
			if(addContainer.size()==1)
				equation.setHandSide(addContainer.get(0).isMinus()?addContainer.get(0):addContainer.get(0).getChild(),atLeft);
			selected.setMinus(!selected.isMinus());
			parentOfWhere.add(selected,where,zoneH==MOElement.ZONE_EE);
		}
		else if(whereElement==equation.getHandSide(!atLeft)) {
			equation.setLeftHandSide(moeAZ.getLeftHandSide());
			equation.setRightHandSide(moeAZ.getRightHandSide());
		}
	}

	@Override
	public MOWordingWidget getMessage(int answer) {
		if(answer==1)
			return new MOWordingWidget("Oui ! on a ",isMinus?"ajouté ":"soustrait ",selected.getChild().clone()," des deux côtés du signe égal.<br/>"+
					"<em>c'est une des opérations qu'on sait faire sur une équation.</em><br/>"+
					"Ensuite, on a le droit de placer ",selected.clone().setMinus(!selected.isMinus())," où on veut dans l'addition ! <br/>"+
					"<em>c'est la commutation dans l'addition !</em>");
		else if(answer==2)
			return new MOWordingWidget("Oui ! on a ",isMinus?"ajouté ":"soustrait ",selected.getChild().clone()," des deux côtés du signe égal.<br/>"+
					"<em>c'est une des opérations qu'on sait faire sur une équation.</em>");
		else
			return new MOWordingWidget("Attention ! Ici, on veut déplacer le terme d'une addition !<br/>" +
					"On a le droit de faire des opérations <u>des deux côtés du signe égal</u>... mais laquelle utiliser ?");
	}

}
