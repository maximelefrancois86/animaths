package fr.upmf.animaths.client.mvp.presenter.MathObject;

import fr.upmf.animaths.client.mvp.display.MathML.MathMLElement;
import fr.upmf.animaths.client.mvp.display.MathML.MathMLOperator;
import fr.upmf.animaths.client.mvp.display.MathObject.IMathObjectHasFence;
import fr.upmf.animaths.client.mvp.display.MathObject.IMathObjectHasSign;
import fr.upmf.animaths.client.mvp.display.MathObject.MathObjectElementDisplay;
import fr.upmf.animaths.client.mvp.display.MathObject.MathObjectSignedElementDisplay;
import fr.upmf.animaths.client.mvp.presenter.MathObjectPresenter;

public class MathObjectSignedElementPresenter extends MathObjectElementPresenter<MathObjectSignedElementPresenter.Display> implements IMathObjectHasOneChild {

	public static final short type = MathObjectElementPresenter.MATH_OBJECT_SIGNED_ELEMENT;
	public short getType() {
		return type;
	}
	
	private boolean isMinus = false;
	private boolean needsSign = true;
	private MathObjectElementPresenter<?> child;
	
	public interface Display extends MathObjectElementDisplay, IMathObjectHasFence, IMathObjectHasSign {
	}

	public MathObjectSignedElementPresenter() {
		super(new MathObjectSignedElementDisplay());
	}
	
	public MathObjectSignedElementPresenter(MathObjectElementPresenter<?> child) {
		this();
		setChild(child);
	}
	
	public MathObjectSignedElementPresenter(MathObjectElementPresenter<?> child, boolean isMinus) {
		this(child);
		setMinus(isMinus);
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
		if(needsSign||isMinus) {
			display.setSign(new MathMLOperator(isMinus?"-":"+"));
			mathMLParent.appendChild(display.getSign());
			if(presenter!=null)
				presenter.putDOMElement(display.getSign().getElement(),this);
		}
		child.pack(mathMLParent, presenter);
		if(needsFence) {
			display.setRFence(MathMLOperator.rFence());
			mathMLParent.appendChild(display.getRFence());
			if(presenter!=null)
				presenter.putDOMElement(display.getRFence().getElement(),this);
		}
	}

	@Override
	public MathObjectSignedElementPresenter clone() {
		return new MathObjectSignedElementPresenter(child.clone(),isMinus);
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
	public  MathObjectElementPresenter<?> getMathObjectFirstChild() {
		if(child.getType()==MathObjectElementPresenter.MATH_OBJECT_NUMBER
				||child.getType()==MathObjectElementPresenter.MATH_OBJECT_IDENTIFIER)
			return this;
		else
			return child.getMathObjectFirstChild();
	}

	@Override
	public MathObjectElementPresenter<?> getMathObjectNextChild(MathObjectElementPresenter<?> child) {
		return mathObjectParent.getMathObjectNextChild(this);
	}

	@Override
	public MathObjectElementPresenter<?> getMathObjectPreviousChild(MathObjectElementPresenter<?> child) {
		return mathObjectParent.getMathObjectPreviousChild(this);
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
	public MathObjectElementPresenter<?> getChild() {
		return child;
	}
	
	@Override
	public void setChild(MathObjectElementPresenter<?> child) {
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

}
