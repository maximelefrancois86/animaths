//package fr.upmf.animaths.client.interaction.process.core;
//
//import fr.upmf.animaths.client.interaction.process.MOAbstractProcess;
//import fr.upmf.animaths.client.mvp.MathObject.MOElement;
//import fr.upmf.animaths.client.mvp.MathObject.MOEquation;
//import fr.upmf.animaths.client.mvp.MathObject.MOMultiplyContainer;
//import fr.upmf.animaths.client.mvp.MathObject.MOMultiplyElement;
//
//public final class MEs_MC_E_ChangeHandSide extends MOAbstractProcess{
//
//	private static final MEs_MC_E_ChangeHandSide instance = new MEs_MC_E_ChangeHandSide();
//	protected MEs_MC_E_ChangeHandSide() {}
//	public static void setEnabled() {
//		MOAbstractProcess.setEnabled(instance);
//	}
//
//	private boolean atLeft;
//	private short zoneHOldEq = 100;
//	private short zoneHEq = 100;
//	private boolean okForDropping = false;
//	
//	private MOEquation equation;
//	private MOMultiplyContainer multiplyContainer;
//	
//	@Override
//	protected boolean isProcessInvolved() {
//		if(selectedElement instanceof MOMultiplyElement) {
//			MOElement<?> parentOfSelectedElement = selectedElement.getMathObjectParent();
//			if(parentOfSelectedElement instanceof MOMultiplyContainer) {
//				multiplyContainer = (MOMultiplyContainer) parentOfSelectedElement;
//				MOElement<?> greatParentOfSelectedElement = parentOfSelectedElement.getMathObjectParent();
//				if(greatParentOfSelectedElement instanceof MOEquation) {
//					equation = (MOEquation) greatParentOfSelectedElement;
//					atLeft = parentOfSelectedElement == ((MOEquation)greatParentOfSelectedElement).getLeftHandSide();
//					okForDropping = false;
//					return true;
//				}
//			}
//		}
//		return false;
//	}
//
//	@Override
//	protected int getPriorityOfProcess() {
//		if(whereElement==equation) {
//			zoneHOldEq = zoneHEq;
//			zoneHEq = zoneH;
//			boolean a = atLeft^okForDropping;
//			boolean b = zoneHOldEq<=MOElement.ZONE_CENTER && zoneHEq>=MOElement.ZONE_E;
//			boolean c = zoneHOldEq>=MOElement.ZONE_CENTER && zoneHEq<=MOElement.ZONE_O;
//			if(a&&b || !a&&c) {
//				coreInteraction.inverseSign();
//				okForDropping = !okForDropping;
//			}
//
//		}
////		if(okForDropping) {
////			if(whereElement==equation) {
////				//cas 1;
////				if(zoneV!=MOElement.ZONE_NNN && zoneV!=MOElement.ZONE_SSS 
////						&&( (atLeft && (zoneH==MOElement.ZONE_CENTER ||zoneH==MOElement.ZONE_EE))
////						 || (!atLeft && (zoneH==MOElement.ZONE_CENTER ||zoneH==MOElement.ZONE_OO)) ))
////					return 1;
////			}
////			else {
////				//cas 2;
////				MOElement<?> parentOfWhereElement = whereElement.getMathObjectParent();
////				if(parentOfWhereElement==equation && whereElement != multiplyContainer && (zoneH==MOElement.ZONE_EE || zoneH==MOElement.ZONE_OO))
////					return 1;
////				else {
////					//cas 3;
////					MOElement<?> greatParentOfWhereElement = parentOfWhereElement.getMathObjectParent();
////					if(greatParentOfWhereElement==equation
////						&& parentOfWhereElement instanceof MOMultiplyContainer
////						&& parentOfWhereElement != multiplyContainer
////						&& (zoneH==MOElement.ZONE_EE || zoneH==MOElement.ZONE_OO))
////						return 1;
////				}
////			}
////		}
//		return 0;
//	}
//
//	@Override
//	protected void executeProcess() {
////		multiplyContainer.remove((MOMultiplyElement) selectedElement);
////		assert multiplyContainer.size()>0;
////		// simplification...
////		if(multiplyContainer.size()==1)
////			if(atLeft) {
////				equation.setLeftHandSide(multiplyContainer.get(0));
////				if(equation.getLeftHandSide() instanceof MOMultiplyElement
////						&& !((MOMultiplyElement) equation.getLeftHandSide()).isMinus())
////					equation.setLeftHandSide(((MOMultiplyElement) equation.getLeftHandSide()).getChild());
////			}
////			else {
////				equation.setRightHandSide(multiplyContainer.get(0));
////				if(equation.getRightHandSide() instanceof MOMultiplyElement
////						&& !((MOMultiplyElement) equation.getRightHandSide()).isMinus())
////					equation.setRightHandSide(((MOMultiplyElement) equation.getRightHandSide()).getChild());
////			}
////		//cas 1;
////		if(whereElement==equation)
////			executeProcessInCase1();
////		else {
////			//cas 2;
////			MOElement<?> parentOfWhereElement = whereElement.getMathObjectParent();
////			if(parentOfWhereElement==equation)
////				executeProcessInCase2();
////			else {
////				MOElement<?> greatParentOfWhereElement = parentOfWhereElement.getMathObjectParent();
////				if(greatParentOfWhereElement==equation) {
////					assert parentOfWhereElement instanceof MOMultiplyContainer;
////					executeProcessInCase3(((MOMultiplyContainer) parentOfWhereElement));
////				}
////			}
////		}
//	}
//
////	private void executeProcessInCase1() {
////		MOMultiplyElement newElement = (MOMultiplyElement) coreInteraction.getCopiedPresenter().getElement();
////		MOElement<?> handSide = (atLeft) ? equation.getRightHandSide() : equation.getLeftHandSide();
////		if(handSide instanceof MOMultiplyContainer) {
////			if(zoneH==MOElement.ZONE_OO || (atLeft && zoneH==MOElement.ZONE_CENTER))
////				((MOMultiplyContainer) handSide).add(0,newElement);
////			else if(zoneH==MOElement.ZONE_EE || (!atLeft && zoneH==MOElement.ZONE_CENTER))
////				((MOMultiplyContainer) handSide).add(newElement);
////		}
////		else {
////			handSide = (handSide instanceof MOMultiplyElement)? handSide : new MOMultiplyElement(handSide);
////			if(zoneH==MOElement.ZONE_OO || (atLeft && zoneH==MOElement.ZONE_CENTER))
////				handSide = new MOMultiplyContainer(newElement, (MOMultiplyElement)handSide);
////			else if(zoneH==MOElement.ZONE_EE ||(!atLeft && zoneH==MOElement.ZONE_CENTER))
////				handSide = new MOMultiplyContainer((MOMultiplyElement)handSide,newElement);
////			if(atLeft)
////				equation.setRightHandSide(handSide);
////			else
////				equation.setLeftHandSide(handSide);
////		}
////	}
////	
////	private void executeProcessInCase2() {
////		MOMultiplyElement newElement = (MOMultiplyElement) coreInteraction.getCopiedPresenter().getElement();
//////		List<MOMultiplyElement> children;
////		if(whereElement instanceof MOMultiplyContainer) {
////			if(zoneH==MOElement.ZONE_EE)
////				((MOMultiplyContainer) whereElement).add(newElement);
////			else if(zoneH==MOElement.ZONE_OO)
////				((MOMultiplyContainer) whereElement).add(0,newElement);
////			else
////				System.out.println("erreur");
////		}
////		else {
////			MOMultiplyContainer handSide = new MOMultiplyContainer((whereElement instanceof MOMultiplyElement)? (MOMultiplyElement)whereElement : new MOMultiplyElement(whereElement));
////			if(zoneH==MOElement.ZONE_EE)
////				handSide.add(newElement);
////			else if(zoneH==MOElement.ZONE_OO)
////				handSide.add(0,newElement);
////			else
////				System.out.println("erreur");
////			if(atLeft)
////				equation.setRightHandSide(handSide);
////			else
////				equation.setLeftHandSide(handSide);
////		}
////	}
////	
////	private void executeProcessInCase3(MOMultiplyContainer multiplyContainer) {
////		MOMultiplyElement newElement = (MOMultiplyElement) coreInteraction.getCopiedPresenter().getElement();
////		multiplyContainer.add(newElement,(MOMultiplyElement) whereElement,zoneH==MOElement.ZONE_EE);
////	}
//}
