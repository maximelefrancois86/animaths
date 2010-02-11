/*
The contents of this file are subject to the Mozilla Public License
Version 1.1 (the "License"); you may not use this file except in
compliance with the License. You may obtain a copy of the License at
http://www.mozilla.org/MPL/

Software distributed under the License is distributed on an "AS IS"
basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
License for the specific language governing rights and limitations
under the License.

The Original Code is "mathml-in-gwt, an implementation of MathML in GWT".

The Initial Developer of the Original Code is Jonathan Wong.
Portions created by the Initial Developer are Copyright (C) 2008. All Rights Reserved.

Contributor(s): all the names of the contributors are added in the source code where applicable.

Alternatively, the contents of this file may be used under the terms
of the GPL license (the  "General Public License"), in which case the
provisions of GPL License are applicable instead of those
above. If you wish to allow use of your version of this file only
under the terms of the GPL License and not to allow others to use
your version of this file under the MPL, indicate your decision by
deleting the provisions above and replace them with the notice and
other provisions required by the GPL License. If you do not delete
the provisions above, a recipient may use your version of this file
under either the MPL or the GPL License."
 */

package fr.upmf.animaths.client.mvp.MathML;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;

import fr.upmf.animaths.client.mvp.MathObject.MOElement;

public abstract class MMLElement extends Widget {

	private static MMLImpl impl = MMLImpl.getInstance();
	
	private static final ArrayList<String> styleClasses = new ArrayList<String>();
	static {
		styleClasses.add(MOElement.STYLE_CLASS_NONE,"");
		styleClasses.add(MOElement.STYLE_CLASS_SELECTABLE,"selectable");
		styleClasses.add(MOElement.STYLE_CLASS_SELECTED,"selected");
		styleClasses.add(MOElement.STYLE_CLASS_DRAGGED,"dragged");
		styleClasses.add(MOElement.STYLE_CLASS_OK_ADD,"okAdd");
		styleClasses.add(MOElement.STYLE_CLASS_OK_DROP,"okDrop");
		styleClasses.add(MOElement.STYLE_CLASS_NO_DROP,"noDrop");
		styleClasses.add(MOElement.STYLE_CLASS_OK,"ok");
		styleClasses.add(MOElement.STYLE_CLASS_CAUTION,"caution");
		styleClasses.add(MOElement.STYLE_CLASS_NO,"no");
	};

	private Map<String,String> style = new HashMap<String,String>();

	
	public MMLElement(String elementName) {
		setElement(impl.createElement(elementName));
		setStyleClass(MOElement.STYLE_CLASS_NONE);
		disableContextMenu(getElement());
	}

	public void appendChild(MMLElement child) {
		if (child != null)
			getElement().appendChild(child.getElement());
	}

	public void setStyleClass(short styleClass) {
		if(styleClass==0)
			getElement().removeAttribute("class");
		else
			getElement().setAttribute("class",styleClasses.get((int) styleClass));
	}
	
	public short getStyleClass() {
		return (short) styleClasses.indexOf(getElement().getAttribute("class"));
	}

	abstract public MMLElement clone();
	
	public native float getBoundingClientLeft() /*-{
     return this.@fr.upmf.animaths.client.mvp.MathML.MMLElement::getElement()().getBoundingClientRect().left;
 }-*/;

	 public native float getBoundingClientTop() /*-{
     return this.@fr.upmf.animaths.client.mvp.MathML.MMLElement::getElement()().getBoundingClientRect().top;
 }-*/;

	 public native float getBoundingClientWidth() /*-{
     return this.@fr.upmf.animaths.client.mvp.MathML.MMLElement::getElement()().getBoundingClientRect().width;
 }-*/;

	 public native float getBoundingClientHeight() /*-{
     return this.@fr.upmf.animaths.client.mvp.MathML.MMLElement::getElement()().getBoundingClientRect().height;
 }-*/;

	public void setStyleAttribute(String key, String value) {
		style.put(key,value);
		updateStyle();		
	}
	
	public void removeStyleAttribute(String key) {
		style.remove(key);
		updateStyle();		
	}
	
	public void updateStyle() {
		String value="";
		for ( Iterator<Entry<String,String>> iter = style.entrySet().iterator(); iter.hasNext(); ) {
			Entry<String,String> ent = (Entry<String,String>) iter.next();
			value = value + ent.getKey() +":"+ent.getValue()+";";			
		}
		getElement().setAttribute("style", value);
	}

  /**
   * Disables the browsers default context menu for the specified element.
   * @param elem the element whose context menu will be disabled
   */
	public static native void disableContextMenu(Element elem) /*-{
		elem.oncontextmenu=function() {  return false};
	}-*/;

//	public void setStyle(short style) {
//	this.style = new HashMap<String,String>();
//	switch(style) {
//	case MathObjectElementPresenter.STATE_NONE:
//		break;
//	case MathObjectElementPresenter.STYLE_OK_LEFT:
//		setStyleAttribute("border-left","plain 2px green");
//		break;
//	case MathObjectElementPresenter.STYLE_OK_RIGHT:
//		setStyleAttribute("border-right","plain 2px green");
//		break;
//	case MathObjectElementPresenter.STYLE_OK_TOP:
//		setStyleAttribute("border-top","plain 2px green");
//		break;
//	case MathObjectElementPresenter.STYLE_OK_BOTTOM:
//		setStyleAttribute("border-bottom","plain 2px green");
//		break;
//	case MathObjectElementPresenter.STYLE_WARNING_LEFT:
//		setStyleAttribute("border-left","plain 2px orange");
//		break;
//	case MathObjectElementPresenter.STYLE_WARNING_RIGHT:
//		setStyleAttribute("border-right","plain 2px orange");
//		break;
//	case MathObjectElementPresenter.STYLE_WARNING_TOP:
//		setStyleAttribute("border-top","plain 2px orange");
//		break;
//	case MathObjectElementPresenter.STYLE_WARNING_BOTTOM:
//		setStyleAttribute("border-bottom","plain 2px orange");
//		break;
//	}
//	
//	updateStyle();
//}

}
