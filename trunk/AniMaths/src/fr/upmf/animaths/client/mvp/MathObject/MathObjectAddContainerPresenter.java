package fr.upmf.animaths.client.mvp.MathObject;

import java.util.ArrayList;
import java.util.List;

import fr.upmf.animaths.client.mvp.MathObjectPresenter;
import fr.upmf.animaths.client.mvp.MathML.MathMLElement;
import fr.upmf.animaths.client.mvp.MathML.MathMLOperator;

public class MathObjectAddContainerPresenter extends MathObjectElementPresenter<MathObjectAddContainerPresenter.Display> {

	public static final short type = MathObjectElementPresenter.MATH_OBJECT_ADDITION_CONTAINER;
	public short getType() {
		return type;
	}

	private List<MathObjectSignedElementPresenter> children = new ArrayList<MathObjectSignedElementPresenter>();
	
	public interface Display extends MathObjectElementDisplay {
		abstract public void setLFence(MathMLOperator lFence);
		abstract public void setRFence(MathMLOperator rFence);
		abstract public MathMLOperator getLFence();
		abstract public MathMLOperator getRFence();
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
	public void setState(short state) {
		this.state = state;
		if(display.getLFence()!=null)
			display.getLFence().setState(state);
		if(display.getRFence()!=null)
			display.getRFence().setState(state);
		for(MathObjectSignedElementPresenter child : children)
			child.setState(state);
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

	public void addChild(MathObjectSignedElementPresenter child) {
		child.setMathObjectParent(this);
		children.add(child);
	}	
}	

