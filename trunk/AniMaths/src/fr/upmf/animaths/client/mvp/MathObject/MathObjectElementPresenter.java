package fr.upmf.animaths.client.mvp.MathObject;

import net.customware.gwt.presenter.client.BasicPresenter;
import net.customware.gwt.presenter.client.place.Place;
import net.customware.gwt.presenter.client.place.PlaceRequest;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;

import fr.upmf.animaths.client.mvp.AniMathsPresenter;
import fr.upmf.animaths.client.mvp.MathObjectAbtractPresenter;
import fr.upmf.animaths.client.mvp.MathML.MathMLElement;

/**
 * Abstract super-class for {@link BasicPresenter}s that work with GWT
 * {@link Widget}s via {@link WidgetDisplay}s.
 * 
 * @author Maxime Lefranï¿½ois
 * 
 * @param <D>
 *            The {@link WidgetDisplay} type.
 */
public abstract class MathObjectElementPresenter<D extends MathObjectElementDisplay> extends BasicPresenter<D>
												implements IMathObjectHasType, IMathObjectHasStyleClass, IMathObjectHasZones, IMathObjectSelection {
	
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

	@Override
	public MathObjectElementPresenter<?> getMathObjectSelectableElement() {
		MathObjectElementPresenter<?> moP = getMathObjectParent();
		if(moP.getType()==MATH_OBJECT_SIGNED_ELEMENT
				|| moP.getType()==MATH_OBJECT_MULTIPLY_ELEMENT)
			return moP;
		return this;
	}

	@Override
	public MathObjectElementPresenter<?> getMathObjectFirstSelectableParent() {
		return getMathObjectParent().getMathObjectSelectableElement();
	}

	abstract public MathObjectElementPresenter<D> clone();

	abstract public void pack(MathMLElement mathMLParent, MathObjectAbtractPresenter<?> presenter);
	    	
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
						|| typeP == MATH_OBJECT_MULTIPLY_CONTAINER
						|| typeP == MATH_OBJECT_POWER)
					return true;
				return false;
			case MATH_OBJECT_ADDITION_CONTAINER:
				if (typeP == MATH_OBJECT_SIGNED_ELEMENT
						|| typeP == MATH_OBJECT_MULTIPLY_ELEMENT
						|| typeP == MATH_OBJECT_POWER)
					return true;
				return false;
			case MATH_OBJECT_MULTIPLY_CONTAINER:
				if (typeP == MATH_OBJECT_MULTIPLY_ELEMENT
						|| typeP == MATH_OBJECT_POWER)
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
	
	@Override
	public short getZone(int x, int y) {
		int top = getBoundingClientTop();
		int right = getBoundingClientRight();
		int bottom = getBoundingClientBottom();
		int left = getBoundingClientLeft();
		if(x<left) // en dehors à gauche
			return ZONE_NONE;
		else
			if(x<left+10) // couloir OUEST
				if(y<top) // en dehors au dessus
					return ZONE_NONE;
				else
					if(y<top+10) //partie Nord-Ouest
						return ZONE_IN_NO;
					else
						if(y<bottom-10) //partie Ouest
							return ZONE_IN_O;
						else
							if(y<bottom) // partie Sud-ouest
								return ZONE_IN_SO;
							else // en dehors au dessous
								return ZONE_NONE;
			else
				if(x<right-10) // couloir CENTRE
					if(y<top) // en dehors au dessus
						return ZONE_NONE;
					else
						if(y<top+10) //partie Nord
							return ZONE_IN_N;
						else
							if(y<bottom-10) //partie Centre
								return ZONE_IN_C;
							else
								if(y<bottom) // partie Sud
									return ZONE_IN_S;
								else // en dehors au dessous
									return ZONE_NONE;
				else
					if(x>right) // en dehors à droite
						return ZONE_NONE;
					else // couloir EST
						if(y<top) // en dehors au dessus
							return ZONE_NONE;
						else
							if(y<top+10) //partie Nord
								return ZONE_IN_NE;
							else
								if(y<bottom-10) //partie Centre
									return ZONE_IN_E;
								else
									if(y<bottom) // partie Sud
										return ZONE_IN_SE;
									else // en dehors au dessous
										return ZONE_NONE;
	}

	abstract public Element getFirstDOMElement();
	abstract public Element getLastDOMElement();

}
