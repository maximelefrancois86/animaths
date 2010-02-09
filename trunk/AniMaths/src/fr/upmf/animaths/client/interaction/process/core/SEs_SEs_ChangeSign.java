package fr.upmf.animaths.client.interaction.process.core;

import fr.upmf.animaths.client.interaction.SelectionElement;
import fr.upmf.animaths.client.interaction.process.MathObjectProcess;
import fr.upmf.animaths.client.interaction.process.event.DragSelectedEvent;
import fr.upmf.animaths.client.interaction.process.event.DropSelectedEvent;
import fr.upmf.animaths.client.interaction.process.event.GrabSelectedEvent;
import fr.upmf.animaths.client.mvp.MathObjectDynamicPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectElementPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectMultiplyElementPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectSignedElementPresenter;

public final class SEs_SEs_ChangeSign extends MathObjectProcess{

	// instancier 'instance' au chargement de la classe
	private static final SEs_SEs_ChangeSign instance = new SEs_SEs_ChangeSign();
	protected SEs_SEs_ChangeSign() {}
	public static void setEnabled() {
		MathObjectProcess.setEnabled(instance);
	}

	private SelectionElement selectionElement;
	private MathObjectDynamicPresenter presenter;

	private MathObjectSignedElementPresenter childOfSelected;
	private MathObjectSignedElementPresenter selected;
	private MathObjectSignedElementPresenter parentOfSelected;
	
	private MathObjectSignedElementPresenter where;

	@Override
	public void onGrabSelected(GrabSelectedEvent event) {
		selectionElement = event.getSelectionElement();
		presenter = event.getSelectionElement().getPresenter();
		boolean ok = false;
		MathObjectElementPresenter<?> selected = selectionElement.getSelectedElement();
		if(selected instanceof MathObjectSignedElementPresenter) {
			this.selected = (MathObjectSignedElementPresenter) selected;
			MathObjectElementPresenter<?> childOfSelected = selected.getMathObjectParent();
			if(childOfSelected instanceof MathObjectSignedElementPresenter) {
				this.childOfSelected = (MathObjectSignedElementPresenter) this.childOfSelected;
				ok = true;
			}
			MathObjectElementPresenter<?> parentOfSelected = selected.getMathObjectParent();
			if(parentOfSelected instanceof MathObjectSignedElementPresenter) {
				this.parentOfSelected = (MathObjectSignedElementPresenter) this.parentOfSelected;
				ok = true;
			}
		}
		if(selected instanceof MathObjectMultiplyElementPresenter) {
			MathObjectElementPresenter<?> child = ((MathObjectMultiplyElementPresenter) selected).getChild();
			if(child instanceof MathObjectSignedElementPresenter) {
				MathObjectElementPresenter<?> greatChild = ((MathObjectSignedElementPresenter) child).getChild();
				if(greatChild instanceof MathObjectSignedElementPresenter) {
					this.selected = (MathObjectSignedElementPresenter) child;
					this.childOfSelected = (MathObjectSignedElementPresenter) greatChild;
					ok = true;
				}
			}
		}
		if(ok) {
			setHandler(DragSelectedEvent.getType());
			selectionElement.setProcessFound(true);
			return;
		}
		removeHandler(DragSelectedEvent.getType());

	}

	@Override
	public void onDragSelected(DragSelectedEvent event) {
		MathObjectElementPresenter<?> element = event.getWhereElement();
		if(!selectionElement.getProcessFound() 
				&& element instanceof MathObjectSignedElementPresenter) {
			where = (MathObjectSignedElementPresenter) element;
			if(where.getChild()==selected || where.getMathObjectParent()==selected) {
				setHandler(DropSelectedEvent.getType());
				selectionElement.setProcessFound(true);
				return;
			}
		}
		removeHandler(DropSelectedEvent.getType());
	}

	@Override
	public void onDropSelected(DropSelectedEvent event) {
		removeHandler(DragSelectedEvent.getType());
		removeHandler(DropSelectedEvent.getType());
		if(where.getChild()==selected) {
			where.setMinus(where.isMinus()^selected.isMinus());
			where.setChild(selected.getChild());
		}	
		else if(where.getMathObjectParent()==selected) {
			selected.setMinus(where.isMinus()^selected.isMinus());
			selected.setChild(where.getChild());
		}
		presenter.setElement(presenter.getElement());
	}

}
