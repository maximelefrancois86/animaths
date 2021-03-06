package fr.upmf.animaths.client.interaction;

import com.google.gwt.user.client.ui.FlowPanel;

import fr.upmf.animaths.client.mvp.MOWordingWidget;
import fr.upmf.animaths.client.mvp.MathObject.MOElement;
import fr.upmf.animaths.client.mvp.MathObject.MOMultiplyContainer;
import fr.upmf.animaths.client.mvp.MathObject.MOMultiplyElement;
import fr.upmf.animaths.client.mvp.MathObject.MONumber;
import fr.upmf.animaths.client.mvp.MathObject.MOSignedElement;

public final class MEs_N_Multiply extends AniMathsAbstractProcess{

	private static final MEs_N_Multiply instance = new MEs_N_Multiply();
	protected MEs_N_Multiply() {}
	public static void setEnabled(boolean enabled) {
		AniMathsAbstractProcess.setEnabled(instance, enabled);
	}

	MOMultiplyContainer multiplyContainer;
	MOMultiplyElement selected;
	boolean atNum;
	float floatSelected;
	MOMultiplyElement where;
	float floatWhere;
	float floatMult;
	
	@Override
	protected boolean isProcessInvolved() {
		if(selectedElement instanceof MOMultiplyElement) {
			selected = (MOMultiplyElement) selectedElement;
			atNum = !selected.isDivided();
			MOElement<?> parentOfSelected = selected.getMathObjectParent();
			if(selected.getChild() instanceof MONumber && parentOfSelected instanceof MOMultiplyContainer) {
				multiplyContainer = (MOMultiplyContainer) parentOfSelected;
				floatSelected = ((MONumber) selected.getChild()).getValue();
				System.out.println("MEs_N_Multiply : Interested");
				return true;
			}
			if(selected.getChild() instanceof MOSignedElement 
					&& ((MOSignedElement) selected.getChild()).getChild() instanceof MONumber
					&& parentOfSelected instanceof MOMultiplyContainer) {
				multiplyContainer = (MOMultiplyContainer) parentOfSelected;
				floatSelected = ((MONumber)((MOSignedElement) selected.getChild()).getChild()).getValue();
				if(((MOSignedElement) selected.getChild()).isMinus())
					floatSelected = -floatSelected;
				System.out.println("MEs_N_Multiply : Interested");
				return true;
			}
		}
		return false;
	}

	@Override
	protected short getTagOfProcess() {
		if(whereElement instanceof MOMultiplyElement) {
			where = (MOMultiplyElement) whereElement;
			if(!where.isDivided()==atNum && where.getChild() instanceof MONumber && where.getMathObjectParent()==multiplyContainer) {
				floatWhere = ((MONumber) where.getChild()).getValue();
				floatMult = floatSelected*floatWhere;
				System.out.println("MEs_N_Multiply : PROCESS_CAUTION");
				return PROCESS_CAUTION;
			}
			if(!where.isDivided()==atNum && where.getChild() instanceof MOSignedElement 
					&& ((MOSignedElement) where.getChild()).getChild() instanceof MONumber
					&& where.getMathObjectParent() instanceof MOMultiplyContainer) {
				floatWhere = ((MONumber)((MOSignedElement) where.getChild()).getChild()).getValue();
				if(((MOSignedElement) where.getChild()).isMinus())
					floatWhere = -floatWhere;
				floatMult = floatSelected*floatWhere;
				System.out.println("MEs_N_Multiply : PROCESS_CAUTION");
				return PROCESS_CAUTION;
			}
		}
		return PROCESS_NO;
	}

	@Override
	public void onAskQuestion() {
		System.out.println("MEs_N_Multiply : askQuestion");
		MOWordingWidget wording = new MOWordingWidget(new FlowPanel());
		wording.getElement().setId("question-multiply");
		MOMultiplyElement selectedClone = selected.clone();
		selectedClone.setDivided(false);
		MOMultiplyElement whereClone = where.clone();
		whereClone.setDivided(false);
		if(multiplyContainer.indexOf(where)>multiplyContainer.indexOf(selected))
			wording.setWording("Combien font ",new MOMultiplyContainer(selectedClone,whereClone)," ?");
		else
			wording.setWording("Combien font ",new MOMultiplyContainer(whereClone,selectedClone)," ?");
		System.out.println(String.valueOf(floatMult));
		if(Math.round(floatMult)==floatMult)
			(new AniMathsQuestionTextBox(this, wording, String.valueOf(Math.round(floatMult)))).center();
		else
			(new AniMathsQuestionTextBox(this, wording, String.valueOf(floatMult))).center();
	}

	@Override
	public void onExecuteProcess(int answer) {
		System.out.println("MEs_N_Multiply : ExecuteProcess");
		multiplyContainer.remove(selected);
		if(floatMult<0)
			where.setChild(new MOSignedElement(new MONumber(Math.abs(floatMult)), true));
		else
			where.setChild(new MONumber(floatMult));
	}

	@Override
	public MOWordingWidget getMessage(int answer) {
		if(answer>0)
			return new MOWordingWidget("Bonne réponse !");
		else if(answer==0)
			return new MOWordingWidget("Non, réessaie et attention aux signes !");
		else if(answer==-2)
			return new MOWordingWidget("La réponse doit être un nombre ! <br/> Si tu pense que la réponse n'est pas un nombre entier, utilise un point plutôt qu'une virgule...");
		else
			return new MOWordingWidget("Non, réessaie, c'est une mauvaise réponse !");
	}
}
