package fr.upmf.animaths.client.mvp.MathObject;

import net.customware.gwt.presenter.client.BasicPresenter;
import net.customware.gwt.presenter.client.place.Place;
import net.customware.gwt.presenter.client.place.PlaceRequest;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;

import com.google.gwt.user.client.ui.Widget;

import fr.upmf.animaths.client.mvp.AniMathsPresenter;
import fr.upmf.animaths.client.mvp.MathObjectPresenter;
import fr.upmf.animaths.client.mvp.MathML.MathMLElement;

/**
 * Abstract super-class for {@link BasicPresenter}s that work with GWT
 * {@link Widget}s via {@link WidgetDisplay}s.
 * 
 * @author Maxime Lefrançois
 * 
 * @param <D>
 *            The {@link WidgetDisplay} type.
 */
public abstract class MathObjectElementPresenter<D extends MathObjectElementDisplay> extends BasicPresenter<D>
												implements IMathObjectHasType, IMathObjectHasStyleClass, IMathObjectHasStyleDrop, IMathObjectHasZones {
	
	public short styleClass = STYLE_CLASS_NONE;
	@Override
	public short getStyleClass() {
		return styleClass;
	}

//	public short styleDrop = STYLE_DROP_NONE;
//	public short getStyle() {
//		return styleDrop;
//	}
	
	public static final Place PLACE = new Place("MathObject");

	@Override
	public Place getPlace() {
		return PLACE;
	}
	
	public MathObjectElementPresenter(D display) {
        super(display, AniMathsPresenter.eventBus );
    }
    
	protected MathObjectElementPresenter<?> mathObjectParent = null;
	
	public MathObjectElementPresenter<?> getMathObjectParent() {
		if(mathObjectParent==null)
			return this;
		return mathObjectParent;
	}

	public void setMathObjectParent(MathObjectElementPresenter<?> mathObjectParent) {
		this.mathObjectParent = mathObjectParent;
	}
	
	abstract public MathObjectElementPresenter<?> getMathObjectFirstChild();
	abstract public MathObjectElementPresenter<?> getMathObjectPreviousChild(MathObjectElementPresenter<?> child);
	abstract public MathObjectElementPresenter<?> getMathObjectNextChild(MathObjectElementPresenter<?> child);
	

	abstract public MathObjectElementPresenter<D> clone();

	abstract public void pack(MathMLElement mathMLParent, MathObjectPresenter<?> presenter);
	    	
	@Override
	protected void onBind() {
	}

	@Override
	protected void onPlaceRequest(PlaceRequest request) {
	}

	@Override
	protected void onUnbind() {
	}

	@Override
	public void refreshDisplay() {
	}

	@Override
	public void revealDisplay() {
	}

	public boolean needsFence() {
		if(mathObjectParent!=null) {
			short typeP = mathObjectParent.getType();
			switch (this.getType()) {
			case MATH_OBJECT_SIGNED_ELEMENT:
				if (typeP == MATH_OBJECT_SIGNED_ELEMENT
						|| typeP == MATH_OBJECT_POWER
						|| typeP == MATH_OBJECT_MULTIPLY_ELEMENT)
					return true;
				return false;
			case MATH_OBJECT_ADDITION_CONTAINER:
				if (typeP == MATH_OBJECT_SIGNED_ELEMENT
						|| typeP == MATH_OBJECT_POWER
						|| typeP == MATH_OBJECT_MULTIPLY_ELEMENT)
					return true;
				return false;
			case MATH_OBJECT_MULTIPLY_CONTAINER:
				if (typeP == MATH_OBJECT_MULTIPLY_ELEMENT
						|| typeP == MATH_OBJECT_POWER
						|| typeP == MATH_OBJECT_MULTIPLY_ELEMENT)
					return true;
				return false;
			case MATH_OBJECT_POWER:
				if (typeP == MATH_OBJECT_POWER)
					return true;
				else
					return false;
			default:
				return false;
			}
		}
		else
			return false;
	}
	
	abstract public int getBoundingClientLeft();
	abstract public int getBoundingClientTop();
	abstract public int getBoundingClientRight();
	abstract public int getBoundingClientBottom();
	
	
//	abtract protected short isFlyOver(int x, int y) {
////		int left = getBoundingClientLeft();
////		int right = (int) (0.75*left + 0.25*getBoundingClientRight());
////		return (left<x && x<right && getBoundingClientBottom()<x && x<getBoundingClientTop());
//		return 0;
//	}

}
