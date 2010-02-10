//package fr.upmf.animaths.client.interaction.process.core;
//
//import java.util.List;
//
//import fr.upmf.animaths.client.interaction.SelectionElement;
//import fr.upmf.animaths.client.interaction.process.MathObjectProcess;
//import fr.upmf.animaths.client.interaction.process.event.DragSelectedEvent;
//import fr.upmf.animaths.client.interaction.process.event.DropSelectedEvent;
//import fr.upmf.animaths.client.interaction.process.event.GrabSelectedEvent;
//import fr.upmf.animaths.client.mvp.MathObjectDynamicPresenter;
//import fr.upmf.animaths.client.mvp.MathObjectStaticPresenter;
//import fr.upmf.animaths.client.mvp.MathObject.MathObjectAddContainerPresenter;
//import fr.upmf.animaths.client.mvp.MathObject.MathObjectElementPresenter;
//import fr.upmf.animaths.client.mvp.MathObject.MathObjectEquationPresenter;
//import fr.upmf.animaths.client.mvp.MathObject.MathObjectSignedElementPresenter;
//
//public final class SEs_AC_E_ChangeHandSide extends MathObjectProcess{
//
//	// instancier 'instance' au chargement de la classe
//	private static final SEs_AC_E_ChangeHandSide instance = new SEs_AC_E_ChangeHandSide();
//	public static final int priority = 0;
//	protected SEs_AC_E_ChangeHandSide() {}
//	public static void setEnabled() {
//		MathObjectProcess.setEnabled(instance);
//	}
//
//	private SelectionElement selectionElement;
//	private MathObjectDynamicPresenter presenter;
//
//	private MathObjectSignedElementPresenter selected;
//	private MathObjectAddContainerPresenter parentOfSelected;
//	private MathObjectEquationPresenter equation;
//	
//	private MathObjectSignedElementPresenter where;
//	private MathObjectAddContainerPresenter parentOfWhere;
//	private short zoneHOld = -1;
//	private short zoneH = -1;
//
//	@Override
//	public void onGrabSelected(GrabSelectedEvent event) {
//		selectionElement = event.getSelectionElement();
//		presenter = event.getSelectionElement().getPresenter();
//		MathObjectElementPresenter<?> selected = selectionElement.getSelectedElement();
//		MathObjectElementPresenter<?> parentOfSelected = selected.getMathObjectParent();
//		MathObjectElementPresenter<?> greatParentOfSelected = parentOfSelected.getMathObjectParent();
//		if(selected instanceof MathObjectSignedElementPresenter
//				&& parentOfSelected instanceof MathObjectAddContainerPresenter
//			&& greatParentOfSelected instanceof MathObjectEquationPresenter) {
//			this.selected = (MathObjectSignedElementPresenter) selected;
//			this.parentOfSelected = (MathObjectAddContainerPresenter) parentOfSelected;
//			this.equation = (MathObjectEquationPresenter) greatParentOfSelected;
//			setHandler(DragSelectedEvent.getType());
//			selectionElement.setProcessFound();
//		}
//	}
//
//	@Override
//	public void onDragSelected(DragSelectedEvent event) {
//		MathObjectElementPresenter<?> where = event.getWhereElement();
//		MathObjectElementPresenter<?> parentOfWhere = where.getMathObjectParent();
//		MathObjectElementPresenter<?> greatParentOfWhere = parentOfWhere.getMathObjectParent();
//		zoneHOld = zoneH;
//		zoneH = event.getZoneH();
//		if(where instanceof MathObjectEquationPresenter
//				&& (zoneHOld==MathObjectElementPresenter.ZONE_CENTER && zoneH==MathObjectElementPresenter.ZONE_E 
//						||zoneHOld==MathObjectElementPresenter.ZONE_CENTER && zoneH==MathObjectElementPresenter.ZONE_O)) {
//			MathObjectStaticPresenter copiedPresenter = selectionElement.getCopiedPresenter();
//			MathObjectSignedElementPresenter copiedElement = ((MathObjectSignedElementPresenter) copiedPresenter.getElement());
//			copiedElement.setMinus(!copiedElement.isMinus());
//			copiedPresenter.setElement(copiedElement);
//		}
//		if(equation == greatParentOfWhere
//			&& parentOfSelected != parentOfWhere
//			&& parentOfWhere instanceof MathObjectAddContainerPresenter
//			&& where instanceof MathObjectSignedElementPresenter) {
//			this.where = (MathObjectSignedElementPresenter) where;
//			this.parentOfWhere = (MathObjectAddContainerPresenter) parentOfWhere;
//			if(zoneH==MathObjectElementPresenter.ZONE_OO || zoneH==MathObjectElementPresenter.ZONE_EE) {
//				setHandler(DropSelectedEvent.getType());
//				selectionElement.setPriorityOfProcess(priority);
//			}
//		}
//	}
//
//	@Override
//	public void onDropSelected(DropSelectedEvent event) {
//		removeHandler(DragSelectedEvent.getType());
//		removeHandler(DropSelectedEvent.getType());
//		if(selectionElement.isProcessDone() || selectionElement.getGreatestPriorityFound()!=priority)
//			return;
//		List<MathObjectSignedElementPresenter> childrenS = parentOfSelected.getChildren();
//		List<MathObjectSignedElementPresenter> childrenW = parentOfWhere.getChildren();
//		childrenS.remove(childrenS.indexOf(selected));
//		int indexOfWhere = childrenW.indexOf(where);
//		if(zoneH==MathObjectElementPresenter.ZONE_EE)
//			indexOfWhere++;
//		selected.setMinus(!selected.isMinus());
//		childrenW.add(indexOfWhere,selected);
//		presenter.setElement(presenter.getElement());
//		selectionElement.setProcessDone();
//	}
//
//}
