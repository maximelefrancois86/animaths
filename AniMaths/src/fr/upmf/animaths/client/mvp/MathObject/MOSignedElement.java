package fr.upmf.animaths.client.mvp.MathObject;


import com.google.gwt.dom.client.Node;
import com.google.gwt.xml.client.Element;

import fr.upmf.animaths.client.mvp.MOAbtractPresenter;
import fr.upmf.animaths.client.mvp.MathML.MMLElement;
import fr.upmf.animaths.client.mvp.MathML.MMLOperator;

public class MOSignedElement extends MOElement<MOSignedElement.Display> implements IMOHasOneChild {

	public static final short type = MOElement.MATH_OBJECT_SIGNED_ELEMENT;
	public short getType() {
		return type;
	}
	
	private boolean isMinus = false;
	private boolean needsSign = true;
	private MOElement<?> child;
	
	public interface Display extends MOElementDisplay, IMOHasFence, IMOHasSign {
	}

	public MOSignedElement() {
		super(new MOSignedElementDisplay());
	}
	
	public MOSignedElement(MOElement<?> child) {
		this();
		setChild(child);
	}
	
	public MOSignedElement(MOElement<?> child, boolean isMinus) {
		this(child);
		setMinus(isMinus);
	}
	
	@Override
	public void pack(MMLElement mathMLParent, MOAbtractPresenter<?> presenter) {
		boolean needsFence = needsFence();
		if(needsFence) {
			display.setLFence(MMLOperator.lFence());
			mathMLParent.appendChild(display.getLFence());
			if(presenter!=null)
				presenter.putDOMElement(display.getLFence().getElement(),this);
		}
		else
			display.setLFence(null);
		if(needsSign||isMinus) {
			display.setSign(new MMLOperator(isMinus?"-":"+"));
			mathMLParent.appendChild(display.getSign());
			if(presenter!=null)
				presenter.putDOMElement(display.getSign().getElement(),this);
		}
		else
			display.setSign(null);
		child.pack(mathMLParent, presenter);
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
	public MOSignedElement clone() {
		return new MOSignedElement(child.clone(),isMinus);
	}

	@Override
	public void setStyleClass(short styleClass) {
		this.styleClass = styleClass;
		if(display.getLFence()!=null)
			display.getLFence().setStyleClass(styleClass);
		if(display.getRFence()!=null)
			display.getRFence().setStyleClass(styleClass);
		if(display.getSign()!=null)
			display.getSign().setStyleClass(styleClass);
		child.setStyleClass(styleClass);
	}

	@Override
	public  MOElement<?> getMathObjectFirstSelectableChild() {
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
	public int getBoundingClientBottom() {
		if(display.getLFence()!=null)
			return (int) (display.getLFence().getBoundingClientTop() + display.getLFence().getBoundingClientHeight());
		else 
			return child.getBoundingClientBottom();
	}

	@Override
	public int getBoundingClientLeft() {
		if(display.getLFence()!=null)
			return (int) display.getLFence().getBoundingClientLeft();
		else if(display.getSign()!=null)
			return (int) display.getSign().getBoundingClientLeft();
		else 
			return child.getBoundingClientLeft();
	}

	@Override
	public int getBoundingClientRight() {
		if(display.getRFence()!=null)
			return (int) (display.getRFence().getBoundingClientLeft() + display.getRFence().getBoundingClientWidth());
		else
			return child.getBoundingClientRight();
	}

	@Override
	public int getBoundingClientTop() {
		if(display.getLFence()!=null)
			return (int) display.getLFence().getBoundingClientTop();
		else
			return child.getBoundingClientTop();
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

	public boolean isMinus() {
		return isMinus;
	}

	public void setMinus(boolean isMinus) {
		this.isMinus = isMinus;
	}		

	public boolean needsSign() {
		return needsSign;
	}

	public void setNeedsSign(boolean needsSign) {
		this.needsSign = needsSign;
	}

//	@Override
//	public com.google.gwt.dom.client.Element getFirstDOMElement() {
//			return display.getLFence().getElement();
//		if(display.getSign()!=null)
//			return display.getSign().getElement();
//		return child.getFirstDOMElement();
//	}
//
//	@Override
//	public com.google.gwt.dom.client.Element getLastDOMElement() {
//		if(display.getRFence()!=null)
//			return display.getRFence().getElement();
//		return child.getLastDOMElement();
//	}		

	
	public static MOSignedElement parse(Element element){
		assert element.getTagName().equals("mose");		
		assert element.getFirstChild().getNodeType() == Node.ELEMENT_NODE;
		if(element.hasAttribute("isMinus")) {
			return new MOSignedElement(MOElement.parse((Element) element.getFirstChild()), Boolean.parseBoolean(element.getAttribute("isMinus")));
		}
		else
			return new MOSignedElement(MOElement.parse((Element) element.getFirstChild()));
	}

}
