//package fr.upmf.animaths.client.interaction.process.core;
//
//import com.google.gwt.user.client.ui.FlowPanel;
//
//import fr.upmf.animaths.client.interaction.process.MOAbstractProcess;
//import fr.upmf.animaths.client.interaction.process.QuestionButton;
//import fr.upmf.animaths.client.mvp.MathWordingWidget;
//import fr.upmf.animaths.client.mvp.MathObject.MOAddContainer;
//import fr.upmf.animaths.client.mvp.MathObject.MOElement;
//import fr.upmf.animaths.client.mvp.MathObject.MOEquation;
//import fr.upmf.animaths.client.mvp.MathObject.MOIdentifier;
//import fr.upmf.animaths.client.mvp.MathObject.MOMultiplyContainer;
//import fr.upmf.animaths.client.mvp.MathObject.MOMultiplyElement;
//import fr.upmf.animaths.client.mvp.MathObject.MONumber;
//import fr.upmf.animaths.client.mvp.MathObject.MOSignedElement;
//
//public final class Ns_Is_E_ChangeHandSide extends MOAbstractProcess{
//
//	private static final Ns_Is_E_ChangeHandSide instance = new Ns_Is_E_ChangeHandSide();
//	protected Ns_Is_E_ChangeHandSide() {}
//	public static void setEnabled() {
//		MOAbstractProcess.setEnabled(instance);
//	}
//	
//	private MOEquation equation;
//	private boolean atLeft;	
//	private MOElement<?> otherHandSide;
//	
//	@Override
//	protected boolean isProcessInvolved() {
//		MOElement<?> parentOfSelectedElement = selectedElement.getMathObjectParent();
//		if( parentOfSelectedElement instanceof MOEquation
//				&& (selectedElement instanceof MONumber 
//						|| selectedElement instanceof MOIdentifier
//						|| selectedElement instanceof MOSignedElement)) {
//			equation = (MOEquation) parentOfSelectedElement;
//			atLeft = selectedElement == equation.getLeftHandSide();
//			otherHandSide = ((atLeft)?equation.getRightHandSide():equation.getLeftHandSide());
//			System.out.println("Ns_Is_E_ChangeHandSide : Interested");
//			return true;
//		}
//		return false;
//	}
//
//	@Override
//	protected short getTagOfProcess() {
//		if((zoneH==MOElement.ZONE_EE || zoneH==MOElement.ZONE_OO)) {
//			if(whereElement == otherHandSide)
//				return PROCESS_CAUTION;
//			else if(whereElement instanceof MOSignedElement) {
//				MOElement<?> parentOfWhereElement = whereElement.getMathObjectParent();
//				if(parentOfWhereElement instanceof MOAddContainer && parentOfWhereElement == otherHandSide) {
//					int index = ((MOAddContainer)parentOfWhereElement).indexOf((MOSignedElement)whereElement);
//					if(!(  (index==0 && zoneH==MOElement.ZONE_OO)
//							|| (index==((MOAddContainer)parentOfWhereElement).size()-1 && zoneH==MOElement.ZONE_EE)))
//					return PROCESS_CAUTION;
//				}
//			}
//			else if(whereElement instanceof MOMultiplyElement) {
//				MOMultiplyContainer parentOfWhereElement = (MOMultiplyContainer) whereElement.getMathObjectParent();
//				if(parentOfWhereElement == otherHandSide) {
//					int index = parentOfWhereElement.indexOf((MOMultiplyElement)whereElement);
//					if(!(  (index==0 && zoneH==MOElement.ZONE_OO)
//							|| (index==parentOfWhereElement.sizeOfNumerator()-1 && zoneH==MOElement.ZONE_EE)))
//					return PROCESS_CAUTION;
//				}
//			}
//		}
//		return PROCESS_NO;
//	}
//
//	@Override
//	public void askQuestion() {
//		System.out.println("SEs_AC_E_ChangeHandSide : askQuestion");
//
//		MathWordingWidget wording = new MathWordingWidget(new FlowPanel());		
//		wording.setWording("Après avoir déplacé ",selectedElement.clone()," de l'autre côté du signe égal, quel est le résultat ?");
//		QuestionButton questionButton = new QuestionButton(this, wording);
//		
//		MONumber zero = new MONumber(0);
//		MONumber one = new MONumber(1);
//
//		if(whereElement == otherHandSide) {
//			if(whereElement instanceof MOAddContainer) {
//				MOEquation good1 = equation.clone();
//				if(atLeft)
//					equation.setLeftHandSide(zero.clone());
//				else
//					equation.setRightHandSide(zero.clone());
//					
//				
//				good1.setMinus(!good1.isMinus());
//				MOAddContainer goodAnswer = parentOfWhere.clone();
//				goodAnswer.add(index,good);
//				questionButton.addAnswer(goodAnswer, true);
//
//				MOSignedElement good = selected.clone();
//				good.setMinus(!good.isMinus());
//				MOAddContainer goodAnswer = parentOfWhere.clone();
//				goodAnswer.add(index,good);
//				questionButton.addAnswer(goodAnswer, true);
//
//				MOSignedElement good = selected.clone();
//				good.setMinus(!good.isMinus());
//				MOAddContainer goodAnswer = parentOfWhere.clone();
//				goodAnswer.add(index,good);
//				questionButton.addAnswer(goodAnswer, true);
//
//				MOSignedElement good = selected.clone();
//				good.setMinus(!good.isMinus());
//				MOAddContainer goodAnswer = parentOfWhere.clone();
//				goodAnswer.add(index,good);
//				questionButton.addAnswer(goodAnswer, true);
//			}			
//			else if(whereElement instanceof MOMultiplyContainer)
//				return;
//			else
//				return;
//			
//			if(zoneH==MOElement.ZONE_OO)
//				return;
//			else
//				return;
//				
//			if(selectedElement instanceof MOSignedElement)
//				return;
//			else
//				return;
//				
//				
//			MOSignedElement good1 = selected.clone();
//			MOSignedElement good2 = selected.clone();
//			MOSignedElement good3 = selected.clone();
//			
//		}
//		else if(whereElement instanceof MOSignedElement) {
//			
//			int index = otherHandSide.indexOf(whereElement);
//			assert index!=-1;
//			if(zoneH==MOElement.ZONE_EE)
//				index++;
//			
//			
//		}
//		else if(whereElement instanceof MOMultiplyElement) {
//			
//		}
//		
//		
//		
//		
//		MOSignedElement good = selected.clone();
//		good.setMinus(!good.isMinus());
//		MOAddContainer goodAnswer = parentOfWhere.clone();
//		goodAnswer.add(index,good);
//		questionButton.addAnswer(goodAnswer, true);
//
//		MOSignedElement bad = selected.clone();
//		MOAddContainer badAnswer = parentOfWhere.clone();
//		badAnswer.add(index,bad);
//		questionButton.addAnswer(badAnswer, false);	
//		
//		questionButton.center();
//		good.setStyleClass(MOElement.STYLE_CLASS_FOCUS);
//		bad.setStyleClass(MOElement.STYLE_CLASS_FOCUS);
//	}
//	
//	@Override
//	public void onExecuteProcess() {
//		addContainer.remove(selected);
//		assert addContainer.size()>0;
//		// simplification...
//		if(addContainer.size()==1)
//			if(atLeft) {
//				equation.setLeftHandSide(addContainer.get(0));
//				if(equation.getLeftHandSide() instanceof MOSignedElement
//						&& !((MOSignedElement) equation.getLeftHandSide()).isMinus())
//					equation.setLeftHandSide(((MOSignedElement) equation.getLeftHandSide()).getChild());
//			}
//			else {
//				equation.setRightHandSide(addContainer.get(0));
//				if(equation.getRightHandSide() instanceof MOSignedElement
//						&& !((MOSignedElement) equation.getRightHandSide()).isMinus())
//					equation.setRightHandSide(((MOSignedElement) equation.getRightHandSide()).getChild());
//			}
//		selected.setMinus(!selected.isMinus());
//		parentOfWhere.add(selected,where,zoneH==MOElement.ZONE_EE);
//	}
//
//}
