package fr.upmf.animaths.client.mvp.MathObject;

import java.util.ArrayList;
import java.util.List;

import fr.upmf.animaths.client.mvp.MathObjectPresenter;
import fr.upmf.animaths.client.mvp.widgets.MathML.MathMLElement;
import fr.upmf.animaths.client.mvp.widgets.MathML.MathMLFrac;
import fr.upmf.animaths.client.mvp.widgets.MathML.MathMLOperator;
import fr.upmf.animaths.client.mvp.widgets.MathML.MathMLRow;


public class MathObjectMultiplyContainerPresenter extends MathObjectElementPresenter<MathObjectMultiplyContainerPresenter.Display> {

	public static final short type = MathObjectElementPresenter.MATH_OBJECT_MULTIPLY_CONTAINER;
	public short getType() {
		return type;
	}

	private List<MathObjectMultiplyElementPresenter> numerator = new ArrayList<MathObjectMultiplyElementPresenter>();
	private List<MathObjectMultiplyElementPresenter> denominator = new ArrayList<MathObjectMultiplyElementPresenter>();
	
	public interface Display extends MathObjectElementView {
		abstract public void setLFence(MathMLOperator lFence);
		abstract public void setRFence(MathMLOperator rFence);
		abstract public void setFrac(MathMLFrac frac);
		abstract public void setNumeratorRow(MathMLRow numeratorRow);
		abstract public void setDenominatorRow(MathMLRow denominatorRow);
		abstract public MathMLOperator getLFence();
		abstract public MathMLOperator getRFence();
		abstract public MathMLFrac getFrac();
		abstract public MathMLRow getNumeratorRow();
		abstract public MathMLRow getDenominatorRow();
	}

	public MathObjectMultiplyContainerPresenter() {
		super(new MathObjectMultiplyContainerView());
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
	public void setState(short state) {
		this.state = state;
		if(display.getLFence()!=null)
			display.getLFence().setState(state);
		if(display.getRFence()!=null)
			display.getRFence().setState(state);
		if(display.getFrac()!=null)
			display.getFrac().setState(state);
		if(display.getNumeratorRow()!=null)
			display.getNumeratorRow().setState(state);
		if(display.getDenominatorRow()!=null)
			display.getDenominatorRow().setState(state);
		for(MathObjectMultiplyElementPresenter child : numerator)
			child.setState(state);		
		for(MathObjectMultiplyElementPresenter child : denominator)
			child.setState(state);		
	}

	@Override
	public MathObjectElementPresenter<?> getMathObjectFirstChild() {
		return numerator.get(0);
	}

	@Override
	public MathObjectElementPresenter<?> getMathObjectNextChild(MathObjectElementPresenter<?> child) {
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
	public MathObjectElementPresenter<?> getMathObjectPreviousChild(MathObjectElementPresenter<?> child) {
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

	public void addChild(MathObjectMultiplyElementPresenter child) {
		child.setMathObjectParent(this);
		if(!child.isDivided())
			numerator.add(child);
		else
			denominator.add(child);
	}
	
}	

