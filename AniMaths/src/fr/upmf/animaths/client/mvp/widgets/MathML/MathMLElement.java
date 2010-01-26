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

package fr.upmf.animaths.client.mvp.widgets.MathML;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;

import fr.upmf.animaths.client.mvp.modele.MathObject.MathObjectElement;

public abstract class MathMLElement extends Widget {

	private static MathMLImpl impl = MathMLImpl.getInstance();
	public static Map<Element,MathMLElement> map = new HashMap<Element,MathMLElement>();

	private MathObjectElement mathObjectElement;
	private Map<String,String> style = new HashMap<String,String>();

	public MathMLElement(String elementName, MathObjectElement element) {
		setElement(impl.createElement(elementName));
		setMathObjectElement(element);
		map.put(getElement(), this);
		setState(MathObjectElement.STATE_NONE);
	}

	public MathObjectElement getMathObjectElement() {
		return mathObjectElement;
	}

	public void setMathObjectElement(MathObjectElement element) {
		this.mathObjectElement = element;
	}

	public void appendChild(MathMLElement child) {
		if (child != null)
			getElement().appendChild(child.getElement());
	}

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

	public void setState(short state) {
		switch(state) {
			case MathObjectElement.STATE_NONE :
				removeStyleAttribute("background-color");
				break;
			case MathObjectElement.STATE_SELECTABLE :
				setStyleAttribute("background-color","#DDDDFF");
				break;
			case MathObjectElement.STATE_SELECTED :
				setStyleAttribute("background-color","#AAAAFF");
				break;
		}
		getElement().setAttribute("state",Integer.toString(state));
	}
	
	public short getState() {
		return (short) Integer.parseInt(getElement().getAttribute("state"));
	}

	abstract public MathMLElement clone(MathObjectElement element);
	
	public native float getBoundingClientLeft() /*-{
     return this.@fr.upmf.animaths.client.mvp.widgets.MathML.MathMLElement::getElement()().getBoundingClientRect().left;
 }-*/;

	 public native float getBoundingClientTop() /*-{
     return this.@fr.upmf.animaths.client.mvp.widgets.MathML.MathMLElement::getElement()().getBoundingClientRect().top;
 }-*/;

	 public native float getBoundingClientWidth() /*-{
     return this.@fr.upmf.animaths.client.mvp.widgets.MathML.MathMLElement::getElement()().getBoundingClientRect().width;
 }-*/;

	 public native float getBoundingClientHeight() /*-{
     return this.@fr.upmf.animaths.client.mvp.widgets.MathML.MathMLElement::getElement()().getBoundingClientRect().height;
 }-*/;

}
