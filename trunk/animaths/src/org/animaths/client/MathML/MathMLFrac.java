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

package org.animaths.client.MathML;


public class MathMLFrac extends MathMLGenericTwoChild {
	
	static String elementName="mfrac";

	public MathMLFrac() {
		super(elementName);
	}
	
	public MathMLFrac(MathMLElement child1, MathMLElement child2) {
		super(elementName, child1, child2);
	}

}
