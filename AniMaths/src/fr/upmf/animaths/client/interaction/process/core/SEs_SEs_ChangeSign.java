package fr.upmf.animaths.client.interaction.process.core;

import fr.upmf.animaths.client.interaction.process.MathObjectProcess;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectElementPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectMultiplyElementPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectSignedElementPresenter;

public final class SEs_SEs_ChangeSign extends MathObjectProcess{

	private static final SEs_SEs_ChangeSign instance = new SEs_SEs_ChangeSign();
	protected SEs_SEs_ChangeSign() {}
	public static void setEnabled() {
		MathObjectProcess.setEnabled(instance);
	}

	@Override
	protected boolean isProcessInvolved() {
		if(selectedElement instanceof MathObjectSignedElementPresenter) {
			if(((MathObjectSignedElementPresenter) selectedElement).getChild() instanceof MathObjectSignedElementPresenter)
				return true;
			if(((MathObjectSignedElementPresenter) selectedElement).getMathObjectParent() instanceof MathObjectSignedElementPresenter)
				return true;
		}
		if(selectedElement instanceof MathObjectMultiplyElementPresenter) {
			MathObjectElementPresenter<?> child = ((MathObjectMultiplyElementPresenter) selectedElement).getChild();
			if(child instanceof MathObjectSignedElementPresenter) {
				MathObjectElementPresenter<?> greatChild = ((MathObjectSignedElementPresenter) child).getChild();
				if(greatChild instanceof MathObjectSignedElementPresenter) {
					selectedElement = child;
					return true;
				}
			}
		}
		return false;
	}

	@Override
	protected int getPriorityOfProcess() {
		if(whereElement instanceof MathObjectSignedElementPresenter) {
			MathObjectSignedElementPresenter where = (MathObjectSignedElementPresenter) whereElement;
			if(where.getChild()==selectedElement || where.getMathObjectParent()==selectedElement) {
				return 1;
			}
		}
		return 0;
	}

	@Override
	protected void executeProcess() {
		assert whereElement instanceof MathObjectSignedElementPresenter;
		assert selectedElement instanceof MathObjectSignedElementPresenter;
		MathObjectSignedElementPresenter where = (MathObjectSignedElementPresenter) whereElement;
		MathObjectSignedElementPresenter selected = (MathObjectSignedElementPresenter) selectedElement;
		if(where.getChild()==selectedElement) {
			where.setMinus(where.isMinus()^selected.isMinus());
			where.setChild(selected.getChild());
		}	
		else if(where.getMathObjectParent()==selected) {
			selected.setMinus(where.isMinus()^selected.isMinus());
			selected.setChild(where.getChild());
		}
	}

}
