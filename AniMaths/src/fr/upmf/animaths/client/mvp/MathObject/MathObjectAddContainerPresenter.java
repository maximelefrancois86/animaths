package fr.upmf.animaths.client.mvp.MathObject;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.dom.client.Element;

import fr.upmf.animaths.client.mvp.MathObjectAbtractPresenter;
import fr.upmf.animaths.client.mvp.MathML.MathMLElement;
import fr.upmf.animaths.client.mvp.MathML.MathMLOperator;

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
	public void pack(MathMLElement mathMLParent, MathObjectAbtractPresenter<?> presenter) {
		boolean needsFence = needsFence();
		if(needsFence) {
			display.setLFence(MathMLOperator.lFence());
			mathMLParent.appendChild(display.getLFence());
			if(presenter!=null)
				presenter.putDOMElement(display.getLFence().getElement(),this);
		}
		else
			display.setLFence(null);
		setNeedsSigns();
		for(MathObjectSignedElementPresenter child : children)
			child.pack(mathMLParent, presenter);
		if(needsFence) {
			display.setRFence(MathMLOperator.rFence());
			mathMLParent.appendChild(display.getRFence());
			if(presenter!=null)
				presenter.putDOMElement(display.getRFence().getElement(),this);
		}
		else
			display.setRFence(null);
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
	public MathObjectElementPresenter<?> getMathObjectFirstSelectableChild() {
		return children.get(0);
	}

	@Override
	public MathObjectElementPresenter<?> getMathObjectNextSelectableChild(MathObjectElementPresenter<?> child) {
		int i = children.indexOf(child);
		if(i!=children.size()-1)
			return children.get(i+1);
		return children.get(0);
	}

	@Override
	public MathObjectElementPresenter<?> getMathObjectPreviousSelectableChild(MathObjectElementPresenter<?> child) {
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

	@Override
	public Element getFirstDOMElement() {
		if(display.getLFence()!=null)
			return display.getLFence().getElement();
		return children.get(0).getFirstDOMElement();
	}

	@Override
	public Element getLastDOMElement() {
		if(display.getRFence()!=null)
			return display.getRFence().getElement();
		return children.get(children.size()-1).getLastDOMElement();
	}

	public List<MathObjectSignedElementPresenter> getChildren() {
		return children;
	}

	private void setNeedsSigns() {
		children.get(0).setNeedsSign(false);
		for(int i=1;i<children.size();i++)
			children.get(i).setNeedsSign(true);
	}
}	

