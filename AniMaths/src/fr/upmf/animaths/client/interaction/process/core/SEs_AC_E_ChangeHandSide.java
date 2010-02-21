package fr.upmf.animaths.client.interaction.process.core;

import fr.upmf.animaths.client.interaction.process.MOAbstractProcess;
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

	private boolean atLeft;
	private short zoneHOldEq = 100;
	private short zoneHEq = 100;
	private boolean okForDropping = false;
	
	private MOEquation equation;
	private MOAddContainer addContainer;
	
	@Override
	protected boolean isProcessInvolved() {
		if(selectedElement instanceof MOSignedElement) {
			MOElement<?> parentOfSelectedElement = selectedElement.getMathObjectParent();
			if(parentOfSelectedElement instanceof MOAddContainer) {
				addContainer = (MOAddContainer) parentOfSelectedElement;
				MOElement<?> greatParentOfSelectedElement = parentOfSelectedElement.getMathObjectParent();
				if(greatParentOfSelectedElement instanceof MOEquation) {
					equation = (MOEquation) greatParentOfSelectedElement;
					atLeft = parentOfSelectedElement == ((MOEquation)greatParentOfSelectedElement).getLeftHandSide();
					okForDropping = false;
					return true;
				}
			}
		}
		return false;
	}

	@Override
	protected int getPriorityOfProcess() {
		if(whereElement==equation) {
			zoneHOldEq = zoneHEq;
			zoneHEq = zoneH;
			boolean a = atLeft^okForDropping;
			boolean b = zoneHOldEq<=MOElement.ZONE_CENTER && zoneHEq>=MOElement.ZONE_E;
			boolean c = zoneHOldEq>=MOElement.ZONE_CENTER && zoneHEq<=MOElement.ZONE_O;
			if(a&&b || !a&&c) {
//				coreInteraction.changeSign();
				okForDropping = !okForDropping;
			}

		}
		if(okForDropping) {
			if(whereElement==equation) {
				//cas 1;
				if(zoneV!=MOElement.ZONE_NNN && zoneV!=MOElement.ZONE_SSS 
						&&( (atLeft && (zoneH==MOElement.ZONE_CENTER ||zoneH==MOElement.ZONE_EE))
						 || (!atLeft && (zoneH==MOElement.ZONE_CENTER ||zoneH==MOElement.ZONE_OO)) ))
					return 1;
			}
			else {
				//cas 2;
				MOElement<?> parentOfWhereElement = whereElement.getMathObjectParent();
				if(parentOfWhereElement==equation && whereElement != addContainer && (zoneH==MOElement.ZONE_EE || zoneH==MOElement.ZONE_OO))
					return 1;
				else {
					//cas 3;
					MOElement<?> greatParentOfWhereElement = parentOfWhereElement.getMathObjectParent();
					if(greatParentOfWhereElement==equation
						&& parentOfWhereElement instanceof MOAddContainer
						&& parentOfWhereElement != addContainer
						&& (zoneH==MOElement.ZONE_EE || zoneH==MOElement.ZONE_OO))
						return 1;
				}
			}
		}
		return 0;
	}

	@Override
	protected short getTagOfProcess() {
		return PROCESS_CAUTION;
	}

	@Override
	protected void executeProcess() {
		addContainer.remove((MOSignedElement) selectedElement);
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
		//cas 1;
		if(whereElement==equation)
			executeProcessInCase1();
		else {
			//cas 2;
			MOElement<?> parentOfWhereElement = whereElement.getMathObjectParent();
			if(parentOfWhereElement==equation)
				executeProcessInCase2();
			else {
				MOElement<?> greatParentOfWhereElement = parentOfWhereElement.getMathObjectParent();
				if(greatParentOfWhereElement==equation) {
					assert parentOfWhereElement instanceof MOAddContainer;
					executeProcessInCase3(((MOAddContainer) parentOfWhereElement));
				}
			}
		}
	}

	private void executeProcessInCase1() {
		MOSignedElement newElement = (MOSignedElement) coreInteraction.getCopiedPresenter().getElement();
		MOElement<?> handSide = (atLeft) ? equation.getRightHandSide() : equation.getLeftHandSide();
		if(handSide instanceof MOAddContainer) {
			if(zoneH==MOElement.ZONE_OO || (atLeft && zoneH==MOElement.ZONE_CENTER))
				((MOAddContainer) handSide).add(0,newElement);
			else if(zoneH==MOElement.ZONE_EE || (!atLeft && zoneH==MOElement.ZONE_CENTER))
				((MOAddContainer) handSide).add(newElement);
		}
		else {
			handSide = (handSide instanceof MOSignedElement)? handSide : new MOSignedElement(handSide);
			if(zoneH==MOElement.ZONE_OO || (atLeft && zoneH==MOElement.ZONE_CENTER))
				handSide = new MOAddContainer(newElement, (MOSignedElement)handSide);
			else if(zoneH==MOElement.ZONE_EE ||(!atLeft && zoneH==MOElement.ZONE_CENTER))
				handSide = new MOAddContainer((MOSignedElement)handSide,newElement);
			if(atLeft)
				equation.setRightHandSide(handSide);
			else
				equation.setLeftHandSide(handSide);
		}
	}
	
	private void executeProcessInCase2() {
		MOSignedElement newElement = (MOSignedElement) coreInteraction.getCopiedPresenter().getElement();
//		List<MOSignedElement> children;
		if(whereElement instanceof MOAddContainer) {
			if(zoneH==MOElement.ZONE_EE)
				((MOAddContainer) whereElement).add(newElement);
			else if(zoneH==MOElement.ZONE_OO)
				((MOAddContainer) whereElement).add(0,newElement);
			else
				System.out.println("erreur");
		}
		else {
			MOAddContainer handSide = new MOAddContainer((whereElement instanceof MOSignedElement)? (MOSignedElement)whereElement : new MOSignedElement(whereElement));
			if(zoneH==MOElement.ZONE_EE)
				handSide.add(newElement);
			else if(zoneH==MOElement.ZONE_OO)
				handSide.add(0,newElement);
			else
				System.out.println("erreur");
			if(atLeft)
				equation.setRightHandSide(handSide);
			else
				equation.setLeftHandSide(handSide);
		}
	}
	
	private void executeProcessInCase3(MOAddContainer addContainer) {
		MOSignedElement newElement = (MOSignedElement) coreInteraction.getCopiedPresenter().getElement();
		addContainer.add(newElement,(MOSignedElement) whereElement,zoneH==MOElement.ZONE_EE);
	}
}
