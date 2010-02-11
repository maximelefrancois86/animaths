package fr.upmf.animaths.client.mvp.MathObject;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.dom.client.Element;

import fr.upmf.animaths.client.mvp.MOAbtractPresenter;
import fr.upmf.animaths.client.mvp.MathML.MMLElement;
import fr.upmf.animaths.client.mvp.MathML.MMLFrac;
import fr.upmf.animaths.client.mvp.MathML.MMLOperator;
import fr.upmf.animaths.client.mvp.MathML.MMLRow;


public class MOMultiplyContainer extends MOElement<MOMultiplyContainer.Display> implements IMOHasSeveralChildren<MOMultiplyElement>{

	public static final short type = MOElement.MATH_OBJECT_MULTIPLY_CONTAINER;
	@Override
	public short getType() {
		return type;
	}

	private List<MOMultiplyElement> numerator = new ArrayList<MOMultiplyElement>();
	private List<MOMultiplyElement> denominator = new ArrayList<MOMultiplyElement>();
	
	public interface Display extends MOElementDisplay, IMOHasFence {
		abstract public void setFrac(MMLFrac frac);
		abstract public void setNumeratorRow(MMLRow numeratorRow);
		abstract public void setDenominatorRow(MMLRow denominatorRow);
		abstract public MMLFrac getFrac();
		abstract public MMLRow getNumeratorRow();
		abstract public MMLRow getDenominatorRow();
	}

	public MOMultiplyContainer() {
		super(new MOMultiplyContainerDisplay());
	}

	public MOMultiplyContainer(MOMultiplyElement ... children) {
		this();
		for(MOMultiplyElement child : children)
			addChild(child);
	}

	@Override
	public void pack(MMLElement mathMLParent, MOAbtractPresenter<?> presenter) {
		MMLElement mmlp = mathMLParent;
		boolean needsFence = needsFence();
		if(needsFence) {
			display.setLFence(MMLOperator.lFence());
			mathMLParent.appendChild(display.getLFence());
			if(presenter!=null)
				presenter.putDOMElement(display.getLFence().getElement(),this);
		}
		else
			display.setLFence(null);
		setNeedsSigns();
		if(denominator.size()!=0) {
			display.setFrac(new MMLFrac());
			display.setNumeratorRow(new MMLRow());
			display.setDenominatorRow(new MMLRow());
			mathMLParent.appendChild(display.getFrac());
			display.getFrac().appendChild(display.getNumeratorRow());
			display.getFrac().appendChild(display.getDenominatorRow());
			denominator.get(0).setNeedsSign(false);
			mmlp = display.getNumeratorRow();
			if(numerator.size()==0)
				addChild(new MOMultiplyElement(new MONumber(1)));
			for(MOMultiplyElement child : denominator)
				child.pack(display.getDenominatorRow(), presenter);
		}
		else {
			display.setFrac(null);
			display.setNumeratorRow(null);
			display.setDenominatorRow(null);
		}
		numerator.get(0).setNeedsSign(false);
		for(MOMultiplyElement child : numerator)
			child.pack(mmlp, presenter);
		if(needsFence) {
			display.setRFence(MMLOperator.rFence());
			mathMLParent.appendChild(display.getRFence());
			if(presenter!=null)
				presenter.putDOMElement(display.getRFence().getElement(),this);
		}
		else
			display.setRFence(null);
	}

	@Override
	public MOMultiplyContainer clone() {
		MOMultiplyContainer object = new MOMultiplyContainer();
		for(MOMultiplyElement child : numerator)
			object.addChild(child.clone());
		for(MOMultiplyElement child : denominator)
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
		for(MOMultiplyElement child : numerator)
			child.setStyleClass(styleClass);		
		for(MOMultiplyElement child : denominator)
			child.setStyleClass(styleClass);		
	}

	@Override
	public MOElement<?> getMathObjectFirstSelectableChild() {
		return numerator.get(0);
	}

	@Override
	public MOElement<?> getMathObjectNextSelectableChild(MOElement<?> child) {
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
	public MOElement<?> getMathObjectPreviousSelectableChild(MOElement<?> child) {
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
			for(MOMultiplyElement element : numerator)
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
			for(MOMultiplyElement element : numerator)
				i = Math.max(i,element.getBoundingClientBottom());
			return i;
		}
	}

	@Override
	public void addChild(MOMultiplyElement child) {
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

	public List<MOMultiplyElement> getNumerator() {
		return numerator;
	}

	public List<MOMultiplyElement> getDenominator() {
		return denominator;
	}

	private void setNeedsSigns() {
		if(numerator.size()!=0) {
			numerator.get(0).setNeedsSign(false);
			for(int i=1;i<numerator.size();i++)
				numerator.get(i).setNeedsSign(true);
		}
		if(denominator.size()!=0) {
			denominator.get(0).setNeedsSign(false);
			for(int i=1;i<denominator.size();i++)
				denominator.get(i).setNeedsSign(true);
		}
	}

}	

