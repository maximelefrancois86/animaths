package fr.upmf.animaths.client.mvp.MathObject;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NodeList;

import fr.upmf.animaths.client.mvp.MOAbstractPresenter;
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
			add(child);
	}

	@Override
	public void pack(MMLElement mathMLParent, MOAbstractPresenter<?> presenter) {
		checkChildrenPlaces();
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
				add(new MOMultiplyElement(new MONumber(1)));
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
			object.add(child.clone());
		for(MOMultiplyElement child : denominator)
			object.add(child.clone());
		return object;
	}

	private void checkChildrenPlaces() {
		MOMultiplyElement child;
		for(int i=0;i<numerator.size();) {
			child = numerator.get(i);
			if(!child.isDivided())
				i++;
			else {
				numerator.remove(child);
				denominator.add(child);
			}
		}
		for(int i=0;i<denominator.size();) {
			child = denominator.get(i);
			if(child.isDivided())
				i++;
			else {
				denominator.remove(child);
				numerator.add(child);
			}
		}
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

//	@Override
//	public Element getFirstDOMElement() {
//		if(display.getLFence()!=null)
//			return display.getLFence().getElement();
//		if(display.getFrac()!=null)
//			return display.getFrac().getElement();
//		return numerator.get(0).getFirstDOMElement();
//	}
//
//	@Override
//	public Element getLastDOMElement() {
//		if(display.getRFence()!=null)
//			return display.getRFence().getElement();
//		if(display.getFrac()!=null)
//			return display.getFrac().getElement();
//		return numerator.get(numerator.size()-1).getLastDOMElement();
//	}

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
	
	@Override
	public void add(MOMultiplyElement child) {
		child.setMathObjectParent(this);
		List<MOMultiplyElement> children = (child.isDivided())? denominator: numerator;
		children.add(child);
	}

	@Override
	public void add(MOMultiplyElement child, MOMultiplyElement refChild, boolean after) {
		child.setMathObjectParent(this);
		int index = numerator.indexOf(refChild);
		if(index!=-1) {
//			assert !child.isDivided() && !refChild.isDivided();
			if(after)
				index++;
			numerator.add(index,child);
		}
		else {
			index = denominator.indexOf(refChild);
			assert index!=-1 ;//&& child.isDivided() && refChild.isDivided();
			if(after)
				index++;
			denominator.add(index,child);
		}
	}

	public void add(int index, MOMultiplyElement child) {
		child.setMathObjectParent(this);
		if(index<numerator.size())
			numerator.add(index, child);
		else
			denominator.add(index-numerator.size(),child);
	}

	public int indexOf(MOMultiplyElement child) {
		int index = numerator.indexOf(child);
		if(index!=-1)
			return index;
		else
			return numerator.size()+denominator.indexOf(child);
	}

	public int size() {
		return numerator.size()+denominator.size();
	}

	public MOMultiplyElement get(int index) {
		if(index<numerator.size())
			return numerator.get(index);
		else if(index-numerator.size()<denominator.size())
			return denominator.get(index-numerator.size());
		else
			return null;
	}

	public void remove(int index) {
		if(index<numerator.size())
			numerator.remove(index);
		else if(index-numerator.size()<denominator.size())
			denominator.remove(index-numerator.size());
	}

	@Override
	public void remove(MOMultiplyElement child) {
		if(numerator.contains(child))
			numerator.remove(child);
		else {
			assert denominator.contains(child);
			denominator.remove(child);
		}
	}

	public int sizeOfNumerator() {
		return numerator.size();
	}

	public int sizeOfDenominator() {
		return denominator.size();
	}
	
	public void inverseSign() {
		for(MOMultiplyElement child:numerator)
			child.setDivided(true);
		for(MOMultiplyElement child:denominator)
			child.setDivided(false);
		checkChildrenPlaces();
		if(denominator.size()==1) {
			MOElement<?> element = denominator.get(0).getChild();
			if(element instanceof MONumber && ((MONumber)element).getValue()==(Number)1)
				denominator.remove(0);
		}
	}

	@Override
	public short getZoneV(int y) {
		int px = 10;
		int pc = 5;
		int top = getBoundingClientTop();
		int bottom = getBoundingClientBottom();
		int dOut = Math.min(px,(int)((bottom-top)/pc));
		int barPos;
		if(display.getFrac()!=null) {
			barPos = (int)(0.5*(display.getNumeratorRow().getBoundingClientTop()+display.getNumeratorRow().getBoundingClientWidth()+display.getDenominatorRow().getBoundingClientTop()));
			return getZone(y,top,barPos,bottom,px,dOut);
		}
		return super.getZoneV(y);
	}

	public static MOMultiplyContainer parse(Element element){
		assert element.getTagName().equals("momc");
		MOMultiplyContainer momc = new MOMultiplyContainer();
		NodeList children = element.getChildNodes();
		for(int i=0;i<children.getLength();i++) {
			Node n = children.item(i);
			if(n.getNodeType() == Node.ELEMENT_NODE)
				momc.add((MOMultiplyElement) MOMultiplyElement.parse((Element) n));
		}
		return momc;
	}
	
}	

