package fr.upmf.animaths.client.mvp.MathObject;

import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;

import fr.upmf.animaths.client.mvp.MOAbstractPresenter;
import fr.upmf.animaths.client.mvp.MathML.MMLElement;
import fr.upmf.animaths.client.mvp.MathML.MMLOperator;

public class MOMultiplyElement extends MOElement<MOMultiplyElement.Display> implements IMOHasOneChild {

	public static final short type = MOElement.MATH_OBJECT_MULTIPLY_ELEMENT;
	@Override
	public short getType() {
		return type;
	}

	private boolean isDivided = false;
	private boolean needsSign = true;
	private MOElement<?> child;

	public interface Display extends MOElementDisplay, IMOHasSign {
	}

	public MOMultiplyElement() {
		super(new MOMultiplyElementDisplay());
	}

	public MOMultiplyElement(MOElement<?> child) {
		this();
		setChild(child);
	}
	
	public MOMultiplyElement(MOElement<?> child, boolean isDivided) {
		this(child);
		setDivided(isDivided);
	}
	
	@Override
	public void pack(MMLElement mathMLParent, MOAbstractPresenter<?> presenter) {
		if(needsSign) {
			display.setSign(MMLOperator.times());
			mathMLParent.appendChild(display.getSign());
			if(presenter!=null)
				presenter.putDOMElement(display.getSign().getElement(),this);
		}
		else
			display.setSign(null);
		child.pack(mathMLParent, presenter);
	}

	@Override
	public MOMultiplyElement clone() {
		return new MOMultiplyElement(child.clone(),isDivided);
	}

	@Override
	public void setStyleClass(short styleClass) {
		this.styleClass = styleClass;
		if(display.getSign()!=null)
			display.getSign().setStyleClass(styleClass);
		child.setStyleClass(styleClass);
	}

	@Override
	public MOElement<?> getMathObjectFirstSelectableChild() {
		if(child.getType()==MOElement.MATH_OBJECT_NUMBER
				||child.getType()==MOElement.MATH_OBJECT_IDENTIFIER)
			return this;
		else
			return child.getMathObjectFirstSelectableChild();
	}

	@Override
	public MOElement<?> getMathObjectNextSelectableChild(MOElement<?> child) {
		return mathObjectParent.getMathObjectNextSelectableChild(this);
	}

	@Override
	public MOElement<?> getMathObjectPreviousSelectableChild(MOElement<?> child) {
		return mathObjectParent.getMathObjectPreviousSelectableChild(this);
	}

	@Override
	public int getBoundingClientLeft() {
		if(display.getSign()!=null)
			return (int) display.getSign().getBoundingClientLeft();
		else
			return child.getBoundingClientLeft();
	}

	@Override
	public int getBoundingClientRight() {
		return child.getBoundingClientRight();
	}

	@Override
	public int getBoundingClientTop() {
		return child.getBoundingClientTop();
	}

	@Override
	public int getBoundingClientBottom() {
			return child.getBoundingClientBottom();
	}

	@Override
	public MOElement<?> getChild() {
		return child;
	}
	
	@Override
	public void setChild(MOElement<?> child) {
		child.setMathObjectParent(this);
		this.child = child;
	}

	public void setNeedsSign(boolean needsSign) {
		this.needsSign = needsSign;
	}

	public boolean needsSign() {
		return needsSign;
	}

	public boolean isDivided() {
		return isDivided;
	}

	public void setDivided(boolean isDivided) {
		this.isDivided = isDivided;
	}

//	@Override
//	public Element getFirstDOMElement() {
//		if(display.getSign()!=null)
//			return display.getSign().getElement();
//		return child.getFirstDOMElement();
//	}
//
//	@Override
//	public Element getLastDOMElement() {
//		return child.getLastDOMElement();
//	}		
	
	public static MOMultiplyElement parse(Element element){
		assert element.getTagName().equals("mome");		
		MOMultiplyElement mome = new MOMultiplyElement();
		NodeList children = element.getChildNodes();
		short k=0;
		for(int i=0;i<children.getLength();i++) {
			Node n = children.item(i);
			if(n.getNodeType() == Node.ELEMENT_NODE) {
				k++;
				assert k<=1;
				mome.setChild(MOElement.parse((Element) n));
			}
		}
		if(element.hasAttribute("isDivided"))
			mome.setDivided(Boolean.parseBoolean(element.getAttribute("isDivided")));
		return mome;
	}

}
