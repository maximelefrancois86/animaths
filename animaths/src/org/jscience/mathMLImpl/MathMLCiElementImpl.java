package org.jscience.mathMLImpl;

import org.w3c.dom.mathML.MathMLCiElement;


/**
 * Implements a MathML content identifier element.
 *
 * @author Mark Hale
 * @version 1.0
 */
public class MathMLCiElementImpl extends MathMLContentTokenImpl
    implements MathMLCiElement {
/**
     * Constructs a MathML content identifier element.
     *
     * @param owner         DOCUMENT ME!
     * @param qualifiedName DOCUMENT ME!
     */
    public MathMLCiElementImpl(MathMLDocumentImpl owner, String qualifiedName) {
        super(owner, qualifiedName);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getType() {
        return getAttribute("type");
    }

    /**
     * DOCUMENT ME!
     *
     * @param type DOCUMENT ME!
     */
    public void setType(String type) {
        setAttribute("type", type);
    }
}
