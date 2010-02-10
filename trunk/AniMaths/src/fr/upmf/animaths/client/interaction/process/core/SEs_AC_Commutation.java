package fr.upmf.animaths.client.interaction.process.core;

import java.util.List;

import fr.upmf.animaths.client.interaction.process.MathObjectProcess;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectAddContainerPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectElementPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectSignedElementPresenter;

public final class SEs_AC_Commutation extends MathObjectProcess{

	private static final SEs_AC_Commutation instance = new SEs_AC_Commutation();
	protected SEs_AC_Commutation() {}
	public static void setEnabled() {
		MathObjectProcess.setEnabled(instance);
	}

	private MathObjectSignedElementPresenter selected;
	private MathObjectAddContainerPresenter parentOfSelected;
	
	@Override
	protected boolean isProcessInvolved() {
		if(selectedElement instanceof MathObjectSignedElementPresenter) {
			MathObjectElementPresenter<?> parentOfSelectedElement = selectedElement.getMathObjectParent();
			if(parentOfSelectedElement instanceof MathObjectAddContainerPresenter) {
				selected = (MathObjectSignedElementPresenter) selectedElement;
				parentOfSelected = (MathObjectAddContainerPresenter) parentOfSelectedElement;
				return true;
			}
		}
		return false;
	}

	@Override
	protected int getPriorityOfProcess() {
		if(parentOfSelected == whereElement.getMathObjectParent()
			&& (zoneH==MathObjectElementPresenter.ZONE_OO || zoneH==MathObjectElementPresenter.ZONE_EE))
			return 1;
		return 0;
	}

	@Override
	protected void executeProcess() {
		assert whereElement instanceof MathObjectSignedElementPresenter;
		List<MathObjectSignedElementPresenter> children = parentOfSelected.getChildren();
		children.remove(children.indexOf(selected));
		int indexOfWhere = children.indexOf(whereElement);
		assert indexOfWhere != -1;
		if(zoneH==MathObjectElementPresenter.ZONE_EE)
			indexOfWhere++;
		children.add(indexOfWhere,selected);
	}
}
