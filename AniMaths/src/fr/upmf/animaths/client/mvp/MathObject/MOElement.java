package fr.upmf.animaths.client.mvp.MathObject;

import net.customware.gwt.presenter.client.BasicPresenter;
import net.customware.gwt.presenter.client.place.Place;
import net.customware.gwt.presenter.client.place.PlaceRequest;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;

import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.xml.client.Element;

import fr.upmf.animaths.client.mvp.AniMathsPresenter;
import fr.upmf.animaths.client.mvp.MOAbstractPresenter;
import fr.upmf.animaths.client.mvp.MathML.MMLElement;

/**
 * Abstract super-class for {@link BasicPresenter}s that work with GWT
 * {@link Widget}s via {@link WidgetDisplay}s.
 * 
 * @author Maxime Lefran�ois
 * 
 * @param <D>
 *            The {@link WidgetDisplay} type.
 */
public abstract class MOElement<D extends MOElementDisplay> extends BasicPresenter<D>
												implements IMOHasType, IMOHasStyleClass, IMOHasZones, IMOSelection {
	
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
	
	public MOElement(D display) {
        super(display, AniMathsPresenter.eventBus );
    }
    
	protected MOElement<?> mathObjectParent = null;
	
	public MOElement<?> getMathObjectParent() {
		if(mathObjectParent==null)
			return this;
		return mathObjectParent;
	}

	public void setMathObjectParent(MOElement<?> mathObjectParent) {
		this.mathObjectParent = mathObjectParent;
	}	

	@Override
	public MOElement<?> getMathObjectSelectableElement() {
		MOElement<?> moP = getMathObjectParent();
		if((moP.getType()==MATH_OBJECT_SIGNED_ELEMENT && getType()!=MATH_OBJECT_SIGNED_ELEMENT)
				|| moP.getType()==MATH_OBJECT_MULTIPLY_ELEMENT)
			return moP.getMathObjectSelectableElement();
		return this;
	}

	@Override
	public MOElement<?> getMathObjectFirstSelectableParent() {
		if(getMathObjectParent() instanceof MOEquation)
			return this;
		return getMathObjectParent().getMathObjectSelectableElement();
	}

	abstract public MOElement<D> clone();

	abstract public void pack(MMLElement mathMLParent, MOAbstractPresenter<?> presenter);
	    	
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
						|| typeP == MATH_OBJECT_MULTIPLY_ELEMENT
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
	public short getZoneH(int x) {
		int px = 10;
		int pc = 5;
		int left = getBoundingClientLeft();
		int right = getBoundingClientRight();
		int center = (int)((left+right)/2);
		int d = Math.min(px,(int)((right-left)/pc));
		return getZone(x,left,center,right,d,d);
	}

	@Override
	public short getZoneV(int y) {
		int px = 10;
		int pc = 5;
		int top = getBoundingClientTop();
		int bottom = getBoundingClientBottom();
		int center = (int)((top+bottom)/2);
		int d = Math.min(px,(int)((bottom-top)/pc));
		return getZone(y,top,center,bottom,d,d);
	}

	/**
	 * Permet de d�terminer la zone o� le curseur est, suivant un axe.
	 * Bien qu'elle soit �crite pour l'axe vertical, cette fonction se comporte bien pour l'axe horizontal
	 * @param y emplacement du pointer
	 * @param top centre de la zone limite
	 * @param center centre de la zone centrale
	 * @param bottom centre de la zone limite
	 * @param dIn tol�rence autour du centre de la zone centrale
	 * @param dOut tol�rence autour du centre de la zone limite
	 * @return une des 7 zones o� on est
	 */
	protected short getZone(int y, int top, int center, int bottom, int dIn, int dOut) {
		if(y<top-dOut) // en dehors � gauche
			return ZONE_NNN;
		else if(y<top+dOut)
			return ZONE_NN;
		else if(y<center-dIn)
			return ZONE_N;
		else if(y<center+dIn)
			return ZONE_CENTER;
		else if(y<bottom-dOut)
			return ZONE_S;
		else if(y<bottom+dOut)
			return ZONE_SS;
		else
			return ZONE_SSS;
	}

//	abstract public com.google.gwt.dom.client.Element getFirstDOMElement();
//	abstract public com.google.gwt.dom.client.Element getLastDOMElement();

	public static MOElement<?> parse(Element element) {
		System.out.println(element.getNodeValue());
		String tagName = element.getTagName();
		if(tagName.equals("moe"))
			return MOEquation.parse(element);
		if(tagName.equals("moi"))
			return MOIdentifier.parse(element);
		if(tagName.equals("mon"))
			return MONumber.parse(element);
		if(tagName.equals("mose"))
			return MOSignedElement.parse(element);
		if(tagName.equals("moac"))
			return MOAddContainer.parse(element);
		if(tagName.equals("momc"))
			return MOMultiplyContainer.parse(element);
		if(tagName.equals("mome"))
			return MOMultiplyElement.parse(element);
		return null;
	}

	public boolean hasMathObjectAncestor(MOElement<?> ancestor) {
		MOElement<?> element1 = this;
		MOElement<?> element2;
		while(true) {
			element2 = element1;
			element1 = element1.getMathObjectParent();
			if(element1==ancestor)
				return true;
			else if(element1==null || element1==element2)
				return false;
		}
	}
}
