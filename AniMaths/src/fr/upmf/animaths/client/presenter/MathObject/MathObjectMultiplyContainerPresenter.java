package fr.upmf.animaths.client.presenter.MathObject;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.dom.client.Element;

import fr.upmf.animaths.client.display.MathML.MathMLElement;
import fr.upmf.animaths.client.display.MathML.MathMLFrac;
import fr.upmf.animaths.client.display.MathML.MathMLOperator;
import fr.upmf.animaths.client.display.MathML.MathMLRow;
import fr.upmf.animaths.client.display.MathObject.IMathObjectHasFence;
import fr.upmf.animaths.client.display.MathObject.MathObjectElementDisplay;
import fr.upmf.animaths.client.display.MathObject.MathObjectMultiplyContainerDisplay;
import fr.upmf.animaths.client.presenter.MathObjectPresenter;
import fr.upmf.animaths.client.presenter.MathObject.Interfaces.IMathObjectHasSeveralChildren;


public class MathObjectMultiplyContainerPresenter extends MathObjectElementPresenter<MathObjectMultiplyContainerPresenter.Display> implements IMathObjectHasSeveralChildren<MathObjectMultiplyElementPresenter>{

	public static final short type = MathObjectElementPresenter.MATH_OBJECT_MULTIPLY_CONTAINER;
	@Override
	public short getType() {
		return type;
	}

	private List<MathObjectMultiplyElementPresenter> numerator = new ArrayList<MathObjectMultiplyElementPresenter>();
	private List<MathObjectMultiplyElementPresenter> denominator = new ArrayList<MathObjectMultiplyElementPresenter>();
	
	public interface Display extends MathObjectElementDisplay, IMathObjectHasFence {
		abstract public void setFrac(MathMLFrac frac);
		abstract public void setNumeratorRow(MathMLRow numeratorRow);
		abstract public void setDenominatorRow(MathMLRow denominatorRow);
		abstract public MathMLFrac getFrac();
		abstract public MathMLRow getNumeratorRow();
		abstract public MathMLRow getDenominatorRow();
	}

	public MathObjectMultiplyContainerPresenter() {
		super(new MathObjectMultiplyContainerDisplay());
	}

	public MathObjectMultiplyContainerPresenter(MathObjectMultiplyElementPresenter ... children) {
		this();
		for(MathObjectMultiplyElementPresenter child : children)
			addChild(child);
	}

	@Override
	public void pack(MathMLElement mathMLParent, MathObjectPresenter<?> presenter) {
		MathMLElement mmlp = mathMLParent;
		boolean needsFence = needsFence();
		if(needsFence) {
			display.setLFence(MathMLOperator.lFence());
			mathMLParent.appendChild(display.getLFence());
			if(presenter!=null)
				presenter.putDOMElement(display.getLFence().getElement(),this);
		}
		if(denominator.size()!=0) {
			display.setFrac(new MathMLFrac());
			display.setNumeratorRow(new MathMLRow());
			display.setDenominatorRow(new MathMLRow());
			mathMLParent.appendChild(display.getFrac());
			display.getFrac().appendChild(display.getNumeratorRow());
			display.getFrac().appendChild(display.getDenominatorRow());
			denominator.get(0).setNeedsSign(false);
			mmlp = display.getNumeratorRow();
			if(numerator.size()==0)
				addChild(new MathObjectMultiplyElementPresenter(new MathObjectNumberPresenter(1)));
			for(MathObjectMultiplyElementPresenter child : denominator)
				child.pack(display.getDenominatorRow(), presenter);
		}
		numerator.get(0).setNeedsSign(false);
		for(MathObjectMultiplyElementPresenter child : numerator)
			child.pack(mmlp, presenter);
		if(needsFence) {
			display.setRFence(MathMLOperator.rFence());
			mathMLParent.appendChild(display.getRFence());
			if(presenter!=null)
				presenter.putDOMElement(display.getRFence().getElement(),this);
		}
	}

	@Override
	public MathObjectMultiplyContainerPresenter clone() {
		MathObjectMultiplyContainerPresenter object = new MathObjectMultiplyContainerPresenter();
		for(MathObjectMultiplyElementPresenter child : numerator)
			object.addChild(child.clone());
		for(MathObjectMultiplyElementPresenter child : denominator)
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
		if(display.getFrac()!=null)
			display.getFrac().setStyleClass(styleClass);
		if(display.getNumeratorRow()!=null)
			display.getNumeratorRow().setStyleClass(styleClass);
		if(display.getDenominatorRow()!=null)
			display.getDenominatorRow().setStyleClass(styleClass);
		for(MathObjectMultiplyElementPresenter child : numerator)
			child.setStyleClass(styleClass);		
		for(MathObjectMultiplyElementPresenter child : denominator)
			child.setStyleClass(styleClass);		
	}

