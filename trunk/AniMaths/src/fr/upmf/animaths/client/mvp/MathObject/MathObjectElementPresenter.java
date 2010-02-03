package fr.upmf.animaths.client.mvp.MathObject;

import net.customware.gwt.presenter.client.BasicPresenter;
import net.customware.gwt.presenter.client.place.Place;
import net.customware.gwt.presenter.client.place.PlaceRequest;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;

import com.google.gwt.user.client.ui.Widget;

import fr.upmf.animaths.client.mvp.AniMathsPresenter;
import fr.upmf.animaths.client.mvp.MathObjectPresenter;
import fr.upmf.animaths.client.mvp.widgets.MathML.MathMLElement;

/**
 * Abstract super-class for {@link BasicPresenter}s that work with GWT
 * {@link Widget}s via {@link WidgetDisplay}s.
 * 
 * @author Maxime Lefrançois
 * 
 * @param <D>
 *            The {@link WidgetDisplay} type.
 */
public abstract class MathObjectElementPresenter<D extends MathObjectElementView> extends BasicPresenter<D> {
	
	public static final short MATH_OBJECT_WRAPPER = 0;
	public static final short MATH_OBJECT_EQUATION = 1;
	public static final short MATH_OBJECT_NUMBER = 2;
	public static final short MATH_OBJECT_IDENTIFIER = 3;
	public static final short MATH_OBJECT_SIGNED_ELEMENT = 4;
	public static final short MATH_OBJECT_ADDITION_CONTAINER = 5;
	public static final short MATH_OBJECT_MULTIPLY_ELEMENT = 6;
	public static final short MATH_OBJECT_MULTIPLY_CONTAINER = 7;
	public static final short MATH_OBJECT_POWER = 8;

	abstract public short getType();

	public static final short STATE_NONE = 0;
	public static final short STATE_SELECTABLE = 1;
	public static final short STATE_SELECTED = 2;
	public static final short STATE_DRAGGED = 3;

	public short state = STATE_NONE;
	abstract public void setState(short state);
	public short getState() {
		return state;
	}

	public static final Place PLACE = new Place("MathObject");

	@Override
	public Place getPlace() {
		return PLACE;
	}
	
	public MathObjectElementPresenter(D display) {
        super( display, AniMathsPresenter.eventBus );
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

}
