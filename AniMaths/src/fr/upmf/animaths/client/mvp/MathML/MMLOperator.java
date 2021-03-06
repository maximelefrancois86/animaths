/*The contents of this file are subject to the Mozilla Public License
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

public class MMLOperator extends MMLGenericAtom {
       
    static String elementName="mo";

    public MMLOperator(String n) {
            super(elementName, n);
    }

    public static MMLOperator equality() {
            return new MMLOperator("=");
    }
    
    public static MMLOperator times() {
    	return new MMLOperator("×");    	
    }
    
    public static MMLOperator invisibleTimes() {
    	return new MMLOperator("⋅");
    }
    
    public static MMLOperator lFence() {
    	MMLOperator lFence = new MMLOperator("(");
    	lFence.getElement().setAttribute("form","prefix");
    	lFence.getElement().setAttribute("fence","true");
    	lFence.getElement().setAttribute("stretchy","true");
    	lFence.getElement().setAttribute("symmetric","true");
    	lFence.getElement().setAttribute("lspace","thinmathspace");
    	return lFence;
    }

    public static MMLOperator rFence() {
    	MMLOperator rFence = new MMLOperator(")");
    	rFence.getElement().setAttribute("form","postfix");
    	rFence.getElement().setAttribute("fence","true");
    	rFence.getElement().setAttribute("stretchy","true");
    	rFence.getElement().setAttribute("symmetric","true");
    	rFence.getElement().setAttribute("rspace","thinmathspace");
    	return rFence;
    }

//    private static MathMLOperator localization = new MathMLOperator("|");
//    static {
//    	localization.getElement().setAttribute("symmetric","false");
//    	localization.getElement().setAttribute("stretchy","true");
//    }
//    
//    public static MathMLOperator getLocalisation(String color) {
//    	localization.getElement().setAttribute("style","color:"+color+";");
//    	return localization;
//    }
    
	@Override
	public MMLElement clone() {
		String text = getElement().getInnerText();
		if(text.equals("("))
			return MMLOperator.lFence();
		if(text.equals(")"))
			return MMLOperator.rFence();
		return new MMLOperator(text);
	}
}