	@Override
	public MathObjectElementPresenter<?> getMathObjectFirstSelectableChild() {
		return numerator.get(0);
	}

	@Override
	public MathObjectElementPresenter<?> getMathObjectNextSelectableChild(MathObjectElementPresenter<?> child) {
		int i = numerator.indexOf(child);
		if(i!=-1) {
			if(i!=numerator.size()-1)
				return numerator.get(i+1);
			else {
				if(denominator.size()!=0)
					return denominator.get(0);
				else
					return numerator.get(0);
			}
		}
		else {
			i = denominator.indexOf(child);
			if(i!=denominator.size()-1)
				return denominator.get(i+1);
			else
				return numerator.get(0);
		}
	}

	@Override
	public MathObjectElementPresenter<?> getMathObjectPreviousSelectableChild(MathObjectElementPresenter<?> child) {
		int i = numerator.indexOf(child);
		if(i!=-1) {
			if(i!=0)
				return numerator.get(i-1);
			else {
				if(denominator.size()!=0)
					return denominator.get(denominator.size()-1);
				else
					return numerator.get(numerator.size()-1);
			}
		}
		else {
			i = denominator.indexOf(child);
			if(i!=0)
				return denominator.get(i-1);
			else
				return numerator.get(numerator.size()-1);
		}
	}

	@Override
	public int getBoundingClientLeft() {
		if(display.getLFence()!=null)
			return (int) display.getLFence().getBoundingClientLeft();
		else if(display.getFrac()!=null)
			return (int) display.getFrac().getBoundingClientLeft();
		else 
			return numerator.get(0).getBoundingClientLeft();
	}

	@Override
	public int getBoundingClientRight() {
		if(display.getRFence()!=null)
			return (int) (display.getRFence().getBoundingClientLeft() + display.getRFence().getBoundingClientWidth());
		else if(display.getFrac()!=null)
			return (int) (display.getFrac().getBoundingClientLeft() + display.getFrac().getBoundingClientWidth());
		else
			return numerator.get(numerator.size()-1).getBoundingClientRight();
	}

	@Override
	public int getBoundingClientTop() {
		if(display.getLFence()!=null)
			return (int) display.getLFence().getBoundingClientTop();
		else if(display.getFrac()!=null)
			return (int) display.getFrac().getBoundingClientTop();
		else {
			int i=Integer.MAX_VALUE;
			for(MathObjectMultiplyElementPresenter element : numerator)
				i = Math.min(i,element.getBoundingClientTop());
			return i;
		}
	}

	@Override
	public int getBoundingClientBottom() {
		if(display.getLFence()!=null)
			return (int) (display.getLFence().getBoundingClientTop() + display.getLFence().getBoundingClientHeight());
		else if(display.getFrac()!=null)
			return (int) (display.getFrac().getBoundingClientTop() + display.getFrac().getBoundingClientHeight());
		else {
			int i=0;
			for(MathObjectMultiplyElementPresenter element : numerator)
				i = Math.max(i,element.getBoundingClientBottom());
			return i;
		}
	}

	@Override
	public void addChild(MathObjectMultiplyElementPresenter child) {
		child.setMathObjectParent(this);
		if(!child.isDivided())
			numerator.add(child);
		else
			denominator.add(child);
	}

	@Override
	public Element getFirstDOMElement() {
		if(display.getLFence()!=null)
			return display.getLFence().getElement();
		if(display.getFrac()!=null)
			return display.getFrac().getElement();
		return numerator.get(0).getFirstDOMElement();
	}

	@Override
	public Element getLastDOMElement() {
		if(display.getRFence()!=null)
			return display.getRFence().getElement();
		if(display.getFrac()!=null)
			return display.getFrac().getElement();
		return numerator.get(numerator.size()-1).getLastDOMElement();
	}

	@Override
	public List<MathObjectMultiplyElementPresenter> getChildren() {
		List<MathObjectMultiplyElementPresenter> children = new ArrayList<MathObjectMultiplyElementPresenter>();
		children.addAll(numerator);
		children.addAll(denominator);
		return children;
	}
}	

