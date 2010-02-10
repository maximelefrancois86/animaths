package fr.upmf.animaths.client.interaction.process.core;

import java.util.List;

import fr.upmf.animaths.client.interaction.process.MathObjectProcess;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectElementPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectMultiplyContainerPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectMultiplyElementPresenter;

public class MEs_MC_Commutation extends MathObjectProcess{

	private static final MEs_MC_Commutation instance = new MEs_MC_Commutation();
	protected MEs_MC_Commutation() {}
	public static void setEnabled() {
		MathObjectProcess.setEnabled(instance);
	}

	private MathObjectMultiplyElementPresenter selected;
	private MathObjectMultiplyContainerPresenter parentOfSelected;
	private boolean atNum;
	
	@Override
	protected boolean isProcessInvolved() {
		if(selectedElement instanceof MathObjectMultiplyElementPresenter) {
			MathObjectElementPresenter<?> parentOfSelectedElement = selectedElement.getMathObjectParent();
			if(parentOfSelectedElement instanceof MathObjectMultiplyContainerPresenter) {
				selected = (MathObjectMultiplyElementPresenter) selectedElement;
				parentOfSelected = (MathObjectMultiplyContainerPresenter) parentOfSelectedElement;
				atNum = parentOfSelected.getNumerator().contains(selected);
				return true;
			}
		}
		return false;
	}

	@Override
	protected int getPriorityOfProcess() {
		if(parentOfSelected == whereElement.getMathObjectParent()
			&&(atNum && parentOfSelected.getNumerator().contains(whereElement) || !atNum && parentOfSelected.getDenominator().contains(whereElement))
			&&(zoneH==MathObjectElementPresenter.ZONE_OO || zoneH==MathObjectElementPresenter.ZONE_EE))
			return 1;
		return 0;
	}

	@Override
	protected void executeProcess() {
		assert whereElement instanceof MathObjectMultiplyElementPresenter;
		List<MathObjectMultiplyElementPresenter> children;
		if(atNum)
			children = parentOfSelected.getNumerator();
		else
			children = parentOfSelected.getDenominator();
		children.remove(children.indexOf(selected));
		int indexOfWhere = children.indexOf(whereElement);
		assert indexOfWhere != -1;
		if(zoneH==MathObjectElementPresenter.ZONE_EE)
			indexOfWhere++;
		children.add(indexOfWhere,selected);
	}
}
