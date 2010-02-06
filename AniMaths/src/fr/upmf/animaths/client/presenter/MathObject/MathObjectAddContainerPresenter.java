package fr.upmf.animaths.client.presenter.MathObject;

import java.util.ArrayList;
import java.util.List;

import fr.upmf.animaths.client.display.MathML.MathMLElement;
import fr.upmf.animaths.client.display.MathML.MathMLOperator;
import fr.upmf.animaths.client.display.MathObject.IMathObjectHasFence;
import fr.upmf.animaths.client.display.MathObject.MathObjectAddContainerDisplay;
import fr.upmf.animaths.client.display.MathObject.MathObjectElementDisplay;
import fr.upmf.animaths.client.presenter.MathObjectPresenter;
import fr.upmf.animaths.client.presenter.MathObject.Interfaces.IMathObjectHasSeveralChildren;

public class MathObjectAddContainerPresenter extends MathObjectElementPresenter<MathObjectAddContainerPresenter.Display> implements IMathObjectHasSeveralChildren<MathObjectSignedElementPresenter> {

	public static final short type = MathObjectElementPresenter.MATH_OBJECT_ADDITION_CONTAINER;
	@Override
	public short getType() {
		return type;
	}

	private List<MathObjectSignedElementPresenter> children = new ArrayList<MathObjectSignedElementPresenter>();
	
	public interface Display extends MathObjectElementDisplay, IMathObjectHasFence {
	}

	public MathObjectAddContainerPresenter() {
		super(new MathObjectAddContainerDisplay());
	}

	public MathObjectAddContainerPresenter(MathObjectSignedElementPresenter ... children) {
		this();
		for(MathObjectSignedElementPresenter child : children)
			addChild(child);
	}

	@Override
	public void pack(MathMLElement mathMLParent, MathObjectPresenter<?> presenter) {
		boolean needsFence = needsFence();
		if(needsFence) {
			display.setLFence(MathMLOperator.lFence());
			mathMLParent.appendChild(display.getLFence());
			if(presenter!=null)
				presenter.putDOMElement(display.getLFence().getElement(),this);
		}
		children.get(0).setNeedsSign(false);
		for(MathObjectSignedElementPresenter child : children)
			child.pack(mathMLParent, presenter);
		if(needsFence) {
			display.setRFence(MathMLOperator.rFence());
			mathMLParent.appendChild(display.getRFence());
			if(presenter!=null)
				presenter.putDOMElement(display.getRFence().getElement(),this);
		}
	}
	
	@Override
	public MathObjectAddContainerPresenter clone() {
		MathObjectAddContainerPresenter object = new MathObjectAddContainerPresenter();
		for(MathObjectSignedElementPresenter child : children)
			object.addChild(child.clone());
		return object;
	}

	@Override
	public void setStyleClass(short styleClass) {
		this.styleClass = styleClass;
		if(display.getLFence()!=null)
			display.getLFence().setStyleClass(styleClass);
		if(display.getRFence()!=null)
			display.getRFence().setStyleClass(styleClass);
		for(MathObjectSignedElementPresenter child : children)
			child.setStyleClass(styleClass);
	}

	@Override
	public MathObjectElementPresenter<?> getMathObjectFirstChild() {
		return children.get(0);
	}

	@Override
	public MathObjectElementPresenter<?> getMathObjectNextChild(MathObjectElementPresenter<?> child) {
		int i = children.indexOf(child);
		if(i!=children.size()-1)
			return children.get(i+1);
		return children.get(0);
	}

	@Override
	public MathObjectElementPresenter<?> getMathObjectPreviousChild(MathObjectElementPresenter<?> child) {
		int i = children.indexOf(child);
		if(i!=0)
			return children.get(i-1);
		return children.get(children.size()-1);
	}

	@Override
	public int getBoundingClientLeft() {
		if(display.getLFence()!=null)
			return (int) display.getLFence().getBoundingClientLeft();
		else 
			return children.get(0).getBoundingClientLeft();
	}

	@Override
	public int getBoundingClientRight() {
		if(display.getRFence()!=null)
			return (int) (display.getRFence().getBoundingClientLeft() + display.getRFence().getBoundingClientWidth());
		else
			return children.get(children.size()-1).getBoundingClientRight();
	}

	@Override
	public int getBoundingClientTop() {
		if(display.getLFence()!=null)
			return (int) display.getLFence().getBoundingClientTop();
		else {
			int i=Integer.MAX_VALUE;
			for(MathObjectSignedElementPresenter element : children)
				i = Math.min(i,element.getBoundingClientTop());
			return i;
		}
	}

	@Override
	public int getBoundingClientBottom() {
		if(display.getLFence()!=null)
			return (int) (display.getLFence().getBoundingClientTop() + display.getLFence().getBoundingClientHeight());
		else {
			int i=0;
			for(MathObjectSignedElementPresenter element : children)
				i = Math.max(i,element.getBoundingClientBottom());
			return i;
		}
	}

	@Override
	public void addChild(MathObjectSignedElementPresenter child) {
		child.setMathObjectParent(this);
		children.add(child);
	}

}	

