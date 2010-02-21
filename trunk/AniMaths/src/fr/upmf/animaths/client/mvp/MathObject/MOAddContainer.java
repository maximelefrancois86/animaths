package fr.upmf.animaths.client.mvp.MathObject;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NodeList;

import fr.upmf.animaths.client.mvp.MOAbtractPresenter;
import fr.upmf.animaths.client.mvp.MathML.MMLElement;
import fr.upmf.animaths.client.mvp.MathML.MMLOperator;

public class MOAddContainer extends MOElement<MOAddContainer.Display> implements IMOHasSeveralChildren<MOSignedElement> {

	public static final short type = MOElement.MATH_OBJECT_ADDITION_CONTAINER;
	@Override
	public short getType() {
		return type;
	}

	private List<MOSignedElement> children = new ArrayList<MOSignedElement>();
	
	public interface Display extends MOElementDisplay, IMOHasFence {
	}

	public MOAddContainer() {
		super(new MOAddContainerDisplay());
	}

	public MOAddContainer(MOSignedElement ... children) {
		this();
		for(MOSignedElement child : children)
			add(child);
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
		setNeedsSigns();
		for(MOSignedElement child : children)
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
	public MOAddContainer clone() {
		MOAddContainer object = new MOAddContainer();
		for(MOSignedElement child : children)
			object.add(child.clone());
		return object;
	}

	@Override
	public void setStyleClass(short styleClass) {
		this.styleClass = styleClass;
		if(display.getLFence()!=null)
			display.getLFence().setStyleClass(styleClass);
		if(display.getRFence()!=null)
			display.getRFence().setStyleClass(styleClass);
		for(MOSignedElement child : children)
			child.setStyleClass(styleClass);
	}

	@Override
	public MOElement<?> getMathObjectFirstSelectableChild() {
		return children.get(0);
	}

	@Override
	public MOElement<?> getMathObjectNextSelectableChild(MOElement<?> child) {
		int i = children.indexOf(child);
		if(i!=children.size()-1)
			return children.get(i+1);
		return children.get(0);
	}

	@Override
	public MOElement<?> getMathObjectPreviousSelectableChild(MOElement<?> child) {
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
			for(MOSignedElement element : children)
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
			for(MOSignedElement element : children)
				i = Math.max(i,element.getBoundingClientBottom());
			return i;
		}
	}

	public int size() {
		return children.size();		
	}

	@Override
	public void add(MOSignedElement child) {
		child.setMathObjectParent(this);
		children.add(child);
	}

	public void add(int index, MOSignedElement child) {
		child.setMathObjectParent(this);
		children.add(index, child);
	}

	@Override
	public void add(MOSignedElement child, MOSignedElement refChild, boolean after) {
		int index = children.indexOf(refChild);
		assert index!=-1;
		if(after)
			index++;
		add(index, child);
	}

	public MOSignedElement get(int index) {
		return children.get(index);
	}

	@Override
	public void remove(MOSignedElement child) {
		children.remove(child);
	}

//	@Override
//	public Element getFirstDOMElement() {
//		if(display.getLFence()!=null)
//			return display.getLFence().getElement();
//		return children.get(0).getFirstDOMElement();
//	}
//
//	@Override
//	public Element getLastDOMElement() {
//		if(display.getRFence()!=null)
//			return display.getRFence().getElement();
//		return children.get(children.size()-1).getLastDOMElement();
//	}

	public void changeSign() {
		for(MOSignedElement child: children)
			child.setMinus(!child.isMinus());
	}
	
	private void setNeedsSigns() {
		children.get(0).setNeedsSign(false);
		for(int i=1;i<children.size();i++)
			children.get(i).setNeedsSign(true);
	}

	public static MOAddContainer parse(Element element){
		assert element.getTagName().equals("moac");
		MOAddContainer moac = new MOAddContainer();
		NodeList children = element.getChildNodes();
		for(int i=0;i<children.getLength();i++) {
			assert children.item(i).getNodeType() == Node.ELEMENT_NODE;
			moac.add((MOSignedElement) MOSignedElement.parse((Element) children.item(i)));
		}
		return moac;
	}
	
}	

